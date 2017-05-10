package com.csair.csairmind.hunter.client;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@ComponentScan(basePackages = {"com.csair.csairmind.hunter.common.plug","com.csair.csairmind.hunter.client"})
public class BootStrap {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(BootStrap.class, args);
    }
}