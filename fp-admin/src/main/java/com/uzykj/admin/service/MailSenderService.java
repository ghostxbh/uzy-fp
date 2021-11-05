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
import java.util.Arrays;

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
        int batchId = mailBatchLogService.addBatchLog(mailBatchLog);

        String fromAddress = mailProperties.getUsername();
        String receiveAddress = mailBatchLog.getReceiveAddress();

        Arrays.stream(receiveAddress.split(";")).peek(receive -> {
            try {
                MailLog build = MailLog.builder()
                        .batchId(batchId)
                        .status(MailSendStatus.TOBE.getCode())
                        .remark(mailBatchLog.getRemark())
                        .fromAddress(fromAddress)
                        .toAddress(receive)
                        .build();
                int logId = mailLogService.addLog(build);
                mailService.sendMail(MailSenderBo.toSender(mailBatchLog, fromAddress, receive));
                mailLogService.updateStatus(logId, MailSendStatus.SUCCESS.getCode());
            } catch (MessagingException e) {
                log.warn("mail real sender fail, toAddress: " + receive);
                log.error("mail sender error: ", e);
            }
        });
        mailBatchLogService.updateStatus(batchId, MailSendStatus.SUCCESS.getCode());
    }
}
