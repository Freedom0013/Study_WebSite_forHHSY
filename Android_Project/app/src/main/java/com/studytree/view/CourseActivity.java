package com.studytree.view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.studytree.InitManager;
import com.studytree.R;
import com.studytree.bean.CourseBean;
import com.studytree.bean.PictureBean;
import com.studytree.bean.ProfessionBean;
import com.studytree.commonfile.Constants;
import com.studytree.http.HttpResultCallback;
import com.studytree.http.logic.InitLogic;
import com.studytree.log.Logger;
import com.studytree.utils.StringUtils;
import com.studytree.utils.StudyTreeTools;
import com.studytree.view.adapter.CourseAdapter;
import com.studytree.view.base.BaseActivity;
import com.studytree.view.widget.StudyTreeTitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程Activity
 * Title: CourseActivity
 * @date 2018/7/19 18:49
 * @author Freedom0013
 */
public class CourseActivity extends BaseActivity implements StudyTreeTitleBar.TitleBarClickListener, AdapterView.OnItemClickListener{
    public static final String TAG = CourseActivity.class.getSimpleName();
    /** 上级页面ProfessionBean */
    private ProfessionBean mProfession;
    /** 课程ListView */
    private ListView course_list;
    /** 初级课程List */
    private List<CourseBean> mCourseLow;
    /** 中级课程List */
    private List<CourseBean> mCourseMiddle;
    /** 高级课程List */
    private List<CourseBean> mCourseHigh;
    /** 其他课程List */
    private List<CourseBean> mCourseOther;
    /** 课程ListView适配器 */
    private CourseAdapter mCourseAdapter;

    /**
     * 启动CourseActivity
     * @param ctx 来源Context
     * @param bean 来源ProfessionBean
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

        //配置课程ListView
        course_list = findViewById(R.id.course_list);
        mCourseLow = new ArrayList<CourseBean>();
        mCourseMiddle = new ArrayList<CourseBean>();
        mCourseHigh = new ArrayList<CourseBean>();
        mCourseOther = new ArrayList<CourseBean>();
        mCourseAdapter = new CourseAdapter(CourseActivity.this,mCourseLow,mCourseMiddle,mCourseHigh,mCourseOther);
        course_list.setAdapter(mCourseAdapter);
        course_list.setOnItemClickListener(this);

        //请求课程数据
        showProgressDialog();
        InitLogic.getInstance().getCourseInfo(mProfession, new HttpResultCallback() {
            @Override
            public void onSuccess(int action, Object obj) {
                doGetCourse((String) obj);
            }

            @Override
            public void onFail(int action, int responseCode, String responseMsg) {
                Logger.e(TAG, "获取专业大类失败！responseCode = " + responseCode + "responseMsg = " + responseMsg);
                showToast("获取专业大类失败！请检查网络");
                dismissProgressDialog();
            }
        });
    }

    /**
     * 解析课程数据
     * @param dataStr json
     */
    private void doGetCourse(String dataStr) {
        if (StringUtils.isNullOrEmpty(dataStr)) {
            return;
        }
        //本地缓存Json数据
        InitManager.getInstance().saveStringPreference("course_list", dataStr);
        List<CourseBean> tempAll = new ArrayList<CourseBean>();
        List<CourseBean> courses_low = new ArrayList<CourseBean>();
        List<CourseBean> courses_middle = new ArrayList<CourseBean>();
        List<CourseBean> courses_high = new ArrayList<CourseBean>();
        List<CourseBean> courses_other = new ArrayList<CourseBean>();
        if (dataStr != null) {
            try {
                //解析Json
                JsonObject data = new JsonParser().parse(dataStr).getAsJsonObject();
                Gson gson = new Gson();
                JsonArray dataArray = data.getAsJsonArray("data");
                //解析数据
                JsonElement rootinfo = dataArray.get(0);
                JsonObject rootinfos = new JsonParser().parse(rootinfo.toString()).getAsJsonObject();
                JsonArray root = rootinfos.getAsJsonArray("root");
                //解析图片
                JsonElement picinfo = dataArray.get(1);
                JsonObject picinfos = new JsonParser().parse(picinfo.toString()).getAsJsonObject();
                JsonArray pic = picinfos.getAsJsonArray("pic");

                //封装List
                if (root != null) {
                    for (JsonElement course : root) {
                        CourseBean coursebean = gson.fromJson(course, CourseBean.class);
                        switch (coursebean.course_degree) {
                            case CourseBean.DEGREE_LOW:
                                courses_low.add(coursebean);
                                break;
                            case CourseBean.DEGREE_MIDDLE:
                                courses_middle.add(coursebean);
                                break;
                            case CourseBean.DEGREE_HIGH:
                                courses_high.add(coursebean);
                                break;
                            case CourseBean.DEGREE_OTHER:
                                courses_other.add(coursebean);
                                break;
                            default:
                                courses_other.add(coursebean);
                                break;
                        }
                        tempAll.add(coursebean);
                    }
                }

                //匹配封装对应Image
                if (pic != null) {
                    for (JsonElement picture : pic) {
                        PictureBean picturebean = gson.fromJson(picture, PictureBean.class);
                        for (CourseBean course : tempAll) {
                            int isequals = course.course_picture_id.compareTo(picturebean.picture_id);
                            if (isequals == 0) {
                                course.course_image_url = "http://" + Constants.HOST + "/" + picturebean.picture_img;
                            }
                        }
                    }
                }
                initCourseList(courses_low,courses_middle,courses_high,courses_other);
            } catch (Exception e) {
                Logger.e(TAG, "解析课程错误！", e);
                initCourseList(null,null,null,null);
            }
        }
    }

