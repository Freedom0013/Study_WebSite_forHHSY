package com.studytree.http.logic.download;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 下载处理类
 * Title: DownloadTask
 * @date 2018/7/5 21:14
 * @author Freedom0013
 */
public class DownloadTask {
    private static final String TAG = DownloadTask.class.getSimpleName();
    /** 文件下载地址 */
    private String url;
    /** 文件名称 */
    private String name;
    /** 文件大小 */
    private long mContentLength;
    /** 文件下载的线程的个数 */
    private int mThreadSize;
    /** 线程下载成功的个数,使用volatile关键字，多线程保证变量可见性 */
    private volatile int mSuccessNumber;
    /** 总进度=每个线程的进度的和 */
    private long mTotalProgress;
    /** 下载线程集合 */
    private List<DownloadRunnable> mDownloadRunnables;
    /** 下载回调 */
    private DownloadCallback mDownloadCallback;

    /**
     * 处理类构造函数
     * @param name 下载文件名
     * @param url 地址
     * @param threadSize 线程总个数
     * @param contentLength 文件大小
     * @param callBack 下载回调
     */
    public DownloadTask(String name, String url, int threadSize, long contentLength, DownloadCallback callBack) {
        this.name = name;
        this.url = url;
        this.mThreadSize = threadSize;
        this.mContentLength = contentLength;
        this.mDownloadRunnables = new ArrayList<>();
        this.mDownloadCallback = callBack;
    }

    /**
     * 初始化
     */
    public void init() {
        for (int i = 0; i < mThreadSize; i++) {
            //每个线程的下载的大小threadSize
            long threadSize = mContentLength / mThreadSize;
            //开始下载的位置
            long start = i * threadSize;
            //结束下载的位置
            long end = start + threadSize - 1;
            if (i == mThreadSize - 1) {
                end = mContentLength - 1;
            }
            DownloadRunnable downloadRunnable = new DownloadRunnable(name, url, mContentLength, i, start, end, new DownloadCallback() {
                @Override
                public void onFailure(Exception e) {        //下载失败
                    //有一个线程发生异常，下载失败，需要把其它线程停止掉
                    mDownloadCallback.onFailure(e);
                    stopDownload();
                }

                @Override
                public void onSuccess(File file) {                  //下载成功
                    mSuccessNumber = mSuccessNumber + 1;
                    if (mSuccessNumber == mThreadSize) {            //所有线程下载完毕
                        mDownloadCallback.onSuccess(file);          //返回下载文件
                        DownloadDispatcher.getInstance().recyclerTask(DownloadTask.this);   //回收
                    }
                }

                @Override
                public void onProgress(long progress, long currentLength) {
                    //叠加下progress，实时去更新进度条,这里需要synchronized下
                    synchronized (DownloadTask.this) {
                        mTotalProgress = mTotalProgress + progress;
                        mDownloadCallback.onProgress(mTotalProgress, currentLength);
                    }
                }

                @Override
                public void onPause(long progress, long currentLength) {
                    mDownloadCallback.onPause(progress,currentLength);
                }
            });
            //通过线程池去执行
            DownloadDispatcher.getInstance().executorService().execute(downloadRunnable);
            mDownloadRunnables.add(downloadRunnable);
        }
    }

    /**
     * 停止下载
     */
    public void stopDownload() {
        for (DownloadRunnable runnable : mDownloadRunnables) {
            runnable.stop();
        }
    }

    /**
     * 获取下载地址
     * @return 下载地址
     */
    public String getUrl() {
        return url;
    }
}
