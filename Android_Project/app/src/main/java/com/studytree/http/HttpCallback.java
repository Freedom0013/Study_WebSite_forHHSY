package com.studytree.http;

import com.google.gson.JsonObject;

/**
 * Http监听接口
 * Title: HttpCallback
 * @date 2018/6/20 23:32
 * @author Freedom0013
 */
public interface HttpCallback {
    /**
     * 成功
     * @param action 地址
     * @param responseCode 状态码
     * @param obj 响应体
     */
    void onSuccess(int action, int responseCode, JsonObject obj);

    /**
     * 失败
     * @param action 地址
     * @param responseCode 状态码
     * @param responseMsg 错误信息
     */
    void onFail(int action, int responseCode, String responseMsg);
}
