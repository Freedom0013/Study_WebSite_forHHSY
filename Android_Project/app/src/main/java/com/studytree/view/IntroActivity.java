package com.studytree.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.studytree.InitManager;
import com.studytree.R;
import com.studytree.commonfile.Constants;
import com.studytree.log.Logger;
import com.studytree.view.adapter.IntroViewPagerAdapter;
import com.studytree.view.base.BaseActivity;
import com.studytree.view.widget.IntroViewCreator;

import java.util.ArrayList;
import java.util.List;

/**
 * 新手引导Activity
 * Title: IntroActivity
 * @date 2018/7/6 16:55
 * @author Freedom0013
 */
public class IntroActivity extends BaseActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{
    public static final String TAG = IntroActivity.class.getSimpleName();
    private ViewPager splash_viewpager;
    private RadioGroup mRadioGroup;
    private TextView intro_enter;
    /** 引导页View集合 */
    private List<View> mViews;
    /** 引导页View */
    private IntroViewCreator        creator1;
    private IntroViewCreator        creator2;
    private IntroViewCreator        creator3;
    private IntroViewCreator        creator4;

    /**
     * 启动IntroActivity
     * @param ctx 来源Context
     */
    public static void start(Context ctx){
        ctx.startActivity(new Intent(ctx,IntroActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        //去除ActionBar和界面占满屏幕
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //设置已展示新手引导页
        InitManager.getInstance().saveBooleanPreference(Constants.PREF_INTRO,true);

        initView();
    }

    /**
     * 初始化View
     */
    private void initView() {
        splash_viewpager = findViewById(R.id.splash_viewpager);
        mRadioGroup = findViewById(R.id.radio);
        intro_enter = findViewById(R.id.intro_enter);
        intro_enter.setOnClickListener(this);
        initViewPager();
    }

    /**
     * 初始化ViewPager
     */
    private void initViewPager() {
        mViews = new ArrayList<View>();
        creator1 = new IntroViewCreator();
        creator2 = new IntroViewCreator();
        creator3 = new IntroViewCreator();
        creator4 = new IntroViewCreator();
        LayoutInflater inflater = LayoutInflater.from(this);
        View v1 = creator1.getView(inflater, R.drawable.intro_1);
        View v2 = creator2.getView(inflater, R.drawable.intro_2);
        View v3 = creator3.getView(inflater, R.drawable.intro_3);
        View v4 = creator4.getView(inflater, R.drawable.intro_4);
        mViews.add(v1);
        mViews.add(v2);
        mViews.add(v3);
        mViews.add(v4);
        //初始化ViewPagerAdapter
        IntroViewPagerAdapter mAdapter = new IntroViewPagerAdapter(mViews);
        splash_viewpager.setAdapter(mAdapter);
        splash_viewpager.setOnPageChangeListener(this);
        splash_viewpager.setCurrentItem(0);
        RadioButton btn = (RadioButton) mRadioGroup.getChildAt(0);
        btn.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.intro_enter:      //进入下一步
                doOut();
                break;
        }
    }

    /**
     * 离开引导页
     */
    private void doOut() {
        //TODO:留空日后补全可能的验证登录逻辑
        MainActivity.start(IntroActivity.this);
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        try {
            RadioButton btn = (RadioButton) mRadioGroup.getChildAt(position % mViews.size());
            btn.setChecked(true);
        } catch (Exception e) {
            Logger.e(TAG,"引导页圆点切换错误",e);
        }
        //当显示最后一张引导页时，可见进入主页按钮
        if(position == 3){
            intro_enter.setVisibility(View.VISIBLE);
        }else{
            intro_enter.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        creator1.release();
        creator2.release();
        creator3.release();
        creator4.release();
    }
}
