package com.example.cardgame;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication @Slf4j
class CardGameApplication {
    public static void main(String[] args) {
        SpringApplication.run(CardGameApplication.class, args);
        log.info("Application démarrée sur http://localhost:8080");
        log.info("Swagger : http://localhost:8080/swagger-ui.html");
    }
}