/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : zrtjoa

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 16/03/2019 16:26:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activities
-- ----------------------------
DROP TABLE IF EXISTS `activities`;
CREATE TABLE `activities`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '内容',
  `crttime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '填写时间',
  `userid` int(11) NULL DEFAULT NULL COMMENT '填写人id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '填写人姓名',
  `times` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for activityrecord
-- ----------------------------
DROP TABLE IF EXISTS `activityrecord`;
CREATE TABLE `activityrecord`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tid` int(11) NULL DEFAULT NULL COMMENT '教师id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教师姓名',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '内容',
  `recordtime` datetime(0) NULL DEFAULT NULL COMMENT '记录时间',
  `activitytime` datetime(0) NULL DEFAULT NULL COMMENT '活动时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for award
-- ----------------------------
DROP TABLE IF EXISTS `award`;
CREATE TABLE `award`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '内容',
  `crttime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '填写时间',
  `userid` int(11) NULL DEFAULT NULL COMMENT '填写人id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '填写人姓名',
  `times` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for awardrecord
-- ----------------------------
DROP TABLE IF EXISTS `awardrecord`;
CREATE TABLE `awardrecord`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tid` int(11) NULL DEFAULT NULL COMMENT '教师id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教师姓名',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '内容',
  `recordtime` datetime(0) NULL DEFAULT NULL COMMENT '记录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `number` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '类别编号',
  `category` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '类别名称',
  `remarks` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `createid` int(11) NOT NULL COMMENT '创建人id',
  `createname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '1', '器乐', NULL, 1, '测试', '2018-12-04 10:39:07');
INSERT INTO `category` VALUES (2, '2', '声乐', NULL, 1, '测试', '2018-12-04 10:40:21');
INSERT INTO `category` VALUES (3, '3', '舞蹈', NULL, 1, '测试', '2018-12-04 10:40:39');
INSERT INTO `category` VALUES (4, '4', '养生保健', NULL, 1, '测试', '2018-12-04 10:41:26');
INSERT INTO `category` VALUES (5, '5', '书法', NULL, 1, '测试', '2018-12-04 10:41:33');
INSERT INTO `category` VALUES (6, '6', '绘画', NULL, 1, '测试', '2018-12-04 10:42:09');
INSERT INTO `category` VALUES (7, '7', '语言', NULL, 1, '测试', '2018-12-04 10:42:22');
INSERT INTO `category` VALUES (8, '8', '生活艺术', NULL, 1, '测试', '2018-12-04 10:42:42');
INSERT INTO `category` VALUES (9, '9', '传统技艺', NULL, 1, '测试', '2018-12-04 10:42:59');

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `number` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级序号',
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  `plansize` int(11) NULL DEFAULT NULL COMMENT '计划人数',
  `actualsize` int(11) NULL DEFAULT 0 COMMENT '实际人数',
  `majorid` int(11) NULL DEFAULT NULL COMMENT '专业id',
  `majorname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '专业名称',
  `headmaster` int(11) NULL DEFAULT NULL COMMENT '班主任id',
  `headmastername` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班长任姓名',
  `monitor` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班长姓名',
  `studyer` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学习委员',
  `safer` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '安全委员',
  `tid` int(11) NULL DEFAULT NULL COMMENT '授课教师id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '授课教师姓名',
  `cateid` int(11) NULL DEFAULT NULL COMMENT '类别id',
  `catename` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '类别名称',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of classes
-- ----------------------------
INSERT INTO `classes` VALUES (44, '1', '班级1', 50, 0, 33, '水墨水乡画', 21, '老师1', NULL, NULL, NULL, NULL, '老师2', 6, '绘画', '2019-03-12 13:47:51');
INSERT INTO `classes` VALUES (46, '1', '班级2', 50, 0, 1, '葫芦丝', 21, '老师1', NULL, NULL, NULL, NULL, '老师3', 1, '器乐', '2019-03-12 16:01:57');
INSERT INTO `classes` VALUES (48, '1', '大1', 40, 0, 2, '电子琴', 21, '老师1', NULL, NULL, NULL, NULL, '请选择', 1, '器乐', '2019-03-13 13:44:00');

-- ----------------------------
-- Table structure for classrecord
-- ----------------------------
DROP TABLE IF EXISTS `classrecord`;
CREATE TABLE `classrecord`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `classid` int(11) NULL DEFAULT NULL COMMENT '班级id',
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  `roomid` int(11) NULL DEFAULT NULL COMMENT '教室id',
  `roomname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教室名称',
  `starttime` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `endtime` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `tid` int(11) NULL DEFAULT NULL COMMENT '使用人id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '使用人姓名',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `coursename` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '课程名称',
  `courseid` int(11) NULL DEFAULT NULL COMMENT '课程id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for classroom
-- ----------------------------
DROP TABLE IF EXISTS `classroom`;
CREATE TABLE `classroom`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `classroom` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教室名称',
  `cateid` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教室类别id',
  `catename` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '类别名称',
  `purpose` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教室用途',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教室地址',
  `isuser` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '是否可用（0：启用、1：停用）',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of classroom
-- ----------------------------
INSERT INTO `classroom` VALUES (3, '教室1', '3', '类别1', '用途一', '地址1', '0', '2019-03-13 16:56:31');
INSERT INTO `classroom` VALUES (4, '教室二', '4', '类别二', '用途二', '地址二', '0', '2019-03-13 16:57:45');
INSERT INTO `classroom` VALUES (5, '教室3', '5', '类别三', '用途三', '地址三', '0', '2019-03-13 16:58:14');
INSERT INTO `classroom` VALUES (6, '天津权健足球教室', '6', '足球类别', '足球教学', '西青老年大学足球场', '0', '2019-03-15 11:35:58');
INSERT INTO `classroom` VALUES (7, '测绘教室1', '1', '美术类', '画画', '地址1', '0', '2019-03-15 16:07:13');
INSERT INTO `classroom` VALUES (8, '测试教室2', '2', '舞蹈类', '跳舞', '地址2', '0', '2019-03-15 16:07:36');
INSERT INTO `classroom` VALUES (9, '测试教室3', '6', '足球类别', '踢球', '地址4', '0', '2019-03-15 16:08:11');
INSERT INTO `classroom` VALUES (10, '测试教室5', '2', '舞蹈类', '跳舞唱歌', '地址6', '0', '2019-03-15 16:08:46');
INSERT INTO `classroom` VALUES (11, '测试教室6', '1', '美术类', '画画1', '地址7', '0', '2019-03-15 16:09:19');
INSERT INTO `classroom` VALUES (12, '测试教室8', '2', '舞蹈类', '跳舞2', '地址8', '0', '2019-03-15 16:09:45');
INSERT INTO `classroom` VALUES (13, '测试教室9', '1', '美术类', '画画4', '地址9', '0', '2019-03-15 16:10:13');

-- ----------------------------
-- Table structure for classsuggest
-- ----------------------------
DROP TABLE IF EXISTS `classsuggest`;
CREATE TABLE `classsuggest`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sort` int(11) NULL DEFAULT NULL COMMENT '序号',
  `tid` int(11) NULL DEFAULT NULL COMMENT '教师id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教师姓名',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '内容',
  `suggesttime` datetime(0) NULL DEFAULT NULL COMMENT '建议时间',
  `reply` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '回复情况',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `classid` int(11) NULL DEFAULT NULL COMMENT '班级id',
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for classtype
-- ----------------------------
DROP TABLE IF EXISTS `classtype`;
CREATE TABLE `classtype`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `catename` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '类别名称',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of classtype
-- ----------------------------
INSERT INTO `classtype` VALUES (1, '美术类', '2019-01-04 14:28:48');
INSERT INTO `classtype` VALUES (2, '舞蹈类', '2019-03-13 14:19:39');
INSERT INTO `classtype` VALUES (3, '类别1', '2019-03-13 16:56:09');
INSERT INTO `classtype` VALUES (4, '类别二', '2019-03-13 16:57:12');
INSERT INTO `classtype` VALUES (5, '类别三', '2019-03-13 16:57:21');
INSERT INTO `classtype` VALUES (6, '足球类别', '2019-03-15 11:35:11');
INSERT INTO `classtype` VALUES (7, '计算级教室', '2019-03-15 16:13:28');
INSERT INTO `classtype` VALUES (8, '化学实验室', '2019-03-15 16:14:17');
INSERT INTO `classtype` VALUES (9, '生物实验室', '2019-03-15 16:14:45');
INSERT INTO `classtype` VALUES (10, '科学实验室', '2019-03-15 16:15:30');
INSERT INTO `classtype` VALUES (11, '自然科学', '2019-03-15 16:16:22');

