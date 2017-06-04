package com.register.logUpRegister;

import com.json.Info_Status;
import com.model.user.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/1.
 */
public interface LogUpRegister {

    //注册用户 0:注册成功  1:账户重名  2:邮箱重名
    int regist(HttpServletRequest rq, User user, Info_Status is);
}
