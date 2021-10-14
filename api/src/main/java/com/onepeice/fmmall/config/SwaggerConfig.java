package com.onepeice.fmmall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //封装接口文档信息
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)//指定文档风格为SWAGGER_2
                .apiInfo(apiInfo())//指定生成的文档的封面信息及标题之类的
                .select()//com.example.springbootjpa.jpademo.controller
                .apis(RequestHandlerSelectors.basePackage("com.onepeice.fmmall.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("利用swagger2构建的API文档")
                .description("用restful风格写接口")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
