package com.uzykj.fpocr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.uzykj.fpocr.mapper")
public class FpOcrApplication {

    public static void main(String[] args) {
        SpringApplication.run(FpOcrApplication.class, args);
    }

}
