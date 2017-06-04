package com.register.logInRegister;

import com.json.Info_Status;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/4.
 */
public class GetNameRegister implements LoginRegister {
    @Override
    public int regist(HttpServletRequest rq, Info_Status is) {
        try {
            String account = (String) rq.getSession().getAttribute("account");
            is.setStatus(true);
            is.setInfo(account);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            is.setStatus(false);
            is.setInfo("服务器出现了意外错误");
            return 0;
        }
    }
}
