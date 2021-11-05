package com.uzykj.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName MailBatchLog
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/4
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("fp_mail_batch_log")
public class MailBatchLog {
    @TableId(type = IdType.AUTO)
    private Integer id;
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
    private String files;
    /**
     * 收件地址
     */
    private String receiveAddress;
    /**
     * 抄送地址
     */
    private String ccAddress;
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
