package com.studytree.view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.studytree.R;
import com.studytree.bean.ProfessionBean;
import com.studytree.utils.StudyTreeTools;
import com.studytree.view.base.BaseActivity;
import com.studytree.view.widget.StudyTreeTitleBar;

/**
 * 课程Activity
 * Title: CourseActivity
 * @date 2018/7/19 18:49
 * @author Freedom0013
 */
public class CourseActivity extends BaseActivity implements StudyTreeTitleBar.TitleBarClickListener {
    public static final String TAG = CourseActivity.class.getSimpleName();
    private ProfessionBean mProfession;

    /**
     * 启动CourseActivity
     * @param ctx 来源Context
     */
    public static void start(Context ctx, ProfessionBean bean) {
        Intent intent = new Intent(ctx, CourseActivity.class);
        intent.putExtra("ProfessionBean", bean);
        ctx.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        //沉浸
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        //解析上级页面传递来的Bean
        mProfession = (ProfessionBean) getIntent().getSerializableExtra("ProfessionBean");
        
        initView();
    }

    private void initView() {
        //设置占位View以实现沉浸式状态栏
        View statusBar = findViewById(R.id.statusBarView);
        ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        layoutParams.height = StudyTreeTools.getStatusBarHeight(CourseActivity.this);

        //配置toolBar
        StudyTreeTitleBar course_tool = findViewById(R.id.course_tool);
        course_tool.setTitleRightVisibility(false);
        course_tool.setLeftDrawable(R.drawable.titlebar_back);
        course_tool.setTitle(mProfession.profession_name);
        course_tool.setOnTitleBarClickedListener(this);
        //添加系统
        setSupportActionBar(course_tool);


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
