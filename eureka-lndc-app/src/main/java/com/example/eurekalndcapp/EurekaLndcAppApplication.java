package com.example.eurekalndcapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaLndcAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaLndcAppApplication.class, args);
    }

}
