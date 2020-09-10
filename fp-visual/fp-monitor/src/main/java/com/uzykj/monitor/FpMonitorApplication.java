package com.uzykj.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * 监控中心
 * 
 * @author ghostxbh
 */
@EnableAdminServer
@SpringCloudApplication
public class FpMonitorApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(FpMonitorApplication.class, args);
        System.out.println("======== 监控中心启动成功 =========");
    }
}