-- ----------------------------
-- Table structure for courses
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `week` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '固定周几',
  `date` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '固定时间段',
  `roomid` int(11) NULL DEFAULT NULL COMMENT '固定教室id',
  `classroom` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '固定教室名称',
  `jsid` int(11) NULL DEFAULT NULL COMMENT '固定教师id',
  `teacher` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '固定教师名称',
  `classesid` int(11) NULL DEFAULT NULL COMMENT '固定班级id',
  `classes` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '固定班级名称',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `tid` int(11) NULL DEFAULT NULL COMMENT '班主任id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班主任姓名',
  `plan_id` int(11) NOT NULL COMMENT '排课id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of courses
-- ----------------------------
INSERT INTO `courses` VALUES (11, '1', '09:00-11:00', 3, '教室1', NULL, '请选择', 48, '大1', '2019-03-13 17:08:22', 24, '朱未月', 13);
INSERT INTO `courses` VALUES (12, '2', '09:00-11:00', 4, '教室二', NULL, '老师3', 46, '班级2', '2019-03-13 17:11:51', 24, '朱未月', 14);
INSERT INTO `courses` VALUES (13, '2', '14:00-16:00', 3, '教室1', NULL, '老师3', 46, '班级2', '2019-03-13 17:22:07', 24, '朱未月', 15);
INSERT INTO `courses` VALUES (14, '2', '17:00-19:00', 6, '天津权健足球教室', NULL, '老师3', 46, '班级2', '2019-03-15 14:54:01', 27, '陈世璠', 16);

-- ----------------------------
-- Table structure for coursetime
-- ----------------------------
DROP TABLE IF EXISTS `coursetime`;
CREATE TABLE `coursetime`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `coursename` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '课时名称',
  `time` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '时间段',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '时间段代表int',
  `timetype` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '0为上午1为下午\r\n',
  `crttime` datetime(0) NULL DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '状态(0:启用、1:停用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of coursetime
-- ----------------------------
INSERT INTO `coursetime` VALUES (9, '第一课时', '09:00-11:00', NULL, NULL, '2019-03-13 16:42:27', '0');
INSERT INTO `coursetime` VALUES (10, '第二课时', '14:00-16:00', NULL, NULL, '2019-03-13 16:42:54', '0');
INSERT INTO `coursetime` VALUES (11, '第三课时', '17:00-19:00', NULL, NULL, '2019-03-15 14:49:14', '0');

-- ----------------------------
-- Table structure for identitys
-- ----------------------------
DROP TABLE IF EXISTS `identitys`;
CREATE TABLE `identitys`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `duties` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '职务',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for inforremind
-- ----------------------------
DROP TABLE IF EXISTS `inforremind`;
CREATE TABLE `inforremind`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '消息分类（0：教师请假、1：上课提醒）',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '消息内容',
  `createid` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  `createname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `calltid` int(11) NULL DEFAULT NULL COMMENT '提醒人id',
  `calltname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '提醒人姓名',
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '状态（0：已处理、1：未处理）',
  `sourceid` int(11) NULL DEFAULT NULL COMMENT '来源信息id',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for meetrecord
