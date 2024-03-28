package com.ynov.webfullstack.imagelib;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(){
        return args -> {
            System.out.println("Database initialized");
        };
    }
}
