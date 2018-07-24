package com.studytree.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 推荐资源Bean
 * Title: ResourceBean
 * @date 2018年1月26日
 * @author Freedom0013
 */
public class ResourceBean implements Serializable{
	/** 资源编号 */
	public BigDecimal resource_id;
	/** 资源名称 */
	public String resource_name;
	/** 资源详情 */
	public String resource_detail;
	/** 资源分类 */
	public int resource_type;
	/** 资源描述 */
	public String resource_caption;
	/** 资源等级:1高级 2中级 3基础 */
	public int resource_degree;
	/** 资源添加时间 */
	public String resource_addtime;
	/** 资源所属课程 */
	public int resource_course_id;
	/** 资源所述图片id */
	public BigDecimal resource_picture_id;
	/** 资源所述图片 */
	public String resource_image_url;

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
			String resource_caption, int resource_degree, String resource_addtime, int resource_course_id, BigDecimal resource_picture_id,String resource_image_url) {
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
		this.resource_image_url = resource_image_url;
	}

    @Override
    public String toString() {
        return "ResourceBean [resource_id=" + resource_id + ", resource_name=" + resource_name + ", resource_detail="
                + resource_detail + ", resource_type=" + resource_type + ", resource_caption=" + resource_caption
                + ", resource_degree=" + resource_degree + ", resource_addtime=" + resource_addtime
                + ", resource_course_id=" + resource_course_id + ", resource_picture_id=" + resource_picture_id
                + ", resource_image_url=" + resource_image_url + "]";
    }
}
