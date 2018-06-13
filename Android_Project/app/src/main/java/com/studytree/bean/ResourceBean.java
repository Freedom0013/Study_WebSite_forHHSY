package com.studytree.bean;

import java.math.BigDecimal;

/**
 * 推荐资源Bean
 * Title: ResourceBean
 * @date 2018年1月26日
 * @author Freedom0013
 */
public class ResourceBean {
	/** 资源编号 */
	private BigDecimal resource_id;
	/** 资源名称 */
	private String resource_name;
	/** 资源详情 */
	private String resource_detail;
	/** 资源分类 */
	private int resource_type;
	/** 资源描述 */
	private String resource_caption;
	/** 资源等级:1高级 2中级 3基础 */
	private int resource_degree;
	/** 资源添加时间 */
	private String resource_addtime;
	/** 资源所属课程 */
	private int resource_course_id;
	/** 资源所述图片id */
	private BigDecimal resource_picture_id;
	
	/**
	 * 空参构造函数
	 */
	public ResourceBean() {
		super();
	}
	
	/**
	 * 全参构造函数
	 */
	public ResourceBean(BigDecimal resource_id, String resource_name, String resource_detail, int resource_type,
			String resource_caption, int resource_degree, String resource_addtime, int resource_course_id, BigDecimal resource_picture_id) {
		super();
		this.resource_id = resource_id;
		this.resource_name = resource_name;
		this.resource_detail = resource_detail;
		this.resource_type = resource_type;
		this.resource_caption = resource_caption;
		this.resource_degree = resource_degree;
		this.resource_addtime = resource_addtime;
		this.resource_course_id = resource_course_id;
		this.resource_picture_id = resource_picture_id;
	}
	
	public BigDecimal getResource_id() {
		return resource_id;
	}
	public void setResource_id(BigDecimal resource_id) {
		this.resource_id = resource_id;
	}
	public String getResource_name() {
		return resource_name;
	}
	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}
	public String getResource_detail() {
		return resource_detail;
	}
	public void setResource_detail(String resource_detail) {
		this.resource_detail = resource_detail;
	}
	public int getResource_type() {
		return resource_type;
	}
	public void setResource_type(int resource_type) {
		this.resource_type = resource_type;
	}
	public String getResource_caption() {
		return resource_caption;
	}
	public void setResource_caption(String resource_caption) {
		this.resource_caption = resource_caption;
	}
	public int getResource_degree() {
		return resource_degree;
	}
	public void setResource_degree(int resource_degree) {
		this.resource_degree = resource_degree;
	}
	public String getResource_addtime() {
		return resource_addtime;
	}
	public void setResource_addtime(String resource_addtime) {
		this.resource_addtime = resource_addtime;
	}
	public int getResource_course_id() {
		return resource_course_id;
	}
	public void setResource_course_id(int resource_course_id) {
		this.resource_course_id = resource_course_id;
	}
	public BigDecimal getResource_picture_id() {
        return resource_picture_id;
    }
    public void setResource_picture_id(BigDecimal resource_picture_id) {
        this.resource_picture_id = resource_picture_id;
    }

    @Override
    public String toString() {
        return "ResourceBean [resource_id=" + resource_id + ", resource_name=" + resource_name + ", resource_detail="
                + resource_detail + ", resource_type=" + resource_type + ", resource_caption=" + resource_caption
                + ", resource_degree=" + resource_degree + ", resource_addtime=" + resource_addtime
                + ", resource_course_id=" + resource_course_id + ", resource_picture_id=" + resource_picture_id + "]";
    }
}
