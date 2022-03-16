/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost
 Source Database       : gs

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : utf-8

 Date: 03/09/2022 14:31:54 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `hospital`
-- ----------------------------
DROP TABLE IF EXISTS `hospital`;
CREATE TABLE `hospital` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `hospital`
-- ----------------------------
BEGIN;
INSERT INTO `hospital` VALUES ('1', '1', '上海某医院', null, null, null, null, null);
COMMIT;

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `email` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '昵称',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话号',
  `deleted` bit(1) DEFAULT NULL COMMENT '删除标记：0-未删除，1-已删除',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登录token',
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `user`
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES ('1', '0712', 'e10adc3949ba59abbe56e057f20f883e', 'admin', '15909812111', b'0', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY0NjgxNTIwNSwiaWF0IjoxNjQ2Nzk3MjA1fQ.56VaEr-xXQ0LuyDQKmkeGR7fb9QfPJ1NUc-qg_MJ89gL7-sjVoKo2PmlLkBnGvV63ENXJXtsTvNepxijEAPqyQ', '2021-12-22 11:42:33', null, '2022-03-09 11:40:06', null), ('21', 'liuchengd', '123456', 'admin2', '123123123', b'0', null, '2022-03-09 11:20:07', null, '2022-03-09 11:20:07', null), ('23', '111', '123456', 'admin3', '111', b'0', null, '2022-03-09 11:43:03', null, '2022-03-09 11:43:03', null), ('24', 'string', '123456', 'admin4', '132', b'0', null, '2022-03-09 11:50:29', 'admin', '2022-03-09 11:50:29', 'admin'), ('25', 'string', '123456', 'admin5', '132', b'0', null, '2022-03-09 11:51:26', 'admin', '2022-03-09 11:51:26', 'admin');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