-- ----------------------------
DROP TABLE IF EXISTS `meetrecord`;
CREATE TABLE `meetrecord`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `weeksort` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '周次',
  `tid` int(11) NULL DEFAULT NULL COMMENT '教师id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教师姓名',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '主题',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '记录日期',
  `classid` int(11) NULL DEFAULT NULL COMMENT '班级id',
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `meettime` datetime(0) NULL DEFAULT NULL COMMENT '会议时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for memorabilia
-- ----------------------------
DROP TABLE IF EXISTS `memorabilia`;
CREATE TABLE `memorabilia`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '内容',
  `userid` int(11) NULL DEFAULT NULL COMMENT '填写人id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '填写人姓名',
  `crttime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '填写时间',
  `times` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of memorabilia
-- ----------------------------
INSERT INTO `memorabilia` VALUES (5, '啊大苏打', '啊大苏打实打实大苏打', NULL, NULL, '2019-03-13 14:12:20', NULL);
INSERT INTO `memorabilia` VALUES (6, '王测试', '测试内容', NULL, NULL, '2019-03-15 13:43:50', '2019-03-15 00:00:00');

-- ----------------------------
-- Table structure for planrecord
-- ----------------------------
DROP TABLE IF EXISTS `planrecord`;
CREATE TABLE `planrecord`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `week` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '周几',
  `courseid` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '课程时间id',
  `classroom` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教室',
  `teacher` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '授课教师',
  `datetype` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '类型（0为上午1为下午）',
  `tid` int(11) NULL DEFAULT NULL COMMENT '排课人id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '排课人姓名',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `classid` int(11) NULL DEFAULT NULL COMMENT '班级id',
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of planrecord
-- ----------------------------
INSERT INTO `planrecord` VALUES (13, '1', '9', '教室1', '请选择', NULL, 24, '朱未月', '2019-03-13 17:08:21', 48, '大1');
INSERT INTO `planrecord` VALUES (14, '2', '9', '教室二', '老师3', NULL, 24, '朱未月', '2019-03-13 17:11:10', 46, '班级2');
INSERT INTO `planrecord` VALUES (15, '2', '10', '教室1', '老师3', NULL, 24, '朱未月', '2019-03-13 17:22:07', 46, '班级2');
INSERT INTO `planrecord` VALUES (16, '2', '11', '天津权健足球教室', '老师3', NULL, 27, '陈世璠', '2019-03-15 14:54:01', 46, '班级2');

-- ----------------------------
-- Table structure for power
-- ----------------------------
DROP TABLE IF EXISTS `power`;
CREATE TABLE `power`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `rules` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '权限规则（路径名称）',
  `powername` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '权限名称',
  `category` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '所属分类',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `types` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '属性（0：菜单、1：按钮）',
  `iconcls` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '图标',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `powername`(`powername`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 57 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of power
-- ----------------------------
INSERT INTO `power` VALUES (1, '/term', '基础设置', '0', '2019-03-15 18:12:48', '0', 'iconfont icon-shezhi', NULL);
INSERT INTO `power` VALUES (2, '/term,/term/termlist,/term/deleteterm,/term/newterm,/term/updateterm,/term/detail,/term/active,/term/disable', '学期维护', '1', '2019-03-15 18:16:19', '0', NULL, '../xqgl.html');
INSERT INTO `power` VALUES (3, '/classroom/list,/classroom/addNewClassRoom,/classroom/updateClassroom,/classroom/clsRmUsageHistory,/classroom/delete,/classroom/query,/classroom/tylist,/classroom/courselist,/classroom/getClassroom,/classroom/queryvacantclsroom', '教室类别', '1', '2019-03-15 18:18:13', '0', NULL, '../jslb_jslb.html');
INSERT INTO `power` VALUES (4, '/identity/list,/identity/save,/identity/query,/identity/update,/identity/delete', '班委会职务', '1', '2019-03-15 18:18:52', '0', NULL, '../bzrgl_bwh.html');
INSERT INTO `power` VALUES (5, '/user/list', '人员管理', '0', '2019-03-15 18:22:23', '0', 'iconfont icon-jiaoseguanli', NULL);
INSERT INTO `power` VALUES (6, '/teacher,/teacher/enter,/teacher/list,/teacher/delete,/teacher/detail,/teacher/update,/teacher/query-prfs-teacher,/teacher/queryteacherbyroleid,/teacher/charge-cls,/teacher/ifhasbindcls,/teacher/queryteacherbyroleids', '教师管理', '5', '2019-03-15 18:25:10', '0', NULL, '../yhgl_lsgl.html');
INSERT INTO `power` VALUES (7, '/student,/student/enter,/student/list,/student/detail,/student/all-detail,/student/delete,/student/update,/student/add', '学生管理', '5', '2019-03-15 18:25:33', '0', NULL, '../yhgl_xsgl.html');
INSERT INTO `power` VALUES (8, '/roles,/role/create,/role/role-list,/role/delete,/role/detail,/role/update,/role/allocate', '角色管理', '0', '2019-03-15 18:27:48', '0', 'iconfont icon-jiaoseguanli1', NULL);
INSERT INTO `power` VALUES (9, '/power/powers,/role/role-list', '角色绑定权限', '8', '2019-03-15 18:29:07', '0', NULL, '../jsgl_jsbdqx.html');
INSERT INTO `power` VALUES (10, '/roles', '角色绑定用户', '8', '2019-03-15 18:29:10', '0', NULL, '../jsgl_jsbdyh.html');
INSERT INTO `power` VALUES (11, '/classroom/toClassroomCategoryManage,/classroom/toClassroomManage,/classroom/addNewCategory,/classroom/updateClassRoomType,/classroom/addNewClassRoom,/classroom/updateClassroom,/classroom/clsRmUsageHistory,/classroom/typelist,/classroom/list', '教室管理', '0', '2019-03-15 18:29:45', '0', 'iconfont icon-tubiao_jiaoshiguanli', NULL);
INSERT INTO `power` VALUES (12, '/classroom/typelist,/classroom/addNewCategory,/classroom/updateClassRoomType,/classroom/querytype,/classroom/deletetype,/classroom/getCate', '教室列表', '11', '2019-03-15 18:52:40', '0', NULL, '../jslb_jslbgl.html');
INSERT INTO `power` VALUES (13, '/classes/add,/classes/save,/classes/catelist,/classes/prolist,/classes/save,/classes/query,/classes/update,/classes/delete,/classes/clist,/classes/all-categories,/classes/all-professions,/common/getTealist', '班级管理', '0', '2019-03-15 18:52:40', '0', 'iconfont icon-banji', NULL);
INSERT INTO `power` VALUES (14, '/class,/classes/classcatelist,/classes/professions,/classes/queryclsbycateidandprfid,/classes/queryclscatetoacquirecls,/classes/categories,/classes/queryClsByPrfs,/classes/class-list', '班级维护', '13', '2019-03-15 18:52:40', '0', NULL, '../bjgl.html');
INSERT INTO `power` VALUES (15, '/register', '报名管理', '0', '2019-03-15 18:52:40', '0', 'iconfont icon-baomingguanli', NULL);
INSERT INTO `power` VALUES (16, '/register', '学生报名', '15', '2019-03-15 18:52:40', '0', NULL, '../bmgl_xzbj.html');
INSERT INTO `power` VALUES (17, '/arrange,/schedule-class/update,/schedule-class/detail', '排课管理', '0', '2019-03-15 18:52:40', '0', 'iconfont icon-jiaoseguanli1', NULL);
INSERT INTO `power` VALUES (18, '/timeslot,/schedule-class/pre/list,/schedule-class/pre/course-time,/schedule-class/pre/coursetimebyone,/schedule-class/pre/detail,/schedule-class/pre/delete,/schedule-class/pre/update,/schedule-class/delete', '时间段管理', '17', '2019-03-15 18:52:40', '0', NULL, '../pkgl_ksdgl.html');
INSERT INTO `power` VALUES (19, '/autoarrange,/schedule-class/plan-record,/schedule-class/schedule-course,/schedule-class/detail', '自动排课', '17', '2019-03-15 18:52:40', '0', NULL, '../pkgl_zdpk.html');
INSERT INTO `power` VALUES (20, '/handarrange,/course/scanCurriculum,/course/export', '手动改课', '17', '2019-03-15 18:52:40', '0', NULL, '../pkgl_sdpk.html');
INSERT INTO `power` VALUES (21, '/teaching', '教学管理', '0', '2019-03-15 18:52:40', '0', 'iconfont icon-guanli', NULL);
INSERT INTO `power` VALUES (22, '/teachmanager/list,/teachmanager/getNameList,/teachmanager/classlist,/teachmanager/tealist,/teachmanager/save,/teachmanager/query,/teachmanager/export,/teachmanager/catelist,/teachmanager/prolist,/teachmanager/teacherlist', '请假单', '21', '2019-03-15 18:52:40', '0', NULL, '../jxgl_qj.html');
INSERT INTO `power` VALUES (23, '/reverse', '倒课单', '21', '2019-03-15 18:52:40', '0', NULL, '../jxgl_dk.html');
INSERT INTO `power` VALUES (24, '/stopclass', '停课单', '21', '2019-03-15 18:52:40', '0', NULL, '../jxgl_tk.html');
INSERT INTO `power` VALUES (25, '/classmaster', '班主任日志套', '0', '2019-03-15 18:52:40', '0', 'iconfont icon-icon_banzhurenguanliweixuanzhong', NULL);
INSERT INTO `power` VALUES (26, '/roster/list,/roster/clist,/roster/query,/roster/save,/roster/identity,/roster/jundge,/roster/conversion,/roster/exportcheckinform', '花名册', '25', '2019-03-15 18:52:40', '0', NULL, '../bzrgl_hmc.html');
INSERT INTO `power` VALUES (27, '/worksummary/list,/worksummary/getTitle,/worksummary/save,/worksummary/update,/worksummary/delete,/worksummary/detail,/worksummary/export', '班主任工作总结', '25', '2019-03-15 18:52:40', '0', NULL, '../bzrgl_bzrgzzj.html');
INSERT INTO `power` VALUES (28, '/workplan/list,/workplan/classlist,/workplan/save,/workplan/detail,/workplan/update,/workplan/delete,/workplan/export,/workplan/catelist,/workplan/prolist,/workplan/classlist,/workplan/getclasslist,/workplan/judgeheader,/workplan/getClass', '班级工作计划', '25', '2019-03-15 18:52:40', '0', NULL, '../bzrgl_bjgzjh.html');
INSERT INTO `power` VALUES (29, '/specialmanager/list,/specialmanager/getStuByName,/specialmanager/save,/specialmanager/query,/specialmanager/update,/specialmanager/delete,/specialmanager/export,/specialmanager/querylist', '特殊学员记录', '25', '2019-03-15 18:52:40', '0', NULL, '../bzrgl_tsxyjl.html');
INSERT INTO `power` VALUES (30, '/awardrecord/list,/awardrecord/getTitle,/awardrecord/save,/awardrecord/query,/awardrecord/update,/awardrecord/delete,/awardrecord/export', '获奖记录', '25', '2019-03-15 18:52:40', '0', NULL, '../bzrgl_hjjl.html');
INSERT INTO `power` VALUES (31, '/activityrecord/list,/activityrecord/save,/activityrecord/query,/activityrecord/update,/activityrecord/delete,/activityrecord/export,/activityrecord/getTitle', '学校活动记录', '25', '2019-03-15 18:52:40', '0', NULL, '../bzrgl_xxhdjl.html');
INSERT INTO `power` VALUES (32, '/classsuggest/list,/classsuggest/getTitle,/classsuggest/save,/classsuggest/query,/classsuggest/update,/classsuggest/delete,/classsuggest/exportclzissuerecmd', '班级问题与建议', '25', '2019-03-15 18:52:40', '0', NULL, '../bzrgl_bjwtyjy.html');
INSERT INTO `power` VALUES (33, '/meetrecord/list,/meetrecord/getTitle,/meetrecord/save,/meetrecord/query,/meetrecord/update,/meetrecord/delete,/meetrecord/exportmeetrcd', '班会记录', '25', '2019-03-15 18:52:40', '0', NULL, '../bzrgl_bhjl.html');
INSERT INTO `power` VALUES (34, '/worknotes/list,/worknotes/getTitle,/worknotes/list,/worknotes/save,/worknotes/query,/worknotes/update,/worknotes/delete,/worknotes/export', '班主任工作手记', '25', '2019-03-15 18:52:40', '0', NULL, '../bzrgl_bzrgzsj.html');
INSERT INTO `power` VALUES (35, '/studymeet/list,/studymeet/slist,/roster/list', '学委会管理', '0', '2019-03-15 18:52:40', '0', 'iconfont icon-houtaitubiao-1', NULL);
INSERT INTO `power` VALUES (36, '/studymeet/list,/studymeet/slist,/studymeet/delete,/studymeet/save,/studymeet/addduties,/studymeet/query,/studymeet/getName', '学委会成员', '35', '2019-03-15 18:52:40', '0', NULL, '../xwhgl.html');
INSERT INTO `power` VALUES (37, '/studylog/list,/studylog/save,/studylog/query,/studylog/delete,/studylog/update,/studylog/getTitle', '学委会活动记录', '35', '2019-03-15 18:52:40', '0', NULL, '../xwhrz.html');
INSERT INTO `power` VALUES (38, '/common/catelist,/common/prolist,/common/classlist,/common/studentlist,/common/judge,/common/getClass', '档案管理', '0', '2019-03-15 18:52:40', '0', 'iconfont icon-houtaitubiao-2', NULL);
INSERT INTO `power` VALUES (39, '/memorabilia/list,/memorabilia/save,/memorabilia/update,/memorabilia/queryMemorabiliaById,/memorabilia/delete,/memorabilia/export,/memorabilia/getTitle', '大事记', '38', '2019-03-15 18:52:40', '0', NULL, '../dagl_dsj.html');
INSERT INTO `power` VALUES (40, '/award/list,/award/save,/award/export,/award/update,/award/delete,/award/query,/award/getTitle', '获奖档案', '38', '2019-03-15 18:52:40', '0', NULL, '../dagl_hjqk.html');
INSERT INTO `power` VALUES (41, '/scientific/list,/scientific/save,/scientific/export,/scientific/update,/scientific/delete,/scientific/query,/scientific/getTitle', '科研档案', '38', '2019-03-15 18:52:40', '0', NULL, '../dagl_kyhd.html');
INSERT INTO `power` VALUES (42, '/resource/list,/resource/saveResource,/resource/queryResourceById,/resource/update,/resource/delete,/resource/getTitle,/resource/export', '资源建设', '38', '2019-03-15 18:52:40', '0', NULL, '../dagl_zyjs.html');
INSERT INTO `power` VALUES (43, '/activities/list,/activities/save,/activities/query,/activities/update,/activities/export,/activities/delete,/activities/getTitle,/activities/exportstatistics', '学院活动', '38', '2019-03-15 18:52:40', '0', NULL, '../dagl_xyhd.html');
INSERT INTO `power` VALUES (44, '/studentrecord/termlist,/studentrecord/getlist,/studentrecord/classlist,/studentrecord/stulist', '学籍档案', '38', '2019-03-15 18:52:40', '0', NULL, '../dagl_xjda.html');
INSERT INTO `power` VALUES (45, '/statistics,/statistics/student/base/wordoutput', '数据分析', '0', '2019-03-15 18:52:40', '0', 'iconfont icon-shujutongji', NULL);
INSERT INTO `power` VALUES (46, '/statistics,/statistics/student/total', '学院总人数', '45', '2019-03-15 18:52:40', '0', NULL, '../sjtj_xyzrs.html');
INSERT INTO `power` VALUES (47, '/statistics,/statistics/student/newrate', '新生率', '45', '2019-03-15 18:52:40', '0', NULL, '../sjtj_xsl.html');
INSERT INTO `power` VALUES (48, '/statistics,,/statistics/student/lossrate', '学员流失率', '45', '2019-03-15 18:52:40', '0', NULL, '../sjtj_xylsl.html');
INSERT INTO `power` VALUES (49, '/statistics,/statistics/student/base/agerate', '年龄统计', '45', '2019-03-15 18:52:40', '0', NULL, '../sjtj_nltj.html');
INSERT INTO `power` VALUES (50, '/statistics/student/base/sexrate,/statistics/student/base/wordoutput', '性别统计', '45', '2019-03-15 18:52:40', '0', NULL, '../sjtj_xbtj.html');
INSERT INTO `power` VALUES (51, '/statistics/student/base/rewardstatistics', '奖励统计', '45', '2019-03-15 18:52:40', '0', NULL, '../sjtj_jltj.html');
INSERT INTO `power` VALUES (52, '/statistics/student/base/problemstatistics', '问题统计', '45', '2019-03-15 18:52:40', '0', NULL, '../sjtj_wttj.html');

-- ----------------------------
-- Table structure for profession
-- ----------------------------
DROP TABLE IF EXISTS `profession`;
CREATE TABLE `profession`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `majornumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '专业序号',
  `majorname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '专业名称',
  `createid` int(11) NOT NULL COMMENT '创建人id',
  `createname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `cateid` int(11) NULL DEFAULT NULL COMMENT '类别id',
  `catename` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '类别名称',
  `numbers` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '类别序号',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of profession
