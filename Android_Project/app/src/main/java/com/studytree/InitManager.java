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
    /** LayoutInflater对象 */
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
}
