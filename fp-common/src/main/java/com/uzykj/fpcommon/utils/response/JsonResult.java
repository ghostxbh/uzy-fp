package com.uzykj.fpcommon.utils.response;

/**
 * api返回结果类
 * Create by xbh 2019-09-29
 */
public class JsonResult {
    /* 状态码 */
    private int code;

    /* 提示信息 */
    private String message;

    /* 业务数据 */
    private Object data;


    public JsonResult() {
        this.code = ResCode.SUCCESS;
        this.message = "OK";
    }

    public JsonResult(Object data) {
        this();//调用当前类的构造方法
        this.data = data;
    }

    public JsonResult(Throwable e) {
        this.code = ResCode.ERROR;
        this.message = e.getMessage();
    }

    //设置状态码和提示信息
    public JsonResult(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    //设置状态码和数据
    public JsonResult(int code, Object data) {
        super();
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }


    public JsonResult setCode(int code) {
        this.code = code;
        return this;
    }


    public String getMessage() {
        return message;
    }


    public JsonResult setMessage(String message) {
        this.message = message;
        return this;
    }


    public Object getData() {
        return data;
    }


    public JsonResult setData(Object data) {
        this.data = data;
        return this;
    }


    public static int getSuccess() {
        return ResCode.SUCCESS;
    }

    public static int getFail() {
        return ResCode.FAIL;
    }

    public static int getError() {
        return ResCode.ERROR;
    }


}
