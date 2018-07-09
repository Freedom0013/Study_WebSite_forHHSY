package com.studytree.view.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;

/**
 * Fragment基类
 * Title: BaseFragment
 * @date 2018/6/23 19:45
 * @author Freedom0013
 */
public abstract class BaseFragment extends Fragment {
    public static final String TAG = BaseFragment.class.getSimpleName();
    Activity mActivity;

    //Fragment创建
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    //处理Fragment布局
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return initView();
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 初始化Fragment布局文件
     * @return 布局
     */
    public abstract View initView();

    //依赖Activity创建完成版
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initData();
    }

    /**
     * 初始化数据
     */
    public void initData(){

    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(getClass().getName());
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(getActivity().getLocalClassName());
    }

    /**
     * 显示String Toast
     * @param s Toast信息
     */
    public void showToast(final String s){
        if(getActivity() != null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getActivity(),s,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    protected void showProgress(){
        if(getActivity() != null){
            if(getActivity() instanceof BaseFragmentActivity){
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ((BaseFragmentActivity) getActivity()).showProgressDialog();
                    }
                });
            }
        }
    }
}
