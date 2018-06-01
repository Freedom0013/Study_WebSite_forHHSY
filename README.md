Study\_WebSite\_forHHSY
======
一个学生在线做题及推荐资源的学习网站。
------
说明文档
------
项目开发环境为:<br>

* JDK：1.7.0_71
* IDE：MyEclipse 2014
* 数据库：MySQL 5.0
* 服务器：Tomcat 8.5
* 所使用技术：c3p0+Servlet+JSP

使用的数据库文件为：study_platform.sql<br>
数据库链接采用c3p0连接池，其设置文件在src/c3p0-config.xml(可根据需要酌情修改数据源设置)

请注意
------
* 由于现有数据库文件数据量较小，仅<br>
  `主页`--`信息工程系`--`计算机微机应用技术`--`java基础教程`<br>
  内容走得通，如有需要可不断扩充现有数据库数据容量，使网站其他院系数据可用。
* 部分功能尚未实现，待后续更新版本扩充，未实现功能例如：<br>
`科目收藏`、`全局登陆筛选`、`全站内容搜索`、`做题记录/成绩排行榜`、`修改头像`、`自建题库`等<br>
* 在扩充数据库时请注意

数据字典
------
**1.用户表（users）**
<table>
	<tr>
		<th width=18%,>名称</th>
		<th width=32%,>库字段名</th>
		<th width=10%,>类型</th>
		<th width=6%,>长度</th>
		<th width=10%,>可否为空</th>
		<th width="24%">备注</th>
	</tr>
	<tr>
	    <th width=18%,>用户编号</th>
		<th width=32%,>user_id</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>false</th>
		<th width="24%">Primary Key</th>
  	</tr>
  	<tr>
		<th width=18%,>用户头像编号</th>
		<th width=32%,>user_picture_id</th>
		<th width=10%,>bigint</th>
		<th width=6%,>20</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>用户名</th>
		<th width=32%,>user_name</th>
		<th width=10%,>varchar</th>
		<th width=6%,>30</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>用户密码</th>
		<th width=32%,>user_password</th>
		<th width=10%,>varchar</th>
		<th width=6%,>64</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>用户昵称</th>
		<th width=32%,>user_nickname</th>
		<th width=10%,>varchar</th>
		<th width=6%,>30</th>
		<th width=10%,>false</th>
		<th width="24%">默认自动赋值</th>
	</tr>
	<tr>
		<th width=18%,>用户注册时间</th>
		<th width=32%,>user_register_time</th>
		<th width=10%,>datetime</th>
		<th width=6%,></th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>用户真实姓名</th>
		<th width=32%,>user_realname</th>
		<th width=10%,>varchar</th>
		<th width=6%,>20</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>用户年龄</th>
		<th width=32%,>user_age</th>
		<th width=10%,>int</th>
		<th width=6%,>3</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>用户性别</th>
		<th width=32%,>user_gendar</th>
		<th width=10%,>int</th>
		<th width=6%,>2</th>
		<th width=10%,>true</th>
		<th width="24%">0:男 1:女</th>
	</tr>
	<tr>
		<th width=18%,>用户手机</th>
		<th width=32%,>user_phone</th>
		<th width=10%,>varchar</th>
		<th width=6%,>15</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>用户邮箱</th>
		<th width=32%,>user_email</th>
		<th width=10%,>varchar</th>
		<th width=6%,>50</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>分数信息编号</th>
		<th width=32%,>user_fraction_id</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>true</th>
		<th width="24%">等 fraction_id</th>
	</tr>
	<tr>
		<th width=18%,>用户学校</th>
		<th width=32%,>user_university</th>
		<th width=10%,>varchar</th>
		<th width=6%,>50</th>
		<th width=10%,>true</th>
		<th width="24%">备注</th>
	</tr>
	<tr>
		<th width=18%,>用户积分</th>
		<th width=32%,>user_integral</th>
		<th width=10%,>int</th>
		<th width=6%,>8</th>
		<th width=10%,>false</th>
		<th width="24%">default 0</th>
	</tr>
	<tr>
		<th width=18%,>用户归属地</th>
		<th width=32%,>user_city</th>
		<th width=10%,>varchar</th>
		<th width=6%,>100</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>用户qq</th>
		<th width=32%,>user_qq</th>
		<th width=10%,>bigint</th>
		<th width=6%,>35</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>最后登录时间</th>
		<th width=32%,>user_lastlogin_time</th>
		<th width=10%,>datetime</th>
		<th width=6%,></th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>用户年级</th>
		<th width=32%,>user_grade</th>
		<th width=10%,>int</th>
		<th width=6%,>30</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>用户等级</th>
		<th width=32%,>user_status</th>
		<th width=10%,>int</th>
		<th width=6%,>3</th>
		<th width=10%,>false</th>
		<th width="24%">default 0</th>
	</tr>
	<tr>
		<th width=18%,>管理员标识</th>
		<th width=32%,>user_admin_flag</th>
		<th width=10%,>int</th>
		<th width=6%,>3</th>
		<th width=10%,>false</th>
		<th width="24%">0:管理员 1:用户</th>
	</tr>
