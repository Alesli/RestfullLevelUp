package com.training.jmp.service.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * Spring Boot application starter class
 */
@SpringBootApplication(scanBasePackages = {"jmp-service-api", "jmp-cloud-service-impl", "jmp-dto", "jmp-service-rest"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
