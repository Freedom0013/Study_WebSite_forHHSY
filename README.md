Study\_WebSite\_forHHSY
======
一个学生在线做题及推荐资源的学习网站。
------
##说明
项目开发环境为:<br>

* JDK：1.7.0_71
* IDE：MyEclipse 2014
* 数据库：MySQL 5.0
* 服务器：Tomcat 8.5
* 所使用技术：c3p0+Servlet+JSP

使用的数据库文件为：study_platform.sql<br>
数据库链接采用c3p0连接池，其设置文件在src/c3p0-config.xml(可根据需要酌情修改数据源设置)

###请注意
* 由于现有数据库文件数据量较小，仅<br>
  `主页`--`信息工程系`--`计算机微机应用技术`--`java基础教程`<br>
  内容走得通，如有需要可不断扩充现有数据库数据容量，使网站其他院系数据可用。
* 部分功能尚未实现，待后续更新版本扩充，未实现功能例如：<br>
`科目收藏`、`全局登陆筛选`、`全站内容搜索`、`做题记录/成绩排行榜`、`修改头像`、`自建题库`等<br>
* 在扩充数据库时请注意

##数据字典
###1.用户表
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

##图片表
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

##写在最后
######本项目为基础项目，功能实现较为单一，且尚不完善适合初学者使用。不可用于商业。
**本项目中思路可能仅为实现思路，并非最简，如有Bug请帮助指正，大家相互学习。**
![test](https://github.com/Freedom0013/Study_WebSite_forHHSY/blob/master/Study_Platform_website/WebRoot/images/1383531804.jpg)