package com.uzykj.admin.web;

import com.baomidou.mybatisplus.extension.api.R;
import com.uzykj.system.domain.MailProperties;
import com.uzykj.system.service.MailPropertiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName MailConfigController
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/5
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/mail/config")
public class MailConfigController {
    @Autowired
    private MailPropertiesService mailPropertiesService;

    @PostMapping("/add")
    public R addMailConfig(@RequestBody MailProperties mailProperties) {
        try {
            if (ObjectUtils.isEmpty(mailProperties.getUserId())
                    || ObjectUtils.isEmpty(mailProperties.getMailHost())
                    || ObjectUtils.isEmpty(mailProperties.getMailUser())
                    || ObjectUtils.isEmpty(mailProperties.getMailPwd())) {
                return R.failed("缺少必填参数");
            }

            MailProperties properties = mailPropertiesService.selectOneByKey(mailProperties.getMailKey());
            if (properties != null) {
                return R.failed("配置" + mailProperties.getMailKey() + "已存在");
            }

            mailPropertiesService.addProperties(mailProperties);
            return R.ok(null);
        } catch (Exception e) {
            log.error("add mail properties error", e);
            return R.failed("add mail properties error");
        }
    }

    @PostMapping("/edit")
    public R updateMailConfig(@RequestBody MailProperties mailProperties) {
        try {
            if (ObjectUtils.isEmpty(mailProperties.getUserId())) {
                return R.failed("缺少必填参数");
            }
            mailProperties.setUpdator(mailProperties.getUserId());
            mailPropertiesService.updateProperties(mailProperties);
            return R.ok(null);
        } catch (Exception e) {
            log.error("update mail properties error", e);
            return R.failed("update mail properties error");
        }
    }

    @GetMapping("/user/{userId}")
    public R<List<MailProperties>> listByUser(@PathVariable Integer userId) {
        try {
            if (ObjectUtils.isEmpty(userId)) {
                return R.failed("没有用户ID");
            }
            List<MailProperties> mailProperties = mailPropertiesService.selectByUser(userId);
            return R.ok(mailProperties);
        } catch (Exception e) {
            log.error("get mail properties list by user error", e);
            return R.failed("get mail properties list by user error");
        }
    }

    @GetMapping("/company/{companyId}")
    public R<List<MailProperties>> listByCompany(@PathVariable Integer companyId) {
        try {
            if (ObjectUtils.isEmpty(companyId)) {
                return R.failed("没有用户ID");
            }
            List<MailProperties> mailProperties = mailPropertiesService.selectByCompany(companyId);
            return R.ok(mailProperties);
        } catch (Exception e) {
            log.error("get mail properties list by company error", e);
            return R.failed("get mail properties list by company error");
        }
    }
}
