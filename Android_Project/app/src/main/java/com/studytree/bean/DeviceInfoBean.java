package com.studytree.bean;

import android.os.Build;

/**
 * 设备信息Bean
 * Title: DeviceInfoBean
 * @date 2018/6/28 16:13
 * @author Freedom0013
 */
public class DeviceInfoBean {
    /** WiFi网络 */
    public static final String NETWORK_WIFI = "wifi";
    /** 4g网络 */
    public static final String NETWORK_4G = "4g";
    /** 3g网络 */
    public static final String NETWORK_3G = "3g";
    /** 2g网络 */
    public static final String NETWORK_2G = "2g";
    /** 平台 */
    public static final String device = "android";
    /** 系统版本 */
    public static final String osversion = Build.VERSION.SDK_INT+"";
    /** 设备名称 */
    public static final String model = Build.MODEL;
    /** mac地址 */
    public String mac = "";
    /** ip地址 */
    public String ip = "";
    /** 应用版本名 */
    public String appversion = "";
    /** 分辨率 */
    public String screen = "";
    /** 网络类型 */
    public String network = "";
    /** 设备唯一码 */
    public String uuid = "";

    @Override
    public String toString() {
        return "DeviceInfoBean [mac=" + mac + ", ip=" + ip + ", appversion=" + appversion + ", screen=" + screen
                + ", network=" + network + ", uuid=" + uuid + ", model=" + model  + ", osversion=" + osversion
                +"]";
    }
}
