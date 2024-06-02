-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: estateadvance
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assignmentbuilding`
--

DROP TABLE IF EXISTS `assignmentbuilding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignmentbuilding` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `staffid` bigint NOT NULL,
  `buildingid` bigint NOT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_building` (`staffid`),
  KEY `fk_building_user` (`buildingid`),
  CONSTRAINT `fk_building_user` FOREIGN KEY (`buildingid`) REFERENCES `building` (`id`),
  CONSTRAINT `fk_user_building` FOREIGN KEY (`staffid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignmentbuilding`
--

LOCK TABLES `assignmentbuilding` WRITE;
/*!40000 ALTER TABLE `assignmentbuilding` DISABLE KEYS */;
INSERT INTO `assignmentbuilding` VALUES (18,2,91,NULL,NULL,NULL,NULL),(20,2,2,NULL,NULL,NULL,NULL),(21,4,1,NULL,NULL,NULL,NULL),(22,3,78,NULL,NULL,NULL,NULL),(23,4,78,NULL,NULL,NULL,NULL),(24,2,90,NULL,NULL,NULL,NULL),(25,3,90,NULL,NULL,NULL,NULL),(26,4,4,NULL,NULL,NULL,NULL),(27,3,89,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `assignmentbuilding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignmentcustomer`
--

DROP TABLE IF EXISTS `assignmentcustomer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignmentcustomer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `staffid` bigint NOT NULL,
  `customerid` bigint NOT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_customer` (`staffid`),
  KEY `fk_customer_user` (`customerid`),
  CONSTRAINT `fk_customer_user` FOREIGN KEY (`customerid`) REFERENCES `customer` (`id`),
  CONSTRAINT `fk_user_customer` FOREIGN KEY (`staffid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignmentcustomer`
--

LOCK TABLES `assignmentcustomer` WRITE;
/*!40000 ALTER TABLE `assignmentcustomer` DISABLE KEYS */;
INSERT INTO `assignmentcustomer` VALUES (30,2,10,NULL,NULL,NULL,NULL),(31,3,10,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `assignmentcustomer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `building`
--

DROP TABLE IF EXISTS `building`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `building` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `street` varchar(255) DEFAULT NULL,
  `ward` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `structure` varchar(255) DEFAULT NULL,
  `numberofbasement` int DEFAULT NULL,
  `floorarea` int DEFAULT NULL,
  `direction` varchar(255) DEFAULT NULL,
  `level` varchar(255) DEFAULT NULL,
  `rentprice` int DEFAULT NULL,
  `rentpricedescription` text,
  `servicefee` varchar(255) DEFAULT NULL,
  `carfee` varchar(255) DEFAULT NULL,
  `motofee` varchar(255) DEFAULT NULL,
  `overtimefee` varchar(255) DEFAULT NULL,
  `waterfee` varchar(255) DEFAULT NULL,
  `electricityfee` varchar(255) DEFAULT NULL,
  `deposit` varchar(255) DEFAULT NULL,
  `payment` varchar(255) DEFAULT NULL,
  `renttime` varchar(255) DEFAULT NULL,
  `decorationtime` varchar(255) DEFAULT NULL,
  `brokeragefee` decimal(13,2) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `linkofbuilding` varchar(255) DEFAULT NULL,
  `map` varchar(255) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `managername` varchar(45) DEFAULT NULL,
  `managerphone` varchar(45) DEFAULT NULL,
  `rentareadescription` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=160 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `building`
--

LOCK TABLES `building` WRITE;
/*!40000 ALTER TABLE `building` DISABLE KEYS */;
INSERT INTO `building` VALUES (1,'Nam Giao Building Tower','59 phan xích long','Phường 2','QUAN_1','',2,500,'','',15,'15 triệu/m2','','','','','',NULL,'','','','',NULL,'TANG_TRET,NGUYEN_CAN','','','',NULL,'2024-05-21 10:16:31','2024-05-23 15:46:53',NULL,'nguyenquangvinh',NULL,'',NULL,''),(2,'ACM Tower','96 cao thắng','Phường 4','QUAN_2','',2,650,'','',18,'18 triệu/m2','','','','','',NULL,'','','','',NULL,'NGUYEN_CAN','','','',NULL,'2024-05-21 10:19:53','2024-05-23 15:46:47',NULL,'nguyenquangvinh',NULL,'',NULL,''),(3,'Alpha 2 Building Tower','153 nguyễn đình chiểu','Phường 6','QUAN_1','',1,200,'','',20,'20 triệu/m2','','','','','',NULL,'','','','',NULL,'NOI_THAT','','','',NULL,'2024-05-21 10:20:31','2024-05-22 08:13:45',NULL,'nguyenvana',NULL,'',NULL,''),(4,'IDD 1 Building','111 Lý Chính Thắng','Phường 7','QUAN_4','',1,200,'','',12,'12 triệu/m2','','','','','',NULL,'','','','',NULL,'TANG_TRET,NGUYEN_CAN','','','',NULL,'2024-05-21 10:20:41','2024-05-24 20:08:25',NULL,'admin',NULL,'',NULL,''),(78,'SAIGON COOP TOWER','131 Điện Biên Phủ','phường 15','QUAN_BINHTHANH','',4,415,'','',21,'','','','','','',NULL,'','','','',NULL,'TANG_TRET','','','',NULL,'2024-05-21 10:20:21','2024-05-23 15:47:13',NULL,'nguyenquangvinh',NULL,'',NULL,''),(79,'Saigon Paragon Building','3 Nguyễn Lương Bằng','phường Tân Phú','QUAN_7','',NULL,135,'','',17,'','','','','','',NULL,'','','','',NULL,'[]','','','',NULL,NULL,'2024-05-21 20:27:54',NULL,'nguyenvana','/building/toa-nha-vpbank-saigon-tower.jpg','',NULL,''),(80,'Hemera Building','181-183-185 Trần Hưng Đạo','phường Cô Giang','QUAN_1',NULL,NULL,210,NULL,NULL,17,'','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'NGUYEN_CAN,NOI_THAT',NULL,NULL,NULL,NULL,'2024-05-11 10:37:06','2024-05-11 10:37:06','nguyenvana','nguyenvana',NULL,'',NULL,NULL),(81,'Tòa nhà 2 Trương Quốc Dung','2 Trương Quốc Dung','phường 8','QUAN_PHUNHUAN',NULL,NULL,300,NULL,NULL,55,'','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'TANG_TRET',NULL,NULL,NULL,NULL,'2024-05-11 10:46:11','2024-05-29 23:46:42','nguyenvana','admin',NULL,'',NULL,NULL),(82,'AB Bank Building','18 Phan Đình Giót','phường 2','QUAN_TANBINH','',6,350,'','',25,'','','','','','',NULL,'','','','',NULL,'NGUYEN_CAN','','','',NULL,NULL,'2024-05-21 11:00:40',NULL,'nguyenvana',NULL,'',NULL,''),(89,'Bitexco Financial Tower','2 Hải Triều','phường Bến Nghé','QUAN_1','',NULL,400,'','',50,'','','','','','',NULL,'','','','',NULL,'[]','','','',NULL,'2024-05-21 10:20:49','2024-05-29 23:46:36',NULL,'admin','/building/toa-nha-vpbank-saigon-tower.jpg','',NULL,'100 triệu rẻ quá'),(90,'The Crest Office','Lô 1.13','phường Thủ Thiêm','QUAN_2',NULL,NULL,589,NULL,NULL,39,'','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'TANG_TRET,NGUYEN_CAN',NULL,NULL,NULL,NULL,'2024-05-11 11:03:14','2024-05-24 00:07:17','nguyenvana','nguyenquangvinh',NULL,'',NULL,NULL),(91,'Transimex Building','172 Hai Bà Trưng','phường Bến Nghé','QUAN_1','',2,400,'Đông','',NULL,'','','','','','',NULL,'','','','',NULL,'[]','','','',NULL,NULL,'2024-05-26 23:00:41',NULL,'admin',NULL,'',NULL,''),(159,'test','','','','',NULL,NULL,'','',NULL,'','','','','','',NULL,'','','','',NULL,'[]','','','',NULL,'2024-05-29 23:31:18','2024-05-29 23:31:18','admin','admin',NULL,'',NULL,'');
/*!40000 ALTER TABLE `building` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fullname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `requirement` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (10,'nguyen thi xin tươi','0123456789','thiaa@gmail.com.','2024-05-24 19:41:50','2024-05-26 13:05:38','admin','admin','mua nhà',' xử lý thành công','mua nhà quận 2'),(11,'nguyen minh t','0987654321','minht@gmail.com','2024-05-24 19:42:41','2024-05-29 23:47:03','admin','admin','thuê nhà','đã xử lý','muốn thuê quận 1');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rentarea`
--

DROP TABLE IF EXISTS `rentarea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rentarea` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `value` int DEFAULT NULL,
  `buildingid` bigint DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rentarea_building` (`buildingid`),
  CONSTRAINT `rentarea_building` FOREIGN KEY (`buildingid`) REFERENCES `building` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=508 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rentarea`
--

LOCK TABLES `rentarea` WRITE;
/*!40000 ALTER TABLE `rentarea` DISABLE KEYS */;
INSERT INTO `rentarea` VALUES (246,78,80,'2024-05-11 10:37:06','2024-05-11 10:37:06','nguyenvana','nguyenvana'),(247,136,80,'2024-05-11 10:37:06','2024-05-11 10:37:06','nguyenvana','nguyenvana'),(248,210,80,'2024-05-11 10:37:06','2024-05-11 10:37:06','nguyenvana','nguyenvana'),(249,100,81,'2024-05-11 10:46:11','2024-05-11 10:46:11','nguyenvana','nguyenvana'),(250,200,81,'2024-05-11 10:46:11','2024-05-11 10:46:11','nguyenvana','nguyenvana'),(251,300,81,'2024-05-11 10:46:11','2024-05-11 10:46:11','nguyenvana','nguyenvana'),(256,100,90,'2024-05-11 11:03:14','2024-05-11 11:03:14','nguyenvana','nguyenvana'),(257,200,90,'2024-05-11 11:03:14','2024-05-11 11:03:14','nguyenvana','nguyenvana'),(258,300,90,'2024-05-11 11:03:14','2024-05-11 11:03:14','nguyenvana','nguyenvana'),(259,500,90,'2024-05-11 11:03:14','2024-05-11 11:03:14','nguyenvana','nguyenvana'),(451,100,1,'2024-05-21 10:16:31','2024-05-21 10:16:31','nguyenvana','nguyenvana'),(452,200,1,'2024-05-21 10:16:31','2024-05-21 10:16:31','nguyenvana','nguyenvana'),(456,200,2,'2024-05-21 10:19:53','2024-05-21 10:19:53','nguyenvana','nguyenvana'),(457,300,2,'2024-05-21 10:19:53','2024-05-21 10:19:53','nguyenvana','nguyenvana'),(458,400,2,'2024-05-21 10:19:53','2024-05-21 10:19:53','nguyenvana','nguyenvana'),(459,197,78,'2024-05-21 10:20:21','2024-05-21 10:20:21','nguyenvana','nguyenvana'),(460,415,78,'2024-05-21 10:20:21','2024-05-21 10:20:21','nguyenvana','nguyenvana'),(461,445,78,'2024-05-21 10:20:21','2024-05-21 10:20:21','nguyenvana','nguyenvana'),(462,300,3,'2024-05-21 10:20:31','2024-05-21 10:20:31','nguyenvana','nguyenvana'),(463,400,3,'2024-05-21 10:20:31','2024-05-21 10:20:31','nguyenvana','nguyenvana'),(464,800,3,'2024-05-21 10:20:31','2024-05-21 10:20:31','nguyenvana','nguyenvana'),(465,100,4,'2024-05-21 10:20:41','2024-05-21 10:20:41','nguyenvana','nguyenvana'),(466,400,4,'2024-05-21 10:20:41','2024-05-21 10:20:41','nguyenvana','nguyenvana'),(467,600,4,'2024-05-21 10:20:41','2024-05-21 10:20:41','nguyenvana','nguyenvana'),(486,100,82,'2024-05-21 11:00:40','2024-05-21 11:00:40','nguyenvana','nguyenvana'),(487,200,82,'2024-05-21 11:00:40','2024-05-21 11:00:40','nguyenvana','nguyenvana'),(488,500,82,'2024-05-21 11:00:40','2024-05-21 11:00:40','nguyenvana','nguyenvana'),(489,100,79,'2024-05-21 20:27:54','2024-05-21 20:27:54','nguyenvana','nguyenvana'),(490,300,79,'2024-05-21 20:27:54','2024-05-21 20:27:54','nguyenvana','nguyenvana'),(491,500,79,'2024-05-21 20:27:54','2024-05-21 20:27:54','nguyenvana','nguyenvana'),(492,50,91,'2024-05-26 23:00:41','2024-05-26 23:00:41','admin','admin'),(493,100,91,'2024-05-26 23:00:41','2024-05-26 23:00:41','admin','admin'),(494,200,91,'2024-05-26 23:00:41','2024-05-26 23:00:41','admin','admin'),(495,570,91,'2024-05-26 23:00:41','2024-05-26 23:00:41','admin','admin'),(504,100,89,'2024-05-29 20:21:19','2024-05-29 20:21:19','admin','admin'),(505,200,89,'2024-05-29 20:21:19','2024-05-29 20:21:19','admin','admin'),(506,500,89,'2024-05-29 20:21:19','2024-05-29 20:21:19','admin','admin'),(507,1000,89,'2024-05-29 20:21:19','2024-05-29 20:21:19','admin','admin');
/*!40000 ALTER TABLE `rentarea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Quản lý','MANAGER',NULL,NULL,NULL,NULL),(2,'Nhân viên','STAFF',NULL,NULL,NULL,NULL),(3,'Quản trị viên','ADMIN',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `customerid` bigint NOT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  `staffid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_customer_transaction` (`customerid`),
  CONSTRAINT `fk_customer_transaction` FOREIGN KEY (`customerid`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (23,'QUA_TRINH_CSKH','test ',10,'2024-05-24 20:04:02','2024-05-24 20:04:02','admin','admin',NULL),(24,'QUA_TRINH_CSKH','gửi thông tin nhà cho khách',10,'2024-05-24 20:21:43','2024-05-24 20:21:43','admin','admin',NULL),(25,'DAN_DI_XEM','hẹn 2h chiều mai',10,'2024-05-24 20:21:57','2024-05-24 20:21:57','admin','admin',NULL),(26,'QUA_TRINH_CSKH','khách yêu cầu thêm thông tin',10,'2024-05-24 20:22:55','2024-05-24 20:22:55','admin','admin',NULL),(27,'DAN_DI_XEM','đã gặp khách',10,'2024-05-24 20:25:36','2024-05-24 20:25:36','nguyenvana','nguyenvana',NULL),(44,'DAN_DI_XEM','khách chốt đơn',10,'2024-05-26 12:46:13','2024-05-26 12:46:13','admin','admin',NULL);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'nguyenvana','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van a',NULL,NULL,1,NULL,NULL,NULL,NULL),(2,'nguyenvanb','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van b',NULL,NULL,1,NULL,NULL,NULL,NULL),(3,'nguyenvanc','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van c',NULL,NULL,1,NULL,NULL,NULL,NULL),(4,'nguyenvand','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','nguyen van d',NULL,NULL,1,NULL,'2024-05-22 14:43:29',NULL,'nguyenquangvinh'),(5,'admin','$2a$10$OPyRSTu04vLLdCsJwgonSus9bRfvMm21jWtEZs189BS/SW86ZF1u2','nguyen quang vinh',NULL,NULL,1,NULL,'2024-05-24 06:35:33',NULL,'nguyenquangvinh'),(8,'test','$2a$10$4ZhhQES/o.s4B.ax4zd.aue8.ZOh25cveSOy9JY5SgUfCHI2VONja','test',NULL,NULL,0,'2024-05-24 19:40:55','2024-05-24 19:41:07','admin','admin'),(10,'test 1','$2a$10$uKF553bB/yKTT0WgO/O3POR1K7.GSLhtWp.9YYxe8Qq/PEOiJ/CfC','test',NULL,NULL,0,'2024-05-24 20:24:03','2024-05-24 20:24:18','admin','admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `roleid` bigint NOT NULL,
  `userid` bigint NOT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_role` (`userid`),
  KEY `fk_role_user` (`roleid`),
  CONSTRAINT `fk_role_user` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,1,NULL,NULL,NULL,NULL),(2,2,2,NULL,NULL,NULL,NULL),(5,3,5,NULL,NULL,NULL,NULL),(9,2,4,NULL,NULL,NULL,NULL),(10,2,3,NULL,NULL,NULL,NULL),(11,2,8,NULL,NULL,NULL,NULL),(12,2,10,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-03  3:20:44
