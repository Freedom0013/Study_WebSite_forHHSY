package com.studytree.utils.permissions;

import android.Manifest;
import android.os.Build;

/**
 * Android6.0后危险权限封装类
 * Title: PermissionConfig
 * @date 2018/6/15 18:32
 * @author Freedom0013
 */
public final class PermissionConfig {
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
    /** 读取日历 */
    public static final String PERMISSION_READ_CALENDAR = Manifest.permission.READ_CALENDAR;
    /** 写日历 */
    public static final String PERMISSION_WRITE_CALENDAR = Manifest.permission.WRITE_CALENDAR;
    /** 摄像头权限 */
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    /** 读取联系人 */
    public static final String PERMISSION_READ_CONTACTS = Manifest.permission.READ_CONTACTS;
    /** 修改联系人*/
    public static final String PERMISSION_WRITE_CONTACTS = Manifest.permission.WRITE_CONTACTS;
    /** 获取联系人*/
    public static final String PERMISSION_GET_ACCOUNTS = Manifest.permission.GET_ACCOUNTS;
    /** 获取精确位置*/
    public static final String PERMISSION_ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    /** 获取粗略位置信息*/
    public static final String PERMISSION_ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    /** 录制麦克风音频*/
    public static final String PERMISSION_RECORD_AUDIO = Manifest.permission.RECORD_AUDIO;
    /** 读取电话状态*/
    public static final String PERMISSION_READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    /** 拨打电话*/
    public static final String PERMISSION_CALL_PHONE = Manifest.permission.CALL_PHONE;
    /** 读通话记录*/
    public static final String PERMISSION_READ_CALL_LOG = Manifest.permission.READ_CALL_LOG;
    /** 修改通话记录*/
    public static final String PERMISSION_WRITE_CALL_LOG = Manifest.permission.WRITE_CALL_LOG;
    /** 添加语音邮箱*/
    public static final String PERMISSION_ADD_VOICEMAIL = Manifest.permission.ADD_VOICEMAIL;
    /** 使用SIP视频电话服务*/
    public static final String PERMISSION_USE_SIP = Manifest.permission.USE_SIP;
    /** 监视，修改或放弃播出电话*/
    public static final String PERMISSION_PROCESS_OUTGOING_CALLS = Manifest.permission.PROCESS_OUTGOING_CALLS;
    /** 访问用户用来测量他/她身体内发生的事情的传感器的数据，例如心率*/
    public static final String PERMISSION_BODY_SENSORS = Manifest.permission.BODY_SENSORS;
    /** 发送短信*/
    public static final String PERMISSION_SEND_SMS = Manifest.permission.SEND_SMS;
    /** 程序接收短信*/
    public static final String PERMISSION_RECEIVE_SMS = Manifest.permission.RECEIVE_SMS;
    /** 读取短信*/
    public static final String PERMISSION_READ_SMS = Manifest.permission.READ_SMS;
    /** 接收WAP PUSH信息*/
    public static final String PERMISSION_RECEIVE_WAP_PUSH = Manifest.permission.RECEIVE_WAP_PUSH;
    /** 接收彩信*/
    public static final String PERMISSION_RECEIVE_MMS = Manifest.permission.RECEIVE_MMS;
    /** 程序可以读取设备外部存储空间（内置SDcard和外置SDCard）的文件响应码，如果您的App已经添加了“WRITE_EXTERNAL_STORAGE ”权限 ，则就没必要添加读的权限了，写权限已经包含了读权限了。 */
    public static final String PERMISSION_READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE;
    /** 允许程序写入外部存储,如SD卡上写文件*/
    public static final String PERMISSION_WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    /** 读取日历*/
    public static final int REQUEST_READ_CALENDAR = 1;
    /** 写日历响应码 */
    public static final int REQUEST_WRITE_CALENDAR = 2;
    /** 摄像头权限响应码 */
    public static final int REQUEST_CAMERA = 3;
    /** 读取联系人响应码 */
    public static final int REQUEST_READ_CONTACTS = 4;
    /** 修改联系人响应码 */
    public static final int REQUEST_WRITE_CONTACTS = 5;
    /** 获取联系人响应码 */
    public static final int REQUEST_GET_ACCOUNTS = 6;
    /** 获取精确位置响应码 */
    public static final int REQUEST_ACCESS_FINE_LOCATION = 7;
    /** 获取粗略位置信息响应码 */
    public static final int REQUEST_ACCESS_COARSE_LOCATION = 8;
    /** 录制麦克风音频响应码 */
    public static final int REQUEST_RECORD_AUDIO = 9;
    /** 读取电话状态响应码 */
    public static final int REQUEST_READ_PHONE_STATE = 10;
    /** 拨打电话响应码 */
    public static final int REQUEST_CALL_PHONE = 11;
    /** 读通话记录响应码 */
    public static final int REQUEST_READ_CALL_LOG = 12;
    /** 修改通话记录响应码 */
    public static final int REQUEST_WRITE_CALL_LOG = 13;
    /** 添加语音邮箱响应码 */
    public static final int REQUEST_ADD_VOICEMAIL = 14;
    /** 使用SIP视频电话服务响应码 */
    public static final int REQUEST_USE_SIP = 15;
    /** 监视，修改或放弃播出电话响应码 */
    public static final int REQUEST_PROCESS_OUTGOING_CALLS = 16;
    /** 访问用户用来测量他/她身体内发生的事情的传感器的数据，例如心率响应码 */
    public static final int REQUEST_BODY_SENSORS = 17;
    /** 发送短信响应码 */
    public static final int REQUEST_SEND_SMS = 18;
    /** 程序接收短信响应码 */
    public static final int REQUEST_RECEIVE_SMS = 19;
    /** 读取短信响应码 */
    public static final int REQUEST_READ_SMS = 20;
    /** 接收WAP PUSH信息响应码 */
    public static final int REQUEST_RECEIVE_WAP_PUSH = 21;
    /** 接收彩信响应码 */
    public static final int REQUEST_RECEIVE_MMS = 22;
    /** 程序可以读取设备外部存储空间（内置SDcard和外置SDCard）的文件响应码，如果您的App已经添加了“WRITE_EXTERNAL_STORAGE ”权限 ，则就没必要添加读的权限了，写权限已经包含了读权限了。 */
    public static final int REQUEST_READ_EXTERNAL_STORAGE = 23;
    /** 允许程序写入外部存储,如SD卡上写文件响应码 */
    public static final int REQUEST_WRITE_EXTERNAL_STORAGE = 24;
    /** 请求日历权限组响应码 */
    public static final int REQUEST_CALENDAR = 25;
    /** 请求摄像头权限组响应码 */
    public static final int REQUEST_CAMERA_GROUP = 26;
    /** 请求联系人权限组响应码 */
    public static final int REQUEST_CONTACTS = 27;
    /** 请求定位权限组响应码 */
    public static final int REQUEST_LOCATION = 28;
    /** 请求麦克风权限组响应码 */
    public static final int REQUEST_MICROPHONE = 29;
    /** 请求电话权限组响应码 */
    public static final int REQUEST_PHONE = 30;
    /** 请求传感器权限组响应码 */
    public static final int REQUEST_SENSORS = 31;
    /** 请求短信权限组响应码 */
    public static final int REQUEST_SMS = 32;
    /** 请求储存卡权限组响应码 */
    public static final int REQUEST_STORAGE = 33;
    /** 权限组响应码 */
    public static final int REQUEST_SOME_PERMISSIONS = 34;


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
                    Manifest.permission.ADD_VOICEMAIL,
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

