package com.studytree.view;

import android.Manifest;
import android.content.pm.PackageManager;
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

import java.util.List;

/**
 * 主页Activity
 * Title: MainActivity
 * @date 2018/6/12 19:16
 * @author Freedom0013
 */
public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{
    /*
     * AppCompatActivity继承自ActionBarActivity
     */

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PermissionUtils permissions = new PermissionUtils();
        List<String> list = permissions.isPermissionsAllGranted(PermissionConfig.STORAGE, MainActivity.this);
        if(!(list.isEmpty())){
            for (String str : list) {
                if(str.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    Logger.w(TAG, "============Permission===================" + str);
                    permissions.Requestpermission(PermissionConfig.STORAGE,PermissionConfig.REQUEST_WRITE_EXTERNAL_STORAGE,"需要请求读写内部储存权限！",MainActivity.this);
                }
            }
        }

        permissions.setRequestPermissionListener(new RequestPermissionListener() {
            @Override
            public void onPermissionPass(int requestCode) {
               Toast.makeText(MainActivity.this, PermissionConfig.getPermissionToString(requestCode) + "权限请求失败！", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPermissionAccreditSucceed(int requestCode) {
                Toast.makeText(MainActivity.this, PermissionConfig.getPermissionToString(requestCode) + "权限获取成功！", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onPermissionAccreditFailed(int requestCode) {
                Toast.makeText(MainActivity.this, PermissionConfig.getPermissionToString(requestCode) + "权限请求失败！", Toast.LENGTH_LONG).show();
            }
        });
    }
}
