/*
 Navicat MySQL Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50022
 Source Host           : localhost:3306
 Source Schema         : study_platform

 Target Server Type    : MySQL
 Target Server Version : 50022
 File Encoding         : 65001

 Date: 01/06/2018 18:24:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for collects
-- ----------------------------
DROP TABLE IF EXISTS `collects`;
CREATE TABLE `collects`  (
  `collect_id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '收藏编号',
  `collect_user_id` int(20) NOT NULL DEFAULT '' COMMENT '收藏用户编号',
  `collect_course_id` int(20) NOT NULL DEFAULT '' COMMENT '收藏课程编号',
  `collect_addtime` datetime NOT NULL DEFAULT '' COMMENT '收藏添加时间',
  PRIMARY KEY USING BTREE (`collect_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of collects
-- ----------------------------
INSERT INTO `collects` VALUES (1, 1, 1, '2018-01-26 16:31:10');
INSERT INTO `collects` VALUES (2, 1, 3, '2018-01-26 16:31:28');
INSERT INTO `collects` VALUES (3, 1, 5, '2018-01-26 16:31:39');

-- ----------------------------
-- Table structure for courses
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses`  (
  `course_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '课程编号',
  `course_name` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '课程名称',
  `course_applypeople` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '课程适用人群',
  `course_summary` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '课程概述',
  `course_picture_id` bigint(30) NOT NULL DEFAULT 0 COMMENT '课程图片编号',
  `course_catalog_id` int(20) NULL DEFAULT NULL COMMENT '课程目录标识',
  `course_addtime` datetime NOT NULL DEFAULT '' COMMENT '课程添加时间',
  `course_profession_id` int(20) NOT NULL DEFAULT '' COMMENT '课程所属专业',
  `course_degree` int(5) NOT NULL DEFAULT '' COMMENT '课程所属年级',
  PRIMARY KEY USING BTREE (`course_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of courses
-- ----------------------------
INSERT INTO `courses` VALUES (1, 'Java基础教程', '适用于初级学习人群', 'Java是一种可以撰写跨平台应用软件的面向对象的程序设计语言，是由Sun Microsystems公司于1995年5月推出的Java程序设计语言和Java平台（即JavaSE, JavaEE, JavaME）的总称。Java 技术具有卓越的通用性、高效性、平台移植性和安全性，广泛应用于个人PC、数据中心、游戏控制台、科学超级计算机、移动电话和互联网，同时拥有全球最大的开发者专业社群。在全球云计算和移动互联网的产业环境下，Java更具备了显著优势和广阔前景。', 48, 0, '2018-01-25 17:53:17', 1, 1);
INSERT INTO `courses` VALUES (2, 'Html网页设计', '适用于初学网页人群', '网页设计的工作目标，是通过使用更合理的颜色、字体、图片、样式进行页面设计美化，在功能限定的情况下，尽可能给予用户完美的视觉体验。高级的网页设计甚至会考虑到通过声光、交互等来实现更好的试听感受。', 49, 0, '2018-01-25 17:54:32', 1, 1);
INSERT INTO `courses` VALUES (3, 'PhotoShop基础教程', '适用于初学PS人群', '《photoshop基础教程》包括图像处理的基础知识和基本操作、选区的绘制与编辑、图像的编辑、路径的使用、文字与矢量图形处理、图像色调与色彩调整、图层和通道等重要调板的应用、滤镜特效、各种新颖特效字的制作及网页特效元素的设计等内容。', 50, 0, '2018-01-25 17:55:27', 1, 1);
INSERT INTO `courses` VALUES (4, '计算机基础', '适用于初步接触计算机人群', '介绍了计算机的各种硬件和软件知识，并以简单易学的方法，把计算机理论知识与实际应用巧妙结合起来，为读者认识计算机，进一步学习计算机软硬件知识打下坚实基础。\r\n本书多达329道复习题、315道选择题、325道填空题，是高等院校““计算机应用”课程的理想教材。', 51, 0, '2018-01-25 17:56:24', 1, 1);
INSERT INTO `courses` VALUES (5, 'JavaWeb基础教程', '适用于Web入门人群', '在开发动态网站时，离不开服务器端技术。目前，比较常用的服务器端技术包括CGI、ASP、PHP、ASP.NET和JSP等。', 52, 0, '2018-01-25 17:57:06', 1, 2);
INSERT INTO `courses` VALUES (6, 'JavaScript基础教程', '适用于JS入门人群', 'Javascript 脚本语言同其他语言一样，有它自身的基本数据类型，表达式和算术运算符及程序的基本程序框架。Javascript 提供了四种基本的数据类型和两种特殊数据类型用来处理数据和文字。而变量提供存放信息的地方，表达式则可以完成较复杂的信息处理。', 53, 0, '2018-01-25 17:57:49', 1, 2);
INSERT INTO `courses` VALUES (7, '软件工程', '软件工程入门人群', '在现代社会中，软件应用于多个方面。典型的软件有电子邮件、嵌入式系统、人机界面、办公套件、操作系统、编译器、数据库、游戏等。同时，各个行业几乎都有计算机软件的应用，如工业、农业、银行、航空、政府部门等。这些应用促进了经济和社会的发展，也提高了工作效率和生活效率 。', 54, 0, '2018-01-25 17:58:23', 1, 2);
INSERT INTO `courses` VALUES (8, 'Android入门', 'Android开发入门人群', 'android入门教程之android高级应用，引入实战型的迭代软件工程管理，并且侧重应用型软件开发教学。android高级应用面向对移动通讯技术感兴趣的技术人员,有一定开发基础的的技术人员，企业CIO及相关信息化部门成员，参与培训人员最好有一定的Linux系统基础。', 55, 0, '2018-01-25 17:58:59', 1, 2);
INSERT INTO `courses` VALUES (9, '数据库技术', 'MySQL数据库入门人群', '数据库技术研究和管理的对象是数据，所以数据库技术所涉及的具体内容主要包括：通过对数据的统一组织和管理，按照指定的结构建立相应的数据库和数据仓库；利用数据库管理系统和数据挖掘系统设计出能够实现对数据库中的数据进行添加、修改、删除、处理、分析、理解、报表和打印等多种功能的数据管理和数据挖掘应用系统；并利用应用管理系统最终实现对数据的处理、分析和理解。', 56, 0, '2018-01-25 17:59:44', 1, 2);
INSERT INTO `courses` VALUES (10, 'PHP网页设计', 'PHP入门开发人群', '本书共分10章。主要内容包括：配置PHP开发环境、PHP语言基础、数组与函数、字符串与正则表达式、构建PHP互动网页、PHP文件编程、PHP图像处理、MySQL数据库管理、PHP数据库编程、会员管理系统设计。', 57, 0, '2018-01-25 18:00:19', 1, 2);

-- ----------------------------
-- Table structure for departments
-- ----------------------------
DROP TABLE IF EXISTS `departments`;
CREATE TABLE `departments`  (
  `department_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '系别编号',
  `department_name` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '系别名称',
  `department_picture_id` bigint(30) NOT NULL DEFAULT 0 COMMENT '系别图片编号',
  `department_caption` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系别描述',
  `department_addtime` datetime NOT NULL DEFAULT '' COMMENT '系别添加时间',
  PRIMARY KEY USING BTREE (`department_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of departments
-- ----------------------------
INSERT INTO `departments` VALUES (1, '水利工程学院', 13, '水利系', '2018-01-25 17:43:13');
INSERT INTO `departments` VALUES (2, '土木与交通工程学院', 14, '土木系', '2018-01-25 17:43:58');
INSERT INTO `departments` VALUES (3, '测绘工程学院', 8, '测绘系', '2018-01-25 17:44:18');
INSERT INTO `departments` VALUES (4, '机械工程学院', 11, '机电系', '2018-01-25 17:44:45');
INSERT INTO `departments` VALUES (5, '国际教育学院', 10, '国际教育系', '2018-01-25 17:45:14');
INSERT INTO `departments` VALUES (6, '环境与化学工程系', 18, '环化系', '2018-01-25 17:45:49');
INSERT INTO `departments` VALUES (7, '信息工程系', 15, '信息系', '2018-01-25 17:46:06');
INSERT INTO `departments` VALUES (8, '自动化工程系', 17, '自动化系', '2018-01-25 17:46:28');
INSERT INTO `departments` VALUES (9, '财经系', 7, '财经系', '2018-01-25 17:46:59');
INSERT INTO `departments` VALUES (10, '管理系', 9, '管理系', '2018-01-25 17:47:15');
INSERT INTO `departments` VALUES (11, '旅游系', 12, '旅游系', '2018-01-25 17:47:35');
INSERT INTO `departments` VALUES (12, '艺术系(艺术教育中心)', 16, '艺术系', '2018-01-25 17:48:01');

-- ----------------------------
-- Table structure for fractions
-- ----------------------------
DROP TABLE IF EXISTS `fractions`;
CREATE TABLE `fractions`  (
  `fraction_id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '分数信息编号',
  `fraction_content` int(20) NOT NULL DEFAULT '' COMMENT '分数',
  `fraction_addtime` datetime NOT NULL DEFAULT '' COMMENT '分数产生时间',
  `fraction_course_id` int(20) NOT NULL DEFAULT '' COMMENT '分数所属课程',
  `fraction_user_id` int(20) NOT NULL DEFAULT '' COMMENT '分数所属用户',
  PRIMARY KEY USING BTREE (`fraction_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of fractions
-- ----------------------------
INSERT INTO `fractions` VALUES (1, 86, '2018-01-26 16:32:52', 1, 1);
INSERT INTO `fractions` VALUES (2, 66, '2018-01-24 16:34:03', 1, 1);
INSERT INTO `fractions` VALUES (3, 75, '2018-01-25 16:34:24', 1, 1);
INSERT INTO `fractions` VALUES (4, 57, '2018-02-05 21:02:33', 1, 2);
INSERT INTO `fractions` VALUES (5, 66, '2018-02-15 21:02:56', 1, 2);
INSERT INTO `fractions` VALUES (6, 68, '2018-02-22 21:03:10', 1, 2);
INSERT INTO `fractions` VALUES (7, 91, '2018-02-27 21:03:35', 1, 4);
INSERT INTO `fractions` VALUES (8, 90, '2018-02-26 21:04:08', 1, 4);
INSERT INTO `fractions` VALUES (9, 75, '2018-02-20 17:22:20', 1, 5);
INSERT INTO `fractions` VALUES (10, 50, '2018-02-27 21:39:57', 1, 5);
INSERT INTO `fractions` VALUES (11, 53, '2018-02-27 21:41:24', 1, 5);

-- ----------------------------
-- Table structure for officialtests
-- ----------------------------
DROP TABLE IF EXISTS `officialtests`;
CREATE TABLE `officialtests`  (
  `officialtest_id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '试卷编号',
  `officialtest_name` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '试卷名称',
  `officialtest_addtime` datetime NOT NULL DEFAULT '' COMMENT '试卷添加时间',
  `officialtest_content` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '试卷内容',
  `officialtest_caption` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '试卷描述',
  `officialtest_dep_id` int(20) NOT NULL DEFAULT '' COMMENT '试卷所属系别',
  `officialtest_pro_id` int(20) NOT NULL DEFAULT '' COMMENT '试卷所属专业',
  `officialtest_course_id` int(20) NOT NULL DEFAULT '' COMMENT '试卷所属课程',
  `officialtest_difficulty` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '3' COMMENT '试卷难度默认3  0~5级',
  PRIMARY KEY USING BTREE (`officialtest_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for options
-- ----------------------------
DROP TABLE IF EXISTS `options`;
CREATE TABLE `options`  (
  `option_id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '选项编号',
  `option_a` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '选项A',
  `option_b` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '选项B',
  `option_c` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '选项C',
  `option_d` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '选项D',
  `option_e` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项E',
  `option_f` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项F',
  `option_g` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选项G',
  PRIMARY KEY USING BTREE (`option_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of options
-- ----------------------------
INSERT INTO `options` VALUES (1, 'sleep是线程类（Thread）的方法，wait是Object类的方法；', 'sleep不释放对象锁，wait放弃对象锁；', 'sleep暂停线程、但监控状态仍然保持，结束后会自动恢复；', 'wait后进入等待锁定池，只有针对此对象发出notify方法后获得对象锁进入运行状态。', NULL, NULL, NULL);
INSERT INTO `options` VALUES (2, 'setLayout(aLayoutManager);', 'addLayout(aLayoutManager);', 'layout(aLayoutManager);', 'setLayoutManager(aLayoutManager);', NULL, NULL, NULL);
INSERT INTO `options` VALUES (3, 'java.sql', 'java.awt', 'java.lang', 'java.swing', NULL, NULL, NULL);
INSERT INTO `options` VALUES (4, 'm.length()', 'm.length', 'm.length()+1', 'm.length+1', NULL, NULL, NULL);
INSERT INTO `options` VALUES (5, '通过调用stop()方法而停止的线程', '通过调用sleep()方法而停止的线程', '通过调用wait()方法而停止的线程', '通过调用suspend()方法而停止的线程', NULL, NULL, NULL);
INSERT INTO `options` VALUES (6, 'Factory', 'Abstract Factory', 'Singleton', 'Builder', NULL, NULL, NULL);
INSERT INTO `options` VALUES (7, 'public int MAX_LENGTH=1', 'final int MAX_LENGTH=1', 'final public int MAX_LENGTH=1', 'public final int MAX_LENGTH=1', NULL, NULL, NULL);
INSERT INTO `options` VALUES (8, '线程对象必须实现Runnable接口', '启动一个线程直接调用线程对象的run()方法', 'Java提供对多线程同步提供语言级的支持', ' 一个线程可以包含多个进程', NULL, NULL, NULL);
INSERT INTO `options` VALUES (9, 'ArrayList myList = new Object()', 'List myList = new ArrayList()', 'ArraylList myList = new List()', 'List myList = new List()', NULL, NULL, NULL);
INSERT INTO `options` VALUES (10, '2', '3', '4', '5', NULL, NULL, NULL);
INSERT INTO `options` VALUES (11, '数据链路层', '网络层', '传输层', '逻辑层', NULL, NULL, NULL);
INSERT INTO `options` VALUES (12, '6', '7', '8', '9', NULL, NULL, NULL);
INSERT INTO `options` VALUES (13, '星型', '总线型', '树型', '环型', NULL, NULL, NULL);
INSERT INTO `options` VALUES (14, '鼠标', '磁盘', '键盘', '显示器', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for pictures
-- ----------------------------
DROP TABLE IF EXISTS `pictures`;
CREATE TABLE `pictures`  (
  `picture_id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '图片编号',
  `picture_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片名称',
  `picture_caption` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '图片描述',
  `picture_img` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '图片存储路径',
  `picture_addtime` datetime NOT NULL DEFAULT '' COMMENT '图片储存时间',
  PRIMARY KEY USING BTREE (`picture_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 61 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of pictures
-- ----------------------------
INSERT INTO `pictures` VALUES (1, '院系默认图片', '用于首页院系默认图片', 'images/system_image/department_default_icon.png', '2018-02-28 18:17:45');
INSERT INTO `pictures` VALUES (2, '专业默认图片', '用于院系下辖专业默认图片', 'images/system_image/profession_default_icon.jpg', '2018-02-28 18:20:12');
INSERT INTO `pictures` VALUES (3, '课程默认图片', '用于课程默认图片', 'images/system_image/course_default_icon.png', '2018-02-28 18:23:56');
INSERT INTO `pictures` VALUES (4, '女生默认头像', '用于明确性别后的女生默认系统头像', 'images/system_image/female_default_icon.png', '2018-02-28 18:24:59');
INSERT INTO `pictures` VALUES (5, '男生默认头像', '用于明确性别后的男生的默认头像', 'images/system_image/male_default_icon.png', '2018-02-28 18:25:37');
INSERT INTO `pictures` VALUES (6, '系统默认头像', '用于未明确性别的用户系统自动赋予的头像', 'images/system_image/user_default_icon.png', '2018-02-28 18:26:24');
INSERT INTO `pictures` VALUES (7, '财经系图片', '财经系主页', 'images/system_image/department_caijing_icon.jpg', '2018-03-09 08:52:29');
INSERT INTO `pictures` VALUES (8, '测绘系图片', '测绘系主页', 'images/system_image/department_cehui_icon.jpg', '2018-03-09 08:53:03');
INSERT INTO `pictures` VALUES (9, '管理系图片', '管理系主页', 'images/system_image/department_guanli_icon.jpg', '2018-03-09 08:54:02');
INSERT INTO `pictures` VALUES (10, '国际教育学院图片', '攻击教育学院主页', 'images/system_image/department_guoji_icon.jpg', '2018-03-09 08:54:46');
INSERT INTO `pictures` VALUES (11, '机械工程学院图片', '机械工程主页', 'images/system_image/department_jixie_icon.jpg', '2018-03-09 08:55:43');
INSERT INTO `pictures` VALUES (12, '旅游系图片', '旅游系主页', 'images/system_image/department_lvyou_icon.jpg', '2018-03-09 08:56:51');
INSERT INTO `pictures` VALUES (13, '水利工程学院图片', '水利系主页', 'images/system_image/department_shuili_icon.jpg', '2018-03-09 08:57:33');
INSERT INTO `pictures` VALUES (14, '土木系图片', '土木系主页', 'images/system_image/department_tumu_icon.jpg', '2018-03-09 08:58:17');
INSERT INTO `pictures` VALUES (15, '信息系图片', '信息系主页', 'images/system_image/department_xinxi_icon.jpg', '2018-03-09 08:58:54');
INSERT INTO `pictures` VALUES (16, '艺术系', '艺术系主页', 'images/system_image/department_yishu_icon.jpg', '2018-03-09 09:00:09');
INSERT INTO `pictures` VALUES (17, '自动化工程', '自动化工程主页', 'images/system_image/department_zidong_icon.jpg', '2018-03-09 09:00:47');
INSERT INTO `pictures` VALUES (18, '环化系', '环化系主页', 'images/system_image/department_huanjing_icon.jpg', '2018-03-09 09:01:51');
INSERT INTO `pictures` VALUES (19, '工程测量', '工程测量', 'images/system_image/profession_cehui1_icon.jpg', '2018-03-09 09:05:17');
INSERT INTO `pictures` VALUES (20, '地理信息系统', '地理信息', 'images/system_image/profession_cehui2_icon.jpg', '2018-03-09 09:05:45');
INSERT INTO `pictures` VALUES (21, '摄影与遥感', '摄影', 'images/system_image/profession_cehui3_icon.jpg', '2018-03-09 09:06:19');
INSERT INTO `pictures` VALUES (22, '工程机械', '工程机械', 'images/system_image/profession_jixie1_icon.jpg', '2018-03-09 09:16:11');
INSERT INTO `pictures` VALUES (23, '机械设计', '机械设计', 'images/system_image/profession_jixie2_icon.jpg', '2018-03-09 09:16:39');
INSERT INTO `pictures` VALUES (24, '机电一体化', '机电一体化', 'images/system_image/profession_jixie3_icon.jpg', '2018-03-09 09:17:06');
INSERT INTO `pictures` VALUES (25, '模具制造', '模具制造', 'images/system_image/profession_jixie4_icon.jpg', '2018-03-09 09:17:25');
INSERT INTO `pictures` VALUES (26, '汽车检修', '汽车', 'images/system_image/profession_jixie5_icon.jpg', '2018-03-09 09:18:04');
INSERT INTO `pictures` VALUES (27, '数控', '数控', 'images/system_image/profession_jixie6_icon.jpg', '2018-03-09 09:18:19');
INSERT INTO `pictures` VALUES (28, '水电建筑', '水电', 'images/system_image/profession_shuili1_icon.jpg', '2018-03-09 09:20:18');
INSERT INTO `pictures` VALUES (29, '水电工程造价', '工程造价', 'images/system_image/profession_shuili2_icon.jpg', '2018-03-09 09:20:48');
INSERT INTO `pictures` VALUES (30, '水利施工', '水工', 'images/system_image/profession_shuili3_icon.jpg', '2018-03-09 09:21:32');
INSERT INTO `pictures` VALUES (31, '水利试验', '试验', 'images/system_image/profession_shuili4_icon.jpg', '2018-03-09 09:21:48');
INSERT INTO `pictures` VALUES (32, '水电站动力', '水动', 'images/system_image/profession_shuili5_icon.jpg', '2018-03-09 09:22:17');
INSERT INTO `pictures` VALUES (33, '水工监理', '监理', 'images/system_image/profession_shuili6_icon.jpg', '2018-03-09 09:22:48');
INSERT INTO `pictures` VALUES (34, '水利安全', '安全', 'images/system_image/profession_shuili7_icon.jpg', '2018-03-09 09:23:01');
INSERT INTO `pictures` VALUES (35, '水文', '水文', 'images/system_image/profession_shuili8_icon.jpg', '2018-03-09 09:23:17');
INSERT INTO `pictures` VALUES (36, '水务', '水务', 'images/system_image/profession_shuili9_icon.jpg', '2018-03-09 09:23:30');
INSERT INTO `pictures` VALUES (37, '水文工', '水文工', 'images/system_image/profession_shuili10_icon.jpg', '2018-03-09 09:23:51');
INSERT INTO `pictures` VALUES (38, '土木工程', '土木', 'images/system_image/profession_tumu1_icon.jpg', '2018-03-09 09:26:38');
INSERT INTO `pictures` VALUES (39, '土木工造价', '造价', 'images/system_image/profession_tumu2_icon.jpg', '2018-03-09 09:27:02');
INSERT INTO `pictures` VALUES (40, '土木给排水', '给排水', 'images/system_image/profession_tumu3_icon.jpg', '2018-03-09 09:27:25');
INSERT INTO `pictures` VALUES (41, '道桥', '道桥', 'images/system_image/profession_tumu4_icon.jpg', '2018-03-09 09:27:39');
INSERT INTO `pictures` VALUES (42, '基础工程', '基础', 'images/system_image/profession_tumu5_icon.jpg', '2018-03-09 09:28:04');
INSERT INTO `pictures` VALUES (43, '公路养护', '养护', 'images/system_image/profession_tumu6_icon.jpg', '2018-03-09 09:28:23');
INSERT INTO `pictures` VALUES (44, '微机', '微机', 'images/system_image/profession_xinxi1_icon.jpg', '2018-03-09 09:30:32');
INSERT INTO `pictures` VALUES (45, '软件', '软件', 'images/system_image/profession_xinxi2_icon.jpg', '2018-03-09 09:30:46');
INSERT INTO `pictures` VALUES (46, '多媒体', '多媒体', 'images/system_image/profession_xinxi3_icon.jpg', '2018-03-09 09:30:58');
INSERT INTO `pictures` VALUES (47, '网络', '网络', 'images/system_image/profession_xinxi4_icon.jpg', '2018-03-09 09:31:17');
INSERT INTO `pictures` VALUES (48, 'JAVA基础', '基础', 'images/system_image/course_xinxi1.1_icon.jpg', '2018-03-09 09:33:36');
INSERT INTO `pictures` VALUES (49, 'HTML', 'html', 'images/system_image/course_xinxi1.2_icon.jpg', '2018-03-09 09:33:50');
INSERT INTO `pictures` VALUES (50, 'PS', 'ps', 'images/system_image/course_xinxi1.3_icon.jpg', '2018-03-09 09:34:15');
INSERT INTO `pictures` VALUES (51, '计算机基础', '基础', 'images/system_image/course_xinxi1.4_icon.jpg', '2018-03-09 09:34:32');
INSERT INTO `pictures` VALUES (52, 'web', 'web', 'images/system_image/course_xinxi1.5_icon.jpg', '2018-03-09 09:34:48');
INSERT INTO `pictures` VALUES (53, 'JS', 'js', 'images/system_image/course_xinxi1.6_icon.jpg', '2018-03-09 09:35:02');
INSERT INTO `pictures` VALUES (54, '软件', '软件', 'images/system_image/course_xinxi1.7_icon.jpg', '2018-03-09 09:35:28');
INSERT INTO `pictures` VALUES (55, 'Android', 'Android', 'images/system_image/course_xinxi1.8_icon.jpg', '2018-03-09 09:35:43');
INSERT INTO `pictures` VALUES (56, '数据库', '数据库', 'images/system_image/course_xinxi1.9_icon.jpg', '2018-03-09 09:35:56');
INSERT INTO `pictures` VALUES (57, 'PHP', 'php', 'images/system_image/course_xinxi1.10_icon.jpg', '2018-03-09 09:36:09');
INSERT INTO `pictures` VALUES (58, 'java入门', 'java', 'images/system_image/java_res_icon.jpg', '2018-04-16 12:09:46');
INSERT INTO `pictures` VALUES (59, 'java8', 'java8', 'images/system_image/java_eight_res_icon.jpg', '2018-04-16 12:13:18');
INSERT INTO `pictures` VALUES (60, 'Android', 'Android', 'images/system_image/Android_res_icon.jpg', '2018-04-16 12:14:39');

-- ----------------------------
-- Table structure for professions
-- ----------------------------
DROP TABLE IF EXISTS `professions`;
CREATE TABLE `professions`  (
  `profession_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '专业编号',
  `profession_name` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '专业名称',
  `profession_picture_id` bigint(30) NOT NULL DEFAULT 0 COMMENT '专业图片编号',
  `profession_caption` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业描述',
  `profession_addtime` datetime NOT NULL DEFAULT '' COMMENT '专业添加时间',
  `profession_department_id` int(20) NOT NULL DEFAULT '' COMMENT '专业所属系别',
  PRIMARY KEY USING BTREE (`profession_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of professions
-- ----------------------------
INSERT INTO `professions` VALUES (1, '计算机微机应用技术', 44, '微机应用描述', '2018-01-25 17:49:46', 7);
INSERT INTO `professions` VALUES (2, '计算机软件技术', 45, '软件工程描述', '2018-01-25 17:50:27', 7);
INSERT INTO `professions` VALUES (3, '计算机多媒体技术', 46, '多媒体技术描述', '2018-01-25 17:50:50', 7);
INSERT INTO `professions` VALUES (4, '计算机网络技术', 47, '计算机网络描述', '2018-01-25 17:51:15', 7);
INSERT INTO `professions` VALUES (5, '水利水电建筑工程', 28, '水利水电建筑工程描述', '2018-01-26 15:34:17', 1);
INSERT INTO `professions` VALUES (6, '水利水电工程造价管理', 29, '水工造价描述', '2018-01-26 15:43:49', 1);
INSERT INTO `professions` VALUES (7, '水利工程施工技术', 30, '水工技术描述', '2018-01-26 15:44:45', 1);
INSERT INTO `professions` VALUES (8, '水利工程试验与检测技术', 31, '水利工程试验与检测描述', '2018-01-26 15:45:25', 1);
INSERT INTO `professions` VALUES (9, '水电站动力设备与管理', 32, '水电站动力设备与管理描述', '2018-01-26 15:46:03', 1);
INSERT INTO `professions` VALUES (10, '水利工程监理', 33, '水利工程监理描述', '2018-01-26 15:46:34', 1);
INSERT INTO `professions` VALUES (11, '水利安全技术管理', 34, '水利安全技术管理描述', '2018-01-26 15:46:57', 1);
INSERT INTO `professions` VALUES (12, '水文与水资源', 35, '水文与水资源描述', '2018-01-26 15:47:24', 1);
INSERT INTO `professions` VALUES (13, '水务管理', 36, '水务管理描述', '2018-01-26 15:48:35', 1);
INSERT INTO `professions` VALUES (14, '水文与工程地质', 37, '水文与工程地质描述', '2018-01-26 15:49:09', 1);
INSERT INTO `professions` VALUES (15, '土木建筑工程技术', 38, '土木建筑工程技术描述', '2018-01-26 15:50:18', 2);
INSERT INTO `professions` VALUES (16, '土木工程造价', 39, '土木工程造价描述', '2018-01-26 15:51:06', 2);
INSERT INTO `professions` VALUES (17, '土木给排水工程技术', 40, '土木给排水工程技术描述', '2018-01-26 15:51:33', 2);
INSERT INTO `professions` VALUES (18, '道路桥梁工程技术', 41, '道路桥梁工程技术描述', '2018-01-26 15:51:49', 2);
INSERT INTO `professions` VALUES (19, '基础工程技术', 42, '基础工程技术描述', '2018-01-26 15:52:24', 2);
INSERT INTO `professions` VALUES (20, '高等级公路维护与管理', 43, '高等级公路维护与管理描述', '2018-01-26 15:52:59', 2);
INSERT INTO `professions` VALUES (21, '工程测量技术', 19, '工程测量技术描述', '2018-01-26 15:53:48', 3);
INSERT INTO `professions` VALUES (22, '地理信息系统与地图制图技术', 20, '地理信息系统与地图制图技术描述', '2018-01-26 15:54:38', 3);
INSERT INTO `professions` VALUES (23, '摄影测量与遥感技术', 21, '摄影测量与遥感技术描述', '2018-01-26 15:55:07', 3);
INSERT INTO `professions` VALUES (24, '工程机械运用与维护', 22, '工程机械运用与维护描述', '2018-01-26 15:55:53', 4);
INSERT INTO `professions` VALUES (25, '机械设计与制造', 23, '机械设计与制造描述', '2018-01-26 15:56:21', 4);
INSERT INTO `professions` VALUES (26, '机电一体化技术', 24, '机电一体化技术描述', '2018-01-26 15:56:40', 4);
INSERT INTO `professions` VALUES (27, '模具设计与制造', 25, '磨具设计与制造描述', '2018-01-26 15:57:06', 4);
INSERT INTO `professions` VALUES (28, '汽车检测与维修技术', 26, '汽车检测与维修技术描述', '2018-01-26 15:57:35', 4);
INSERT INTO `professions` VALUES (29, '数控技术', 27, '数控技术描述', '2018-01-26 15:57:56', 4);

-- ----------------------------
-- Table structure for questions
-- ----------------------------
DROP TABLE IF EXISTS `questions`;
CREATE TABLE `questions`  (
  `question_id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '题目编号',
  `question_stem` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '题目题干',
  `question_option_id` bigint(30) NOT NULL DEFAULT '' COMMENT '题目选项id',
  `question_level` int(10) NOT NULL DEFAULT '' COMMENT '题目难度等级：0基础，1中级，2高级',
  `question_answer` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '题目答案',
  `question_analysis` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '题目解析',
  `question_type` int(4) NOT NULL DEFAULT '' COMMENT '题目类型:0单选，1多选，2判断',
  `question_addtime` datetime NOT NULL DEFAULT '' COMMENT '题目插入时间',
  `question_course_id` int(20) NOT NULL DEFAULT '' COMMENT '题目所属课程',
  `question_chapter` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '题目所属章节',
  PRIMARY KEY USING BTREE (`question_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of questions
-- ----------------------------
INSERT INTO `questions` VALUES (1, '关于sleep()和wait()，以下描述错误的一项是？', 1, 0, 'D', 'sleep是线程类（Thread）的方法，导致此线程暂停执行指定时间，给执行机会给其他线程，但是监控状态依然保持，到时后会自动恢复。调用sleep不会释放对象锁。wait是Object类的方法，对此对象调用wait方法导致本线程放弃对象锁，进入等待此对象的等待锁定池，只有针对此对象发出notify方法（或notifyAll）后本线程才进入对象锁定池准备获得对象锁进入运行状态。', 0, '2018-01-25 17:39:18', 1, '0');
INSERT INTO `questions` VALUES (2, '下面哪个可以改变容器的布局？', 2, 1, 'A', 'Java设置布局管理器setLayout()', 0, '2018-01-26 16:01:49', 1, '0');
INSERT INTO `questions` VALUES (3, '提供Java存取数据库能力的包是？', 3, 0, 'A', 'java.sql是JDBC的编程接口；java.awt和java.swing是做图像界面的类库；java.lang: Java 编程语言进行程序设计的基础类', 0, '2018-01-26 16:03:59', 1, '0');
INSERT INTO `questions` VALUES (4, '已知表达式 int m [ ] = {，1，2，3，4，5，6}；下面哪个表达式的值与数组下标量总数相等？', 4, 0, 'B', '数组下标是从零开始的，但是数据下标的总量和数据长度相同。', 0, '2018-01-26 16:07:53', 1, '0');
INSERT INTO `questions` VALUES (5, '方法resume()负责恢复哪些线程的执行？', 5, 1, 'D', 'Suspend可以挂起一个线程，就是把这个线程暂停了，它占着资源，但不运行，用Resume是恢复挂起的线程，让这个线程继续执行下去。', 0, '2018-01-26 16:09:14', 1, '0');
INSERT INTO `questions` VALUES (6, '下面的代码实现了设计模式中的什么模式？', 6, 1, 'C', 'Singleton单例模式：该设计模式确保某一个类只有一个实例，而且自行实例化并向整个系统提供这个实例', 0, '2018-01-26 16:12:08', 1, '0');
INSERT INTO `questions` VALUES (7, 'MAX_LENGTH 是int 型public 成员变量，变量值保持为常量1，用简短语句定义这个变量？', 7, 0, 'D', '通过题的描述就是定义常量，在java中常量命名规范是所有字母都大写用下划线分割每个单词', 0, '2018-01-26 16:15:16', 1, '0');
INSERT INTO `questions` VALUES (8, '关于线程设计，下列描述正确的是？', 8, 0, 'C', '略', 0, '2018-01-26 16:16:53', 1, '0');
INSERT INTO `questions` VALUES (9, '欲构造ArrayList类得一个实例，此类继承了List接口，下列哪个方法是正确的是？', 9, 0, 'B', '略', 0, '2018-01-26 16:18:16', 1, '0');
INSERT INTO `questions` VALUES (10, 'TCP通信建立在连接的基础上，TCP连接的建立要使用几次握手的过程？', 10, 2, 'B', '略', 0, '2018-01-26 16:19:34', 1, '0');
INSERT INTO `questions` VALUES (11, '路由器工作在ISO/OSI参考模型的？', 11, 0, 'B', '网络层属于OSI中的较高层次了，从它的名字可以看出，它解决的是网络与网络之间，即网际的通信问题，而不是同一网段内部的事。网络层的主要功能即是提供路由，即选择到达目标主机的最佳路径，并沿该路径传送数据包。除此之外，网络层还要能够消除网络拥挤，具有流量控制和拥挤控制的能力。网络边界中的路由器就工作在这个层次上，现在较高档的交换机也可直接工作在这个层次上，因此它\r\n们也提供了路由功能，俗称“第三层交换机“。', 0, '2018-01-26 16:22:30', 1, '0');
INSERT INTO `questions` VALUES (12, 'OSI体系结构定义了一个几层模型？', 12, 2, 'B', 'OSI-RM ISO／OSI Reference Model该模型是国际标准化组织（ISO）为网络通信制定的协议，根据网络通信的功能要求，它把通信过程分为七层，分别为物理层、数据链路层、网络层、传输层、会话层、表示层和应用层，每层都规定了完成的功能及相应的协议。', 0, '2018-01-26 16:24:14', 1, '0');
INSERT INTO `questions` VALUES (13, '在一个办公室内，将6台计算机用交换机连接成网络，该网络的屋里拓扑结构为？', 13, 2, 'C', '选项A：星型拓扑结构 是一种以中央节点为中心，把若干外围节点连接起来的辐射式互联结构。这种结构适用于局域网，特别是近年来连接的局域网大都采用这种连接方式。这种连接方式以双绞线或同轴电缆作连接线路。\r\n优点：结构简单、容易实现、便于管理，通常以集线器（Hub）作为中央节点，便于维护和管理。缺点：中心结点是全网络的可靠瓶颈，中心结点出现故障会导致网络的瘫痪。\r\n选项B：总线拓扑结构 是将网络中的所有设备通过相应的硬件接口直接连接到公共总线上，结点之间按广播方式通信，一个结点发出的信息，总线上的其它结点均可“收听”到。\r\n优点：结构简单、布线容易、可靠性较高，易于扩充，节点的故障不会殃及系统，是局域网常采用的\r\n拓扑结构。\r\n缺点：所有的数据都需经过总线传送，总线成为整个网络的瓶颈；出现故障诊断较为困难。另外，由于信道共享，连接的节点不宜过多，总线自身的故障可以导致系统的崩溃。最著名的总线拓扑结构是以太网（Ethernet）。\r\n选项C ：树型拓扑结构 是一种层次结构，结点按层次连结，信息交换主要在上下结点之间进行，相邻结点或同层结点之间一般不进行数据交换。\r\n优点：连结简单，维护方便，适用于汇集信息的应用要求。\r\n缺点：资源共享能力较低，可靠性不高，任何一个工作站或链路的故障都会影响整个网络的运行。\r\n选项D： 环形拓扑结构 各结点通过通信线路组成闭合回路，环中数据只能单向传输，信息在每台设备上的延时时间是固定的。特别适合实时控制的局域网系统。\r\n优点：结构简单，适合使用光纤，传输距离远，传输延迟确定。\r\n缺点：环网中的每个结点均成为网络可靠性的瓶颈，任意结点出现故障都会造成网络瘫痪，另外故障诊断也较困难。最著名的环形拓扑结构网络是令牌环网（Token Ring）', 0, '2018-01-26 16:25:51', 1, '0');
INSERT INTO `questions` VALUES (14, '下列既可用作输入设备又可用作输出设备的是？', 14, 0, 'B', '略', 0, '2018-01-26 16:27:27', 1, '0');

-- ----------------------------
-- Table structure for resources
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources`  (
  `resource_id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '资源编号',
  `resource_name` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '资源名称',
  `resource_detail` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '资源详情',
  `resource_type` int(10) NOT NULL DEFAULT '' COMMENT '资源分类',
  `resource_caption` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源描述',
  `resource_degree` int(10) NOT NULL DEFAULT '' COMMENT '资源等级:1高级 2中级 3基础',
  `resource_addtime` datetime NOT NULL DEFAULT '' COMMENT '资源添加时间',
  `resource_course_id` int(20) NULL DEFAULT NULL COMMENT '资源所属课程',
  `resource_picture_id` bigint(30) NOT NULL DEFAULT '',
  PRIMARY KEY USING BTREE (`resource_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of resources
-- ----------------------------
INSERT INTO `resources` VALUES (1, 'Java程序开发从入门到精通', 'http://product.dangdang.com/23935155.html', 2, '学出版社图书奖优秀畅销书，国家信息技术紧缺人才培养工程指定教材，新形态应用型IT教育校企合作教材，上百家高校用作教材，几万学子用它自学成才。', 3, '2018-02-27 21:47:09', 1, 58);
INSERT INTO `resources` VALUES (2, 'Java编程思想', 'http://product.dangdang.com/9317290.html', 2, 'Java学习必读经典,殿堂级著作！赢得了全球程序员的广泛赞誉。', 2, '2018-02-27 21:48:38', 1, 3);
INSERT INTO `resources` VALUES (3, '疯狂Java讲义', 'http://product.dangdang.com/23532609.html', 2, '10年经典原创读物基于Java 9抢先升级面世，李刚作品成为50万读者之选，本书赠送包含1500分钟课程讲解的视频、源代码、电子书、课件、面试题，提供微信+QQ答疑群，配套学习交流网站 ', 3, '2018-02-27 21:49:15', 1, 60);
INSERT INTO `resources` VALUES (4, 'Java EE互联网轻量级框架整合开发', 'http://product.dangdang.com/25111311.html', 2, 'SSM框架（Spring MVC+Spring+MyBatis）和Redis实现', 1, '2018-02-27 21:50:16', 1, 3);
INSERT INTO `resources` VALUES (5, 'HTML5权威指南', 'http://product.dangdang.com/23386583.html', 2, '全面详实的web网页设计参考书 贴心汇聚HTML5和CSS3 JavaScript', 3, '2018-02-27 21:51:15', 2, 3);
INSERT INTO `resources` VALUES (6, 'HTML+CSS+JavaScript网页设计从入门到精通', 'http://product.dangdang.com/25157436.html', 2, '网页编程 入门热销 书籍 web 前端开发名作 html css javascript 368节同步视频讲解 数百个实战案例 4个大型综合实战案例 380套网页模板 30部工具集 609项配色', 3, '2018-02-27 21:51:59', 2, 3);
INSERT INTO `resources` VALUES (7, '细说HTML5高级API', 'http://product.dangdang.com/25187748.html', 2, '本书比较适合有一定的JavaScript HTML5开发基础的读者，希望读者能够从每个例子中举一反三，获取更多知识。 ', 1, '2018-02-27 21:53:10', 2, 3);
INSERT INTO `resources` VALUES (8, 'JavaScript高级程序设计', 'http://product.dangdang.com/22628333.html', 2, '前端开发经典图书 JavaScript技术名著 web前端开发国内js一书 掌握JavaScript编程艺术 ', 2, '2018-02-27 21:53:42', 2, 3);
INSERT INTO `resources` VALUES (9, '中文版Photoshop CS6完全自学教程', 'http://product.dangdang.com/22786104.html', 2, 'PS CS6从入门到精通必选图书教程 让你毫无ps痕迹的书籍 平面设计 UI设计 淘宝美工网店装修 摄影后期修图大神器书籍 赠光盘含同步视频和海量素材资源库', 3, '2018-02-27 21:54:53', 3, 3);
INSERT INTO `resources` VALUES (10, '中文版Photoshop CC从入门到精通', 'http://product.dangdang.com/25181888.html', 2, '重印30次畅销12万册 339集PS视频教程 Adobe专家作品 PS抠图 修图 调色 合成 特效 PS平面设计 淘宝美工 照片处理 网页设计 插画 UI 赠配色/构图/商业设计宝典', 3, '2018-02-27 21:55:28', 3, 3);
INSERT INTO `resources` VALUES (11, 'Adobe Photoshop大师班：高级合成的秘密', 'http://product.dangdang.com/23702530.html', 2, '站在巨人的肩膀上 掌握大师的高级合成技巧 随心所欲实现任何幻想中的画面 ', 2, '2018-02-27 21:56:09', 3, 3);
INSERT INTO `resources` VALUES (12, 'Photoshop商业摄影后期高级修图技法', 'http://product.dangdang.com/23995988.html', 2, '本书就是讲如何使一张普普通通的照片，摇身一变成为具有无比质感、光感及产品诱惑力的照片', 1, '2018-02-27 21:56:53', 3, 3);
INSERT INTO `resources` VALUES (13, 'Java零基础入门', 'https://edu.aliyun.com/course/34?spm=5176.8764702.aliyun-edu-developer-courselist.30.MNMDEs', 1, '本课程主要讲解JavaSE的发展历史，JDK开发环境的搭建，CLASSPATH属性作用，Java程序基本结构、基本数据类型的划分及使用、程序结构、方法的定义与使用，本课程是作为Java系列课程的初期课程，掌握本课程之后可以继续学习Java高级开发部分。', 3, '2018-02-27 22:01:27', 1, 59);
INSERT INTO `resources` VALUES (14, 'Java面向对象开发', 'https://edu.aliyun.com/course/35?spm=5176.8764702.aliyun-edu-developer-courselist.31.MNMDEs', 1, '面向对象是Java语言之中最为重要的特征，本课程主要讲解面向对象的核心知识，并且利用大量的代码、数据结构课程深入分析Java面向对象特征。', 2, '2018-02-27 22:02:06', 1, 3);
INSERT INTO `resources` VALUES (15, 'Java高级开发', 'https://edu.aliyun.com/course/36?spm=5176.8764702.aliyun-edu-developer-courselist.32.MNMDEs', 1, '当你已经熟练的掌握了面向对象中的各种概念后，是否会对这些知识是如何使用的产生浓厚的兴趣？本课程主要针对于已经掌握了JAVA核心开发技术的读者准备，讲解了JAVA多线程、常用类库、IO编程、网络编程、类集框架、JDBC等与Java实际应用有关的开发技术。', 1, '2018-02-27 22:02:44', 1, 3);
INSERT INTO `resources` VALUES (16, 'HTML5新特性教程', 'https://edu.aliyun.com/course/72?spm=5176.10731491.0.0.eGwqbA', 1, 'HTML5是HTML最新的修订版本，2014年10月由万维网联盟（W3C）完成标准制定，其设计目的是为了在移动设备上支持多媒体。HTML5中包含了一些有趣的新特性', 1, '2018-02-27 22:03:56', 2, 3);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `user_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_picture_id` bigint(20) NULL DEFAULT NULL COMMENT '用户头像编号',
  `user_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `user_password` varchar(70) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户密码',
  `user_nickname` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户昵称',
  `user_register_time` datetime NOT NULL DEFAULT '' COMMENT '用户注册时间',
  `user_realname` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户真实姓名',
  `user_age` int(3) NULL DEFAULT NULL COMMENT '用户年龄',
  `user_gendar` int(2) NULL DEFAULT NULL COMMENT '用户性别',
  `user_phone` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户手机',
  `user_email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `user_fraction_id` int(20) NULL DEFAULT NULL COMMENT '用户分数信息编号',
  `user_university` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户学校',
  `user_integral` int(8) NOT NULL DEFAULT 0 COMMENT '用户积分',
  `user_city` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户归属地',
  `user_qq` bigint(35) NULL DEFAULT NULL COMMENT '用户QQ',
  `user_lastlogin_time` datetime NOT NULL DEFAULT '' COMMENT '用户最后登录时间',
  `user_grade` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户年级',
  `user_status` int(3) NOT NULL DEFAULT 0 COMMENT '用户等级',
  `user_admin_flag` int(3) NOT NULL DEFAULT 1 COMMENT '用户管理员标识',
  PRIMARY KEY USING BTREE (`user_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 6, 'admin', '21232F297A57A5A743894A0E4A801FC3', '管理员01', '2018-01-20 14:34:11', 'Jeff', 24, 0, '18754865982', '845878585@qq.com', 0, '河科大', 0, '郑州', 856985874225, '2018-01-20 14:41:16', '毕业', 30, 0);
INSERT INTO `users` VALUES (2, 5, 'freedom123456', 'E10ADC3949BA59ABBE56E057F20F883E', 'Freedom0013', '2018-01-25 17:28:20', 'SZR', 26, 0, '18736054875', '587458965@qq.com', 0, '河南大学', 0, '开封', 548698458, '2018-01-25 17:30:23', '大二', 1, 1);
INSERT INTO `users` VALUES (3, 6, 'user', 'EE11CBB19052E40B07AAC0CA060C23EE', '用户1', '2018-01-25 17:31:34', 'null', 0, 0, 'null', 'null', 0, 'null', 0, 'null', 0, '2018-01-25 17:32:03', NULL, 1, 1);
INSERT INTO `users` VALUES (4, 5, 'hahahaha123456789', '25F9E794323B453885F5181F1B624D0B', '曾小贤', '2018-02-05 00:35:27', '陈赫', 20, 0, '15847854854', '888444555@gmail.com', 0, '陕师大', 0, '西安', 587458548, '2018-02-05 00:35:27', '大二', 1, 1);
INSERT INTO `users` VALUES (5, 4, 'xixixixi987654321', '6EBE76C9FB411BE97B3B0D48B791A7C9', '胡一菲', '2018-02-05 00:55:14', '娄艺潇', 28, 0, '15965884578', '11223344@gmail.com', 0, '上海戏剧学院', 0, '上海', 587458548, '2018-02-05 00:55:14', '大四', 1, 1);
INSERT INTO `users` VALUES (6, 5, 'birdddd', '96E79218965EB72C92A549DD5A330112', '陆展博', '2018-02-26 18:35:47', 'null', 0, 0, 'null', 'null', 0, 'null', 0, 'null', 0, '2018-02-26 18:35:47', 'null', 1, 1);
INSERT INTO `users` VALUES (7, 0, 'a3657387', 'E10ADC3949BA59ABBE56E057F20F883E', 'niai', '2018-04-19 10:16:29', 'null', 0, 0, 'null', 'null', 0, 'null', 0, 'null', 0, '2018-04-19 10:16:29', 'null', 1, 1);
INSERT INTO `users` VALUES (8, 0, 'b12345', '93838CD7D8FA14B8E67BE84E330E5101', 'rgz', '2018-04-19 10:58:25', 'null', 0, 0, 'null', 'null', 0, 'null', 0, 'null', 0, '2018-04-19 10:58:25', 'null', 1, 1);
INSERT INTO `users` VALUES (9, 0, 'a123456', '508DF4CB2F4D8F80519256258CFB975F', 'kkk', '2018-04-21 15:15:34', 'null', 0, 0, 'null', 'null', 0, 'null', 0, 'null', 0, '2018-04-21 15:15:34', 'null', 1, 1);
INSERT INTO `users` VALUES (10, 0, 'ldy1234', 'A9D1C28F94F2F61BDB42EC33A06656AF', '666', '2018-05-03 16:13:04', 'null', 0, 0, 'null', 'null', 0, 'null', 0, 'null', 0, '2018-05-03 16:13:04', 'null', 1, 1);

-- ----------------------------
-- Table structure for userstests
-- ----------------------------
DROP TABLE IF EXISTS `userstests`;
CREATE TABLE `userstests`  (
  `userstest_id` bigint(30) NOT NULL DEFAULT '' COMMENT '试卷编号',
  `userstest_name` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '试卷名称',
  `userstest_addtime` datetime NOT NULL DEFAULT '' COMMENT '试卷添加时间',
  `userstest_content_id` bigint(30) NOT NULL DEFAULT '' COMMENT '试卷内容id',
  `userstest_caption` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '试卷描述',
  `userstest_dep_id` int(20) NOT NULL DEFAULT '' COMMENT '试卷所属系别',
  `userstest_pro_id` int(20) NOT NULL DEFAULT '' COMMENT '试卷所属专业',
  `userstest_course_id` int(20) NOT NULL DEFAULT '' COMMENT '试卷所属课程',
  `userstest_difficulty` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '3' COMMENT '试卷难度默认3 1~5级',
  `userstest_user_id` int(20) NOT NULL DEFAULT '' COMMENT '出卷人id',
  PRIMARY KEY USING BTREE (`userstest_id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for userstests_contents
-- ----------------------------
DROP TABLE IF EXISTS `userstests_contents`;
CREATE TABLE `userstests_contents`  (
  `contents_id` bigint(30) NOT NULL AUTO_INCREMENT COMMENT '试卷内容编号',
  `contents_single_num` int(10) NULL DEFAULT NULL COMMENT '单选题数量',
  `contents_single_scoue` int(100) NULL DEFAULT NULL COMMENT '单选题分数',
  `contents_single_difficulty` int(10) NULL DEFAULT NULL COMMENT '单选题难度',
  `contents_mul_num` int(10) NULL DEFAULT NULL COMMENT '多选题数量',
  `contents_mul_score` int(100) NULL DEFAULT NULL COMMENT '多选题分数',
  `contents_mul_difficulty` int(10) NULL DEFAULT NULL COMMENT '多选题难度',
  `contents_judge_num` int(10) NULL DEFAULT NULL COMMENT '判断题数量',
  `contents_judge_score` int(100) NULL DEFAULT NULL COMMENT '判断题分数',
  `contents_judge_difficulty` int(10) NULL DEFAULT NULL COMMENT '判断题难度',
  `contents_allscore` int(100) NOT NULL DEFAULT '' COMMENT '总分',
  PRIMARY KEY USING BTREE (`contents_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
