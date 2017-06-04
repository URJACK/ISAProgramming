package com.register.logInRegister;

import com.json.Info_Status;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/2.
 */
public interface LoginRegister {
    int regist(HttpServletRequest rq, Info_Status is);
}
