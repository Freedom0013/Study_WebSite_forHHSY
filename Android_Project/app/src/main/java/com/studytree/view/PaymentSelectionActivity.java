package com.studytree.view;

import android.app.Activity;
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
 * 支付选择页
 * Title: PaymentSelectionActivity
 * @date 2018/7/30 20:38
 * @author Freedom0013
 */
public class PaymentSelectionActivity extends BaseActivity implements StudyTreeTitleBar.TitleBarClickListener {
    private static final String TAG = PaymentSelectionActivity.class.getSimpleName();
    private static int mRequestCode;

    /**
     * 启动PaymentSelectionActivity
     * @param ctx 来源Activity
     * @param requestCode 请求码
     */
    public static void startForResult(Activity ctx, int requestCode){
        Intent intent = new Intent(ctx,PaymentSelectionActivity.class);
        intent.putExtra("requestResult",true);
        mRequestCode = requestCode;
        ctx.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acrivity_paymentselection);

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
        layoutParams.height = StudyTreeTools.getStatusBarHeight(PaymentSelectionActivity.this);

        //配置toolBar
        StudyTreeTitleBar paymentselection_tool = findViewById(R.id.paymentselection_tool);
        paymentselection_tool.setTitleRightVisibility(false);
        paymentselection_tool.setLeftDrawable(R.drawable.titlebar_back);
        paymentselection_tool.setTitle("选择支付");
        paymentselection_tool.setOnTitleBarClickedListener(this);
        //添加系统
        setSupportActionBar(paymentselection_tool);

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
