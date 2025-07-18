package com.heulwen.elearnweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.heulwen")
@ComponentScan(basePackages = {
    "com.heulwen.controllers",
    "com.heulwen.repositories",
    "com.heulwen.services",
    "com.heulwen.mapper",
    "com.heulwen.exceptions",
    "com.heulwen.configs"
})
@EnableJpaRepositories(basePackages = "com.heulwen.repositories")
@EntityScan(basePackages = "com.heulwen.pojo")
public class ElearnwebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElearnwebApplication.class, args);
    }

}
