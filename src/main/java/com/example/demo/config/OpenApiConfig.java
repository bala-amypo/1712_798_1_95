// package com.example.demo.config;

// import io.swagger.v3.oas.models.OpenAPI;
// import io.swagger.v3.oas.models.info.Info;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import java.util.List;

// @Configuration
// public class OpenApiConfig {

//     @Bean
//     public OpenAPI openAPI() {
//         return new OpenAPI()
//                .servers(List.of(
//                 new Server().url("https://9237.pro604cr.amypo.ai/")));
//     }
// }
package com.example.demo.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    // Swagger/OpenAPI removed to avoid dependency issues
}
