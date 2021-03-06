package com.adidas.routes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Date;
 
 
@EnableSwagger2
@Configuration
public class SwaggerConfiguration {
 
	@Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("api-routes")
                .apiInfo(apiInfo())
                .directModelSubstitute(Date.class, Date.class)
                .select()
                .paths(regex("/routes.*"))
                .build();
    }
 
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Routes REST api")
                .description("PoC of a REST api, Routes")
                .contact("laurafs@gmail.com")
                .version("1.0")
                .build();
    }
}