package com.uzykj.fpfile.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.MultipartConfigElement;

/**
 * @author ghostxbh
 * @date 2020/6/5
 * @description
 */
@Component
public class ServletFileConfig {
    @Value("${oss.max_file_size}")
    private String MAX_FILE_SIZE;

    /**
     * 配置上传文件大小的配置
     *
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize(MAX_FILE_SIZE);
        /// 总上传数据大小
        factory.setMaxRequestSize(MAX_FILE_SIZE);
        return factory.createMultipartConfig();
    }
}
