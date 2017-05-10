package com.csair.csairmind.hunter.client;

import com.csair.csairmind.hunter.client.content.DefaultApplicationContext;
import com.csair.csairmind.hunter.common.inf.MgrService;
import com.csair.csairmind.hunter.common.spring.ApplicationContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
@Slf4j
public class Start {

    @Resource
    MgrService mgrService;

    public @PostConstruct void init() {
        //放入全局公共变量，以便引用
        DefaultApplicationContext.context().setMgrService(mgrService);
        DefaultApplicationContext.context().start();
    }
}