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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
 * 用户注册
 * Title: RegisterActivity
 * @date 2018/7/26 10:00
 * @author Freedom0013
 */
public class RegisterActivity extends BaseActivity implements StudyTreeTitleBar.TitleBarClickListener,View.OnClickListener {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    /** 上级页请求码 */
    private static int mRequestCode;
    /** 用户名输入框 */
    private EditText register_phone_edit;
    /** 密码输入框 */
    private EditText register_password_edit;
    /** 确认密码输入框 */
    private EditText register_password_admit_edit;
    /** 昵称输入框 */
    private EditText register_nickname_edit;
    /** 注册按钮 */
    private Button do_register;
    /** Gson对象 */
    private Gson gson;

    /**
     * 启动RegisterActivity
     * @param ctx 来源Context
     */
    public static void start(Context ctx) {
        Intent intent = new Intent(ctx, RegisterActivity.class);
        ctx.startActivity(intent);
    }

    /**
     * 启动RegisterActivity
     * @param ctx 来源Activity
     * @param requestCode 请求码
     */
    public static void startForResult(Activity ctx, int requestCode){
        Intent intent = new Intent(ctx,RegisterActivity.class);
        intent.putExtra("requestResult",true);
        mRequestCode = requestCode;
        ctx.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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
        layoutParams.height = StudyTreeTools.getStatusBarHeight(RegisterActivity.this);
        statusBar.setAlpha(0.95f);

        //配置toolBar
        StudyTreeTitleBar register_tool = findViewById(R.id.register_tool);
        register_tool.setTitleRightVisibility(false);
        register_tool.setLeftDrawable(R.drawable.titlebar_back);
        register_tool.setTitle("用户注册");
        register_tool.setOnTitleBarClickedListener(this);
        //添加系统
        setSupportActionBar(register_tool);
        register_tool.setAlpha(0.95f);

        //输入框
        register_phone_edit = findViewById(R.id.register_phone_edit);
        register_password_edit = findViewById(R.id.register_password_edit);
        register_password_admit_edit = findViewById(R.id.register_password_admit_edit);
        register_nickname_edit = findViewById(R.id.register_nickname_edit);

        //格式化小图标
        initEditImage(register_phone_edit);
        initEditImage(register_password_edit);
        initEditImage(register_password_admit_edit);
        initEditImage(register_nickname_edit);

        do_register = findViewById(R.id.do_register);
        do_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.do_register:      //注册
                preRegister();
                break;
        }
    }

    /**
     * 检查注册数据
     */
    private void preRegister() {
        final String mobile = register_phone_edit.getText().toString();
        final String password = register_password_edit.getText().toString();
        final String admit_password = register_password_admit_edit.getText().toString();
        final String nickname = register_nickname_edit.getText().toString();

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
        if(StringUtils.isNullOrEmpty(admit_password)){
            showToast("请输入确认密码");
            return;
        }
        if(!password.equals(admit_password)){
            showToast("请检查两次输入的密码是否相同");
            return;
        }
        if(StringUtils.isNullOrEmpty(nickname)){
            showToast("请输入昵称");
            return;
        }else if(nickname.length()<3){
            showToast("请输入大于三个字符的昵称");
            return;
        }

        UserBean user = new UserBean();
        user.user_name = mobile;
        user.user_password = password;
        user.user_nickname = nickname;

        showProgressDialog();
        AccountLogic.getInstance().regisier(user, new HttpResultCallback() {
            @Override
            public void onSuccess(int action, Object obj) {
                initRegisier((String) obj);
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
     * 解析注册Json
     * @param dataStr Json
     */
    private void initRegisier(String dataStr) {
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

                    doRegisterSuccess(userbean, data);
                } else {
                    doRegisterFail(responseCode, data);
                }
            } catch (Exception e) {
                Logger.e(TAG, "注册解析错误！", e);
                showToast("注册解析错误！");
                dismissProgressDialog();
            }
        }
    }

    /**
     * 注册失败
     * @param responseCode 状态码
     * @param data Json
     */
    private void doRegisterFail(int responseCode, JsonObject data) {
        try {
            JsonArray dataArray = data.getAsJsonArray("data");
            JsonElement errorinfo = dataArray.get(0);
            JsonObject errorinfos = new JsonParser().parse(errorinfo.toString()).getAsJsonObject();
            String errormessage = errorinfos.get("errormessage").getAsString();
            dismissProgressDialog();
            showToast(errormessage);
        } catch (Exception e) {
            Logger.e(TAG, "注册解析错误！", e);
            showToast("注册解析错误！");
            dismissProgressDialog();
        }
    }

    /**
     * 注册成功
     * @param userbean 用户Bean
     * @param data 数据
     */
    private void doRegisterSuccess(UserBean userbean, JsonObject data) {
        Intent intent = new Intent();
        intent.putExtra("userbean", userbean);
        intent.putExtra("JsonObject", data.toString());
        RegisterActivity.this.setResult(mRequestCode, intent);
        RegisterActivity.this.finish();
    }

    /**
     * 设置输入框左边图片大小
     * @param editText 输入框
     */
    private void initEditImage(EditText editText) {
        Drawable leftDrawable = editText.getCompoundDrawables()[0];
        if (leftDrawable != null) {
            leftDrawable.setBounds(0, 0, 60, 60);
            editText.setCompoundDrawables(leftDrawable, editText.getCompoundDrawables()[1], editText.getCompoundDrawables()[2], editText.getCompoundDrawables()[3]);
        }
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
