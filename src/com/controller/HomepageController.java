package com.controller;

import com.google.gson.Gson;
import com.json.Info_Status;
import com.model.user.User;
import com.register.logInRegister.ForgetRegister;
import com.register.logInRegister.ForgetRegister_Confirm;
import com.register.logInRegister.LoginRegister;
import com.register.logInRegister.LoginRegisterImp1;
import com.register.logUpRegister.CheckNumberRegister;
import com.register.logUpRegister.LogUpRegister;
import com.register.logUpRegister.LogUpRegisterimp1;
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
    @RequestMapping("")
    public ModelAndView homepage(HttpSession httpSession) {
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

            LogUpRegister lug = new LogUpRegisterimp1();
            lug.regist(rq, user, is); //将User 和 is 做相应处理

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

            CheckNumberRegister cng = new CheckNumberRegister();
            cng.check(rq, is);

            PrintWriter writer = rsp.getWriter();
            writer.print(new Gson().toJson(is));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/forget")
    public void forget(HttpServletRequest rq, HttpServletResponse rsp) {
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status is = new Info_Status();

            LoginRegister fr = new ForgetRegister();
            fr.regist(rq, is);

            PrintWriter writer = rsp.getWriter();
            writer.print(new Gson().toJson(is));
            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/forget2")
    public void forget2(HttpServletRequest rq, HttpServletResponse rsp) {
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status is = new Info_Status();

            LoginRegister fr = new ForgetRegister_Confirm();
            fr.regist(rq, is);

            PrintWriter writer = rsp.getWriter();
            writer.print(new Gson().toJson(is));
            writer.flush();
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/login")
    public void login(HttpServletRequest rq, HttpServletResponse rsp) {
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status is = new Info_Status();

            LoginRegister lr = new LoginRegisterImp1();
            lr.regist(rq,is);

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
}