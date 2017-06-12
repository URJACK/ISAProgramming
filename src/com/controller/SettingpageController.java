package com.controller;

import com.google.gson.Gson;
import com.json.Info_Status;
import com.worker.ModelGetWorker.SettingAddRequestWorker;
import com.worker.SettingWorker.*;
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
    public ModelAndView def() {
        ModelAndView mv = new ModelAndView("setting");
        return mv;
    }

    //A请求更改密码
    @RequestMapping("/password")
    public void password(HttpServletRequest rq, HttpServletResponse rsp) {
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status is = new Info_Status();
            SettingWorker sw = new PasswordChangeWorker();
            sw.work(rq, is);

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

    //A请求更多的信息
    @RequestMapping("/moreinfo")
    public void moreinfo(HttpServletRequest rq, HttpServletResponse rsp) {
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status is = new Info_Status();
            SettingWorker sw = new MoreInfoChangeWorker();
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

    //A需要删除B
    @RequestMapping("/delete")
    public void delete(HttpServletRequest rq,HttpServletResponse rsp){
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status is = new Info_Status();
            SettingWorker sw= new DeleteWorker();
            sw.work(rq,is);

            PrintWriter writer = rsp.getWriter();
            writer.print(new Gson().toJson(is));
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //'A' 向 'B' 发出好友申请
    @RequestMapping("/add")
    public void add(HttpServletRequest rq,HttpServletResponse rsp){
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status is = new Info_Status();
            SettingWorker mgw = new SettingAddRequestWorker();
            mgw.work(rq,is);

            PrintWriter writer = rsp.getWriter();
            writer.write(new Gson().toJson(is));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //'A' 同意 'B'的好友申请
    @RequestMapping("/agreerefuse")
    public void agreerefuse(HttpServletRequest rq,HttpServletResponse rsp){
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status is = new Info_Status();
            SettingWorker mgw = new SettingAgreeWorker();
            mgw.work(rq,is);

            PrintWriter writer = rsp.getWriter();
            writer.write(new Gson().toJson(is));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
