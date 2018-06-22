package com.studytree;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;

import com.studytree.commonfile.Constants;

/**
 * 应用初始化管理
 * Title: InitManager
 * @date 2018/6/13 15:27
 * @author Freedom0013
 */
public class InitManager {
    private static final String TAG = InitManager.class.getSimpleName();
    /** InitManager单例 */
    private static InitManager _instance = null;
    /** Context对象 */
    private Context mContext;
    /** 是否初始化过标识 */
    private boolean inited = false;
    /** SharedPreferences对象 */
    private SharedPreferences mConfigPrefs;
    /** 屏幕宽度 */
    private int mScreenWidth = -1;
    /** 屏幕高度 */
    private int mScreenHeight = -1;
    /** LayoutInflater布局服务对象 */
    private LayoutInflater mInflater;

    /**
     * 私有构造函数
     */
    private InitManager(){
    }

    /**
     * 获取InitManager单例
     * @return InitManager对象
     */
    public static InitManager getInstance(){
        if(_instance == null){
            _instance = new InitManager();
        }
        return _instance;
    }

    /**
     * 初始化应用
     */
    public void init(Context context){
        if(inited){
            return;
        }
        inited = true;
        this.mContext = context;
        //初始化异常处理类
        ExceptionHandler.getInstance().init(mContext.getApplicationContext());
    }

    /**
     * 获取屏幕宽度
     * @return 屏幕宽度（获取失败返回-1）
     */
    public int getScreenWidth() {
        if (mScreenWidth == -1) {
            mScreenWidth = mContext.getResources().getDisplayMetrics().widthPixels;
        }
        return mScreenWidth;
    }

    /**
     * 获取屏幕高度
     * @return 屏幕高度（获取失败返回-1）
     */
    public int getScreenHeight() {
        if (mScreenHeight == -1) {
            mScreenHeight = mContext.getResources().getDisplayMetrics().heightPixels;
        }
        return mScreenHeight;
    }

    /**
     * 获取LayoutInflater对象
     * @return LayoutInflater
     */
    public LayoutInflater getInflater(){
        if(mInflater == null){
            mInflater = LayoutInflater.from(mContext);
        }
        return mInflater;
    }

    /**
     * 获取Context对象
     * @return Context
     */
    public Context getContext(){
        return mContext;
    }

    /**
     * 获取系统SharedPreferences对象
     * @return SharedPreferences对象
     */
    private SharedPreferences getPrefs(){
        if(mConfigPrefs == null){
            mConfigPrefs = mContext.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE);
        }
        return mConfigPrefs;
    }

    /**
     * 获取String配置项
     * @param key 键
     * @return 值
     */
    public String getStringPreference(String key){
        return getPrefs().getString(key, "");
    }

    /**
     * 保存String配置项
     * @param key 键
     * @param value 值
     */
    public void saveStringPreference(String key,String value){
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 获取Boolean配置项
     * @param key 键
     * @return 值
     */
    public boolean getBooleanPreference(String key){
        return getPrefs().getBoolean(key,false);
    }

    /**
     * 保存Boolean配置项
     * @param key 键
     * @param value 值
     */
    public void saveBooleanPreference(String key,boolean value){
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 保存Int配置项
     * @param key 键
     * @param value 值
     */
    public void saveIntPreference(String key,int value){
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 获取默认值Int配置项
     * @param key 键
     * @param defaultValue 默认值
     * @return 值
     */
    public int getIntPreference(String key,int defaultValue){
        int index = getPrefs().getInt(key,defaultValue);
        return index;
    }

    /**
     * 获取Int配置项
     * @param key 键
     * @return 值
     */
    public int getIntPreference(String key){
        int index = getPrefs().getInt(key,-1);
        return index;
    }

    /**
     *获取Long配置项
     * @param key 键
     * @return 值
     */
    public long getLongPreference(String key){
        long index = getPrefs().getLong(key,0L);
        return index;
    }

    /**
     * 保存Long配置项
     * @param key 键
     * @param value 值
     */
    public void saveLongPreference(String key,long value){
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putLong(key, value);
        editor.commit();
    }

}
