package com.codemind.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.codemind")
public class CodemindUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodemindUserApplication.class, args);
    }

}