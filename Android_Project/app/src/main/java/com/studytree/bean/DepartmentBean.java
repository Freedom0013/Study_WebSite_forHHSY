package com.studytree.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 系别Bean
 * Title: DepartmentBean
 * @date 2018年1月26日
 * @author Freedom0013
 */
public class DepartmentBean implements Serializable {
	/** 系别编号 */
	public int department_id;
	/** 系别名称 */
	public String department_name;
	/** 系别图片编号 */
	public BigDecimal department_picture_id;
	/** 系别描述 */
	public String department_caption;
	/** 系别添加时间 */
	public String department_addtime;
    /** 系别图片地址 */
	public String department_image_url;

	/**
	 * 空参数构造函数
	 */
	public DepartmentBean() {
	}

	/**
	 * 全参数构造函数
	 */
	public DepartmentBean(int department_id, String department_name, BigDecimal department_picture_id,
			String department_caption, String department_addtime, String department_image_url) {
		super();
		this.department_id = department_id;
		this.department_name = department_name;
		this.department_picture_id = department_picture_id;
		this.department_caption = department_caption;
		this.department_addtime = department_addtime;
		this.department_image_url = department_image_url;
	}

	@Override
	public String toString() {
		return "DepartmentBean [department_id=" + department_id + ", department_name=" + department_name
				+ ", department_picture_id=" + department_picture_id + ", department_caption=" + department_caption
				+ ", department_addtime=" + department_addtime + ", department_image_url=" + department_image_url +"]";
	}
}
