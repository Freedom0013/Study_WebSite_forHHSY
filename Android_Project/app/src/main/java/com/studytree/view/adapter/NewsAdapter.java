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
import com.studytree.bean.NewsBean;
import com.studytree.view.base.BaseActivity;

import java.util.List;

/**
 * 资讯Adapter
 * Title: NewsAdapter
 * @date 2018/7/27 17:26
 * @author Freedom0013
 */
public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = NewsAdapter.class.getSimpleName();
    /** BaseActivity对象 */
    private BaseActivity mActivity;
    /** 专业List */
    private List<NewsBean> mNewsList;
    /** LayoutInflater对象 */
    private LayoutInflater mLayoutInflater;
    /** 条目点击事件监听器 */
    private NewsAdapter.NewsItemOnClickListener mClickListener;
    /** 图片信息配置 */
    private DisplayImageOptions mOptions;

    /**
     * NewsAdapter构造函数
     * @param activity activity对象
     * @param list 专业List
     * @param clickListener 条目点击事件监听器
     */
    public NewsAdapter(BaseActivity activity, List<NewsBean> list, NewsAdapter.NewsItemOnClickListener clickListener) {
        mActivity = activity;
        mNewsList = list;
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
        return new NewsAdapter.NewsItemViewHolder(mLayoutInflater.inflate(R.layout.layout_item_tab_news, parent, false));
    }

    //绑定View数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        NewsBean newsbean = mNewsList.get(position);
        if (null == newsbean) {
            return;
        }
        if (holder instanceof NewsAdapter.NewsItemViewHolder) {
            bindGroupItem(newsbean, (NewsAdapter.NewsItemViewHolder) holder, position);
        }
    }

    /**
     * 绑定View数据
     * @param bean NewsBean
     * @param viewholder NewsItemViewHolder
     * @param position position
     */
    void bindGroupItem(NewsBean bean, NewsAdapter.NewsItemViewHolder viewholder, int position) {
        viewholder.news_item_title.setText(bean.title);
        viewholder.news_updata_date.setText(bean.createTime);
        ImageLoader.getInstance().displayImage(bean.pictureUrl, viewholder.news_image, mOptions);
        final int posi = position;
        viewholder.news_item_container.setOnClickListener(new View.OnClickListener() {
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
        return mNewsList.size();
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
     * NewsItemViewHolder
     * Title: NewsAdapter
     * @date 2018/7/18 17:33
     * @author Freedom0013
     */
    class NewsItemViewHolder extends RecyclerView.ViewHolder{
        /** 资讯布局 */
        CardView news_item_container;
        /** 资讯图片 */
        ImageView news_image;
        /** 资讯标题 */
        TextView news_item_title;
        /** 资讯更新日期 */
        TextView news_updata_date;

        /**
         * 构造函数
         * @param itemView View对象
         */
        public NewsItemViewHolder(View itemView) {
            super(itemView);
            news_item_container = itemView.findViewById(R.id.news_item_container);
            news_image = itemView.findViewById(R.id.news_image);
            news_item_title = itemView.findViewById(R.id.news_item_title);
            news_updata_date = itemView.findViewById(R.id.news_updata_date);
        }
    }

    /**
     * News条目点击监听器
     * Title: NewsAdapter
     * @date 2018/7/18 17:33
     * @author Freedom0013
     */
    public interface NewsItemOnClickListener{
        /**
         * 条目监听事件回调
         * @param v view对象
         * @param position 位置
         */
        void onItemClick(View v,int position);
    }
}
