package com.prateek.reap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication

public class ReapApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReapApplication.class, args);
    }

}
