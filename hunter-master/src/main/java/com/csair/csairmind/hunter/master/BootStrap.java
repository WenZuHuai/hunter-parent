package com.csair.csairmind.hunter.master;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@ComponentScan(basePackages = {"com.csair.csairmind.hunter.common.plug"})
@ImportResource({ "classpath:dubbo-producer.xml" })
public class BootStrap {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(BootStrap.class, args);
        System.in.read();
    }
}