package com.uzykj.system.enums;

import lombok.Getter;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName MaillEnable
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/5
 * @Version 1.0
 */
@Getter
public enum MaillEnable {
    DISABLE(0),
    ENABLE(1);

    private final Integer code;

    MaillEnable(Integer code) {
        this.code = code;
    }
}
