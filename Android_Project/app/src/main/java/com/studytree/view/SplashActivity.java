package com.studytree.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.studytree.InitManager;
import com.studytree.R;
import com.studytree.bean.InitBean;
import com.studytree.commonfile.Constants;
import com.studytree.http.HttpResultCallback;
import com.studytree.http.logic.InitLogic;
import com.studytree.http.logic.download.DownloadListener;
import com.studytree.http.logic.download.DownloadProgressManager;
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
    /** 标题logo根节点 */
    private LinearLayout splash_logo_root;
    /** 更新信息 */
    private InitBean initbean;
    /** 闪屏页广告 */
    private ImageView splash_logo;
    /** 闪屏页文字行1 */
    private ImageView splash_textline1;
    /** 闪屏页文字行2 */
    private ImageView splash_textline2;

    private static final int CODE_MUST_UPDATE_DIALOG = 1;
    private static final int CODE_UPDATE_DIALOG = 2;
    private static final int CODE_ENTER_HOME = 3;
    private static final int CODE_ANIMATION_END = 8;

    private Handler mhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case CODE_UPDATE_DIALOG:            //有app更新
                    showUpdataDialog(initbean);
                    break;
                case CODE_ENTER_HOME:               //进入下一步
                    enterNextStep();
                    break;
                case CODE_ANIMATION_END:            //

                    break;
                default:

                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //去除ActionBar和界面占满屏幕
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        analysisAutoLogin();
        initView();
    }

    /** 初始化界面 */
    @SuppressLint("WrongViewCast")
    private void initView() {
        splash_advert_image = findViewById(R.id.splash_advert_image);
        splash_advert_image.setBackgroundResource(R.drawable.ic_default_splash);
        splash_logo_root = findViewById(R.id.splash_logo_root);
        splash_logo = findViewById(R.id.splash_logo);
        splash_logo.setBackgroundResource(R.drawable.logo);
        splash_textline1 = findViewById(R.id.splash_textline1);
        splash_textline1.setBackgroundResource(R.drawable.splash_text_line1);
        splash_textline2 = findViewById(R.id.splash_textline2);
        splash_textline2.setBackgroundResource(R.drawable.splash_text_line2);

        //动画集合
        AnimationSet animationSet = new AnimationSet(false);
        //渐变动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.2f,1.0f);
        alphaAnimation.setDuration(1000);
        //缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.7f,1.0f,0.7f,1.0f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(400);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(scaleAnimation);
        //设置动画监听
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                checkingUpdata();
            }
        });
        splash_advert_image.startAnimation(animationSet);
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
                JsonObject data = new JsonParser().parse(obj.toString()+"").getAsJsonObject();
                JsonObject info = data.get("data").getAsJsonObject();
                initbean = new InitBean(info);
                //更新逻辑
                Message message = Message.obtain();
                if(initbean.updata_visionCode > DevicesUtils.getVersionCode()){     //有版本更新
                    message.what = CODE_UPDATE_DIALOG;
                } else {    //没有版本更新
                    message.what = CODE_ENTER_HOME;
                }
                mhandler.sendMessage(message);
            }

            @Override
            public void onFail(int action, int responseCode, String responseMsg) {
                Logger.d(TAG,"接口请求失败！responseMsg = "+responseMsg);
                showToast("网络异常！接口请求失败！");
                //失败依旧可以进入下一步
                Message message = Message.obtain();
                message.what = CODE_ENTER_HOME;
                mhandler.sendMessage(message);
            }
        });
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
                PermissionConfig.PERMISSION_WRITE_EXTERNAL_STORAGE,
        };
        List<String> permissionslist = super.permissionsutils.hasPermissionsAllGranted(permissions, SplashActivity.this);
        if (permissionslist.size() != 0) {
            //调用BaseActivity检查权限工具类
            super.permissionsutils.Requestpermission(permissions, PermissionConfig.REQUEST_SOME_PERMISSIONS, "需要请求一些必要权限！", SplashActivity.this);
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
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.permissionsutils.handleSingleRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    /**
     * 弹出正常更新Dialog
     * @param initbean 更新信息
     */
    public void showUpdataDialog(InitBean initbean) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //填充内容
        builder.setTitle("发现新版本：" + initbean.updata_title);
        if (initbean.UpdataMessage != null && initbean.UpdataMessage.size() >= 0) {
            StringBuffer msgstr = new StringBuffer();
            msgstr.append("更新内容如下：\n");
            for (int i = 0; i < initbean.UpdataMessage.size(); i++) {
                msgstr.append((i+1) + ".");
                msgstr.append(initbean.UpdataMessage.get(i));
                msgstr.append("\n");
            }
            builder.setMessage(msgstr.toString());
            msgstr.delete(0, msgstr.length());
        } else {
            builder.setMessage("欢迎更新至最新版本！");
        }
        //立即更新
        builder.setPositiveButton("立即更新！", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new DownloadProgressManager(SplashActivity.this,new DownloadListener(){
                    @Override
                    public void onDownloadFinished(boolean success) {

                    }
                }).showDownloadDialog(Constants.DOWNLOAD_APP_NAME,Constants.DOWNLOAD_URL);
            }
        });
        //以后再说（如果为强制更新，无此选项）
        if (!initbean.ismust_updata_flag) {
            builder.setNegativeButton("以后再说", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Message message = Message.obtain();
                    message.what = CODE_ENTER_HOME;
                    mhandler.sendMessage(message);
                }
            });

            //Dialog取消按键监听
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {

                }
            });
        } else {
            builder.setCancelable(false);   //禁用返回键，尽量不用（强制更新）
        }
        builder.setCancelable(false);
        builder.show();
    }

    /**
     * 页面跳转
     */
    private void enterNextStep(){
        boolean introShowed = InitManager.getInstance().getBooleanPreference(Constants.PREF_INTRO);
        if(introShowed){
            MainActivity.start(SplashActivity.this);
            finish();
        }else{
            IntroActivity.start(SplashActivity.this);
            finish();
        }
    }
}
