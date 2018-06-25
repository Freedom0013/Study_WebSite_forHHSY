package com.studytree.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.studytree.InitManager;
import com.studytree.commonfile.Constants;
import com.studytree.log.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 项目工具类
 * Title: StudyTreeTools
 * @date 2018/6/20 18:14
 * @author Freedom0013
 */
public class StudyTreeTools {
    private static final String TAG = StudyTreeTools.class.getSimpleName();
    /** 排序比较器 */
    private static MapKeyComparator mapKeyComparator = new MapKeyComparator();

    /**
     * 空参构造函数
     */
    public StudyTreeTools() {
    }

    /**
     * 获取版本号
     * @param _context Context对象
     * @param _package 包名
     * @return versionCode
     */
    public static String getVerName(Context _context, String _package) {
        String verCode = "-1";
        try {
            verCode = _context.getPackageManager().getPackageInfo(_package, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(TAG,"getVerName错误！",e);
        }
        return verCode;
    }

    /**
     * 创建验证字符
     * @param params 参数
     * @return
     */
    public static String createSign(HashMap<String, Object> params) {
        Map<String, Object> resultMap = sortMapByKey(params);
        String originalCode = Constants.MD5_PREFIX;
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            originalCode += entry.getValue();
        }
        return MD5Utils.str2MD5(originalCode);
    }

    /**
     * 使用Map按key进行排序
     * @param map 被排序数
     * @return 排序完成
     */
    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, Object> sortMap = new TreeMap<String, Object>(mapKeyComparator);
        sortMap.putAll(map);
        return sortMap;
    }

    /**
     * 判断网络连接
     * @param con Activity对象
     * @return 结果
     */
    public static boolean isConNetwork(final Activity activity) {
        if (!isConnectInternet(activity)) {
            return true;
        }
        return false;
    }

    /**
     * 判断网络
     * @param inContext Activity对象
     * @return 结果
     */
    public static boolean isConnectInternet(Activity inContext) {
        Context incontext = inContext.getApplicationContext();
        ConnectivityManager conManager = (ConnectivityManager) incontext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isAvailable();
        }
        return false;
    }

    /**
     * 判断网络(空参)
     * @return 结果
     */
    public static boolean isConnectInternet() {
        ConnectivityManager conManager = (ConnectivityManager) InitManager.getInstance().getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isAvailable();
        }
        return false;
    }

    /**
     * 判断当前网络是否为WiFi链接
     * @param context Context对象
     * @return 结果（是wifi：true，否则返回false）
     */
    public static boolean isWifi(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo == null) {
            return false;
        }
        int nType = networkInfo.getType();
        System.out.println("networkInfo.getExtraInfo() is "
                + networkInfo.getExtraInfo());
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            return false;
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    /**
     * dp转px
     * @param dpValue dp值
     * @return px
     */
    public static int dip2px(float dpValue) {
        final float scale = InitManager.getInstance().getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     * @param pxValue px值
     * @return dp值
     */
    public static int px2dip(float pxValue) {
        final float scale = InitManager.getInstance().getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转px（文字大小不变）
     * @param spValue sp值
     * @return px值
     */
    public static int sp2px(float spValue) {
        final float fontScale = InitManager.getInstance().getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
