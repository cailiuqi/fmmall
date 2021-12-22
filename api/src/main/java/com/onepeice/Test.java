package com.onepeice;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Test {

    @Scheduled(cron = "0/5 * * * * ?")
    public void print(){
        System.out.println("scheuld执行======");
    }
}
