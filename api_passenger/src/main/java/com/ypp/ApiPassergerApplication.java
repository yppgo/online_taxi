package com.ypp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//启动类
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ApiPassergerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiPassergerApplication.class);
    }
}
