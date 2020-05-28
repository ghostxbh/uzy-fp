package com.uzykj.fpsso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.uzykj.fpsso.mapper")
public class FpSsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FpSsoApplication.class, args);
    }

}
