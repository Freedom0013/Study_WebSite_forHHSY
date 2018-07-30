package com.studytree.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.studytree.InitManager;
import com.studytree.R;
import com.studytree.bean.UserBean;
import com.studytree.commonfile.Constants;
import com.studytree.log.Logger;
import com.studytree.utils.StudyTreeTools;
import com.studytree.view.LoginActivity;
import com.studytree.view.MainActivity;
import com.studytree.view.PaymentSelectionActivity;
import com.studytree.view.QRScannerActivity;
import com.studytree.view.SettingActivity;
import com.studytree.view.WebViewActivity;
import com.studytree.view.base.BaseFragment;
import com.studytree.view.widget.CircularImage;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

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
    private RelativeLayout login_exit_rl;
    /** 用户Bean */
    private UserBean mUserBean;
    private Button join_button;

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
        menu_sign.setText("加入会员可查看更多专属题目~");

        //登陆附属控件
        login_ln = mRootView.findViewById(R.id.login_ln);
        menu_avatar = mRootView.findViewById(R.id.menu_avatar);
        menu_name = mRootView.findViewById(R.id.menu_name);
        rank_level_fm = mRootView.findViewById(R.id.rank_level_fm);
        rank_level_number = mRootView.findViewById(R.id.rank_level_number);
        login_score = mRootView.findViewById(R.id.login_score);
        login_exit_rl = mRootView.findViewById(R.id.login_exit_rl);
        join_button = mRootView.findViewById(R.id.join_button);
        join_button.setVisibility(View.GONE);

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
        login_exit_rl.setOnClickListener(this);
        join_button.setOnClickListener(this);

        mUserBean = InitManager.getInstance().getUserInfo();
        if(mUserBean !=null){
            Logger.d(TAG,"本地保存的用户信息："+ mUserBean.toString());
            updataLoginUser(mUserBean);
        }
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
                new ShareAction(mActivity).withText("知识森驿站分享")
                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                        .setCallback(shareListener).open();
                break;

            case R.id.leftmenu_feedback:        //反馈
                if(mUserBean !=null){
                    showToast("请先登录~");
                }
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
                LoginActivity.startForResult(mActivity,mActivity.LOGIN_REQUEST_CODE);
                break;
            case R.id.login_exit_rl:            //退出登录
                mActivity.closeMenu();
                showWrittenOffDialog();
                break;
            case R.id.join_button:              //加入会员
                mActivity.closeMenu();
                PaymentSelectionActivity.startForResult(mActivity,mActivity.PAY_REQUEST_CODE);
                break;
        }
    }

    /**
     * 注销登录确认框
     */
    private void showWrittenOffDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        //填充内容
        builder.setTitle("您确定退出登录吗？");
        builder.setMessage("退出后将无法再积累积分和等级");
        builder.setPositiveButton("立即注销！", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                writtenOfflogin();
            }
        });
        builder.setNegativeButton("暂不注销", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        //Dialog取消按键监听
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
            }
        });
        builder.show();
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

    /**
     * 登录成功更新UI
     * @param bean 用户Bean
     */
    public void updataLoginUser(final UserBean bean) {
        Logger.d(TAG, bean.toString());
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                join_button.setVisibility(View.VISIBLE);
                menu_sign.setText("加入会员可查看更多专属题目~");
                menu_name.setText(bean.user_nickname);
                ImageLoader.getInstance().displayImage(bean.user_picture_url, menu_avatar);
                rank_level_fm.setVisibility(View.VISIBLE);
                rank_level_number.setText("Lv." + bean.user_status);
                login_score.setVisibility(View.VISIBLE);
                login_score.setText("积分：" + bean.user_integral + "分");
                login_exit_rl.setVisibility(View.VISIBLE);
                login_ln.setOnClickListener(null);
            }
        });
    }

    /**
     * 注销登录
     */
    private void writtenOfflogin() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                InitManager.getInstance().setUserInfo(null, null);
                InitManager.getInstance().savePhoneAndPasswordToPrefs(null, null);
                menu_sign.setVisibility(View.VISIBLE);
                join_button.setVisibility(View.GONE);
                menu_sign.setText("登录后可体验更多学习乐趣~");
                menu_name.setText("立即登录");
                menu_avatar.setImageDrawable(getResources().getDrawable(R.drawable.icon_user_left_normal));
                rank_level_number.setText("");
                rank_level_fm.setVisibility(View.GONE);
                login_score.setText("");
                login_score.setVisibility(View.GONE);
                login_exit_rl.setVisibility(View.GONE);
                showToast("注销成功！");
                login_ln.setOnClickListener(LeftMenuFragment.this);
                mActivity.getMainFragment().getMine_pager().writtenOfflogin();
            }
        });
    }

    public UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(mActivity, "分享成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(mActivity, "分享失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(mActivity, "分享取消了", Toast.LENGTH_LONG).show();

        }
    };
}
