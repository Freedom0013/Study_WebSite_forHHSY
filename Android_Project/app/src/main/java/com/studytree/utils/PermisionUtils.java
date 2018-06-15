package com.studytree.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * 权限工具类
 * Title: PermisionUtils
 * @date 2018/6/15 17:58
 * @author Freedom0013
 */
public class PermisionUtils {

    /**
     * 检查是否拥有日历权限组
     * @param activity
     */
    public static void verifyCalendarPermissions(Activity activity) {
        /*
         * 判断权限是否授予
         * 拥有权限返回PackageManager.PERMISSION_GRANTED
         * 没有权限返回PackageManager.PERMISSION_DENIED
         */
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            //请求权限，用户同意或者拒绝后会通过方法onRequestPermissionsResult()返回结果
            ActivityCompat.requestPermissions(activity, Permissions.CALENDAR, Permissions.REQUEST_EXTERNAL_STORAGE);
        }
    }

    public static void verifyCameraPermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, Permissions.CAMERA, Permissions.REQUEST_EXTERNAL_STORAGE);
        }
    }
    public static void verifySContactsPermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, Permissions.CONTACTS, Permissions.REQUEST_EXTERNAL_STORAGE);
        }
    }
    public static void verifyLocationPermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, Permissions.LOCATION, Permissions.REQUEST_EXTERNAL_STORAGE);
        }
    }
    public static void verifyMicrophonePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, Permissions.MICROPHONE, Permissions.REQUEST_EXTERNAL_STORAGE);
        }
    }
    public static void verifyPhonePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, Permissions.PHONE, Permissions.REQUEST_EXTERNAL_STORAGE);
        }
    }
    public static void verifySensorsPermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, Permissions.SENSORS, Permissions.REQUEST_EXTERNAL_STORAGE);
        }
    }
    public static void verifySmsPermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, Permissions.SMS,Permissions.REQUEST_EXTERNAL_STORAGE);
        }
    }

    /**
     * 检查是否拥有写SD卡权限组，如果没有回请求用户授予
     * @param activity Activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, Permissions.STORAGE, Permissions.REQUEST_EXTERNAL_STORAGE);
        }
    }
}
