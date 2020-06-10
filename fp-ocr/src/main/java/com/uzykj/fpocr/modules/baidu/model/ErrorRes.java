package com.uzykj.fpocr.modules.baidu.model;

import lombok.Data;

/**
 * @author ghostxbh
 * @date 2020/6/4
 * @description baidu ocr error model
 */
@Data
public class ErrorRes {
    private String error_msg;
    private String error_code;
}
