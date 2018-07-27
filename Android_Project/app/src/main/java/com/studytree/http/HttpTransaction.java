package com.studytree.http;

import com.studytree.commonfile.Constants;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

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

    /** 空参构造函数 */
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

    /**
     * 获取Url
     * @param action 接口码
     * @return Url字符串
     */
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
            case ActionID.ACTION_BANNER:
                path = Protocol.ACTION_BANNER;
                break;
            case ActionID.ACTION_NEWS:
                path = Protocol.ACTION_NEWS;
                break;
        }
        return "http://" + Constants.HOST + path;
    }

    /**
     * 获取请求方式（现阶段只有Post）
     * @param action 接口码
     * @return 请求方式
     */
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

    /**
     * 多线程下载请求
     * @param url 下载地址
     * @param start 下载开始位置
     * @param end 下载结束位置
     * @return Response
     */
    public Response syncResponse(String url, long start, long end) throws IOException{
        return super.syncResponse(url,start,end);
    }

    /**
     * 获取下载信息
     * @param url 下载地址
     * @return Call
     */
    public Call asyncCall(String url){
        return super.asyncCall(url);
    }
}
