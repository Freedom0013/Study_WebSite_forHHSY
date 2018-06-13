package com.studytree.bean;

import java.math.BigDecimal;

/**
 * 系别Bean
 * Title: DepartmentBean
 * @date 2018年1月26日
 * @author Freedom0013
 */
public class DepartmentBean {
	/** 系别编号 */
	private int department_id;
	/** 系别名称 */
	private String department_name;
	/** 系别图片编号 */
	private BigDecimal department_picture_id;
	/** 系别描述 */
	private String department_caption;
	/** 系别添加时间 */
	private String department_addtime;
	
	/**
	 * 空参数构造函数
	 */
	public DepartmentBean() {
		
	}

	/**
	 * 全参数构造函数
	 */
	public DepartmentBean(int department_id, String department_name, BigDecimal department_picture_id,
			String department_caption, String department_addtime) {
		super();
		this.department_id = department_id;
		this.department_name = department_name;
		this.department_picture_id = department_picture_id;
		this.department_caption = department_caption;
		this.department_addtime = department_addtime;
	}

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public BigDecimal getDepartment_picture_id() {
		return department_picture_id;
	}

	public void setDepartment_picture_id(BigDecimal department_picture_id) {
		this.department_picture_id = department_picture_id;
	}

	public String getDepartment_caption() {
		return department_caption;
	}

	public void setDepartment_caption(String department_caption) {
		this.department_caption = department_caption;
	}

	public String getDepartment_addtime() {
		return department_addtime;
	}

	public void setDepartment_addtime(String department_addtime) {
		this.department_addtime = department_addtime;
	}

	@Override
	public String toString() {
		return "DepartmentBean [department_id=" + department_id + ", department_name=" + department_name
				+ ", department_picture_id=" + department_picture_id + ", department_caption=" + department_caption
				+ ", department_addtime=" + department_addtime + "]";
	}
}
