package com.controller;

import com.DAO.QuestionDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.json.Info_Status;
import com.json.Info_Status_Object;
import com.json.QuestionContent_Json;
import com.json.Question_Json;
import com.lib.javaConsole.JavaConsole;
import com.model.Question;
import com.model.QuestionCase;
import com.tool.SessionOpenner;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * Created by FuFangzhou on 2017/6/12.
 */
@Controller
@RequestMapping("/program")
public class ProgramController {
    static private String COMPILE_ROOT;         //临时存放编译文件的盘符
    static private String COMPILE_DIRECTORY;    //临时存放编译文件的路径(绝对路径)

    static {
        Properties properties = new Properties();
        try {
            InputStream in = ProgramController.class.getClassLoader().getResourceAsStream("compile.properties");
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        COMPILE_ROOT = properties.getProperty("compile_root");
        COMPILE_DIRECTORY = properties.getProperty("compile_directory");
    }

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
        Session session = SessionOpenner.getInstance().getSession();
        ModelAndView mv = new ModelAndView("question");
        int index = Integer.parseInt(rq.getParameter("index"));
        int level = Integer.parseInt(rq.getParameter("level"));

        Info_Status_Object iso = new Info_Status_Object();
        QuestionContent_Json qcj = new QuestionContent_Json();
        try {
            Question question = QuestionDAO.getQuestion(index, level, session);
            qcj.setLevel(question.getLv());
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
            int level = Integer.parseInt(rq.getParameter("level"));
            int number = Integer.parseInt(rq.getParameter("number"));
            doCompile(content, rsp, level, number);
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
    private synchronized static void doCompile(String sourceCode, HttpServletResponse rsp, int level, int number) {
        try {
            //生成最终需要返回的信息
            Info_Status is = new Info_Status();
            //得到查询数据库的Session
            Session session = SessionOpenner.getInstance().getSession();
            Question question = QuestionDAO.getQuestion(number, level, session);

            //在该方法里，改变需要返回的信息
            javaCompile(sourceCode, is, question.getCases());

            //将需要返回的信息打印回去
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
     *
     * @param sourceCode
     */
    private static void javaCompile(String sourceCode, Info_Status is, Set<QuestionCase> cases) {
        writeJavaFile(sourceCode);
        QuestionCase[] caseArray = new QuestionCase[cases.size()];
        cases.toArray(caseArray);
        for (int i = 0; i < caseArray.length; i++) {
            try {
                JavaConsole.getAnswer(COMPILE_DIRECTORY, COMPILE_ROOT, caseArray[i].getInput());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 根据源代码，将写成一个.java 的文件放在配置文件制定的目录下
     *
     * @param sourceCode 源代码
     */
    private static void writeJavaFile(String sourceCode) {
        File file = new File(String.format("%s:%s\\Main.java", COMPILE_ROOT, COMPILE_DIRECTORY));
        if (file.exists())
            System.out.println("存在");
        else {
            System.out.println("不存在");
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(file);
            String content = new String(sourceCode);
            fileWriter.write(content);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static void main(String[] args) {
//        String answer;
//        try {
//            answer = JavaConsole.getAnswer(COMPILE_DIRECTORY, COMPILE_ROOT, "25 24");
//            System.out.println(answer);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
        writeJavaFile("This is a test FFZ");
    }
}
