package com.udacity.vehicles.config;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@ApiResponses(value = {
        @ApiResponse(code=400, message="this is a bad request, please follow the API documentation"),
        @ApiResponse(code=400, message="this is a bad request, please follow the API documentation"),
        @ApiResponse(code=400, message="this is a bad request, please follow the API documentation")
})
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Vehicle API ",
                "This API returns a list of vehucles API",
                "1.0",
                "http://localhost:8084",
                new Contact("Lehbib","localhost:8084","devexpress2013@gmail.com"),"Licence of API","localhost:8084", Collections.emptyList()
        );

    }
}
