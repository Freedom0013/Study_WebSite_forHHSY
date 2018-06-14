package com.studytree.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;


/**
 * 日志文件工具
 * Title: LogFileUtil
 * @date 2018/6/14 16:57
 * @author Freedom0013
 */
public class LogFileUtil {
    /** 标签 */
    private static final String TAG = "FileUtil";

    /** 构造函数 */
    private LogFileUtil() {
    }

    /**
     * 删除文件
     * @param file 文件
     * @return 结果
     */
    public static boolean forceDeleteFile(File file) {
        boolean result = false;
        int tryCount = 0;
        while (!result && tryCount++ < 10) {
            result = file.delete();
            if (!result) {
                try {
                    synchronized (file) {
                        file.wait(200);
                    }
                } catch (InterruptedException e) {
                    Log.e("FileUtil.forceDeleteFile", "", e);
                }
            }
        }
        Log.v("FileUtil.forceDeleteFile", "tryCount = " + tryCount);
        return result;
    }

    /**
     * 从/data/data/package/filename读取信息
     * @param context 上下文
     * @param file 文件
     * @return 数据
     */
    public static String read(Context context, String file) {
        String data = "";
        try {
            FileInputStream stream = context.openFileInput(file);
            StringBuffer sb = new StringBuffer();
            int c;
            while ((c = stream.read()) != -1) {
                sb.append((char) c);
            }
            stream.close();
            data = sb.toString();

        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
        return data;
    }

    /**
     * 向/data/data/package/filename地址写入文件
     * @param context 上下文
     * @param file 文件
     * @param msg 写入信息
     */
    @SuppressLint("WorldWriteableFiles")
    public static void write(Context context, String file, String msg) {
        try {
            @SuppressWarnings("deprecation")
            FileOutputStream stream = context.openFileOutput(file, Context.MODE_WORLD_WRITEABLE);
            stream.write(msg.getBytes());
            stream.flush();
            stream.close();
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }
}

