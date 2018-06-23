package com.studytree.view;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;

import com.studytree.R;
import com.studytree.http.HttpResultCallback;
import com.studytree.http.logic.InitLogic;
import com.studytree.log.Logger;
import com.studytree.utils.permissions.PermissionConfig;
import com.studytree.view.base.BaseActivity;

import java.util.List;


/**
 * 主页Activity
 * AppCompatActivity继承自ActionBarActivity
 * Title: MainActivity
 * @date 2018/6/12 19:16
 * @author Freedom0013
 */
public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    /**
     * 启动MainActivity
     * @param ctx 来源Context
     */
    public static void start(Context ctx){
        Intent intent = new Intent(ctx,MainActivity.class);
//        intent.putExtra("键",值);
        ctx.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //权限组检查
        String[] permissions = { PermissionConfig.PERMISSION_READ_PHONE_STATE,
                                 PermissionConfig.PERMISSION_WRITE_EXTERNAL_STORAGE };
        List<String> permissionslist = super.permissionsutils.hasPermissionsAllGranted(permissions, MainActivity.this);
        if (permissionslist.size() != 0) {
                //调用BaseActivity检查权限工具类
                super.permissionsutils.Requestpermission(permissions, PermissionConfig.REQUEST_SOME_PERMISSIONS, "需要请求一些必要权限！", MainActivity.this);
        }

        //网络请求
        InitLogic initLogic = InitLogic.getInstance();
        initLogic.getUpdataInfo(new HttpResultCallback() {
            @Override
            public void onSuccess(int action, Object obj) {
                Logger.d(TAG,"接口请求成功！obj = "+obj.toString());
            }

            @Override
            public void onFail(int action, int responseCode, String responseMsg) {
                Logger.d(TAG,"接口请求失败！responseMsg = "+responseMsg);
            }
        });
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
        //交给Base处理
        super.permissionsutils.handleSingleRequestPermissionsResult(requestCode,permissions,grantResults);
    }

//    public static void startForResult(Activity ctx){
//        Intent intent = new Intent(ctx,MainActivity.class);
//        intent.putExtra("requestResult",true);
//        ctx.startActivityForResult(intent, 0);
//    }
}