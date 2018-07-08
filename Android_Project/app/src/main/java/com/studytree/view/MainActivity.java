package com.studytree.view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

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

    }

//    public static void startForResult(Activity ctx){
//        Intent intent = new Intent(ctx,MainActivity.class);
//        intent.putExtra("requestResult",true);
//        ctx.startActivityForResult(intent, 0);
//    }
}