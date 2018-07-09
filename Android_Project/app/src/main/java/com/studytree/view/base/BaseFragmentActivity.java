package com.studytree.view.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.studytree.ActivityCleanupStack;
import com.studytree.log.Logger;
import com.studytree.view.widget.LoadingDialog;
import com.umeng.analytics.MobclickAgent;

/**
 * FragmentActivity基类
 * Title: BaseFragmentActivity
 * @date 2018/6/23 19:49
 * @author Freedom0013
 */
public class BaseFragmentActivity extends FragmentActivity {
    public static final String TAG = BaseFragmentActivity.class.getSimpleName();
    /** 加载进度圈 */
    private LoadingDialog mLoadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //压栈
        ActivityCleanupStack.push(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void finish() {
        super.finish();
        //弹栈
        ActivityCleanupStack.pop(this.getClass());
        super.finish();
        System.gc();
    }

    /**
     * 显示String Toast
     * @param s Toast信息
     */
    public void showToast(final String s){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseFragmentActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 显示Loading进度条
     */
    public void showProgressDialog(){
        if (isFinishing()) {
            return;
        }
        mLoadingDialog = LoadingDialog.showDialog(this);
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
}
