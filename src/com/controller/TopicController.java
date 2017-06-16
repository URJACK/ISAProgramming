package com.controller;

import com.DAO.TopicDAO;
import com.google.gson.Gson;
import com.json.admin.Topic_Modify_Json;
import com.model.Topic;
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
@RequestMapping("/topic")
public class TopicController {

    @RequestMapping("")
    public ModelAndView topic() {
        ModelAndView mv = new ModelAndView("topic");
        return mv;
    }

    @RequestMapping("/content")
    public ModelAndView content(HttpServletRequest rq, HttpServletResponse rsp) {
        Session session = SessionOpenner.getInstance().getSession();
        ModelAndView mv = new ModelAndView("topic_content");
        Topic_Modify_Json tmj = new Topic_Modify_Json();
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            int id = Integer.parseInt(rq.getParameter("id"));
            Topic topic = TopicDAO.getTopicById(session, id);

            tmj.setTitle(topic.getTitle());
            tmj.setContent(topic.getContent());
            tmj.setOwner(topic.getUser().getAccount());
            tmj.setId(topic.getId());
            tmj.setUid(topic.getUid());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            mv.getModel().put("model", new Gson().toJson(tmj));
            return mv;
        }
    }
}
