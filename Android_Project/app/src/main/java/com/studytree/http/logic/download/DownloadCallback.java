package com.studytree.http.logic.download;

import java.io.File;

public interface DownloadCallback {
    void onSuccess(File file);
    void onFailure(Exception e);
    void onProgress(long progress, long currentLength);
    void onPause(long progress, long currentLength);
}
