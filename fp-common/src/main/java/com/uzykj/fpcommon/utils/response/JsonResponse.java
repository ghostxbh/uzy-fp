package com.uzykj.fpcommon.utils.response;

/**
 * @author ghostxbh
 * @date 2020/5/27
 * @description json数据返回
 */
public class JsonResponse<T> {
    private Integer code;
    private String message;
    private T data;

    public JsonResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public JsonResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public JsonResponse() {
        super();
    }

    public Integer getCode() {
        return code;
    }

    public JsonResponse setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public JsonResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public JsonResponse setData(T data) {
        this.data = data;
        return this;
    }

    public JsonResponse<T> success(T data) {
        this.data = data;
        this.code = 200;
        this.message = "ok";
        return this;
    }

    public JsonResponse fail() {
        this.code = 500;
        this.message = "fail";
        return this;
    }
}
