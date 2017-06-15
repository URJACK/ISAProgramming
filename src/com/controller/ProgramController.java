package com.controller;

import com.DAO.QuestionDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.json.Info_Status;
import com.json.Info_Status_Object;
import com.json.QuestionContent_Json;
import com.json.Question_Json;
import com.model.Question;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by FuFangzhou on 2017/6/12.
 */
@Controller
@RequestMapping("/program")
public class ProgramController {

    @RequestMapping("")
    public ModelAndView program() {
        ModelAndView mv = new ModelAndView("program");
        return mv;
    }

    /**
     * 在program主页选择了任意一道题之后，发送题号(index,原名questionIndex)与题目集的编号(level,questionsetIndex)进入该页面
     *
     * @return 返还第二个页面:附带题目的内容的Json:
     * QuestionContent_Json{
     * String title;            //题目的名字
     * int number;              //题目的编号
     * String tip;              //一些额外的提示信息 <HTML/>
     * String content;          //题目的主要信息 <HTML/>
     * }
     */
    @RequestMapping(value = "/question", method = RequestMethod.POST)
    public ModelAndView question(HttpServletRequest rq, HttpServletResponse rsp) {
        ModelAndView mv = new ModelAndView("question");
        int index = Integer.parseInt(rq.getParameter("index"));
        int level = Integer.parseInt(rq.getParameter("level"));

        Info_Status_Object iso = new Info_Status_Object();
        QuestionContent_Json qcj = new QuestionContent_Json();
        try {
            Question question = QuestionDAO.getQuestion(index, level);
            qcj.setNumber(question.getNumber());
            qcj.setContent(question.getContent());
            qcj.setTitle(question.getTitle());
            qcj.setTip(question.getTip());
            iso.setInfos("Success");
            iso.setStatus(true);
            iso.setObj(qcj);
        } catch (IndexOutOfBoundsException e) {
            iso.setStatus(false);
            iso.setInfos("Failed");
            iso.setObj(null);
        }

        mv.getModel().put("questioncontent", new Gson().toJson(iso));
        return mv;
    }

    /**
     * 该方法为用户请求题库。我们需要根据用户发送的 target 来返回对应的题库
     */
    @RequestMapping("/set")
    public void set(HttpServletRequest rq, HttpServletResponse rsp) {
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status_Object iso = new Info_Status_Object();
            int target = Integer.parseInt(rq.getParameter("target"));
            List<Question_Json> questionList = null;
            try {
                questionList = QuestionDAO.getQuestion_JsonList(target);
            } catch (Exception e) {
                e.printStackTrace();
                iso.setInfos(e.getMessage());
                iso.setStatus(false);
            }
            String questionSetName = getQuestionSetName(target);
            iso.setInfos(questionSetName);
            iso.setStatus(true);
            iso.setObj(questionList);

            PrintWriter writer = rsp.getWriter();
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.serializeSpecialFloatingPointValues();
            Gson gson = gsonBuilder.create();
            writer.write(gson.toJson(iso));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @RequestMapping("/submit")
    public void submit(HttpServletRequest rq, HttpServletResponse rsp) {
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            String content = rq.getParameter("content");
            doCompile(content,rsp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 该方法是一个同步方法
     *
     * @param sourceCode User发过来的源代码
     * @param rsp        当前请求的对应的回复
     */
    private synchronized static void doCompile(String sourceCode, HttpServletResponse rsp) {
        try {
            Info_Status is = new Info_Status();
            javaCompile(sourceCode, is);
            PrintWriter writer = rsp.getWriter();
            writer.write(new Gson().toJson(is));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据源代码，调用JavaConsole进行编译和运行，得到结果，并将结果放入Info_Status 中
     * @param sourceCode
     */
    private static void javaCompile(String sourceCode, Info_Status is) {

    }

    /**
     * 根据传入的target 返回正确的 问题集的名字
     *
     * @param target
     * @return
     */
    private String getQuestionSetName(int target) {
        if (target == 1)
            return "初级测试题集";
        else if (target == 2)
            return "中级测试题集";
        else if (target == 3)
            return "高级测试题集";
        else
            return null;
    }
}
