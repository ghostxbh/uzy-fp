package com.uzykj.system.enums;

import lombok.Getter;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName MaillSendStatus
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/4
 * @Version 1.0
 */
@Getter
public enum MailSendStatus {
    TOBE(0),
    SUCCESS(1),
    FAIL(-1);

    private final Integer code;

    MailSendStatus(Integer code) {
        this.code = code;
    }
}
