package com.luospace.blog.util;

import org.springframework.util.DigestUtils;


public class MD5Util {
    private static String sale = "free";

    public static String getMD5(String str){
        String password = str + sale;
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
