package com.uzykj.admin.web;

import com.baomidou.mybatisplus.extension.api.R;
import com.uzykj.common.core.response.Rep;
import com.uzykj.system.domain.MailProperties;
import com.uzykj.system.service.MailPropertiesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/api/mail/config")
@Api("mailConfig")
public class MailConfigController {
    @Autowired
    private MailPropertiesService mailPropertiesService;

    @PostMapping("/add")
    @ApiOperation("增加邮件配置")
    public R<?> addMailConfig(@RequestBody MailProperties mailProperties) {
        try {
            if (ObjectUtils.isEmpty(mailProperties.getUserId())
                    || ObjectUtils.isEmpty(mailProperties.getMailHost())
                    || ObjectUtils.isEmpty(mailProperties.getMailUser())
                    || ObjectUtils.isEmpty(mailProperties.getMailPwd())) {
                return Rep.error("缺少必填参数");
            }

            MailProperties properties = mailPropertiesService.selectOneByKey(mailProperties.getMailKey());
            if (properties != null) {
                return Rep.error("配置" + mailProperties.getMailKey() + "已存在");
            }

            mailPropertiesService.addProperties(mailProperties);
            return Rep.success();
        } catch (Exception e) {
            log.error("add mail properties error", e);
            return Rep.error("add mail properties error");
        }
    }

    @PutMapping("/edit")
    @ApiOperation("编辑邮件配置")
    public R<?> updateMailConfig(@RequestBody MailProperties mailProperties) {
        try {
            if (ObjectUtils.isEmpty(mailProperties.getUserId())) {
                return Rep.error("缺少必填参数");
            }
            mailProperties.setUpdator(mailProperties.getUserId());
            mailPropertiesService.updateProperties(mailProperties);
            return Rep.success();
        } catch (Exception e) {
            log.error("update mail properties error", e);
            return Rep.error("update mail properties error");
        }
    }

    @DeleteMapping("/remove")
    @ApiOperation("删除邮件配置")
    public R<?> deleteMailConfig(@RequestParam Integer[] ids) {
        try {
            if (ObjectUtils.isEmpty(ids)) {
                return Rep.error("缺少必填参数");
            }
            mailPropertiesService.deletePropertiesByIds(ids);
            return Rep.success();
        } catch (Exception e) {
            log.error("update mail properties error", e);
            return Rep.error("update mail properties error");
        }
    }

    @GetMapping("/user/{userId}")
    @ApiOperation("获取用户的邮件配置列表")
    public R<?> listByUser(@PathVariable Integer userId) {
        try {
            if (ObjectUtils.isEmpty(userId)) {
                return Rep.error("没有用户ID");
            }
            List<MailProperties> mailProperties = mailPropertiesService.selectByUser(userId);
            return Rep.success(mailProperties);
        } catch (Exception e) {
            log.error("get mail properties list by user error", e);
            return Rep.error("get mail properties list by user error");
        }
    }

    @GetMapping("/company/{companyId}")
    @ApiOperation("获取企业的邮件配置列表")
    public R<?> listByCompany(@PathVariable Integer companyId) {
        try {
            if (ObjectUtils.isEmpty(companyId)) {
                return Rep.error("没有用户ID");
            }
            List<MailProperties> mailProperties = mailPropertiesService.selectByCompany(companyId);
            return Rep.success(mailProperties);
        } catch (Exception e) {
            log.error("get mail properties list by company error", e);
            return Rep.error("get mail properties list by company error");
        }
    }
}
