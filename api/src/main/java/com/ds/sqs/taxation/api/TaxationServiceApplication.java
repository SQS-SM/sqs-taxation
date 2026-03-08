package com.ds.sqs.taxation.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = {"com.ds.sqs.taxation"})
public class TaxationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaxationServiceApplication.class, args);
    }
}
