package com.controller;

import com.DAO.QuestionDAO;
import com.DAO.TopicDAO;
import com.DAO.UserDAO;
import com.google.gson.Gson;
import com.json.Info_Status;
import com.json.Info_Status_Object;
import com.json.admin.Question_Modify_Json;
import com.json.admin.Topic_Modify_Json;
import com.model.Question;
import com.model.Topic;
import com.model.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by FuFangzhou on 2017/6/16.
 */
@Controller
@RequestMapping("/managecontroll")
public class ManageModifyController {

    private static final int ACTIONCREATE = 0;

    @RequestMapping("/user")
    public ModelAndView user(HttpServletRequest rq, HttpServletResponse rsp) {
        ModelAndView mv = new ModelAndView("manage_modify_user");
        User user = null;
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");
            int id = Integer.parseInt(rq.getParameter("id"));
            if (id != ACTIONCREATE) {
                Session session = SessionOpenner.getInstance().getSession();
                user = UserDAO.getUserbyId(session, id);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (user != null)
                mv.getModel().put("model", new Gson().toJson(user));
            else
                mv.getModel().put("model", null);
            return mv;
        }
    }

    @RequestMapping("/question")
    public ModelAndView question(HttpServletRequest rq, HttpServletResponse rsp) {
        ModelAndView mv = new ModelAndView("manage_modify_question");
        Question_Modify_Json json = null;
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");
            int id = Integer.parseInt(rq.getParameter("id"));
            if (id != ACTIONCREATE) {
                Session session = SessionOpenner.getInstance().getSession();
                Question question = QuestionDAO.getQuestionById(session, id);
                json = makeQuestionModifyJson(question);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (json != null)
                mv.getModel().put("model", new Gson().toJson(json));
            else
                mv.getModel().put("model", null);
            return mv;
        }
    }

    @RequestMapping("/topic")
    public ModelAndView topic(HttpServletRequest rq, HttpServletResponse rsp) {
        ModelAndView mv = new ModelAndView("manage_modify_topic");
        Topic_Modify_Json json = null;
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");
            int id = Integer.parseInt(rq.getParameter("id"));
            if (id != ACTIONCREATE) {
                Session session = SessionOpenner.getInstance().getSession();
                Topic topic = TopicDAO.getTopicById(session, id);
                json = makeTopicModifyJson(topic);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            if (json != null)
                mv.getModel().put("model", new Gson().toJson(json));
            else
                mv.getModel().put("model", null);
            return mv;
        }
    }

    private Topic_Modify_Json makeTopicModifyJson(Topic topic) {
        Topic_Modify_Json tmj = new Topic_Modify_Json();
        tmj.setId(topic.getId());
        tmj.setTitle(topic.getTitle());
        tmj.setContent(topic.getContent());
        tmj.setUid(topic.getUid());
        tmj.setOwner(topic.getUser().getAccount());
        return tmj;
    }

    private Question_Modify_Json makeQuestionModifyJson(Question question) {
        Question_Modify_Json qmj = new Question_Modify_Json();
        qmj.setId(question.getId());
        qmj.setContent(question.getContent());
        qmj.setLv(question.getLv());
        qmj.setNumber(question.getNumber());
        qmj.setTip(question.getTip());
        qmj.setTitle(question.getTitle());
        return qmj;
    }

    @RequestMapping("/changeuser")
    public void changeuser(HttpServletRequest rq, HttpServletResponse rsp) {
        Session session = null;
        Info_Status is = new Info_Status();
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            //得到传过来的参数
            int id = Integer.parseInt(rq.getParameter("id"));
            String account = rq.getParameter("account");
            String password = rq.getParameter("password");
            int clazz = Integer.parseInt(rq.getParameter("clazz"));
            String email = rq.getParameter("email");

            //更新数据
            session = SessionOpenner.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            User user = UserDAO.getUserbyId(session, id);
            user.setAccount(account);
            user.setPassword(password);
            user.setClazz(clazz);
            user.setEmail(email);
            session.saveOrUpdate(user);
            transaction.commit();

            is.setStatus(true);
            is.setInfo("改变成功");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            is.setStatus(false);
            is.setInfo("改变失败");
        } finally {
            giveResponse(rsp, session, is);
        }
    }

    @RequestMapping("/changequestion")
    public void changequestion(HttpServletRequest rq, HttpServletResponse rsp) {
        Session session = null;
        Info_Status is = new Info_Status();
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            //得到传过来的参数
            int id = Integer.parseInt(rq.getParameter("id"));
            int lv = Integer.parseInt(rq.getParameter("lv"));
            int number = Integer.parseInt(rq.getParameter("number"));
            String title = rq.getParameter("title");
            String content = rq.getParameter("content");
            String tip = rq.getParameter("tip");

            //更新数据
            session = SessionOpenner.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            Question question = QuestionDAO.getQuestionById(session, id);
            question.setLv(lv);
            question.setNumber(number);
            question.setTitle(title);
            question.setContent(content);
            question.setTip(tip);
            session.saveOrUpdate(question);
            transaction.commit();

            is.setStatus(true);
            is.setInfo("改变成功");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            is.setStatus(false);
            is.setInfo("改变失败");
        } finally {
            giveResponse(rsp, session, is);
        }
    }

    @RequestMapping("/changetopic")
    public void changetopic(HttpServletRequest rq, HttpServletResponse rsp) {
        Session session = null;
        Info_Status_Object iso = new Info_Status_Object();
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Topic_Modify_Json tmj = new Topic_Modify_Json();
            //得到传过来的参数
            tmj.setId(Integer.parseInt(rq.getParameter("id")));
            tmj.setUid(Integer.parseInt(rq.getParameter("uid")));
            tmj.setTitle(rq.getParameter("title"));
            tmj.setContent(rq.getParameter("content"));
            tmj.setOwner(rq.getParameter("owner"));

            //更新数据
            session = SessionOpenner.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            Topic topic = TopicDAO.getTopicById(session, tmj.getId());
            topic.setTitle(tmj.getTitle());
            topic.setContent(tmj.getContent());
            topic.setUid(tmj.getUid());
            //先把可能改变的uid 先设置一次
            session.saveOrUpdate(topic);
            transaction.commit();
            //提交之后，重新获取它，以查找正确的Owner
            topic = TopicDAO.getTopicById(session, tmj.getId());
            tmj.setOwner(topic.getUser().getAccount());

            iso.setStatus(true);
            iso.setInfos("改变成功");
            iso.setObj(new Gson().toJson(tmj));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            iso.setStatus(false);
            iso.setInfos("改变失败");
        } finally {
            if (session!=null)
                session.close();
            try {
                PrintWriter writer = rsp.getWriter();
                writer.print(new Gson().toJson(iso));
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void giveResponse(HttpServletResponse rsp, Session session, Info_Status is) {
        if (session != null)
            session.close();
        try {
            PrintWriter writer = rsp.getWriter();
            writer.print(new Gson().toJson(is));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}