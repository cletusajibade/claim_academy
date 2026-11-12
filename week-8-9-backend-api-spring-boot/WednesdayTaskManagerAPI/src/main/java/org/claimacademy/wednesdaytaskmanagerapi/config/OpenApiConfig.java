package org.claimacademy.wednesdaytaskmanagerapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** The title, version and description shown at the top of http://localhost:8080/swagger-ui.html */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI taskManagerOpenApi() {
        return new OpenAPI().info(new Info()
                .title("Task Manager API")
                .version("1.0")
                .description("Capstone REST API"));
    }
}
