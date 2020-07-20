package com.ml.testsecurity.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密算法类，命名是按照MD5命名的，类中可能包含所有关于算法的静态类
 * @author MaLei
 * @description: MD5Utils
 * @create 2020/7/14
 */
public class MD5Utils {
    /**
     * MD5加密方法
     * @param password 要加密的字符串
     * @return 加密后字符串
     * 如果程序返回原输入字符串则意味着程序加密出错了
     */
    public static String getMD5(String password) {
        MessageDigest md;
        try {
            // 生成一个MD5加密计算摘要
            md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(password.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            String pwd = new BigInteger(1, md.digest()).toString(16);
            // System.err.println(pwd);
            return pwd.toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return password;
    }
}
