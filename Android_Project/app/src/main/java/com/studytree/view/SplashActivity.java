package com.studytree.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.studytree.R;
import com.studytree.bean.InitBean;
import com.studytree.http.HttpResultCallback;
import com.studytree.http.logic.InitLogic;
import com.studytree.log.Logger;
import com.studytree.utils.DevicesUtils;
import com.studytree.utils.permissions.PermissionConfig;
import com.studytree.view.base.BaseActivity;

import java.util.List;

/**
 * 闪屏页
 * Title: SplashActivity
 * @date 2018/6/25 16:59
 * @author Freedom0013
 */
public class SplashActivity extends BaseActivity {
    public static final String TAG = SplashActivity.class.getSimpleName();
    /** 广告ImageView */
    private ImageView splash_advert_image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
        checkingUpdata();
        analysisAutoLogin();
    }

    /**
     * 检查登录状态
     */
    private void analysisAutoLogin() {
        preAutoLogin();
    }

    /**
     * 检查权限
     */
    private void preAutoLogin() {
        //权限组检查
        String[] permissions = {
                PermissionConfig.PERMISSION_READ_PHONE_STATE,
                PermissionConfig.PERMISSION_WRITE_EXTERNAL_STORAGE };
        List<String> permissionslist = super.permissionsutils.hasPermissionsAllGranted(permissions, SplashActivity.this);
        if (permissionslist.size() != 0) {
            //调用BaseActivity检查权限工具类
            super.permissionsutils.Requestpermission(permissions, PermissionConfig.REQUEST_SOME_PERMISSIONS, "需要请求一些必要权限！", SplashActivity.this);
        }
        //获取设备信息
        DevicesUtils.initDevicesInfos();
    }

    /**
     * 检查更新
     */
    private void checkingUpdata() {
        //网络请求
        InitLogic initLogic = InitLogic.getInstance();
        initLogic.getUpdataInfo(new HttpResultCallback() {
            @Override
            public void onSuccess(int action, Object obj) {
                Logger.d(TAG,"接口请求成功！obj = "+obj.toString());
                Gson gson = new Gson();
                JsonObject data = new JsonParser().parse(obj.toString()+"").getAsJsonObject();
                JsonObject info = data.get("data").getAsJsonObject();
                InitBean initbean = new InitBean(info);
                Logger.d(TAG,initbean.toString());
            }

            @Override
            public void onFail(int action, int responseCode, String responseMsg) {
                Logger.d(TAG,"接口请求失败！responseMsg = "+responseMsg);
            }
        });
    }

    /** 初始化界面 */
    private void initView() {
        splash_advert_image = findViewById(R.id.splash_advert_image);
    }

    /**
     * 用户权限授权回调
     * @param requestCode 请求码
     * @param permissions 权限列表
     * @param grantResults 授权结果码
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.permissionsutils.handleSingleRequestPermissionsResult(requestCode,permissions,grantResults);
    }
}
