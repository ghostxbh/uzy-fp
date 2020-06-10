package com.uzykj.fpocr.config;

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
 * @author ghostxbh
 * @date 2020.2.8
 * @description swagger配置
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket tess4jApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Tess4j OCR")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.uzykj.fpocr.modules.tess4j.web"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket baiduApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Baidu OCR")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.uzykj.fpocr.modules.baidu.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("OCR 文字识别")
                .description("uzy fp ocr")
                .contact("ghostxbh")
                .version("1.0")
                .build();
    }
}
