package com.studytree.http.logic.download;

import java.io.File;

/**
 * 下载状态回调
 * Title: DownloadCallback
 * @date 2018/7/5 20:51
 * @author Freedom0013
 */
public interface DownloadCallback {
    /**
     * 下载成功
     * @param file 下载文件
     */
    void onSuccess(File file);

    /**
     * 下载失败
     * @param e Exception
     */
    void onFailure(Exception e);

    /**
     * 下载进度回调
     * @param progress 当前进度
     * @param currentLength 总进度
     */
    void onProgress(long progress, long currentLength);

    /**
     * 暂停下载
     * @param progress 当前进度
     * @param currentLength 总进度
     */
    void onPause(long progress, long currentLength);
}
