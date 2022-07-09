package com.yygh.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created on 2022/7/8.
 *
 * @author Ann Zhou
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.yygh")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.yygh")
public class ServiceUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUserApplication.class, args);
    }
}