</table>

**2.图片表（pictures）**
<table>
	<tr>
		<th width=18%,>名称</th>
		<th width=32%,>库字段名</th>
		<th width=10%,>类型</th>
		<th width=6%,>长度</th>
		<th width=10%,>可否为空</th>
		<th width="24%">备注</th>
	</tr>
	<tr>
		<th width=18%,>图片编号</th>
		<th width=32%,>picture_id</th>
		<th width=10%,>bigint</th>
		<th width=6%,>30</th>
		<th width=10%,>false</th>
		<th width="24%">Primary Key</th>
	</tr>
	<tr>
		<th width=18%,>图片名称</th>
		<th width=32%,>picture_name</th>
		<th width=10%,>varchar</th>
		<th width=6%,>100</th>
		<th width=10%,>false</th>
		<th width="24%">有默认规则</th>
	</tr>
	<tr>
		<th width=18%,>图片描述</th>
		<th width=32%,>picture_caption</th>
		<th width=10%,>varchar</th>
		<th width=6%,>200</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>图片存储路径</th>
		<th width=32%,>picture_img</th>
		<th width=10%,>varchar</th>
		<th width=6%,>400</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>图片存储时间</th>
		<th width=32%,>picture_addtime</th>
		<th width=10%,>datetime</th>
		<th width=6%,></th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
</table>

**3.题目表（questions）**
<table>
	<tr>
		<th width=18%,>名称</th>
		<th width=32%,>库字段名</th>
		<th width=10%,>类型</th>
		<th width=6%,>长度</th>
		<th width=10%,>可否为空</th>
		<th width="24%">备注</th>
	</tr>
	<tr>
		<th width=18%,>题目编号</th>
		<th width=32%,>question_id</th>
		<th width=10%,>bigint</th>
		<th width=6%,>30</th>
		<th width=10%,>fasle</th>
		<th width="24%">Primary Key</th>
	</tr>
	<tr>
		<th width=18%,>题目题干</th>
		<th width=32%,>question_stem</th>
		<th width=10%,>varchar</th>
		<th width=6%,>2000</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>题目选项id</th>
		<th width=32%,>question_option_id</th>
		<th width=10%,>bigint</th>
		<th width=6%,>30</th>
		<th width=10%,>false</th>
		<th width="24%">判断题 -1</th>
	</tr>
	<tr>
		<th width=18%,>题目难度等级</th>
		<th width=32%,>question_level</th>
		<th width=10%,>int</th>
		<th width=6%,>10</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>题目答案</th>
		<th width=32%,>question_answer</th>
		<th width=10%,>varchar</th>
		<th width=6%,>1000</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>题目解析</th>
		<th width=32%,>question_analysis</th>
		<th width=10%,>varchar</th>
		<th width=6%,>2000</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>题目类型标识</th>
		<th width=32%,>question_type</th>
		<th width=10%,>int</th>
		<th width=6%,>4</th>
		<th width=10%,>fasle</th>
		<th width="24%">default 0，见补充</th>
	</tr>
	<tr>
		<th width=18%,>题目插入时间</th>
		<th width=32%,>question_addtime</th>
		<th width=10%,>datetime</th>
		<th width=6%,></th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>题目所属课程</th>
		<th width=32%,>question_course_id</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>false</th>
		<th width="24%">等course_id</th>
	</tr>
	<tr>
		<th width=18%,>题目所属章节</th>
		<th width=32%,>question_chapter</th>
		<th width=10%,>varchar</th>
		<th width=6%,>300</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
</table>

