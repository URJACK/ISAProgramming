package com.controller;

import com.google.gson.Gson;
import com.json.Info_Status;
import com.json.Info_Status_User;
import com.worker.ModelGetWorker.ModelGetWorker;
import com.worker.ModelGetWorker.UserGetWorker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/model")
public class ModelGetController {

    @RequestMapping("/user")
    public void user(HttpServletRequest rq, HttpServletResponse rsp){
        try {
            rq.setCharacterEncoding("UTF-8");
            rsp.setCharacterEncoding("UTF-8");
            rsp.setContentType("text/html");

            Info_Status_User isu = new Info_Status_User();
            ModelGetWorker mgw = new UserGetWorker();
            mgw.work(rq,isu);

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
}