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
 * @ClassName MailLog
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/4
 * @Version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("fp_mail_log")
public class MailLog {
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 发件地址
     */
    private String fromAddress;
    /**
     * 收件地址
     */
    private String toAddress;
    /**
     * 发送状态
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 批量ID
     */
    private Integer batchId;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 更新日期
     */
    private Date updateTime;
}
