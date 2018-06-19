package com.studytree;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

import com.studytree.log.Logger;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义应用异常处理类
 * Title: ExceptionHandler
 * @date 2018/6/19 15:35
 * @author Freedom0013
 */
public class ExceptionHandler implements Thread.UncaughtExceptionHandler{
    private static final String TAG = ExceptionHandler.class.getSimpleName();
    /** 系统默认异常处理类 */
    private Thread.UncaughtExceptionHandler mDefaultHandler = null;
    /** 存储异常发生的设备参数信息 */
    private Map<String, String> paramsMap = new HashMap<>();
    /** ExceptionHandler单例 */
    private static ExceptionHandler __instance = null;
    /** context对象 */
    private Context mContext;

    /**
     * 空参构造函数
     */
    private ExceptionHandler(){}

    /**
     * 初始化应用异常处理类
     * @param context context对象
     */
    public void init(Context context){
        this.mContext = context;
        //将异常处理类设置为系统默认的异常处理类，当出现异常时，有该类处理
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 获取ExceptionHandler单例
     * @return ExceptionHandler对象
     */
    public static ExceptionHandler getInstance(){
        if(__instance == null){
            synchronized (ExceptionHandler.class){
                if(__instance == null){
                    __instance = new ExceptionHandler();
                }
            }
        }
        return __instance;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable exception) {
        //收集设备参数信息
        if(mContext != null){
            collectDeviceInfo(mContext);
        }
        Logger.e(TAG, "===========知识森驿站警告！！！============CrashHandler uncaughtException================开始===================");
        Logger.w(TAG, "CrashHandler uncaughtException"+exception.toString());
        exception.printStackTrace();
        Logger.w(TAG,exception);
        Logger.w(TAG,"-----------设备信息开始-----------");
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            Logger.w(TAG,key + "=" + value);
        }
        Logger.w(TAG,"-----------设备信息结束-----------");
        Logger.e(TAG, "===========知识森驿站警告！！！============CrashHandler uncaughtException================结束===================");
        //退出程序
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    /**
     * 收集设备参数信息
     * @param context Context对象
     */
    public void collectDeviceInfo(Context context) {
        //获取versionName,versionCode
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                paramsMap.put("versionName", versionName);
                paramsMap.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Logger.e(TAG, "收集包信息出错！", e);
        }
        //获取所有系统信息
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                paramsMap.put(field.getName(), field.get(null).toString());
            } catch (Exception e) {
                Logger.e(TAG, "收集系统信息出错！", e);
            }
        }
    }
}
