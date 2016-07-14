/*
Navicat MySQL Data Transfer

Source Server         : zhu
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : cms

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-04-30 18:37:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cms_advertising`
-- ----------------------------
DROP TABLE IF EXISTS `cms_advertising`;
CREATE TABLE `cms_advertising` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `order_num` int(11) DEFAULT NULL COMMENT '顺序',
  `picture_path` varchar(255) DEFAULT NULL COMMENT '存储路径',
  `picture_url` varchar(255) DEFAULT NULL COMMENT '访问地址',
  `jump_url` varchar(255) DEFAULT NULL COMMENT '跳转地址',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `type` tinyint(4) DEFAULT NULL COMMENT '所属终端',
  `ts` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='广告位';

-- ----------------------------
-- Records of cms_advertising
-- ----------------------------
INSERT INTO `cms_advert` VALUES ('1', '1', '1', '1', '1', '1', '1', '1', '2', '2016-03-15 14:53:02');
INSERT INTO `cms_advert` VALUES ('22', '2', '2', '2', '2', '2', '2', '1', '2', '2016-03-15 14:53:20');

-- ----------------------------
-- Table structure for `cms_banner`
-- ----------------------------
DROP TABLE IF EXISTS `cms_banner`;
CREATE TABLE `cms_banner` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `order_num` int(11) DEFAULT NULL COMMENT '顺序',
  `picture_path` varchar(255) DEFAULT NULL COMMENT '存储路径',
  `picture_url` varchar(255) DEFAULT NULL COMMENT '访问地址',
  `jump_url` varchar(255) DEFAULT NULL COMMENT '跳转地址',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态',
  `type` tinyint(4) DEFAULT NULL COMMENT '所属终端',
  `ts` datetime DEFAULT NULL COMMENT '时间戳',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='轮播图';

-- ----------------------------
-- Records of cms_banner
-- ----------------------------
INSERT INTO `cms_banner` VALUES ('3', '1', '1', 'D:/img/banner/1462009048902.png', 'http://img.power.combanner/1462009048902.png', 'baidu', '', '1', '1', '2016-04-30 17:37:28');

-- ----------------------------
-- Table structure for `sys_auth`
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth`;
CREATE TABLE `sys_auth` (
  `AUTHORITY_ID` varchar(32) NOT NULL COMMENT '主键',
  `RESOURCE_ID` varchar(32) DEFAULT NULL COMMENT '资源ID',
  `RESOURCE_TYPE` varchar(50) DEFAULT NULL COMMENT '资源类类',
  `VISITOR_ID` varchar(32) DEFAULT NULL COMMENT '访问者ID',
  `REMARK` varchar(300) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`AUTHORITY_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='权限信息';

-- ----------------------------
-- Records of sys_auth
-- ----------------------------
INSERT INTO `sys_auth` VALUES ('f6d97ec06549471c82602e9d2619a5b3', '14d02052ca24408fb6edf26f16394983', 'Menu', '75de94ba43f34738a65790a539985fc3', null);
INSERT INTO `sys_auth` VALUES ('95c042b81e7e442caabd4b4488b5ebb1', 'ef33d21e9d8945b2ac813408866e028c', 'Menu', '75de94ba43f34738a65790a539985fc3', null);
INSERT INTO `sys_auth` VALUES ('f5bc3cddcbbb4316842ce2af51145e6c', '33ac8a8ece714f3998215db84a045083', 'Menu', '75de94ba43f34738a65790a539985fc3', null);
INSERT INTO `sys_auth` VALUES ('10de67f25f9945b28f94e875d4b5f98e', '1', 'Menu', '75de94ba43f34738a65790a539985fc3', null);
INSERT INTO `sys_auth` VALUES ('f1064319d75b47a3876cc743ee2ab37b', '402880ec2af47efb012af48398a30001', 'Menu', '75de94ba43f34738a65790a539985fc3', null);

-- ----------------------------
-- Table structure for `sys_dept`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `NAME` varchar(60) DEFAULT NULL COMMENT '名称',
  `PARENT_ID` varchar(32) DEFAULT NULL COMMENT '父结点',
  `ORDER_CODE` varchar(10) DEFAULT NULL COMMENT '排序编号',
  `IS_LEAF` tinyint(2) DEFAULT NULL COMMENT '是否叶子',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='组织机构';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '大型商务', '', '0', '0', '');
INSERT INTO `sys_dept` VALUES ('29176d46665d43b88d124fe49385aeb2', '市场部', '1', '0', '1', '1');
INSERT INTO `sys_dept` VALUES ('bd2b7de706dd4088b00ef4bd3225b28b', '教学部', '1', '12', '1', '12');

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `TYPE_ID` varchar(32) DEFAULT NULL COMMENT '类型名称',
  `TYPE_CODE` int(3) DEFAULT NULL COMMENT '类型编码',
  `DATA_KEY` int(3) DEFAULT NULL COMMENT '数据键',
  `DATA_VALUE` varchar(50) DEFAULT NULL COMMENT '数据值',
  `REMARK` varchar(300) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('f08b82ff2b5b4f56a433ef6588663a2a', '632bc81dd54d4915b533d61d8e8c5529', '100', '1', '男', '');
INSERT INTO `sys_dict` VALUES ('9779d754ebed475fb3f55ef1cde64b29', '632bc81dd54d4915b533d61d8e8c5529', '100', '2', '女', '');
INSERT INTO `sys_dict` VALUES ('1c5f64219a0243488ec02dcdb07da8ce', '632bc81dd54d4915b533d61d8e8c5529', '100', '3', '3', '');
INSERT INTO `sys_dict` VALUES ('d4d7eb1318084444989b6c5a5fc06e92', 'f536e7e1e59842dc83fd178948b67bb3', '10', '1', 'PC端', '');
INSERT INTO `sys_dict` VALUES ('52335ad1e1f84a638b8774b2df559e7c', 'f536e7e1e59842dc83fd178948b67bb3', '10', '2', 'M站', '');
INSERT INTO `sys_dict` VALUES ('0a51157d082b4829bd2b69d1d726d039', 'f536e7e1e59842dc83fd178948b67bb3', '10', '3', 'APP', '');
INSERT INTO `sys_dict` VALUES ('ca9954fabd99406abba63cc7f48339a6', '18dc97416b954d01bb058a441f2760c2', '11', '1', '启用', '');
INSERT INTO `sys_dict` VALUES ('3390fb28749a41bd90e50664f6a5dacb', '18dc97416b954d01bb058a441f2760c2', '11', '0', '禁用', '');

-- ----------------------------
-- Table structure for `sys_dict_type`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `TYPE_CODE` int(3) DEFAULT NULL COMMENT '类型标识',
  `TYPE_NAME` varchar(50) DEFAULT NULL COMMENT '类型名称',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='字典类型';

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES ('632bc81dd54d4915b533d61d8e8c5529', '100', '性别', '性别');
INSERT INTO `sys_dict_type` VALUES ('f536e7e1e59842dc83fd178948b67bb3', '10', '终端类型', '');
INSERT INTO `sys_dict_type` VALUES ('18dc97416b954d01bb058a441f2760c2', '11', '轮播图状态', '');

-- ----------------------------
-- Table structure for `sys_func`
-- ----------------------------
DROP TABLE IF EXISTS `sys_func`;
CREATE TABLE `sys_func` (
  `FUNC_ID` varchar(32) NOT NULL COMMENT '主键',
  `PARENT_ID` varchar(32) DEFAULT NULL COMMENT '父结点',
  `NAME` varchar(50) DEFAULT NULL COMMENT '名称',
  `URL` varchar(200) DEFAULT NULL COMMENT '地址或按钮键',
  `TYPE` varchar(6) DEFAULT NULL COMMENT '类型',
  `ORDER_CODE` varchar(10) DEFAULT NULL COMMENT '排序编号',
  `IS_LEAF` tinyint(2) DEFAULT NULL COMMENT '是否叶子',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`FUNC_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='功能菜单';

-- ----------------------------
-- Records of sys_func
-- ----------------------------
INSERT INTO `sys_func` VALUES ('1', '-1', '功能菜单', '', 'MENU', '0010', '0', '');
INSERT INTO `sys_func` VALUES ('14d02052ca24408fb6edf26f16394983', '402880ec2af47efb012af48398a30001', '用户管理', '/user/list', 'MENU', '', '1', '');
INSERT INTO `sys_func` VALUES ('33ac8a8ece714f3998215db84a045083', '402880ec2af47efb012af48398a30001', '修改密码', '/user/toEditPws', 'MENU', '020', '1', '');
INSERT INTO `sys_func` VALUES ('402880ec2af47efb012af48398a30001', '1', '系统管理', '', 'MENU', '20', '0', '');
INSERT INTO `sys_func` VALUES ('64e4716f5311449188aec9b9db4cd82a', '402880ec2af47efb012af48398a30001', '字典管理', '/dicttype/list', 'MENU', '040', '1', '');
INSERT INTO `sys_func` VALUES ('9fe11e2f455a46b2a6874fa57cc80ff8', '402880ec2af47efb012af48398a30001', '菜单维护', '/sysfunc/totree', 'MENU', '070', '1', '');
INSERT INTO `sys_func` VALUES ('a98721606eb1495d964f2a499a7ff793', '402880ec2af47efb012af48398a30001', '参数维护', '/param/list', 'MENU', '050', '1', '');
INSERT INTO `sys_func` VALUES ('cf997dfd0a1e4a0d91651ebfee9774bd', '402880ec2af47efb012af48398a30001', '机构管理', '/sysdept/totree', 'MENU', '080', '1', '');
INSERT INTO `sys_func` VALUES ('ef33d21e9d8945b2ac813408866e028c', '402880ec2af47efb012af48398a30001', '角色管理', '/role/list', 'MENU', '010', '1', '');
INSERT INTO `sys_func` VALUES ('d4dc9c057184433caaaf2dab4bcff994', '1', 'cms管理', '', 'MENU', '', '0', '');
INSERT INTO `sys_func` VALUES ('e052bea8096243abb28fb3a5f71c08a4', 'd4dc9c057184433caaaf2dab4bcff994', '轮播图管理', '/banner/list', 'MENU', '', '1', '');

-- ----------------------------
-- Table structure for `sys_para`
-- ----------------------------
DROP TABLE IF EXISTS `sys_para`;
CREATE TABLE `sys_para` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `NAME` varchar(50) DEFAULT NULL COMMENT '参数名称',
  `PARAM_KEY` varchar(50) DEFAULT NULL COMMENT '参数键',
  `PARAM_VALUE` varchar(100) DEFAULT NULL COMMENT '参数值',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='系统参数';

-- ----------------------------
-- Records of sys_para
-- ----------------------------
INSERT INTO `sys_para` VALUES ('111', '重置密码', 'defalt_password', '111111', '重置密码时的默认值');
INSERT INTO `sys_para` VALUES ('f59b5ed5ba4f4548b2f1356634529100', '每页记录个数', 'PAGE_SIZE', '30', '每页记录个数');
INSERT INTO `sys_para` VALUES ('95bdab926799482da6b5a3a44a776135', '文件写入根路径', 'fileRoot', 'D:/img/', '');
INSERT INTO `sys_para` VALUES ('e9faf482e8f349019bb29ff3722b0c12', '文件展示路径前缀', 'imgUrl', 'http://img.power.com', '');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `NAME` varchar(32) DEFAULT NULL COMMENT '名称',
  `REMARK` varchar(300) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('75de94ba43f34738a65790a539985fc3', '管理员组', '管理员组');

-- ----------------------------
-- Table structure for `sys_role_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `USER_ID` varchar(32) DEFAULT NULL COMMENT '用户',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='角色用户关联';

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('19d44dcffa2545bab64af004e6126aa9', 'cd85183b048248dba517dee7ead00ab0', '75de94ba43f34738a65790a539985fc3');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `ID` varchar(32) NOT NULL COMMENT '主键',
  `dept_ID` varchar(32) DEFAULT NULL COMMENT '行政区划',
  `LOGIN_ID` varchar(50) DEFAULT NULL COMMENT '登录名',
  `PASSWORD` varchar(50) DEFAULT NULL COMMENT '登录密码',
  `SEX_STAT` varchar(10) DEFAULT NULL COMMENT '性别',
  `NAME` varchar(50) DEFAULT NULL COMMENT '姓名',
  `TEL` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('659311b34d384059b621dbf1ffc84314', 'bd2b7de706dd4088b00ef4bd3225b28b', 'donghongzeng', 'Y5ofPcegZM2S/bn+tjPWXA==', '1', '杜老师', '18618171514', '');
INSERT INTO `sys_user` VALUES ('cd85183b048248dba517dee7ead00ab0', 'bd2b7de706dd4088b00ef4bd3225b28b', 'gzz', 'lueSGJZetyySpUndWjMBEg==', '1', '高振中', '13688888888', '');
INSERT INTO `sys_user` VALUES ('5b19061977384264ad554b9529d36754', 'bd2b7de706dd4088b00ef4bd3225b28b', 'laoguo', 'lueSGJZetyySpUndWjMBEg==', '1', '老郭', '13888888888', '');
INSERT INTO `sys_user` VALUES ('1', '29176d46665d43b88d124fe49385aeb2', 'admin', 'lueSGJZetyySpUndWjMBEg==', '1', '管理员', '', '');
