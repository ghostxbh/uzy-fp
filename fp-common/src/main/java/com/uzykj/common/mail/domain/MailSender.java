package com.uzykj.common.mail.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MailSender {
    /**
     * 发件地址
     */
    private String fromAddress;
    /**
     * 收件地址
     */
    private String toAddress;
    /**
     * 抄送地址
     */
    private String[] ccAddress;
    /**
     * 密抄送地址
     */
    private String[] bccAddress;
    /**
     * 标题
     */
    private String subject;
    /**
     * 内容
     */
    private String content;
    /**
     * 附件
     */
    private Map<String, String> files;

    /**
     * 发件类型
     * 1、批量
     * 2、单条
     */
    private Integer sendType;
    /**
     * 发件状态
     * 0、未发送
     * 1、已发送
     * -1、发送失败
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 签名
     */
    private String sign;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 企业ID
     */
    private Integer companyId;
    /**
     * 配置ID
     */
    private Integer propertiesId;
    /**
     * 批量ID
     */
    private Integer batchId;
    /**
     * 发送时间（定时发送）
     */
    private Date sendTime;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 更新日期
     */
    private Date updateTime;
}
