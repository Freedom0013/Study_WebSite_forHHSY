package com.studytree.view.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.studytree.bean.QuestionBean;
import com.studytree.view.ExaminationActivity;
import com.studytree.view.fragment.QuestionFragment;

import java.util.List;

/**
 * 试题ViewPager适配器
 * Title: QuestionViewPagerAdapter
 * @date 2018/7/23 18:26
 * @author Freedom0013
 */
public class QuestionViewPagerAdapter extends FragmentStatePagerAdapter {
    private static final String TAG = QuestionViewPagerAdapter.class.getSimpleName();
    /** ExaminationActivity */
    private ExaminationActivity mActivity;
    /** 试题列表 */
    List<QuestionBean> questionlist;

    public QuestionViewPagerAdapter(Activity activity,FragmentManager fm, List<QuestionBean> questionlist){
        super(fm);
        this.questionlist = questionlist;
        mActivity = (ExaminationActivity)activity;
    }

    @Override
    public Fragment getItem(int position) {
        QuestionBean question = questionlist.get(position);
        QuestionFragment fragment = new QuestionFragment(mActivity,question,position);
        return fragment;
    }

    @Override
    public int getCount() {
        return questionlist == null ? 0 : questionlist.size();
    }
}
