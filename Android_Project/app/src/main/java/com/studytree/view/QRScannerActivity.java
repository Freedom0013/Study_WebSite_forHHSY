package com.studytree.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.google.zxing.Result;
import com.google.zxing.client.result.ParsedResult;
import com.mylhyl.zxing.scanner.OnScannerCompletionListener;
import com.mylhyl.zxing.scanner.ScannerView;
import com.studytree.R;
import com.studytree.log.Logger;
import com.studytree.utils.StudyTreeTools;
import com.studytree.utils.permissions.PermissionConfig;
import com.studytree.view.base.BaseActivity;
import com.studytree.view.widget.StudyTreeTitleBar;

/**
 * 二维码扫描Activity
 * Title: QRScannerActivity
 * @date 2018/7/19 21:26
 * @author Freedom0013
 */
public class QRScannerActivity extends BaseActivity implements StudyTreeTitleBar.TitleBarClickListener,OnScannerCompletionListener{
    public static final String TAG = QRScannerActivity.class.getSimpleName();
    /** 二维码扫描View */
    private ScannerView mScannerView;
    /** 闪光灯开关 */
    private boolean isLightOpen = false;
    /** 扫一扫结果码 */
    private static int mRequestCode;
    /** 用户点击了不再提示权限 */
    public final static String QR_PERMISSION_ERROR = "error";

    /**
     * 启动QRScannerActivity
     * @param ctx 来源Activity
     * @param requestCode 结果码
     */
    public static void startForResult(Activity ctx,int requestCode){
        Intent intent = new Intent(ctx,QRScannerActivity.class);
        intent.putExtra("requestResult",true);
        mRequestCode = requestCode;
        ctx.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scanner);

        preScan();
        //沉浸
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        initView();
    }

    /** 检查权限 */
    private void preScan() {
        boolean isHaspermission = super.permissionsutils.hasPermissionGranted(PermissionConfig.PERMISSION_CAMERA, QRScannerActivity.this);
        if (!isHaspermission) {
            //调用BaseActivity检查权限工具类
            super.permissionsutils.Requestpermission(PermissionConfig.PERMISSION_CAMERA, PermissionConfig.REQUEST_CAMERA, "使用扫一扫需要摄像头权限！", QRScannerActivity.this);
        }
    }

    //权限请求失败
    @Override
    public void PermissionAccreditFailed(int requestCode, String PermissionName) {
        super.PermissionAccreditFailed(requestCode, PermissionName);
        showToast("请授予" + PermissionConfig.getPermissionToString(requestCode) + "权限！");
        QRScannerActivity.this.finish();
    }

    //用户点击了不再提示！
    @Override
    public void RequestPermissionRationale(int requestCode, String PermissionName) {
        Intent intent = new Intent();
        intent.putExtra("qu_result",QR_PERMISSION_ERROR);
        intent.putExtra("PermissionName",PermissionName);
        QRScannerActivity.this.setResult(mRequestCode, intent);
        QRScannerActivity.this.finish();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        //设置占位View以实现沉浸式状态栏
        View statusBar = findViewById(R.id.statusBarView);
        ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        layoutParams.height = StudyTreeTools.getStatusBarHeight(QRScannerActivity.this);

        //配置toolBar
        StudyTreeTitleBar scanner_tool = findViewById(R.id.scanner_tool);
        scanner_tool.setTitleRightVisibility(true);
        scanner_tool.setRightDrawable(R.drawable.icon_scanner_light);
        scanner_tool.setLeftDrawable(R.drawable.titlebar_back);
        scanner_tool.setTitle("扫一扫");
        scanner_tool.setOnTitleBarClickedListener(this);
        //添加系统
        setSupportActionBar(scanner_tool);

        //初始化扫描控件
        mScannerView = findViewById(R.id.scanner_view);
        mScannerView.setOnScannerCompletionListener(this);
    }

    //扫描结果监听
    @Override
    public void onScannerCompletion(Result rawResult, ParsedResult parsedResult, Bitmap barcode) {
        Logger.d(TAG,rawResult.getText());
        Intent intent = new Intent();
        intent.putExtra("qu_result",rawResult.getText());
        QRScannerActivity.this.setResult(mRequestCode, intent);
        QRScannerActivity.this.finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.permissionsutils.handleSingleRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onResume() {
        mScannerView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mScannerView.onPause();
        super.onPause();
    }

    @Override
    public void onTitleLeftClicked() {
        finish();
    }

    @Override
    public void onTitleRightClicked() {
        //闪光灯
        mScannerView.toggleLight(isLightOpen);
        isLightOpen = !isLightOpen;
    }

    @Override
    public void onTitleClicked() {

    }
}
