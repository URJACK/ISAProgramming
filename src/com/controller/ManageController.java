package com.controller;

import com.DAO.QuestionDAO;
import com.DAO.UserDAO;
import com.google.gson.Gson;
import com.json.Info_Status;
import com.json.admin.Question_Admin_Json;
import com.json.admin.User_Admin_Json;
import com.model.Question;
import com.model.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
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

    @RequestMapping("/user")
    public void user(HttpServletRequest rq, HttpServletResponse rsp) {
        Session session = null;
        List<User_Admin_Json> userJsons = null;
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            session = SessionOpenner.getInstance().getSession();
            List<User> users = UserDAO.getAllUser(session);
            userJsons = new ArrayList<>(7);
            for (int i = 0; i < users.size(); i++) {
                User_Admin_Json uaj = new User_Admin_Json();
                User user = users.get(i);
                uaj.setId(user.getId());
                uaj.setAccount(user.getAccount());
                uaj.setEmail(user.getEmail());
                userJsons.add(uaj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
            writeResponse(rsp, new Gson().toJson(userJsons));
        }
    }

    @RequestMapping("/question")
    public void question(HttpServletRequest rq, HttpServletResponse rsp) {
        Session session = null;
        List<Question_Admin_Json> questionJsons = null;
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            session = SessionOpenner.getInstance().getSession();
            List<Question> questions = QuestionDAO.getAllQuestion(session);
            questionJsons = new ArrayList<>(7);
            for (int i = 0; i < questions.size(); i++) {
                Question_Admin_Json qaj = new Question_Admin_Json();
                Question question = questions.get(i);
                qaj.setId(question.getId());
                qaj.setLevel(question.getLv());
                qaj.setNumber(question.getNumber());
                qaj.setTitle(question.getTitle());
                questionJsons.add(qaj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
            writeResponse(rsp, new Gson().toJson(questionJsons));
        }
    }

    private void writeResponse(HttpServletResponse rsp, String s) {
        try {
            PrintWriter writer = rsp.getWriter();
            writer.print(s);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/topic")
    public void topic(HttpServletRequest rq, HttpServletResponse rsp) {

    }

    @RequestMapping("/match")
    public void match(HttpServletRequest rq, HttpServletResponse rsp) {

    }
}
