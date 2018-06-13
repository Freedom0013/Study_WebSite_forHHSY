package com.studytree.bean;

import java.math.BigDecimal;

/**
 * 课程Bean
 * Title: CourseBean
 * @date 2018年1月26日
 * @author Freedom0013
 */
public class CourseBean {
	/** 课程编号 */
	private int course_id;
	/** 课程名称 */
	private String course_name;
	/** 课程适用人群 */
	private String course_applypeople;
	/** 课程概述 */
	private String course_summary;
	/** 课程图片编号 */
	private BigDecimal course_picture_id;
	/** 课程目录编号 */
	private int course_catalog_id;
	/** 课程添加时间 */
	private String course_addtime;
	/** 课程所属专业 */
	private int course_profession_id;
	/** 课程所属年级 */
	private int course_degree;
	
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
			int course_degree) {
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
	}
	
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int course_id) {
		this.course_id = course_id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getCourse_applypeople() {
		return course_applypeople;
	}
	public void setCourse_applypeople(String course_applypeople) {
		this.course_applypeople = course_applypeople;
	}
	public String getCourse_summary() {
		return course_summary;
	}
	public void setCourse_summary(String course_summary) {
		this.course_summary = course_summary;
	}
	public BigDecimal getCourse_picture_id() {
		return course_picture_id;
	}
	public void setCourse_picture_id(BigDecimal course_picture_id) {
		this.course_picture_id = course_picture_id;
	}
	public int getCourse_catalog_id() {
		return course_catalog_id;
	}
	public void setCourse_catalog_id(int course_catalog_id) {
		this.course_catalog_id = course_catalog_id;
	}
	public String getCourse_addtime() {
		return course_addtime;
	}
	public void setCourse_addtime(String course_addtime) {
		this.course_addtime = course_addtime;
	}
	public int getCourse_profession_id() {
		return course_profession_id;
	}
	public void setCourse_profession_id(int course_profession_id) {
		this.course_profession_id = course_profession_id;
	}
	public int getCourse_degree() {
		return course_degree;
	}
	public void setCourse_degree(int course_degree) {
		this.course_degree = course_degree;
	}
	
	@Override
	public String toString() {
		return "CourseBean [course_id=" + course_id + ", course_name=" + course_name + ", course_applypeople="
				+ course_applypeople + ", course_summary=" + course_summary + ", course_picture_id=" + course_picture_id
				+ ", course_catalog_id=" + course_catalog_id + ", course_addtime=" + course_addtime
				+ ", course_profession_id=" + course_profession_id + ", course_degree=" + course_degree + "]";
	}
}