-- ----------------------------
INSERT INTO `profession` VALUES (1, '1', '葫芦丝', 1, '测试', 1, '器乐', '1', '2018-12-04 10:52:53');
INSERT INTO `profession` VALUES (2, '2', '电子琴', 1, '测试', 1, '器乐', '1', '2018-12-04 10:53:19');
INSERT INTO `profession` VALUES (3, '3', '二胡', 1, '测试', 1, '器乐', '1', '2018-12-04 10:53:41');
INSERT INTO `profession` VALUES (4, '4', '古筝', 1, '测试', 1, '器乐', '1', '2018-12-04 10:53:55');
INSERT INTO `profession` VALUES (5, '5', '歌唱', 1, '测试', 2, '声乐', '2', '2018-12-04 10:54:17');
INSERT INTO `profession` VALUES (6, '6', '合唱', 1, '测试', 2, '声乐', '2', '2018-12-04 10:54:32');
INSERT INTO `profession` VALUES (7, '7', '京剧', 1, '测试', 2, '声乐', '2', '2018-12-04 10:54:49');
INSERT INTO `profession` VALUES (8, '8', '民族舞', 1, '测试', 3, '舞蹈', '3', '2018-12-04 10:55:16');
INSERT INTO `profession` VALUES (9, '9', '拉丁舞', 1, '测试', 3, '舞蹈', '3', '2018-12-04 10:55:30');
INSERT INTO `profession` VALUES (10, '10', '摩登舞', 1, '测试', 3, '舞蹈', '3', '2018-12-04 10:55:50');
INSERT INTO `profession` VALUES (11, '11', '交谊舞', 1, '测试', 3, '舞蹈', '3', '2018-12-04 10:56:06');
INSERT INTO `profession` VALUES (12, '12', '民族民间舞', 1, '测试', 3, '舞蹈', '3', '2018-12-04 10:56:22');
INSERT INTO `profession` VALUES (13, '13', '中国古典舞', 1, '测试', 3, '舞蹈', '3', '2018-12-04 10:56:39');
INSERT INTO `profession` VALUES (14, '14', '水兵舞', 1, '测试', 3, '舞蹈', '3', '2018-12-04 10:57:02');
INSERT INTO `profession` VALUES (15, '15', '国际舞', 1, '测试', 3, '舞蹈', '3', '2018-12-04 10:57:18');
INSERT INTO `profession` VALUES (16, '16', '营养与养生', 1, '测试', 4, '养生保健', '4', '2018-12-04 10:57:43');
INSERT INTO `profession` VALUES (17, '17', '按摩养生', 1, '测试', 4, '养生保健', '4', '2018-12-04 10:58:04');
INSERT INTO `profession` VALUES (18, '18', '服饰表演', 1, '测试', 4, '养生保健', '4', '2018-12-04 10:58:23');
INSERT INTO `profession` VALUES (19, '19', '陈式太极48扇', 1, '测试', 4, '养生保健', '4', '2018-12-04 10:58:44');
INSERT INTO `profession` VALUES (20, '20', '48式太极拳', 1, '测试', 4, '养生保健', '4', '2018-12-04 10:59:04');
INSERT INTO `profession` VALUES (21, '21', '32式太极拳', 1, '测试', 4, '养生保健', '4', '2018-12-04 10:59:28');
INSERT INTO `profession` VALUES (22, '22', '16式太极拳', 1, '测试', 4, '养生保健', '4', '2018-12-04 10:59:47');
INSERT INTO `profession` VALUES (23, '23', '篆书与篆刻', 1, '测试', 5, '书法', '5', '2018-12-04 11:00:07');
INSERT INTO `profession` VALUES (24, '24', '行书', 1, '测试', 5, '书法', '5', '2018-12-04 11:00:27');
INSERT INTO `profession` VALUES (25, '25', '楷书', 1, '测试', 5, '书法', '5', '2018-12-04 11:00:43');
INSERT INTO `profession` VALUES (26, '26', '隶书', 1, '测试', 5, '书法', '5', '2018-12-04 11:01:00');
INSERT INTO `profession` VALUES (27, '27', ' 钢笔画', 1, '测试', 6, '绘画', '6', '2018-12-04 11:01:20');
INSERT INTO `profession` VALUES (28, '28', '南宗山水', 1, '测试', 6, '绘画', '6', '2018-12-04 11:01:38');
INSERT INTO `profession` VALUES (29, '29', '花鸟', 1, '测试', 6, '绘画', '6', '2018-12-04 11:01:58');
INSERT INTO `profession` VALUES (30, '30', '山水', 1, '测试', 6, '绘画', '6', '2018-12-04 11:02:16');
INSERT INTO `profession` VALUES (31, '31', '水彩', 1, '测试', 6, '绘画', '6', '2018-12-04 11:02:29');
INSERT INTO `profession` VALUES (32, '32', '工笔画', 1, '测试', 6, '绘画', '6', '2018-12-04 11:02:50');
INSERT INTO `profession` VALUES (33, '33', '水墨水乡画', 1, '测试', 6, '绘画', '6', '2018-12-04 11:03:05');
INSERT INTO `profession` VALUES (34, '34', '素描', 1, '测试', 6, '绘画', '6', '2018-12-04 11:03:20');
INSERT INTO `profession` VALUES (35, '35', '朗诵', 1, '测试', 7, '语言', '7', '2018-12-04 11:03:40');
INSERT INTO `profession` VALUES (36, '36', '英语口语', 1, '测试', 7, '语言', '7', '2018-12-04 11:04:00');
INSERT INTO `profession` VALUES (37, '37', '戏剧体验', 1, '测试', 7, '语言', '7', '2018-12-04 11:04:21');
INSERT INTO `profession` VALUES (38, '38', '老年心理学', 1, '测试', 8, '生活艺术', '8', '2018-12-04 11:05:00');
INSERT INTO `profession` VALUES (39, '39', '形象设计', 1, '测试', 8, '生活艺术', '8', '2018-12-04 11:05:16');
INSERT INTO `profession` VALUES (40, '40', '数码摄影', 1, '测试', 8, '生活艺术', '8', '2018-12-04 11:05:31');
INSERT INTO `profession` VALUES (41, '41', '证券理财', 1, '测试', 8, '生活艺术', '8', '2018-12-04 11:05:52');
INSERT INTO `profession` VALUES (42, '42', '中西面点', 1, '测试', 8, '生活艺术', '8', '2018-12-04 11:06:10');
INSERT INTO `profession` VALUES (43, '43', '国学修养', 1, '测试', 8, '生活艺术', '8', '2018-12-04 11:06:26');
INSERT INTO `profession` VALUES (44, '44', '母婴护理', 1, '测试', 8, '生活艺术', '8', '2018-12-04 11:06:44');
INSERT INTO `profession` VALUES (45, '45', '会声会影', 1, '测试', 8, '生活艺术', '8', '2018-12-04 11:06:57');
INSERT INTO `profession` VALUES (46, '46', '手机应用', 1, '测试', 8, '生活艺术', '8', '2018-12-04 11:07:12');
INSERT INTO `profession` VALUES (47, '47', '面塑', 1, '测试', 9, '传统技艺', '9', '2018-12-04 11:07:32');
INSERT INTO `profession` VALUES (48, '48', '泥塑', 1, '测试', 9, '传统技艺', '9', '2018-12-04 11:07:59');
INSERT INTO `profession` VALUES (49, '49', '剪纸', 1, '测试', 9, '传统技艺', '9', '2018-12-04 11:08:17');
INSERT INTO `profession` VALUES (50, '50', '刻瓷', 1, '测试', 9, '传统技艺', '9', '2018-12-04 11:08:31');
INSERT INTO `profession` VALUES (51, '51', '葫芦烙画', 1, '测试', 9, '传统技艺', '9', '2018-12-04 11:08:51');
INSERT INTO `profession` VALUES (52, '52', '杨柳青年画', 1, '测试', 9, '传统技艺', '9', '2018-12-04 11:09:08');

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL DEFAULT '0' COMMENT '类别\r\n（0：数字化资源建设；1：教材建设）',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '内容',
  `crttime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '填写时间',
  `userid` int(11) NULL DEFAULT NULL COMMENT '填写人id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '填写人姓名',
  `times` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `rolename` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '角色名称',
  `powerid` varchar(500) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '权限id',
  `powername` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '权限名称',
  `description` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '角色描述',
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '状态\n（0：启用、1：停用）',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `rolename`(`rolename`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (24, '班主任', '1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,44,43,45,46,47,48,49,50,51,', '班级管理,班级维护,学期管理,学期维护,报名管理,选择班级,用户管理,老师管理,学生管理,数据分析,学院总人数,新生率,学院流失率,年龄统计,性别统计,奖励统计,问题统计,角色管理,角色绑定权限,角色绑定用户,班主任管理,班委会职位管理,花名册,班主任工作总结,班级工作计划,特殊学员记录,获奖记录,学校活动记录,班级问题与建议,班会记录,班主任工作手记,档案管理,大事记,获奖情况,科研情况,资源建设,学院活动,学籍档案,教室管理,教室列表,教室类别,教学管理,倒课,请假,排课管理,时间段管理,自动排课,手动改课,学委会管理,学委会维护,学委会日志管理,', '班主任', '0', '2019-03-13 14:08:10');
INSERT INTO `role` VALUES (26, '角色1', '1,2,3,4,5,6,7,8,9,55,56,10,11,57,12,13,14,15,16,17,18,19,20,50,51,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,', '用户管理,老师管理,学生管理,角色管理,角色绑定权限,角色绑定用户,教室管理,教室类别,教室列表,教室查询,教室类别查询,班级管理,班级维护,班级类别查询,学期管理,学期维护,报名管理,选择班级,排课管理,时间段管理,自动排课,手动改课,教学管理,请假,倒课,班主任管理,班委会职位管理,花名册,班主任工作总结,班级工作计划,特殊学员记录,获奖记录,学校活动记录,班级问题与建议,班会记录,班主任工作手记,学委会管理,学委会维护,学委会日志管理,档案管理,大事记,获奖情况,科研情况,资源建设,学院活动,学籍档案,数据分析,学院总人数,新生率,学院流失率,年龄统计,性别统计,奖励统计,问题统计,', '角色1', '0', '2019-03-15 11:08:07');

-- ----------------------------
-- Table structure for roster
-- ----------------------------
DROP TABLE IF EXISTS `roster`;
CREATE TABLE `roster`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `birthdate` datetime(0) NULL DEFAULT NULL COMMENT '出生日期',
  `stunumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学号',
  `classid` int(11) NULL DEFAULT NULL COMMENT '班级id',
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  `stuid` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `stuname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `isleader` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '职位\n（学生)',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '状态（0:退班、1：转班、2：休学、3：开除）',
  `termid` int(11) NULL DEFAULT NULL COMMENT '学期id',
  `termname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学期名称',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '联系方式',
  `famPhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '家庭联系方式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 171 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci COMMENT = '花名册信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of roster
-- ----------------------------
INSERT INTO `roster` VALUES (153, '1966-02-10 00:00:00', '201901010101', 46, '班级2', 61, '朱未月', 53, NULL, '2019-03-13 16:22:19', NULL, 11, '2019春季', '18888888888', '18888888888');
INSERT INTO `roster` VALUES (156, '1966-02-10 00:00:00', '201901010101', 46, '班级2', 61, '朱未月', 53, NULL, '2019-03-13 16:23:25', NULL, 12, '2019秋季', '18888888888', '18888888888');
INSERT INTO `roster` VALUES (157, '1964-02-10 00:00:00', '201901010102', 46, '班级2', 62, '王侯将相', 55, NULL, '2019-03-13 16:26:00', NULL, 11, '2019春季', '19999990000', '19999990000');
INSERT INTO `roster` VALUES (158, '1959-02-10 00:00:00', '201901010103', 46, '班级2', 63, '宁有种乎', 60, NULL, '2019-03-13 16:28:07', NULL, 12, '2019秋季', '12222222222', '12222222222');
INSERT INTO `roster` VALUES (161, '1964-02-10 00:00:00', '201901010102', 46, '班级2', 62, '王侯将相', 55, NULL, '2019-03-13 16:33:43', NULL, 12, '2019秋季', '18888888888', '18888888888');
INSERT INTO `roster` VALUES (162, '1964-02-10 00:00:00', '201901020101', 48, '大1', 62, '王侯将相', 55, NULL, '2019-03-13 16:33:43', NULL, 12, '2019秋季', '18888888888', '18888888888');
INSERT INTO `roster` VALUES (163, '1964-02-10 00:00:00', '201906330101', 44, '班级1', 62, '王侯将相', 55, NULL, '2019-03-13 16:33:43', NULL, 12, '2019秋季', '18888888888', '18888888888');
INSERT INTO `roster` VALUES (164, '1958-11-23 00:00:00', '201906330102', 44, '班级1', 64, '梅西', 61, NULL, '2019-03-15 13:21:24', NULL, 12, '2019秋季', '13812341234', '合肥');
INSERT INTO `roster` VALUES (165, '1952-11-24 00:00:00', '201901020102', 48, '大1', 65, '陈世璠', 67, NULL, '2019-03-15 13:34:46', NULL, 11, '2019春季', '18326156169', '222');
INSERT INTO `roster` VALUES (166, '1968-11-24 00:00:00', '201901020103', 48, '大1', 66, '陈世璠', 51, NULL, '2019-03-15 13:44:19', NULL, 11, '2019春季', '18326156160', '18326156160');
INSERT INTO `roster` VALUES (167, '1942-11-24 00:00:00', '201906330103', 44, '班级1', 67, '陈世璠', 77, NULL, '2019-03-15 14:15:29', NULL, 12, '2019秋季', '13812344321', '学习');
INSERT INTO `roster` VALUES (168, '1997-10-29 00:00:00', '201906330104', 44, '班级1', 68, '张云龙', 22, NULL, '2019-03-15 14:20:51', NULL, 12, '2019秋季', '18326156169', '谢家集10-1');
INSERT INTO `roster` VALUES (169, '1962-11-24 00:00:00', '201906330105', 44, '班级1', 69, '陈世璠', 57, NULL, '2019-03-15 14:31:52', NULL, 12, '2019秋季', '18322716765', '哈哈');
INSERT INTO `roster` VALUES (170, '1977-10-20 00:00:00', '201906330106', 44, '班级1', 70, '张心心', 42, NULL, '2019-03-15 15:55:32', NULL, 11, '2019春季', '13655635662', '13655635662');

-- ----------------------------
-- Table structure for scientific
-- ----------------------------
DROP TABLE IF EXISTS `scientific`;
CREATE TABLE `scientific`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '内容',
  `crttime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '填写时间',
  `userid` int(11) NULL DEFAULT NULL COMMENT '填写人id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '填写人姓名',
  `times` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for specialmanager
