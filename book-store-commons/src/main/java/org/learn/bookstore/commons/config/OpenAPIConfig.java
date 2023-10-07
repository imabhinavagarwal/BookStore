package org.learn.bookstore.commons.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author abhinavagarwal
 * <p>
 * Created On: 07-Oct-2023
 */

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI().info(new Info().title("Book Store").description("Book Store API").version("1.0"));
    }
}
