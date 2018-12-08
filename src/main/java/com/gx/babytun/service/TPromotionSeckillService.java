package com.gx.babytun.service;

import com.gx.babytun.entity.TPromotionSeckill;
import com.gx.babytun.mapper.TPromotionSeckillMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 谷鑫 G x
 * @Classname TPromotionSeckillServiceImpl
 * @Describe:
 * @date 2018/10/19 17:22
 */
@Service
public class TPromotionSeckillService {
    @Resource
    private TPromotionSeckillMapper tPromotionSeckillMapper;
    @Resource
    private RedisTemplate<Object, Object> redisTemplate;
    @Resource
    private RabbitTemplate rabbitTemplate;
    RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean isOk, String body) {
            if (isOk) {
                System.out.println("消息已经被接收 Tag" + correlationData.getId());
            } else {
                System.err.println("==========================");
                System.err.println("消息被拒收：TagId=" + correlationData.getId() + "==描述：" + body);
                System.err.println("==========================");
            }
        }
    };
    RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        //退回的消息，
        //错误编码，
        //错误的描述
        //交换机名称
        //路由名称
        @Override
        public void returnedMessage(Message message, int i, String s, String s1, String s2) {
            System.err.println("==========================");
            System.err.println("消息被退回，消息：" + message + "\t 错误编码：" + i + "\t 原因:" + s + "\t 交换机" + s1);
            System.err.println("==========================");
        }
    };

    public int updateByPrimaryKeySelective(TPromotionSeckill record) {
        return tPromotionSeckillMapper.updateByPrimaryKeySelective(record);
    }

    public List<TPromotionSeckill> findPromotionByStatus0() {
        return tPromotionSeckillMapper.findPromotionByStatus0();
    }

    public void SeckillGoods(long id, String userid, int num) throws Exception {
        TPromotionSeckill tPromotionSeckill = tPromotionSeckillMapper.findById(id);
        if (tPromotionSeckill == null) {
            throw new Exception("抱歉，暂无此活动商品");
        } else if (tPromotionSeckill.getStatus() == 0) {
            throw new Exception("抱歉，活动未开始");
        } else if (tPromotionSeckill.getStatus() == 2) {
            throw new Exception("抱歉，活动已经结束");
        }
        Integer goodsId = (Integer) redisTemplate.opsForList().leftPop("seckill:count:" + tPromotionSeckill.getPsId());
        if (goodsId == null) {
            throw new Exception("抱歉，商品已被抢光");
        }
        boolean isSeckill = redisTemplate.opsForSet().isMember("seckill:users:" + tPromotionSeckill.getPsId(), userid);
        if (isSeckill) {
            redisTemplate.opsForList().rightPush("seckill:count:" + tPromotionSeckill.getPsId(), tPromotionSeckill.getGoodsId());
            throw new Exception("抱歉，您已抢购过此商品");
        }
        redisTemplate.opsForSet().add("seckill:users:" + tPromotionSeckill.getPsId(), userid);

    }

    public String SeckillOrder(String userid) {
        String orderNo = UUID.randomUUID().toString();
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("userid", userid);
        concurrentHashMap.put("orderNo", orderNo);
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId("1");
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        rabbitTemplate.convertAndSend("babytun-order", null, concurrentHashMap, correlationData);
        return orderNo;
    }

    public List<TPromotionSeckill> findByEndTime() {
        return tPromotionSeckillMapper.findByEndTime();
    }
}
