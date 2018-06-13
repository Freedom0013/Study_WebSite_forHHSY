package com.studytree;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Application类
 * Title: StudyTreeApplication
 * @date 2018/6/13 14:40
 * @author Freedom0013
 */
public class StudyTreeApplication extends Application {
    private static final String TAG = StudyTreeApplication.class.getSimpleName();

    public static StudyTreeApplication app;

    /**
     * 获取Application单例
     */
    public static StudyTreeApplication getInstance(){
        return app;
    }

    /**
     * 应用启动执行
     */
    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
    * 应用关闭执行
    */
    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * 低内存执行
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    /**
     * 回收内存（home）
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    /**
     * 配置改变，与activity不同，应用程序不会重启(横竖屏旋转)
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
