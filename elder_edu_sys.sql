/*
 Navicat Premium Data Transfer

 Source Server         : 111.231.115.198
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 111.231.115.198:3306
 Source Schema         : elder_edu_sys

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 14/01/2021 15:19:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activities
-- ----------------------------
DROP TABLE IF EXISTS `activities`;
CREATE TABLE `activities`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '内容',
  `crttime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '填写时间',
  `userid` int(0) NULL DEFAULT NULL COMMENT '填写人id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '填写人姓名',
  `times` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for activityrecord
-- ----------------------------
DROP TABLE IF EXISTS `activityrecord`;
CREATE TABLE `activityrecord`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tid` int(0) NULL DEFAULT NULL COMMENT '教师id',
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
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '内容',
  `crttime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '填写时间',
  `userid` int(0) NULL DEFAULT NULL COMMENT '填写人id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '填写人姓名',
  `times` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for awardrecord
-- ----------------------------
DROP TABLE IF EXISTS `awardrecord`;
CREATE TABLE `awardrecord`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tid` int(0) NULL DEFAULT NULL COMMENT '教师id',
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
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `number` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '类别编号',
  `category` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '类别名称',
  `remarks` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '备注',
  `createid` int(0) NOT NULL COMMENT '创建人id',
  `createname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes`  (
  `id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `number` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级序号',
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  `plansize` int(0) NULL DEFAULT NULL COMMENT '计划人数',
  `actualsize` int(0) NULL DEFAULT 0 COMMENT '实际人数',
  `majorid` int(0) NULL DEFAULT NULL COMMENT '专业id',
  `majorname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '专业名称',
  `headmaster` int(0) NULL DEFAULT NULL COMMENT '班主任id',
  `headmastername` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班长任姓名',
  `monitor` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班长姓名',
  `studyer` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学习委员',
  `safer` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '安全委员',
  `tid` int(0) NULL DEFAULT NULL COMMENT '授课教师id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '授课教师姓名',
  `cateid` int(0) NULL DEFAULT NULL COMMENT '类别id',
  `catename` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '类别名称',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for classrecord
-- ----------------------------
DROP TABLE IF EXISTS `classrecord`;
CREATE TABLE `classrecord`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `classid` int(0) NULL DEFAULT NULL COMMENT '班级id',
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  `roomid` int(0) NULL DEFAULT NULL COMMENT '教室id',
  `roomname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教室名称',
  `starttime` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `endtime` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `tid` int(0) NULL DEFAULT NULL COMMENT '使用人id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '使用人姓名',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `coursename` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '课程名称',
  `courseid` int(0) NULL DEFAULT NULL COMMENT '课程id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for classroom
-- ----------------------------
DROP TABLE IF EXISTS `classroom`;
CREATE TABLE `classroom`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
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
-- Table structure for classsuggest
-- ----------------------------
DROP TABLE IF EXISTS `classsuggest`;
CREATE TABLE `classsuggest`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sort` int(0) NULL DEFAULT NULL COMMENT '序号',
  `tid` int(0) NULL DEFAULT NULL COMMENT '教师id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教师姓名',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '内容',
  `suggesttime` datetime(0) NULL DEFAULT NULL COMMENT '建议时间',
  `reply` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '回复情况',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `classid` int(0) NULL DEFAULT NULL COMMENT '班级id',
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for classtype
-- ----------------------------
DROP TABLE IF EXISTS `classtype`;
CREATE TABLE `classtype`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `catename` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '类别名称',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for courses
-- ----------------------------
DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `week` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '固定周几',
  `date` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '固定时间段',
  `roomid` int(0) NULL DEFAULT NULL COMMENT '固定教室id',
  `classroom` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '固定教室名称',
  `jsid` int(0) NULL DEFAULT NULL COMMENT '固定教师id',
  `teacher` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '固定教师名称',
  `classesid` int(0) NULL DEFAULT NULL COMMENT '固定班级id',
  `classes` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '固定班级名称',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `tid` int(0) NULL DEFAULT NULL COMMENT '班主任id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班主任姓名',
  `plan_id` int(0) NOT NULL COMMENT '排课id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for coursetime
-- ----------------------------
DROP TABLE IF EXISTS `coursetime`;
CREATE TABLE `coursetime`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `coursename` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '课时名称',
  `time` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '时间段',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '时间段代表int',
  `timetype` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '0为上午1为下午\r\n',
  `crttime` datetime(0) NULL DEFAULT NULL,
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '状态(0:启用、1:停用）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for identitys
-- ----------------------------
DROP TABLE IF EXISTS `identitys`;
CREATE TABLE `identitys`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `duties` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '职务',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for inforremind
-- ----------------------------
DROP TABLE IF EXISTS `inforremind`;
CREATE TABLE `inforremind`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '消息分类（0：教师请假、1：上课提醒）',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '消息内容',
  `createid` int(0) NULL DEFAULT NULL COMMENT '创建人id',
  `createname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `calltid` int(0) NULL DEFAULT NULL COMMENT '提醒人id',
  `calltname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '提醒人姓名',
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '状态（0：已处理、1：未处理）',
  `sourceid` int(0) NULL DEFAULT NULL COMMENT '来源信息id',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for meetrecord
-- ----------------------------
DROP TABLE IF EXISTS `meetrecord`;
CREATE TABLE `meetrecord`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `weeksort` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '周次',
  `tid` int(0) NULL DEFAULT NULL COMMENT '教师id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教师姓名',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '主题',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '记录日期',
  `classid` int(0) NULL DEFAULT NULL COMMENT '班级id',
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
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '内容',
  `userid` int(0) NULL DEFAULT NULL COMMENT '填写人id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '填写人姓名',
  `crttime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '填写时间',
  `times` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for planrecord
-- ----------------------------
DROP TABLE IF EXISTS `planrecord`;
CREATE TABLE `planrecord`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `week` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '周几',
  `courseid` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '课程时间id',
  `classroom` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教室',
  `teacher` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '授课教师',
  `datetype` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '类型（0为上午1为下午）',
  `tid` int(0) NULL DEFAULT NULL COMMENT '排课人id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '排课人姓名',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `classid` int(0) NULL DEFAULT NULL COMMENT '班级id',
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for power
-- ----------------------------
DROP TABLE IF EXISTS `power`;
CREATE TABLE `power`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
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
-- Table structure for profession
-- ----------------------------
DROP TABLE IF EXISTS `profession`;
CREATE TABLE `profession`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `majornumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '专业序号',
  `majorname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '专业名称',
  `createid` int(0) NOT NULL COMMENT '创建人id',
  `createname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  `cateid` int(0) NULL DEFAULT NULL COMMENT '类别id',
  `catename` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '类别名称',
  `numbers` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '类别序号',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL DEFAULT '0' COMMENT '类别\r\n（0：数字化资源建设；1：教材建设）',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '内容',
  `crttime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '填写时间',
  `userid` int(0) NULL DEFAULT NULL COMMENT '填写人id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '填写人姓名',
  `times` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
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
-- Table structure for roster
-- ----------------------------
DROP TABLE IF EXISTS `roster`;
CREATE TABLE `roster`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `birthdate` datetime(0) NULL DEFAULT NULL COMMENT '出生日期',
  `stunumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学号',
  `classid` int(0) NULL DEFAULT NULL COMMENT '班级id',
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  `stuid` int(0) NULL DEFAULT NULL COMMENT '学生id',
  `stuname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `isleader` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '职位\n（学生)',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '状态（0:退班、1：转班、2：休学、3：开除）',
  `termid` int(0) NULL DEFAULT NULL COMMENT '学期id',
  `termname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学期名称',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '联系方式',
  `famPhone` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '家庭联系方式',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 171 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci COMMENT = '花名册信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for scientific
-- ----------------------------
DROP TABLE IF EXISTS `scientific`;
CREATE TABLE `scientific`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '内容',
  `crttime` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '填写时间',
  `userid` int(0) NULL DEFAULT NULL COMMENT '填写人id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '填写人姓名',
  `times` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for specialmanager
-- ----------------------------
DROP TABLE IF EXISTS `specialmanager`;
CREATE TABLE `specialmanager`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sid` int(0) NULL DEFAULT NULL COMMENT '学生id',
  `sname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学生性别（0：男、1：女）',
  `filtime` datetime(0) NULL DEFAULT NULL COMMENT '建档时间',
  `types` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '建档原因（0:退班、1：转班、2：休学、3：开除）',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '主要情况',
  `times` datetime(0) NULL DEFAULT NULL COMMENT '时间',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '地点',
  `tid` int(0) NULL DEFAULT NULL COMMENT '教师id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教师姓名',
  `question` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '针对问题',
  `options` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '辅导情况',
  `classid` int(0) NULL DEFAULT NULL COMMENT '班级id',
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '联系方式',
  `stunumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学号',
  `idcard` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL,
  `termid` int(0) NULL DEFAULT NULL COMMENT '学期id',
  `termname` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学期名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci COMMENT = '日志管理-特殊学员档案表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `stunumber` varchar(100) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '学员编号（多个，逗号隔开）',
  `sfzh` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '身份证号',
  `stuname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '学生姓名',
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '性别（0：男、1：女）',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
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
-- Table structure for studententer
-- ----------------------------
DROP TABLE IF EXISTS `studententer`;
CREATE TABLE `studententer`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `stuid` int(0) NOT NULL COMMENT '学生id',
  `stuname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `tid` int(0) NULL DEFAULT NULL COMMENT '经手人（教师id）',
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
  `term_id` int(0) NOT NULL COMMENT '学期id',
  `term_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学期名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 96 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for studentrecord
-- ----------------------------
DROP TABLE IF EXISTS `studentrecord`;
CREATE TABLE `studentrecord`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `birthdate` datetime(0) NULL DEFAULT NULL COMMENT '出生日期',
  `stunumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学号',
  `classid` int(0) NULL DEFAULT NULL COMMENT '班级id',
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  `stuid` int(0) NULL DEFAULT NULL COMMENT '学生id',
  `stuname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
  `isleader` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '职位\n（学生)',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `termid` int(0) NULL DEFAULT NULL COMMENT '学期id',
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
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `tid` int(0) NULL DEFAULT NULL COMMENT '填写人id',
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
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `sid` int(0) NULL DEFAULT NULL COMMENT '学生id',
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
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tnumber` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NOT NULL COMMENT '教师工号',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教师姓名',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '密码',
  `sex` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '性别（0：男、1：女）',
  `age` int(0) NULL DEFAULT NULL COMMENT '年龄',
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
-- Table structure for teachmanager
-- ----------------------------
DROP TABLE IF EXISTS `teachmanager`;
CREATE TABLE `teachmanager`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tid` int(0) NULL DEFAULT NULL COMMENT '教师id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教师名称',
  `classid` int(0) NULL DEFAULT NULL COMMENT '班级id',
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  `type` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '分类（0：请假、1：停课、2：倒课）',
  `starttime` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `endtime` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `leavedate` datetime(0) NULL DEFAULT NULL COMMENT '日期',
  `bkkssj` datetime(0) NULL DEFAULT NULL COMMENT '补课开始时间',
  `bkjssj` datetime(0) NULL DEFAULT NULL COMMENT '补课结束时间',
  `roomid` int(0) NULL DEFAULT NULL COMMENT '教室id',
  `roomname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教室名称',
  `auditorid` int(0) NULL DEFAULT NULL COMMENT '审核人id',
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
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `term` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '学期名称',
  `starttime` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `endtime` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `isenable` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '是否可用\n（0：本学期、1：学期报名激活状态 2：未激活状态3：过期状态）',
  `crttime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `userid` int(0) NULL DEFAULT NULL COMMENT '创建人id',
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '创建人姓名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for worknotes
-- ----------------------------
DROP TABLE IF EXISTS `worknotes`;
CREATE TABLE `worknotes`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tid` int(0) NULL DEFAULT NULL COMMENT '教师id',
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
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tid` int(0) NULL DEFAULT NULL COMMENT '教师id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教师姓名',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '标题',
  `content` varchar(2000) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '内容',
  `recordtime` datetime(0) NULL DEFAULT NULL COMMENT '记录时间',
  `classid` int(0) NULL DEFAULT NULL COMMENT '班级id',
  `classname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '班级名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for worksummary
-- ----------------------------
DROP TABLE IF EXISTS `worksummary`;
CREATE TABLE `worksummary`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `tid` int(0) NULL DEFAULT NULL COMMENT '教师id',
  `tname` varchar(20) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '教师名称',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_croatian_ci NULL COMMENT '内容',
  `recordtime` datetime(0) NULL DEFAULT NULL COMMENT '记录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_croatian_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
