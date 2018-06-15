package com.studytree.utils;

import android.Manifest;
import android.os.Build;

/**
 * Android6.0后危险权限封装类
 * Title: Permissions
 * @date 2018/6/15 18:32
 * @author Freedom0013
 */
public final class Permissions {
    /** 日历权限组 */
    public static final String[] CALENDAR;
    /** 摄像头权限组 */
    public static final String[] CAMERA;
    /** 联系人权限组 */
    public static final String[] CONTACTS;
    /** 定位权限组 */
    public static final String[] LOCATION;
    /** 麦克风权限组 */
    public static final String[] MICROPHONE;
    /** 电话权限组 */
    public static final String[] PHONE;
    /** 传感器权限组 */
    public static final String[] SENSORS;
    /** 短信权限组 */
    public static final String[] SMS;
    /** 储存卡权限组 */
    public static final String[] STORAGE;

    public static final int REQUEST_READ_CALENDAR = 1;
    public static final int REQUEST_WRITE_CALENDAR = 2;
    public static final int REQUEST_CAMERA = 3;
    public static final int REQUEST_READ_CONTACTS = 4;
    public static final int REQUEST_WRITE_CONTACTS = 5;
    public static final int REQUEST_GET_ACCOUNTS = 6;
    public static final int REQUEST_ACCESS_FINE_LOCATION = 7;
    public static final int REQUEST_ACCESS_COARSE_LOCATION = 8;
    public static final int REQUEST_RECORD_AUDIO = 9;
    public static final int REQUEST_READ_PHONE_STATE = 10;
    public static final int REQUEST_CALL_PHONE = 11;
    public static final int REQUEST_READ_CALL_LOG = 12;
    public static final int REQUEST_WRITE_CALL_LOG = 13;
    public static final int REQUEST_USE_SIP = 14;
    public static final int REQUEST_PROCESS_OUTGOING_CALLS = 15;
    public static final int REQUEST_BODY_SENSORS = 16;
    public static final int REQUEST_SEND_SMS = 17;
    public static final int REQUEST_RECEIVE_SMS = 18;
    public static final int REQUEST_READ_SMS = 19;
    public static final int REQUEST_RECEIVE_WAP_PUSH = 20;
    public static final int REQUEST_RECEIVE_MMS = 21;
    public static final int REQUEST_READ_EXTERNAL_STORAGE = 22;
    public static final int REQUEST_WRITE_EXTERNAL_STORAGE = 23;
    public static final int REQUEST_EXTERNAL_STORAGE = 24;

    static {
        //如果API大于Android6.0 创建运行时权限组
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            CALENDAR = new String[]{};
            CAMERA = new String[]{};
            CONTACTS = new String[]{};
            LOCATION = new String[]{};
            MICROPHONE = new String[]{};
            PHONE = new String[]{};
            SENSORS = new String[]{};
            SMS = new String[]{};
            STORAGE = new String[]{};
        } else {
            CALENDAR = new String[]{
                    Manifest.permission.READ_CALENDAR,
                    Manifest.permission.WRITE_CALENDAR};

            CAMERA = new String[]{
                    Manifest.permission.CAMERA};

            CONTACTS = new String[]{
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS,
                    Manifest.permission.GET_ACCOUNTS};

            LOCATION = new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION};

            MICROPHONE = new String[]{
                    Manifest.permission.RECORD_AUDIO};

            PHONE = new String[]{
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_CALL_LOG,
                    Manifest.permission.WRITE_CALL_LOG,
                    Manifest.permission.USE_SIP,
                    Manifest.permission.PROCESS_OUTGOING_CALLS};

            SENSORS = new String[]{
                    Manifest.permission.BODY_SENSORS};

            SMS = new String[]{
                    Manifest.permission.SEND_SMS,
                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.RECEIVE_WAP_PUSH,
                    Manifest.permission.RECEIVE_MMS};

            STORAGE = new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE};
        }
    }
}
