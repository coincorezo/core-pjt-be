package com.pjt.core.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        String schemeName = "Bearer Authentication";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(schemeName);

        SecurityScheme securityScheme = new SecurityScheme().name("schemeName").type(SecurityScheme.Type.HTTP)
                .scheme("bearer").bearerFormat("JWT");
        Components components = new Components().addSecuritySchemes(schemeName, securityScheme);

		return new OpenAPI()
				.info(new Info().title("CrazeCoin API"))
				.externalDocs(new ExternalDocumentation()
						.description("CrazeCoin Wiki")
						.url("https://github.com/coincorezo/core-pjt-be/wiki"))
				.addSecurityItem(securityRequirement)
				.components(components);
    }

    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("all")
                .packagesToScan("com.pjt.core")
                .build();
    }

    @Bean
    public GroupedOpenApi commonCodeApi() {
        return GroupedOpenApi.builder()
                .group("common")
                .packagesToScan("com.pjt.core.common.code")
                .build();
    }

    @Bean
    public GroupedOpenApi boardApi() {
        return GroupedOpenApi.builder()
                .group("board")
                .packagesToScan("com.pjt.core.board")
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
