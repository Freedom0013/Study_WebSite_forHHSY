package com.studytree.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.studytree.InitManager;
import com.studytree.commonfile.Constants;

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
    /** 排序比较器 */
    private static MapKeyComparator mapKeyComparator = new MapKeyComparator();

    /**
     * 空参构造函数
     */
    public StudyTreeTools() {
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
}
