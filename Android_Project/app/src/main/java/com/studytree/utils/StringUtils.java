package com.studytree.utils;

import android.text.Html;
import android.widget.TextView;

import com.studytree.log.Logger;

import java.util.Collection;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具类
 * Title: StringUtils
 * @date 2018/6/13 23:04
 * @author Freedom0013
 */
public class StringUtils {
    private static final String TAG = StringUtils.class.getSimpleName();
    /**
     * 正则：手机号（精确）[需要保持更新]
     * 移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188、198
     * 联通：130、131、132、145、155、156、175、176、185、186、166
     * 电信：133、153、173、177、180、181、189、199
     * 全球星：1349
     * 虚拟运营商：170
     */
    public static final String MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$";

    /**
     * 验证手机号（精确）
     * @param phone 待验证文本
     * @return 结果
     */
    public static boolean isMobile(CharSequence phone) {
        return isMatch(MOBILE_EXACT, phone);
    }

    /**
     * 判断字符串是否为空
     * @param string 字符串
     * @return 结果
     */
    public static boolean isNullOrEmpty(final String string) {
        return string == null || string.trim().length() == 0 || string.equalsIgnoreCase("null");
    }

    /**
     * 匹配正则
     * @param regex 正则表达式
     * @param input 字符串
     * @return 结果
     */
    private static boolean isMatch(String regex, CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
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

    /**
     * 判断两字符串内容是否相同
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 结果
     */
    public static boolean equals(String str1, String str2) {
        return str1 == str2 || str1 != null && str1.equals(str2);
    }

    /**
     * 判断字符串1是否包含字符串2
     * @param str1 字符串1
     * @param str2 字符串2
     * @return 结果
     */
    public static boolean contains(String str1, String str2) {
        return str1 != null && str1.contains(str2);
    }

    /**
     * 判断字符串是否为空，如果为空则返回空值否则返回str
     * @param str 被测字符串
     * @return 结果
     */
    public static String getString(String str) {
        return str == null ? "" : str;
    }

    /**
     * 从字符串中过滤所有Html标签
     * @param inputString 输入字符串
     * @return 输出字符串
     */
    public static String filterHtmlTag(String inputString) {
        String htmlStr = inputString;
        String textStr = "";
        Pattern pScript;
        Matcher mScript;
        Pattern pStyle;
        Matcher mStyle;
        Pattern pHtml;
        Matcher mHtml;
        try {
            // script正则表达式
            String regExScript = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?/[\\s]*?script[\\s]*?>";
            // style正则表达式
            String regExStyle = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?/[\\s]*?style[\\s]*?>";
            // Html匹配正则表达式
            String regExHtml = "<[^>\"]+>";
            pScript = Pattern.compile(regExScript, Pattern.CASE_INSENSITIVE);
            mScript = pScript.matcher(htmlStr);
            // 过滤script标签
            htmlStr = mScript.replaceAll("");
            pStyle = Pattern.compile(regExStyle, Pattern.CASE_INSENSITIVE);
            mStyle = pStyle.matcher(htmlStr);
            // 过滤style标签
            htmlStr = mStyle.replaceAll("");
            pHtml = Pattern.compile(regExHtml, Pattern.CASE_INSENSITIVE);
            mHtml = pHtml.matcher(htmlStr);
            // 过滤HTML标签
            htmlStr = mHtml.replaceAll("");
            textStr = htmlStr;
        } catch (Exception e) {
            Logger.e(TAG, "filterHtmlTag错误！", e);
        }
        return textStr;
    }

    /**
     * 将字符串数组合并为整体字符串，默认以逗号分隔
     * @param values 字符串数组
     * @param split 自定义分隔符
     * @return 合并后串
     */
    public static String arrayToString(String[] values, String split) {
        StringBuffer buffer = new StringBuffer();
        if (values != null) {
            if (split == null) {
                split = ",";
            }
            int len = values.length;
            for (int i = 0; i < len; i++) {
                buffer.append(values[i]);
                if (i != len - 1) {
                    buffer.append(split);
                }
            }
        }
        return buffer.toString();
    }

    /**
     * 字符串集合合并为整体字符串
     * @param strList 字符串集合
     * @param split 分隔符
     * @return 合并后字符串
     */
    public static String listToString(Collection<String> strList, String split) {
        String[] values = null;
        if (strList != null) {
            values = new String[strList.size()];
            strList.toArray(values);
        }
        return arrayToString(values, split);
    }

    /**
     * 验证字符串是否为邮箱
     * @param email 被验证字符串
     * @return 结果
     */
    public static boolean isEmail(String email) {
        if (email == null) {
            return false;
        }
        //正则匹配
        if (email.toLowerCase().matches("^([a-zA-Z0-9_\\.-])+@(([a-zA-Z0-9-])+\\.)+([a-zA-Z0-9]{2,4})+$")) {
            return true;
        }
        return false;
    }

    /**
     * 去除Url多余斜杠
     * @param url 地址
     * @return 去斜杠结果
     */
    public static String fixUrl(String url) {
        if (null == url) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer(url);
        for (int i = stringBuffer.indexOf("//", stringBuffer.indexOf("//") + 2); i != -1; i = stringBuffer.indexOf("//",i + 1)) {
            stringBuffer.deleteCharAt(i);
        }
        return stringBuffer.toString();
    }

    /**
     * 判断字符串中是否有中文
     * @param str 被判断字符串
     * @return 结果
     */
    public static boolean hasChinese(String str) {
        boolean hasChinese = false;
        if (str != null) {
            for (char c : str.toCharArray()) {
                if (isChinese(c)) {
                    hasChinese = true;
                    break;
                }
            }
        }
        return hasChinese;
    }

    /**
     * 截取字符串，超过部分用...结束
     * @param originStr 原字符串
     * @param maxCharLength 最大长度
     * @return 结果
     */
    public static String mytrim(String originStr, int maxCharLength) {
        int count = 0;
        int index = 0;
        int originLen = originStr.length();
        for (index = 0; index < originLen; index++) {
            char c = originStr.charAt(index);
            int len = 1;
            if (isChinese(c)) {
                len++;
            }
            if (count + len <= maxCharLength) {
                count += len;
            } else {
                break;
            }
        }

        if (index < originLen) {
            return originStr.substring(0, index) + "...";
        } else {
            return originStr;
        }
    }

    /**
     * 检查密码强度
     * @param password 密码
     * @return 结果（1.低  2.中  3.高）
     */
    public static int checkStrong(String password) {
        boolean num = false;
        boolean lowerCase = false;
        boolean upperCase = false;
        boolean other = false;

        int threeMode = 0;
        int fourMode = 0;
        int size = 0;
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < password.length(); i++) {
            if (password.codePointAt(i) >= 48 && password.codePointAt(i) <= 57) { // 单个字符是数字
                num = true;
            } else if (password.codePointAt(i) >= 97 && password.codePointAt(i) <= 122) { // 单个字符是小写
                lowerCase = true;
            } else if (password.codePointAt(i) >= 65 && password.codePointAt(i) <= 90) { // 单个字符是大写
                upperCase = true;
            } else { // 特殊字符
                other = true;
                set.add(password.codePointAt(i));
            }
        }
        size = set.size();  // 获取特殊字符个数，一个特殊字符算一种来计算密码强度
        set.clear();
        set = null;
        if (num) {
            threeMode++;
            fourMode++;
        }
        if (lowerCase) {
            threeMode++;
            fourMode++;
        }
        if (upperCase) {
            threeMode++;
            fourMode++;
        }
        if (other) {
            fourMode = fourMode + size;
        }
        // 数字、大写字母、小写字母只有一个，密码强度低（只有一种特殊字符也是密码强度低）
        if (threeMode == 1 && !other || fourMode == 1) {
            return 1;
        } else if (fourMode == 2) { // 四种格式有其中两个，密码强度中
            return 2;
        } else if (fourMode >= 3) { // 四种格式有三个或者四个，密码强度高
            return 3;
        } else { // 正常情况下不会出现这种情况
            return 0;
        }
    }

