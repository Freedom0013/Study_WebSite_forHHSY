package com.studytree.log;

import android.os.Environment;
import android.util.Log;

import com.studytree.BuildConfig;
/**
 * [一句话功能简述]日志工具
 * [功能详细描述]
 *
 */
public final class Logger
{

    /**
     * log tag
     */
    public static final String TAG = "Logger";
    
    /**
     * log dir
     */
    public static final String LOG_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Yikao/log/";
    
    /**
     * log path
     */
//    public static final String LOG_FILE_PATH = LOG_DIR + "rcs.log";
    public static final String LOG_FILE_PATH = LOG_DIR + "run.log";
    
    /**
     * file amount limitation
     */
    public static final int FILE_AMOUNT = 5;
    
    /**
     * file size limitation per log file
     */
    public static final long MAXSIZE_PERFILE = 1048576;
    
    /**
     * Priority constant for the println method; use Log.v.
     */
    public static final int VERBOSE = 2;
    
    /**
     * Priority constant for the println method; use Log.d.
     */
    public static final int DEBUG = 3;
    
    /**
     * Priority constant for the println method; use Log.i.
     */
    public static final int INFO = 4;
    
    /**
     * Priority constant for the println method; use Log.w.
     */
    public static final int WARN = 5;
    
    /**
     * Priority constant for the println method; use Log.e.
     */
    public static final int ERROR = 6;
    
    /**
     * current log level
     */
    private static int currentLevel = VERBOSE;
	static {
		if (BuildConfig.DEBUG) {
            currentLevel = VERBOSE;
        }else {
            currentLevel = WARN;
        }
	}
	
    private Logger()
    {
    }
    
    /**
     * Low-level logging call.
     * 
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the
     * log call occurs.
     * @param msg The message you would like logged.
     * @param level level
     * @return The number of bytes written.
     */
    public static int println(int level, String tag, String msg)
    {
        int result = 0;
        if (isLoggable(level))
        {
            result = Log.println(level, tag, msg);
        }
        else
        {
            return result;
        }
        
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
        	Log.w(TAG, "SD Card is unavailable.");
            return result;
        }
        
        if (!LogCache.getInstance().isStarted())
        {
            startService();
        }
        
        if (isLoggable(level))
        {
            long id = Thread.currentThread().getId();
            String methodName = Thread.currentThread().getStackTrace()[4].getMethodName();
            LogCache.getInstance().write(levelString(level), tag, msg, id, methodName);
        }
        
        return result;
    }
    
    private static String levelString(int level)
    {
        switch (level)
        {
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
     * Send a {@link #VERBOSE} log message.
     * 
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the
     * log call occurs.
     * @param msg The message you would like logged.
     * @return The number of bytes written. 
     */
    public static int v(String tag, String msg)
    {
        return println(VERBOSE, tag, "[VERBOSE]" + " " + msg);
    }
    
    /**
     * Send a {@link #VERBOSE} log message and log the exception.
     * 
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the
     * log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     * @return The number of bytes written.  
     */
    public static int v(String tag, String msg, Throwable tr)
    {
        return println(VERBOSE, tag, "[VERBOSE]" + " " + msg + " " + getStackTraceString(tr));
    }
    
    /**
     * Send a {@link #DEBUG} log message.
     * 
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the
     * log call occurs.
     * @param msg The message you would like logged.
     * @return The number of bytes written. 
     */
    public static int d(String tag, String msg)
    {
        return println(DEBUG, tag, "[Debug]" + msg);
    }
    
    /**
     * Send a {@link #DEBUG} log message and log the exception.
     * 
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the
     * log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     * @return The number of bytes written. 
     */
    public static int d(String tag, String msg, Throwable tr)
    {
        return println(DEBUG, tag, "[Debug]"+ msg + " " + getStackTraceString(tr));
    }
    
    /**
     * Send an {@link #INFO} log message.
     * 
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the
     * log call occurs.
     * @param msg The message you would like logged.
     * @return The number of bytes written. 
     */
    public static int i(String tag, String msg)
    {
        return println(INFO, tag, "[Info]" + " " + msg);
    }
    
    /**
     * Send a {@link #INFO} log message and log the exception.
     * 
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the
     * log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     * @return The number of bytes written. 
     */
    public static int i(String tag, String msg, Throwable tr)
    {
        return println(INFO, tag, "[Info]" + " " + msg + " " + getStackTraceString(tr));
    }
    
    /**
     * Send a {@link #WARN} log message.
     * 
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the
     * log call occurs.
     * @param msg The message you would like logged.
     * @return The number of bytes written. 
     */
    public static int w(String tag, String msg)
    {
        return println(WARN, tag, "[Warning]" + " " + msg);
    }
    
    /**
     * Send a {@link #WARN} log message and log the exception.
     * 
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the
     * log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     * @return The number of bytes written. 
     */
    public static int w(String tag, String msg, Throwable tr)
    {
        return println(WARN, tag, "[Warning]" + " " + msg + " " + getStackTraceString(tr));
    }
    
    /**
     * 
     * Send a {@link #WARN} log message and log the exception.<BR>
     * [功能详细描述]
     * @param tag  Used to identify the source of a log message. It usually
     * identifies the class or activity where the log call occurs.
     * @param tr An exception to log
     * @return The number of bytes written. 
     */
    public static int w(String tag, Throwable tr)
    {
        return println(WARN, tag, "[Warning]" + " " + getStackTraceString(tr));
    }
    
    /**
     * Send an {@link #ERROR} log message.
     * 
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the
     * log call occurs.
     * @param msg The message you would like logged.
     * @return The number of bytes written. 
     */
    public static int e(String tag, String msg)
    {
        return println(ERROR, tag, "[ERROR]" + " " + msg);
    }
    
    /**
     * Send a {@link #ERROR} log message and log the exception.
     * 
     * @param tag Used to identify the source of a log message. It usually identifies the class or activity where the
     * log call occurs.
     * @param msg The message you would like logged.
     * @param tr An exception to log
     * @return The number of bytes written. 
     */
    public static int e(String tag, String msg, Throwable tr)
    {
        return println(ERROR, tag, "[ERROR]" + " " + msg + " " + getStackTraceString(tr));
    }
    
    /**
     * Handy function to get a loggable stack trace from a Throwable
     * 
     * @param tr An exception to log
     * @return The number of bytes written. 
     */
    public static String getStackTraceString(Throwable tr)
    {
        return Log.getStackTraceString(tr);
    }
    
    /**
     * 
     * Checks to see whether or not a log for the specified tag is loggable at the specified level.<BR>
     * [功能详细描述]
     * @param level level
     * @return The number of bytes written.
     */
    public static boolean isLoggable(int level)
    {
        return level >= currentLevel;
    }
    
    /**
     * 
     * try to start the service of logging into sdcard.
     */
    public static synchronized void startService()
    {
        LogCache.getInstance().start();
    }
    
    /**
     * 
     *stop the service of logging into sdcard.
     */
    public static synchronized void stopService()
    {
        LogCache.getInstance().stop();
    }
    

}
