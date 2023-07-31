package com.martheseladvier.interstellartravel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Interstellar Travel Journey Cost Calculator")
                        .description("This API will calculate the shortest path and associated cost for a return journey between two interstellar accelerators, or calculate the cost of a transfer to an accelerator.")
                        .version("1.0"));
    }
}