package com.studytree.log;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.studytree.utils.MemoryStatus;
import com.studytree.utils.StringUtils;

import android.annotation.SuppressLint;
import android.util.Log;

/**
 * 日志本地化缓存
 * Title: LogCache
 * @date 2018/6/14 16:54
 * @author Freedom0013
 */
public class LogCache {
    /** 日志标记 */
    public static final String TAG = LogCache.class.getSimpleName();
    /**
     * 日志数量限制
     * 原因：存储卡剩余空间大于50M，规格要求日志总占用不超过4M，考虑rcs.log的打印，最多缓存3个文件
     */
    public static final int FILE_AMOUNT = 3;
    /** 日志文件大小限制 */
    public static final long MAXSIZE_PERFILE = 1048576;
    /** 时间格式 */
    @SuppressLint("SimpleDateFormat")
    private DateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS.sss");
    /** SD卡可存储的最小空间要求：50M */
    private static final long MIX_SIZE = 5242880;
    /** LogCache单例 */
    private static final LogCache INSTANCE = new LogCache();
    /**  */
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
    /** LogCache启动标识 */
    private volatile boolean started;
    /**  */
    private volatile Thread logWorkerThread;
    /** 日志操作类 */
    private LogWriter logWriter = null;
    /**  */
    private int counter = 0;

    /** 空参构造器 */
    private LogCache() {
        this(Logger.LOG_FILE_PATH, FILE_AMOUNT, MAXSIZE_PERFILE);
    }

    /**
     * 必要构造函数
     * @param filePath 日志文件存放目录
     */
    private LogCache(String filePath) {
        this(filePath, 0, 0);
    }

    /**
     * 全参数构造函数
     * @param filePath 日志文件存放目录
     * @param fileAmount 文件数量限制
     * @param maxSize 最大大小
     */
    private LogCache(String filePath, int fileAmount, long maxSize) {
        this.logWriter = new LogWriter(new File(filePath), fileAmount, maxSize);
    }

    /** 获取LogCache单例 */
    static LogCache getInstance() {
        return INSTANCE;
    }

    /**
     * 把信息放入同步队列
     * @param msg 信息
     */
    public void write(String msg) {
        if (started) {
            try {
                queue.put(msg);
            } catch (InterruptedException e) {
                Logger.e("LogCache","",e);
            }
        }
    }

    /**
     * 构造即将存放的信息
     * @param level 日志级别
     * @param tag 日志来源标签
     * @param msg 日志信息
     * @param id 线程/进程id
     * @param methodName 方法名
     */
    public void write(String level, String tag, String msg, long id, String methodName) {
        StringBuilder sbr = new StringBuilder();
        //时间
        sbr.append('[');
        sbr = addCurrentTime(sbr);
        sbr.append(']');
        //进程和线程
        sbr.append('[');
        sbr.append("P:").append(android.os.Process.myPid()).append('/').append("T:").append(id);
        sbr.append(']');
        //函数名，所在文件和行数
        sbr.append('[');
        sbr.append(tag).append(' ').append(methodName);
        sbr.append(']');
        //日志级别
        sbr.append('[');
        sbr.append(level);
        sbr.append(']');
        //消息非空的时候输入
        if (!StringUtils.isNullOrEmpty(msg)) {
            sbr.append('\n').append(msg);
        }
        write(sbr.toString());
    }

    /**
     * 写入开始时间的日志
     */
    public void writeBegin() {
        StringBuilder sbr = new StringBuilder();
        //输入起始内容
        sbr.append("Begin Time:");
        sbr = addCurrentTime(sbr);
        write(sbr.toString());
    }

    /**
     * 添加时间
     * @param sbr StringBuilder待添加
     * @return 添加过后的StringBuilder
     */
    public StringBuilder addCurrentTime(StringBuilder sbr) {
        if (null == sbr) {
            return null;
        }
        //添加时间,格式:YYYY-MM-DD HH-MM-SS.mmm
        sbr.append(TIME_FORMAT.format(System.currentTimeMillis()));
        return sbr;
    }

    /**
     * 判断是否可写入存储
     * 1.需要判断剩余空间是否符合要求；规格要求：
     * 2.无存储卡，不记录日志
     * 3.存储卡剩余空间《 50M，不记录日志。
     * 4.存储卡剩余空间>=50M，日志文件总占用空间不超过4M。
     * @param text 文本
     * @return 结果
     */
    public boolean isExternalMemoryAvailable(String text) {
        return MemoryStatus.isExternalMemoryAvailable(text.getBytes().length + MIX_SIZE);
    }

    /**
     * [一句话功能简述]<BR>
     * [功能详细描述]
     *
     * @return CacheSize
     */
    public synchronized long getCacheSize() {
        long size = 0;
        for (String text : queue) {
            size += text.getBytes().length;
        }
        return size;
    }

    public boolean isStarted() {
        return started;
    }

    public boolean isLogThreadNull() {
        return null == logWorkerThread;
    }

    /**
     * 启动日志本地化服务
     */
    public synchronized void start() {
        if (null == logWorkerThread) {
            logWorkerThread = new Thread(new LogTask(),
                    "Log Worker Thread - " + counter);
        }
        if (started || !logWriter.initialize()) {
            return;
        }
        started = true;
        logWorkerThread.start();
    }

    /**
     * [一句话功能简述]<BR>
     * [功能详细描述]
     */
    public synchronized void stop() {
        Log.v("LogCache",
                "Log Cache instance is stopping...");
        started = false;
        queue.clear();
        logWriter.close();
        if (null != logWorkerThread) {
            logWorkerThread.interrupt();
            logWorkerThread = null;
        }
        Log.v("LogCache",
                "Log Cache instance is stopped");
    }

    /**
     * [一句话功能简述]<BR>
     * [功能详细描述]
     *
     * @author 盛兴亚
     * @version [RCS Client V100R001C03, 2012-2-15]
     */
    private final class LogTask implements Runnable {

        public LogTask() {
            counter++;
        }

        private void dealMsg() throws InterruptedException {
            String msg = null;
            while (started && !Thread.currentThread().isInterrupted()) {
                msg = queue.take();
                synchronized (logWriter) {
                    if (isExternalMemoryAvailable(msg)) {
                        // if current file is deleted, rebuild it
                        if (!logWriter.isCurrentExist()) {
                            Log.v(TAG,
                                    "current is initialing...");
                            if (!logWriter.initialize()) {
                                continue;
                            }
                        }
                        // if current log file reaches size limitation, log into
                        // next log file
                        else if (!logWriter.isCurrentAvailable()) {
                            Log.v(TAG,
                                    "current is rotating...");
                            if (!logWriter.rotate()) {
                                continue;
                            }
                        }
                        logWriter.println(msg);
                    } else if (logWriter.clearSpace()) {
                        if (!logWriter.rotate()) {
                            continue;
                        }
                        logWriter.println(msg);
                    } else {
                        Log.e(TAG,
                                "can't log into sdcard.");
                    }
                }
            }
        }

        public void run() {
            try {
                dealMsg();
            } catch (InterruptedException e) {
                Log.e(TAG,
                        Thread.currentThread().toString(),
                        e);
            } catch (RuntimeException e) {
                Log.e(TAG,
                        Thread.currentThread().toString(),
                        e);
                logWorkerThread = new Thread(new LogTask(),"Log Worker Thread - " + counter);
                started = false;
            } finally {
                Log.v(TAG, "Log Worker Thread is terminated.");
            }
        }

    }

}

