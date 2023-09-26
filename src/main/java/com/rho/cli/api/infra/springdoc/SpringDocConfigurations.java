package com.rho.cli.api.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("Stateless REST API CLINIC")
                        .description("Stateless REST API for Clinic Management System to schedule an appointment with a specialist doctor")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Rodrigo Medina")
                                .email("idrodrigo@icloud.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://rho/api/license")));
    }
    }

