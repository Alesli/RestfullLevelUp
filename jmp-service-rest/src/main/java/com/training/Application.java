package com.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Spring Boot application starter class
 */

//@SpringBootApplication(scanBasePackages = {"jmp-service-rest", "jmp-cloud-service-impl", "jmp-service-api"})
@SpringBootApplication
@ComponentScan("com.code.sample.event.service")
@EntityScan("com.code.sample.event.service.dto")
@EnableJpaRepositories(basePackages = "com.code.sample.event.service.impl.repository")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
