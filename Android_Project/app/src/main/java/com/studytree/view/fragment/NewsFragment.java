package com.studytree.view.fragment;

import android.app.Activity;
import android.view.View;

import com.studytree.R;
import com.studytree.view.base.BaseFragment;

/**
 * 资讯页面Fragment
 * Title: NewsFragment
 * @date 2018/7/10 17:16
 * @author Freedom0013
 */
public class NewsFragment extends BaseFragment {
    /** 空参构造函数（必须） */
    public NewsFragment(){}

    /**
     * 构造函数
     * @param activity Activity对象
     */
    public NewsFragment(Activity activity) {
        mActivity = activity;
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.tab_news, null);
        return view;
    }
}
