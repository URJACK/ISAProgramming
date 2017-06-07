package com.controller;

import com.google.gson.Gson;
import com.json.Info_Status;
import com.json.Info_Status_Object;
import com.json.Info_Status_User;
import com.worker.ModelGetWorker.FriendGetWorker;
import com.worker.ModelGetWorker.ModelGetWorker;
import com.worker.ModelGetWorker.UserGetWorker;
import com.worker.ModelGetWorker.UserOtherGetWorker;
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
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status_Object iso = new Info_Status_Object();
            ModelGetWorker mgw = new UserOtherGetWorker();
            mgw.work(rq,iso);

            PrintWriter writer = rsp.getWriter();
            writer.print(new Gson().toJson(iso));
            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
