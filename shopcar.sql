/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 50562
Source Host           : localhost:3306
Source Database       : shopcar

Target Server Type    : MYSQL
Target Server Version : 50562
File Encoding         : 65001

Date: 2020-07-16 15:12:53
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `car`
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `singleNumber` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '',
  `applicationSector` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `applicationData` date DEFAULT NULL,
  `applicationName` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `productName` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `specification` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `quantity` int(32) DEFAULT NULL,
  `address` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `needsTime` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `total` int(32) DEFAULT NULL,
  PRIMARY KEY (`singleNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO car VALUES ('056678e7-46e4-40c5-a97f-166c96d85c2f', '研发部', '2020-07-16', '3171911111', '3171911111', '123456', '100', '地址', '几天', '1');
INSERT INTO car VALUES ('1c76d480-18af-486d-8f6b-b7cf6446dd4c', '研发部', '2020-07-16', '3171911111', '3171911111', '123456', '100', '地址', '几天', '1');
INSERT INTO car VALUES ('28ef436c-5684-41a8-980f-32ceda2e3cf6', '研发部', '2020-07-16', '3171911111', '3171911111', '123456', '100', '地址', '几天', '1');
INSERT INTO car VALUES ('43a1ac99-9bcb-4993-94d4-b3274d6852fb', '研发部', '2020-07-16', '3171911111', '3171911111', '123456', '100', '地址', '几天', '1');
INSERT INTO car VALUES ('4b3b4e26-7144-4a13-988d-2a2677b4e3bb', '研发部', '2020-07-16', '3171911111', '3171911111', '123456', '100', '地址', '几天', '1');
INSERT INTO car VALUES ('5b3d8710-4de2-4d91-b98c-6cc43ab23f79', '研发部', '2020-07-16', '3171911111', '3171911111', '123456', '100', '地址', '几天', '1');
INSERT INTO car VALUES ('9dd76d87-b46d-40f2-a9db-189c2a6ecd8c', '研发部', '2020-07-16', '3171911111', '3171911111', '123456', '100', '地址', '几天', '1');
INSERT INTO car VALUES ('f6e1bebb-faba-4c08-abc4-9d613addea12', '研发部', '2020-07-16', '3171911111', '3171911111', '123456', '100', '地址', '几天', '1');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `name` varchar(40) NOT NULL DEFAULT '',
  `password` varchar(40) NOT NULL,
  `Sector` varchar(40) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO user VALUES ('3171911111', '123456', '研发部');
INSERT INTO user VALUES ('3171911130', '123456', '研发部');
