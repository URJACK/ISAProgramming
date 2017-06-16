package com.listener;

import com.controller.Global;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by FuFangzhou on 2017/6/16.
 */
public class LinkSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        Global.getInstance().getSessions().add(httpSessionEvent.getSession());
        System.out.println("添加了session");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        Global.getInstance().getSessions().remove(httpSessionEvent.getSession());
        System.out.println("删除了session");
    }
}
