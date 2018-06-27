package com.studytree.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.studytree.R;
import com.studytree.view.base.BaseActivity;

/**
 * 闪屏页
 * Title: SplashActivity
 * @date 2018/6/25 16:59
 * @author Freedom0013
 */
public class SplashActivity extends BaseActivity {
    public static final String TAG = SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        initView();
    }

    /** 初始化界面 */
    private void initView() {

    }
}
