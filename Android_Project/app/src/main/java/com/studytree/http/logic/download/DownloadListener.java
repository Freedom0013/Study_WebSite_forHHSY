package com.studytree.http.logic.download;

/**
 * 下载完成监听
 * Title: DownloadListener
 * @date 2018/7/5 20:53
 * @author Freedom0013
 */
public interface DownloadListener {
    /** 下载完成回调 */
    void onDownloadFinished(boolean success);
}
