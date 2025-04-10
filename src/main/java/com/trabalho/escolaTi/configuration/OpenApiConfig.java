package com.trabalho.escolaTi.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI testeOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Crud Escola TI")
                        .version("1.0.0")
                        .description("Crud trabalho 5º semestre")
                );
    }
}
