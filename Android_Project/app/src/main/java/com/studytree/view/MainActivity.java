package com.studytree.view;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.studytree.R;
import com.studytree.log.Logger;
import com.studytree.utils.PermisionUtils;

/**
 * 主页Activity
 * Title: MainActivity
 * @date 2018/6/12 19:16
 * @author Freedom0013
 */
public class MainActivity extends AppCompatActivity {
    /*
     * AppCompatActivity继承自ActionBarActivity
     */

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.w(TAG,"Log日志打印！");
        PermisionUtils.verifyStoragePermissions(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
