package com.springboot.weking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Wk01Application {

    public static void main(String[] args) {
        SpringApplication.run(Wk01Application.class, args);
    }

}
