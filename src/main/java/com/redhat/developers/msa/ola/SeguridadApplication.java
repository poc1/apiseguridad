package com.redhat.developers.msa.ola;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SeguridadApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeguridadApplication.class, args);
    }

    @Bean
	   public Docket newsApi() {
	       return new Docket(DocumentationType.SWAGGER_2)
	               .select()
             .apis(RequestHandlerSelectors.any())
             //.apis(RequestHandlerSelectors.basePackage("com.demo.controller"))
             .paths(regex("/api/.*"))
             .build()
         .pathMapping("/")
         .apiInfo(apiInfo());


	               
	   }

	   
	   private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	            .title("REST API Seguridad")
	            .description("Microservicio Seguridad")
//	            .contact(new Contact("AN Global", "",/*"http://developers.redhat.com"*/ "paul.montoya@agsnasoft.com"))
//	            .license("Apache License Version 2.0")
//	            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
	            .version("1.0.0")
	            .build();
	    }
}
