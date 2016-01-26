/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50615
 Source Host           : localhost
 Source Database       : db_health

 Target Server Type    : MySQL
 Target Server Version : 50615
 File Encoding         : utf-8

 Date: 01/26/2016 17:51:36 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tb_aboutus`
-- ----------------------------
DROP TABLE IF EXISTS `tb_aboutus`;
CREATE TABLE `tb_aboutus` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_bootpage`
-- ----------------------------
DROP TABLE IF EXISTS `tb_bootpage`;
CREATE TABLE `tb_bootpage` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `boot_page` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_firstpage`
-- ----------------------------
DROP TABLE IF EXISTS `tb_firstpage`;
CREATE TABLE `tb_firstpage` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `introduction` varchar(100) DEFAULT NULL,
  `img_info` varchar(300) DEFAULT NULL,
  `source` varchar(50) DEFAULT NULL,
  `creat_time` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `video_url` varchar(300) DEFAULT NULL,
  `img_detail` varchar(300) DEFAULT NULL,
  `img_video` varchar(300) DEFAULT NULL,
  `img_top` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_knowledge`
-- ----------------------------
DROP TABLE IF EXISTS `tb_knowledge`;
CREATE TABLE `tb_knowledge` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `introduction` varchar(100) DEFAULT NULL,
  `img_info` varchar(300) DEFAULT NULL,
  `source` varchar(50) DEFAULT NULL,
  `creat_time` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `video_url` varchar(300) DEFAULT NULL,
  `img_detail` varchar(300) DEFAULT NULL,
  `img_video` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_message`
-- ----------------------------
DROP TABLE IF EXISTS `tb_message`;
CREATE TABLE `tb_message` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `creat_time` varchar(50) DEFAULT NULL,
  `end_time` varchar(50) DEFAULT NULL,
  `phone_id` varchar(100) DEFAULT NULL,
  `correct` int(10) DEFAULT NULL,
  `total` int(10) DEFAULT NULL,
  `time_space` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_onlinefirst`
-- ----------------------------
DROP TABLE IF EXISTS `tb_onlinefirst`;
CREATE TABLE `tb_onlinefirst` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `img_info` varchar(300) DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `introduction` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_onlinetest`
-- ----------------------------
DROP TABLE IF EXISTS `tb_onlinetest`;
CREATE TABLE `tb_onlinetest` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `topic` varchar(200) DEFAULT NULL,
  `testa` varchar(100) DEFAULT NULL,
  `testb` varchar(100) DEFAULT NULL,
  `testc` varchar(100) DEFAULT NULL,
  `testd` varchar(100) DEFAULT NULL,
  `correct_answer` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_recommend`
-- ----------------------------
DROP TABLE IF EXISTS `tb_recommend`;
CREATE TABLE `tb_recommend` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `introduction` varchar(100) DEFAULT NULL,
  `img_info` varchar(300) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `img_detail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_supports`
-- ----------------------------
DROP TABLE IF EXISTS `tb_supports`;
CREATE TABLE `tb_supports` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `content` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_user_info`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_info`;
CREATE TABLE `tb_user_info` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `account` varchar(100) DEFAULT NULL,
  `pass_word` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_version`
-- ----------------------------
DROP TABLE IF EXISTS `tb_version`;
CREATE TABLE `tb_version` (
  `id` bigint(32) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) DEFAULT NULL,
  `url_num` varchar(1000) DEFAULT NULL,
  `urlpath` varchar(200) DEFAULT NULL,
  `type` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
