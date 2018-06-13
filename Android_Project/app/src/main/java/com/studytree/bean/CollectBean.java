package com.studytree.bean;

import java.math.BigDecimal;

/**
 * 收藏关系Bean
 * Title: CollectBean
 * @date 2018年1月26日
 * @author Freedom0013
 */
public class CollectBean {
	/** 收藏编号 */
	private BigDecimal collect_id;
	/** 收藏用户编号 */
	private int collect_user_id;
	/** 收藏课程编号 */
	private int collect_course_id;
	/** 收藏添加时间 */
	private String collect_addtime;

	/**
	 * 空参构造函数
	 */
	public CollectBean() {

	}

	/**
	 * 全参数构造函数
	 */
	public CollectBean(BigDecimal collect_id, int collect_user_id, int collect_course_id, String collect_addtime) {
		super();
		this.collect_id = collect_id;
		this.collect_user_id = collect_user_id;
		this.collect_course_id = collect_course_id;
		this.collect_addtime = collect_addtime;
	}

    public BigDecimal getCollect_id() {
        return collect_id;
    }

    public void setCollect_id(BigDecimal collect_id) {
        this.collect_id = collect_id;
    }

    public int getCollect_user_id() {
        return collect_user_id;
    }

    public void setCollect_user_id(int collect_user_id) {
        this.collect_user_id = collect_user_id;
    }

    public int getCollect_course_id() {
        return collect_course_id;
    }

    public void setCollect_course_id(int collect_course_id) {
        this.collect_course_id = collect_course_id;
    }

    public String getCollect_addtime() {
        return collect_addtime;
    }

    public void setCollect_addtime(String collect_addtime) {
        this.collect_addtime = collect_addtime;
    }

    @Override
	public String toString() {
		return "CollectBean [collect_id=" + collect_id + ", collect_user_id=" + collect_user_id + ", collect_course_id="
				+ collect_course_id + ", collect_addtime=" + collect_addtime + "]";
	}
}
