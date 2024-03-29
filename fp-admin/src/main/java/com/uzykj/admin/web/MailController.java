package com.uzykj.admin.web;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import com.uzykj.admin.service.MailSenderService;
import com.uzykj.common.core.response.Rep;
import com.uzykj.system.domain.MailBatchLog;
import com.uzykj.system.enums.MailSendStatus;
import com.uzykj.system.enums.MailSendType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/api/mail")
@Api("mail")
public class MailController {
    @Autowired
    private MailSenderService mailSenderService;

    @PostMapping("/send")
    @ApiOperation("邮件发送")
    public R<?> sendOfPost(@RequestBody MailBatchLog mailBatchLog) {
        log.info("sender param: " + mailBatchLog.toString());
        try {
            if (ObjectUtils.isEmpty(mailBatchLog.getUserId())
                    || ObjectUtils.isEmpty(mailBatchLog.getPropertiesId())
                    || ObjectUtils.isEmpty(mailBatchLog.getReceiveAddress())) {
                return Rep.error("缺少必填参数");
            }

            int sendType = mailBatchLog.getReceiveAddress().contains(";") ? MailSendType.BATCH.getCode() : MailSendType.SINGLE.getCode();
            mailBatchLog.setSendType(sendType);
            mailBatchLog.setStatus(MailSendStatus.TOBE.getCode());

            mailSenderService.realSend(mailBatchLog);
            return Rep.success();
        } catch (Exception e) {
            log.error("mail sender error:", e);
            return Rep.error("mail sender error");
        }
    }

    @GetMapping("/send")
    @ApiOperation("邮件发送")
    public R<?> sendOfGet(@RequestParam String mailParams) {
        log.info("sender param: " + mailParams);
        try {
            MailBatchLog mailBatchLog = JSONObject.parseObject(mailParams, MailBatchLog.class);
            if (ObjectUtils.isEmpty(mailBatchLog.getUserId())
                    || ObjectUtils.isEmpty(mailBatchLog.getPropertiesId())
                    || ObjectUtils.isEmpty(mailBatchLog.getReceiveAddress())) {
                return Rep.error("缺少必填参数");
            }

            int sendType = mailBatchLog.getReceiveAddress().contains(";") ? MailSendType.BATCH.getCode() : MailSendType.SINGLE.getCode();
            mailBatchLog.setSendType(sendType);
            mailBatchLog.setStatus(MailSendStatus.TOBE.getCode());

            mailSenderService.realSend(mailBatchLog);
            return Rep.success();
        } catch (Exception e) {
            log.error("mail sender error:", e);
            return Rep.error("mail sender error");
        }
    }
}
