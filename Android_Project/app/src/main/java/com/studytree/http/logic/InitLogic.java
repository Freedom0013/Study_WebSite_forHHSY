package com.studytree.http.logic;

import com.google.gson.JsonObject;
import com.studytree.http.ActionID;
import com.studytree.http.HttpCallback;
import com.studytree.http.HttpResultCallback;
import com.studytree.http.HttpTransaction;

import java.util.HashMap;

/**
 * 更新信息获取
 * Title: InitLogic
 * @date 2018/6/21 17:16
 * @author Freedom0013
 */
public class InitLogic{
    private static final String TAG = InitLogic.class.getSimpleName();
    /** InitLogic单例 */
    private static InitLogic _instance;
    /** 网络接口类 */
    private HttpTransaction mHttpTransaction = HttpTransaction.getInstance();

    private InitLogic(){

    }

    /**
     * 获取单例（线程安全）
     * @return InitLogic单例
     */
    public static InitLogic getInstance() {
        if (_instance == null) {
            synchronized (InitLogic.class) {
                if (_instance == null) {
                    _instance = new InitLogic();
                }
            }
        }
        return _instance;
    }


    /**
     * 获取更新信息
     * @param listener 结果监听
     */
    public void getUpdataInfo(final HttpResultCallback listener) {
        //封装参数列表
        final HashMap<String, Object> dataMessage = new HashMap<String, Object>();
        dataMessage.put("from", TAG+"我是来自客户端的信息！！");
//----------------------实例数据start----------------------------------
//        JsonObject data = new JsonObject();
//        JsonArray data_array = new JsonArray();
//
//        //更新标识
//        JsonObject updata_flag = new JsonObject();
//        updata_flag.addProperty("updata_flag", "false");
//        data_array.add(updata_flag);
//
//        //更新标题
//        JsonObject title = new JsonObject();
//        title.addProperty("updata_title", "我是更新标题");
//        data_array.add(title);
//
//        //更新版本号
//        JsonObject vision = new JsonObject();
//        vision.addProperty("updata_visionCode", 1);
//        data_array.add(vision);
//
//        //更新日期
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        JsonObject date = new JsonObject();
//        date.addProperty("updata_date", df.format(new Date()));
//        data_array.add(date);
//
//        //更新说明
//        List<String> UpdataMessage = new ArrayList<String>();
//        UpdataMessage.add("我是更新日志第一条。");
//        UpdataMessage.add("我是更新日志第二条。");
//        UpdataMessage.add("我是更新日志第三条。");
//        UpdataMessage.add("我是更新日志第四条。");
//        JsonArray messagearray = new JsonArray();
//        for (int i = 0; i < UpdataMessage.size(); i++) {
//            JsonObject single_message = new JsonObject();
//            single_message.addProperty("updata_Message" + i, UpdataMessage.get(i));
//            messagearray.add(single_message);
//        }
//
//        JsonObject messages = new JsonObject();
//        messages.add("Messages", messagearray);
//        data_array.add(messages);
//        data.add("data", data_array);
//        dataMessage.put("sign", data.toString());

//----------------------实例数据end----------------------------------

        mHttpTransaction.send(ActionID.ACTION_INIT, dataMessage, new HttpCallback() {
            @Override
            public void onSuccess(int action, int responseCode, JsonObject obj) {
                if (listener != null) {
                    listener.onSuccess(action,obj.toString());
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
     * 获取Banner信息
     * @param listener 结果监听
     */
    public void getBannnerInfo(final HttpResultCallback listener) {
        mHttpTransaction.send(ActionID.ACTION_BANNER, new HashMap<String, Object>(), new HttpCallback() {
            @Override
            public void onSuccess(int action, int responseCode, JsonObject obj) {
                if (listener != null) {
                    listener.onSuccess(action,obj.toString());
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
     * 获取Department信息
     * @param listener 结果监听
     */
    public void getDepartmentInfo(final HttpResultCallback listener) {
        mHttpTransaction.send(ActionID.ACTION_DEPARTMENT, new HashMap<String, Object>(), new HttpCallback() {
            @Override
            public void onSuccess(int action, int responseCode, JsonObject obj) {
                if (listener != null) {
                    listener.onSuccess(action,obj.toString());
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
