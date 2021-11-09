package com.uzykj.admin.web;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import com.uzykj.admin.service.MailSenderService;
import com.uzykj.system.domain.MailBatchLog;
import com.uzykj.system.enums.MailSendStatus;
import com.uzykj.system.enums.MailSendType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

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
    public R sendOfPost(@RequestBody MailBatchLog mailBatchLog) {
        log.info("sender param: " + mailBatchLog.toString());
        try {
            if (ObjectUtils.isEmpty(mailBatchLog.getUserId())
                    || ObjectUtils.isEmpty(mailBatchLog.getPropertiesId())
                    || ObjectUtils.isEmpty(mailBatchLog.getReceiveAddress())) {
                return R.failed("缺少必填参数");
            }

            int sendType = mailBatchLog.getReceiveAddress().contains(";") ? MailSendType.BATCH.getCode() : MailSendType.SINGLE.getCode();
            mailBatchLog.setSendType(sendType);
            mailBatchLog.setStatus(MailSendStatus.TOBE.getCode());

            mailSenderService.realSender(mailBatchLog);
            return R.ok(null);
        } catch (Exception e) {
            log.error("mail sender error:", e);
            return R.failed("mail sender error");
        }
    }

    @GetMapping("/send")
    public R sendOfGet(@RequestParam String mailParams) {
        log.info("sender param: " + mailParams);
        try {
            MailBatchLog mailBatchLog = JSONObject.parseObject(mailParams, MailBatchLog.class);
            if (ObjectUtils.isEmpty(mailBatchLog.getUserId())
                    || ObjectUtils.isEmpty(mailBatchLog.getPropertiesId())
                    || ObjectUtils.isEmpty(mailBatchLog.getReceiveAddress())) {
                return R.failed("缺少必填参数");
            }

            int sendType = mailBatchLog.getReceiveAddress().contains(";") ? MailSendType.BATCH.getCode() : MailSendType.SINGLE.getCode();
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
