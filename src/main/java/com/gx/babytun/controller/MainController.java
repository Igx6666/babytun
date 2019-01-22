package com.gx.babytun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 谷鑫 G x
 * @Classname MainController
 * @Describe:
 * @date 2019/1/22 13:50
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
