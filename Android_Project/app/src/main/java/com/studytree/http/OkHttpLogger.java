package com.studytree.http;

import com.studytree.log.Logger;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * OKHttp日志拦截器
 * Title: OkHttpLogger
 * @date 2018/6/22 18:10
 * @author Freedom0013
 */
public class OkHttpLogger implements HttpLoggingInterceptor.Logger {
    private static final String TAG = OkHttpLogger.class.getSimpleName();

    @Override
    public void log(String message) {
        Logger.d(TAG, "=======OkHttpLogger-------:" + message);
    }
}