    /**
     * 获取权限响应码对用中文
     * @param Request_Code 响应码
     * @return 中文字符串
     */
    public static String getPermissionToString(final int Request_Code){
        switch (Request_Code){
            case REQUEST_READ_CALENDAR:
                return "读取日历";
            case REQUEST_WRITE_CALENDAR:
                return "修改日历";
            case REQUEST_CAMERA:
                return "使用摄像头";
            case REQUEST_READ_CONTACTS:
                return "读取联系人";
            case REQUEST_WRITE_CONTACTS:
                return "修改联系人";
            case REQUEST_GET_ACCOUNTS:
                return "获取联系人";
            case REQUEST_ACCESS_FINE_LOCATION:
                return "获取精确位置";
            case REQUEST_ACCESS_COARSE_LOCATION:
                return "获取粗略位置信息";
            case REQUEST_RECORD_AUDIO:
                return "录制麦克风音频";
            case REQUEST_READ_PHONE_STATE:
                return "读取手机信息";
            case REQUEST_CALL_PHONE:
                return "拨打电话";
            case REQUEST_READ_CALL_LOG:
                return "读取通话记录";
            case REQUEST_WRITE_CALL_LOG:
                return "修改通话记录";
            case REQUEST_ADD_VOICEMAIL:
                return "添加语音邮箱";
            case REQUEST_USE_SIP:
                return "使用SIP视频电话服务";
            case REQUEST_PROCESS_OUTGOING_CALLS:
                return "监视，修改或放弃播出电话";
            case REQUEST_BODY_SENSORS:
                return "测量身体传感器";
            case REQUEST_SEND_SMS:
                return "发送短信";
            case REQUEST_RECEIVE_SMS:
                return "接收短信";
            case REQUEST_READ_SMS:
                return "读取短信";
            case REQUEST_RECEIVE_WAP_PUSH:
                return "接收WAP PUSH信息";
            case REQUEST_RECEIVE_MMS:
                return "读取日历";
            case REQUEST_READ_EXTERNAL_STORAGE:
                return "读取设备外部存储空间（内置SDcard和外置SDCard）";
            case REQUEST_WRITE_EXTERNAL_STORAGE:
                return "写入外部存储空间";
            case REQUEST_SOME_PERMISSIONS:
                return "权限组";
            default:
                return null;
        }
    }

