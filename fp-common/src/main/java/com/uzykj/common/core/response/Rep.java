package com.uzykj.common.core.response;

import com.baomidou.mybatisplus.extension.api.R;
import com.uzykj.common.core.response.enums.BizCode;
import lombok.NoArgsConstructor;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName Rep
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/5
 * @Version 1.0
 */
@NoArgsConstructor
public class Rep extends R {
    public static R success() {
        return new R(BizCode.SUCCESS);
    }

    public static <T> R<T> success(T data) {
        return R.restResult(data, BizCode.SUCCESS);
    }

    public static R error() {
        return new R(BizCode.ERROR);
    }

    public static R error(Exception e) {
        R r = new R();
        r.setCode(BizCode.ERROR.getCode());
        r.setMsg(e.getMessage());
        return r;
    }
}
