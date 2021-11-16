package com.uzykj.common.core.response.enums;

import com.baomidou.mybatisplus.extension.api.IErrorCode;
import com.uzykj.common.core.response.constant.ResponseConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName BizCode
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/16
 * @Version 1.0
 */
@Getter
@AllArgsConstructor
public enum BizCode implements IErrorCode {
    SUCCESS(ResponseConstant.SUCCESS, ResponseConstant.SUCCESS_MSG),
    ERROR(ResponseConstant.ERROR, ResponseConstant.ERROR_MSG);

    private long code;
    private String msg;

    @Override
    public long getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
