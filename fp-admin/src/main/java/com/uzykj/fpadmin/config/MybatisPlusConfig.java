package com.uzykj.fpadmin.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ghostxbh
 * @date 2020/6/18
 * @description
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.uzykj.fpadmin.mapper")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor getInterceptor() {
        PaginationInterceptor interceptor = new PaginationInterceptor();
        return interceptor;
    }
}
