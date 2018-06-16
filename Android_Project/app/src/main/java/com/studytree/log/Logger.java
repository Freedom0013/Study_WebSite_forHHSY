package com.studytree.log;

import android.os.Environment;
import android.util.Log;

import com.studytree.BuildConfig;

/**
 * log打印封装类
 * Title: Logger
 * @date 2018/6/14 11:59
 * @author Freedom0013
 */
public final class Logger {
    /** 标签 */
    public static final String TAG = "Logger";
    /** log目录地址 */
    public static final String LOG_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/StudyTree/log/";
    /** log保存地址 */
    public static final String LOG_FILE_PATH = LOG_DIR + "run.log";
    /** 文件数量限制 */
    public static final int FILE_AMOUNT = 5;
    /** log文件大小限制1M */
    public static final long MAXSIZE_PERFILE = 1048576;
    /** log VERBOSE级别 */
    public static final int VERBOSE = 2;
    /** log DEBUG级别 */
    public static final int DEBUG = 3;
    /** log INFO级别 */
    public static final int INFO = 4;
    /** log WARN级别 */
    public static final int WARN = 5;
    /** log ERROR级别 */
    public static final int ERROR = 6;
    /** 当前log级别 */
    private static int currentLevel = VERBOSE;

    //根据BuildConfig标识显示最低log级别
	static {
		if (BuildConfig.DEBUG) {
            currentLevel = VERBOSE;
        } else {
            currentLevel = INFO;
        }
	}

	/** 构造器 */
    private Logger() {
    }

    /**
     * 调用系统日志
     * @param level 日志级别
     * @param tag 日志发生来源标签
     * @param msg 日志显示信息
     * @return
     */
    public static int println(int level, String tag, String msg) {
        int result = 0;

        //判断是否需要输出Log
        if (isLoggable(level)) {
            result = Log.println(level, tag, msg);
        } else {
            return result;
        }

        //判断SD卡是否可用
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Log.w(TAG, "[Study_tree]：：：：：SD卡不可用！");
            return result;
        }

        //启动日志本地化服务
        if (!LogCache.getInstance().isStarted()) {
            startService();
        }

        //本地化
        if (isLoggable(level)) {
            long id = Thread.currentThread().getId();
            String methodName = Thread.currentThread().getStackTrace()[4].getMethodName();
            LogCache.getInstance().write(levelString(level), tag, msg, id, methodName);
        }
        return result;
    }

    /**
     * 判断当前日志级别是否可输出
     * @param level 日志级别
     * @return 是否可输出
     */
    public static boolean isLoggable(int level) {
        return level >= currentLevel;
    }

    /**
     * 启动log日志写入SD卡服务
     */
    public static synchronized void startService() {
        LogCache.getInstance().start();
    }

    /**
     * 停止log日志写入SD卡服务
     */
    public static synchronized void stopService() {
        LogCache.getInstance().stop();
    }

    /**
     * 日志级别转换为字符串
     * @param level 级别编码
     * @return 级别字符串
     */
    private static String levelString(int level) {
        switch (level) {
            case Logger.VERBOSE:
                return "Ver";
            case Logger.DEBUG:
                return "Deb";
            case Logger.INFO:
                return "Inf";
            case Logger.WARN:
                return "War";
            case Logger.ERROR:
                return "Err";
            default:
                return "Deb";
        }
    }
    
    /**
     * 发送一个VERBOSE级别的日志请求
     * @param tag 日志发生来源标签
     * @param msg 日志信息
     * @return 写入结果
     */
    public static int v(String tag, String msg) {
        return println(VERBOSE, tag, "[Study_tree]：：：：：[VERBOSE]" + " " + msg);
    }
    
    /**
     * 发送一个VERBOSE级别的Exception日志请求
     * @param tag 日志发生来源标签
     * @param msg 日志信息
     * @param tr 异常信息
     * @return 写入结果
     */
    public static int v(String tag, String msg, Throwable tr) {
        return println(VERBOSE, tag, "[Study_tree]：：：：：[VERBOSE]" + " " + msg + " " + getStackTraceString(tr));
    }

    /**
     * 发送一个DEBUG级别的日志请求
     * @param tag 日志发生来源标签
     * @param msg 日志信息
     * @return 写入结果
     */
    public static int d(String tag, String msg) {
        return println(DEBUG, tag, "[Study_tree]：：：：：[Debug]" + msg);
    }

    /**
     * 发送一个Debug级别的Exception日志请求
     * @param tag 日志发生来源标签
     * @param msg 日志信息
     * @param tr 异常信息
     * @return 写入结果
     */
    public static int d(String tag, String msg, Throwable tr) {
        return println(DEBUG, tag, "[Study_tree]：：：：：[Debug]" + msg + " " + getStackTraceString(tr));
    }

    /**
     * 发送一个INFO级别的日志请求
     * @param tag 日志发生来源标签
     * @param msg 日志信息
     * @return 写入结果
     */
    public static int i(String tag, String msg) {
        return println(INFO, tag, "[Study_tree]：：：：：[Info]" + " " + msg);
    }

    /**
     * 发送一个INFO级别的Exception日志请求
     * @param tag 日志发生来源标签
     * @param msg 日志信息
     * @param tr 异常信息
     * @return 写入结果
     */
    public static int i(String tag, String msg, Throwable tr) {
        return println(INFO, tag, "[Study_tree]：：：：：[Info]" + " " + msg + " " + getStackTraceString(tr));
    }

    /**
     * 发送一个WARN级别的日志请求
     * @param tag 日志发生来源标签
     * @param msg 日志信息
     * @return 写入结果
     */
    public static int w(String tag, String msg) {
        return println(WARN, tag, "[Study_tree]：：：：：[Warning]" + " " + msg);
    }

    /**
     * 发送一个WARN级别的Exception日志请求
     * @param tag 日志发生来源标签
     * @param msg 日志信息
     * @param tr 异常信息
     * @return 写入结果
     */
    public static int w(String tag, String msg, Throwable tr) {
        return println(WARN, tag, "[Study_tree]：：：：：[Warning]" + " " + msg + " " + getStackTraceString(tr));
    }

    /**
     * 发送一个WARN级别的日志请求
     * @param tag 日志发生来源标签
     * @return 写入结果
     */
    public static int w(String tag, Throwable tr) {
        return println(WARN, tag, "[Study_tree]：：：：：[Warning]" + " " + getStackTraceString(tr));
    }

    /**
     * 发送一个Error级别的日志请求
     * @param tag 日志发生来源标签
     * @param msg 日志信息
     * @return 写入结果
     */
    public static int e(String tag, String msg) {
        return println(ERROR, tag, "[Study_tree]：：：：：[ERROR]" + " " + msg);
    }

    /**
     * 发送一个Error级别的Exception日志请求
     * @param tag 日志发生来源标签
     * @param msg 日志信息
     * @param tr 异常信息
     * @return 写入结果
     */
    public static int e(String tag, String msg, Throwable tr) {
        return println(ERROR, tag, "[Study_tree]：：：：：[ERROR]" + " " + msg + " " + getStackTraceString(tr));
    }

    /**
     * 获取异常信息
     * @param tr 异常信息log
     * @return 结果
     */
    public static String getStackTraceString(Throwable tr) {
        return Log.getStackTraceString(tr);
    }
}
