package com.zufang.renthouse.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密工具（无依赖，课设专用）
 */
public class Md5Util {
    private static final String SALT = "zufang_rent_2025"; // 自定义盐值

    public static String encrypt(String password) {
        if (password == null || password.isEmpty()) {
            return null;
        }
        String encryptStr = password + SALT;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(encryptStr.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    sb.append('0');
                }
                sb.append(hex);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}