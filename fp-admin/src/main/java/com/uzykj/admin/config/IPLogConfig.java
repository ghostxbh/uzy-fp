package com.uzykj.admin.config;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Copyright http://fp.uzykj.com
 * @ClassName IPLogConfig
 * @Description desc
 * @Author ghostxbh
 * @Date 2021/11/5
 * @Version 1.0
 */
public class IPLogConfig extends ClassicConverter {

    private static String ip = "";

    static {
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        return ip;
    }
}