package com.studytree.view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.studytree.R;
import com.studytree.utils.StudyTreeTools;
import com.studytree.view.base.BaseActivity;
import com.studytree.view.widget.StudyTreeTitleBar;

/**
 * 登录Activity
 * Title: LoginActivity
 * @date 2018/7/19 18:28
 * @author Freedom0013
 */
public class LoginActivity extends BaseActivity implements StudyTreeTitleBar.TitleBarClickListener {
    public static final String TAG = LoginActivity.class.getSimpleName();

    /**
     * LoginActivity
     * @param ctx 来源Context
     */
    public static void start(Context ctx) {
        Intent intent = new Intent(ctx, LoginActivity.class);
        ctx.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //沉浸
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        initView();
    }

    private void initView() {
        //设置占位View以实现沉浸式状态栏
        View statusBar = findViewById(R.id.statusBarView);
        ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        layoutParams.height = StudyTreeTools.getStatusBarHeight(LoginActivity.this);

        //配置toolBar
        StudyTreeTitleBar login_tool = findViewById(R.id.login_tool);
        login_tool.setTitleRightVisibility(false);
        login_tool.setLeftDrawable(R.drawable.titlebar_back);
        login_tool.setTitle("用户登录");
        login_tool.setOnTitleBarClickedListener(this);
        //添加系统
        setSupportActionBar(login_tool);
    }

    @Override
    public void onTitleLeftClicked() {
        finish();
    }

    @Override
    public void onTitleRightClicked() {

    }

    @Override
    public void onTitleClicked() {

    }
}