    //课程条目点击
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        CourseBean course = null;
        if (position >= 1 && position < mCourseLow.size() + 1) {
            //把多出来的特殊的条目减掉
            course = mCourseLow.get(position - 1);
        } else if (mCourseLow.size() + 2 <= position && position < mCourseLow.size() + mCourseMiddle.size() + 2) {
            course = mCourseMiddle.get(position - 2 - mCourseLow.size());
        } else if (mCourseLow.size() + mCourseMiddle.size() + 3 <= position && position < mCourseLow.size() + mCourseMiddle.size() + mCourseHigh.size() + 3) {
            course = mCourseHigh.get(position - 3 - mCourseLow.size() - mCourseMiddle.size());
        } else if (mCourseLow.size() + mCourseMiddle.size() + mCourseHigh.size() + 4 <= position && position < mCourseLow.size() + mCourseMiddle.size() + mCourseHigh.size() + mCourseOther.size() + 4) {
            course = mCourseOther.get(position - 4 - mCourseLow.size() - mCourseMiddle.size() - mCourseHigh.size());
        }
        //特殊条目不处理点击
        if(course == null){
            return;
        }
//        Logger.d(TAG,course.toString());
        CourseDetailActivity.start(CourseActivity.this,course);
    }

    /**
     * 更新课程ListView
     */
    private void initCourseList(final List<CourseBean> courses_low, final List<CourseBean> courses_middle, final List<CourseBean> courses_high, final List<CourseBean> courses_other) {
        CourseActivity.this.mBasehandler.post(new Runnable() {
            @Override
            public void run() {
                if(courses_low.isEmpty() && mCourseMiddle.isEmpty() && mCourseHigh.isEmpty() && mCourseOther.isEmpty()){
                    //去除进度
                    dismissProgressDialog();
                    showToast("暂时没有数据，拼命补充中....");
                }else{
                    mCourseLow.clear();
                    mCourseMiddle.clear();
                    mCourseHigh.clear();
                    mCourseOther.clear();
                    if (courses_low != null && !courses_low.isEmpty()) {
                        mCourseLow.addAll(courses_low);
                    }
                    if (courses_middle != null && !courses_middle.isEmpty()) {
                        mCourseMiddle.addAll(courses_middle);
                    }
                    if (courses_high != null && !courses_high.isEmpty()) {
                        mCourseHigh.addAll(courses_high);
                    }
                    if (courses_other != null && !courses_other.isEmpty()) {
                        mCourseOther.addAll(courses_other);
                    }
                    //去除进度
                    dismissProgressDialog();
                    mCourseAdapter.notifyDataSetChanged();
                }
            }
        });
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
