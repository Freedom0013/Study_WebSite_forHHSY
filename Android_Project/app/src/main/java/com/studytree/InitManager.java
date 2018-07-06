package com.studytree;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.studytree.bean.DeviceInfoBean;
import com.studytree.commonfile.Constants;
import com.umeng.commonsdk.UMConfigure;

import java.io.File;

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
    public Context mContext;
    /** 是否初始化过标识 */
    public boolean inited = false;
    /** SharedPreferences对象 */
    public SharedPreferences mConfigPrefs;
    /** 屏幕宽度 */
    public int mScreenWidth = -1;
    /** 屏幕高度 */
    public int mScreenHeight = -1;
    /** LayoutInflater布局服务对象 */
    public LayoutInflater mInflater;
    /** ImageLoader缓存图片存储目录 */
    public File ImageLoader_Cache_dir = new File(Constants.IMAGE_DIR);
    /** 设备信息DeviceUtils中引用 */
    public DeviceInfoBean devceinfo;

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
        if (_instance == null) {
            synchronized (InitManager.class) {
                if (_instance == null) {
                    _instance = new InitManager();
                }
            }
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
        //初始化友盟统计
        UMConfigure.init(mContext,Constants.UMAppKey, Constants.UMChannelID, UMConfigure.DEVICE_TYPE_PHONE ,null);

        //ImageLoader初始化设置（ImageLoader建造者设计模式）
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
            .threadPoolSize(5) // 线程池大小
            .threadPriority(Thread.NORM_PRIORITY - 2) // 设置线程优先级
            .denyCacheImageMultipleSizesInMemory() // 不允许在内存中缓存同一张图片的多个尺寸
            .tasksProcessingOrder(QueueProcessingType.LIFO) // 设置处理队列的类型，包括LIFO， FIFO
            .memoryCache(new LruMemoryCache(3 * 1024 * 1024)) // 内存缓存策略
            .memoryCacheSize(5 * 1024 * 1024)  // 内存缓存大小
            .memoryCacheExtraOptions(480, 800) // 内存缓存中每个图片的最大宽高
            .memoryCacheSizePercentage(50) // 内存缓存占总内存的百分比
            .diskCache(new UnlimitedDiskCache(ImageLoader_Cache_dir)) // 设置磁盘缓存路径
            .diskCacheSize(50 * 1024 * 1024) // 设置磁盘缓存的大小
            .diskCacheFileCount(50) // 磁盘缓存文件数量
            .diskCacheFileNameGenerator(new Md5FileNameGenerator()) // 磁盘缓存时图片名称加密方式
            .imageDownloader(new BaseImageDownloader(mContext,60 * 1000, 60 * 1000)) // 图片下载器(后面数字为超时时间)
            .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
            .writeDebugLogs() // 打印日志
            .build();
        //ImageLoader初始化
        ImageLoader.getInstance().init(config);
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
     * @return 值（默认false）
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
