package com.uzykj.fpsso.enums;

import lombok.Getter;

/**
 * @author ghostxbh
 * @date 2020/5/27
 * @description
 */
@Getter
public enum SsoEnum {
    SUCCESS(200, "ok"),

    INVALID_PARAM(400, "无效参数"),
    ESSENT_PARAM(401, "不存在必填参数"),

    WARONG_PASSWORD(410, "密码错误"),
    USER_EXISTED(411, "用户已存在"),
    USER_NOT_FOUND(412, "用户不存在"),
    USER_NOT_ENABLE(413, "用户未启用"),

    INVALID_TOKEN(420, "无效token"),

    FAILED(500, "接口处理错误");

    private Integer code;
    private String msg;

    SsoEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
