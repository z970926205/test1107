/*
SQLyog Ultimate v12.2.6 (32 bit)
MySQL - 8.0.12 : Database - springboott1105
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`springboott1105` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `springboott1105`;

/*Table structure for table `test` */

DROP TABLE IF EXISTS `test`;

CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `test` */

insert  into `test`(`id`,`time`) values 
(1,NULL),
(2,'2018-11-21 18:36:41');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userimage` varchar(50) DEFAULT NULL,
  `sex` varchar(1) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `mender` varchar(10) DEFAULT NULL,
  `updatedate` timestamp NULL DEFAULT NULL,
  `creator` varchar(10) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`userimage`,`sex`,`phone`,`email`,`birthday`,`mender`,`updatedate`,`creator`,`createdate`) values 
(2,'xiaohei','4297F44B13955235245B2497399D7A93',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
(3,'xiaohua','4297F44B13955235245B2497399D7A93','userFace(4).jpg','0','15290712995','970926205@qq.com','2018-11-16',NULL,NULL,NULL,NULL),
(4,'admin','4297F44B13955235245B2497399D7A93',NULL,'2',NULL,NULL,NULL,NULL,NULL,'admin','2018-11-22 14:56:58');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
