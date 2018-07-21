package com.studytree.view.adapter;

import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.studytree.R;
import com.studytree.bean.CourseBean;
import com.studytree.view.base.BaseActivity;
import com.studytree.view.widget.RoundImageView;

import java.util.List;

/**
 * 课程List适配器
 * Title: CourseAdapter
 * @date 2018/7/20 21:27
 * @author Freedom0013
 */
public class CourseAdapter extends BaseAdapter {
    private static final String TAG = CourseAdapter.class.getSimpleName();
    /** BaseActivity对象 */
    private BaseActivity mActivity;
    /** ImageLoader配置器 */
    private DisplayImageOptions mOptions;
    /** 初级课程List */
    private List<CourseBean> mCourseLow;
    /** 中级课程List */
    private List<CourseBean> mCourseMiddle;
    /** 高级课程List */
    private List<CourseBean> mCourseHigh;
    /** 其他课程List */
    private List<CourseBean> mCourseOther;

    /**
     * 构造函数
     * @param activity BaseActivity
     * @param courses_low 初级课程
     * @param courses_middle 中级课程
     * @param courses_high 高级课程
     * @param courses_other 其他课程
     */
    public CourseAdapter(BaseActivity activity, List<CourseBean> courses_low, List<CourseBean> courses_middle, List<CourseBean> courses_high, List<CourseBean> courses_other) {
        mActivity = activity;
        mCourseLow = courses_low;
        mCourseMiddle = courses_middle;
        mCourseHigh = courses_high;
        mCourseOther = courses_other;
        mOptions = new DisplayImageOptions
                .Builder()
                .showImageOnLoading(R.drawable.course_default) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.course_default)    // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.course_default)        // 设置图片加载或解码过程中发生错误显示的图片
//                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }

    @Override
    public int getCount() {
        return preCount();
    }

    @Override
    public Object getItem(int position) {
        if (position == 0) {
            return null;
        } else if (position == mCourseLow.size() + 1) {
            return null;
        } else if (position == mCourseLow.size() + mCourseMiddle.size() + 2) {
            return null;
        } else if (position == mCourseLow.size() + mCourseMiddle.size() + mCourseHigh.size() + 3) {
            return null;
        }
        CourseBean coursebean = null;
        if (position >= 1 && position < mCourseLow.size() + 1) {
            //把多出来的特殊的条目减掉
            coursebean = mCourseLow.get(position - 1);
        } else if (mCourseLow.size() + 2 <= position && position < mCourseLow.size() + mCourseMiddle.size() + 2) {
            coursebean = mCourseMiddle.get(position - 2);
        } else if (mCourseLow.size() + mCourseMiddle.size() + 3 <= position && position < mCourseLow.size() + mCourseMiddle.size() + mCourseHigh.size() + 3) {
            coursebean = mCourseHigh.get(position - 3);
        } else if (mCourseLow.size() + mCourseMiddle.size() + mCourseHigh.size() + 4 <= position && position < mCourseLow.size() + mCourseMiddle.size() + mCourseHigh.size() + mCourseOther.size() + 4) {
            coursebean = mCourseOther.get(position - 4);
        }
        return coursebean;
    }

    @Override
    public long getItemId(int position) {
        if (position == mCourseLow.size() + 1) {
            return position - 1;
        } else if (position == mCourseLow.size() + mCourseMiddle.size() + 2) {
            return position - 2;
        } else if (position == mCourseLow.size() + mCourseMiddle.size() + mCourseHigh.size() + 3) {
            return position - 3;
        }
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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
        View v = null;
        ViewHolder mHolder;
        if (convertView == null) {
            v = View.inflate(mActivity, R.layout.layout_item_course, null);
            mHolder = new ViewHolder();
            //把布局文件中所有组件的对象封装至ViewHolder对象中
            mHolder.cou_item_container = v.findViewById(R.id.cou_item_container);
            mHolder.cou_image = v.findViewById(R.id.cou_image);
            mHolder.cou_item_title = v.findViewById(R.id.cou_item_title);
            mHolder.cou_updata_date = v.findViewById(R.id.cou_updata_date);
            mHolder.course_degree_ln = v.findViewById(R.id.course_degree_ln);
            mHolder.course_degree_text = v.findViewById(R.id.course_degree_text);
            //把ViewHolder对象封装至View对象中
            v.setTag(mHolder);
        } else {
            v = convertView;
            mHolder = (ViewHolder) v.getTag();
        }
        if (position == 0) {
            setViewVisibility(mHolder, "初级课程");
        } else if (position == mCourseLow.size() + 1) {
            setViewVisibility(mHolder, "中级课程");
        } else if (position == mCourseLow.size() + mCourseMiddle.size() + 2) {
            setViewVisibility(mHolder, "高级课程");
        } else if (position == mCourseLow.size() + mCourseMiddle.size() + mCourseHigh.size() + 3) {
            setViewVisibility(mHolder, "其他课程");
        } else {
            mHolder.course_degree_ln.setVisibility(View.GONE);
            mHolder.cou_item_container.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage(course.course_image_url, mHolder.cou_image, mOptions);
            mHolder.cou_item_title.setText(course.course_name);
            mHolder.cou_updata_date.setText(course.course_addtime);
        }
        return v;
    }

    /**
     * 获取item总数
     * @return count
     */
    private int preCount() {
        int count = 0;
        if (mCourseLow.size() > 0) {
            count += mCourseLow.size() + 1;
        }
        if (mCourseMiddle.size() > 0) {
            count += mCourseMiddle.size() + 1;
        }
        if (mCourseHigh.size() > 0) {
            count += mCourseHigh.size() + 1;
        }
        if (mCourseOther.size() > 0) {
            count += mCourseOther.size() + 1;
        }
        return count;
    }

    /**
     * 设置特殊Item
     * @param title item_title
     */
    public void setViewVisibility(ViewHolder mHolder, String title) {
        mHolder.course_degree_ln.setVisibility(View.VISIBLE);
        mHolder.cou_item_container.setVisibility(View.GONE);
        mHolder.course_degree_text.setText(title);
    }

    /**
     * 课程ViewHolder
     * Title: CourseAdapter
     * @date 2018/7/21 12:45
     * @author Freedom0013
     */
    private class ViewHolder {
        LinearLayout course_degree_ln;
        TextView course_degree_text;
        CardView cou_item_container;
        RoundImageView cou_image;
        TextView cou_item_title;
        TextView cou_updata_date;
    }
}
