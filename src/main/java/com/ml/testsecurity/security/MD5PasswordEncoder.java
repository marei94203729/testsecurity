package com.ml.testsecurity.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author MaLei
 * @description: MD5加密
 * @create 2020/7/14
 */
public class MD5PasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return MD5Utils.getMD5(charSequence.toString());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return !StringUtils.isNotEmpty(s)?false:StringUtils.equals(MD5Utils.getMD5(charSequence.toString()),s);
    }
}
