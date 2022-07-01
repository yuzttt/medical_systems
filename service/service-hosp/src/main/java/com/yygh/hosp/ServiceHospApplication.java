package com.yygh.hosp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created on 2022/6/25.
 *
 * @author Ann Zhou
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.yygh")
public class ServiceHospApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceHospApplication.class, args);
    }
}