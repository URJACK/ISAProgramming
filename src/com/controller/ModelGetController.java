package com.controller;

import com.google.gson.Gson;
import com.json.Info_Status;
import com.json.Info_Status_Object;
import com.json.Info_Status_User;
import com.worker.ModelGetWorker.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/model")
public class ModelGetController {

    //查询自己的其他信息
    @RequestMapping("/user")
    public void user(HttpServletRequest rq, HttpServletResponse rsp){
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status_User isu = new Info_Status_User();
            ModelGetWorker mgw = new UserGetWorker();
            mgw.work(rq,isu);

            PrintWriter writer = rsp.getWriter();
            writer.print(new Gson().toJson(isu));
            writer.flush();
            writer.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //查询自己的好友
    @RequestMapping("/friend")
    public void friend(HttpServletRequest rq,HttpServletResponse rsp){
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status_Object iso = new Info_Status_Object();
            ModelGetWorker mgw = new FriendGetWorker();
            mgw.work(rq,iso);

            PrintWriter writer = rsp.getWriter();
            writer.print(new Gson().toJson(iso));
            writer.flush();
            writer.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //查询一个非自己的用户的信息
    @RequestMapping("/otheruser")
    public void otheruser(HttpServletRequest rq,HttpServletResponse rsp){
        Info_Status_Object iso = new Info_Status_Object();
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            ModelGetWorker mgw = new UserOtherGetWorker();
            mgw.work(rq,iso);

            PrintWriter writer = rsp.getWriter();
            writer.print(new Gson().toJson(iso));
            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
            iso.setStatus(false);
            iso.setInfos("Server Error");
            iso.setObj(null);
        }
    }

    //'A'发来请求，查看与'B'的聊天记录
    @RequestMapping("/chat")
    public void chat(HttpServletRequest rq,HttpServletResponse rsp){
        Info_Status_Object iso = new Info_Status_Object();
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            ModelGetWorker mgw = new ChatInfosGetWorker();
            mgw.work(rq,iso);

            PrintWriter writer = rsp.getWriter();
            writer.write(new Gson().toJson(iso));
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
            iso.setStatus(false);
            iso.setInfos("Server Error");
            iso.setObj(null);
        }
    }

    //'A'发来消息，更新数据库的同时，返回与B的聊天记录
    @RequestMapping("/send")
    public void send(HttpServletRequest rq,HttpServletResponse rsp){
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            System.out.println(rq.getParameter("content"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
