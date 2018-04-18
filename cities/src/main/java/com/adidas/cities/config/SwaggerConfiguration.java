package com.adidas.cities.config;

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
                .groupName("api-cities")
                .apiInfo(apiInfo())
                .directModelSubstitute(Date.class, Date.class)
                .select()
                .paths(regex("/cities.*"))
                .build();
    }
 
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Cities REST api")
                .description("PoC of a REST api, Cities")
                .contact("laurafs@gmail.com")
                .version("1.0")
                .build();
    }
}