package com.worker.logInWorker;

import com.json.Info_Status;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/2.
 */
public interface LoginWorker {
    int work(HttpServletRequest rq, Info_Status is);
}
