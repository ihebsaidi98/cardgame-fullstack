package com.example.cardgame.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Configuration Swagger. */
@Configuration @Slf4j
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        log.info("Swagger activé");
        return new OpenAPI()
                .info(new Info()
                        .title("Card Game API")
                        .version("1.0")
                        .description("Test technique - Jeu de cartes")
                        .contact(new Contact().name("Iheb")));
    }
}