    /**
     * 获取权限全称获取对用中文
     * @param permission_name 权限全称
     * @return 中文字符串
     */
    public static String getPermissionNameToString(final String permission_name){
        switch (permission_name){
            case PERMISSION_READ_CALENDAR:
                return "读取日历";
            case PERMISSION_WRITE_CALENDAR:
                return "修改日历";
            case PERMISSION_CAMERA:
                return "使用摄像头";
            case PERMISSION_READ_CONTACTS:
                return "读取联系人";
            case PERMISSION_WRITE_CONTACTS:
                return "修改联系人";
            case PERMISSION_GET_ACCOUNTS:
                return "获取联系人";
            case PERMISSION_ACCESS_FINE_LOCATION:
                return "获取精确位置";
            case PERMISSION_ACCESS_COARSE_LOCATION:
                return "获取粗略位置信息";
            case PERMISSION_RECORD_AUDIO:
                return "录制麦克风音频";
            case PERMISSION_READ_PHONE_STATE:
                return "读取手机信息";
            case PERMISSION_CALL_PHONE:
                return "拨打电话";
            case PERMISSION_READ_CALL_LOG:
                return "读取通话记录";
            case PERMISSION_WRITE_CALL_LOG:
                return "修改通话记录";
            case PERMISSION_ADD_VOICEMAIL:
                return "添加语音邮箱";
            case PERMISSION_USE_SIP:
                return "使用SIP视频电话服务";
            case PERMISSION_PROCESS_OUTGOING_CALLS:
                return "监视，修改或放弃播出电话";
            case PERMISSION_BODY_SENSORS:
                return "测量身体传感器";
            case PERMISSION_SEND_SMS:
                return "发送短信";
            case PERMISSION_RECEIVE_SMS:
                return "接收短信";
            case PERMISSION_READ_SMS:
                return "读取短信";
            case PERMISSION_RECEIVE_WAP_PUSH:
                return "接收WAP PUSH信息";
            case PERMISSION_RECEIVE_MMS:
                return "读取日历";
            case PERMISSION_READ_EXTERNAL_STORAGE:
                return "读取设备存储（内置SDcard和外置SDCard）";
            case PERMISSION_WRITE_EXTERNAL_STORAGE:
                return "读取写入存储";
            default:
                return null;
        }
    }
}
