package com.uzykj.fpsso.pojo;

import lombok.Data;

import java.util.Date;
@Data
public class SsoUser {
    private String id;

    private String name;

    private String realName;

    private String password;

    private String mobile;

    private String email;

    private String orgId;

    private String isEnable;

    private String remark;

    private String a1;

    private String a2;

    private String a3;

    private String a4;

    private String creater;

    private Date createTime;

    private String updater;

    private Date updateTime;
}