package com.uzykj.fpadmin;

import com.uzykj.fpcommon.apollo.ApolloConfigProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;

@SpringBootApplication
@ComponentScan(basePackages = {"com.uzykj.fpadmin", "com.uzykj.fpcommon.apollo"})
public class FpAdminApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(FpAdminApplication.class, args);
    }

    @Override
    public void run(String... args) {
        // 获取所有配置
        HashMap<String, String> apolloConfig = ApolloConfigProvider.getAllParams();
        System.out.println("Apollo Config: " + apolloConfig);
    }
}
