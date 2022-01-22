package com.example.AirlineManagementSystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.readers.operation.ResponseMessagesReader;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;
@Configuration
@EnableSwagger2
public class SwaggerConfig {

        @Bean
        public Docket api(){
            List<ResponseMessagesReader> responseMessage = new ArrayList<>();
            //    responseMessage.add(new ResponseMessageBuilder().code(500).message("internalMessageErrro"));

            return  new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .useDefaultResponseMessages(false)
                    //       .globalRequestParameters(RequestMethod.POST, responseMessage)
                    //     .globalRequestParameters(RequestMethod.GET, responseMessage)
                    //   .globalRequestParameters(RequestMethod.PUT , responseMessage)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.example.AirlineManagementSystem"))
                    .paths(PathSelectors.any())
                    .build();
        }


        private ApiInfo apiInfo(){
            return  new ApiInfoBuilder().title("Airline Management System")
                    .description("SM AIRLINE")
                    .version("2.0").build();

        }
    }