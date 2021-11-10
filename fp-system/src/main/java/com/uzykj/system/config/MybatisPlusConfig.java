package com.uzykj.system.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName MybatisPlusConfig
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/8
 * @Version 1.0
 */
@Configuration
public class MybatisPlusConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() {
        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        mybatisPlus.setDataSource(dataSource);
        return mybatisPlus;
    }
}
