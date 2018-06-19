package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ssmshiro.**.dao")
public class SSMShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSMShiroApplication.class,args);
    }
}
