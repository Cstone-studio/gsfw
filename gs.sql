/*
 Navicat MySQL Data Transfer

 Source Server         : 本机开发环境
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : gs

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 07/04/2022 16:43:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hospital
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
  PRIMARY KEY (`id`),
  KEY `FK6t8w2b6ev7lgub3tygdgshv3j` (`user_id`),
  CONSTRAINT `FK6t8w2b6ev7lgub3tygdgshv3j` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hospital
-- ----------------------------
BEGIN;
INSERT INTO `hospital` (`id`, `user_id`, `name`, `create_time`, `create_user`, `deleted`, `update_time`, `update_user`) VALUES (1, 1, '上海某医院', NULL, NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `email`, `password`, `user_name`, `mobile`, `deleted`, `token`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES (1, '0712', 'e10adc3949ba59abbe56e057f20f883e', 'admin', '15909812111', b'0', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY0NzQ0MjI4MiwiaWF0IjoxNjQ3NDI0MjgyfQ.3ll5QxscugrfMMY9zdPchDNpenVkE6h-hTK7YWbFWiBtWo2XVaZCOt4vfyN9sE15r422opYyV_f6bnS6pvGuiA', '2021-12-22 11:42:33', NULL, '2022-03-16 17:51:23', NULL);
INSERT INTO `user` (`id`, `email`, `password`, `user_name`, `mobile`, `deleted`, `token`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES (21, 'liuchengd', '123456', 'admin2', '123123123', b'0', NULL, '2022-03-09 11:20:07', NULL, '2022-03-09 11:20:07', NULL);
INSERT INTO `user` (`id`, `email`, `password`, `user_name`, `mobile`, `deleted`, `token`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES (23, '111', '123456', 'admin3', '111', b'0', NULL, '2022-03-09 11:43:03', NULL, '2022-03-09 11:43:03', NULL);
INSERT INTO `user` (`id`, `email`, `password`, `user_name`, `mobile`, `deleted`, `token`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES (24, 'string', '123456', 'admin4', '132', b'0', NULL, '2022-03-09 11:50:29', 'admin', '2022-03-09 11:50:29', 'admin');
INSERT INTO `user` (`id`, `email`, `password`, `user_name`, `mobile`, `deleted`, `token`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES (25, 'string', '123456', 'admin5', '132', b'0', NULL, '2022-03-09 11:51:26', 'admin', '2022-03-09 11:51:26', 'admin');
INSERT INTO `user` (`id`, `email`, `password`, `user_name`, `mobile`, `deleted`, `token`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES (26, 'string', 'string222', 'string', 'string', b'0', NULL, '2022-03-16 17:51:41', 'admin', '2022-03-16 17:51:41', 'admin');
INSERT INTO `user` (`id`, `email`, `password`, `user_name`, `mobile`, `deleted`, `token`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES (27, 'string', 'string', 'string', 'string', b'0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `email`, `password`, `user_name`, `mobile`, `deleted`, `token`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES (28, '', '', 'string', '', b'0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `email`, `password`, `user_name`, `mobile`, `deleted`, `token`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES (29, '', '', 'string', '', b'0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `email`, `password`, `user_name`, `mobile`, `deleted`, `token`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES (30, '', '', 'string', '', b'0', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` (`id`, `email`, `password`, `user_name`, `mobile`, `deleted`, `token`, `create_time`, `create_user`, `update_time`, `update_user`) VALUES (31, '', '', 'string', '', b'0', NULL, NULL, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
