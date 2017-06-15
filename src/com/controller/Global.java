package com.controller;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by FuFangzhou on 2017/6/15.
 */
@Service
public class Global {


    @PostConstruct
    public void applicationStart() {
        System.out.println("ISAPROGRAM START");
    }

    @PreDestroy
    public void applicatonDestroy() {
        System.out.println("ISAPROGRAM DESTROY");
    }

}
