/*
MySQL Data Transfer
Source Host: localhost
Source Database: bookorder
Target Host: localhost
Target Database: bookorder
Date: 2017/5/14 1:43:57
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `aid` int(11) NOT NULL auto_increment COMMENT '管理员编号',
  `adminid` varchar(20) NOT NULL default '' COMMENT '管理员账号',
  `pwd` varchar(50) NOT NULL COMMENT '管理员密码',
  `name` varchar(50) default NULL COMMENT '管里员名字',
  `rights` varchar(50) default NULL COMMENT '管理员权限(,分隔）',
  `telphone` varchar(50) default NULL COMMENT '电话号码',
  `email` varchar(50) default NULL COMMENT '邮箱',
  PRIMARY KEY  (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bookclass
-- ----------------------------
DROP TABLE IF EXISTS `bookclass`;
CREATE TABLE `bookclass` (
  `cid` int(11) NOT NULL auto_increment,
  `classname` varchar(20) default NULL COMMENT '图书类名',
  PRIMARY KEY  (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for buycar
-- ----------------------------
DROP TABLE IF EXISTS `buycar`;
CREATE TABLE `buycar` (
  `carid` int(11) NOT NULL auto_increment,
  `bookid` int(11) default NULL COMMENT '图书编号',
  `userid` int(11) default NULL COMMENT '用户编号',
  `buymount` int(11) default NULL COMMENT '购买数量',
  PRIMARY KEY  (`carid`),
  KEY `fk_car_uid` (`userid`),
  CONSTRAINT `fk_car_uid` FOREIGN KEY (`userid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for come
-- ----------------------------
DROP TABLE IF EXISTS `come`;
CREATE TABLE `come` (
  `comeid` int(11) NOT NULL auto_increment COMMENT '入库编号',
  `bookid` int(11) NOT NULL COMMENT '图书编号',
  `comenumber` int(11) default NULL COMMENT '进仓数量',
  `comedate` date default NULL,
  `suplyer` varchar(50) default NULL COMMENT '供应商',
  `suplyphone` varchar(15) default NULL COMMENT '供应商联系方式',
  `adminid` int(11) default NULL COMMENT '经办人（采购人员）ID',
  PRIMARY KEY  (`comeid`),
  KEY `fk_bid` (`bookid`),
  KEY `fk_aid` (`adminid`),
  CONSTRAINT `fk_aid` FOREIGN KEY (`adminid`) REFERENCES `admin` (`aid`),
  CONSTRAINT `fk_bid` FOREIGN KEY (`bookid`) REFERENCES `textbook` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for getbook
-- ----------------------------
DROP TABLE IF EXISTS `getbook`;
CREATE TABLE `getbook` (
  `gid` int(11) NOT NULL auto_increment,
  `oitemid` int(11) default NULL COMMENT '定单号',
  `bookid` int(11) NOT NULL COMMENT '领书图书编号',
  `userid` int(11) NOT NULL COMMENT 'int',
  `amount` int(11) default NULL COMMENT '领书数量',
  `location` varchar(50) default NULL COMMENT '领书地点',
  `date` date default NULL COMMENT '领取时间',
  PRIMARY KEY  (`gid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for lackbook
-- ----------------------------
DROP TABLE IF EXISTS `lackbook`;
CREATE TABLE `lackbook` (
  `lackid` int(11) NOT NULL auto_increment COMMENT '缺书教材编号',
  `stockid` int(11) default NULL COMMENT '来源于库存编号',
  `amount` int(11) default NULL COMMENT '缺书数量',
  `itemid` int(11) NOT NULL COMMENT '缺书来源订单',
  `uid` int(11) default NULL COMMENT '书缺用户',
  `lackDate` datetime default NULL COMMENT '缺书日期',
  PRIMARY KEY  (`lackid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem` (
  `orderItemid` int(32) NOT NULL auto_increment COMMENT '二次审核人员',
  `itemid` char(32) default NULL COMMENT '订单项编号',
  `oid` char(32) NOT NULL COMMENT '订单所属编号',
  `bookid` int(11) NOT NULL COMMENT '图书编号',
  `buyamount` int(11) default '0' COMMENT '购买数量',
  `subtotal` decimal(10,0) default '0' COMMENT '小计',
  `islack` smallint(1) unsigned zerofill default '0' COMMENT '是否标记为缺书（0未登记，1登记）',
  `isregisbuy` smallint(1) unsigned default '0' COMMENT '是否已登记购书（0为未登记，1为登记)',
  `regisdate` date default NULL COMMENT '登记购书时间',
  `state` smallint(6) default '1' COMMENT '订单状态订单状态0未付款，1已付款但未发货，2已发货但未领书,3领书成功）',
  `outdate` date default NULL COMMENT '发货日期',
  `fckstate` smallint(6) default '0' COMMENT ' 一审状态，1审核通过，2审核未通过，3审核通过',
  `fckaid` int(11) default NULL COMMENT '初审审核人员',
  `fckdate` date default NULL COMMENT '初审审核日期',
  `sckstate` smallint(6) default '0' COMMENT '二审状态，1审核通过，2审核未通过，3审核通过',
  `sckaid` int(11) default NULL COMMENT '二审人员',
  `sckdate` date default NULL COMMENT '二次审核日期',
  `enddate` date default NULL COMMENT '交易结束时间（即用户确认领书)',
  `paydate` date default NULL COMMENT ' 付款日期',
  `isdelete` smallint(6) default '0' COMMENT '删除订单',
  PRIMARY KEY  (`orderItemid`),
  KEY `oid` (`oid`),
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`oid`) REFERENCES `orders` (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `orderid` char(32) NOT NULL COMMENT '订单编号',
  `userid` int(11) NOT NULL COMMENT '购买人id',
  `ordertime` datetime default NULL COMMENT '订单生成日期',
  `total` decimal(10,0) default NULL,
  `isdelete` smallint(6) default '0' COMMENT '用户是否删除该订单（0为未删，1为已删）',
  PRIMARY KEY  (`orderid`),
  KEY `fk_uid` (`userid`),
  KEY `orderid` (`orderid`),
  CONSTRAINT `fk_uid` FOREIGN KEY (`userid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for outbook
-- ----------------------------
DROP TABLE IF EXISTS `outbook`;
CREATE TABLE `outbook` (
  `outid` int(11) unsigned NOT NULL auto_increment COMMENT '出库编号',
  `itemid` int(11) default '0' COMMENT '库出订单号',
  `sid` int(11) NOT NULL COMMENT '出库编号',
  `outamount` int(11) default NULL COMMENT '出库数量',
  `userid` int(11) default NULL COMMENT '交易人',
  `outdate` date default NULL COMMENT '出库时间',
  `adminid` int(11) default NULL COMMENT '经办人',
  PRIMARY KEY  (`outid`),
  KEY `sid` (`sid`),
  CONSTRAINT `outbook_ibfk_1` FOREIGN KEY (`sid`) REFERENCES `stock` (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `sid` int(11) NOT NULL auto_increment COMMENT '库存编号',
  `bookid` int(11) NOT NULL COMMENT '图书编号',
  `stocknumer` int(11) default NULL COMMENT '库存数量',
  `islack` smallint(2) default '0' COMMENT '是否登记缺书（默认为0,未登记缺书）',
  PRIMARY KEY  (`sid`),
  KEY `fk_stock_bid` (`bookid`),
  CONSTRAINT `fk_stock_bid` FOREIGN KEY (`bookid`) REFERENCES `textbook` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for textbook
-- ----------------------------
DROP TABLE IF EXISTS `textbook`;
CREATE TABLE `textbook` (
  `id` int(11) NOT NULL auto_increment,
  `isbn` varchar(20) NOT NULL COMMENT '图书编号',
  `bookname` varchar(50) default NULL COMMENT '教材名称',
  `cid` int(11) default NULL COMMENT '图书分类di',
  `author` varchar(50) default '' COMMENT '作者',
  `publiser` varchar(50) default NULL COMMENT '出版社',
  `publishtime` date default NULL COMMENT '出版时间',
  `price` float(50,0) default NULL COMMENT '定价',
  `comeprice` float default '0' COMMENT '图书进价',
  `saleprice` float default '0' COMMENT '卖价',
  `details` text COMMENT '教材详细描述',
  `bookpicture` varchar(200) default NULL COMMENT '图书图片',
  PRIMARY KEY  (`id`),
  KEY `fk_cid` (`cid`),
  CONSTRAINT `fk_cid` FOREIGN KEY (`cid`) REFERENCES `bookclass` (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL auto_increment,
  `userid` varchar(15) NOT NULL COMMENT '学号（工号)',
  `userpwd` varchar(50) NOT NULL COMMENT '用户密码',
  `username` varchar(50) NOT NULL COMMENT '用户名称',
  `idennty` int(11) NOT NULL default '0' COMMENT '身份（0学生，1教师）',
  `email` varchar(50) default NULL COMMENT '邮箱',
  `telphone` varchar(20) default NULL COMMENT '电话号码',
  `school` varchar(50) default '' COMMENT '所在学校',
  `academy` varchar(50) default '' COMMENT '学院',
  `major` varchar(50) default '' COMMENT '专业',
  `grade` varchar(10) default NULL COMMENT '年级',
  `isstate` tinyint(4) default NULL COMMENT '用户是否激活(0 未激活，1为激活)',
  `code` varchar(255) default NULL COMMENT '激活码（验证码）',
  PRIMARY KEY  (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for waitbuy
-- ----------------------------
DROP TABLE IF EXISTS `waitbuy`;
CREATE TABLE `waitbuy` (
  `wid` int(11) NOT NULL auto_increment COMMENT '编号',
  `isbn` varchar(20) default NULL COMMENT '待购图书ISBn',
  `bookname` varchar(50) default NULL COMMENT '待购书籍名称',
  `plantmount` int(11) default NULL COMMENT '计划预定数量',
  `author` varchar(50) default NULL COMMENT '作者',
  `publisher` varchar(50) default NULL COMMENT '出版商',
  `publishtime` datetime default NULL COMMENT '出版时间',
  PRIMARY KEY  (`wid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'sys', 'sys', 'system', '1,1,1,1,1', '18883311011', null);
INSERT INTO `admin` VALUES ('2', 'admin1', '1234567', 'a1', '1,0,0,0,0', '18883311100', null);
INSERT INTO `admin` VALUES ('3', 'admin2', '1234567', 'a2', '0,1,0,0,0', '18883311101', null);
INSERT INTO `admin` VALUES ('4', 'admin3', '1234567', 'a3', '0,0,1,0,0', '18883311102', null);
INSERT INTO `admin` VALUES ('6', 'admin5', '1234567', 'a4', '0,0,0,1,0', '18883311103', null);
INSERT INTO `admin` VALUES ('7', 'admin6', '1234567', 'a5', '0,0,0,0,1', '18883311104', null);
INSERT INTO `admin` VALUES ('8', 'admin7', '1234567', 'a6', '0,0,0,0,0', '18883311105', '');
INSERT INTO `bookclass` VALUES ('1', '会计');
INSERT INTO `bookclass` VALUES ('2', '机械仪表工程');
INSERT INTO `bookclass` VALUES ('3', '语言与编程');
INSERT INTO `bookclass` VALUES ('4', '数据库');
INSERT INTO `bookclass` VALUES ('5', '人力资源');
INSERT INTO `bookclass` VALUES ('7', '网页制作');
INSERT INTO `bookclass` VALUES ('8', '网络通信');
INSERT INTO `bookclass` VALUES ('9', '市场营销');
INSERT INTO `bookclass` VALUES ('10', '财务管理');
INSERT INTO `bookclass` VALUES ('11', '经济学理论');
INSERT INTO `bookclass` VALUES ('12', '电工电子');
INSERT INTO `bookclass` VALUES ('13', '文学');
INSERT INTO `bookclass` VALUES ('14', '英语');
INSERT INTO `bookclass` VALUES ('15', '生物');
INSERT INTO `bookclass` VALUES ('16', '医学');
INSERT INTO `bookclass` VALUES ('17', '数学');
INSERT INTO `bookclass` VALUES ('18', '计算机');
INSERT INTO `bookclass` VALUES ('19', '农学');
INSERT INTO `bookclass` VALUES ('20', '理学');
INSERT INTO `bookclass` VALUES ('21', '工学');
INSERT INTO `bookclass` VALUES ('22', '土木工程类');
INSERT INTO `buycar` VALUES ('7', '1', '12', '1');
INSERT INTO `buycar` VALUES ('9', '2', '12', '1');
INSERT INTO `buycar` VALUES ('10', '6', '12', '1');
INSERT INTO `come` VALUES ('1', '1', '500', '2017-04-09', 'sf', '1209876', '2');
INSERT INTO `come` VALUES ('2', '2', '500', '2017-04-09', 'sf', '1209876', '2');
INSERT INTO `come` VALUES ('3', '3', '500', '2017-04-09', 'sf', '1209876', '2');
INSERT INTO `come` VALUES ('4', '4', '500', '2017-04-09', 'sf', '1209876', '2');
INSERT INTO `come` VALUES ('5', '5', '500', '2017-04-09', 'sf', '1209876', '2');
INSERT INTO `come` VALUES ('6', '6', '500', '2017-04-09', 'sf', '1209876', '2');
INSERT INTO `come` VALUES ('7', '7', '500', '2017-04-09', 'sf', '1209876', '2');
INSERT INTO `come` VALUES ('8', '8', '500', '2017-04-09', 'sf', '1209876', '2');
INSERT INTO `come` VALUES ('9', '13', '500', '2017-04-09', 'sf', '1209876', '2');
INSERT INTO `come` VALUES ('11', '14', '12', '2017-05-17', '', '', '1');
INSERT INTO `come` VALUES ('12', '2', '200', '2017-05-22', '', '', '1');
INSERT INTO `come` VALUES ('14', '15', '233', '2017-05-03', '', '', '1');
INSERT INTO `come` VALUES ('15', '2', '0', null, '', '', '1');
INSERT INTO `come` VALUES ('16', '2', '0', null, '', '', '1');
INSERT INTO `come` VALUES ('17', '3', '100', '2017-05-08', 'hu', '', '1');
INSERT INTO `getbook` VALUES ('5', '27', '6', '6', '3', '重庆工商大学', '2017-05-14');
INSERT INTO `getbook` VALUES ('6', '27', '6', '6', '3', '重庆工商大学', '2017-05-14');
INSERT INTO `lackbook` VALUES ('3', '4', '200', '6', null, '2017-05-01 02:04:16');
INSERT INTO `lackbook` VALUES ('4', '8', '100', '5', '0', '2017-05-09 15:05:08');
INSERT INTO `orderitem` VALUES ('5', 'DA006FBC733A464A992CAC8C3D919E10', 'DA006FBC733A464A992CAC8C3D919E18', '5', '100', '2900', '1', '0', null, '1', null, '2', '1', '2017-05-07', '2', '1', '2017-05-01', null, null, null);
INSERT INTO `orderitem` VALUES ('6', 'DA006FBC733A464A992CAC8C3D919E16', 'E4248AFE9DF64391B3D3B9B3E374E9FA', '4', '1', '27', '1', '0', null, '1', null, '2', '1', '2017-05-07', '2', '1', '2017-05-07', null, null, null);
INSERT INTO `orderitem` VALUES ('7', 'DA006FBC733A464A992CAC8C3D919E15', 'E4248AFE9DF64391B3D3B9B3E374E9FA', '10', '1', '34', '0', '0', null, '1', null, '2', '1', '2017-05-07', '2', '1', '2017-05-01', null, null, null);
INSERT INTO `orderitem` VALUES ('10', 'DA006FBC733A464A992CAC8C3D919E11', '2CBD2A7B3C1F4DD7B11486401FDF0AD1', '12', '1', '23', '0', '0', null, '3', null, '2', '1', '2017-05-02', '1', '1', '2017-05-13', null, null, null);
INSERT INTO `orderitem` VALUES ('11', 'DA006FBC733A464A992CAC8C3D919E12', 'DCE9A17A5DAB4AA4875A011ECAB9BBEB', '6', '4', '100', '0', '0', null, '2', null, '2', '1', '2017-05-11', '2', null, null, null, null, '0');
INSERT INTO `orderitem` VALUES ('12', 'DA006FBC733A464A992CAC8C3D919E13', 'D13043B1F5F0429C88A93286B48DCFF0', '6', '4', '100', '0', '0', null, '1', null, '2', '1', '2017-05-05', '2', '1', '2017-05-11', null, null, '0');
INSERT INTO `orderitem` VALUES ('14', 'DA006FBC733A464A992CAC8C3D919E14', '609E34F077564D6E8B3564EC4EBB5CF1', '10', '100', '3400', '0', '0', null, '0', null, '2', '1', '2017-05-11', '0', null, null, null, null, '0');
INSERT INTO `orderitem` VALUES ('17', 'DA006FBC733A464A992CAC8C3D919E13', '6748434EE73E4A9483424C77EBCDF01A', '10', '100', '3400', '0', '0', null, '0', null, '0', null, null, '0', null, null, null, null, '0');
INSERT INTO `orderitem` VALUES ('18', 'E4CC7F52C58E4D66B2F159CE26B0D08A', 'A3823E376F72400CB2E06B0FE1214DC4', '1', '3', '117', '0', '0', null, '0', null, '0', null, null, '0', null, null, null, null, '0');
INSERT INTO `orderitem` VALUES ('19', '5771A8F6E16D4047A9C58503CC4C3C63', '6959408CD59F40B9AD902C95443C761F', '1', '3', '117', '0', '0', null, '0', null, '0', null, null, '0', null, null, null, null, '0');
INSERT INTO `orderitem` VALUES ('20', '2C5F59BDC10A495C8D733AC2FC1AD571', '47E8987E6CD44E45A0D61972BF0F01FC', '1', '3', '117', '0', '0', null, '0', null, '0', null, null, '0', '1', '2017-05-11', null, null, '0');
INSERT INTO `orderitem` VALUES ('21', '12ABCE39B00F4FA4B21D44F2B53D9192', '5BE72752B8164D92B1CA33129933C1B8', '1', '3', '117', '0', '0', null, '0', null, '0', null, null, '0', null, null, null, null, '0');
INSERT INTO `orderitem` VALUES ('22', 'B8D11ABA742840708847B90ED30FC7E8', 'B684BA91A3CE4779AE93D985A5852B9E', '1', '3', '117', '0', '0', null, '0', null, '0', null, null, '2', '1', '2017-05-11', null, null, '0');
INSERT INTO `orderitem` VALUES ('23', 'AB5AD8A2C3024F748566B4B215C6E2CD', '0AF025B05AF647A2B77876CF2D62573C', '1', '3', '117', '0', '0', null, '0', null, '0', null, null, '0', null, null, null, null, '0');
INSERT INTO `orderitem` VALUES ('27', '5008BA46733E43769E453E40D5C04FE2', '1F57B527505942F4A84834F42A4FE970', '6', '3', '75', '0', '1', '2017-05-14', '2', null, '2', '1', '2017-05-14', '2', '1', '2017-05-14', null, null, '0');
INSERT INTO `orders` VALUES ('0AF025B05AF647A2B77876CF2D62573C', '9', '2017-05-08 11:13:34', '117', '0');
INSERT INTO `orders` VALUES ('1F57B527505942F4A84834F42A4FE970', '6', '2017-05-13 22:28:31', '75', '0');
INSERT INTO `orders` VALUES ('2CBD2A7B3C1F4DD7B11486401FDF0AD1', '6', '2017-05-01 01:58:45', '23', '0');
INSERT INTO `orders` VALUES ('47E8987E6CD44E45A0D61972BF0F01FC', '9', '2017-05-08 11:10:31', '117', '0');
INSERT INTO `orders` VALUES ('5BE72752B8164D92B1CA33129933C1B8', '9', '2017-05-08 11:11:35', '117', '0');
INSERT INTO `orders` VALUES ('609E34F077564D6E8B3564EC4EBB5CF1', '9', '2017-05-05 01:58:02', '3623', '0');
INSERT INTO `orders` VALUES ('6748434EE73E4A9483424C77EBCDF01A', '9', '2017-05-05 01:59:47', '3623', '0');
INSERT INTO `orders` VALUES ('6959408CD59F40B9AD902C95443C761F', '9', '2017-05-08 11:09:42', '117', '0');
INSERT INTO `orders` VALUES ('A3823E376F72400CB2E06B0FE1214DC4', '9', '2017-05-08 11:07:19', '117', '0');
INSERT INTO `orders` VALUES ('B684BA91A3CE4779AE93D985A5852B9E', '9', '2017-05-08 11:12:05', '117', '0');
INSERT INTO `orders` VALUES ('D13043B1F5F0429C88A93286B48DCFF0', '1', '2017-05-03 15:42:16', '100', '0');
INSERT INTO `orders` VALUES ('DA006FBC733A464A992CAC8C3D919E18', '1', '2017-04-30 09:29:53', '107', '0');
INSERT INTO `orders` VALUES ('DCE9A17A5DAB4AA4875A011ECAB9BBEB', '1', '2017-05-03 15:38:46', '100', '0');
INSERT INTO `orders` VALUES ('E4248AFE9DF64391B3D3B9B3E374E9FA', '4', '2017-04-30 18:05:11', '61', '0');
INSERT INTO `outbook` VALUES ('3', '7', '13', '1', '4', '2017-05-03', '1');
INSERT INTO `outbook` VALUES ('4', '27', '9', '3', '6', '2017-05-14', '0');
INSERT INTO `stock` VALUES ('1', '1', '800', '0');
INSERT INTO `stock` VALUES ('2', '2', '800', '0');
INSERT INTO `stock` VALUES ('3', '3', '620', '0');
INSERT INTO `stock` VALUES ('4', '4', '49', '0');
INSERT INTO `stock` VALUES ('8', '5', '10', '1');
INSERT INTO `stock` VALUES ('9', '6', '12', '0');
INSERT INTO `stock` VALUES ('10', '7', '20', '0');
INSERT INTO `stock` VALUES ('11', '8', '23', '0');
INSERT INTO `stock` VALUES ('12', '9', '16', '0');
INSERT INTO `stock` VALUES ('13', '10', '100', '0');
INSERT INTO `stock` VALUES ('14', '11', '15', '0');
INSERT INTO `stock` VALUES ('15', '12', '14', '1');
INSERT INTO `stock` VALUES ('16', '13', '45', '0');
INSERT INTO `stock` VALUES ('17', '14', '12', '0');
INSERT INTO `stock` VALUES ('18', '15', '233', '0');
INSERT INTO `stock` VALUES ('19', '27', '0', '0');
INSERT INTO `stock` VALUES ('20', '29', '0', '0');
INSERT INTO `stock` VALUES ('21', '30', '0', '0');
INSERT INTO `stock` VALUES ('22', '32', '0', '0');
INSERT INTO `stock` VALUES ('23', '33', '0', '0');
INSERT INTO `textbook` VALUES ('1', '9787030391124', '统计学-(第二版)', '1', '杜欢政', '科学出版社', '2013-11-11', '39', '20', '39', '统计学-(第二版)_杜欢政_科学_', '/book_img/06.jpg');
INSERT INTO `textbook` VALUES ('2', '9787300140186', '宏观经济学(第七版)', '11', 'N·格里高利·曼昆(N. Gregory Mankiw)', '中国人民大学出版社', '2011-09-11', '65', '40', '60', '本书涵盖了当代宏观经济学的所有主要领域,介绍了在经济学界达成共识的主要观点,以及存在分歧的不同学派。', '/book_img/07.jpg');
INSERT INTO `textbook` VALUES ('3', '9787040299601', '产业经济学(第三版)', '3', '苏东水', '高等教育出版社', '2010-08-01', '40', '30', '34', '苏东水主编的《产业经济学(第3版)》是教育部“高等教育面向21世纪教学内容和课程体系改革计划”的研究成果,是以教学实践为基础在《产业经济学》(第二版)基础上补充修改而成的。\r\n本书根植于东方管理文化与现实产业发展的实践,围绕如何实现产业资源的有效开发、合理配置和合理利用这一中心,按照企业、市场和政府三条主线逐层展开,既同国际接轨又与实践结合。除论述产业组织、产业结构、产业管理、产业发展外,还适应中国特色和经济全球化对产业经济理论的要求,论述了东方管理“以人为本、以德为先、人为为人”的产业经济思想,吸收、体现了世界产业经济领域的最新动态和精华。\r\n《产业经济学(第3版)》可作为高等院校经济学、管理学门类的专业基础课程教材,也可供其他学科门类选用和社会读者阅读。', '/book_img/08.jpg');
INSERT INTO `textbook` VALUES ('4', '9787302213529', 'Java面向对象程序设计', '1', '耿祥义 张跃平', '清华大学出版社', '2010-01-11', '27', '15', '27', 'Java语言具有面向对象、与平台无关、安全、稳定和多线程等优良特性,是目前软件设计中极为强大的编程语言。Java已成为网络时代最重要的语言之一。本书注重结合实例,以及重要的设计模式,循序渐进地向读者介绍Java面向对象编程的重要知识。所列举例子都是由简到繁,便于读者掌握Java面向对象编程的思想。全书分为17章,分别讲解了基本数据类型、枚举和数组、运算符、表达式和语句、类、对象和接口、面向对象的几个基本原则、重要的设计模式、常用实用类、Java Swing图形用户界面、对话框、Java输入输出流、泛型与集合框架、JDBC数据库操作、Java多线程机制、Java网络基础以及Java Applet等内容。', '/book_img/09.jpg');
INSERT INTO `textbook` VALUES ('5', '9787565408052', '高级财务会计（第三版)', '1', '刘永泽', '东北财经大学出版社', '2012-01-01', '29', '10', '29', '《高级财务会计》(第三版)共设置十章，分别介绍企业合并会计、合并财务报表、外币业务会计、租赁会计、衍生金融工具会计、股份支付会计、中期财务报告与分部报告、物价变动会计、企业重组与清算会计以及特殊行业会计十个专题。《高级财务会计》(第三版)由东北财经大学会计学院刘永泽教授、傅荣教授主编。', '/book_img/11.jpg');
INSERT INTO `textbook` VALUES ('6', '9787565415616', '高级财务会计-第四版', '3', '刘永泽', '东北财经大学出版社', '2014-07-13', '31', '15', '25', '刘永泽、傅荣主编的《**财务会计(第4版东北 财经大学会计学系列教材辽宁省十二五普通高等教育 本科省级规划教材)》分别介绍了企业合并会计、合并 财务报表、外币业务会计、租赁会计、衍生金融工具 会计、股份支付会计、中期财务报告、分部报告、清 算会计以及特殊行业会计十个专题', '/book_img/05.jpg');
INSERT INTO `textbook` VALUES ('7', '9787121193', '疯狂Ajax讲义(第3版)', '3', '李刚', '电子工业出版社', '2013-02-01', '39', '20', '37', '本书为《疯狂Ajax讲义》的第3版,其中jQuery升级到1.8版本;Prototype升级到1.7.1版本;DWR升级到3.0版本。第3版最大更新是详细、全面地介绍了Ext JS 4.1的知识。由于Ext JS 4.1本身内容比较多,因此本书花了几百页来介绍Ext JS的功能和用法,这部分内容独立出来即可作为Ext JS 4.1的学习手册。本书详细介绍了jQuery 1.8、Ext JS 4.1、Prototype 1.7.1、DWR这4个最常用的Ajax框架的用法,并针对每个框架提供了一个实用案例,让读者理论联系实际。这部分内容是“疯狂软件教育中心”的标准讲义,它既包含了实际Ajax开发的重点和难点,也融入了大量学习者的学习经验和感悟。再由笔者以丰富的授课经验为基础,深入浅出地介绍它们,希望读者真正掌握Ajax开发的精髓。本书最后提供了2个综合性案例:Blog系统和电子拍卖系统,让读者将前面所学真正应用到实际项目中。电子拍卖系统是一个包含5个表、表之间具有复杂关联映射的系统,该案例采用目前最流行、最规范的轻量级Java EE架构,将整个应用分为领域对象层、DAO层、业务逻辑层, ', '/book_img/01.jpg');
INSERT INTO `textbook` VALUES ('8', '9787040195835', '数据库系统概论(第四版)', '3', '王珊 萨师煊', '高等教育出版社', '2006-05-01', '45', '20', '45', '本书第1版、第2版和第3版分别于1983年、1991年、2000年出版。第3版被列为“面向21世纪课程教材”,第4版是普通高等教育“十五”国家级规划教材,相应课程于2005年被评为国家精品课程。\r\n本书系统全面地阐述数据库系统的基础理论、基本技术和基本方法。全书分为4篇17章。基础篇包括绪论、关系数据库、关系数据库标准语言SQL、数据库安全性和数据库完整性,共5章;设计与应用开发篇包括关系数据理论、数据库设计和数据库编程,共3章;系统篇包括关系查询处理和查询优化、数据库恢复技术、并发控制和数据库管理系统,共4章;新技术篇包括数据库技术新发展、分布式数据库系统、对象关系数据库系统、XML数据库和数据仓库与联机分析处理技术,共5章。', '/book_img/04.jpg');
INSERT INTO `textbook` VALUES ('9', '9787546369464', '剑桥雅思全真试题原版解析7·8（环球雅思出品）', '2', '环球雅思教学研究中心GTRC', '吉林出版集团有限责任公', '2011-11-01', '48', '37', '48', '本书分为4个单元,每个单元包括:场景介绍、听前预测、解题技巧、核心词汇、拓展词汇、名师解读。', '/book_img/10.jpg');
INSERT INTO `textbook` VALUES ('10', '9787121054785', 'C#实用教程', '1', '郑阿奇', '电子工业出版社', '2008-01-01', '35', '23', '35', 'C#是目前主流的程序设计语言之一,本书以Microsoft Visual Studio 2005作为平台,系统地介绍C#编程基础、面向对象编程、Windows应用程序、GDI+编程、文件操作、数据库应用、多线程技术和Web应用程序。本书包含实用教程、习题、实验和综合应用实习四部分。习题主要突出基本编程和基本概念;实验主要锻炼学生编程和应用的能力,读者先跟着做,然后自己练习;综合应用实习突出C#的主要应用,实习1为Windows应用程序开发,实习2为ASP.NET应用程序开发。本书配有教学课件和应用实例源文件。\r\n本书可作为大学本科、高职高专有关专业C#课程教材,也可供广大C#开发用户参考。', '/book_img/03.jpg');
INSERT INTO `textbook` VALUES ('11', '9787111064596', '机械优化设计(第4版)', '7', '孙靖民', '机械工业出版社', '2011-06-01', '32', '20', '32', '本书为普通高等教育“十一五”国家级规划教材，是教育部“面向21世纪课程教材”。\r\n　　本教材荣获2002年全国普通高等教育优秀教材二等奖。\r\n　　本书是根据全国高等工业学校机械设计及制造专业教学指导委员会所制定的数学计划和教学大纲编写的。本书主要内容有：优化设计概述，优化设计的数学基础，一维搜索方法，无约束优化方法，线性规划，约束优化方法，多目标及离散变量优化方法，机械优化设计实例，常用优化方法程序的使用说明及计算机实习。本书可作为高等工科学校机械设计及制造专业本科生、研究生教材，也可供有关专业师生及工程技术人员参考。', '/book_img/02.jpg');
INSERT INTO `textbook` VALUES ('12', '9787113184391', '软件项目管理（第二版）', '1', '韩万江', '机械工业出版社', '2010-12-07', '23', '0', '23', '软件项目管理这本书详细描述了....', '/book_img/8ABBBE36932A450E846B6073ACFC062C_1.jpg');
INSERT INTO `textbook` VALUES ('13', '55567', 'fg', '2', 'fghf', 'fj', '2010-06-06', '45', '34', '35', '本书是根据教育部组织实施“高等教育面向21世纪教学内容和课程体系改革计划”的要求，经原国家教委批准立项的《面向21世纪工科(化工类)化学系列课程改革的研究与实践》项目中的子课题，由大连理工大学“国家高校工科化学教学基地”组织编写的。由教育部批准从第2版起作为“面向21世纪课程教材”出版。', '/book_img/E66C17DEC44B4CBEB776C5281D6D7FDA_创意返校粉笔画矢量素材.jpg');
INSERT INTO `textbook` VALUES ('14', '34', '23', '19', '', '', '2009-02-11', '34', '23', '0', '本书是把教学内容、教学体系、教学手段的改革融为一体，文字版与电子版相结合，面向21世纪，可用现代化的多媒体技术教学的创新教材。适用于高等理工、师范院校化学、应用化学、化工工艺、化学工程、化工材料、生物化工、化工制药、轻工食品、石油化工等专业。', '/book_img/E03B5C9A0CEF41E48542DE331CD4D2CC_10.jpg');
INSERT INTO `textbook` VALUES ('15', '978711384391', 'JavaEE', '1', '韩万江', '铁道', '2017-05-08', '56', '23', '23', '本书是把教学内容、教学体系、教学手段的改革融为一体，文字版与电子版相结合，面向21世纪，可用现代化的多媒体技术教学的创新教材。适用于高等理工、师范院校化学、应用化学、化工工艺、化学工程、化工材料、生物化工、化工制药、轻工食品、石油化工等专业。', '/book_img/CD5C1726248943649CA3A012905EDC58_22722790-1_l.jpg');
INSERT INTO `textbook` VALUES ('16', '9787040063707', '系统工程理论、方法与应用（第二版）', '1', ' 汪应洛主编', '高等教育出版社', '2003-01-07', '21', '0', '0', '本书是把教学内容、教学体系、教学手段的改革融为一体，文字版与电子版相结合，面向21世纪，可用现代化的多媒体技术教学的创新教材。适用于高等理工、师范院校化学、应用化学、化工工艺、化学工程、化工材料、生物化工、化工制药、轻工食品、石油化工等专业。', '/book_img/13.jpg');
INSERT INTO `textbook` VALUES ('17', '9787561114056', '多媒体CAI物理化学（第五版）', '1', ' 傅玉普', ' 大连理工大学出版社', '2009-07-30', '45', '0', '0', '本书是根据教育部组织实施“高等教育面向21世纪教学内容和课程体系改革计划”的要求，经原国家教委批准立项的《面向21世纪工科(化工类)化学系列课程改革的研究与实践》项目中的子课题，由大连理工大学“国家高校工科化学教学基地”组织编写的。由教育部批准从第2版起作为“面向21世纪课程教材”出版。', '/book_img/14.jpg');
INSERT INTO `textbook` VALUES ('18', '9787109106246', '养牛学（第二版）', '1', ' 王根林', '中国农业出版社', '2012-06-01', '45', '0', '0', '《养牛学》作为一门专业特色课程，在国外，特别是欧美养牛业发达国家的同类大学专业中，一直很受重视。同时，本课程又是一门多学科交叉融合的课程。改革并加强本课程的教学工作，在培养学生将科学技术转化为生产另的创新精神和理论联系实际、促进产业发展的操作能力方面有着特殊的意义。', '/book_img/15.jpg');
INSERT INTO `textbook` VALUES ('19', '9787030111838', '应用概率统计', '20', ' 张国权', '科学出版社', '2011-03-29', '22', '0', '0', '　本书可作为高职高专以及成人高等教育经济管理类各专业的高等数学通用教材，也可作为在职经济管理人员和数学爱好者的经济数学自学教材.\r\n本书可作为高职高专以及成人高等教育经济管理类各专业的高等数学通用教材，也可作为在职经济管理人员和数学爱好者的经济数学自学教材.', '/book_img/16.jpg');
INSERT INTO `textbook` VALUES ('20', '9787111342304', '材料分析方法（第3版)', '20', ' 周玉', '机械工业出版社', '2011-06-01', '42', '0', '0', ' 《材料分析方法(第3版)》可以作为材料科学与工程学科的本科生和研究生教材或教学参考书，也可供材料成形及控制工程等其他专业师生和从事材料研究及分析检测方面工作的技术人员学习参考。', '/book_img/17.jpg');
INSERT INTO `textbook` VALUES ('21', '9787111285007', '机械制造装备设计（第3版）', '21', '关慧珍、冯辛安', '机械工业出版社', '2010-05-01', '40', '0', '0', '　本书适用于高等院校“机械设计制造及其自动化”专业以及相关专业，也可供从事机械制造装备设计和研究的工程技术人员和研究生参考。', '/book_img/18.jpg');
INSERT INTO `textbook` VALUES ('22', '711143225', '经济数学', '2', ' 段瑞 ', '机械工业出版社', '2005-11-24', '33', '0', '0', '机械设计类教材', '/book_img/19.jpg');
INSERT INTO `textbook` VALUES ('23', '9787109218413', '数学（下册）', '1', ' 杨六山、邱宁', '中国农业出版社', '2016-08-02', '25', '0', '0', '暂无', '/book_img/19.jpg');
INSERT INTO `textbook` VALUES ('24', '9787117089272', '医用物理学', '1', '童家明', '人民卫生出版社', '2007-03-01', '36', '0', '0', '医用物理学以全国高等学校医学成人学历教育培养目标、卫生部教材办公室提出的成人学历教育教材要“能够体现我国医学成人学历教育的特点、能够确保成人学历教育目标的实现”编写目标为依据，由全国10省11所院校中长期从事成人医用物理学教学的骨干教师结合多年的教学实践体会共同编写。', '/book_img/20.jpg');
INSERT INTO `textbook` VALUES ('27', '23', '34', '1', '', '', '2017-05-16', '0', '0', '0', '', '/book_img/moren_01.jpg');
INSERT INTO `textbook` VALUES ('28', '23', '34', '1', '', '', '2017-05-16', '0', '0', '0', '', '/book_img/moren_01.jpg');
INSERT INTO `textbook` VALUES ('29', '978565', '图书', '12', '23', '', '2017-05-07', '0', '0', '0', '', '/book_img/E7A31BD6ADB6466B87DF1F1CA0AA472C_22722790-1_l.jpg');
INSERT INTO `textbook` VALUES ('30', 'I7899876123', '计算机', '21', '', '', '2014-05-06', '0', '0', '0', '该书.......', '/book_img/BDA482B1978749A390A141E2DECD4403_20.jpg');
INSERT INTO `textbook` VALUES ('32', 'I7899876123', '计算机', '21', '', '', '2014-05-06', '0', '0', '0', '该书.......', '/book_img/DF7D7EBF3A83409597EE624D1C3279C1_20.jpg');
INSERT INTO `textbook` VALUES ('33', '978302209485', '软件项目', '18', '徐文华', '清华大学', '2009-12-10', '39', '39', '39', '', '/book_img/6965431F89F0444C998305CFDBF41704_17.jpg');
INSERT INTO `user` VALUES ('1', '111', '123456789', '十一', '0', '1101271954@qq.com', '123456789', '重庆工商大学', '', '', '', null, '632964F0B6EE412981A23E59C33F53BF');
INSERT INTO `user` VALUES ('3', '2014131124', '12345678', 'li', '1', '13368253735@168.com', '', '重庆工商大学', '数统', '', '', null, '29BB99508FA2482AAD130DFAD8F19482');
INSERT INTO `user` VALUES ('4', 'user', '123456', 're', '0', 'it@168.com', '', '重庆工商大学', '', '', '', null, 'AFB23010E14A45168F6F106036B50927');
INSERT INTO `user` VALUES ('5', '2014131121', '123456', '王小明', '1', '110@163.com', '123456', '重庆工商大学', '计信', '计算机', '', null, 'EBFE9D2576374AE1AE0375B6F959A417');
INSERT INTO `user` VALUES ('6', '110', '12345678', 'user1', '0', '111@168.com', '', '重庆工商大学', '', '', '', null, '7A5FED76DB2D4B87936A833441B4EF3E');
INSERT INTO `user` VALUES ('9', 'me', '12345678', 'yu', '0', '112@163.com', '', '重庆工商大学', '', '', '', null, null);
INSERT INTO `user` VALUES ('10', '2014120001', '12345678', '鸿雁', '0', '13@168.com', '', '重庆工商大学', '', '', '', null, null);
INSERT INTO `user` VALUES ('11', '2014130001', '12345678', '小豆', '0', '14@168.com', '', '重庆工商大学', '', '', '', null, null);
INSERT INTO `user` VALUES ('12', 'user8', '12345678', 'user8', '0', '124@168.com', '', '重庆工商大学', '', '', '', null, null);
INSERT INTO `user` VALUES ('13', '2014131111', '12345678', '余', '0', '16@163.com', '', '重庆工商大学', '', '', '', null, null);
INSERT INTO `user` VALUES ('14', 'admin14', '12345678', 'admin14', '0', '122@168.cm', '', '重庆工商大学', '', '', '', null, null);
INSERT INTO `user` VALUES ('15', 'KK', '123456', 'KK', '0', '12@168.com', '', '重庆工商大学', '', '', '', null, null);
INSERT INTO `waitbuy` VALUES ('3', '', 'Head First Ajax', '100', '', null, null);
