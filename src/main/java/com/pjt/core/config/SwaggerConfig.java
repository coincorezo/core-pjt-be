package com.pjt.core.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("CrazeCoin API"))
                .externalDocs(new ExternalDocumentation()
                        .description("CrazeCoin Wiki")
                        .url("https://github.com/coincorezo/core-pjt-be/wiki"));
    }

    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("all")
                .packagesToScan("com.pjt.core")
                .build();
    }

    @Bean
    public GroupedOpenApi storageApi() {
        return GroupedOpenApi.builder()
                .group("storage")
                .packagesToScan("com.pjt.core.common.storage")
                .build();
    }

    @Bean
    public GroupedOpenApi bookApi() {
        return GroupedOpenApi.builder()
                .group("book")
                .packagesToScan("com.pjt.core.example")
                .build();
    }

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("user")
                .packagesToScan("com.pjt.core.user")
                .build();
    }

}
