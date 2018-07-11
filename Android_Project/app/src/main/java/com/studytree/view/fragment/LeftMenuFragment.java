package com.studytree.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import com.studytree.R;
import com.studytree.view.base.BaseFragment;

/**
 * 侧边菜单Fragment
 * Title: LeftMenuFragment
 * @date 2018/7/10 14:31
 * @author Freedom0013
 */
public class LeftMenuFragment extends BaseFragment {
    private static final String TAG = LeftMenuFragment.class.getSimpleName();
    /** Activity对象 */
    private Activity mActivity;
    /** 菜单根布局 */
    private LinearLayout root_menu;

    /** 空参构造函数（必须） */
    public LeftMenuFragment(){}

    /**
     * 侧滑菜单Fragment构造函数
     * @param activity Activity对象
     */
    @SuppressLint("ValidFragment")
    public LeftMenuFragment(Activity activity){
        mActivity = activity;
    }

    @Override
    public View initView() {
        View mRootView = View.inflate(mActivity, R.layout.fragment_left_menu, null);
        //设置侧滑菜单背景
        root_menu = mRootView.findViewById(R.id.left_menu_root);
        root_menu.setBackgroundResource(R.drawable.menu_background);

        return mRootView;
    }
}
