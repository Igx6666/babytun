package com.gx.babytun.controller;

import com.gx.babytun.entity.*;
import com.gx.babytun.service.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import javafx.beans.DefaultProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.groups.Default;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 谷鑫 G x
 * @Classname GoodsController
 * @Describe:
 * @date 2018/10/17 15:13
 */
@Controller
public class GoodsController {
    @Resource
    TGoodsService tGoodsService;
    @Resource
    private TGoodsCoverService tGoodsCoverService;
    @Resource
    private TGoodsDetailService tGoodsDetailService;
    @Resource
    private TGoodsParamService tGoodsParamService;
    @Resource
    private TEvaluateService tEvaluateService;
    @Resource
    private TPromotionSeckillService tPromotionSeckillService;
    //freemarker的核心配置类，用于动态生成模板对象
    //在springboot ioc容器初始化的时候，自动将Configuration初始化了
    @Resource
    private Configuration configuration;

    @RequestMapping("/goods")
    public ModelAndView goods(@RequestParam("gid") long goodsId) throws Exception {
        ModelAndView mv = new ModelAndView("/goods");
        System.out.println("= = =");
        TGoods tGoods = tGoodsService.getGoods(goodsId);
        if (tGoods == null) {
            return mv;
        }
        List<TGoodsCover> tGoodsCovers = tGoodsCoverService.findCoversByGoodsId(goodsId);
        List<TGoodsDetail> tGoodsDetails = tGoodsDetailService.findDetailByGoodsId(goodsId);
        List<TGoodsParam> tGoodsParams = tGoodsParamService.findParamByGoodsId(goodsId);

        mv.addObject("goods", tGoods);
        mv.addObject("covers", tGoodsCovers);
        mv.addObject("details", tGoodsDetails);
        mv.addObject("params", tGoodsParams);
        return mv;
    }

    @RequestMapping("/static_all")
    @ResponseBody
    public String doStatic() {
        //获取模板
        try {
            Template template = configuration.getTemplate("goods.ftl");
            List<TGoods> tGoods = tGoodsService.getGoods();
            for (TGoods g : tGoods) {
                List<TGoodsCover> tGoodsCovers = tGoodsCoverService.findCoversByGoodsId(g.getGoodsId());
                List<TGoodsDetail> tGoodsDetails = tGoodsDetailService.findDetailByGoodsId(g.getGoodsId());
                List<TGoodsParam> tGoodsParams = tGoodsParamService.findParamByGoodsId(g.getGoodsId());
                File file = new File("E:/babytun/goods/" + g.getGoodsId() + ".html");
                FileWriter out = new FileWriter(file);
                Map map = new HashMap();
                map.put("goods", g);
                map.put("covers", tGoodsCovers);
                map.put("details", tGoodsDetails);
                map.put("params", tGoodsParams);
                template.process(map, out);
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "no";
        } catch (TemplateException e) {
            e.printStackTrace();
            return "no";
        }
        return "ok";
    }

    @RequestMapping("/evaluates/{goodsId}")
    @ResponseBody
    public List<TEvaluate> evaluates(@PathVariable long goodsId) {
        return tEvaluateService.findEvaluateByGoodsId(goodsId);
    }

    @RequestMapping("seckill")
    public String seckill() {
        return "seckill";
    }

    @RequestMapping("/seckill/goods")
    @ResponseBody
    public Map seckillGoods(long id, String userid, @RequestParam(defaultValue = "0") int num) {
        Map map = new HashMap();
        try {
            tPromotionSeckillService.SeckillGoods(id, userid, num);
            String orderNo = tPromotionSeckillService.SeckillOrder(userid);
            map.put("code", "200");
            map.put("msg", "恭喜你，抢购成功");
            map.put("orderNo", orderNo);
            System.out.println("恭喜你，抢购成功");
        } catch (Exception e) {
            map.put("code", "500");
            map.put("msg", e.getMessage());
        }
        return map;
    }
}
