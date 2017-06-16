package com.controller;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
/**
 * Created by FuFangzhou on 2017/6/15.
 */
@Service
public class Global {
    private List<HttpSession> sessions;
    private static Global global;

    public static Global getInstance() {
        return global;
    }

    public List<HttpSession> getSessions() {
        return sessions;
    }

    @PostConstruct
    public void applicationStart() {
        System.out.println("ISAPROGRAM START");
        sessions = new ArrayList<>(10);
        global = this;
    }

    @PreDestroy
    public void applicatonDestroy() {
        System.out.println("ISAPROGRAM DESTROY");
    }

}
