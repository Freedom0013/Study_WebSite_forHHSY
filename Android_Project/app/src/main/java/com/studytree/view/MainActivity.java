package com.studytree.view;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.studytree.R;
import com.studytree.log.Logger;
import com.studytree.utils.permissions.PermissionUtils;
import com.studytree.utils.permissions.PermissionConfig;
import com.studytree.utils.permissions.RequestPermissionListener;


/**
 * 主页Activity
 * AppCompatActivity继承自ActionBarActivity
 * Title: MainActivity
 * @date 2018/6/12 19:16
 * @author Freedom0013
 */
public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    PermissionUtils permissionsutils = new PermissionUtils(MainActivity.this, new RequestPermissionListener() {
        @Override
        public void onPermissionPass(int requestCode) {
            Logger.w(TAG,"已有权限");
        }

        @Override
        public void onPermissionAccreditSucceed(int requestCode) {
            Logger.w(TAG,"权限获取成功");
        }

        @Override
        public void onPermissionAccreditFailed(int requestCode, String PermissionName) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //判断用户是否点击不在提示按钮，true：未选中，false：选中
                boolean isTip = ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,PermissionName);
                if (isTip) {//表明用户没有彻底禁止弹出权限请求
//                    requestPermission(PermissionHelper.getInstance().filterPermissions(permissions));
                } else {//表明用户已经彻底禁止弹出权限请求
                    Logger.e(TAG, "==========警告！用户拒绝并不在提示权限==========permission = " + PermissionName);
                    permissionsutils.popPermissionAlterDialog("我们需要必要的权限来保证应用的正常运行！", MainActivity.this);
                    finish();
                }
            }
            Logger.e(TAG, "权限获取失败");
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!permissionsutils.hasPermissionGranted(PermissionConfig.PERMISSION_WRITE_EXTERNAL_STORAGE,MainActivity.this)){
            Logger.i(TAG,"没有 = "+PermissionConfig.PERMISSION_WRITE_EXTERNAL_STORAGE+"权限");
            permissionsutils.Requestpermission(PermissionConfig.PERMISSION_WRITE_EXTERNAL_STORAGE,PermissionConfig.REQUEST_WRITE_EXTERNAL_STORAGE,"需要请求修改内部储存权限！",MainActivity.this);
        }
    }

    /**
     * 用户权限授权回调
     * @param requestCode 请求码
     * @param permissions 权限列表
     * @param grantResults 授权结果码
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        permissionsutils.handleSingleRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}