    /**
     * 去除字符串前后空格
     * @param string 原串
     * @return 去空格结果
     */
    public static String stringTrimAllSpace(String string) {
        if (isNullOrEmpty(string)) {
            return "";
        }
        string = string.trim();
        while (string.startsWith(" ")) {
            string = string.substring(1, string.length());
        }
        return string;
    }

    /**
     * 根据毫秒值获取时间字符串
     * @param milli 毫秒
     * @return 时间字符串
     */
    public static String getTimeDesc(long milli) {
        long secondRange = 1000;
        long minuteRange = 60 * secondRange;
        long hourRange = 60 * minuteRange;
        long dayRange = 24 * hourRange;
        long monthRange = 30 * dayRange;
        long yearRange = 12 * monthRange;
        if (milli >= yearRange) {
            return (milli / yearRange) + "年";
        } else if (milli >= monthRange) {
            return (milli / monthRange) + "个月";
        } else if (milli >= dayRange) {
            return (milli / dayRange) + "天";
        } else if (milli >= hourRange) {
            return (milli / hourRange) + "小时";
        } else if (milli >= minuteRange) {
            return (milli / minuteRange) + "分钟";
        } else if (milli >= secondRange) {
            return (milli / secondRange) + "秒";
        } else {
            return "1秒";
        }
    }


    /**
     * 判断字符是否是中文字符
     * @param c 被判断字符
     * @return 结果
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;

    }

    /**
     * 去除字符串中Html代码
     * @param content 文本
     * @param label 设置TextView
     */
    public static void setTextOrHtml(String content, TextView label) {
        if (isNullOrEmpty(content)) {
            label.setText("");
            return;
        }
        if (content.contains("</") || content.contains("<br>")) {
            try {
                CharSequence cs = Html.fromHtml(content);
                if (cs == null || isNullOrEmpty(cs.toString().trim())) {
                    label.setText(content);
                } else {
                    label.setText(Html.fromHtml(content));
                }
            } catch (Exception e) {
                label.setText(content);
            }
        } else {
            label.setText(content);
        }
    }
}
