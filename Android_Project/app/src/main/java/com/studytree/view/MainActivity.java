package com.studytree.view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.WindowManager;

import com.studytree.R;
import com.studytree.bean.UserBean;
import com.studytree.utils.permissions.PermissionConfig;
import com.studytree.view.base.BaseActivity;
import com.studytree.view.fragment.LeftMenuFragment;
import com.studytree.view.fragment.MainFragment;
import com.studytree.view.widget.ResideLayout;

/**
 * 主页Activity
 * AppCompatActivity继承自ActionBarActivity
 * Title: MainActivity
 * @date 2018/6/12 19:16
 * @author Freedom0013
 */
public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    /** 主界面Fragment标签 */
    private static final String FRAGMENT_MAIN = "fragment_main";
    /** 侧滑菜单Fragment标签 */
    private static final String FRAGMENT_LEFT_MENU = "fragment_left_menu";
    /** 侧滑菜单对象 */
    public ResideLayout main_residelayout;
    /** 登录结果码 */
    public static final int LOGIN_REQUEST_CODE = 0;
    /** 扫一扫结果码 */
    public static final int QR_REQUEST_CODE = 1;
    /** 点击两次返回时间间隔 */
    private long exitTime = 0;

    /**
     * 启动MainActivity
     * @param ctx 来源Context
     */
    public static void start(Context ctx) {
        Intent intent = new Intent(ctx, MainActivity.class);
//        intent.putExtra("键",值);
        ctx.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case QR_REQUEST_CODE:
                if(data!=null){                           //当点击不再提示返回时，显示权限提示
                    if(QRScannerActivity.QR_PERMISSION_ERROR.equals(data.getStringExtra("qu_result"))){
                        super.RequestPermissionRationale(PermissionConfig.REQUEST_CAMERA,data.getStringExtra("PermissionName"));
                    }else{                                //扫一扫结果
                        showToast("扫一扫结果：" + data.getStringExtra("qu_result"));
                    }
                }
                break;
            case LOGIN_REQUEST_CODE:                //用户登录回调
                if (data != null) {
                    UserBean bean = (UserBean) data.getSerializableExtra("userbean");
                    if (bean != null) {
                        showToast("登录成功！");
                        getLeftMenuFragment().updataLoginUser(bean);
                    }
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //去除ActionBar
//        getSupportActionBar().hide();
        //沉浸
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        initView();
    }

    /** 初始化界面 */
    private void initView() {
        main_residelayout = findViewById(R.id.main_residelayout);

        FragmentManager fm = getSupportFragmentManager();
        // 开启事务
        FragmentTransaction transaction = fm.beginTransaction();
        // 用fragment替换framelayout
        transaction.replace(R.id.menu, new LeftMenuFragment(MainActivity.this), FRAGMENT_LEFT_MENU);
        transaction.replace(R.id.main_fram, new MainFragment(MainActivity.this), FRAGMENT_MAIN);
        transaction.commit();// 提交事务
    }

    /**
     * 获取侧边菜单Fragment
     * @return LeftMenuFragment对象
     */
    public LeftMenuFragment getLeftMenuFragment() {
        FragmentManager fm = getSupportFragmentManager();
        LeftMenuFragment fragment = (LeftMenuFragment) fm.findFragmentByTag(FRAGMENT_LEFT_MENU);
        return fragment;
    }

    /**
     * 获取主界面Fragment
     * @return MainFragment对象
     */
    public MainFragment getMainFragment() {
        FragmentManager fm = getSupportFragmentManager();
        MainFragment fragment = (MainFragment) fm.findFragmentByTag(FRAGMENT_MAIN);
        return fragment;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                // 判断2次点击事件时间
                if ((System.currentTimeMillis() - exitTime) > 2000) {       //间隔两秒以上弹Toast
                    showToast("再按一次退出程序");
                    exitTime = System.currentTimeMillis();
                } else {                            //直接退出
                    finish();                       //交由Base处理
                }
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /** 打开侧滑菜单 */
    public void openMenu(){
        main_residelayout.openPane();
    }

    /** 关闭侧滑菜单 */
    public void closeMenu(){
        main_residelayout.closePane();
    }
//    public static void startForResult(Activity ctx){
//        Intent intent = new Intent(ctx,MainActivity.class);
//        intent.putExtra("requestResult",true);
//        ctx.startActivityForResult(intent, 0);
//    }
}