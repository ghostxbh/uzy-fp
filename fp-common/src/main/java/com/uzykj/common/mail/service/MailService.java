package com.uzykj.common.mail.service;

import com.uzykj.common.mail.config.MailSenderConfig;
import com.uzykj.common.mail.domain.MailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Slf4j
@Component
public class MailService {
    @Resource
    private MailSenderConfig mailSenderConfig;

    public void sendMail(MailSender mailSender) throws MessagingException {
        JavaMailSenderImpl mailSenderConfigSender = mailSenderConfig.getSender(mailSender.getMailKey());
        MimeMessage message = mailSenderConfigSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(mailSender.getFromAddress());
        helper.setTo(mailSender.getToAddress());
        helper.setSubject(mailSender.getSubject());
        if (mailSender.getCcAddress() != null) {
            helper.setCc(mailSender.getCcAddress());
        }
        if (mailSender.getBccAddress() != null) {
            helper.setBcc(mailSender.getBccAddress());
        }

        Context context = new Context();
        context.setVariable("context", mailSender);

        String emailContent = this.getTemplateEngine().process("template", context);
        helper.setText(emailContent, true);
        mailSenderConfigSender.send(message);
    }

    private TemplateEngine getTemplateEngine() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode("HTML");
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        final TemplateEngine tplEngine = new TemplateEngine();
        tplEngine.setTemplateResolver(templateResolver);
        return tplEngine;
    }
}