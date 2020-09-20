package com.uzykj.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 网关启动程序
 *
 * @author ghostxbh
 */
@EnableDiscoveryClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FpGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(FpGatewayApplication.class, args);
        System.out.println("<<======== 网关中心启动成功 =========>>");
    }
}
