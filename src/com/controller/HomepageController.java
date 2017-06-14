package com.controller;

import com.google.gson.Gson;
import com.json.Info_Status;
import com.model.User;
import com.tool.SessionOpenner;
import com.worker.logInWorker.*;
import com.worker.logUpWorker.CheckNumberWorker;
import com.worker.logUpWorker.LogUpWorker;
import com.worker.logUpWorker.LogUpWorkerimp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by FuFangzhou on 2017/5/31.
 */
@Controller
@RequestMapping("/home")
public class HomepageController {

    static {
        SessionOpenner.getInstance();
        System.out.println("HomePage Initiallized");
    }

    @RequestMapping("")
    public ModelAndView homepage() {
        ModelAndView mv = new ModelAndView("homepage");
        return mv;
    }

    //用来确认邮箱，并向邮箱发送验证码的一个请求
    @RequestMapping("/logup")
    public void logup(HttpServletRequest rq, HttpServletResponse rsp) {
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");
            rsp.setCharacterEncoding("UTF-8");

            User user = new User();

            Info_Status is = new Info_Status();
            is.setStatus(true);

            LogUpWorker lug = new LogUpWorkerimp();
            lug.work(rq, user, is); //将User 和 is 做相应处理

            PrintWriter writer = rsp.getWriter();
            writer.print(new Gson().toJson(is));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //收到验证码后，发送的请求
    @RequestMapping("/logup2")
    public void logup2(HttpServletRequest rq, HttpServletResponse rsp) {
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");
            rsp.setCharacterEncoding("UTF-8");

            Info_Status is = new Info_Status();

            CheckNumberWorker cng = new CheckNumberWorker();
            cng.check(rq, is);

            PrintWriter writer = rsp.getWriter();
            writer.print(new Gson().toJson(is));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //点击遗忘密码后，向邮箱发送验证码
    @RequestMapping("/forget")
    public void forget(HttpServletRequest rq, HttpServletResponse rsp) {
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status is = new Info_Status();

            LoginWorker fr = new ForgetWorker();
            fr.work(rq, is);

            PrintWriter writer = rsp.getWriter();
            writer.print(new Gson().toJson(is));
            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //收到邮箱验证码后，收到的请求
    @RequestMapping("/forget2")
    public void forget2(HttpServletRequest rq, HttpServletResponse rsp) {
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status is = new Info_Status();

            LoginWorker fr = new ForgetWorker_Confirm();
            fr.work(rq, is);

            PrintWriter writer = rsp.getWriter();
            writer.print(new Gson().toJson(is));
            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //收到的登陆请求
    @RequestMapping("/login")
    public void login(HttpServletRequest rq, HttpServletResponse rsp) {
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status is = new Info_Status();

            LoginWorker lr = new LoginWorkerImp();
            lr.work(rq, is);

            PrintWriter writer = rsp.getWriter();
            writer.write(new Gson().toJson(is));
            writer.flush();
            writer.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //每次刷新页面自动会调用这个请求
    @RequestMapping("/defaultlogin")
    public void defaultlogin(HttpServletRequest rq, HttpServletResponse rsp) {
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status is = new Info_Status();
            LoginWorker lr = new DefaultLoginWorker();
            lr.work(rq, is);

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

    //自动登陆成功之后，会再次请求一次获取account
    @RequestMapping("/getname")
    public void getname(HttpServletRequest rq, HttpServletResponse rsp){
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status is = new Info_Status();
            LoginWorker lr = new GetNameWorker();
            lr.work(rq,is);

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