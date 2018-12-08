package com.gx.babytun.scheduler;

import com.gx.babytun.entity.*;
import com.gx.babytun.service.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @author 谷鑫 G x
 * @Classname StaticHtmlTask
 * @Describe:
 * @date 2018/10/18 16:12
 */
//组件类，IOC容器扫描到后会自动实例化加载
@Component
public class StaticHtmlTask {
    @Resource
    TGoodsService tGoodsService;
    @Resource
    private TGoodsCoverService tGoodsCoverService;
    @Resource
    private TGoodsDetailService tGoodsDetailService;
    @Resource
    private TGoodsParamService tGoodsParamService;
    @Resource
    private TPromotionSeckillService tPromotionSeckillService;
    //freemarker的核心配置类，用于动态生成模板对象
    //在springboot ioc容器初始化的时候，自动将Configuration初始化了
    @Resource
    private Configuration configuration;

    @Resource
    private RedisTemplate<Object, Object> redis;

    /**
     * 按指定时间调度任务
     * * * * * * ? 每秒执行一次
     * 秒 分 时 日 月 星期
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void doStaticHtml() {
        try {
            Template template = configuration.getTemplate("goods.ftl");
            List<TGoods> tGoodsList = tGoodsService.findLastTime5();
            for (TGoods g : tGoodsList) {
                Map map = new Hashtable<>();
                List<TGoodsCover> tGoodsCovers = tGoodsCoverService.findCoversByGoodsId(g.getGoodsId());
                List<TGoodsDetail> tGoodsDetails = tGoodsDetailService.findDetailByGoodsId(g.getGoodsId());
                List<TGoodsParam> tGoodsParams = tGoodsParamService.findParamByGoodsId(g.getGoodsId());
                map.put("goods", g);
                map.put("covers", tGoodsCovers);
                map.put("details", tGoodsDetails);
                map.put("params", tGoodsParams);
                File file = new File("E:/babytun/goods/" + g.getGoodsId() + ".html");
                FileWriter out = new FileWriter(file);
                template.process(map, out);
                System.out.println(new Date() + "" + file + "已经生成");
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void startSeckill() {
        List<TPromotionSeckill> tPromotionSeckills = tPromotionSeckillService.findPromotionByStatus0();
        for (TPromotionSeckill tp : tPromotionSeckills) {
            redis.delete("seckill:count:" + tp.getPsId());
            TPromotionSeckill t = new TPromotionSeckill();
            for (int i = 0; i < tp.getPsCount(); i++) {
                redis.opsForList().rightPush("seckill:count:" + tp.getPsId(), tp.getGoodsId());
            }
            t.setStatus(1);
            t.setPsId(tp.getPsId());
            tPromotionSeckillService.updateByPrimaryKeySelective(t);
            System.out.println(tp.getGoodsId() + "活动开始");
        }
    }
    @Scheduled(cron = "0/5 * * * * ?")
    public void endSeckill(){
        List<TPromotionSeckill> tPromotionSeckills = tPromotionSeckillService.findByEndTime();
        for (TPromotionSeckill tp : tPromotionSeckills) {
            redis.delete("seckill:count:" + tp.getPsId());
            TPromotionSeckill t = new TPromotionSeckill();
            t.setStatus(2);
            t.setPsId(tp.getPsId());
            tPromotionSeckillService.updateByPrimaryKeySelective(t);
            System.out.println(tp.getGoodsId() + "活动结束");
        }
    }
}
