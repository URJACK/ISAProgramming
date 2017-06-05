package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by FuFangzhou on 2017/6/5.
 */
@Controller
@RequestMapping("/setting")
public class SettingpageController {
    @RequestMapping("")
    public ModelAndView def(){
        ModelAndView mv = new ModelAndView("setting");
        return mv;
    }
}
