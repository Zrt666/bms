/*
SQLyog v10.2 
MySQL - 5.7.9-log : Database - bms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bms` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bms`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `bid` char(32) NOT NULL,
  `bname` varchar(300) DEFAULT NULL,
  `press` varchar(300) DEFAULT NULL,
  `image` varchar(300) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `cid` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`bid`,`bname`,`press`,`image`,`status`,`cid`) values ('1','Java编程思想（第4版）','张瑞涛','book_img/9317290-1_l.jpg',0,'1'),('2','Java核心技术卷1','qdmmy6','book_img/20285763-1_l.jpg',0,'1'),('3','Java就业培训教程','张孝祥','book_img/8758723-1_l.jpg',0,'1'),('4','Head First java','（美）塞若','book_img/9265169-1_l.jpg',0,'1'),('5','JavaWeb开发详解','孙鑫','book_img/22788412-1_l.jpg',0,'2'),('6','Struts2深入详解','孙鑫','book_img/20385925-1_l.jpg',0,'2'),('7','精通Hibernate','孙卫琴','book_img/8991366-1_l.jpg',0,'2'),('7A5A8AAEB7C24BA58F22A640065F6112',NULL,NULL,'book_img/5E56EB22CD16424F94CA48AE93B6AF35',0,NULL),('8','精通Spring2.x','陈华雄','book_img/20029394-1_l.jpg',0,'2'),('9','Javascript权威指南','（美）弗兰纳根','book_img/22722790-1_l.jpg',0,'3');

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `cid` char(32) NOT NULL,
  `cname` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `category` */

insert  into `category`(`cid`,`cname`) values ('1','JavaSE'),('2','JavaEE'),('3','Javascript'),('9349904E051A4EC8BF9F47E0001F5F6A','spring');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
