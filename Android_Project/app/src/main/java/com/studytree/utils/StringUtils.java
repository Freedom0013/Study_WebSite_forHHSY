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
        return string == null || string.trim().length() == 0 || string.equalsIgnoreCase("null");
    }

    /**
     * 获取文件后缀
     * @param filename 文件名
     * @return 后缀名
     */
    public static String getFileSuffix(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }
}