**补充**
<br>
**题目类型标识：0：单选题		1：多选题		2：判断题**
<br>
**题目难度等级：0：基础题		1：中级题		2：高级题**

**4.选项表（options）**
<table>
	<tr>
		<th width=18%,>名称</th>
		<th width=32%,>库字段名</th>
		<th width=10%,>类型</th>
		<th width=6%,>长度</th>
		<th width=10%,>可否为空</th>
		<th width="24%">备注</th>
	</tr>
	<tr>
		<th width=18%,>选项编号</th>
		<th width=32%,>option_id</th>
		<th width=10%,>bigint</th>
		<th width=6%,>30</th>
		<th width=10%,>false</th>
		<th width="24%">Primary Key</th>
	</tr>
	<tr>
		<th width=18%,>选项A</th>
		<th width=32%,>option_a</th>
		<th width=10%,>varchar</th>
		<th width=6%,>1000</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>选项B</th>
		<th width=32%,>option_b</th>
		<th width=10%,>varchar</th>
		<th width=6%,>1000</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>选项C</th>
		<th width=32%,>option_c</th>
		<th width=10%,>varchar</th>
		<th width=6%,>1000</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>选项D</th>
		<th width=32%,>option_d</th>
		<th width=10%,>varchar</th>
		<th width=6%,>1000</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>选项E</th>
		<th width=32%,>option_e</th>
		<th width=10%,>varchar</th>
		<th width=6%,>1000</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>选项F</th>
		<th width=32%,>option_f</th>
		<th width=10%,>varchar</th>
		<th width=6%,>1000</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>选项G</th>
		<th width=32%,>option_g</th>
		<th width=10%,>varchar</th>
		<th width=6%,>1000</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
</table>

**5.系别表（departments）**
<table>
	<tr>
		<th width=18%,>名称</th>
		<th width=32%,>库字段名</th>
		<th width=10%,>类型</th>
		<th width=6%,>长度</th>
		<th width=10%,>可否为空</th>
		<th width="24%">备注</th>
	</tr>
	<tr>
		<th width=18%,>系别编号</th>
		<th width=32%,>department_id</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>false</th>
		<th width="24%">Primary Key</th>
	</tr>
	<tr>
		<th width=18%,>系别名称</th>
		<th width=32%,>department_name</th>
		<th width=10%,>varchar</th>
		<th width=6%,>80</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>系别图片编号</th>
		<th width=32%,>department_picture_id</th>
		<th width=10%,>bigint</th>
		<th width=6%,>30</th>
		<th width=10%,>false</th>
		<th width="24%">default 0</th>
	</tr>
	<tr>
		<th width=18%,>系别描述</th>
		<th width=32%,>department_caption</th>
		<th width=10%,>varchar</th>
		<th width=6%,>300</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>系别添加时间</th>
		<th width=32%,>department_addtime</th>
		<th width=10%,>datetime</th>
		<th width=6%,></th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
</table>

**6.专业表（professions）**
<table>
	<tr>
		<th width=18%,>名称</th>
		<th width=32%,>库字段名</th>
		<th width=10%,>类型</th>
		<th width=6%,>长度</th>
		<th width=10%,>可否为空</th>
		<th width="24%">备注</th>
	</tr>
	<tr>
		<th width=18%,>专业编号</th>
		<th width=32%,>profession_id</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>false</th>
		<th width="24%">Primary Key</th>
	</tr>
	<tr>
		<th width=18%,>专业名称</th>
		<th width=32%,>profession_name</th>
		<th width=10%,>varchar</th>
		<th width=6%,>80</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>专业图片编号</th>
		<th width=32%,>profession_picture_id</th>
		<th width=10%,>bigint</th>
		<th width=6%,>30</th>
		<th width=10%,>false</th>
		<th width="24%">default 0</th>
	</tr>
	<tr>
		<th width=18%,>专业描述</th>
		<th width=32%,>profession_caption</th>
		<th width=10%,>varchar</th>
		<th width=6%,>150</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>专业添加时间</th>
		<th width=32%,>profession_addtime</th>
		<th width=10%,>datetime</th>
		<th width=6%,></th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>专业所属系别</th>
		<th width=32%,>profession_department_id</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>false</th>
		<th width="24%">等department_id</th>
	</tr>
</table>

