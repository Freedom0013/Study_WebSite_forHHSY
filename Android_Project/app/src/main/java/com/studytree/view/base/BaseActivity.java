package com.studytree.view.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.studytree.ActivityCleanupStack;
import com.studytree.log.Logger;
import com.studytree.utils.DevicesUtils;
import com.studytree.utils.permissions.PermissionConfig;
import com.studytree.utils.permissions.PermissionUtils;
import com.studytree.utils.permissions.RequestPermissionListener;
import com.studytree.view.widget.LoadingDialog;
import com.umeng.analytics.MobclickAgent;

/**
 * Activity基类
 * Title: BaseActivity
 * @date 2018/6/16 15:26
 * @author Freedom0013
 */
public class BaseActivity extends AppCompatActivity {
    public static final String TAG = BaseActivity.class.getSimpleName();
    /** 加载进度圈 */
    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉AppCompatActivity标题栏自定义
//        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Activity压栈
        ActivityCleanupStack.push(this);
    }

    /**
     * 权限请求监听器
     */
    public RequestPermissionListener mPermisssionListener = new RequestPermissionListener() {
        @Override
        public void onPermissionPass(int requestCode) {
            Logger.w(TAG,"已有权限");
        }

        @Override
        public void onPermissionAccreditSucceed(int requestCode) {
            Logger.w(TAG,"权限获取成功");
            showToast("权限获取成功");
            //获取设备信息
            DevicesUtils.initDevicesInfos();
        }

        @Override
        public void onPermissionAccreditFailed(int requestCode, final String PermissionName) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //判断用户是否点击不在提示按钮，true：未选中，false：选中
                boolean isTip = ActivityCompat.shouldShowRequestPermissionRationale(BaseActivity.this,PermissionName);
                if (isTip) {//表明用户没有彻底禁止弹出权限请求
//                    requestPermission(PermissionHelper.getInstance().filterPermissions(permissions));
                } else {//表明用户已经彻底禁止弹出权限请求
                    Logger.e(TAG, "==========警告！用户拒绝并不在提示权限==========permission = " + PermissionName);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            permissionsutils.popPermissionAlterDialog("用户您好我们需要必要的权限来保证应用的正常运行！\n" +
                                    "未授权权限为：" + PermissionConfig.getPermissionNameToString(PermissionName) + "。", BaseActivity.this);
                        }
                    });
                }
            }
            showToast("权限获取失败");
        }
    };

    /**
     * 权限检查类
     */
    public PermissionUtils permissionsutils = new PermissionUtils(BaseActivity.this,mPermisssionListener);

    /**
     * 显示Loading进度条
     */
    public void showProgressDialog(){
        if (isFinishing()) {
            return;
        }
        mLoadingDialog = LoadingDialog.showDialog(BaseActivity.this);
        mLoadingDialog.show();
    }

    /**
     * 关闭Loading进度条
     */
    public void dismissProgressDialog(){
        try {
            if(mLoadingDialog != null){
                if(mLoadingDialog.isShowing()){
                    mLoadingDialog.dismiss();
                }
                mLoadingDialog = null;
            }
        } catch (Exception e) {
            Logger.e(TAG,"loading进度圈关闭错误！",e);
        }
    }

    @Override
    public void finish() {
        //Activity弹栈
        ActivityCleanupStack.pop(this.getClass());
        //结束Activity
        super.finish();
        //垃圾回收
        System.gc();
    }

    /**
     * 隐藏软件盘
     */
    public void hideSoftKeyboard() {
        try {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            Logger.e(TAG,"键盘隐藏异常！",e);
        }
    }

    /**
     * 显示软键盘
     * @param target 需要显示的EditText控件
     */
    public void showSoftKeyboard(EditText target) {
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInputFromWindow(target.getWindowToken(), 0, InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            Logger.e(TAG,"键盘显示异常！",e);
        }
    }

    /**
     * 弹出String Toast
     * @param s 要弹出的信息
     */
    public void showToast(final String s){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
