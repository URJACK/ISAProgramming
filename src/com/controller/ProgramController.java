package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by FuFangzhou on 2017/6/12.
 */
@Controller
@RequestMapping("/program")
public class ProgramController {
    @RequestMapping("")
    public ModelAndView program(){
        ModelAndView mv = new ModelAndView("program");
        return mv;
    }
}
