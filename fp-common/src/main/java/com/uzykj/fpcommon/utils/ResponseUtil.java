package com.uzykj.fpcommon.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yangz
 * @date 2018年2月6日 下午7:24:23
 * @description 响应工具类
 */
public class ResponseUtil {

    private static final String ForbiddenJsonMsg = "{\"errorCode\":"+401+",\"msg\":\"您被禁止登录!\"}";

    private static final String UnAuthorizedJsonMsg = "{\"errorCode\":"+404+",\"msg\":\"您没有访问权限!\"}";

    public static void responseJson(HttpServletResponse resp, String msg) {
        PrintWriter writer = null;
        try {
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json; charset=utf-8");
            resp.setStatus(200);
            writer = resp.getWriter();
            writer.print(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static void responseUnAuthorizedJson(HttpServletResponse resp) {
        responseJson(resp, UnAuthorizedJsonMsg);
    }

    public static void responseForbiddenJson(HttpServletResponse resp) {
        responseJson(resp, ForbiddenJsonMsg);
    }

}
