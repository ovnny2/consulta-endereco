package br.com.ovnny.consultaendereco.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI consultaEnderecoOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Consulta Endereço Wipro")
                .description("Exemplo de uma API de consulta e cálculo de frete via cep")
                .version("v0.0.1")
                .license(new License()
                        .name("MIT License - Copyright (c) 2023 Vinícius Ferreira")
                        .url("http://localhost:8080/swagger-ui/index.html")
                )
        );
    }


}