package com.uzykj.system.constant;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName MailConstant
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/4
 * @Version 1.0
 */
public class MailConstant {
    /**
     * 发送状态
     */
    public static final Integer MAIL_SEND_STATUS_TOBE = 0;
    public static final Integer MAIL_SEND_STATUS_SUCCESS = 1;
    public static final Integer MAIL_SEND_STATUS_FAIL = -1;

    /**
     * 发件类型
     */
    public static final Integer MAIL_SEND_TYPE_BATCH = 1;
    public static final Integer MAIL_SEND_TYPE_SINGLE = 2;
}
