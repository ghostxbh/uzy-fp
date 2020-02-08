package com.uzykj.fpcommon.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author ghostxbh
 * @date 2020/2/8
 * @description AES加密工具
 */
public class AESUtil {

    private static String encodeSalt;

    private static final Logger logger = LoggerFactory.getLogger(AESUtil.class);

    private static byte[] encode0(String encodeRules, String content)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException,
            IllegalBlockSizeException, BadPaddingException {
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(encodeRules.getBytes());
        keygen.init(128, random);
        SecretKey original_key = keygen.generateKey();
        byte[] raw = original_key.getEncoded();
        SecretKey key = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] byte_encode = content.getBytes("utf-8");
        byte[] byte_AES = cipher.doFinal(byte_encode);
        return byte_AES;
    }

    private static Cipher decode0(String encodeRules)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(encodeRules.getBytes());
        keygen.init(128, random);
        SecretKey original_key = keygen.generateKey();
        byte[] raw = original_key.getEncoded();
        SecretKey key = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher;
    }

    public static String encodeToHex(String encodeSalt, String content) throws Exception {
        byte[] encode0 = encode0(encodeSalt, content);
        return byte2hex(encode0);
    }

    public static String encodeToHex(String content) throws Exception {
        byte[] encode0 = encode0(encodeSalt, content);
        return byte2hex(encode0);
    }

    public static String decodeFromHex(String encodeSalt, String content) throws Exception {
        Cipher cipher = decode0(encodeSalt);
        byte[] byte_content = hex2byte(content);
        byte[] byte_decode = cipher.doFinal(byte_content);
        return new String(byte_decode, "UTF-8");
    }

    public static String decodeFromHex(String content) throws Exception {
        Cipher cipher = decode0(encodeSalt);
        byte[] byte_content = hex2byte(content);
        byte[] byte_decode = cipher.doFinal(byte_content);
        return new String(byte_decode, "UTF-8");
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

    private static byte[] hex2byte(String hex) throws IllegalArgumentException {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        int i = 0;
        int j = 0;
        for (int l = hex.length(); i < l; ++j) {
            String swap = new StringBuilder().append("").append(arr[(i++)]).append(arr[i]).toString();
            int byteint = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = new Integer(byteint).byteValue();

            ++i;
        }
        return b;
    }

    public static void setEncodeSalt(String encodeSalt) {
        if (AESUtil.encodeSalt == null) {
            AESUtil.encodeSalt = encodeSalt;
            logger.info("加密盐值设置完毕!");
        } else {
            logger.error("加密盐值已经存在,请勿重复设置!");
        }
    }

    public static void main(String[] args) throws Exception {
//        setEncodeSalt("zxc@gmail.com");
        String xubenhao = encodeToHex("zxcc", "zxccc");
        System.out.println(xubenhao);
        String s = decodeFromHex("zxcc", "BEF6B1239A6C72FC9C66B1F720AFB507");
        System.out.println(s);
    }

}
