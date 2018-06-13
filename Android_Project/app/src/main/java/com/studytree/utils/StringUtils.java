package com.studytree.utils;

/**
 * 字符串操作工具类
 * Title: StringUtils
 * @date 2018/6/13 23:04
 * @author Freedom0013
 */
public class StringUtils {
    /**
     * 判断字符串是否为空
     * @param string 字符串
     * @return 结果
     */
    public static boolean isNullOrEmpty(final String string) {
        return string == null || string.trim().length() == 0;
    }
}