**7.课程表（courses）**
<table>
	<tr>
		<th width=18%,>名称</th>
		<th width=32%,>库字段名</th>
		<th width=10%,>类型</th>
		<th width=6%,>长度</th>
		<th width=10%,>可否为空</th>
		<th width="24%">备注</th>
	</tr>
	<tr>
		<th width=18%,>课程编号</th>
		<th width=32%,>course_id</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>false</th>
		<th width="24%">Primary Key</th>
	</tr>
	<tr>
		<th width=18%,>课程名称</th>
		<th width=32%,>course_name</th>
		<th width=10%,>varchar</th>
		<th width=6%,>80</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>课程适用人群</th>
		<th width=32%,>course_applypeople</th>
		<th width=10%,>varchar</th>
		<th width=6%,>1000</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>课程概述</th>
		<th width=32%,>course_summary</th>
		<th width=10%,>varchar</th>
		<th width=6%,>1000</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>课程图片编号</th>
		<th width=32%,>course_picture_id</th>
		<th width=10%,>bigint</th>
		<th width=6%,>30</th>
		<th width=10%,>false</th>
		<th width="24%">default 0</th>
	</tr>
	<tr>
		<th width=18%,>课程目录标识</th>
		<th width=32%,>course_catalog_id</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>课程添加时间</th>
		<th width=32%,>course_addtime</th>
		<th width=10%,>datetime</th>
		<th width=6%,>长度</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>课程所属专业</th>
		<th width=32%,>course_profession_id</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>false</th>
		<th width="24%">等profession_id</th>
	</tr>
	<tr>
		<th width=18%,>课程所属年级</th>
		<th width=32%,>course_degree</th>
		<th width=10%,>int</th>
		<th width=6%,>5</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
</table>

**8.推荐资源表（resources）**
<table>
	<tr>
		<th width=18%,>名称</th>
		<th width=32%,>库字段名</th>
		<th width=10%,>类型</th>
		<th width=6%,>长度</th>
		<th width=10%,>可否为空</th>
		<th width="24%">备注</th>
	</tr>		
	<tr>
		<th width=18%,>资源编号</th>
		<th width=32%,>resource_id</th>
		<th width=10%,>bigint</th>
		<th width=6%,>30</th>
		<th width=10%,>false</th>
		<th width="24%">Primary Key</th>
	</tr>
	<tr>
		<th width=18%,>资源名称</th>
		<th width=32%,>resource_name</th>
		<th width=10%,>varchar</th>
		<th width=6%,>1000</th>
		<th width=10%,>flase</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>资源详情</th>
		<th width=32%,>resource_detail</th>
		<th width=10%,>varchar</th>
		<th width=6%,>2000</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>资源分类</th>
		<th width=32%,>resource_type</th>
		<th width=10%,>int</th>
		<th width=6%,>10</th>
		<th width=10%,>false</th>
		<th width="24%">1:视频2:书籍</th>
	</tr>		
	<tr>
		<th width=18%,>资源描述</th>
		<th width=32%,>resource_caption</th>
		<th width=10%,>varchar</th>
		<th width=6%,>300</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>资源所属等级</th>
		<th width=32%,>resource_degree</th>
		<th width=10%,>int</th>
		<th width=6%,>10</th>
		<th width=10%,>false</th>
		<th width="24%">见补充</th>
	</tr>
	<tr>
		<th width=18%,>资源添加时间</th>
		<th width=32%,>resource_addtime</th>
		<th width=10%,>datetime</th>
		<th width=6%,></th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>资源所属课程</th>
		<th width=32%,>recource_course_id</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>false</th>
		<th width="24%">等course_id</th>
	</tr>
</table>

**补充：资源所属等级说明：**
<br>
**1：100-80 高级资源**
<br>
**2：80-60 中级资源**
<br>
**3：60以下 基础资源**

**9.用户收藏表（collects）**
<table>
	<tr>
		<th width=18%,>名称</th>
		<th width=32%,>库字段名</th>
		<th width=10%,>类型</th>
		<th width=6%,>长度</th>
		<th width=10%,>可否为空</th>
		<th width="24%">备注</th>
	</tr>
	<tr>
		<th width=18%,>收藏编号</th>
		<th width=32%,>collect_id</th>
		<th width=10%,>bigint</th>
		<th width=6%,>30</th>
		<th width=10%,>false</th>
		<th width="24%">Primary Key</th>
	</tr>
	<tr>
		<th width=18%,>收藏用户编号</th>
		<th width=32%,>collect_user_id</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>false</th>
		<th width="24%">等user_id</th>
	</tr>
	<tr>
		<th width=18%,>收藏课程编号</th>
		<th width=32%,>collect_course_id</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>false</th>
		<th width="24%">等course_id</th>
	</tr>
	<tr>
		<th width=18%,>收藏时间</th>
		<th width=32%,>collect_addtime</th>
		<th width=10%,>datetime</th>
		<th width=6%,></th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>		
