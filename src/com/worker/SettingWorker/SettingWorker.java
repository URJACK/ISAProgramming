package com.worker.SettingWorker;

import com.json.Info_Status;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/5.
 */
public interface SettingWorker {
    int work(HttpServletRequest rq, Info_Status is);
}
