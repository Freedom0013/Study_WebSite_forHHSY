package com.studytree.view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.studytree.R;
import com.studytree.bean.CourseBean;
import com.studytree.utils.StringUtils;
import com.studytree.utils.StudyTreeTools;
import com.studytree.view.base.BaseActivity;
import com.studytree.view.widget.CircularImage;
import com.studytree.view.widget.RoundImageView;
import com.studytree.view.widget.StudyTreeTitleBar;

/**
 * 课程详情Activity
 * Title: CourseDetailActivity
 * @date 2018/7/21 20:59
 * @author Freedom0013
 */
public class CourseDetailActivity extends BaseActivity implements StudyTreeTitleBar.TitleBarClickListener,View.OnClickListener {
    public static final String TAG = CourseDetailActivity.class.getSimpleName();
    /** 上级页面CourseBean */
    private CourseBean mCourseBean;
    /** 图片信息配置 */
    private DisplayImageOptions mOptions;

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

        mOptions = new DisplayImageOptions
                .Builder()
                .showImageOnLoading(R.drawable.course_default) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.course_default)    // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.course_default)        // 设置图片加载或解码过程中发生错误显示的图片
//                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();

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

        //配置排行榜
        TextView ranking_top_2_score = findViewById(R.id.ranking_top_2_score);
        ranking_top_2_score.setText("98.5");
        TextView ranking_top_2_nick = findViewById(R.id.ranking_top_2_nick);
        ranking_top_2_nick.setText("Freedom00");
        CircularImage ranking_top_2_avatar = findViewById(R.id.ranking_top_2_avatar);
        TextView ranking_top_1_score = findViewById(R.id.ranking_top_1_score);
        ranking_top_1_score.setText("99.5");
        TextView ranking_top_1_nick = findViewById(R.id.ranking_top_1_nick);
        ranking_top_1_nick.setText("Jeff00");
        CircularImage ranking_top_1_avatar = findViewById(R.id.ranking_top_1_avatar);
        TextView ranking_top_3_score = findViewById(R.id.ranking_top_3_score);
        ranking_top_3_score.setText("96.5");
        TextView ranking_top_3_nick = findViewById(R.id.ranking_top_3_nick);
        ranking_top_3_nick.setText("Ann00");
        CircularImage ranking_top_3_avatar = findViewById(R.id.ranking_top_3_avatar);

        //配置课程详情显示
        RoundImageView course_detail_image = findViewById(R.id.course_detail_image);
        ImageLoader.getInstance().displayImage(mCourseBean.course_image_url, course_detail_image, mOptions);
        TextView cou_detail_title = findViewById(R.id.cou_detail_title);
        cou_detail_title.setText(mCourseBean.course_name);
        TextView cou_detail_date = findViewById(R.id.cou_detail_date);
        cou_detail_date.setText("最近更新:" + mCourseBean.course_addtime);
        if (!StringUtils.isNullOrEmpty(mCourseBean.course_summary)) {
            TextView cou_detail_description_title = findViewById(R.id.cou_detail_description_title);
            TextView cou_detail_description = findViewById(R.id.cou_detail_description);
            cou_detail_description_title.setVisibility(View.VISIBLE);
            cou_detail_description.setVisibility(View.VISIBLE);
            cou_detail_description.setText("    " + mCourseBean.course_summary);
        }
        if (!StringUtils.isNullOrEmpty(mCourseBean.course_applypeople)) {
            TextView cou_detail_people_title = findViewById(R.id.cou_detail_people_title);
            TextView cou_detail_people = findViewById(R.id.cou_detail_people);
            cou_detail_people_title.setVisibility(View.VISIBLE);
            cou_detail_people.setVisibility(View.VISIBLE);
            cou_detail_people.setText("    " + mCourseBean.course_applypeople);
        }

        //开始测试
        Button begin_test = findViewById(R.id.begin_test);
        begin_test.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.begin_test:
                ExaminationActivity.start(CourseDetailActivity.this,mCourseBean);
                break;
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
