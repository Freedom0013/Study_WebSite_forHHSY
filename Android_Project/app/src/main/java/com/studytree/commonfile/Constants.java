package com.studytree.commonfile;

import com.studytree.BuildConfig;

/**
 * 公共参数类
 * Title: Constants
 * @date 2018/6/13 14:54
 * @author Freedom0013
 */
public class Constants {
    //本地测试开关
    public static final boolean LOCAL_TEST = true;
    /** 本地地址 */
    public static final String HOST;
    /** 云服务器地址 */
    public static final String DOMAIN = "119.27.161.250:8080/Study_Platform_website";

    static {
        if (LOCAL_TEST) {
            HOST = "192.168.0.102:8080/Study_Platform_website";     //本地地址
        } else {
            HOST = "" + DOMAIN;
        }
    }

    /** 总SharedPreferences名称 */
    public static final String PREF_NAME = "studytreePrefs";

    /** MD5加盐 */
    public static final String MD5_PREFIX = "studytree@!123321";
    public static final String MD5KEY = "6938";

    /** 友盟AppKey */
    public static final String UMAppKey = "5b2e06e6f43e4876f400004f";
    /** 友盟ChannelID */
    public static final String UMChannelID = "StudyTree";

}