-- ----------------------------
DROP TABLE IF EXISTS `specialmanager`;
CREATE TABLE `specialmanager`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sid` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `sname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学生性别（0：男、1：女）',
  `filtime` datetime(0) NULL DEFAULT NULL COMMENT '建档时间',
  `types` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '建档原因（0:退班、1：转班、2：休学、3：开除）',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '主要情况',
  `times` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '地点',
  `tid` int(11) NULL DEFAULT NULL COMMENT '教师id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教师姓名',
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '针对问题',
  `options` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '辅导情况',
  `classid` int(11) NULL DEFAULT NULL COMMENT '班级id',
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '联系方式',
  `stunumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学号',
  `idcard` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  `termid` int(11) NULL DEFAULT NULL COMMENT '学期id',
  `termname` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学期名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci COMMENT = '日志管理-特殊学员档案表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `stunumber` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '学员编号（多个，逗号隔开）',
  `sfzh` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '身份证号',
  `stuname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '学生姓名',
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '性别（0：男、1：女）',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `classid` varchar(50) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级id',
  `classname` varchar(200) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  `nation` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '民族',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '联系方式',
  `emergency` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '紧急联系人方式',
  `sfzaddress` varchar(500) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '身份证地址',
  `address` varchar(500) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '现地址',
  `carnumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '汽车牌照号',
  `photo` blob NULL COMMENT '照片地址',
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '状态\n(0:启用、1：停用)',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `updatetime` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `entertime` date NULL DEFAULT NULL COMMENT '入学年限',
  `graduatetime` date NULL DEFAULT NULL COMMENT '毕业年限',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (61, '201901010101', '342623196602101928', '朱未月', '女', 53, '46', '班级2', '汉', '18888888888', '18888888888', '安徽省马鞍山市雨山区鹊桥三村４栋１１０号', '的撒发生大', '皖AAA999', NULL, '0', '2019-03-13 16:22:19', '2019-03-13 16:22:19', '2019-03-13', '2020-03-13');
INSERT INTO `student` VALUES (62, '201901010102', '342623196402101928', '王侯将相', '女', 55, '46', '班级2', '汉', '18888888888', '18888888888', '安徽省马鞍山市雨山区鹊桥三村４栋１１０号', '的萨芬萨的撒范德萨', '皖AAA999', NULL, '0', '2019-03-13 16:26:00', '2019-03-13 16:26:00', '2019-03-13', '2020-03-13');
INSERT INTO `student` VALUES (63, '201901010103', '342623195902101928', '宁有种乎', '女', 60, '46', '班级2', '汉', '12222222222', '12222222222', '安徽省马鞍山市雨山区鹊桥三村４栋１１０号', 'v成本那边v免费的告诉对方', '皖BBB888', NULL, '0', '2019-03-13 16:28:07', '2019-03-13 16:28:07', '2019-03-13', '2020-03-13');
INSERT INTO `student` VALUES (64, '201906330102', '340827195811230098', '梅西', '男', 61, '44', '班级1', '汉', '13812341234', '合肥', '巴萨罗那', '合肥', '11111', NULL, '0', '2019-03-15 13:21:24', '2019-03-15 13:21:24', '2019-03-01', '2019-03-28');
INSERT INTO `student` VALUES (65, '201901020102', '340827195211240032', '陈世璠', '男', 67, '48', '大1', '汉', '18326156169', '222', '安徽省安庆市望江县华阳镇东洲路４０-３号', '11', '22222', NULL, '0', '2019-03-15 13:34:46', '2019-03-15 13:34:46', '2019-03-15', '2019-03-30');
INSERT INTO `student` VALUES (66, '201901020103', '340827196811240032', '鲁梅尼格', '男', 51, '48', '大1', '汉', '18326156160', '18326156160', '安徽省安庆市望江县华阳镇东洲路４０-３号', '18326156160', '', NULL, '0', '2019-03-15 13:44:19', '2019-03-15 13:44:19', '2019-03-15', '2019-03-16');
INSERT INTO `student` VALUES (67, '201906330103', '340827194211240032', '陈世璠', '男', 77, '44', '班级1', '汉', '13812344321', '学习', '安徽省安庆市望江县华阳镇东洲路４０-３号', '哈哈', 'D', NULL, '0', '2019-03-15 14:15:29', '2019-03-15 14:15:29', '2019-03-08', '2019-03-23');
INSERT INTO `student` VALUES (68, '201906330104', '340404199710290812', '张云龙', '男', 22, '44', '班级1', '汉', '18326156169', '谢家集10-1', '安徽省淮南市谢家集区蔡新路红建村１２栋６号', '淮南谢家集', NULL, NULL, '0', '2019-03-15 14:20:51', '2019-03-15 14:20:51', '2019-03-15', '2019-03-22');
INSERT INTO `student` VALUES (69, '201906330105', '340827196211240032', '陈世璠', '男', 57, '44', '班级1', '汉', '18322716765', '哈哈', '安徽省安庆市望江县华阳镇东洲路４０-３号', '哈哈', NULL, NULL, '0', '2019-03-15 14:31:52', '2019-03-15 14:31:52', '2019-03-15', '2019-03-16');
INSERT INTO `student` VALUES (70, '201906330106', '341281197710201021', '张心心', '男', 42, '44', '班级1', '汉族', '13655635662', '13655635662', '合肥', '合肥', '皖A33093', NULL, '0', '2019-03-15 15:55:32', '2019-03-15 15:55:32', '2019-03-15', '2020-03-15');

-- ----------------------------
-- Table structure for studententer
-- ----------------------------
DROP TABLE IF EXISTS `studententer`;
CREATE TABLE `studententer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `stuid` int(11) NOT NULL COMMENT '学生id',
  `stuname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `tid` int(11) NULL DEFAULT NULL COMMENT '经手人（教师id）',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '经手人姓名（教师姓名）',
  `invoicenumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '发票号',
  `cateid` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '类别id',
  `catename` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '类别名称',
  `majorid` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '专业id',
  `majorname` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '专业名称',
  `classid` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级id',
  `classname` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  `fee` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '报名费',
  `iscard` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '是否有学生证(0:有、1：无)',
  `signtime` datetime(0) NOT NULL COMMENT '报名时间',
  `term_id` int(11) NOT NULL COMMENT '学期id',
  `term_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学期名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 96 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of studententer
-- ----------------------------
INSERT INTO `studententer` VALUES (83, 61, '朱未月', 24, '朱未月', NULL, '1', '器乐', '1', '葫芦丝', '46', '班级2', '90', '0', '2019-03-13 16:22:19', 11, '2019春季');
INSERT INTO `studententer` VALUES (84, 61, '朱未月', 24, '朱未月', NULL, '1', '器乐', '1', '葫芦丝', '46', '班级2', '90', '0', '2019-03-13 16:23:25', 12, '2019秋季');
INSERT INTO `studententer` VALUES (85, 62, '王侯将相', 24, '朱未月', NULL, '1', '器乐', '1', '葫芦丝', '46', '班级2', '90', '0', '2019-03-13 16:26:00', 11, '2019春季');
INSERT INTO `studententer` VALUES (86, 63, '宁有种乎', 24, '朱未月', NULL, '1', '器乐', '1', '葫芦丝', '46', '班级2', '90', '0', '2019-03-13 16:28:07', 12, '2019秋季');
INSERT INTO `studententer` VALUES (88, 62, '王侯将相', 24, '朱未月', NULL, '1,1,6', '器乐,器乐,绘画', '1,2,33', '葫芦丝,电子琴,水墨水乡画', '46,48,44', '班级2,大1,班级1', '99', '0', '2019-03-13 16:33:43', 12, '2019秋季');
INSERT INTO `studententer` VALUES (89, 64, '梅西', 28, '贝肯鲍尔', NULL, '6', '绘画', '33', '水墨水乡画', '44', '班级1', '10000', '1', '2019-03-15 13:21:24', 12, '2019秋季');
INSERT INTO `studententer` VALUES (90, 65, '陈世璠', 28, '贝肯鲍尔', NULL, '1', '器乐', '2', '电子琴', '48', '大1', '111111', '0', '2019-03-15 13:34:46', 11, '2019春季');
INSERT INTO `studententer` VALUES (91, 66, '鲁梅尼格', 28, '贝肯鲍尔', NULL, '1', '器乐', '2', '电子琴', '48', '大1', '18326156160', '0', '2019-03-15 13:44:19', 11, '2019春季');
INSERT INTO `studententer` VALUES (92, 67, '陈世璠', 28, '贝肯鲍尔', NULL, '6', '绘画', '33', '水墨水乡画', '44', '班级1', '', '1', '2019-03-15 14:15:29', 12, '2019秋季');
INSERT INTO `studententer` VALUES (93, 68, '张云龙', 28, '贝肯鲍尔', NULL, '6', '绘画', '33', '水墨水乡画', '44', '班级1', '', '1', '2019-03-15 14:20:51', 12, '2019秋季');
INSERT INTO `studententer` VALUES (94, 69, '陈世璠', 28, '贝肯鲍尔', NULL, '6', '绘画', '33', '水墨水乡画', '44', '班级1', '', '1', '2019-03-15 14:31:52', 12, '2019秋季');
INSERT INTO `studententer` VALUES (95, 70, '张心心', NULL, '管理员', NULL, '6', '绘画', '33', '水墨水乡画', '44', '班级1', '2000', '1', '2019-03-15 15:55:32', 11, '2019春季');

-- ----------------------------
-- Table structure for studentrecord
-- ----------------------------
DROP TABLE IF EXISTS `studentrecord`;
CREATE TABLE `studentrecord`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `birthdate` datetime(0) NULL DEFAULT NULL COMMENT '出生日期',
  `stunumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学号',
  `classid` int(11) NULL DEFAULT NULL COMMENT '班级id',
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  `stuid` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `stuname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `isleader` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '职位\n（学生)',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `termid` int(11) NULL DEFAULT NULL COMMENT '学期id',
  `termname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学期名称',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '联系方式',
  `famPhone` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '家属联系方式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 85 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci COMMENT = '花名册信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for studylog
