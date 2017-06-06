package com.worker.logUpWorker;

import com.json.Info_Status;
import com.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/1.
 */
public interface LogUpWorker {

    //注册用户 0:注册成功  1:账户重名  2:邮箱重名
    int work(HttpServletRequest rq, User user, Info_Status is);
}
