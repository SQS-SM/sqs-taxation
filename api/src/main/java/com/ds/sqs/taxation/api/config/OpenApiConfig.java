package com.ds.sqs.taxation.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI taxationOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("Taxation Service API")
                        .description("API documentation for Taxation microservice")
                        .version("v1.0"))

                .addSecurityItem(
                        new SecurityRequirement().addList("bearerAuth")
                )
                .components(new Components()

                        // Global error responses
                        .addResponses("InternalServerError",
                                new ApiResponse()
                                        .description("Internal server error")
                                        .content(new Content().addMediaType(
                                                "application/json",
                                                new MediaType().schema(new Schema<>().$ref("#/components/schemas/ErrorResponse"))
                                        )))

                        .addResponses("BadRequest",
                                new ApiResponse().description("Invalid request"))

                        .addResponses("NotFound",
                                new ApiResponse().description("Resource not found"))

                        // Security scheme
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }
}