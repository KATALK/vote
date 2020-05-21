package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2配置类
 */
@Configuration   //让spring来加载该配置类
@EnableSwagger2   //开启Swagger2的功能
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//用来展示Api的基本信息
                .select()//返回一个展示ApiSelectBuilder实例，用来控制那些接口暴露给Swagger来展示
                .apis(RequestHandlerSelectors.basePackage("com.example.controller")) //用来配置包扫描路径
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springBoot利用swagger构建api文档")
                .description("简单优雅的restfun风格，http://blog.csdn.net/forezp")
                .termsOfServiceUrl("http://blog.csdn.net/forezp")
                .version("1.0")
                .build();
    }
}
