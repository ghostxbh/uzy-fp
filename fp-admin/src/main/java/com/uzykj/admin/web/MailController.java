package com.uzykj.admin.web;

import com.baomidou.mybatisplus.extension.api.R;
import com.uzykj.admin.service.MailSenderService;
import com.uzykj.system.domain.MailBatchLog;
import com.uzykj.system.enums.MailSendStatus;
import com.uzykj.system.enums.MailSendType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName MailController
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/5
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    private MailSenderService mailSenderService;

    @PostMapping("/send")
    public R sender(@RequestBody MailBatchLog mailBatchLog) {
        try {
            if (ObjectUtils.isEmpty(mailBatchLog.getUserId())
                    || ObjectUtils.isEmpty(mailBatchLog.getPropertiesId())
                    || ObjectUtils.isEmpty(mailBatchLog.getReceiveAddress())) {
                return R.failed("缺少必填参数");
            }

            String[] receiveSplit = mailBatchLog.getReceiveAddress().split(";");
            int sendType = receiveSplit.length > 1 ? MailSendType.BATCH.getCode() : MailSendType.SINGLE.getCode();
            mailBatchLog.setSendType(sendType);
            mailBatchLog.setStatus(MailSendStatus.TOBE.getCode());

            mailSenderService.realSender(mailBatchLog);
            return R.ok(null);
        } catch (Exception e) {
            log.error("mail sender error:", e);
            return R.failed("mail sender error");
        }
    }
}
