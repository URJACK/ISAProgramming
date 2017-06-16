package com.controller;

import com.DAO.QuestionDAO;
import com.DAO.TopicDAO;
import com.DAO.UserDAO;
import com.google.gson.Gson;
import com.json.Info_Status;
import com.json.Info_Status_Object;
import com.json.admin.Question_Admin_Json;
import com.json.admin.Topic_Admin_Json;
import com.json.admin.User_Admin_Json;
import com.model.Question;
import com.model.Topic;
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
    private static final int PAGECAPACITY = 3;     //每页的容量
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

    /**
     * 根据传入的参数:position 来进行分页发放数据
     *
     * @param rq
     * @param rsp
     */
    @RequestMapping("/user")
    public void user(HttpServletRequest rq, HttpServletResponse rsp) {
        Session session = null;
        List<User_Admin_Json> userJsons = null;
        Info_Status_Object iso = new Info_Status_Object();
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            int position = Integer.parseInt(rq.getParameter("position"));
            //如果是第一页就进行返回
            if (errorWork_First(iso, position)) return;
            session = SessionOpenner.getInstance().getSession();
            List<User> users = UserDAO.getAllUser(session);
            userJsons = new ArrayList<>(8);
            putUserJsons(userJsons, users, position);
            //自动根据需要返回的userJsons进行处理
            errorWork_Second(userJsons, iso);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
            writeResponse(rsp, new Gson().toJson(iso));
        }
    }

    @RequestMapping("/question")
    public void question(HttpServletRequest rq, HttpServletResponse rsp) {
        Session session = null;
        List<Question_Admin_Json> questionJsons = null;
        Info_Status_Object iso = new Info_Status_Object();
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            int position = Integer.parseInt(rq.getParameter("position"));
            //如果是第一页就进行返回
            if (errorWork_First(iso, position)) return;
            session = SessionOpenner.getInstance().getSession();
            List<Question> questions = QuestionDAO.getAllQuestion(session);
            questionJsons = new ArrayList<>(8);
            putQuestionJsons(questionJsons, questions, position);
            //自动根据需要返回的questionJsons进行处理
            errorWork_Second(questionJsons, iso);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
            writeResponse(rsp, new Gson().toJson(iso));
        }
    }

    @RequestMapping("/topic")
    public void topic(HttpServletRequest rq, HttpServletResponse rsp) {
        Session session = null;
        List<Topic_Admin_Json> topicAdminJsons = null;
        Info_Status_Object iso = new Info_Status_Object();
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            int position = Integer.parseInt(rq.getParameter("position"));
            if (errorWork_First(iso, position)) return;
            session = SessionOpenner.getInstance().getSession();
            List<Topic> topics = TopicDAO.getAllTopic(session);
            topicAdminJsons = new ArrayList<>(7);
            putTopicJsons(topicAdminJsons, topics, position);
            errorWork_Second(topicAdminJsons, iso);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
            writeResponse(rsp, new Gson().toJson(iso));
        }
    }

    private void errorWork_Second(List list, Info_Status_Object iso) {
        if (list.size() == 0) {
            iso.setInfos("已经是最后一页了");
            iso.setStatus(false);
            iso.setObj(null);
        } else {
            iso.setInfos("OK");
            iso.setObj(list);
            iso.setStatus(true);
        }
    }

    private boolean errorWork_First(Info_Status_Object iso, int position) {
        if (position < 0) {
            iso.setInfos("已经是第一页了");
            iso.setStatus(false);
            iso.setObj(null);
            return true;
        }
        return false;
    }

    private void putUserJsons(List<User_Admin_Json> userJsons, List<User> users, int position) {
        //i = 0 时，返还的id = 1 的是管理员Admin ，此时不返还他
        int offset = position * PAGECAPACITY;
        try {
            for (int i = 1; i <= PAGECAPACITY; i++) {
                User_Admin_Json uaj = new User_Admin_Json();
                User user = users.get(i + offset);
                uaj.setId(user.getId());
                uaj.setAccount(user.getAccount());
                uaj.setEmail(user.getEmail());
                userJsons.add(uaj);
            }
        } catch (IndexOutOfBoundsException e) {
            //结束对UserJson 的生成
        }
    }

    private void putQuestionJsons(List<Question_Admin_Json> questionJsons, List<Question> questions, int position) {
        int offset = position * PAGECAPACITY;
        try {
            for (int i = 0; i < PAGECAPACITY; i++) {
                Question_Admin_Json qaj = new Question_Admin_Json();
                Question question = questions.get(i + offset);
                qaj.setId(question.getId());
                qaj.setLevel(question.getLv());
                qaj.setNumber(question.getNumber());
                qaj.setTitle(question.getTitle());
                questionJsons.add(qaj);
            }
        } catch (IndexOutOfBoundsException e) {
            //结束对QuestionJson 的生成
        }
    }

    private void putTopicJsons(List<Topic_Admin_Json> topicAdminJsons, List<Topic> topics, int position) {
        int offset = position * PAGECAPACITY;
        try {
            for (int i = 0; i < PAGECAPACITY; i++) {
                Topic_Admin_Json taj = new Topic_Admin_Json();
                Topic topic = topics.get(i + offset);
                taj.setId(topic.getId());
                taj.setOwner(topic.getUser().getAccount());
                taj.setTitle(topic.getTitle());
                topicAdminJsons.add(taj);
            }
        } catch (IndexOutOfBoundsException e) {
            //结束对TopicJson 的继续生成
        }
    }

    @RequestMapping("/match")
    public void match(HttpServletRequest rq, HttpServletResponse rsp) {

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
}
