package com.controller;

import com.google.gson.Gson;
import com.json.Info_Status;
import com.worker.SettingWorker.PasswordChangeWorker;
import com.worker.SettingWorker.SettingWorker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

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
    @RequestMapping("/password")
    public void password(HttpServletRequest rq, HttpServletResponse rsp){
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status is = new Info_Status();
            SettingWorker sw = new PasswordChangeWorker();
            sw.work(rq,is);

            PrintWriter writer = rsp.getWriter();
            writer.print(new Gson().toJson(is));
            writer.flush();
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
