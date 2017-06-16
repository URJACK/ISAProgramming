package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by FuFangzhou on 2017/6/16.
 */
@Controller
@RequestMapping("/topic")
public class TopicController {

    @RequestMapping("")
    public ModelAndView topic(){
        ModelAndView mv = new ModelAndView("topic");
        return mv;
    }
}