-- ----------------------------
DROP TABLE IF EXISTS `studylog`;
CREATE TABLE `studylog`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `tid` int(11) NULL DEFAULT NULL COMMENT '填写人id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '填写人姓名',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(5000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '内容',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `starttime` date NULL DEFAULT NULL COMMENT '开始时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for studymeet
-- ----------------------------
DROP TABLE IF EXISTS `studymeet`;
CREATE TABLE `studymeet`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sid` int(11) NULL DEFAULT NULL COMMENT '学生id',
  `sname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `business` varchar(50) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '职务',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `classid` varchar(50) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级id',
  `classname` varchar(200) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '联系方式',
  `emergency` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '家属联系方式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tnumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '教师工号',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教师姓名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '性别（0：男、1：女）',
  `age` int(11) NULL DEFAULT NULL COMMENT '年龄',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '联系方式',
  `sfzh` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '身份证号',
  `birthdate` datetime(0) NULL DEFAULT NULL COMMENT '出生日期',
  `nation` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '民族',
  `sfzaddress` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '身份证地址',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '现地址',
  `workunit` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '工作单位',
  `classid` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级id',
  `classname` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '状态（0：启用、1：停用)',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '任职时间',
  `roleid` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '角色id',
  `leavedate` datetime(0) NULL DEFAULT NULL COMMENT '离职时间',
  `majorid` varchar(500) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '专业id',
  `majorname` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '专业名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (24, '0001', '朱未月', '123456', '女', NULL, '19999999999', '342623199102101928', '1991-02-10 00:00:00', '汉', '安徽省马鞍山市雨山区鹊桥三村４栋１１０号', NULL, '地方都是广告', NULL, NULL, '0', '2019-03-13 14:04:08', '23,24', NULL, '1', '葫芦丝');
