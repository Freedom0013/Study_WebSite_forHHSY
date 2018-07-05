package com.studytree.log;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * 日志操作类
 * Title: LogWriter
 * @date 2018/6/14 12:06
 * @author Freedom0013
 */
public class LogWriter {
    /** LogWriter标签 */
    public static final String TAG = LogWriter.class.getSimpleName();
    /** log时间格式 */
    private DateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS.sss");
    /** log日志存放文件封装 */
    private File current;
    /** 日志文件数量限制 */
    private int fileAmount = 2;
    /** 日志文件大小限制 */
    private long maxSize = 1048576;
    /** 已存在在SD卡日志集合 */
    private ArrayList<File> historyLogs = null;
    /** 时间 */
    private DateFormat timestampOfName = new SimpleDateFormat("yyyyMMddHHmmss");
    /** 日志打印 */
    private PrintWriter writer = null;

    private final Comparator<File> c = new Comparator<File>() {
        public int compare(File f1, File f2) {
            return String.CASE_INSENSITIVE_ORDER.compare(f1.getName(),
                    f2.getName());
        }
    };

    /**
     * 全参构造函数
     * @param current 文件地址
     * @param fileAmount 文件数量限制
     * @param maxSize 文件最大大小
     */
    public LogWriter(File current, int fileAmount, long maxSize) {
        this.current = current;
        this.fileAmount = fileAmount <= 0 ? this.fileAmount : fileAmount;
        this.maxSize = (maxSize <= 0) ? this.maxSize : maxSize;
        initialize();
    }

    /**
     * 初始化操作类
     * @return 结果
     */
    public synchronized boolean initialize() {
        try {
            if (!current.getParentFile().exists()) {
                current.getParentFile().mkdirs();
            } else if (null == historyLogs) {
                File[] fs = current.getParentFile()
                        .listFiles(new FilenameFilter() {
                            public boolean accept(File dir, String filename) {
                                final String curName = LogWriter.this.current.getName();
                                String patt = curName.replace(curName.substring(curName.lastIndexOf(".")),"_");
                                return filename.contains(patt);
                            }
                        });
                if (fs != null && fs.length != 0) {
                    historyLogs = new ArrayList<File>(Arrays.asList(fs));
                } else {
                    historyLogs = new ArrayList<File>();
                }
            }
            writer = new PrintWriter(new FileOutputStream(current,
                    current.exists() && isCurrentAvailable()), true);
            //打印开始日志
            printBegin();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /** 获取最近日志 */
    private File getTheEarliest() {
        Collections.sort(historyLogs, c);
        return historyLogs.get(0);
    }
    
    /**
     * 日志超过最大容量后，处理日志文件,生成一个新的；超过文件数目需要删除
     * @return 结果
     */
    public boolean rotate() {
        File des = new File(newName());
        if (historyLogs.size() >= fileAmount - 1) {
            boolean deleteResult = LogFileUtil.forceDeleteFile(getTheEarliest());
            if (deleteResult) {
                Log.i(TAG, "旧日志文件: " + historyLogs);
                Log.i(TAG, "删除 " + historyLogs.get(0).getName() + "成功！");
                historyLogs.remove(0);
            } else {
                Log.i(TAG, "删除 " + historyLogs.get(0).getName() + "失败！");
                return false;
            }
        }
        try {
            close();
            boolean result = current.renameTo(des);
            if (!result || !initialize()) {
                Log.v(TAG, "重命名或初始化错误！");
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, "", e);
            return false;
        }
        historyLogs.add(des);
        Log.i(TAG, "新的日志路径为: " + historyLogs);
        return true;
    }
    
    /**
     * 判断正在打日志的问价是否存在
     * @return 结果
     */
    public boolean isCurrentExist() {
        return current.exists();
    }
    
    /**
     * 消息放入之前预算是否会超过最大容量
     * @param msg 消息
     * @return 结果
     */
    public boolean isCurrentAvailable(String msg) {
        return msg.getBytes().length + current.length() < maxSize;
    }
    
    /**
     * 文件未达到最大容量
     * @return 文件是否未达到最大容量
     */
    public boolean isCurrentAvailable() {
        return current.length() < maxSize;
    }

    /**
     * 新生成的日志名,按照最新的规格：日志名称：YYYYMMDDHHMMSS.log
     * @return newName 新文件名
     */
    public String newName() {
        String name = current.getAbsolutePath();
        int dox = name.lastIndexOf('.');
        String suffix = name.substring(dox);
        return timestampOfName.format(System.currentTimeMillis()) + suffix;
    }
    
    /**
     * 删除历史日志
     * @return 结果true代表成功
     */
    private boolean deleteTheEarliest() {
        return (historyLogs.size() != 0) && getTheEarliest().delete();
    }

    /**
     * 删除所有log文件
     * @return 结果
     */
    @SuppressWarnings("unused")
    private boolean deleteAllOthers() {
        for (File file : historyLogs) {
            if (!file.delete()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 写入消息
     * @param msg 消息
     */
    public void println(String msg) {
        if (null == writer) {
            initialize();
        } else {
            writer.println(msg);
        }
    }
    
    /**
     * 打开始日志
     */
    private void printBegin() {
        StringBuilder sbr = new StringBuilder();
        //输入起始内容
        sbr.append("Begin Time:");
        sbr = addCurrentTime(sbr);
        println(sbr.toString());
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
     * 复制
     * @param des 文件
     * @throws IOException
     */
    public void copyTo(File des) throws IOException {
        @SuppressWarnings("resource")
        FileChannel fi = new FileInputStream(current).getChannel();
        @SuppressWarnings("resource")
        FileChannel fo = new FileOutputStream(des, false).getChannel();
        ByteBuffer bf = ByteBuffer.allocateDirect(1024);
        while (fi.read(bf) != -1) {
            bf.flip();
            fo.write(bf);
            bf.clear();
        }
        fi.close();
        fo.close();
    }

    /**
     * 获取指定日志文件
     * @param logFile 日志文件
     * @return 内容
     * @throws IOException
     */
    public String getTextInfo(File logFile) throws IOException {
        BufferedReader bReader = null;
        StringBuilder sbr = new StringBuilder();
        String line;
        bReader = new BufferedReader(new InputStreamReader(new FileInputStream(logFile)));
        while (null != (line = bReader.readLine())) {
            sbr.append(line).append("\n");
        }
        bReader.close();
        return sbr.toString();
    }

    /**
     * 清空目录
     * @return 结果
     */
    public boolean clearSpace() {
        return deleteTheEarliest();
    }

    /**
     * 关流
     */
    public synchronized void close() {
        if (null != writer) {
            writer.close();
        }
    }
}
