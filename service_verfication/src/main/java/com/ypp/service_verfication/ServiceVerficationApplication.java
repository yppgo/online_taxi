package com.ypp.service_verfication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceVerficationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceVerficationApplication.class, args);
    }

}
