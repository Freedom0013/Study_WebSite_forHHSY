package com.studytree.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import com.studytree.R;
import com.studytree.view.MainActivity;
import com.studytree.view.base.BaseFragment;
import com.studytree.view.widget.StudyTreeTitleBar;

/**
 * 资讯页面Fragment
 * Title: NewsFragment
 * @date 2018/7/10 17:16
 * @author Freedom0013
 */
public class NewsFragment extends BaseFragment implements StudyTreeTitleBar.TitleBarClickListener{
    private static final String TAG = NewsFragment.class.getSimpleName();
    private MainActivity mActivity;
    private StudyTreeTitleBar news_tool;

    /** 空参构造函数（必须） */
    public NewsFragment(){}

    /**
     * 构造函数
     * @param activity Activity对象
     */
    @SuppressLint("ValidFragment")
    public NewsFragment(Activity activity) {
        mActivity = (MainActivity) activity;
    }

    @Override
    public View initView() {
        View mRootView = View.inflate(mActivity, R.layout.tab_news, null);

        //配置toolBar
        news_tool = mRootView.findViewById(R.id.news_tool);
        news_tool.setTitleRightVisibility(false);
        news_tool.setTitle("最新资讯");
        mActivity.setSupportActionBar(news_tool);
        news_tool.setOnTitleBarClickedListener(this);

        return mRootView;
    }

    @Override
    public void onTitleLeftClicked() {
        mActivity.openMenu();
    }

    @Override
    public void onTitleRightClicked() {

    }

    @Override
    public void onTitleClicked() {

    }
}
