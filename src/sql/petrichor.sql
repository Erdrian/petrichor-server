/*
SQLyog Community v13.2.0 (64 bit)
MySQL - 8.0.32 : Database - petrichor
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`petrichor` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `petrichor`;

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` bigint NOT NULL,
  `name` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `authority` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `navigation` tinyint(1) DEFAULT NULL,
  `route` tinyint(1) DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  `order_num` int DEFAULT NULL,
  `path` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `title` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `icon` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` char(1) COLLATE utf8mb4_general_ci DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`id`,`name`,`authority`,`navigation`,`route`,`parent_id`,`order_num`,`path`,`title`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`) values 
(476771788668276736,'系统管理','',1,0,NULL,100,NULL,'系统管理',NULL,'admin','2023-08-08 15:20:52','admin','2023-08-10 17:19:33','0'),
(476773977050583040,'用户管理','permission:user-list',1,1,476771788668276736,100,'/sys/user','用户管理',NULL,'admin','2023-08-08 15:29:34','admin','2023-08-10 17:18:41','0'),
(476774099465539584,'角色管理','permission:role-list',1,1,476771788668276736,101,'/sys/role','角色管理',NULL,'admin','2023-08-08 15:30:03','admin','2023-08-11 13:10:19','0'),
(476774250959605760,'权限管理','permission:permission-list',1,1,476771788668276736,102,'/sys/permission','权限管理',NULL,'admin','2023-08-08 15:30:39','admin','2023-08-10 17:36:38','0'),
(477138825710145536,'测试','额',0,0,476771788668276736,99,NULL,NULL,NULL,'admin','2023-08-09 15:39:20',NULL,'2023-08-09 15:40:05','2'),
(477490257235808256,'维护',NULL,0,0,476773977050583040,100,NULL,NULL,NULL,'admin','2023-08-10 14:55:48',NULL,'2023-08-11 12:20:48','2');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint NOT NULL,
  `role_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_by` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(20) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `del_flag` char(1) COLLATE utf8mb4_general_ci DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`role_name`,`create_by`,`create_time`,`update_by`,`update_time`,`del_flag`) values 
(475348849087483904,'管理员','admin','2023-08-04 17:06:37','admin','2023-08-04 17:06:54','0'),
(477038674333143040,'新管理员','admin','2023-08-09 09:01:22',NULL,NULL,'0'),
(480049877246545920,'ces','admin','2023-08-17 16:26:49','admin','2023-08-17 16:26:51','2');

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `role_id` bigint DEFAULT NULL,
  `permission_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_role_permission` */

insert  into `sys_role_permission`(`role_id`,`permission_id`) values 
(475348849087483904,476771788668276736),
(475348849087483904,476774250959605760),
(475348849087483904,476773977050583040),
(475348849087483904,477490257235808256),
(475348849087483904,476774099465539584),
(477038674333143040,476771788668276736),
(477038674333143040,476774250959605760);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint NOT NULL COMMENT '用户ID',
  `user_name` varchar(30) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
  `real_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
  `user_type` varchar(2) COLLATE utf8mb4_general_ci DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `email` varchar(50) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '用户邮箱',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '手机号码',
  `avatar` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '头像地址',
  `password` varchar(100) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '密码',
  `status` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) COLLATE utf8mb4_general_ci DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户信息表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`user_name`,`real_name`,`user_type`,`email`,`phone`,`avatar`,`password`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values 
(1,'admin','浅醉','00','ry@163.com','15888888888','','e10adc3949ba59abbe56e057f20f883e','0','0','admin','2023-07-27 10:04:51','admin','2023-08-10 17:15:57','管理员'),
(475331536627986432,'cefas','123','00','','123',NULL,'b09da03ffbe1f055c7354c59305f0009','0','2','admin','2023-08-04 15:57:49','admin','2023-08-04 16:21:46',NULL),
(477145419382525952,'root','测试下','00','','19822315232',NULL,'8b60b9abda7d97376d809fdb7f75f001','0','0','admin','2023-08-09 16:05:32','admin','2023-08-10 18:03:34',NULL);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` bigint DEFAULT NULL,
  `role_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values 
(477175307221078016,475348849087483904),
(477175382559166464,475348849087483904),
(477176323995865088,475348849087483904),
(477176444326252544,475348849087483904),
(477177324278648832,475348849087483904),
(1,475348849087483904),
(477145419382525952,477038674333143040);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
