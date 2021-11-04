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
 * @ClassName MailProperties
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/4
 * @Version 1.0
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("fp_mail_properties")
public class MailProperties {
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户ID
     */
    private Integer userId;
    /**
     * 企业ID
     */
    private Integer companyId;
    /**
     * 业务ID
     */
    private Integer businessId;
    /**
     * 标识key
     */
    private String key;
    /**
     * host
     */
    private String host;
    /**
     * 端口
     */
    private String port;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 协议
     */
    private String protocol;
    /**
     * 默认编码
     */
    private String defaultEncoding;
    /**
     * 更新人
     */
    private Integer updator;
    /**
     * 是否开启
     */
    private Integer isEnable;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建日期
     */
    private Date createTime;
    /**
     * 更新日期
     */
    private Date updateTime;
}
