package com.studytree.http.logic.download;

public interface DownloadListener {
    /**下载完成回调*/
    void onDownloadFinished(boolean success);
}
