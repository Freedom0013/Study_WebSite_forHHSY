package com.studytree.http;

import com.studytree.commonfile.Constants;
import com.studytree.log.Logger;

import java.util.Map;

/**
 * Http支持类
 * Title: HttpTransaction
 * @date 2018/6/21 16:12
 * @author Freedom0013
 */
public class HttpTransaction extends HttpManager {
    private static final String TAG = HttpTransaction.class.getSimpleName();
    /** HttpTransaction单例 */
    private static HttpTransaction _instance;

    private HttpTransaction(){

    }

    /**
     * 获取HttpTransaction单例
     * @return HttpTransaction单例
     */
    public static HttpTransaction getInstance(){
        if(_instance == null){
            _instance = new HttpTransaction();
        }
        return _instance;
    }

    @Override
    protected String getUrl(int action) {
        String path = "";
        switch (action) {
            case ActionID.ACTION_INIT:
                path = Protocol.ACTION_INIT;
                break;
            case ActionID.ACTION_COURSE:
                path = Protocol.ACTION_COURSE;
                break;
            case ActionID.ACTION_COURSE_DETAIL:
                path = Protocol.ACTION_COURSE_DETAIL;
                break;
            case ActionID.ACTION_DEPARTMENT:
                path = Protocol.ACTION_DEPARTMENT;
                break;
            case ActionID.ACTION_EXAMINATION:
                path = Protocol.ACTION_EXAMINATION;
                break;
            case ActionID.ACTION_LOGIN:
                path = Protocol.ACTION_LOGIN;
                break;
            case ActionID.ACTION_PICTURE:
                path = Protocol.ACTION_PICTURE;
                break;
            case ActionID.ACTION_PROFESSIONAL:
                path = Protocol.ACTION_PROFESSIONAL;
                break;
            case ActionID.ACTION_QUESTION:
                path = Protocol.ACTION_QUESTION;
                break;
            case ActionID.ACTION_REGISTER:
                path = Protocol.ACTION_REGISTER;
                break;
            case ActionID.ACTION_WRITTEN_OFF:
                path = Protocol.ACTION_WRITTEN_OFF;
                break;
            case ActionID.ACTION_ANSWER:
                path = Protocol.ACTION_ANSWER;
                break;
        }
        return "http://" + Constants.HOST + path;
    }

    @Override
    protected int getMethod(int action) {
        int method = HttpConstants.METHOD_POST;
        //如果要改变谋个Action的请求方式只需在这里的switch添加条目即可
//        switch (action){
//            case ActionID.ACTION_INIT:
//                method = HttpConstants.METHOD_GET;
//                break;
//        }
        return method;
    }

    /**
     * 发起请求
     * @param action
     * @param sendData
     * @param httpListener
     */
    public void send(final int action, final Map<String, Object> sendData, final HttpCallback httpListener){
        super.send(action,sendData,httpListener);
    }
}
