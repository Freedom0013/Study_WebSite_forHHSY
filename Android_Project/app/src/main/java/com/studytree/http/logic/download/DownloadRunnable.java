package com.studytree.http.logic.download;


import com.studytree.commonfile.Constants;
import com.studytree.http.HttpTransaction;
import com.studytree.log.Logger;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.Response;

/**
 * 下载线程
 * Title: DownloadRunnable
 * @date 2018/7/5 21:24
 * @author Freedom0013
 */
public class DownloadRunnable implements Runnable {
    private static final String TAG = DownloadRunnable.class.getSimpleName();
    /** 下载进行中状态码 */
    private static final int STATUS_DOWNLOADING = 1;
    /** 停止下载状态码 */
    private static final int STATUS_STOP = 2;
    /** 线程状态码 */
    private int mStatus = STATUS_DOWNLOADING;
    /** 文件下载地址 */
    private String url;
    /** 下载文件名称 */
    private String name;
    /** 线程id */
    private int threadId;
    /** 每个线程下载开始的位置 */
    private long start;
    /** 每个线程下载结束的位置 */
    private long end;
    /** 每个线程的下载进度 */
    private long mProgress;
    /** 文件的总大小 content-length */
    private long mCurrentLength;
    /** 下载回调 */
    private DownloadCallback downloadCallback;

    /**
     * 下载线程构造函数
     * @param name 下载文件名
     * @param url 下载地址
     * @param currentLength 文件大小
     * @param threadId 线程id
     * @param start 线程下载开始的位置
     * @param end 线程下载结束的位置
     * @param downloadCallback 下载回调
     */
    public DownloadRunnable(String name, String url, long currentLength, int threadId, long start, long end, DownloadCallback downloadCallback) {
        this.name = name;
        this.url = url;
        this.mCurrentLength = currentLength;
        this.threadId = threadId;
        this.start = start;
        this.end = end;
        this.downloadCallback = downloadCallback;
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        RandomAccessFile randomAccessFile = null;
        try {
            Response response = HttpTransaction.getInstance().syncResponse(url, start, end);
            Logger.d(TAG, "文件名=" + name + " 下载文件大小contentLength=" + response.body().contentLength());
            Logger.d(TAG, "线程id=" + threadId + "：：开始位置start=" + start + "结束位置end=" + end);
            inputStream = response.body().byteStream();
            //保存文件的路径
            File file = new File(Constants.DOWNLOAD_DIR, name);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            //创建占位文件
            randomAccessFile = new RandomAccessFile(file, "rwd");
            //seek从哪里开始
            randomAccessFile.seek(start);
            int length;
            byte[] bytes = new byte[10 * 1024];
            while ((length = inputStream.read(bytes)) != -1) {
                if (mStatus == STATUS_STOP) {
                    downloadCallback.onPause(length, mCurrentLength);
                    break;
                }
                //写入
                randomAccessFile.write(bytes, 0, length);
                //保存下进度，做断点
                mProgress = mProgress + length;
                //实时去更新下进度条，将每次写入的length传出去
                downloadCallback.onProgress(length, mCurrentLength);
            }
            downloadCallback.onSuccess(file);
        } catch (FileNotFoundException e) {
            Logger.e(TAG,"文件下载出错！FileNotFoundException",e);
        } catch (IOException e) {
            Logger.e(TAG,"文件下载出错！IOException",e);
        } finally {
            close(inputStream);
            close(randomAccessFile);
        }
    }

    /**
     * 关流
     * @param closeable 操作流
     */
    private static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止线程
     */
    public void stop() {
        mStatus = STATUS_STOP;
    }
}
