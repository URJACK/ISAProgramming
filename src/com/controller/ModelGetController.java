package com.controller;

import com.DAO.FriendDAO;
import com.DAO.TopicDAO;
import com.DAO.UserDAO;
import com.google.gson.Gson;
import com.json.HaveSolve_Json;
import com.json.Info_Status_Object;
import com.json.Info_Status_User;
import com.json.Topic.TopicMyself;
import com.json.admin.Topic_Admin_Json;
import com.model.*;
import com.tool.SessionOpenner;
import com.worker.ModelGetWorker.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/model")
public class ModelGetController {

    //查询自己的其他信息
    @RequestMapping("/user")
    public void user(HttpServletRequest rq, HttpServletResponse rsp) {
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status_User isu = new Info_Status_User();
            ModelGetWorker mgw = new UserGetWorker();
            mgw.work(rq, isu);

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
    public void friend(HttpServletRequest rq, HttpServletResponse rsp) {
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status_Object iso = new Info_Status_Object();
            ModelGetWorker mgw = new FriendGetWorker();
            mgw.work(rq, iso);

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
    public void otheruser(HttpServletRequest rq, HttpServletResponse rsp) {
        Info_Status_Object iso = new Info_Status_Object();
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            ModelGetWorker mgw = new UserOtherGetWorker();
            mgw.work(rq, iso);

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
    public void chat(HttpServletRequest rq, HttpServletResponse rsp) {
        Info_Status_Object iso = new Info_Status_Object();
        try {
            putChatMessage(rq, rsp, iso);
        } catch (Exception e) {
            e.printStackTrace();
            iso.setStatus(false);
            iso.setInfos("Server Error");
            iso.setObj(null);
        }
    }

    //'A'发来消息，更新数据库的同时，返回与B的聊天记录
    @RequestMapping("/send")
    public void send(HttpServletRequest rq, HttpServletResponse rsp) {
        Info_Status_Object iso = new Info_Status_Object();
        try {
            String content = rq.getParameter("content");
            String account = rq.getParameter("account");
            String targetaccount = rq.getParameter("targetaccount");
            Session session = SessionOpenner.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            User userA = UserDAO.getUser(account, session);
            User userB = UserDAO.getUser(targetaccount, session);
            int userAid = userA.getId();
            int userBid = userB.getId();
            Friend friend = FriendDAO.getFriend(session, userAid, userBid);
            FriendChat fc = new FriendChat();
            fc.setRid(friend.getId());
            fc.setContent(content);
            fc.setSenderid(userAid);
            session.save(fc);
            transaction.commit();
            session.close();
            putChatMessage(rq, rsp, iso);
        } catch (Exception e) {
            iso.setStatus(false);
            iso.setInfos("Error");
            e.printStackTrace();
        }
    }

    //查询申请自己的好友申请列表
    @RequestMapping("/requestfriend")
    public void requestFriend(HttpServletRequest rq, HttpServletResponse rsp) {
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status_Object iso = new Info_Status_Object();
            ModelGetWorker mgw = new RequestGetWorker();
            mgw.work(rq, iso);

            PrintWriter writer = rsp.getWriter();
            writer.print(new Gson().toJson(iso));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //查询自己的好友申请列表
    @RequestMapping("/solvelist")
    public void solvelist(HttpServletRequest rq, HttpServletResponse rsp) {
        Info_Status_Object iso = null;
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");
            iso = new Info_Status_Object();
            List<HaveSolve_Json> haveSolves = new ArrayList<>(5);
            Session session = SessionOpenner.getInstance().getSession();
            //得到当前的用户对象
            User user = UserDAO.getUser(String.valueOf(rq.getSession().getAttribute("account")), session);
            //根据当前的用户对象放入UserDAO中进行查询
            List<QuestionRecord> recordList = UserDAO.getRecordList(user, session);
            //将查询到的QuestionRecord 转换为 SolvedList
            for (int i = 0; i < recordList.size(); i++) {
                HaveSolve_Json hsj = new HaveSolve_Json();
                QuestionRecord qr = recordList.get(i);
                hsj.setDate(qr.getDate());
                hsj.setNumber(qr.getQuestion().getNumber());
                hsj.setTitle(qr.getQuestion().getTitle());
                hsj.setLevel(qr.getQuestion().getLv());
                haveSolves.add(hsj);
            }
            iso.setStatus(true);
            iso.setObj(haveSolves);
            iso.setInfos("查询完成");
        } catch (Exception e) {
            e.printStackTrace();
            iso.setStatus(false);
            iso.setInfos("数据出现了错误");
            iso.setObj(null);
        } finally {
            try {
                PrintWriter writer = rsp.getWriter();
                writer.write(new Gson().toJson(iso));
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //得到跟自己有关的Topic 的信息
    @RequestMapping("/topicmyself")
    public void topicinfo(HttpServletRequest rq, HttpServletResponse rsp) {
        Session session = null;
        Info_Status_Object iso = new Info_Status_Object();
        TopicMyself tm = new TopicMyself();
        try {
            session = SessionOpenner.getInstance().getSession();
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");
            String account = (String) rq.getSession().getAttribute("account");
            User user = UserDAO.getUser(account, session);
            List<Topic> topicList = TopicDAO.getTopicList(user.getId(), session);
            tm.setAccount(account);
            tm.setCreate(topicList.size());
            tm.setFollow(0);
            iso.setInfos("Success");
            iso.setStatus(true);
            iso.setObj(tm);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            iso.setInfos("Failed");
            iso.setStatus(false);
        } finally {
            if (session != null)
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

    //得到跟自己有关的Topic的信息
    @RequestMapping("/topiclist")
    public void topiclist(HttpServletRequest rq, HttpServletResponse rsp) {
        Session session = null;
        Info_Status_Object iso = new Info_Status_Object();
        List<Topic_Admin_Json> list = null;
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            session = SessionOpenner.getInstance().getSession();
            List<Topic> topics = TopicDAO.getAllTopic(session);
            list = makeTopicJsons(topics);

            iso.setInfos("Success");
            iso.setStatus(true);
            iso.setObj(list);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            iso.setInfos("Failed");
            iso.setStatus(false);
        } finally {
            if (session != null)
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

    private List<Topic_Admin_Json> makeTopicJsons(List<Topic> topics) {
        List<Topic_Admin_Json> list = new ArrayList<>(6);
        for (int i = 0; i < topics.size(); i++) {
            Topic topic = topics.get(i);
            Topic_Admin_Json taj = new Topic_Admin_Json();
            taj.setTitle(topic.getTitle());
            taj.setOwner(topic.getUser().getAccount());
            taj.setId(topic.getId());
            list.add(taj);
        }
        return list;
    }

    //根据ChatIndex 将需要被刷新的信息回传给界面
    private void putChatMessage(HttpServletRequest rq, HttpServletResponse rsp, Info_Status_Object iso) throws IOException {
        rq.setCharacterEncoding("UTF-8");
        rsp.setCharacterEncoding("UTF-8");
        rsp.setContentType("text/html");

        ModelGetWorker mgw = new ChatInfosGetWorker();
        mgw.work(rq, iso);

        PrintWriter writer = rsp.getWriter();
        writer.write(new Gson().toJson(iso));
        writer.flush();
        writer.close();
    }
}
