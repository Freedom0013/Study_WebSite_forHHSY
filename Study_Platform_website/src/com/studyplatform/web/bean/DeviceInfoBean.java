package com.studyplatform.web.bean;

/**
 * 设备信息Bean
 * Title: DeviceInfoBean
 * @date 2018/6/28 16:13
 * @author Freedom0013
 */
public class DeviceInfoBean {
    /** WiFi网络 */
    public static final String NETWORK_WIFI = "";
    /** 4g网络 */
    public static final String NETWORK_4G = "";
    /** 3g网络 */
    public static final String NETWORK_3G = "";
    /** 2g网络 */
    public static final String NETWORK_2G = "";
    /** 平台 */
    public static final String device = "";
    /** 系统版本 */
    public static final String osversion = "";
    /** 设备名称 */
    public static final String model = "";
    /** mac地址 */
    private String mac = "";
    /** ip地址 */
    private String ip = "";
    /** 应用版本 */
    private String appversion = "";
    /** 分辨率 */
    private String screen = "";
    /** 网络类型 */
    private String network = "";
    /** 设备唯一码 */
    private String uuid = "";
    
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAppversion() {
        return appversion;
    }

    public void setAppversion(String appversion) {
        this.appversion = appversion;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public static String getOsversion() {
        return osversion;
    }

    @Override
    public String toString() {
        return "DeviceInfoBean [mac=" + mac + ", ip=" + ip + ", appversion=" + appversion + ", screen=" + screen
                + ", network=" + network + ", uuid=" + uuid + "]";
    }
}
