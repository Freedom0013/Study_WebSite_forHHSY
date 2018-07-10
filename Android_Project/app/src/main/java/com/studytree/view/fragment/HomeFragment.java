package com.studytree.view.fragment;

import android.app.Activity;
import android.view.View;

import com.studytree.R;
import com.studytree.view.base.BaseFragment;

/**
 * 首页界面Fragment
 * Title: HomeFragment
 * @date 2018/7/10 17:16
 * @author Freedom0013
 */
public class HomeFragment extends BaseFragment {
    /** Activity对象 */
    private Activity mActivity;

    /** 空参构造函数（必须） */
    public HomeFragment(){}

    /**
     * 构造函数
     * @param activity Activity对象
     */
    public HomeFragment(Activity activity) {
        mActivity = activity;
    }

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.tab_home, null);
        return view;
    }


}
