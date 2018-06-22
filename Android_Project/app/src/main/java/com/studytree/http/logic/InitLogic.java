package com.studytree.http.logic;

import com.google.gson.JsonObject;
import com.studytree.http.ActionID;
import com.studytree.http.HttpCallback;
import com.studytree.http.HttpResultCallback;
import com.studytree.http.HttpTransaction;
import com.studytree.log.Logger;
import com.studytree.utils.StudyTreeTools;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * 更新信息获取
 * Title: InitLogic
 * @date 2018/6/21 17:16
 * @author Freedom0013
 */
public class InitLogic {
    private static final String TAG = InitLogic.class.getSimpleName();
    /** InitLogic单例 */
    private static InitLogic _instance;
    /** 网络接口类 */
    private HttpTransaction mHttpTransaction = HttpTransaction.getInstance();

    private InitLogic(){

    }

    /**
     * 获取单例
     * @return InitLogic单例
     */
    public static InitLogic getInstance(){
        if(_instance == null){
            _instance = new InitLogic();
        }
        return _instance;
    }

    /**
     * 获取更新信息
     * @param listener 结果监听
     */
    public void getUpdataInfo(final HttpResultCallback listener) {
        final HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("from", TAG+"我是来自客户端的信息！！");
        data.put("sign", StudyTreeTools.createSign(data));
        mHttpTransaction.send(ActionID.ACTION_INIT, data, new HttpCallback() {
//        mHttpTransaction.sendGet(ActionID.ACTION_INIT, data, new HttpCallback() {
            @Override
            public void onSuccess(int action, int responseCode, JsonObject obj) {
                Logger.d(TAG, "action" + action);
                Logger.d(TAG, "responseCode" + responseCode);
                Logger.d(TAG, "obj" + obj.toString());
                if (listener != null) {
                    listener.onSuccess(action,obj.toString());
                }
            }

            @Override
            public void onFail(int action, int responseCode, String responseMsg) {
                Logger.d(TAG, "action" + action);
                Logger.d(TAG, "responseCode" + responseCode);
                Logger.d(TAG, "responseMsg" + responseMsg);
                if (listener != null) {
                    listener.onFail(action, responseCode, responseMsg);
                }
            }
        });
    }
}
