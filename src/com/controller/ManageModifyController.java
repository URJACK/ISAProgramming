package com.controller;

import com.DAO.QuestionDAO;
import com.DAO.TopicDAO;
import com.DAO.UserDAO;
import com.google.gson.Gson;
import com.json.admin.Question_Modify_Json;
import com.json.admin.Topic_Modify_Json;
import com.model.Question;
import com.model.Topic;
import com.model.User;
import com.tool.SessionOpenner;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Created by FuFangzhou on 2017/6/16.
 */
@Controller
@RequestMapping("/managecontroll")
public class ManageModifyController {
    @RequestMapping("/user")
    public ModelAndView user(HttpServletRequest rq, HttpServletResponse rsp) {
        ModelAndView mv = new ModelAndView("manage_modify_user");
        User user = null;
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");
            int id = Integer.parseInt(rq.getParameter("id"));
            Session session = SessionOpenner.getInstance().getSession();
            user = UserDAO.getUserbyId(session, id);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            mv.getModel().put("model", new Gson().toJson(user));
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
            Session session = SessionOpenner.getInstance().getSession();
            Question question = QuestionDAO.getQuestionById(session, id);
            json = makeQuestionModifyJson(question);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            mv.getModel().put("model", new Gson().toJson(json));
            return mv;
        }
    }

    @RequestMapping("/topic")
    public ModelAndView topic(HttpServletRequest rq, HttpServletResponse rsp) {
        ModelAndView mv = new ModelAndView("manage_modify_topic");
        Topic_Modify_Json json = new Topic_Modify_Json();
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");
            int id = Integer.parseInt(rq.getParameter("id"));
            Session session = SessionOpenner.getInstance().getSession();
            Topic topic = TopicDAO.getTopicById(session, id);
            json = makeTopicModifyJson(topic);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            mv.getModel().put("model", new Gson().toJson(json));
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
}
