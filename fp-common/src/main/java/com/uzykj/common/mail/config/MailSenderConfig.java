package com.uzykj.common.mail.config;

import com.uzykj.common.mail.domain.MailConfiguration;
import com.uzykj.system.domain.MailProperties;
import com.uzykj.system.service.MailPropertiesService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@AllArgsConstructor
public class MailSenderConfig {
    @Autowired
    private MailConfiguration mailConfiguration;
    @Autowired
    private MailPropertiesService mailPropertiesService;

    private final Map<String, JavaMailSenderImpl> senderMap;

    /**
     * 初始化 sender
     */
    @Bean
    public void buildMailSender() {
        List<MailConfiguration.MailProperties> mailConfigs = mailConfiguration.getConfigs();
        List<MailProperties> mailProperties = mailPropertiesService.selectByBusiness(3);

        mailConfigs.forEach(config -> {
            MailProperties build = MailProperties.builder()
                    .mailKey(config.getKey())
                    .mailHost(config.getHost())
                    .mailPort(String.valueOf(config.getPort()))
                    .mailUser(config.getUsername())
                    .mailPwd(config.getPassword())
                    .protocol(config.getProtocol())
                    .defaultEncoding(config.getDefaultEncoding())
                    .build();
            mailProperties.add(build);
        });

        mailProperties.forEach(propertie -> {
            // 邮件发送者
            JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
            javaMailSender.setDefaultEncoding(propertie.getDefaultEncoding());
            javaMailSender.setHost(propertie.getMailHost());
            javaMailSender.setPort(Integer.parseInt(propertie.getMailPort()));
            javaMailSender.setProtocol(propertie.getProtocol());
            javaMailSender.setUsername(propertie.getMailUser());
            javaMailSender.setPassword(propertie.getMailPwd());
            // 添加数据
            senderMap.put(propertie.getMailKey(), javaMailSender);
        });

        log.info("初始化mailSender, 成功加载 " + mailProperties.size() + " 条配置");
    }

    /**
     * 获取MailSender
     *
     * @return CustomMailSender
     */
    public JavaMailSenderImpl getSender(String mailKey) {
        if (senderMap.isEmpty()) {
            buildMailSender();
        }
        //返回一个JavaMailSender
        mailKey = StringUtils.isEmpty(mailKey) ? "defualt" : mailKey;
        return senderMap.get(mailKey);
    }

    /**
     * 清理 sender
     */
    public void clear() {
        senderMap.clear();
    }
}
