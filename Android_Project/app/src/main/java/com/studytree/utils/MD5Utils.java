package com.studytree.utils;

import com.studytree.log.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Md5工具类
 * Title: MD5Utils
 * @date 2018/6/20 18:14
 * @author Freedom0013
 */
public class MD5Utils {
    public  static final String TAG = MD5Utils.class.getSimpleName();
    /** 十六进制字符 */
    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 字符串转Md5
     * @param inStr 需要加密的字符串
     * @return 密文
     */
    public final static String str2MD5(String inStr) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] strTemp = inStr.getBytes("UTF-8");
            //如果输入“SHA”，就是实现SHA加密
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            Logger.e(TAG,"====MD5错误====",e);
            return null;
        }
    }

    /**
     * 字符数组转十六进制字符串
     * @param b 字符数组
     * @return 转换后字符串
     */
    public static String toHexString(byte[] b) { // String to byte
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    /**
     * Md5加密（冗余）
     * @param s 被加密字符串
     * @return 密文
     */
    public static String AndroidMd5(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            return toHexString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            Logger.e(TAG,"====MD5错误====",e);
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 32位MD5加密
     * @param str 被加密字符串
     * @return 密文
     */
    public static String MD5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            Logger.e(TAG,"====MD5错误====",e);
            e.printStackTrace();
            return "";
        }
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String[] args) {
        String m = MD5Utils.str2MD5("你好啊好啊哦吼");
        System.out.print(m.length() + "    ");
        System.out.println(m);
    }
}
