package com.studytree.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.studytree.R;
import com.studytree.view.base.BaseActivity;


/**
 * 主页Activity
 * AppCompatActivity继承自ActionBarActivity
 * Title: MainActivity
 * @date 2018/6/12 19:16
 * @author Freedom0013
 */
public class MainActivity extends BaseActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    /** 侧滑菜单 */
    private LinearLayout menu;
    private RadioGroup mRadioGroup;
    private RadioButton rb_tab_home;
    private RadioButton rb_tab_news;
    private RadioButton rb_tab_me;

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
        //去除ActionBar
        getSupportActionBar().hide();
        //沉浸
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        initView();
    }

    /** 初始化界面 */
    private void initView() {
        menu = findViewById(R.id.menu);
        menu.setBackgroundResource(R.drawable.menu_background);
        mRadioGroup = findViewById(R.id.main_tab_radio);
        rb_tab_home = findViewById(R.id.main_tab_home);
        rb_tab_news = findViewById(R.id.main_tab_news);
        rb_tab_me = findViewById(R.id.main_tab_me);
        changeImageSize();
    }

    /**
     * 格式化布局
     */
    private void changeImageSize() {
        int marginVertical = 10;
        int size = 85;
        //定义底部标签图片大小
        Drawable drawableHome = getResources().getDrawable(R.drawable.selector_tab_home);
        //参数1：是距左右边距离||参数2：是距上下边距离||参数3、4：长度,宽度
        drawableHome.setBounds(0, marginVertical, size, size);
        //只放上面
        rb_tab_home.setCompoundDrawables(null, drawableHome, null, null);
        Drawable drawableNews = getResources().getDrawable(R.drawable.selector_tab_news);
        drawableNews.setBounds(0, marginVertical, size, size);
        rb_tab_news.setCompoundDrawables(null, drawableNews, null, null);
        Drawable drawableMe = getResources().getDrawable(R.drawable.selector_tab_me);
        drawableMe.setBounds(0, marginVertical, size, size);
        rb_tab_me.setCompoundDrawables(null, drawableMe, null, null);
    }

//    public static void startForResult(Activity ctx){
//        Intent intent = new Intent(ctx,MainActivity.class);
//        intent.putExtra("requestResult",true);
//        ctx.startActivityForResult(intent, 0);
//    }
}