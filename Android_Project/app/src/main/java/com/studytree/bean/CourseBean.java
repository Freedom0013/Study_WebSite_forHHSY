package com.studytree.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 课程Bean
 * Title: CourseBean
 * @date 2018年1月26日
 * @author Freedom0013
 */
public class CourseBean implements Serializable{
    /** 课程等级识别码（初级） */
    public static final int DEGREE_LOW = 1;
    /** 课程等级识别码（中级） */
    public static final int DEGREE_MIDDLE = 2;
    /** 课程等级识别码（高级） */
    public static final int DEGREE_HIGH = 3;
    /** 课程等级识别码（其他） */
    public static final int DEGREE_OTHER = 4;
	/** 课程编号 */
	public int course_id;
	/** 课程名称 */
	public String course_name;
	/** 课程适用人群 */
	public String course_applypeople;
	/** 课程概述 */
	public String course_summary;
	/** 课程图片编号 */
	public BigDecimal course_picture_id;
	/** 课程目录编号 */
	public int course_catalog_id;
	/** 课程添加时间 */
	public String course_addtime;
	/** 课程所属专业 */
	public int course_profession_id;
	/** 课程所属年级 */
	public int course_degree;
	/** 课程图片地址 */
	public String course_image_url;

	/**
	 * 空参构造函数
	 */
	public CourseBean() {
		super();
	}
	
	/**
	 * 全参构造函数
	 */
	public CourseBean(int course_id, String course_name, String course_applypeople, String course_summary,
			BigDecimal course_picture_id, int course_catalog_id, String course_addtime, int course_profession_id,
			int course_degree, String course_image_url) {
		super();
		this.course_id = course_id;
		this.course_name = course_name;
		this.course_applypeople = course_applypeople;
		this.course_summary = course_summary;
		this.course_picture_id = course_picture_id;
		this.course_catalog_id = course_catalog_id;
		this.course_addtime = course_addtime;
		this.course_profession_id = course_profession_id;
		this.course_degree = course_degree;
        this.course_image_url = course_image_url;
	}

	@Override
	public String toString() {
		return "CourseBean [course_id=" + course_id + ", course_name=" + course_name + ", course_applypeople="
				+ course_applypeople + ", course_summary=" + course_summary + ", course_picture_id=" + course_picture_id
				+ ", course_catalog_id=" + course_catalog_id + ", course_addtime=" + course_addtime
				+ ", course_profession_id=" + course_profession_id + ", course_degree=" + course_degree
                + ", course_image_url=" + course_image_url + "]";
	}
}
