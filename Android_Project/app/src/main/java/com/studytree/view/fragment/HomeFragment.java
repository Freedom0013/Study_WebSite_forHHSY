package com.studytree.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

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
    private ImageView home_banner;

    /** 空参构造函数（必须） */
    public HomeFragment(){}

    /**
     * 构造函数
     * @param activity Activity对象
     */
    @SuppressLint("ValidFragment")
    public HomeFragment(Activity activity) {
        mActivity = activity;
    }

    @Override
    public View initView() {
        View mRootView = View.inflate(mActivity, R.layout.tab_home, null);

        return mRootView;
    }


    /**
     * 修正banner大小
     */
    private void fixTopFrameSize(){
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) home_banner.getLayoutParams();
        params.height = (int) (getActivity().getResources().getDisplayMetrics().widthPixels / 3.12);
        home_banner.setLayoutParams(params);
    }

}
