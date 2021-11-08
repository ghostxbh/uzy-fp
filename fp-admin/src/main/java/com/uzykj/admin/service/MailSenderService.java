package com.uzykj.admin.service;

import com.uzykj.admin.domain.MailSenderBo;
import com.uzykj.common.mail.service.MailService;
import com.uzykj.system.domain.MailBatchLog;
import com.uzykj.system.domain.MailLog;
import com.uzykj.system.domain.MailProperties;
import com.uzykj.system.enums.MailSendStatus;
import com.uzykj.system.service.MailBatchLogService;
import com.uzykj.system.service.MailLogService;
import com.uzykj.system.service.MailPropertiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName MailSenderService
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/5
 * @Version 1.0
 */
@Slf4j
@Service
public class MailSenderService {
    @Autowired
    private MailPropertiesService mailPropertiesService;
    @Autowired
    private MailBatchLogService mailBatchLogService;
    @Autowired
    private MailLogService mailLogService;
    @Autowired
    private MailService mailService;

    public void realSender(MailBatchLog mailBatchLog) {
        MailProperties mailProperties = mailPropertiesService.selectOne(mailBatchLog.getPropertiesId());
        mailBatchLogService.addBatchLog(mailBatchLog);
        int batchId = mailBatchLog.getId();
        String receiveAddress = mailBatchLog.getReceiveAddress();
        if (receiveAddress.contains(";")) {
            for (String receive : receiveAddress.split(";")) {
                this.addMailLogAndSend(mailBatchLog, mailProperties, receive, batchId);
            }
        } else {
            this.addMailLogAndSend(mailBatchLog, mailProperties, receiveAddress, batchId);
        }
        mailBatchLogService.updateStatus(batchId, MailSendStatus.SUCCESS.getCode());
    }

    private void addMailLogAndSend(MailBatchLog mailBatchLog, MailProperties mailProperties, String receive, int batchId) {
        String fromAddress = mailProperties.getMailUser();
        String receiveAddress = mailBatchLog.getReceiveAddress();
        MailLog build = MailLog.builder()
                .batchId(batchId)
                .status(MailSendStatus.TOBE.getCode())
                .remark(mailBatchLog.getRemark())
                .fromAddress(fromAddress)
                .toAddress(receive)
                .build();
        mailLogService.addLog(build);
        try {
            mailService.sendMail(MailSenderBo.toSender(mailBatchLog, mailProperties.getMailKey(), fromAddress, receive));
        } catch (MessagingException e) {
            log.warn("mail real sender fail, toAddress: " + receiveAddress);
            log.error("mail sender error: ", e);
        }
        mailLogService.updateStatus(build.getId(), MailSendStatus.SUCCESS.getCode());
    }
}
