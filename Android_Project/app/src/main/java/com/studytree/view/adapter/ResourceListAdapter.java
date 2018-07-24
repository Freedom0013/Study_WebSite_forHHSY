package com.studytree.view.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.studytree.R;
import com.studytree.bean.ResourceBean;
import com.studytree.view.base.BaseActivity;

import java.util.List;

/**
 * 资源适配器
 * Title: ResourceListAdapter
 * @date 2018/7/24 18:44
 * @author Freedom0013
 */
public class ResourceListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final String TAG = ResourceListAdapter.class.getSimpleName();
    /** BaseActivity对象 */
    private BaseActivity mActivity;
    /** 资源List */
    private List<ResourceBean> mResourceList;
    /** LayoutInflater对象 */
    private LayoutInflater mLayoutInflater;
    /** 条目点击事件监听器 */
    private ResourceItemOnClickListener mClickListener;
    /** 图片信息配置 */
    private DisplayImageOptions mOptions;

    /**
     * 构造函数
     * @param activity activity对象
     * @param list 资源列表
     * @param clickListener 点击事件
     */
    public ResourceListAdapter(BaseActivity activity, List<ResourceBean> list, ResourceItemOnClickListener clickListener) {
        mActivity = activity;
        mResourceList = list;
        mClickListener = clickListener;
        mLayoutInflater = LayoutInflater.from(mActivity);
        mOptions = new DisplayImageOptions
                .Builder()
                .showImageOnLoading(R.drawable.department_default) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.department_default)    // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.department_default)        // 设置图片加载或解码过程中发生错误显示的图片
//                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ResourceItemViewHolder(mLayoutInflater.inflate(R.layout.layout_item_recourse, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ResourceBean resourcebean = mResourceList.get(position);
        if (null == resourcebean) {
            return;
        }
        if (holder instanceof ResourceItemViewHolder) {
            bindGroupItem(resourcebean, (ResourceItemViewHolder) holder, position);
        }
    }

    /**
     * 绑定View
     */
    void bindGroupItem(ResourceBean bean, ResourceItemViewHolder viewholder, int position) {
        viewholder.res_item_title.setText(bean.resource_name);
        viewholder.res_updata_date.setText(bean.resource_addtime);
        ImageLoader.getInstance().displayImage(bean.resource_image_url, viewholder.res_image, mOptions);
        final int posi = position;
        viewholder.res_item_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null) {
                    mClickListener.onItemClick(v, posi);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return mResourceList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    /**
     * 资源ViewHolder
     * Title: ResourceListAdapter
     * @date 2018/7/24 18:45
     * @author Freedom0013
     */
    class ResourceItemViewHolder extends RecyclerView.ViewHolder{
        /** 包裹布局 */
        CardView res_item_container;
        /** 专业图片 */
        ImageView res_image;
        /** 专业标题 */
        TextView res_item_title;
        /** 专业更新日期 */
        TextView res_updata_date;

        /**
         * 构造函数
         * @param itemView View对象
         */
        public ResourceItemViewHolder(View itemView) {
            super(itemView);
            res_item_container = itemView.findViewById(R.id.res_item_container);
            res_image = itemView.findViewById(R.id.res_image);
            res_item_title = itemView.findViewById(R.id.res_item_title);
            res_updata_date = itemView.findViewById(R.id.res_updata_date);
        }
    }

    /**
     * 资源条目点击监听器
     * Title: ResourceListAdapter
     * @date 2018/7/24 18:45
     * @author Freedom0013
     */
    public interface ResourceItemOnClickListener{
        /**
         * 条目监听事件回调
         * @param v view对象
         * @param position 位置
         */
        void onItemClick(View v, int position);
    }
}
