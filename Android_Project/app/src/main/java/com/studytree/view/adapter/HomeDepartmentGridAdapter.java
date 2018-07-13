package com.studytree.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.studytree.R;
import com.studytree.bean.DepartmentBean;
import com.studytree.utils.StudyTreeTools;
import com.studytree.view.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页系别Adapter
 * Title: HomeDepartmentGridAdapter
 * @date 2018/7/13 16:27
 * @author Freedom0013
 */
public class HomeDepartmentGridAdapter extends BaseAdapter {
    /** 依赖Context对象 */
    private BaseActivity context;
    /** 信息列表 */
    private List<DepartmentBean> list = new ArrayList<DepartmentBean>();
    /** 图片信息配置 */
    private DisplayImageOptions mOptions;
    /** 每个小View大小 */
    private int cellSize = 0;

    /**
     * 设置系别数据
     * @param list 系别集合
     */
    public void setList(ArrayList<DepartmentBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 首页系别GridAdapter构造函数
     * @param context Context对象
     * @param list 系别信息集合
     */
    public HomeDepartmentGridAdapter(BaseActivity context, List<DepartmentBean> list) {
        this.context = context;
        this.list = list;
        cellSize = (context.mScreenWidth - StudyTreeTools.dip2px(context, 2)) / 2;
        mOptions = new DisplayImageOptions
                .Builder()
                .showImageOnLoading(R.drawable.department_default) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.department_default)    // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.department_default)        // 设置图片加载或解码过程中发生错误显示的图片
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DepartmentBean bean = list.get(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_item_home_departments, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.department_item_linear = convertView.findViewById(R.id.department_item_linear);
            viewHolder.department_item_image = convertView.findViewById(R.id.department_item_image);
            viewHolder.department_item_title = convertView.findViewById(R.id.department_item_title);
            viewHolder.department_item_enter = convertView.findViewById(R.id.department_item_enter);

            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) viewHolder.department_item_linear.getLayoutParams();
            params.width = cellSize;
            params.height = cellSize;
            viewHolder.department_item_linear.setLayoutParams(params);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (viewHolder != null && bean != null) {
            viewHolder.department_item_title.setText(bean.department_name);
            viewHolder.department_item_enter.setText("点击进入>>");
            ImageLoader.getInstance().displayImage(bean.department_image_url, viewHolder.department_item_image, mOptions);
        }
        return convertView;
    }

    class ViewHolder {
        LinearLayout department_item_linear;
        ImageView department_item_image;
        TextView department_item_title;
        TextView department_item_enter;
    }
}
