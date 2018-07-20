package com.studytree.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;

import com.studytree.R;
import com.studytree.view.MainActivity;
import com.studytree.view.base.BaseFragment;
import com.studytree.view.widget.StudyTreeTitleBar;

/**
 * 我的页面Fragment
 * Title: MineFragment
 * @date 2018/7/10 17:16
 * @author Freedom0013
 */
public class MineFragment extends BaseFragment implements StudyTreeTitleBar.TitleBarClickListener{
    private static final String TAG = MineFragment.class.getSimpleName();
    private StudyTreeTitleBar mine_tool;
    /** Activity对象 */
    private MainActivity mActivity;

    /** 空参构造函数（必须） */
    public MineFragment(){}

    /**
     * 构造函数
     * @param activity Activity对象
     */
    @SuppressLint("ValidFragment")
    public MineFragment(Activity activity) {
        mActivity = (MainActivity) activity;
    }

    @Override
    public View initView() {
        View mRootView = View.inflate(mActivity, R.layout.tab_mine, null);

        //配置toolBar
        mine_tool = mRootView.findViewById(R.id.mine_tool);
        mine_tool.setTitleRightVisibility(false);
        mine_tool.setTitle("个人中心");
        mActivity.setSupportActionBar(mine_tool);
        mine_tool.setOnTitleBarClickedListener(this);

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
