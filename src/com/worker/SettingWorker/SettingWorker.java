package com.worker.SettingWorker;

import com.json.Info_Status;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/5.
 */
public interface SettingWorker {
    //如果is.setInfos("ANS");  Account Not Same
    int work(HttpServletRequest rq, Info_Status is);
}
