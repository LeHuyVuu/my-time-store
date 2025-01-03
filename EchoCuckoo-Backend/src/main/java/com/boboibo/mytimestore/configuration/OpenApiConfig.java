package com.boboibo.mytimestore.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI(@Value("${open.api.title}") String title,
                           @Value("${open.api.url}") String url,
                           @Value("${open.api.version}") String version,
                           @Value("${open.api.server}") String server) {
        return new OpenAPI()
                .info(new Info()
                        .title(title)
                        .description("This is the API documentation for My Time Store application")
                        .version(version)
                        .license(new License().name("Apache 2.0").url(url))
                )
                .servers(Collections.singletonList(new Server().url(server)));
    }
}