</table>

**10.官方默认组卷表（officialtests）**
<table>
	<tr>
		<th width=18%,>名称</th>
		<th width=32%,>库字段名</th>
		<th width=10%,>类型</th>
		<th width=6%,>长度</th>
		<th width=10%,>可否为空</th>
		<th width="24%">备注</th>
	</tr>
	<tr>
		<th width=18%,>试卷编号</th>
		<th width=32%,>officialtest_id</th>
		<th width=10%,>bigint</th>
		<th width=6%,>30</th>
		<th width=10%,>false</th>
		<th width="24%">Primary Key</th>
	</tr>
	<tr>
		<th width=18%,>试卷名称</th>
		<th width=32%,>officialtest_name</th>
		<th width=10%,>varchar</th>
		<th width=6%,>300</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>试卷生成时间</th>
		<th width=32%,>officialtest_addtime</th>
		<th width=10%,>datetime</th>
		<th width=6%,></th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>试卷内容</th>
		<th width=32%,>officialtest_content</th>
		<th width=10%,>varchar</th>
		<th width=6%,>1000</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>试卷描述</th>
		<th width=32%,>officialtest_caption</th>
		<th width=10%,>varchar</th>
		<th width=6%,>300</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>试卷所属系别</th>
		<th width=32%,>officialtest_dep_id</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>false</th>
		<th width="24%">等department_id</th>
	</tr>
	<tr>
		<th width=18%,>试卷所属专业</th>
		<th width=32%,>officialtest_pro_id</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>false</th>
		<th width="24%">等profession_id</th>
	</tr>
	<tr>
		<th width=18%,>试卷所属课程</th>
		<th width=32%,>officialtest_course_id</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>fasle</th>
		<th width="24%">等course_id</th>
	</tr>
	<tr>
		<th width=18%,>试卷难度</th>
		<th width=32%,>officialtest_difficulty</th>
		<th width=10%,>varchar</th>
		<th width=6%,>5</th>
		<th width=10%,>false</th>
		<th width="24%">default 3  1~5级</th>
	</tr>
</table>

**11.用户自定义组卷表（待定功能）（userstests）**
<table>
	<tr>
		<th width=18%,>名称</th>
		<th width=32%,>库字段名</th>
		<th width=10%,>类型</th>
		<th width=6%,>长度</th>
		<th width=10%,>可否为空</th>
		<th width="24%">备注</th>
	</tr>
	<tr>
		<th width=18%,>试卷编号</th>
		<th width=32%,>userstest_id</th>
		<th width=10%,>bigint</th>
		<th width=6%,>30</th>
		<th width=10%,>false</th>
		<th width="24%">Primary Key</th>
	</tr>
	<tr>
		<th width=18%,>试卷名称</th>
		<th width=32%,>userstest_name</th>
		<th width=10%,>varchar</th>
		<th width=6%,>300</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>试卷生成时间</th>
		<th width=32%,>userstest_addtime</th>
		<th width=10%,>datetime</th>
		<th width=6%,></th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>				
	<tr>
		<th width=18%,>试卷内容id</th>
		<th width=32%,>userstest_content_id</th>
		<th width=10%,>bigint</th>
		<th width=6%,>30</th>
		<th width=10%,>false</th>
		<th width="24%">等 contents_id</th>
	</tr>
	<tr>
		<th width=18%,>试卷描述</th>
		<th width=32%,>userstest_caption</th>
		<th width=10%,>varchar</th>
		<th width=6%,>300</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>试卷所属系别</th>
		<th width=32%,>userstest_dep_id</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>false</th>
		<th width="24%">等department_id</th>
	</tr>		
	<tr>
		<th width=18%,>试卷所属专业</th>
		<th width=32%,>userstest_pro_id</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>fasle</th>
		<th width="24%">等profession_id</th>
	</tr>
	<tr>
		<th width=18%,>试卷所属课程</th>
		<th width=32%,>userstest_course_id</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>fasle</th>
		<th width="24%">等course_id</th>
	</tr>
	<tr>
		<th width=18%,>试卷难度</th>
		<th width=32%,>userstest_difficulty</th>
		<th width=10%,>varchar</th>
		<th width=6%,>5</th>
		<th width=10%,>fasle</th>
		<th width="24%">default 3  1~5级</th>
	</tr>
	<tr>
		<th width=18%,>试卷生成人</th>
		<th width=32%,>userstest_user_id</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>fasle</th>
		<th width="24%">等user_id</th>
	</tr>
