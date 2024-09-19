package com.anderson.contasapagar.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;

@OpenAPIDefinition(
        info = @Info(
                title = "Contas A Pagar",
                version = "1.0",
                description = "API Contas A Pagar"
        )
)
@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi publicApiV1() {
        String packagesToscan[] = {"com.anderson.contasapagar.api.resources"};
        return GroupedOpenApi.builder()
                .group("V1")
                .displayName("Version 1")
                .packagesToScan(packagesToscan)
                .build();
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
