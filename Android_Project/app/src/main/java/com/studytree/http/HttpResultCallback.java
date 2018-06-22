package com.studytree.http;

/**
 * 接口结果监听
 * Title: HttpResultCallback
 * @date 2018/6/20 23:42
 * @author Freedom0013
 */
public interface HttpResultCallback {
    /**
     * 成功
     * @param action 地址
     * @param obj 响应体
     */
    void onSuccess(int action, Object obj);

    /**
     * 失败
     * @param action 地址
     * @param responseCode 状态码
     * @param responseMsg 错误信息
     */
    void onFail(int action, int responseCode, String responseMsg);
}
