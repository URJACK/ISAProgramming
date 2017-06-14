package com.controller;

import com.DAO.QuestionDAO;
import com.google.gson.Gson;
import com.json.Info_Status_Object;
import com.json.Question_Json;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
                questionList = QuestionDAO.getQuestionList(target);
            } catch (Exception e) {
                e.printStackTrace();
                iso.setInfos("查询失败");
                iso.setStatus(false);
            }
            iso.setInfos("成功查询");
            iso.setStatus(true);
            iso.setObj(questionList);

            PrintWriter writer = rsp.getWriter();
            writer.write(new Gson().toJson(iso));
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
