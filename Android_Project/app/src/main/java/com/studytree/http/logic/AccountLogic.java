package com.studytree.http.logic;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.studytree.bean.UserBean;
import com.studytree.http.ActionID;
import com.studytree.http.HttpCallback;
import com.studytree.http.HttpResultCallback;
import com.studytree.http.HttpTransaction;
import com.studytree.log.Logger;
import com.studytree.utils.StudyTreeTools;

import java.util.HashMap;

/**
 * 用户信息逻辑类
 * Title: AccountLogic
 * @date 2018/7/25 14:37
 * @author Freedom0013
 */
public class AccountLogic {
    private static final String TAG = AccountLogic.class.getSimpleName();
    /** InitLogic单例 */
    private static AccountLogic _instance;
    /** 网络接口类 */
    private HttpTransaction mHttpTransaction = HttpTransaction.getInstance();

    private AccountLogic(){}

    /**
     * 获取单例（线程安全）
     * @return InitLogic单例
     */
    public static AccountLogic getInstance() {
        if (_instance == null) {
            synchronized (AccountLogic.class) {
                if (_instance == null) {
                    _instance = new AccountLogic();
                }
            }
        }
        return _instance;
    }

    /**
     * 用户登录
     * @param listener 结果监听
     */
    public void login(String username, String passward, final HttpResultCallback listener) {
        HashMap<String, Object> attr = new HashMap<String, Object>();
        attr.put("username", username);
        attr.put("password", passward);
        attr.put("sign", StudyTreeTools.createSign(attr));
        mHttpTransaction.send(ActionID.ACTION_LOGIN, attr, new HttpCallback() {
            @Override
            public void onSuccess(int action, int responseCode, JsonObject obj) {
                if (listener != null) {
                    listener.onSuccess(action, obj.toString());
                }
            }
            @Override
            public void onFail(int action, int responseCode, String responseMsg) {
                if (listener != null) {
                    listener.onFail(action, responseCode, responseMsg);
                }
            }
        });
    }


    /**
     * 用户注册
     * @param listener 结果监听
     */
    public void regisier(UserBean bean, final HttpResultCallback listener) {
        Gson gson = new Gson();
        String userJson = gson.toJson(bean);
        HashMap<String, Object> attr = new HashMap<String, Object>();
        long time = System.currentTimeMillis();
        attr.put("userJson", userJson);
        attr.put("token", time);
        attr.put("sign", StudyTreeTools.createSign(attr));
        Logger.d(TAG, attr.toString());
        mHttpTransaction.send(ActionID.ACTION_REGISTER, attr, new HttpCallback() {
            @Override
            public void onSuccess(int action, int responseCode, JsonObject obj) {
                if (listener != null) {
                    listener.onSuccess(action, obj.toString());
                }
            }
            @Override
            public void onFail(int action, int responseCode, String responseMsg) {
                if (listener != null) {
                    listener.onFail(action, responseCode, responseMsg);
                }
            }
        });
    }
}
