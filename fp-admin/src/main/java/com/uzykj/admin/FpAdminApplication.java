package com.uzykj.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName FpAdminApplication
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/5
 * @Version 1.0
 */
@MapperScan("com.uzykj.**.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}, scanBasePackages = "com.uzykj.**")
public class FpAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(FpAdminApplication.class, args);
    }
}
