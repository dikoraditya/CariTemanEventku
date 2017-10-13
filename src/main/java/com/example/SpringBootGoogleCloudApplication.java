package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan
public class SpringBootGoogleCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootGoogleCloudApplication.class, args);
    }


}
