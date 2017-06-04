package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by FuFangzhou on 2017/6/4.
 */
@Controller
@RequestMapping("/img")
public class PhotoController {
    public static void main(String[] args) throws IOException {
        File file = new File("/picture/1.bmp");
        InputStream inputStream = new FileInputStream(file);
        byte[] img = new byte[inputStream.available()];
        inputStream.read(img);
        FileOutputStream outputStream = new FileOutputStream("/picture/2.bmp");
        outputStream.write(img);
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    @RequestMapping("/user")
    public void getUserImg(HttpServletRequest rq, HttpServletResponse rsp){
        try {
            rsp.setContentType("image/img");
            int id = Integer.parseInt(rq.getParameter("id"));
            File file = new File(String.format("E:\\tomcat\\websource\\ISAProgramming\\user\\%d.bmp",id));
            InputStream inputStream = new FileInputStream(file);
            byte[] img = new byte[inputStream.available()];
            inputStream.read(img);
            ServletOutputStream outputStream = rsp.getOutputStream();
            outputStream.write(img);
            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
