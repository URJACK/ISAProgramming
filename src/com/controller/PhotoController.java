package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Properties;

/**
 * Created by FuFangzhou on 2017/6/4.
 */
@Controller
@RequestMapping("/img")
public class PhotoController {

    private static String LOCATION_USER;

    static {
        Properties properties = new Properties();
        try {
            InputStream in = PhotoController.class.getClassLoader().getResourceAsStream("isa.properties");
            properties.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOCATION_USER = properties.getProperty("img_user");
        System.out.println(LOCATION_USER);
    }

    @RequestMapping("/user")
    public void getUserImg(HttpServletRequest rq, HttpServletResponse rsp) {
        try {
            rsp.setContentType("image/img");
            int id = (int) rq.getSession().getAttribute("id");
            writeUserBitmap(rsp, String.format(LOCATION_USER, id));
        } catch (IOException e) {
            try {
                writeUserBitmap(rsp, String.format(LOCATION_USER, 0));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void writeUserBitmap(HttpServletResponse rsp, String format) throws IOException {
        File file = new File(format);
        InputStream inputStream = new FileInputStream(file);
        byte[] img = new byte[inputStream.available()];
        inputStream.read(img);
        ServletOutputStream outputStream = rsp.getOutputStream();
        outputStream.write(img);
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }
}
