package com.studytree.commonfile;

import android.os.Environment;

import com.studytree.R;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 公共参数类
 * Title: Constants
 * @date 2018/6/13 14:54
 * @author Freedom0013
 */
public class Constants {
    /** 本地测试标识 */
    public static final boolean LOCAL_TEST = true;
    /** 本地地址 */
    public static final String HOST;
    /** 云服务器地址 */
    public static final String DOMAIN = "119.27.161.250:8080/Study_Platform_website";
    /** Log打头显示 */
    public static final String LOG_HEAD_TEXT = "[Study_tree]：：：：";
    /** OKHttp日志等级（共四级：NONE、BASIC、HEADERS、BODY） */
    public static final HttpLoggingInterceptor.Level OKHTTP_LOG_LEVEL = HttpLoggingInterceptor.Level.BASIC;

    static {
        if (LOCAL_TEST) {
            HOST = "192.168.0.103:8080/Study_Platform_website";     //本地地址
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

    /** 日志文件存储路径 */
    public static final String LOG_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/StudyTree/log/";
    /** 图片文件缓存 */
    public static final String IMAGE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/StudyTree/cache_images/";
    /** 默认ImageView图片 */
    public static final int default_image = R.drawable.ic_default_image;
}
