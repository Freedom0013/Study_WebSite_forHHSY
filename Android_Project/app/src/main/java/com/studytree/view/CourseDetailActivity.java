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
import com.studytree.bean.CourseBean;
import com.studytree.utils.StudyTreeTools;
import com.studytree.view.base.BaseActivity;
import com.studytree.view.widget.StudyTreeTitleBar;

/**
 * 课程详情Activity
 * Title: CourseDetailActivity
 * @date 2018/7/21 20:59
 * @author Freedom0013
 */
public class CourseDetailActivity extends BaseActivity implements StudyTreeTitleBar.TitleBarClickListener {
    public static final String TAG = CourseDetailActivity.class.getSimpleName();
    /** 上级页面CourseBean */
    private CourseBean mCourseBean;

    /**
     * 启动CourseDetailActivity
     * @param ctx 来源Context
     * @param bean 来源CourseBean
     */
    public static void start(Context ctx, CourseBean bean) {
        Intent intent = new Intent(ctx, CourseDetailActivity.class);
        intent.putExtra("CourseBean", bean);
        ctx.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coursedetail);

        //沉浸
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        //解析上级页面传递来的Bean
        mCourseBean = (CourseBean) getIntent().getSerializableExtra("CourseBean");

        initView();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        //设置占位View以实现沉浸式状态栏
        View statusBar = findViewById(R.id.statusBarView);
        ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        layoutParams.height = StudyTreeTools.getStatusBarHeight(CourseDetailActivity.this);

        //配置toolBar
        StudyTreeTitleBar coursedetail_tool = findViewById(R.id.coursedetail_tool);
        coursedetail_tool.setTitleRightVisibility(false);
        coursedetail_tool.setLeftDrawable(R.drawable.titlebar_back);
        coursedetail_tool.setTitle(mCourseBean.course_name);
        coursedetail_tool.setOnTitleBarClickedListener(this);
        //添加系统
        setSupportActionBar(coursedetail_tool);

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
