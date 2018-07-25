package com.studytree.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.studytree.InitManager;
import com.studytree.R;
import com.studytree.bean.PictureBean;
import com.studytree.bean.UserBean;
import com.studytree.commonfile.Constants;
import com.studytree.http.HttpResultCallback;
import com.studytree.http.logic.AccountLogic;
import com.studytree.log.Logger;
import com.studytree.utils.StringUtils;
import com.studytree.utils.StudyTreeTools;
import com.studytree.view.base.BaseActivity;
import com.studytree.view.widget.StudyTreeTitleBar;

/**
 * 登录Activity
 * Title: LoginActivity
 * @date 2018/7/19 18:28
 * @author Freedom0013
 */
public class LoginActivity extends BaseActivity implements StudyTreeTitleBar.TitleBarClickListener,View.OnClickListener {
    public static final String TAG = LoginActivity.class.getSimpleName();
    /** 电话 */
    private EditText phone_phone_edit;
    /** 密码 */
    private EditText phone_password_edit;
    /** 登录按钮 */
    private Button do_login;
    /** 注册按钮 */
    private TextView register_enter;
    /** 上级页请求码 */
    private static int mRequestCode;
    /** Gson对象 */
    private Gson gson;

    /**
     * 启动LoginActivity
     * @param ctx 来源Context
     */
    public static void start(Context ctx) {
        Intent intent = new Intent(ctx, LoginActivity.class);
        ctx.startActivity(intent);
    }

    /**
     * 启动LoginActivity
     * @param ctx 来源Activity
     * @param requestCode 结果码
     */
    public static void startForResult(Activity ctx, int requestCode){
        Intent intent = new Intent(ctx,LoginActivity.class);
        intent.putExtra("requestResult",true);
        mRequestCode = requestCode;
        ctx.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        layoutParams.height = StudyTreeTools.getStatusBarHeight(LoginActivity.this);
        statusBar.setAlpha(0.95f);

        //配置toolBar
        StudyTreeTitleBar login_tool = findViewById(R.id.login_tool);
        login_tool.setTitleRightVisibility(false);
        login_tool.setLeftDrawable(R.drawable.titlebar_back);
        login_tool.setTitle("用户登录");
        login_tool.setOnTitleBarClickedListener(this);
        //添加系统
        setSupportActionBar(login_tool);
        login_tool.setAlpha(0.95f);

        //初始化输入框
        phone_phone_edit = findViewById(R.id.phone_phone_edit);
        phone_password_edit = findViewById(R.id.phone_password_edit);

        //设置输入框左边图片大小
        Drawable leftDrawable_phone = phone_phone_edit.getCompoundDrawables()[0];
        if (leftDrawable_phone != null) {
            leftDrawable_phone.setBounds(0, 0, 60, 60);
            phone_phone_edit.setCompoundDrawables(leftDrawable_phone, phone_phone_edit.getCompoundDrawables()[1], phone_phone_edit.getCompoundDrawables()[2], phone_phone_edit.getCompoundDrawables()[3]);
        }
        Drawable leftDrawable_edit = phone_password_edit.getCompoundDrawables()[0];
        if (leftDrawable_edit != null) {
            leftDrawable_edit.setBounds(0, 0, 60, 60);
            phone_password_edit.setCompoundDrawables(leftDrawable_edit, phone_password_edit.getCompoundDrawables()[1], phone_password_edit.getCompoundDrawables()[2], phone_password_edit.getCompoundDrawables()[3]);
        }

        //登录按钮
        do_login = findViewById(R.id.do_login);
        register_enter = findViewById(R.id.register_enter);
        do_login.setOnClickListener(this);
        register_enter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.do_login:             //登录
                submitLogin();
                break;

            case R.id.register_enter:          //注册

                break;
        }
    }

    /**
     * 提交登录
     */
    private void submitLogin() {
        final String mobile = phone_phone_edit.getText().toString();
        final String password = phone_password_edit.getText().toString();

        if(StringUtils.isNullOrEmpty(mobile)){
            showToast("请输入手机号");
            return;
        }else if(!StringUtils.isMobile(mobile)){
            showToast("请输入正确格式的手机号");
            return;
        }

        if(StringUtils.isNullOrEmpty(password)){
            showToast("请输入密码");
            return;
        }

        showProgressDialog();
        AccountLogic.getInstance().login(mobile, password, new HttpResultCallback() {
            @Override
            public void onSuccess(int action, Object obj) {
                initLogin((String) obj);
            }

            @Override
            public void onFail(int action, int responseCode, String responseMsg) {
                Logger.e(TAG, "登录失败！responseCode = " + responseCode + "responseMsg = " + responseMsg);
                showToast("登录失败！请检查网络");
                dismissProgressDialog();
            }
        });
    }

    /**
     * 解析登录数据
     * @param dataStr Json
     */
    private void initLogin(String dataStr) {
        if (StringUtils.isNullOrEmpty(dataStr)) {
            showToast("网络异常！请重试！");
            dismissProgressDialog();
            return;
        }

        if (dataStr != null) {
            try {
                //解析Json
                JsonObject data = new JsonParser().parse(dataStr).getAsJsonObject();
                gson = new Gson();
                int responseCode = data.get("responseCode").getAsInt();

                if (responseCode == 0) {
                    JsonArray dataArray = data.getAsJsonArray("data");
                    //解析数据
                    JsonElement userinfo = dataArray.get(0);
                    JsonObject userinfos = new JsonParser().parse(userinfo.toString()).getAsJsonObject();
                    JsonElement userdata = userinfos.getAsJsonObject("userdata");
                    UserBean userbean = gson.fromJson(userdata, UserBean.class);

                    //解析数据
                    JsonElement picinfo = dataArray.get(1);
                    JsonObject picinfos = new JsonParser().parse(picinfo.toString()).getAsJsonObject();
                    JsonElement picdata = picinfos.getAsJsonObject("pic");
                    PictureBean picture = gson.fromJson(picdata, PictureBean.class);
                    userbean.user_picture_url = "http://" + Constants.HOST + "/" + picture.picture_img;

                    doLoginSuccess(userbean, data);
                } else {
                    doLoginFail(responseCode, data);
                }
            } catch (Exception e) {
                Logger.e(TAG, "登录解析错误！", e);
                showToast("登录解析错误！");
                dismissProgressDialog();
            }
        }
    }

    /**
     * 登录失败
     * @param responseCode 状态码
     * @param data 失败信息
     */
    private void doLoginFail(int responseCode, JsonObject data) {
        try {
            JsonArray dataArray = data.getAsJsonArray("data");
            JsonElement errorinfo = dataArray.get(0);
            JsonObject errorinfos = new JsonParser().parse(errorinfo.toString()).getAsJsonObject();
            String errormessage = errorinfos.get("errormessage").getAsString();
            dismissProgressDialog();
            showToast(errormessage);
        } catch (Exception e) {
            Logger.e(TAG, "登录解析错误！", e);
            showToast("登录解析错误！");
            dismissProgressDialog();
        }
    }

    /**
     * 登录成功
     * @param userbean 用户Bean
     * @param userJson 用户Json
     */
    private void doLoginSuccess(UserBean userbean, JsonObject userJson) {
        dismissProgressDialog();
        InitManager.getInstance().setUserInfo(userbean, userJson);
        InitManager.getInstance().savePhoneAndPasswordToPrefs(userbean.user_name, userbean.user_password);
        Intent intent = new Intent();
        intent.putExtra("userbean", userbean);
        LoginActivity.this.setResult(mRequestCode, intent);
        LoginActivity.this.finish();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
