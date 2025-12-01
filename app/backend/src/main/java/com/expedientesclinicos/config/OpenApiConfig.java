package com.expedientesclinicos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI expedienteClinicosOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Expedientes Clínicos API")
                        .description("Documentación de la API para gestión de pacientes, sesiones, entrevista inicial y consentimiento.")
                        .version("v1")
                        .license(new License().name("Proprietary"))
                );
    }
}
