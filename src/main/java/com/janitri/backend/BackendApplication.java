package com.janitri.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.janitri.backend.repositories")
public class BackendApplication {
    public static void main(String[]args){
        SpringApplication.run(BackendApplication.class, args);
    }

}
