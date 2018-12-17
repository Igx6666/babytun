package com.gx.babytun.controller;

import com.gx.babytun.entity.RabbitCommd;
import com.gx.babytun.entity.TOrder;
import com.gx.babytun.mapper.TOrderMapper;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;

/**
 * @author 谷鑫 G x
 * @Classname OrderConsumer
 * @Describe:
 * @date 2018/11/9 11:53
 */
@Component
public class OrderConsumer {
 /*   @Resource
    private TOrderMapper tOrderMapper;

    //@Payload代表消息的主体数据会自动转换
    //Headers代表消息的附加信息
    //@RabbitHandlers让springboot启动扫描到，下面的方法将处于等待状态，有新的消息会触发
    //@RabbitListener注解用于声明定义消息接收队列与exchange绑定的信息(@QueueBinding来绑定消息队列和交换机@Queue(value="queue名称"),@Exchange(value="交换机名称"))
    @RabbitHandler
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = RabbitCommd.ORDER_QUEUE),
            exchange = @Exchange(value = RabbitCommd.BABYTUN_ORDER_EXCHANGE,type = "fanout"))})
    public void HandleMessage(@Payload ConcurrentHashMap concurrentHashMap, Channel channel,
                              @Headers Map<String, Object> map) throws IOException, TimeoutException {
        System.out.println("获取订单数据" + concurrentHashMap.toString());
        TOrder tOrder = new TOrder();
        tOrder.setOrderNo(concurrentHashMap.get("orderNo").toString());
        tOrder.setUserid(concurrentHashMap.get("userid").toString());
        tOrder.setOrderStatus(0);
        tOrder.setRecvName("xxxxxxx");
        tOrder.setRecvAddress("xxxxxxxx");
        tOrder.setRecvMobile("15515907010");
        tOrder.setAmout(19.8f);
        tOrder.setPostge(0f);
        tOrder.setCreateTime(new Date());
        tOrderMapper.insertSelective(tOrder);
        channel.basicAck((Long)map.get(AmqpHeaders.DELIVERY_TAG), false);
        channel.close();
    }*/
}
