package com.uzykj.fpcommon.utils.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ghostxbh
 * @date 2020/2/8
 * @description MD5加密工具
 */
public class Md5 {
    public static String md5(String code) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        byte[] digest = md.digest(code.getBytes());
        return byte2hex(digest);
    }

    private static String byte2hex(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < b.length; ++n) {
            String stmp = Integer.toHexString(b[n] & 0xFF);
            if (stmp.length() == 1) {
                sb.append("0");
            }
            sb.append(stmp);
        }
        return sb.toString().toUpperCase();
    }
}
