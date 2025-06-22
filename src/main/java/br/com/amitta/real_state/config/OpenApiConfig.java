package br.com.amitta.real_state.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI realStateOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Real State API")
                .description("CRUD API for managing real estate properties")
                .version("1.0.0"));
    }
}