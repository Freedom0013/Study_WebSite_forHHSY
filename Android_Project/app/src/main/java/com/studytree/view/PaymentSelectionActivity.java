package com.studytree.view;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.studytree.R;
import com.studytree.utils.StudyTreeTools;
import com.studytree.view.base.BaseActivity;
import com.studytree.view.widget.PayRadioButton;
import com.studytree.view.widget.StudyTreeTitleBar;

import java.util.List;

/**
 * 支付选择页
 * Title: PaymentSelectionActivity
 * @author Freedom0013
 * @date 2018/7/30 20:38
 */
public class PaymentSelectionActivity extends BaseActivity implements StudyTreeTitleBar.TitleBarClickListener,View.OnClickListener{
    private static final String TAG = PaymentSelectionActivity.class.getSimpleName();
    private static final int PAY_ITEM_ALIPAY = 0;
    private static final int PAY_ITEM_WECHAT = 1;
    private static int mRequestCode;
    //初始化控件
    private LinearLayout total;
    private TextView payment_selection_total_money;
    private LinearLayout discount;
    private TextView payment_selection_discount;
    private LinearLayout total_pay;
    private TextView payment_selection_total_pay;
    private RadioGroup pay_type_group;
    private PayRadioButton pay_type_alipay;
    private PayRadioButton pay_type_wechat;
    private PayRadioButton pay_type_test;
    private Button pay_selection_submit;

    /** 获取到订单号后是否执行paysuccess接口 */
    private boolean autoPaySuccessAfterGetUserId = false;
    /** 点击支付通道的标示 预约返回后根据此标示判断支付渠道  0支付宝 1微信  */
    private int continueItemType  = -1;
    private boolean  running = false;

    /**
     * 启动PaymentSelectionActivity
     * @param ctx 来源Activity
     * @param requestCode 请求码
     */
    public static void startForResult(Activity ctx, int requestCode) {
        Intent intent = new Intent(ctx, PaymentSelectionActivity.class);
        intent.putExtra("requestResult", true);
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

        total = findViewById(R.id.total);
        payment_selection_total_money = findViewById(R.id.payment_selection_total_money);
        discount = findViewById(R.id.discount);
        payment_selection_discount = findViewById(R.id.payment_selection_discount);
        total_pay = findViewById(R.id.total_pay);
        payment_selection_total_pay = findViewById(R.id.payment_selection_total_pay);
        pay_type_group = findViewById(R.id.pay_type_group);
        pay_type_alipay = findViewById(R.id.pay_type_alipay);
        pay_type_wechat = findViewById(R.id.pay_type_wechat);
        pay_type_test = findViewById(R.id.pay_type_test);
        pay_selection_submit = findViewById(R.id.pay_selection_submit);

        payment_selection_total_money.setText(15+"");
        payment_selection_discount.setVisibility(View.INVISIBLE);
        payment_selection_total_pay.setText(15+"");

        discount.setOnClickListener(this);
        pay_selection_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case R.id.discount:          //优惠券

               break;

            case R.id.pay_selection_submit:         //提交
                if (running) {
                    return;
                }
                preparePayment();
                break;
        }
    }

    private void preparePayment() {
        //判断选择的支付方式
        int checkedPayId = pay_type_group.getCheckedRadioButtonId();
        if (checkedPayId == R.id.pay_type_alipay) {
            if (!isAlipayInstalled()) {
                showToast("请先安装支付宝客户端");
            } else {
                continueItemType = PAY_ITEM_ALIPAY;
//                autoPaySuccessAfterGetUserId = false;
                doPayment(continueItemType, autoPaySuccessAfterGetUserId);
            }
        } else if (checkedPayId == R.id.pay_type_wechat) {
            if (!isWechatInstalled()) {
                showToast("请先安装微信客户端");
            } else {
                continueItemType = PAY_ITEM_WECHAT;
//                autoPaySuccessAfterGetUserId = false;
                doPayment(continueItemType, autoPaySuccessAfterGetUserId);
            }
        } else {
//            autoPaySuccessAfterGetUserId = true;
            doPayment(continueItemType, autoPaySuccessAfterGetUserId);
        }
    }

    private void doPayment(int continueItemType, boolean autoPaySuccessAfterGetUserId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(PaymentSelectionActivity.this.continueItemType == PAY_ITEM_ALIPAY){
//                    mAlipay = new AlipayEntrance(PaymentSelectionActivity.this,"知识森驿站会员费","知识森驿站会员费",mReserveId,couponBean == null? "":couponBean.getId());
//                    mAlipay.setAlipayCallback(PaymentSelectionActivity.this);
//                    mAlipay.startPay();
                }else if(PaymentSelectionActivity.this.continueItemType == PAY_ITEM_WECHAT){
//                    mWxpay = new WXPayEntrance(PaymentSelectionActivity.this,mReserveId,couponBean == null? "":couponBean.getId());
//                    mWxpay.startPay();
                }
            }
        });
    }

    /**
     * 检查微信是否安装
     * @return 结果
     */
    private boolean isWechatInstalled() {
        String WeChatPkg = "com.tencent.mm";
        boolean isInstalled = false;
        List<PackageInfo> packageInfos = getPackageManager().getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
        for (int i = 0; i < packageInfos.size(); i++) {
            PackageInfo temp = packageInfos.get(i);
            if (temp.packageName.equals(WeChatPkg)) {
                isInstalled = true;
                break;
            }
        }
        return isInstalled;
    }

    /**
     * 检查支付宝是否安装
     * @return 结果
     */
    private boolean isAlipayInstalled() {
        String AlipayPkg = "com.eg.android.AlipayGphone";
        boolean isInstalled = false;
        List<PackageInfo> packageInfos = getPackageManager().getInstalledPackages(PackageManager.GET_UNINSTALLED_PACKAGES);
        for (int i = 0; i < packageInfos.size(); i++) {
            PackageInfo temp = packageInfos.get(i);
            if (temp.packageName.equals(AlipayPkg)) {
                isInstalled = true;
                break;
            }
        }
        return isInstalled;
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
