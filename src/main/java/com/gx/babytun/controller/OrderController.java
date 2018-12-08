package com.gx.babytun.controller;

import com.gx.babytun.entity.TOrder;
import com.gx.babytun.service.TOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author 谷鑫 G x
 * @Classname OrderController
 * @Describe:
 * @date 2018/11/9 14:51
 */
@Controller
public class OrderController {
    @Resource
    private TOrderService tOrderService;

    @RequestMapping("/checkOrder/{orderNo}")
    public ModelAndView findOrderNo(@PathVariable String orderNo) {
        ModelAndView model = new ModelAndView();
        TOrder order = tOrderService.findByOrderNo(orderNo);
        if (order != null) {
            model.addObject("order", order);
            model.setViewName("/order");
        } else {
            model.addObject("orderNo", orderNo);
            model.setViewName("/wait");
        }
        return model;
    }
}