INSERT INTO `teacher` VALUES (27, '0002', '陈世璠', '123456', '男', NULL, '18326156160', '340827199211240032', '1992-11-24 00:00:00', '汉', '安徽省安庆市望江县华阳镇东洲路４０-３号', NULL, '北京华佑天睿教育', NULL, NULL, '0', '2019-03-15 09:28:47', '24', NULL, '', '请选择');
INSERT INTO `teacher` VALUES (28, '0003', '贝肯鲍尔', '123456', '男', NULL, '13812348228', '340827198807130571', '1988-07-13 00:00:00', '汉', '柏林', NULL, '北京华佑天睿教育', NULL, NULL, '0', '2019-03-15 09:52:58', '24', NULL, '', '请选择');
INSERT INTO `teacher` VALUES (33, '0004', '鲁梅尼格', '123456', '男', NULL, '18326156179', '340827198811240032', '1992-11-24 00:00:00', '汉', '安徽省安庆市望江县华阳镇东洲路４０-３号', NULL, '北京华佑天睿教育', NULL, NULL, '0', '2019-03-15 13:46:27', '24', NULL, '', '请选择');

-- ----------------------------
-- Table structure for teachmanager
-- ----------------------------
DROP TABLE IF EXISTS `teachmanager`;
CREATE TABLE `teachmanager`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tid` int(11) NULL DEFAULT NULL COMMENT '教师id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教师名称',
  `classid` int(11) NULL DEFAULT NULL COMMENT '班级id',
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '分类（0：请假、1：停课、2：倒课）',
  `starttime` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `endtime` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `leavedate` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `bkkssj` datetime(0) NULL DEFAULT NULL COMMENT '补课开始时间',
  `bkjssj` datetime(0) NULL DEFAULT NULL COMMENT '补课结束时间',
  `roomid` int(11) NULL DEFAULT NULL COMMENT '教室id',
  `roomname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教室名称',
  `auditorid` int(11) NULL DEFAULT NULL COMMENT '审核人id',
  `auditorname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '审核人姓名',
  `issure` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '审核意见',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `weeks` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '周次',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for term
-- ----------------------------
DROP TABLE IF EXISTS `term`;
CREATE TABLE `term`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `term` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学期名称',
  `starttime` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `endtime` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `isenable` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '是否可用\n（0：本学期、1：学期报名激活状态 2：未激活状态3：过期状态）',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `userid` int(11) NULL DEFAULT NULL COMMENT '创建人id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of term
-- ----------------------------
INSERT INTO `term` VALUES (11, '2019春季', '2019-02-14 00:00:00', '2019-06-19 00:00:00', '0', '2019-03-12 14:22:56', 21, '老师1');
INSERT INTO `term` VALUES (12, '2019秋季', '2019-09-01 00:00:00', '2019-01-24 00:00:00', '3', '2019-03-12 14:25:59', 21, '老师1');
INSERT INTO `term` VALUES (13, '2019 年夏季', '2020-03-01 00:00:00', '2020-07-09 00:00:00', '2', '2019-03-13 13:47:18', 21, '老师1');
INSERT INTO `term` VALUES (14, '贝肯鲍尔', '2024-12-10 00:00:00', '2024-12-14 00:00:00', '2', '2019-03-15 13:10:09', 28, '贝肯鲍尔');

-- ----------------------------
-- Table structure for worknotes
-- ----------------------------
DROP TABLE IF EXISTS `worknotes`;
CREATE TABLE `worknotes`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tid` int(11) NULL DEFAULT NULL COMMENT '教师id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教师姓名',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '内容',
  `recordtime` datetime(0) NULL DEFAULT NULL COMMENT '记录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for workplan
-- ----------------------------
DROP TABLE IF EXISTS `workplan`;
CREATE TABLE `workplan`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tid` int(11) NULL DEFAULT NULL COMMENT '教师id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教师姓名',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '内容',
  `recordtime` datetime(0) NULL DEFAULT NULL COMMENT '记录时间',
  `classid` int(11) NULL DEFAULT NULL COMMENT '班级id',
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for worksummary
-- ----------------------------
DROP TABLE IF EXISTS `worksummary`;
CREATE TABLE `worksummary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tid` int(11) NULL DEFAULT NULL COMMENT '教师id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教师名称',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL COMMENT '内容',
  `recordtime` datetime(0) NULL DEFAULT NULL COMMENT '记录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
