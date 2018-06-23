package com.studytree.http;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.studytree.log.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Http请求主类
 * Title: HttpManager
 * @date 2018/6/20 23:57
 * @author Freedom0013
 */
public abstract class HttpManager {
    private static final String TAG = HttpManager.class.getSimpleName();
    /** 线程池最大数量为5 */
    private static final int THREAD_POOL_MAX_SIZE = 5;
    /** 线程池对象 */
    private static Executor mFixedThreadPoolExecutor = Executors.newFixedThreadPool(THREAD_POOL_MAX_SIZE);


    /**
     * 发起请求
     * @param action 接口ID
     * @param sendData 发送数据参数列表
     * @param httpListener 结果监听
     */
    public void send(final int action, final Map<String, Object> sendData, final HttpCallback httpListener){
        //判断请求方式
        final int method = getMethod(action);
        switch (method){
            case HttpConstants.METHOD_POST:
                sendPost(action,sendData,httpListener);
                break;
            case HttpConstants.METHOD_GET:
                sendGet(action,sendData,httpListener);
                break;
            case HttpConstants.METHOD_PUT:
                //待定
                break;
            case HttpConstants.METHOD_DELETE:
                //待定
                break;
        }
    }

    /**
     * 发起Post请求
     * @param action 接口ID
     * @param sendData 发送数据参数列表
     * @param httpListener 结果监听
     */
    protected void sendPost(final int action, final Map<String, Object> sendData, final HttpCallback httpListener) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    //创建OkHttpClient.Builder对象。
                    OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder();

                    //创建OKHttp日志拦截器
                    HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new OkHttpLogger());
                    logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    clientBuilder.addNetworkInterceptor(logInterceptor);

                    //设置超时时间
//                    clientBuilder.connectTimeout(15, TimeUnit.SECONDS);
//                    clientBuilder.readTimeout(30, TimeUnit.SECONDS);
//                    clientBuilder.writeTimeout(60, TimeUnit.SECONDS);

                    //创建表单请求体
                    FormBody.Builder formBody = new FormBody.Builder();

                    //解析传递数据Map
                    final HashMap<String, Object> data = (HashMap<String, Object>) sendData;
                    for (String key : data.keySet()){
                        //传递键值对参数
                        formBody.add(key, data.get(key)+"");
                    }

                    //创建Request对象。请注意此处全为Post请求
                    Request request = new Request.Builder()
                            .url(getUrl(action))        //设置地址
                            .post(formBody.build())     //传递请求体
                            .build();

                    //发送请求
                    clientBuilder.build().newCall(request).enqueue(new Callback() {
                        //请求成功
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful() && httpListener!=null) {
                                String responseBody = response.body().string();
                                Logger.d(TAG, "接口返回==" + responseBody);
                                JsonObject data = new JsonParser().parse(responseBody).getAsJsonObject();
                                httpListener.onSuccess(action,response.code(),data);
                            }
                        }
                        //请求失败
                        @Override
                        public void onFailure(Call call, IOException e) {
                            if(httpListener!=null){
                                Logger.e(TAG,"OkHttp请求异常！",e);
                                httpListener.onFail(action,call.request().hashCode(),"OkHttp请求异常！");
                            }
                        }
                    });
                } catch (Exception e) {
                    Logger.e(TAG,"OkHttp--post错误：",e);
                }
            }
        };
        //线程池创建
        mFixedThreadPoolExecutor.execute(runnable);
    }

    /**
     * 发起Get请求
     * @param action 接口ID
     * @param sendData 发送数据参数列表
     * @param httpListener 结果监听
     */
    protected void sendGet(final int action, final Map<String, Object> sendData, final HttpCallback httpListener) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    //创建OkHttpClient.Builder对象。
                    OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder();

                    //创建OKHttp日志拦截器
                    HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new OkHttpLogger());
                    logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    clientBuilder.addNetworkInterceptor(logInterceptor);

                    //设置超时时间
//                    clientBuilder.connectTimeout(15, TimeUnit.SECONDS);
//                    clientBuilder.readTimeout(30, TimeUnit.SECONDS);
//                    clientBuilder.writeTimeout(60, TimeUnit.SECONDS);

                    //url拼串
                    StringBuffer url = new StringBuffer();
                    url.append(getUrl(action));

                    //解析传递数据Map
                    final HashMap<String, Object> data = (HashMap<String, Object>) sendData;
                    if(data.keySet().size()>0 && !data.isEmpty()){
                        for (String key : data.keySet()){
                            //传递键值对参数
                            url.append("?");
                            url.append(key+"=");
                            url.append(data.get(key)+"");
                        }
                    }

                    //创建Request对象。请注意此处全为Post请求
                    Request request = new Request.Builder()
                            .url(url.toString())        //设置地址
                            .build();

                    clientBuilder.build().newCall(request).enqueue(new Callback() {
                        //请求成功
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.isSuccessful() && httpListener!=null) {
                                String responseBody = response.body().string();
                                JsonObject data = new JsonParser().parse(responseBody).getAsJsonObject();
                                Logger.d(TAG, "接口返回==" + responseBody);
                                httpListener.onSuccess(action,response.code(),data);
                            }
                        }
                        //请求失败
                        @Override
                        public void onFailure(Call call, IOException e) {
                            if(httpListener!=null){
                                Logger.e(TAG,"OkHttp请求异常！",e);
                                httpListener.onFail(action,call.request().hashCode(),"OkHttp请求异常！");
                            }
                        }
                    });
                }catch(Exception e){
                    Logger.e(TAG,"OkHttp--get错误：",e);
                }
            }
        };
        //线程池创建
        mFixedThreadPoolExecutor.execute(runnable);
    }

    /**
     * 根据接口码获取地址
     * @param action 接口码
     * @return 地址
     */
    protected abstract String getUrl(int action);

    /**
     * 根据接口码获取请求方式
     * @param action 接口码
     * @return 请求方式代码
     */
    protected abstract int getMethod(int action);
}
