package com.uzykj.common.mail.domain;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "mail")
public class MailConfiguration {
    private List<MailProperties> configs;

    @Data
    public static class MailProperties {
        /**
         *
         */
        private String key;
        /**
         * host
         */
        private String host;
        /**
         * 端口
         */
        private Integer port;
        /**
         * 密码
         */
        private String username;
        /**
         * 密码
         */
        private String password;
        /**
         * 协议
         */
        private String protocol;
        /**
         * 默认编码
         */
        private String defaultEncoding;
    }
}
