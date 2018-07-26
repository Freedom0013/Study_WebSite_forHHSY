package com.studytree.view.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.studytree.InitManager;
import com.studytree.R;
import com.studytree.bean.UserBean;
import com.studytree.log.Logger;
import com.studytree.view.LoginActivity;
import com.studytree.view.MainActivity;
import com.studytree.view.base.BaseFragment;
import com.studytree.view.widget.CircularImage;
import com.studytree.view.widget.StudyTreeTitleBar;

/**
 * 我的页面Fragment
 * Title: MineFragment
 * @date 2018/7/10 17:16
 * @author Freedom0013
 */
public class MineFragment extends BaseFragment implements StudyTreeTitleBar.TitleBarClickListener,View.OnClickListener{
    private static final String TAG = MineFragment.class.getSimpleName();
    private StudyTreeTitleBar mine_tool;
    /** Activity对象 */
    private MainActivity mActivity;
    /** 控件对象 */
    private CircularImage mine_userimage;
    private LinearLayout mine_user_info_ln;
    private TextView mine_nickname;
    private TextView mine_username;
    private ImageView mine_sex_icon;
    private TextView mine_addtime;
    private LinearLayout unlogin_hint_ln;
    private Button mine_login;
    private LinearLayout mine_user_infos;
    private TextView mine_sorce;
    private TextView mine_email;
    private TextView user_city;
    private TextView mine_school;
    private TextView mine_qq;
    /** 用户信息 */
    private UserBean mUserBean;

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

        mine_userimage = mRootView.findViewById(R.id.mine_userimage);
        mine_user_info_ln = mRootView.findViewById(R.id.mine_user_info_ln);
        mine_nickname = mRootView.findViewById(R.id.mine_nickname);
        mine_username = mRootView.findViewById(R.id.mine_username);
        mine_sex_icon = mRootView.findViewById(R.id.mine_sex_icon);
        mine_addtime = mRootView.findViewById(R.id.mine_addtime);
        unlogin_hint_ln = mRootView.findViewById(R.id.unlogin_hint_ln);
        mine_login = mRootView.findViewById(R.id.mine_login);
        mine_user_infos = mRootView.findViewById(R.id.mine_user_infos);
        mine_sorce = mRootView.findViewById(R.id.mine_sorce);
        mine_email = mRootView.findViewById(R.id.mine_email);
        user_city = mRootView.findViewById(R.id.user_city);
        mine_school = mRootView.findViewById(R.id.mine_school);
        mine_qq = mRootView.findViewById(R.id.mine_qq);
        mine_login.setOnClickListener(this);

        mUserBean = InitManager.getInstance().getUserInfo();
        if(mUserBean !=null){
            Logger.d(TAG,"本地保存的用户信息："+ mUserBean.toString());
            updataLoginUser(mUserBean);
        }

        return mRootView;
    }

    /**
     * 更新登录
     * @param UserBean 用户信息
     */
    public void updataLoginUser(final UserBean UserBean) {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ImageLoader.getInstance().displayImage(UserBean.user_picture_url,mine_userimage);
                mine_user_info_ln.setVisibility(View.VISIBLE);
                mine_nickname.setText(""+ UserBean.user_nickname);
                mine_username.setText(""+UserBean.user_name);
                mine_sex_icon.setVisibility(View.VISIBLE);
                if(UserBean.user_gendar == UserBean.USER_MALE){
                    mine_sex_icon.setImageDrawable(getResources().getDrawable(R.drawable.gender_male));
                }else if(UserBean.user_gendar == UserBean.USER_FEMALE){
                    mine_sex_icon.setImageDrawable(getResources().getDrawable(R.drawable.gender_female));
                }else{
                    mine_sex_icon.setVisibility(View.GONE);
                }
                mine_addtime.setText("注册时间："+UserBean.user_register_time);
                unlogin_hint_ln.setVisibility(View.GONE);
                mine_user_infos.setVisibility(View.VISIBLE);
                mine_sorce.setText(UserBean.user_integral +"分");
                mine_email.setText(UserBean.user_email);
                user_city.setText(UserBean.user_city);
                mine_school.setText(UserBean.user_university);
                mine_qq.setText(UserBean.user_qq+"");
            }
        });
    }

    /**
     * 注销
     */
    public void writtenOfflogin(){
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mine_userimage.setImageDrawable(getResources().getDrawable(R.drawable.icon_user_left_normal));
                mine_user_info_ln.setVisibility(View.GONE);
                mine_nickname.setText("");
                mine_username.setText("");
                mine_sex_icon.setVisibility(View.GONE);
                mine_addtime.setText("注册时间：");
                unlogin_hint_ln.setVisibility(View.VISIBLE);
                mine_user_infos.setVisibility(View.GONE);
                mine_sorce.setText("");
                mine_email.setText("");
                user_city.setText("");
                mine_school.setText("");
                mine_qq.setText("");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mine_login:
                LoginActivity.startForResult(mActivity,mActivity.LOGIN_REQUEST_CODE);
                break;
        }
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
