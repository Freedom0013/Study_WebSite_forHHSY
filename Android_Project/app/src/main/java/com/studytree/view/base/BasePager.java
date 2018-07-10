package com.studytree.view.base;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;

import com.studytree.R;

/**
 * 主页ViewPager基类
 * Title: BasePager
 * @date 2018/7/10 12:37
 * @author Freedom0013
 */
public class BasePager {
    private static final String TAG = BasePager.class.getSimpleName();
    /** Activity对象 */
    public Activity mActivity;
    /** View布局 */
    public View mRootView;
    private FrameLayout mBaseFrameLayout;

    public BasePager(Activity activity){
        mActivity = activity;
        initView();
    }

    /**
     * 初始化界面
     */
    public void initView() {
        mRootView = View.inflate(mActivity, R.layout.pager_base,null);
        mBaseFrameLayout = mRootView.findViewById(R.id.base_pager_framelayout);
    }

    /**
     * 初始化数据
     */
    public void initData() {
    }
}
