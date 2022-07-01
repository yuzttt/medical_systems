package com.yygh.cmn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created on 2022/6/30.
 *
 * @author Ann Zhou
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.yygh")
public class ServiceCmnApplication {
    public static void main(String[] args){
        SpringApplication.run(ServiceCmnApplication.class,args);
    }
}
