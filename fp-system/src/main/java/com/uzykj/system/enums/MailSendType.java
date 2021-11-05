package com.uzykj.system.enums;

import lombok.Getter;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName MaillSendType
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/4
 * @Version 1.0
 */
@Getter
public enum MailSendType {
    BATCH(1),
    SINGLE(2);

    private final Integer code;

    MailSendType(Integer code) {
        this.code = code;
    }
}
