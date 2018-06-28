package com.studytree.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;

import com.studytree.InitManager;
import com.studytree.bean.DeviceInfoBean;
import com.studytree.log.Logger;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 设备信息获取类
 * Title: DevicesUtils
 * @date 2018/6/28 16:10
 * @author Freedom0013
 */
public class DevicesUtils {
    private static final String TAG = DevicesUtils.class.getSimpleName();

    /**
     * 获取设备信息
     */
    public static void initDevicesInfos() {
        DeviceInfoBean info = new DeviceInfoBean();
        info.appversion = getAppVersion();
        DisplayMetrics metrics = InitManager.getInstance().getContext().getResources().getDisplayMetrics();
        info.screen = metrics.widthPixels + "_" + metrics.heightPixels;
        try {
            initDeviceNetworkInfos(info);
        } catch (Exception e) {
            Logger.e(TAG, "初始化设备网络信息异常: " + e);
        }
        try {
            TelephonyManager tm = (TelephonyManager) InitManager.getInstance().getContext().getSystemService(Context.TELEPHONY_SERVICE);
            if (tm != null) {
                info.uuid = tm.getDeviceId();
            }
            if (StringUtils.isNullOrEmpty(info.uuid)) {
                if (!StringUtils.isNullOrEmpty(info.mac)) {
                    info.uuid = info.mac;
                }
            }
            if (StringUtils.isNullOrEmpty(info.uuid)) {
                if (!StringUtils.isNullOrEmpty(info.mac)) {
                    info.uuid = android.provider.Settings.Secure.getString(
                            InitManager.getInstance().getContext().getContentResolver(),
                            android.provider.Settings.Secure.ANDROID_ID);
                }
            }
        } catch (Exception e) {
            Logger.e(TAG, "初始化设备信息异常: " + e);
        }
        InitManager.getInstance().devceinfo = info;
        Logger.d(TAG, "==设备信息==" + info.toString());
    }

    /**
     * 缺少权限情况下获取设备信息
     */
    public static void initDevicesInfosWithoutPermission() {
        DeviceInfoBean info = new DeviceInfoBean();
        info.appversion = getAppVersion();
        DisplayMetrics metrics = InitManager.getInstance().getContext().getResources().getDisplayMetrics();
        info.screen = metrics.widthPixels + "_" + metrics.heightPixels;
        InitManager.getInstance().devceinfo = info;
        Logger.d(TAG, info.toString());
    }

    /**
     * 初始化设备网络信息
     * @param info 存储
     */
    private static void initDeviceNetworkInfos(DeviceInfoBean info) {
        Context incontext = InitManager.getInstance().getContext().getApplicationContext();
        ConnectivityManager conManager = (ConnectivityManager) incontext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                info.network = DeviceInfoBean.NETWORK_WIFI; //wifi状态
                info.ip = getWifiIpAddress();   //ip
                info.mac = getMacAddress(); //Mac
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                String _strSubTypeName = networkInfo.getSubtypeName(); //数据流量状态
                Logger.d(TAG, "_strSubTypeName: " + _strSubTypeName);
                int networkType = networkInfo.getSubtype(); //网络制式TD-SCDMA   networkType=17
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:    //Android api<8 : 替换为了 11
                        info.network = DeviceInfoBean.NETWORK_2G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //Android api<9 : 替换为了 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //Android api<11 : 替换为了 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //Android api<13 : 替换为了 15
                        info.network = DeviceInfoBean.NETWORK_3G;
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //Android api<11 : 替换为了 13
                        info.network = DeviceInfoBean.NETWORK_4G;
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA") || _strSubTypeName.equalsIgnoreCase("WCDMA") || _strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                            info.network = DeviceInfoBean.NETWORK_3G;
                        } else {
                            info.network = _strSubTypeName;
                        }
                        break;
                }
                info.ip = getLocalIpAddress();
            }
        }
    }

    /**
     * 获取Wifi下的ip地址
     * @return ip
     */
    private static String getWifiIpAddress() {
        WifiManager wifiManager = (WifiManager) InitManager.getInstance().getContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ipAddress = wifiInfo.getIpAddress();     // 获取32位整型IP地址
        //返回整型地址转换成“*.*.*.*”地址
        return String.format("%d.%d.%d.%d", (ipAddress & 0xff), (ipAddress >> 8 & 0xff), (ipAddress >> 16 & 0xff), (ipAddress >> 24 & 0xff));
    }

    /**
     * 获取Mac地址
     * @return Mac地址
     */
    private static String getMacAddress() {
        WifiManager wifi = (WifiManager) InitManager.getInstance().getContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    /**
     * 获取非wifi下ip地址
     * @return ip
     */
    private static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
            Logger.e(TAG, "获取非WiFi ip地址失败！" + e);
        }
        return null;
    }

    /**
     * 获取App版本
     * @return 版本
     */
    private static String getAppVersion() {
        String appVersion = "";
        PackageManager manager = InitManager.getInstance().getContext().getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(InitManager.getInstance().getContext().getPackageName(), 0);
            appVersion = info.versionName; //版本名
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(TAG,"获取App版本号失败！",e);
            appVersion = "";
        }
        return appVersion;
    }
}
