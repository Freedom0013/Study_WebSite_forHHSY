package com.studytree.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.studytree.R;
import com.studytree.commonfile.Constants;
import com.studytree.utils.StudyTreeTools;
import com.studytree.view.LoginActivity;
import com.studytree.view.MainActivity;
import com.studytree.view.QRScannerActivity;
import com.studytree.view.SettingActivity;
import com.studytree.view.WebViewActivity;
import com.studytree.view.base.BaseFragment;
import com.studytree.view.widget.CircularImage;

/**
 * 侧边菜单Fragment
 * Title: LeftMenuFragment
 * @date 2018/7/10 14:31
 * @author Freedom0013
 */
public class LeftMenuFragment extends BaseFragment implements View.OnClickListener{
    private static final String TAG = LeftMenuFragment.class.getSimpleName();
    /** Activity对象 */
    private MainActivity mActivity;
    /** 菜单根布局 */
    private LinearLayout root_menu;

    /** 登陆点击区域 */
    private LinearLayout login_ln;
    /** 头像（default login） */
    private CircularImage menu_avatar;
    /** 昵称文字区域（default login） */
    private TextView menu_name;
    /** 等级区域（default gone） */
    private FrameLayout rank_level_fm;
    /** 等级（default gone） */
    private TextView rank_level_number;
    /** 积分（default gone） */
    private TextView login_score;
    /** 菜单提示 */
    private TextView menu_sign;
    /** 退出登录点击区域（default gone） */
    private LinearLayout login_exit_ln;

    /** 空参构造函数（必须） */
    public LeftMenuFragment(){}

    /**
     * 侧滑菜单Fragment构造函数
     * @param activity Activity对象
     */
    @SuppressLint("ValidFragment")
    public LeftMenuFragment(Activity activity){
        mActivity = (MainActivity) activity;
    }

    @Override
    public View initView() {
        View mRootView = View.inflate(mActivity, R.layout.fragment_left_menu, null);
        //设置侧滑菜单背景
        root_menu = mRootView.findViewById(R.id.left_menu_root);
        root_menu.setBackgroundResource(R.drawable.menu_background);
        //菜单提示语
        menu_sign = mRootView.findViewById(R.id.menu_sign);

        //登陆附属控件
        login_ln = mRootView.findViewById(R.id.login_ln);
        menu_avatar = mRootView.findViewById(R.id.menu_avatar);
        menu_name = mRootView.findViewById(R.id.menu_name);
        rank_level_fm = mRootView.findViewById(R.id.rank_level_fm);
        rank_level_number = mRootView.findViewById(R.id.rank_level_number);
        login_score = mRootView.findViewById(R.id.login_score);
        login_exit_ln = mRootView.findViewById(R.id.login_exit_ln);

        initButtons(mRootView);
        return mRootView;
    }

    /**
     * 初始化侧边栏按钮
     * @param mRootView 侧边栏布局
     */
    private void initButtons(View mRootView) {
        mRootView.findViewById(R.id.leftmenu_home).setOnClickListener(this);
        mRootView.findViewById(R.id.leftmenu_scan).setOnClickListener(this);
        mRootView.findViewById(R.id.leftmenu_setting).setOnClickListener(this);
        mRootView.findViewById(R.id.leftmenu_share).setOnClickListener(this);
        mRootView.findViewById(R.id.leftmenu_feedback).setOnClickListener(this);
        mRootView.findViewById(R.id.leftmenu_likemine).setOnClickListener(this);
        mRootView.findViewById(R.id.leftmenu_aboutus).setOnClickListener(this);
        login_ln.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.leftmenu_home:        //首页
                mActivity.closeMenu();
                break;

            case R.id.leftmenu_scan:        //扫一扫
                mActivity.closeMenu();
                QRScannerActivity.startForResult(mActivity,mActivity.QR_REQUEST_CODE);
                break;

            case R.id.leftmenu_setting:     //设置
                mActivity.closeMenu();
                SettingActivity.start(mActivity);
                break;

            case R.id.leftmenu_share:       //分享

                break;

            case R.id.leftmenu_feedback:        //反馈

                break;

            case R.id.leftmenu_likemine:        //给个好评
                mActivity.closeMenu();
                goToMarket(mActivity,StudyTreeTools.getAppPackageName(mActivity));
                break;

            case R.id.leftmenu_aboutus:         //关于我们
                mActivity.closeMenu();
                WebViewActivity.start(mActivity, Constants.ABOUT_US_URL,"关于我们",WebViewActivity.WEB_ABOUT);
                break;
            case R.id.login_ln:                 //登录
                mActivity.closeMenu();
                LoginActivity.start(mActivity);
                break;
        }
    }

    /**
     * 跳转应用商店好评
     * @param context Context
     * @param packageName 包名
     */
    public static void goToMarket(Context context, String packageName) {
        Uri uri = Uri.parse("market://details?id=" + packageName);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}
