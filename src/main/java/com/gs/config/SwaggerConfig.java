package com.gs.config;

import java.util.ArrayList;
import java.util.List;

import com.gs.RunApplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {

        // 统一配置ApiResponse
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(200).message("查询成功").build());
        responseMessageList.add(new ResponseMessageBuilder().code(201).message("创建成功").build());
        responseMessageList.add(new ResponseMessageBuilder().code(400).message("请求参数缺失").build());
        responseMessageList.add(new ResponseMessageBuilder().code(401).message("认证失败").build());
        responseMessageList.add(new ResponseMessageBuilder().code(403).message("没有权限").build());
        responseMessageList.add(new ResponseMessageBuilder().code(422).message("请求参数验证不通过").build());
        responseMessageList.add(new ResponseMessageBuilder().code(404).message("请求路径不存在").build());
        responseMessageList.add(new ResponseMessageBuilder().code(500).message("内部服务器错误").build());

        return new Docket(DocumentationType.SWAGGER_2)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessageList)
                .globalResponseMessage(RequestMethod.POST, responseMessageList)
                .globalResponseMessage(RequestMethod.PUT, responseMessageList)
                .globalResponseMessage(RequestMethod.DELETE, responseMessageList)
                .apiInfo(apiInfo())
                .select()
                // 设置basePackage会将包下的所有类的所有方法作为api
                .apis(RequestHandlerSelectors.basePackage("com.gs.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("api文档")
                .description("")
                .contact(new Contact("姓名", "博客地址", "邮箱"))
                .version("1.0")
                .build();
    }
}
