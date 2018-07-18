package com.studytree.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.studytree.R;
import com.studytree.bean.ProfessionBean;
import com.studytree.view.base.BaseActivity;

import java.util.List;

/**
 * 大类RecyclerView-Adapter
 * Title: ProfessionalAdapter
 * @date 2018/7/18 11:08
 * @author Freedom0013
 */
public class ProfessionalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final String TAG = ProfessionalAdapter.class.getSimpleName();
    /** BaseActivity对象 */
    private BaseActivity mActivity;
    /** 专业List */
    private List<ProfessionBean> mProfessionList;
    /** LayoutInflater对象 */
    private LayoutInflater mLayoutInflater;
    /** 条目点击事件监听器 */
    private ProfessionItemOnClickListener mClickListener;
    /** 图片信息配置 */
    private DisplayImageOptions mOptions;

    /**
     * ProfessionalAdapter构造函数
     * @param activity activity对象
     * @param list 专业List
     * @param clickListener 条目点击事件监听器
     */
    public ProfessionalAdapter(BaseActivity activity, List<ProfessionBean> list, ProfessionItemOnClickListener clickListener) {
        mActivity = activity;
        mProfessionList = list;
        mClickListener = clickListener;
        mLayoutInflater = LayoutInflater.from(mActivity);
        mOptions = new DisplayImageOptions
                .Builder()
                .showImageOnLoading(R.drawable.profession_default) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.profession_default)    // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.profession_default)        // 设置图片加载或解码过程中发生错误显示的图片
//                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }

    //创建View
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ProfessionalItemViewHolder(mLayoutInflater.inflate(R.layout.layout_item_professional, parent, false));
    }

    //绑定View数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProfessionBean professionbean = mProfessionList.get(position);
        if (null == professionbean) {
            return;
        }
        if (holder instanceof ProfessionalItemViewHolder) {
            bindGroupItem(professionbean, (ProfessionalItemViewHolder) holder, position);
        }
    }

    /**
     * 绑定View数据
     * @param bean ProfessionBean
     * @param viewholder ProfessionalItemViewHolder
     * @param position position
     */
    void bindGroupItem(ProfessionBean bean, ProfessionalItemViewHolder viewholder, int position) {
        viewholder.pro_item_title.setText(bean.profession_name);
        viewholder.pro_updata_date.setText(bean.profession_addtime);
        ImageLoader.getInstance().displayImage(bean.profession_image_url, viewholder.pro_image, mOptions);
        final int posi = position;
        viewholder.pro_item_container.setOnClickListener(new View.OnClickListener() {
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
        return mProfessionList.size();
    }

    //在一个Adapter中定义两种ViewHolder时需要重写此方法
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    /**
     * ProfessionalItemViewHolder
     * Title: ProfessionalAdapter
     * @date 2018/7/18 17:33
     * @author Freedom0013
     */
    class ProfessionalItemViewHolder extends RecyclerView.ViewHolder{
        /** 包裹布局 */
        LinearLayout pro_item_container;
        /** 专业图片 */
        ImageView pro_image;
        /** 专业标题 */
        TextView pro_item_title;
        /** 专业更新日期 */
        TextView pro_updata_date;

        /**
         * 构造函数
         * @param itemView View对象
         */
        public ProfessionalItemViewHolder(View itemView) {
            super(itemView);
            pro_item_container = itemView.findViewById(R.id.pro_item_container);
            pro_image = itemView.findViewById(R.id.pro_image);
            pro_item_title = itemView.findViewById(R.id.pro_item_title);
            pro_updata_date = itemView.findViewById(R.id.pro_updata_date);
        }
    }

    /**
     * Professional条目点击监听器
     * Title: ProfessionalAdapter
     * @date 2018/7/18 17:33
     * @author Freedom0013
     */
    public interface ProfessionItemOnClickListener{
        /**
         * 条目监听事件回调
         * @param v view对象
         * @param position 位置
         */
        void onItemClick(View v,int position);
    }
}
