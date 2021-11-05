package com.uzykj.common.core.response;

import com.baomidou.mybatisplus.extension.api.R;
import com.uzykj.common.core.response.constant.ResponseConstant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import static com.uzykj.common.core.response.constant.ResponseConstant.*;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName Rep
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/5
 * @Version 1.0
 */
public class Rep extends R {
    public static R success() {
        return R.ok(null);
    }

    public static R error() {
        return R.failed(ERROR_MSG);
    }
}
