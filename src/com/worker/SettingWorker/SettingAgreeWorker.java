package com.worker.SettingWorker;

import com.json.Info_Status;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/12.
 */
public class SettingAgreeWorker implements SettingWorker {
    @Override
    public int work(HttpServletRequest rq, Info_Status is) {
        String account = rq.getParameter("account");
        String targetaccount = rq.getParameter("targetaccount");
        boolean isAgree = Boolean.parseBoolean(rq.getParameter("agree"));
        System.out.println("DEBUG ISAGREE? "+isAgree);
        return 1;
    }
}
