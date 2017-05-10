package com.csair.csairmind.hunter.client;

import com.csair.csairmind.hunter.client.content.DefaultApplicationContext;
import com.csair.csairmind.hunter.common.inf.MgrService;
import com.csair.csairmind.hunter.common.spring.ApplicationContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class Start {
    public @PostConstruct void init() {
        System.out.println("11111111111111111111");
        //放入全局公共变量，以便引用
        DefaultApplicationContext.context().setMgrService(ApplicationContextProvider.getBean("mgrService",MgrService.class));
        DefaultApplicationContext.context().start();
    }
}