package com.august.website.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5辅助类
 **/
public class Md5Util {
    public static final String md5 = "md5";

    public static String md5(String input) {
        return md5(input, Charset.defaultCharset());
    }

    public static String md5(String input, String charset) {
        return md5(input, Charset.forName(charset));
    }

    private static String md5(String input, Charset charset) {
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance(md5);
        } catch (NoSuchAlgorithmException var9) {
            var9.printStackTrace();
        }

        md.update(input.getBytes());
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        byte[] tmp = md.digest();
        char[] str = new char[32];
        int k = 0;

        for (int i = 0; i < 16; ++i) {
            byte byte0 = tmp[i];
            str[k++] = hexDigits[byte0 >>> 4 & 15];
            str[k++] = hexDigits[byte0 & 15];
        }

        String result = new String(str);
        return result;
    }
}
