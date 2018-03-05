package com.studyplatform.web.bean;

import java.math.BigDecimal;

/**
 * 用户组卷Bean
 * Title: User_TestBean
 * @date 2018年1月26日
 * @author Freedom0013
 */
public class User_TestBean {
	/** 试卷编号 */
	private BigDecimal userstest_id;
	/** 试卷名称 */
	private String userstest_name;
	/** 试卷添加时间 */
	private String userstest_addtime;
	/** 试卷内容id */
	private BigDecimal userstest_content_id;
	/** 试卷描述 */
	private String userstest_caption;
	/** 试卷所属系别 */
	private int userstest_dep_id;
	/** 试卷所属专业 */
	private int userstest_pro_id;
	/** 试卷所属课程 */
	private int userstest_course_id;
	/** 试卷难度默认3 1~5级 */
	private String userstest_difficulty;
	/** 出题人id */
	private int userstest_user_id;
	
	/**
	 * 空参构造函数
	 */
	public User_TestBean() {
		super();
	}

	/**
	 * 全参数构造函数
	 */
	public User_TestBean(BigDecimal userstest_id, String userstest_name, String userstest_addtime,
			BigDecimal userstest_content_id, String userstest_caption, int userstest_dep_id, int userstest_pro_id,
			int userstest_course_id, String userstest_difficulty, int userstest_user_id) {
		super();
		this.userstest_id = userstest_id;
		this.userstest_name = userstest_name;
		this.userstest_addtime = userstest_addtime;
		this.userstest_content_id = userstest_content_id;
		this.userstest_caption = userstest_caption;
		this.userstest_dep_id = userstest_dep_id;
		this.userstest_pro_id = userstest_pro_id;
		this.userstest_course_id = userstest_course_id;
		this.userstest_difficulty = userstest_difficulty;
		this.userstest_user_id = userstest_user_id;
	}

	public BigDecimal getUserstest_id() {
		return userstest_id;
	}

	public void setUserstest_id(BigDecimal userstest_id) {
		this.userstest_id = userstest_id;
	}

	public String getUserstest_name() {
		return userstest_name;
	}

	public void setUserstest_name(String userstest_name) {
		this.userstest_name = userstest_name;
	}

	public String getUserstest_addtime() {
		return userstest_addtime;
	}

	public void setUserstest_addtime(String userstest_addtime) {
		this.userstest_addtime = userstest_addtime;
	}

	public BigDecimal getUserstest_content_id() {
		return userstest_content_id;
	}

	public void setUserstest_content_id(BigDecimal userstest_content_id) {
		this.userstest_content_id = userstest_content_id;
	}

	public String getUserstest_caption() {
		return userstest_caption;
	}

	public void setUserstest_caption(String userstest_caption) {
		this.userstest_caption = userstest_caption;
	}

	public int getUserstest_dep_id() {
		return userstest_dep_id;
	}

	public void setUserstest_dep_id(int userstest_dep_id) {
		this.userstest_dep_id = userstest_dep_id;
	}

	public int getUserstest_pro_id() {
		return userstest_pro_id;
	}

	public void setUserstest_pro_id(int userstest_pro_id) {
		this.userstest_pro_id = userstest_pro_id;
	}

	public int getUserstest_course_id() {
		return userstest_course_id;
	}

	public void setUserstest_course_id(int userstest_course_id) {
		this.userstest_course_id = userstest_course_id;
	}

	public String getUserstest_difficulty() {
		return userstest_difficulty;
	}

	public void setUserstest_difficulty(String userstest_difficulty) {
		this.userstest_difficulty = userstest_difficulty;
	}

	public int getUserstest_user_id() {
		return userstest_user_id;
	}

	public void setUserstest_user_id(int userstest_user_id) {
		this.userstest_user_id = userstest_user_id;
	}

	@Override
	public String toString() {
		return "User_TestBean [userstest_id=" + userstest_id + ", userstest_name=" + userstest_name
				+ ", userstest_addtime=" + userstest_addtime + ", userstest_content_id=" + userstest_content_id
				+ ", userstest_caption=" + userstest_caption + ", userstest_dep_id=" + userstest_dep_id
				+ ", userstest_pro_id=" + userstest_pro_id + ", userstest_course_id=" + userstest_course_id
				+ ", userstest_difficulty=" + userstest_difficulty + ", userstest_user_id=" + userstest_user_id + "]";
	}
}
