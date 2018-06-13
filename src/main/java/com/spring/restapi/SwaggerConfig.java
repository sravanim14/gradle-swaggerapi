package com.spring.restapi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.basePackage("com.spring.restapi.controller"))              
          .paths(PathSelectors.ant("/*"))                          
          .build(); 
          .apiInfo(apiInfo());
}
 
private ApiInfo apiInfo() {
     return new ApiInfo(
       "My REST API", 
       "My SpringBoot REST API.", 
       "API TOS", 
       "Terms of service", 
       new Contact("Sai Kishore", "www.google.com", "skishore@in.ibm.com"), 
       "License of API", "API license URL", Collections.emptyList());
}		  
}