</table>

**12.用户自定义组卷内容表（待定）（userstests_contents）**
<table>
	<tr>
		<th width=18%,>名称</th>
		<th width=32%,>库字段名</th>
		<th width=10%,>类型</th>
		<th width=6%,>长度</th>
		<th width=10%,>可否为空</th>
		<th width="24%">备注</th>
	</tr>
	<tr>
		<th width=18%,>试卷内容编号</th>
		<th width=32%,>contents_id</th>
		<th width=10%,>bigint</th>
		<th width=6%,>30</th>
		<th width=10%,>false</th>
		<th width="24%">Primary Key</th>
	</tr>
	<tr>
		<th width=18%,>单选题数量</th>
		<th width=32%,>contents_single_num</th>
		<th width=10%,>int</th>
		<th width=6%,>10</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>单选题分数</th>
		<th width=32%,>contents_single_score</th>
		<th width=10%,>int</th>
		<th width=6%,>100</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>单选题难度</th>
		<th width=32%,>contents_single_difficulty</th>
		<th width=10%,>int</th>
		<th width=6%,>10</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>多选题数量</th>
		<th width=32%,>contents_mul_num</th>
		<th width=10%,>int</th>
		<th width=6%,>10</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>				
	<tr>
		<th width=18%,>多选题分数</th>
		<th width=32%,>contents_mul_score</th>
		<th width=10%,>int</th>
		<th width=6%,>100</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>多选题难度</th>
		<th width=32%,>contents_mul_difficulty</th>
		<th width=10%,>int</th>
		<th width=6%,>10</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>判断题数量</th>
		<th width=32%,>contents_judge_num</th>
		<th width=10%,>int</th>
		<th width=6%,>10</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>判断题分数</th>
		<th width=32%,>contents_judge_score</th>
		<th width=10%,>int</th>
		<th width=6%,>100</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>判断题难度</th>
		<th width=32%,>contents_judge_difficulty</th>
		<th width=10%,>int</th>
		<th width=6%,>10</th>
		<th width=10%,>true</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>总分</th>
		<th width=32%,>contents_allscore</th>
		<th width=10%,>int</th>
		<th width=6%,>100</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
</table>

**题目难度等级：0：基础题		1：中级题		2：高级题**


**13.用户分数历史信息表（fractions）**
<table>
	<tr>
		<th width=18%,>名称</th>
		<th width=32%,>库字段名</th>
		<th width=10%,>类型</th>
		<th width=6%,>长度</th>
		<th width=10%,>可否为空</th>
		<th width="24%">备注</th>
	</tr>
	<tr>
		<th width=18%,>分数信息编号</th>
		<th width=32%,>fraction_id</th>
		<th width=10%,>bigint</th>
		<th width=6%,>30</th>
		<th width=10%,>false</th>
		<th width="24%">Primary Key</th>
	</tr>
	<tr>
		<th width=18%,>分数</th>
		<th width=32%,>fraction_content</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>分数产生时间</th>
		<th width=32%,>fraction_addtime</th>
		<th width=10%,>datetime</th>
		<th width=6%,></th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>分数所属课程</th>
		<th width=32%,>fraction_course_id</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
	<tr>
		<th width=18%,>分数所属用户</th>
		<th width=32%,>fraction_user_id</th>
		<th width=10%,>int</th>
		<th width=6%,>20</th>
		<th width=10%,>false</th>
		<th width="24%"></th>
	</tr>
</table>





写在最后
-----
		本项目为基础项目，功能实现较为单一，且尚不完善适合初学者使用。不可用于商业。
**本项目中思路可能仅为实现思路，并非最简，如有Bug请帮助指正，大家相互学习。**
