package com.onepeice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//@MapperScan(basePackages = {"com.onepeice.fmmall.dao"})
//@MapperScan("com.onepeice.fmmall.dao")
//整合tkmapper，@MapperScan注解要换成tkmapper的：import tk.mybatis.spring.annotation.MapperScan;
@MapperScan("com.onepeice.fmmall.dao")
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
