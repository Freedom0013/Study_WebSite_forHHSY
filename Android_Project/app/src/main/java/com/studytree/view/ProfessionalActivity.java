package com.studytree.view;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.studytree.InitManager;
import com.studytree.R;
import com.studytree.bean.DepartmentBean;
import com.studytree.bean.PictureBean;
import com.studytree.bean.ProfessionBean;
import com.studytree.commonfile.Constants;
import com.studytree.http.HttpResultCallback;
import com.studytree.http.logic.InitLogic;
import com.studytree.log.Logger;
import com.studytree.utils.StringUtils;
import com.studytree.utils.StudyTreeTools;
import com.studytree.view.adapter.ProfessionalAdapter;
import com.studytree.view.base.BaseActivity;
import com.studytree.view.widget.StudyTreeTitleBar;

import java.util.ArrayList;
import java.util.List;

/**
 * 专业Activity
 * Title: ProfessionalActivity
 * @date 2018/7/17 15:51
 * @author Freedom0013
 */
public class ProfessionalActivity extends BaseActivity implements StudyTreeTitleBar.TitleBarClickListener,ProfessionalAdapter.ProfessionItemOnClickListener{
    public static final String TAG = ProfessionalActivity.class.getSimpleName();
    /** 上级页面接受到的大类信息 */
    private DepartmentBean mDepartment;
    /** 标题栏 */
    private StudyTreeTitleBar pro_tool;
    /** RecyclerView */
    private RecyclerView mProRecyclerview;
    /** 专业数据List */
    private List<ProfessionBean> mProfessionData;
    /** RecyclerView-Adapter */
    private ProfessionalAdapter mProAdapter;
    /** 上级页面附属department_id */
    private String department_id;

    /**
     * 启动ProfessionalActivity
     * @param ctx 来源Context
     */
    public static void start(Context ctx, DepartmentBean bean) {
        Intent intent = new Intent(ctx, ProfessionalActivity.class);
        intent.putExtra("DepartmentBean", bean);
        ctx.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional);

        //沉浸
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        //解析上级页面传递来的Bean
        mDepartment = (DepartmentBean) getIntent().getSerializableExtra("DepartmentBean");
        initView();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        //设置占位View以实现沉浸式状态栏
        View statusBar = findViewById(R.id.pro_statusBarView);
        ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        layoutParams.height = StudyTreeTools.getStatusBarHeight(ProfessionalActivity.this);

        //配置toolBar
        pro_tool = findViewById(R.id.pro_tool);
        pro_tool.setTitleRightVisibility(false);
        pro_tool.setLeftDrawable(R.drawable.titlebar_back);
        pro_tool.setTitle(mDepartment.department_name);
        pro_tool.setOnTitleBarClickedListener(this);
        //添加系统
        setSupportActionBar(pro_tool);

        //初始化展示ImageView
        ImageView scroll_back_img = findViewById(R.id.scroll_back_img);
        ImageLoader.getInstance().displayImage(mDepartment.department_image_url, scroll_back_img);

        //配置Recyclerview
        mProRecyclerview = findViewById(R.id.pro_recycview);
        mProfessionData = new ArrayList<ProfessionBean>();
        mProAdapter = new ProfessionalAdapter(ProfessionalActivity.this, mProfessionData, ProfessionalActivity.this);
        mProRecyclerview.setLayoutManager(new LinearLayoutManager(ProfessionalActivity.this));
        mProRecyclerview.setAdapter(mProAdapter);


        //显示进度
        showProgressDialog();
        InitLogic.getInstance().getProfessionInfo(mDepartment, new HttpResultCallback() {
            @Override
            public void onSuccess(int action, Object obj) {
                doGetProfession((String) obj);
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
     * 处理网络返回数据
     * @param dataStr json
     */
    private void doGetProfession(String dataStr) {
        if (StringUtils.isNullOrEmpty(dataStr)) {
            return;
        }
        //本地缓存Json数据
        InitManager.getInstance().saveStringPreference("profession_list", dataStr);
        List<ProfessionBean> professionlist = new ArrayList<ProfessionBean>();
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
                //解析附属department_id
                JsonElement departmentid = dataArray.get(2);
                JsonObject id = new JsonParser().parse(departmentid.toString()).getAsJsonObject();
                department_id = id.get("department_id").getAsString();
                //封装List
                if (root != null) {
                    for (JsonElement profession : root) {
                        ProfessionBean professionbean = gson.fromJson(profession, ProfessionBean.class);
                        professionlist.add(professionbean);
                    }
                }
                //匹配封装对应Image
                if (pic != null) {
                    for (JsonElement picture : pic) {
                        PictureBean picturebean = gson.fromJson(picture, PictureBean.class);
                        for (ProfessionBean profession : professionlist) {
                            int isequals = profession.profession_picture_id.compareTo(picturebean.picture_id);
                            if (isequals == 0) {
                                profession.profession_image_url = "http://" + Constants.HOST + "/" + picturebean.picture_img;
                            }
                        }
                    }
                }
                initProfessionList(professionlist);
            } catch (Exception e) {
                Logger.e(TAG, "解析专业错误！", e);
                initProfessionList(null);
            }
        }
    }

    /**
     * 更新专业UI
     * @param list 专业数据
     */
    public void initProfessionList(final List<ProfessionBean> list) {
        ProfessionalActivity.this.mBasehandler.post(new Runnable() {
            @Override
            public void run() {
                mProfessionData.clear();
                if (list != null && !list.isEmpty()) {
                    mProfessionData.addAll(list);
                    Logger.d(TAG, mProfessionData.toString());
                }else{
                    showToast("暂时没有数据，拼命补充中....");
                }
                //去除进度
                dismissProgressDialog();
                mProAdapter.notifyDataSetChanged();
            }
        });
    }

    //Item点击事件
    @Override
    public void onItemClick(View v, int position) {
        ProfessionBean bean = mProfessionData.get(position);
        CourseActivity.start(ProfessionalActivity.this,bean);
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
