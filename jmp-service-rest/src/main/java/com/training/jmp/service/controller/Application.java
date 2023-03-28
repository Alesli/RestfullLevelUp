package com.training.jmp.service.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Spring Boot application starter class
 */

@SpringBootApplication
@ComponentScan("com.training.jmp.service")
@EntityScan({"com.training.jmp.service.dto", "com.training.jmp.service.entity"})
@EnableJpaRepositories(basePackages = "com.training.jmp.service.repo")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
