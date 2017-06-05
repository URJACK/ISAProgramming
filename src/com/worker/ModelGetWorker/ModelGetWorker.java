package com.worker.ModelGetWorker;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FuFangzhou on 2017/6/5.
 */
public interface ModelGetWorker {
    int work(HttpServletRequest rq,Object ob);
}