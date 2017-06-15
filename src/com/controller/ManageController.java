package com.controller;

import com.google.gson.Gson;
import com.json.Info_Status;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * Created by FuFangzhou on 2017/6/15.
 */
@Controller
@RequestMapping(value = "/manage", method = RequestMethod.POST)
public class ManageController {
    private static String ADMIN;

    static {
        Properties properties = new Properties();
        try {
            InputStream in = ManageController.class.getClassLoader().getResourceAsStream("isa.properties");
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ADMIN = properties.getProperty("admin");
    }

    @RequestMapping("")
    public ModelAndView manage(HttpServletRequest rq, HttpServletResponse rsp) {
        ModelAndView mv = new ModelAndView("manage");
        return mv;
    }

    @RequestMapping("/login")
    public void login(HttpServletRequest rq, HttpServletResponse rsp) {
        Info_Status is = null;
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            is = new Info_Status();
            String admin = rq.getParameter("admin");
            String account = rq.getParameter("account");
            if (admin.equals(ADMIN) && account.equals("Admin")) {
                is.setInfo("管理员登陆成功");
                is.setStatus(true);
            } else {
                is.setInfo("管理员登陆失败");
                is.setStatus(false);
            }


        } catch (Exception e) {
            e.printStackTrace();
            is.setInfo("管理员登陆失败");
            is.setStatus(false);
        } finally {

            PrintWriter writer = null;
            try {
                writer = rsp.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writer.print(new Gson().toJson(is));
            writer.flush();
            writer.close();
        }
    }
}
