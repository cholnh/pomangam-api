CREATE DATABASE  IF NOT EXISTS `db_pmg` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_pmg`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: www.pomangam.com    Database: db_pmg
-- ------------------------------------------------------
-- Server version	5.7.23-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `additional_for_product_tbl`
--

DROP TABLE IF EXISTS `additional_for_product_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `additional_for_product_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `product_idx` int(11) DEFAULT NULL COMMENT '물품 인덱스',
  `map_product_idx` int(11) DEFAULT NULL COMMENT '매핑되는 물품 인덱스\n',
  PRIMARY KEY (`idx`),
  KEY `add_prod_fk_idx` (`product_idx`),
  KEY `target_add_prod_fk_idx` (`map_product_idx`),
  CONSTRAINT `additional_product_fk` FOREIGN KEY (`product_idx`) REFERENCES `product_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `target_add_product_fk` FOREIGN KEY (`map_product_idx`) REFERENCES `product_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='물품 추가사항 매핑 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `additional_for_product_tbl`
--

LOCK TABLES `additional_for_product_tbl` WRITE;
/*!40000 ALTER TABLE `additional_for_product_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `additional_for_product_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `advertise_for_main_tbl`
--

DROP TABLE IF EXISTS `advertise_for_main_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertise_for_main_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `imgpath` varchar(100) DEFAULT NULL COMMENT '광고 제목',
  `next_step_type` tinyint(4) NOT NULL COMMENT '클릭시, 이동할 다음 페이지의 타입\n0 - 이동 안 함\n1 - 음식점 상세 페이지\n2 - 음식 상세 페이지\n3 - 이벤트 상세 페이지\n4 - 공지사항 상세 페이지',
  `next_step_location` varchar(45) NOT NULL COMMENT '클릭시, 이동할 다음 페이지의 주소',
  `state_active` tinyint(4) NOT NULL DEFAULT '1' COMMENT '활성화 여부 상태\n0 - 비활성화\n1 - 활성화',
  `sequence` int(11) NOT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='메인화면 광고 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertise_for_main_tbl`
--

LOCK TABLES `advertise_for_main_tbl` WRITE;
/*!40000 ALTER TABLE `advertise_for_main_tbl` DISABLE KEYS */;
INSERT INTO `advertise_for_main_tbl` VALUES (1,'/assets/image/advertise/main/3.png',2,'products/1',1,1),(2,'/assets/image/advertise/main/4.png',3,'events/1',1,2),(3,'/assets/image/advertise/main/5.png',4,'notices/1',1,3);
/*!40000 ALTER TABLE `advertise_for_main_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `advertise_for_popup_tbl`
--

DROP TABLE IF EXISTS `advertise_for_popup_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advertise_for_popup_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `imgpath` varchar(100) DEFAULT NULL COMMENT '광고 제목',
  `next_step_type` tinyint(4) NOT NULL COMMENT '클릭시, 이동할 다음 페이지의 타입\n0 - 이동 안 함\n1 - 음식점 상세 페이지\n2 - 음식 상세 페이지\n3 - 이벤트 상세 페이지\n4 - 공지사항 상세 페이지',
  `next_step_location` varchar(45) NOT NULL COMMENT '클릭시, 이동할 다음 페이지의 주소',
  `state_active` tinyint(4) NOT NULL DEFAULT '1' COMMENT '활성화 여부 상태\n0 - 비활성화\n1 - 활성화',
  `sequence` int(11) NOT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='메인화면 팝업 광고 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertise_for_popup_tbl`
--

LOCK TABLES `advertise_for_popup_tbl` WRITE;
/*!40000 ALTER TABLE `advertise_for_popup_tbl` DISABLE KEYS */;
INSERT INTO `advertise_for_popup_tbl` VALUES (1,'/assets/image/advertise/popup/1.png',1,'event/1',1,1),(2,'/assets/image/advertise/popup/1.png',1,'event/1',1,1),(3,'/assets/image/advertise/popup/1.png',1,'event/1',1,1);
/*!40000 ALTER TABLE `advertise_for_popup_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authority_tbl`
--

DROP TABLE IF EXISTS `authority_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority_tbl`
--

LOCK TABLES `authority_tbl` WRITE;
/*!40000 ALTER TABLE `authority_tbl` DISABLE KEYS */;
INSERT INTO `authority_tbl` VALUES (1,'cholnh','ROLE_ADMIN'),(2,'staff','ROLE_ADMIN'),(3,'test','ROLE_USER'),(4,'test_make','ROLE_USER');
/*!40000 ALTER TABLE `authority_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_tbl`
--

DROP TABLE IF EXISTS `cart_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `customer_idx` int(11) DEFAULT NULL,
  `guest_idx` int(11) DEFAULT NULL,
  `detail_site_idx` int(11) DEFAULT NULL,
  `arrival_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_cart_customer_idx` (`customer_idx`),
  KEY `fk_cart_dsite_idx` (`detail_site_idx`),
  KEY `fk_cart_gst_idx` (`guest_idx`),
  CONSTRAINT `fk_cart_customer` FOREIGN KEY (`customer_idx`) REFERENCES `user_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_cart_dsite` FOREIGN KEY (`detail_site_idx`) REFERENCES `detail_for_delivery_site_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_cart_gst` FOREIGN KEY (`guest_idx`) REFERENCES `guest_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COMMENT='카트 관리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_tbl`
--

LOCK TABLES `cart_tbl` WRITE;
/*!40000 ALTER TABLE `cart_tbl` DISABLE KEYS */;
INSERT INTO `cart_tbl` VALUES (39,NULL,35,1,'2019-05-08 08:00:00'),(40,NULL,27,1,'2019-05-09 03:00:00'),(42,13,NULL,1,'2019-05-10 03:00:00'),(43,NULL,29,1,'2019-05-11 03:00:00'),(45,NULL,42,2,'2019-05-11 03:00:00'),(46,NULL,19,1,'2019-05-11 08:00:00');
/*!40000 ALTER TABLE `cart_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientdetails`
--

DROP TABLE IF EXISTS `clientdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientdetails` (
  `appId` varchar(255) NOT NULL,
  `resourceIds` varchar(255) DEFAULT NULL,
  `appSecret` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `grantTypes` varchar(255) DEFAULT NULL,
  `redirectUrl` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additionalInformation` varchar(4096) DEFAULT NULL,
  `autoApproveScopes` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientdetails`
--

LOCK TABLES `clientdetails` WRITE;
/*!40000 ALTER TABLE `clientdetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `clientdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_for_all_tbl`
--

DROP TABLE IF EXISTS `comment_for_all_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment_for_all_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `delivery_site_idx` int(11) DEFAULT NULL,
  `store_idx` int(11) DEFAULT NULL,
  `customer_idx` int(11) DEFAULT NULL,
  `register_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `cnt_like` int(11) DEFAULT '0' COMMENT '좋아요 개수',
  `cnt_unlike` int(11) DEFAULT '0' COMMENT '싫어요 개수',
  `cnt_view` int(11) DEFAULT '0',
  `title` varchar(200) NOT NULL COMMENT '제목',
  `contents` text COMMENT '내용',
  `state_active` tinyint(4) DEFAULT '1' COMMENT '후기 상태\n0 - 비활성화 (신고 등으로 인한 비활성화)\n1 - 활성화 (기본)',
  `state_anonymous` tinyint(4) DEFAULT '0' COMMENT '익명 여부\n0 - 실명\n1 - 익명',
  PRIMARY KEY (`idx`),
  KEY `comment_store_fk_idx` (`store_idx`),
  KEY `comment_customer_fk_idx` (`customer_idx`),
  KEY `comment_dsite_fk_idx` (`delivery_site_idx`),
  CONSTRAINT `comment_customer_fk` FOREIGN KEY (`customer_idx`) REFERENCES `user_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_dsite_fk` FOREIGN KEY (`delivery_site_idx`) REFERENCES `delivery_site_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_store_fk` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='고객의소리 리뷰 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_for_all_tbl`
--

LOCK TABLES `comment_for_all_tbl` WRITE;
/*!40000 ALTER TABLE `comment_for_all_tbl` DISABLE KEYS */;
INSERT INTO `comment_for_all_tbl` VALUES (1,1,1,1,'2019-02-20 14:56:27','2019-05-10 03:26:17',43,1,56665,'포만감 솔직 후기','포만감 솔직 후기 작성! 각 대학교에서 사용해 본 포만감 실제 후기',1,0),(2,1,1,1,'2019-02-20 15:16:21','2019-05-10 04:20:00',23,3,34412,'후성식당 맛 평가','군산에 위치한 후성식당의 메뉴를 살펴보았다. ',1,1),(3,1,1,1,'2019-05-07 01:45:32','2019-05-10 03:26:17',12,3,1233,'포만감에서 점심해결','포만감 사용법에 대하여 완벽 정리',1,0);
/*!40000 ALTER TABLE `comment_for_all_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_for_store_tbl`
--

DROP TABLE IF EXISTS `comment_for_store_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment_for_store_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `store_idx` int(11) DEFAULT NULL,
  `customer_idx` int(11) DEFAULT NULL,
  `cnt_star` float DEFAULT '0',
  `cnt_like` int(11) DEFAULT '0',
  `contents` varchar(45) NOT NULL,
  `register_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `state_active` tinyint(4) DEFAULT '1' COMMENT '후기 상태\n0 - 비활성화 (신고 등으로 인한 비활성화)\n1 - 활성화 (기본)',
  `state_anonymous` tinyint(4) DEFAULT '0' COMMENT '익명 여부\n0 - 실명\n1 - 익명',
  PRIMARY KEY (`idx`),
  KEY `fk_cs_store_idx` (`store_idx`),
  KEY `fk_cs_cust_idx` (`customer_idx`),
  CONSTRAINT `fk_cs_cust` FOREIGN KEY (`customer_idx`) REFERENCES `user_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_cs_store` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='5마디 리뷰 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_for_store_tbl`
--

LOCK TABLES `comment_for_store_tbl` WRITE;
/*!40000 ALTER TABLE `comment_for_store_tbl` DISABLE KEYS */;
INSERT INTO `comment_for_store_tbl` VALUES (9,1,1,5,0,'존맛탱구리','2019-04-28 21:38:53','2019-05-05 19:08:57',1,0),(10,1,2,3,0,'너무맛없음','2019-04-28 22:23:53','2019-05-05 19:02:49',1,0),(11,1,3,3.5,0,'이딴거왜함','2019-04-28 22:18:53','2019-05-05 19:02:49',1,0),(12,1,12,4,0,'밥이나먹자','2019-04-27 22:38:53','2019-05-05 19:02:49',1,0),(13,1,1,5,0,'존맛탱구리','2019-04-25 22:38:53','2019-05-05 19:02:49',1,0),(14,1,2,3,0,'너무맛없음','2019-03-28 22:38:53','2019-05-05 19:02:49',1,0),(15,1,3,3,0,'이딴거왜함','2019-04-01 22:38:53','2019-05-05 19:02:49',1,0),(16,1,12,4,0,'밥이나먹자','2019-04-18 22:38:53','2019-05-05 19:02:49',1,0),(17,1,1,5,0,'존맛탱구리','2019-04-28 22:38:53','2019-05-05 19:02:49',1,0),(18,1,2,3,0,'너무맛없음','2019-04-28 22:38:53','2019-05-05 19:02:49',1,0),(19,1,3,3,0,'이딴거왜함','2019-04-28 22:38:53','2019-05-05 19:02:49',1,0),(20,1,12,4,0,'밥이나먹자','2019-04-28 22:38:53','2019-05-05 19:02:49',1,0),(21,1,1,5,0,'존맛탱구리','2019-04-28 22:38:53','2019-05-05 19:02:49',1,0),(22,1,2,3,0,'너무맛없음','2019-04-28 22:38:53','2019-05-05 19:02:49',1,0),(23,1,3,3,0,'이딴거왜함','2019-04-28 22:38:53','2019-05-05 19:02:49',1,0),(24,1,12,4,0,'밥이나먹자','2019-04-28 22:38:53','2019-05-05 19:02:49',1,0),(25,1,1,5,0,'존맛탱구리','2019-04-28 22:38:53','2019-05-05 19:02:49',1,0),(26,1,2,3,0,'너무맛없음','2019-04-28 22:38:53','2019-05-05 19:02:49',1,0),(27,1,3,3,0,'이딴거왜함','2019-04-28 22:38:53','2019-05-05 19:02:49',1,0),(28,1,12,4,0,'밥이나먹자','2019-04-28 22:38:53','2019-05-05 19:02:49',1,0),(29,1,1,3.5,0,'리뷰리뷰리','2019-04-29 02:39:02','2019-04-29 02:40:27',1,1),(30,3,13,5,1,'ㅅㄷㄴㅅㄴ','2019-05-05 10:50:04','2019-05-05 19:03:04',1,0),(31,1,13,5,0,'익명입니다','2019-05-05 11:28:48',NULL,1,0);
/*!40000 ALTER TABLE `comment_for_store_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `common_map_tbl`
--

DROP TABLE IF EXISTS `common_map_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `common_map_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(100) DEFAULT NULL,
  `value` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='공통 매핑 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `common_map_tbl`
--

LOCK TABLES `common_map_tbl` WRITE;
/*!40000 ALTER TABLE `common_map_tbl` DISABLE KEYS */;
INSERT INTO `common_map_tbl` VALUES (2,'splash-background','c:/resources/images/splash/splash-background.png'),(3,'splash-logo','c:/resources/images/splash/splash-logo.png'),(4,'splash-title','c:/resources/images/splash/splash-title.png'),(5,'company-name-kr','ⓒ 미스터포터'),(6,'service-name-kr','포만감'),(7,'company-name-en','ⓒ MR.PORTER'),(8,'service-name-en','POMANGAM'),(9,'point-saving-pct-1','5'),(10,'point-saving-prc-1','0'),(11,'company-number','5460500933');
/*!40000 ALTER TABLE `common_map_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cost_tbl`
--

DROP TABLE IF EXISTS `cost_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cost_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `product_idx` int(11) DEFAULT NULL,
  `unit_cost` int(11) NOT NULL COMMENT '가격 (원가에 해당)',
  `s_commission_prc` int(11) DEFAULT '0' COMMENT '업체로 부터 받는 수수료 가격',
  `s_commission_pct` smallint(6) DEFAULT '0' COMMENT '업체로 부터 받는 수수료 퍼센트',
  `c_commission_prc` int(11) DEFAULT '0' COMMENT '고객으로 부터 받는 수수료 가격',
  `c_commission_pct` smallint(6) DEFAULT '0' COMMENT '고객으로 부터 받는 수수료 퍼센트',
  PRIMARY KEY (`idx`),
  KEY `cost_prod_fk_idx` (`product_idx`),
  CONSTRAINT `cost_product_fk` FOREIGN KEY (`product_idx`) REFERENCES `product_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=361 DEFAULT CHARSET=utf8 COMMENT='product의 가격에 대한 상세 정보가 기입되는 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cost_tbl`
--

LOCK TABLES `cost_tbl` WRITE;
/*!40000 ALTER TABLE `cost_tbl` DISABLE KEYS */;
INSERT INTO `cost_tbl` VALUES (19,21,6100,500,0,1000,0),(20,22,6100,500,0,1000,0),(21,23,5600,500,0,1000,0),(22,24,5800,500,0,1000,0),(23,25,6000,500,0,1000,0),(24,26,6500,500,0,1000,0),(25,27,6000,500,0,1000,0),(26,28,700,500,0,1000,0),(27,29,6400,500,0,1000,0),(28,30,6200,500,0,1000,0),(29,31,5400,500,0,1000,0),(30,32,5800,500,0,1000,0),(31,33,6100,500,0,1000,0),(32,34,5400,500,0,1000,0),(33,35,5200,500,0,1000,0),(34,36,6100,500,0,1000,0),(35,37,6100,500,0,1000,0),(36,38,5100,500,0,1000,0),(37,39,5100,500,0,1000,0),(38,40,4200,500,0,1000,0),(39,41,4000,500,0,1000,0),(40,42,3200,500,0,1000,0),(41,43,3600,500,0,1000,0),(42,44,3900,500,0,1000,0),(43,45,3200,500,0,1000,0),(44,46,3000,500,0,1000,0),(45,47,3900,500,0,1000,0),(46,48,4200,500,0,1000,0),(47,49,2900,500,0,1000,0),(48,50,2900,500,0,1000,0),(49,51,4100,500,0,1000,0),(50,52,3400,500,0,1000,0),(51,53,3600,500,0,1000,0),(52,54,3800,500,0,1000,0),(53,55,4500,500,0,1000,0),(54,56,4000,500,0,1000,0),(55,57,6900,500,0,1000,0),(56,58,4900,500,0,1000,0),(57,59,11200,700,0,1600,0),(58,60,16800,900,0,2200,0),(59,61,22400,1100,0,2800,0),(60,62,28000,1300,0,2800,0),(61,63,5100,500,0,1000,0),(62,64,7000,500,0,1000,0),(63,65,2900,150,0,1000,0),(64,66,3500,150,0,1000,0),(65,67,4200,150,0,1000,0),(66,68,3700,150,0,1000,0),(67,69,3900,150,0,1000,0),(68,70,4400,150,0,1000,0),(69,71,5000,150,0,1000,0),(70,72,5200,150,0,1000,0),(71,73,3900,150,0,1000,0),(72,74,4500,150,0,1000,0),(73,75,3500,150,0,1000,0),(74,76,3400,150,0,1000,0),(75,77,3400,150,0,1000,0),(76,78,2900,150,0,1000,0),(77,79,4300,150,0,1000,0),(78,80,2800,150,0,1000,0),(79,81,4200,150,0,1000,0),(80,82,3900,150,0,1000,0),(81,83,3600,150,0,1000,0),(82,84,4500,150,0,1000,0),(83,85,4500,150,0,1000,0),(84,86,4300,150,0,1000,0),(85,87,5500,150,0,1000,0),(86,88,6000,150,0,1000,0),(87,89,3900,150,0,1000,0),(88,90,4000,150,0,1000,0),(89,91,3800,150,0,1000,0),(90,92,4700,150,0,1000,0),(91,93,3900,150,0,1000,0),(92,94,4800,150,0,1000,0),(93,95,4800,150,0,1000,0),(94,96,5700,150,0,1000,0),(95,97,3800,150,0,1000,0),(96,98,3200,150,0,1000,0),(97,99,10000,150,0,1000,0),(98,100,8000,150,0,1000,0),(99,101,7000,150,0,1000,0),(100,102,6000,150,0,1000,0),(101,103,5800,150,0,1000,0),(102,104,6000,150,0,1000,0),(103,105,4500,150,0,1000,0),(104,106,4300,150,0,1000,0),(105,107,7500,150,0,1000,0),(106,108,2800,150,0,1000,0),(107,109,10000,150,0,1000,0),(108,110,5000,150,0,1000,0),(109,111,2500,150,0,1000,0),(110,112,2800,150,0,1000,0),(111,113,1800,150,0,1000,0),(112,114,1500,150,0,1000,0),(113,115,10000,150,0,1000,0),(114,116,8000,150,0,1000,0),(115,117,4700,150,0,1000,0),(116,118,4000,150,0,1000,0),(117,119,6000,200,0,1300,0),(118,120,7000,200,0,1300,0),(119,121,8000,200,0,1300,0),(120,122,8000,200,0,1300,0),(121,123,8000,200,0,1300,0),(122,124,8000,200,0,1300,0),(123,125,8000,200,0,1300,0),(124,126,9000,200,0,1300,0),(125,127,10000,200,0,1300,0),(126,128,10000,200,0,1300,0),(127,129,10000,200,0,1300,0),(128,130,11000,200,0,1300,0),(129,131,11000,200,0,1300,0),(130,132,12000,200,0,1300,0),(131,133,12000,200,0,1300,0),(132,134,12000,200,0,1300,0),(133,135,12000,200,0,1300,0),(134,136,12000,200,0,1300,0),(135,137,12000,200,0,1300,0),(136,138,12000,200,0,1300,0),(137,139,12000,200,0,1300,0),(138,140,10000,200,0,1300,0),(139,141,4900,0,0,0,0),(140,142,3500,0,0,0,0),(141,143,4000,0,0,0,0),(142,144,3000,0,0,0,0),(143,145,100,0,0,0,0),(144,146,100,0,0,0,0),(145,147,100,0,0,0,0),(146,148,100,0,0,0,0),(147,149,300,0,0,0,0),(148,150,500,0,0,0,0),(149,151,500,0,0,0,0),(150,152,500,0,0,0,0),(151,153,1500,0,0,0,0),(152,154,1000,0,0,0,0),(153,155,2000,0,0,0,0),(154,156,2000,0,0,0,0),(155,157,2000,0,0,0,0),(156,158,3000,0,0,0,0),(157,159,1500,0,0,0,0),(158,160,2000,0,0,0,0),(159,161,1500,0,0,0,0),(160,162,2000,0,0,0,0),(161,163,10000,200,0,1300,0),(162,164,8000,0,0,1500,0),(163,165,14000,0,0,1500,0),(164,166,8000,0,0,1500,0),(165,167,14000,0,0,1500,0),(166,168,8500,0,0,1500,0),(167,169,15000,0,0,1500,0),(168,170,14500,0,0,1500,0),(169,171,500,0,0,0,0),(170,172,1000,0,0,0,0),(171,173,1000,0,0,0,0),(172,174,1000,0,0,0,0),(173,175,2000,0,0,0,0),(174,176,1000,0,0,0,0),(175,177,2000,0,0,0,0),(176,178,7000,0,0,1500,0),(177,179,14000,0,0,3000,0),(178,180,16000,0,0,3000,0),(179,181,8000,1000,0,500,0),(180,182,8500,1000,0,500,0),(181,183,9000,1000,0,500,0),(182,184,9500,1000,0,500,0),(183,185,12500,1000,0,500,0),(184,186,11500,1000,0,500,0),(185,187,9500,1000,0,500,0),(186,188,9500,1000,0,500,0),(187,189,9500,1000,0,500,0),(188,190,7000,1000,0,500,0),(189,191,7000,1000,0,500,0),(190,192,8500,1000,0,500,0),(191,193,7000,1000,0,500,0),(192,194,8000,1000,0,500,0),(193,195,9000,1000,0,500,0),(194,196,10000,1000,0,500,0),(195,197,6000,1000,0,500,0),(196,198,6500,1000,0,500,0),(197,199,7000,1000,0,500,0),(198,200,7500,1000,0,500,0),(199,201,8000,1000,0,500,0),(200,202,8000,1000,0,500,0),(201,203,8500,1000,0,500,0),(202,204,8500,1000,0,500,0),(203,205,8000,1000,0,500,0),(204,206,8500,1000,0,500,0),(205,207,6000,1000,0,500,0),(206,208,6000,1000,0,500,0),(207,209,7500,1000,0,500,0),(208,210,7500,1000,0,500,0),(209,211,7500,1000,0,500,0),(210,212,8500,1000,0,500,0),(211,213,7500,1000,0,500,0),(212,214,9000,1000,0,500,0),(213,215,9500,1000,0,500,0),(214,216,5000,1000,0,500,0),(215,217,7000,1000,0,500,0),(216,218,8000,1000,0,500,0),(217,219,8500,1000,0,500,0),(218,220,8500,1000,0,500,0),(219,221,8500,1000,0,500,0),(220,222,2500,1000,0,500,0),(221,223,2500,1000,0,500,0),(222,224,1000,1000,0,500,0),(223,225,1500,1000,0,500,0),(224,226,1500,1000,0,500,0),(225,227,2000,1000,0,500,0),(226,228,2500,1000,0,500,0),(227,229,7500,1000,0,500,0),(228,230,18000,1500,0,0,0),(229,231,17000,1500,0,0,0),(230,232,17000,1500,0,0,0),(231,233,17000,1500,0,0,0),(232,234,15000,1500,0,0,0),(233,235,17000,1500,0,0,0),(234,236,21000,1500,0,0,0),(235,237,19900,1500,0,0,0),(236,238,19900,1500,0,0,0),(237,239,19900,1500,0,0,0),(238,240,19000,1500,0,0,0),(239,241,19000,1500,0,0,0),(240,242,17000,1500,0,0,0),(241,243,19900,1500,0,0,0),(242,244,19000,1500,0,0,0),(243,245,19000,1500,0,0,0),(244,246,17000,1500,0,0,0),(245,247,19900,1500,0,0,0),(246,248,19000,1500,0,0,0),(247,249,19000,1500,0,0,0),(248,250,19900,1500,0,0,0),(249,251,18000,1500,0,0,0),(250,252,17000,1500,0,0,0),(251,253,17000,1500,0,0,0),(252,254,19000,1500,0,0,0),(253,255,21900,1500,0,0,0),(254,256,21000,1500,0,0,0),(255,257,18500,1500,0,0,0),(256,258,21400,1500,0,0,0),(257,259,20500,1500,0,0,0),(258,260,20500,1500,0,0,0),(259,261,17000,1500,0,0,0),(260,262,19900,1500,0,0,0),(261,263,19000,1500,0,0,0),(262,264,19900,1500,0,0,0),(263,265,17000,1500,0,0,0),(264,266,19900,1500,0,0,0),(265,267,19000,1500,0,0,0),(266,268,19000,1500,0,0,0),(267,269,17000,1500,0,0,0),(268,270,19900,1500,0,0,0),(269,271,19000,1500,0,0,0),(270,272,19000,1500,0,0,0),(271,273,17000,1500,0,0,0),(272,274,19900,1500,0,0,0),(273,275,19000,1500,0,0,0),(274,276,19000,1500,0,0,0),(275,277,17000,1500,0,0,0),(276,278,18000,1500,0,0,0),(277,279,18000,1500,0,0,0),(278,280,26000,1500,0,0,0),(279,281,9000,1500,0,0,0),(280,282,8500,1500,0,0,0),(281,283,8500,1500,0,0,0),(282,284,7500,1500,0,0,0),(283,285,8500,1500,0,0,0),(284,286,8500,1500,0,0,0),(285,287,8500,1500,0,0,0),(286,288,8500,1500,0,0,0),(287,289,13400,1500,0,0,0),(288,290,13400,1500,0,0,0),(289,291,13400,1500,0,0,0),(290,292,27000,1500,0,0,0),(291,293,29000,1500,0,0,0),(292,294,29000,1500,0,0,0),(293,295,12000,0,0,1500,0),(294,296,13000,0,0,1500,0),(295,297,13500,0,0,1500,0),(296,298,15000,0,0,1500,0),(297,299,20500,0,0,1500,0),(298,300,22500,0,0,1500,0),(299,301,3000,0,0,800,0),(300,302,4000,0,0,800,0),(301,303,5000,0,0,800,0),(302,304,1500,0,0,800,0),(303,305,1500,0,0,800,0),(304,306,1500,0,0,800,0),(305,307,1500,0,0,800,0),(306,308,2000,0,0,800,0),(307,309,2500,0,0,800,0),(308,310,2000,0,0,800,0),(309,311,2000,0,0,800,0),(310,312,2500,0,0,800,0),(311,313,2500,0,0,800,0),(312,314,2500,0,0,800,0),(313,315,3500,0,0,800,0),(314,316,3500,0,0,800,0),(315,317,2500,0,0,800,0),(316,318,3500,0,0,800,0),(317,319,5000,0,0,800,0),(318,320,6000,500,0,1000,0),(319,321,7500,500,0,1000,0),(320,322,7500,500,0,1000,0),(321,323,7500,500,0,1000,0),(322,324,2000,0,0,800,0),(323,325,1500,0,0,1300,0),(324,326,1500,0,0,1300,0),(325,327,2000,0,0,1300,0),(326,328,2500,0,0,1300,0),(327,329,3000,0,0,1300,0),(328,330,3000,0,0,1300,0),(329,331,3000,0,0,1300,0),(330,332,3000,0,0,1300,0),(331,333,2500,0,0,1300,0),(332,334,3800,0,0,1300,0),(333,335,4000,0,0,1300,0),(334,336,3000,0,0,1300,0),(335,337,3500,0,0,1300,0),(336,338,3000,0,0,1300,0),(337,339,3000,0,0,1300,0),(338,340,2500,0,0,1300,0),(339,341,2000,0,0,1300,0),(340,342,3500,0,0,1300,0),(341,343,2500,0,0,1300,0),(342,344,3000,0,0,1300,0),(343,345,3500,0,0,1300,0),(344,346,4000,0,0,1300,0),(345,347,1500,0,0,1300,0),(346,348,2500,0,0,1300,0),(347,349,3000,0,0,1300,0),(348,350,2000,0,0,1300,0),(349,351,2500,0,0,1300,0),(350,352,1500,0,0,1300,0),(351,353,1500,0,0,1300,0),(352,354,4500,0,0,1300,0),(353,355,3000,0,0,1300,0),(354,356,3000,0,0,1300,0),(355,357,3000,0,0,1300,0),(356,358,2800,0,0,1300,0),(357,359,3000,0,0,2400,0),(358,360,4500,0,0,2800,0),(359,361,6000,0,0,3200,0),(360,362,7500,0,0,3200,0);
/*!40000 ALTER TABLE `cost_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `count_search_delivery_site_tbl`
--

DROP TABLE IF EXISTS `count_search_delivery_site_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `count_search_delivery_site_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `delivery_site_idx` int(11) DEFAULT NULL,
  `count` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='delivery site 검색 횟수';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `count_search_delivery_site_tbl`
--

LOCK TABLES `count_search_delivery_site_tbl` WRITE;
/*!40000 ALTER TABLE `count_search_delivery_site_tbl` DISABLE KEYS */;
INSERT INTO `count_search_delivery_site_tbl` VALUES (1,402,14),(2,411,13);
/*!40000 ALTER TABLE `count_search_delivery_site_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coupon_tbl`
--

DROP TABLE IF EXISTS `coupon_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coupon_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `issuer_idx` int(11) NOT NULL COMMENT '쿠폰 발급자 직원 인덱스',
  `modifier_idx` int(11) DEFAULT NULL,
  `name` varchar(100) NOT NULL COMMENT '쿠폰 이름',
  `discount_prc` int(11) DEFAULT '0' COMMENT '할인 가격',
  `discount_pct` smallint(6) DEFAULT '0' COMMENT '할인 퍼센테이지',
  `begin_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '쿠폰 시작 날짜\n(기간 0 입력 시 무제한으로 설정)',
  `end_date` timestamp NULL DEFAULT NULL COMMENT '쿠폰 종료 날짜\n(기간 0 입력 시 무제한으로 설정)',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `code` varchar(100) NOT NULL COMMENT '쿠폰 식별 코드',
  `state_active` tinyint(4) NOT NULL COMMENT '쿠폰 상태\n0 - 쿠폰 사용 불가능\n1 - 쿠폰 사용 가능 (지급상태)',
  `customer_idx` int(11) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `fk_cp_emp_idx` (`issuer_idx`),
  KEY `fk_cp_modify_emp_idx` (`modifier_idx`),
  CONSTRAINT `fk_cp_issue_emp` FOREIGN KEY (`issuer_idx`) REFERENCES `employee_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cp_modify_emp` FOREIGN KEY (`modifier_idx`) REFERENCES `employee_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='쿠폰 관련  테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_tbl`
--

LOCK TABLES `coupon_tbl` WRITE;
/*!40000 ALTER TABLE `coupon_tbl` DISABLE KEYS */;
INSERT INTO `coupon_tbl` VALUES (1,1,NULL,'3월 할인 쿠폰',3000,0,'2019-04-16 08:49:54',NULL,'2019-04-16 08:49:54','2019-04-16 19:57:35','TEST-878F-Q3PN',0,2),(2,1,NULL,'3월 할인 쿠폰',3000,0,'2019-04-16 08:49:54',NULL,'2019-04-16 10:33:41','2019-04-16 19:04:45','TEST-878F-Q3PW',1,3),(3,1,NULL,'신 학기 쿠폰',4000,0,'2019-04-16 19:04:20',NULL,'2019-04-16 19:04:20','2019-04-16 19:04:45','TEST-878F-1234',1,2);
/*!40000 ALTER TABLE `coupon_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_site_tbl`
--

DROP TABLE IF EXISTS `delivery_site_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delivery_site_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '배달지 이름',
  `location` varchar(100) DEFAULT NULL COMMENT '상세 장소 설명',
  `region_category_idx` int(11) DEFAULT NULL,
  `campus` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_dsite_region_idx` (`region_category_idx`),
  CONSTRAINT `fk_dsite_region` FOREIGN KEY (`region_category_idx`) REFERENCES `region_category_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=471 DEFAULT CHARSET=utf8 COMMENT='배달지 테이블\n(기관, 학교 등)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_site_tbl`
--

LOCK TABLES `delivery_site_tbl` WRITE;
/*!40000 ALTER TABLE `delivery_site_tbl` DISABLE KEYS */;
INSERT INTO `delivery_site_tbl` VALUES (1,'항공대학교','화전',2,NULL),(2,'단국대학교','천안',6,NULL),(4,'한국골프대학','강원도 횡성군 하대5길 101 (우천면)',4,'본교'),(5,'한림성심대학교','강원도 춘천시 동면 장학길 48 한림성심대학교',4,'본교'),(6,'강원관광대학교','강원도 태백시 대학길 97 (황지동, 강원관광대학)',4,'본교'),(7,'강원도립대학','강원도 강릉시 연주로 270 (주문진읍, 강원도립대학)',4,'본교'),(8,'동우대학','강원 속초시 도리원길 5 동우대학',4,'본교'),(9,'상지영서대학교','강원도 원주시 상지대길 84',4,'본교'),(10,'세경대학교','강원 영월군 영월읍 하송로 197',4,'본교'),(11,'송곡대학교','강원 춘천시 남산면 송곡대학길 34번지',4,'본교'),(12,'송호대학교','강원도 횡성군 남산로 210 (횡성읍)',4,'본교'),(13,'춘천교육대학교','강원도 춘천시 공지로 126 춘천교육대학교',4,'본교'),(14,'가톨릭관동대학교','강원도 강릉시 범일로 579번길 24 (내곡동)',4,'본교'),(15,'강릉원주대학교','강원도 강릉시 죽헌길 7 (지변동, 강릉원주대학교)',4,'본교'),(16,'강릉원주대학교','강원도 원주시 남원로 150 (흥업면, 강릉원주대학교원주캠퍼스)',4,'원주캠'),(17,'강원대학교','강원 춘천시 강원대학길1(효자동) 강원대학교',4,'본교'),(18,'강원대학교','강원 삼척시 중앙로 346(교동 산 253)',4,'삼척캠'),(19,'경동대학교','강원도 고성군 봉포4길 46 (토성면) ',4,'본교'),(20,'경동대학교','강원 속초시 도리원길 5 (산244번지)',4,'설악캠'),(21,'경동대학교','강원 원주시 문막읍 견훤로 815 (산58-2)',4,'메이칼캠'),(22,'상지대학교','강원도 원주시 상지대길 83번지(우산동)',4,'본교'),(23,'연세대학교','강원도 원주시 연세대길 1',4,'원주캠'),(24,'한라대학교','강원 원주시 흥업면 한라대길 28 ',4,'본교'),(25,'한림대학교','강원 춘천시 한림대학길 1 한림대학교',4,'본교'),(26,'한중대학교','강원도 동해시 지양길 200 (지흥동, 한중대학교)',4,'본교'),(27,'한국폴리텍 III','강원도 강릉시 남산초교길 121 (노암동, 한국폴리텍3대학)',4,'강릉캠'),(28,'한국폴리텍 III','강원도 원주시 북원로2425번길 73 (우산동, 한국폴리텍3대학)',4,'원주캠'),(29,'한국폴리텍 III ','강원 춘천시 동산면 영서로 1290-31',4,'춘천캠'),(30,'경민대학교','경기도 의정부시 서부로 545(가능동) 경민대학교',2,'본교'),(31,'경복대학교','경기도 남양주시 경복대로 425 (진접읍, 경복대학교)',2,'본교'),(32,'김포대학교','경기도 김포시 김포대학로 97 (월곶면)',2,'본교'),(33,'농협대학교','경기 고양시 덕양구 서삼릉길 281 (원당동)',2,'본교'),(34,'대림대학교','경기 안양시 동안구 임곡로 29(비산동) 대림대학교 ',2,'본교'),(35,'동남보건대학교','경기도 수원시 장안구 천천로 74번길 50 ',2,'본교'),(36,'동아방송예술대학교','경기 안성시 삼죽면 동아방송대학교 ',2,'본교'),(37,'동원대학교','경기 광주시 곤지암읍 경충대로 26 동원대학교',2,'본교'),(38,'서울예술대학교','경기도 안산시 단원구 예술대학로 171 (고잔동, 서울예술대학)',2,'본교'),(39,'서정대학교','경기도 양주시 화합로 1049-56 (은현면)',2,'본교'),(40,'수원과학대학교','경기도 화성시 세자로 288 (정남면, 수원과학대학교)',2,'본교'),(41,'신구대학교','경기 성남시 중원구 광명로 377',2,'본교'),(42,'안산대학교','경기도 안산시 상록구 안산대학로 155 (일동, 강의동)',2,'본교'),(43,'연성대학교','경기도 안양시 만안구 양화로37번길 34 연성대학교',2,'본교'),(44,'오산대학교','경기도 오산시 청학로 45 (청학동, 오산대학)',2,'본교'),(45,'용인송담대학교','경기 용인시 처인구 동부로 61 용인송담대학교',2,'본교'),(46,'웅지세무대학교','경기 파주시 탄현면 웅지로 144번길 73',2,'본교'),(47,'청강문화산업대학교','경기도 이천시 청강가창로 389-94 (마장면, 청강문화산업대학)',2,'본교'),(48,'한국복지대학교','경기 평택시 삼남로 283',2,'본교'),(49,'경기과학기술대학교','경기 시흥시 경기과기대로 269 (정왕동 2121-3)',2,'본교'),(50,'경원전문대학','경기 성남시 수정구 복정동 경원전문대학',2,'본교'),(51,'계원예술대학교','경기도 의왕시 계원대학로 66 (내손동, 계원예술대학교)',2,'본교'),(52,'국제대학교','경기 평택시 장안웃길 56번지',2,'본교'),(53,'동서울대학교','경기도 성남시 수정구 복정로 76 (복정동)',2,'본교'),(54,'두원공과대학교','경기 안성시 죽산면 관음당길 51 두원공과대학교',2,'본교'),(55,'부천대학교','경기도 부천시 원미구 신흥로56번길 25 (심곡동, 부천대학)',2,'본교'),(56,'서울보건대학','경기도 성남시 수정구 산성대로 553 (양지동, 을지대학교)',2,'본교'),(57,'수원여자대학교','경기도 수원시 권선구 온정로 72 (오목천동) 인제캠퍼스',2,'본교'),(58,'신안산대학교','경기도 안산시 단원구 신안산대학로 135 (초지동 671)',2,'본교'),(59,'신흥대학교','경기 의정부시 호암로 95번지',2,'본교'),(60,'여주대학교','경기도 여주시 세종로 338 (교동, 여주대학)',2,'본교'),(61,'유한대학교','경기도 부천시 소사구 경인로 590 (괴안동, 유한대학)',2,'본교'),(62,'장안대학교','경기 화성시 봉담읍 삼천병마로 1182 장안대학교',2,'본교'),(63,'한국관광대학교','경기도 이천시 이장로311번길 197-73',2,'본교'),(64,'한국철도대학','경기도 의왕시 철도박물관로 157 (월암동, 한국교통대학교)',2,'본교'),(65,'경인교육대학교','경기 안양시 만안구 삼막로 155',2,'경기캠'),(66,'가천대학교','경기 성남시 수정구 성남대로 1342 (복정동)',2,'본교'),(67,'가톨릭대학교','경기도 부천시 원미구 지봉로 43 (역곡동)',2,'본교'),(68,'강남대학교','경기도 용인시 기흥구 강남로 40 (구갈동, 강남대학교)',2,'본교'),(69,'경기대학교','경기 수원시 영통구 광교산로 154-42 경기대학교',2,'본교'),(70,'경동대학교','경기도 양주시 청담로 95 (고암동)',2,'양주캠'),(71,'단국대학교','경기도 용인시 수지구 죽전로 152 (죽전동, 단국대학교죽전캠퍼스)',2,'본교'),(72,'대진대학교','경기도 포천시 호국로 1007 (선단동, 대진대학교)',2,'본교'),(73,'루터대학교','경기 용인시 기흥구 상갈동 금화로82번길 20 루터대학교',2,'본교'),(74,'명지대학교','경기도 용인시 처인구 명지로 116 (남동, 명지대학교용인캠퍼스)',2,'자연캠'),(75,'서울신학대학교','경기도 부천시 소사구 호현로489번길 52 (소사본동, 서울신학대학교)',2,'본교'),(76,'서울장신대학교','경기 광주시 경안로 145 (경안동 219-1) 서울장신대학교',2,'본교'),(77,'성결대학교','경기 안양시 만안구 성결대학로 53',2,'본교'),(78,'수원가톨릭대학교','경기도 화성시 왕림1길 67 (봉담읍, 수원가톨릭대학교)',2,'본교'),(79,'수원대학교','경기도 화성시 와우안길 17 (봉담읍, 수원대학교)',2,'본교'),(80,'신경대학교','경기 화성시 남양동 신경대학교 ',2,'본교'),(81,'신한대학교 ','경기도 동두천시 벌마들로40번길 30 신한대학교',2,'본교'),(82,'신한대학교 ','경기도 의정부시 호암로 95 (호원동) 신한대학교 ',2,'의정부캠'),(83,'아세아연합신학대학교','경기 양평군 옥천면 경강로 1276 아세아연합신학대학교',2,'본교'),(84,'아주대학교','경기도 수원시 영통구 월드컵로 206 (원천동, 아주대학교)',2,'본교'),(85,'안양대학교','경기 안양시 만안구 삼덕로 37번길 22(안양동)',2,'본교'),(86,'예원예술대학교','경기도 양주시 화합로1134번길 110 (은현면, 예원예술대학교 양주캠퍼스)',2,'양주캠'),(87,'용인대학교','경기 용인시 처인구 용인대학로 134',2,'본교'),(88,'을지대학교','경기도 성남시 수정구 산성대로 553 (양지동, 을지대학교)',2,'성남캠'),(89,'중부대학교','경기도 고양시 덕양구 동헌로 305 (대자동 613-1)',2,'고양캠'),(90,'중앙대학교','경기 안성시 대덕면 서동대로 4726',2,'안성캠'),(91,'중앙승가대학교','경기 김포시 승가로 123 (풍무동)',2,'본교'),(92,'차의과학대학교','경기 포천시 해룡로 120',2,'본교'),(93,'칼빈대학교','경기도 용인시 기흥구 마북로 184 (마북동, 칼빈대학교)',2,'본교'),(94,'평택대학교','경기도 평택시 서동대로 3825 (용이동, 평택대학교)',2,'본교'),(95,'한경대학교','경기 안성시 중앙로 327(석정동)',2,'본교'),(96,'한국산업기술대학교','경기도 시흥시 산기대학로 237 (정왕동, 한국산업기술대학교)',2,'본교'),(98,'한북대학교','경기도 동두천시 벌마들로40번길 30 (상패동, 신한대학교)',2,'본교'),(99,'한세대학교','경기 군포시 한세로 30 (한세대학교)',2,'본교'),(100,'한신대학교','경기도 오산시 한신대길 137 (양산동, 한신대학교)',2,'본교'),(101,'한양대학교','경기도 안산시 상록구 한양대학로 55 (사동, 한양대학교)',2,'ERICA'),(102,'협성대학교','경기도 화성시 최루백로 72 (봉담읍, 협성대학교)',2,'본교'),(103,'한경대학교','경기 안성시 중앙로 327 (석정동)',2,'본교'),(104,'한국산업기술대학교','경기도 시흥시 산기대학로 237 (정왕동, 한국산업기술대학교)',2,'본교'),(105,'구세군사관학교','경기도 과천시 관악산길 216 (중앙동, 구세군사관학교)',2,'본교'),(106,'세계사이버대학','경기 광주시 오포읍 태재로 90',2,'본교'),(107,'삼성전자공과대학교','경기 용인시 기흥구 농서동 산24',2,'본교'),(108,'국제사이버대학교','경기 수원시 팔달구 경수대로 490',2,'본교'),(109,'ICT폴리텍대학','경기도 광주시 순암로 16-26 (역동, ICT폴리텍대학)',2,'본교'),(110,'한국폴리텍 I','경기 성남시 수정구 수정로 398 한국폴리텍대학성남캠퍼스 ',2,'성남캠'),(111,'한국폴리텍 II','경기도 안성시 송원길 41-12 (공도읍, 한국폴리텍여자대학)',2,'안성캠'),(112,'한국폴리텍','경기도 안성시 송원길 41-12 (공도읍, 한국폴리텍여자대학)',2,'안성캠'),(113,'거제대학교','경남 거제시 마전1길 91',13,'본교'),(114,'김해대학교','경상남도 김해시 삼안로112번길 198 (삼방동)',13,'본교'),(115,'마산대학교','경남 창원시 마산회원구 내서읍 함마대로 2640(용담리)',13,'본교'),(116,'연암공업대학','경남 진주시 진주대로 629번길 35',13,'본교'),(117,'진주보건대학교','경남 진주시 의병로 51',13,'본교'),(118,'창원문성대학','경남 창원시 의창구 충혼로 91 창원문성대학',13,'본교'),(119,'경남도립거창대학','경남 거창군 거창읍 거창대학로 72',13,'본교'),(120,'경남도립남해대학','경남 남해군 남해읍 화전로 78번길 30',13,'본교'),(121,'동원과학기술대학교','경남 양산시 명곡로 321',13,'본교'),(122,'창신대학','경남 창원시 마산회원구 팔용로 262 창신대학 ',13,'본교'),(123,'한국승강기대학교','경남 거창군 거창읍 운정1길 120',13,'본교'),(124,'진주교육대학교','경상남도 진주시 진양호로369번길 3 (신안동, 진주교육대학교)',13,'본교'),(125,'가야대학교','경상남도 김해시 삼계로 208 (삼계동)',13,'본교'),(126,'경남과학기술대학교','경남 진주시 동진로33 경남과학기술대학교',13,'본교'),(127,'경남대학교','경상남도 창원시 마산합포구 경남대학로 7 (월영동)',13,'본교'),(128,'경상대학교','경남 진주시 진주대로 501',13,'본교'),(129,'부산장신대학교','경남 김해시 김해대로 1894-68(구산동764번지)',13,'본교'),(130,'영산대학교','경상남도 양산시 주남로 288(주남동)',13,'본교'),(131,'인제대학교','경상남도 김해시 인제로 197 (어방동, 인제대학교)',13,'본교'),(132,'창신대학교','경상남도 창원시 마산회원구 팔용로 262 (합성동, 창신대학교)',13,'본교'),(133,'창원대학교','경남 창원시 의창구 창원대학로 20',13,'본교'),(134,'한국국제대학교','경상남도 진주시 동부로 965 (문산읍, 한국국제대학교)',13,'본교'),(135,'영산대학교(산업대)','경상남도 양산시 주남로 288(주남동)',13,'본교'),(136,'진주산업대학교','경남 진주시 동진로 33 경남과학기술대학교',13,'본교'),(137,'대우조선해양공과대학','경상남도 거제시 거제대로 3370 (아주동, 대우조선해양)',13,'본교'),(138,'삼성중공업공과대학','경상남도 거제시 장평3로 80 (장평동, 삼성중공업(주)거제조선소)',13,'본교'),(139,'한국폴리텍','경상남도 창원시 성산구 외동반림로 51-88 (중앙동, 한국폴리텍Ⅶ대학)',13,'창원캠'),(140,'한국폴리텍','경상남도 사천시 대학길 46 (이금동, 한국폴리텍항공대학)',13,'항공캠'),(141,'가톨릭상지대학교','경북 안동시 상지길 45(율세동 393)',12,'본교'),(142,'경북전문대학교','경북 영주시 대학로 77 경북전문대학교',12,'본교'),(143,'대구미래대학교','경북 경산시 미래로 114 대구미래대학교 ',12,'본교'),(144,'서라벌대학교','경북 경주시 태종로 516 서라벌대학교',12,'본교'),(145,'선린대학교','경상북도 포항시 북구 초곡길36번길 30 (흥해읍, 선린대학)',12,'본교'),(146,'성덕대학교','경상북도 영천시 대학길 105 (신녕면, 성덕대학교)',12,'본교'),(147,'안동과학대학교','경상북도 안동시 서선길 189 (서후면, 안동과학대학교)',12,'본교'),(148,'포항대학교','경상북도 포항시 북구 신덕로 60 (흥해읍, 포항대학교)',12,'본교'),(149,'경북과학대학교','경상북도 칠곡군 지산로 634 (기산면)',12,'본교'),(150,'경북도립대학교','경북 예천군 예천읍 도립대학길 114',12,'본교'),(151,'구미대학교','경북 구미시 야은로 37',12,'본교'),(152,'김천과학대학교','경상북도 김천시 대학로 168 (삼락동)',12,'본교'),(153,'김천대학','경상북도 김천시 대학로 214 (삼락동)',12,'본교'),(154,'대경대학교','경상북도 경산시 단북1길 65 (자인면, 대경대학)',12,'본교'),(155,'문경대학교','경북 문경시 호계면 대학길 161',12,'본교'),(156,'영남외국어대학','경북 경산시 남천면 남천로 780-9 ',12,'본교'),(157,'호산대학교','경북 경산시 하양읍 대경로 105길 19',12,'본교'),(158,'가야대학교','경상북도 고령군 대가야로 1103 (고령읍, 가야대학교)',12,'고령캠'),(159,'경운대학교','경북 구미시 산동면 강동로 730',12,'본교'),(160,'경일대학교','경북 경산시 하양읍 가마실길 50 경일대학교',12,'본교'),(161,'경주대학교','경북 경주시 태종로 188 경주대학교',12,'본교'),(162,'금오공과대학교','경북 구미시 대학로 61 금오공과대학교',12,'본교'),(163,'김천대학교','경상북도 김천시 대학로 214 (삼락동)',12,'본교'),(164,'대구가톨릭대학교','경북 경산시 하양읍 하양로 13-13',12,'본교'),(165,'대구대학교','경북 경산시 진량읍 대구대로 201 대구대학교경산캠퍼스',12,'본교'),(166,'대구예술대학교','경상북도 칠곡군 가산면 다부 거문1길 202',12,'본교'),(167,'대구외국어대학교','경북 경산시 남천면 남천로 730  대구외국어대학교 ',12,'본교'),(168,'대구한의대학교','경상북도 경산시 한의대로 1 (유곡동, 대구한의대학교)',12,'본교'),(169,'대신대학교','경북 경산시 백천동 경청로 222길 33',12,'본교'),(170,'동국대학교','경북 경주시 동대로 123번지 동국대학교 경주캠퍼스',12,'경주캠'),(171,'동양대학교','경상북도 영주시 동양대로 145 동양대학교',12,'본교'),(172,'안동대학교','경북 안동시 경동로 1375번지 안동대학교',12,'본교'),(173,'영남대학교','경상북도 경산시 대학로 280 영남대학교',12,'본교'),(174,'영남신학대학교','경상북도 경산시 봉회1길 26 (진량읍)',12,'본교'),(175,'위덕대학교','경북 경주시 강동면 동해대로 261',12,'본교'),(176,'포항공과대학교','경상북도 포항시 남구 청암로 77 (지곡동, 포항공과대학교)',12,'본교'),(177,'한동대학교','경북 포항시 북구 흥해읍 한동로 558  한동대학교',12,'본교'),(178,'경운대학교','경북 구미시 산동면 강동로 730',12,'본교'),(179,'영남사이버대학교','경북 경산시 남천면 협석리 220-1',12,'본교'),(180,'포스코기술대학','경상북도 포항시 남구 지곡로 120 (지곡동, 포스코인재개발원)',12,'본교'),(181,'대구사이버대학교','경북 경산시 진량읍 대구대로 201 대구사이버대학교',12,'본교'),(182,'한국복지사이버대학','경상북도 경산시 남천로 746-10 (남천면)',12,'본교'),(183,'한국폴리텍 Ⅵ','경상북도 영주시 가흥로 2 (문정동)',12,'영주캠'),(184,'한국폴리텍','경북 구미시 수출대로3길 84',12,'구미캠'),(185,'광주보건대학교','광주광역시 광산구 북문대로419번길 73 (신창동, 광주보건대학)',11,'본교'),(186,'기독간호대학교','광주 남구 백서로70번길 6, 기독간호대학교',11,'본교'),(187,'서영대학교','광주광역시 북구 서강로 1 (운암동, 서영대학교)',11,'본교'),(188,'송원대학','광주 남구 송암로 73',11,'본교'),(189,'조선간호대학교','광주광역시 동구 필문대로 309-2 (서석동, 조선간호대학교)',11,'본교'),(190,'동강대학교','광주광역시 북구 동문대로 50 (두암동)',11,'본교'),(191,'조선이공대학교','광주 동구 필문대로 309-1 조선이공대학교',11,'본교'),(192,'광주교육대학교','광주광역시 북구 필문대로55  광주교육대학교',11,'본교'),(193,'광신대학교','광주광역시 북구 양산택지소로 36 (본촌동, 광신대학교)',11,'본교'),(194,'광주과학기술원','광주광역시 북구 첨단과기로 123 (오룡동, 광주과학기술원)',11,'본교'),(195,'광주대학교','광주광역시 남구 효덕로 277 (진월동)',11,'본교'),(196,'광주여자대학교','광주광역시 광산구 여대길 201 (산정동)',11,'본교'),(197,'남부대학교','광주광역시 광산구 첨단중앙로 23 (월계동, 남부대학교)',11,'본교'),(198,'송원대학교','광주 남구 송암로 73',11,'본교'),(199,'전남대학교','광주광역시 북구 용봉로 77',11,'본교'),(200,'조선대학교','광주광역시 동구 필문대로 309 (서석동, 조선대학교)',11,'본교'),(201,'호남대학교','광주광역시 광산구 어등대로 417 (서봉동, 호남대학교광산캠퍼스)',11,'본교'),(202,'호남신학대학교','광주광역시 남구 제중로 77 (양림동, 호남신학대학교)',11,'본교'),(203,'광주대학교(산업대)','광주광역시 남구 효덕로 277 (진월동)',11,'본교'),(204,'한국폴리텍','광주광역시 북구 하서로 85 ',11,'광주캠'),(205,'계명문화대학교','대구광역시 달서구 달서대로 675 (신당동, 계명문화대학)',14,'본교'),(206,'대구보건대학교','대구 북구 영송로 15(태전동)',14,'본교'),(207,'수성대학교','대구 수성구 달구벌대로 528길 15(만촌동) 수성대학교',14,'본교'),(208,'대구공업대학교','대구광역시 달서구 송현로 205 (본동, 대구공업대학)',14,'본교'),(209,'대구과학대학교','대구 북구 영송로 47번지(태전동390)',14,'본교'),(210,'영남이공대학교','대구광역시 남구 현충로 170(대명동)',14,'본교'),(211,'영진전문대학','대구 북구 복현로 35(복현동 218번지)',14,'본교'),(212,'대구교육대학교','대구광역시 남구 중앙대로 219 (대명동)',14,'본교'),(213,'경북대학교','대구광역시 북구 대학로 80 경북대학교 ',14,'본교'),(214,'계명대학교','대구광역시 달서구 달구벌대로 1095 계명대학교 성서캠퍼스',14,'본교'),(215,'대구경북과학기술원','대구광역시 달성군 테크노중앙대로 333 (현풍면)',14,'본교'),(216,'영진사이버대학','대구광역시 북구 복현로 35 (복현동, 영진전문대학)',14,'본교'),(217,'한국폴리텍','대구광역시 동구 팔공로 222 (봉무동, 1558번지)',14,'섬유패션캠'),(218,'한국폴리텍','대구광역시 서구 국채보상로43길 15 (평리동)',14,'대구캠'),(219,'우송정보대학','대전광역시 동구 동대전로 171 (자양동, 우송정보대학, 우송대 서캠퍼스)',7,'본교'),(220,'대덕대학교','대전광역시 유성구 가정북로 68 대덕대학교',7,'본교'),(221,'대전과학기술대학교','대전 서구 혜천로 100',7,'본교'),(222,'대전보건대학교','대전광역시 동구 충정로 21 (가양동, 대전보건대학)',7,'본교'),(223,'우송공업대학','대전광역시 동구 동대전로 171 (자양동, 우송정보대학, 우송대 서캠퍼스)',7,'본교'),(224,'건양대학교','대전광역시 서구 관저동로 158 (관저동) ',7,'대전메디컬캠'),(225,'대전대학교','대전 동구 대학로 62 (용운동) 대전대학교',7,'본교'),(226,'대전신학대학교','대전시 대덕구 한남로 41번지',7,'본교'),(227,'목원대학교','대전광역시 서구 도안북로 88 (도안동, 목원대학교)',7,'본교'),(228,'배재대학교','대전광역시 서구 배재로 155-40 (도마동)',7,'본교'),(229,'우송대학교','대전광역시 동구 동대전로 171 (자양동, 우송정보대학, 우송대 서캠퍼스)',7,'본교'),(230,'을지대학교','대전광역시 중구 계룡로771번길 77 (목동, 을지대학교)',7,'본교'),(231,'충남대학교','대전 유성구 대학로 99 충남대학교',7,'본교'),(232,'침례신학대학교','대전광역시 유성구 북유성대로 190',7,'본교'),(233,'한국과학기술원','대전 유성구 대학로 291 한국과학기술원 ',7,'본교'),(234,'한남대학교','대전광역시 대덕구 한남로 70 (오정동, 한남대학교)',7,'본교'),(235,'한밭대학교','대전광역시 유성구 동서대로 125 (덕명동) 한밭대학교',7,'본교'),(236,'우송대학교','대전광역시 동구 동대전로 171 (자양동, 우송정보대학, 우송대 서캠퍼스)',7,'본교'),(237,'한밭대학교','대전광역시 유성구 동서대로 125 (덕명동) 한밭대학교',7,'본교'),(238,'대전신학교','대전시 대덕구 한남로 41(오정동)',7,'본교'),(239,'LH토지주택대학교','대전광역시 유성구 엑스포로 539번지길 99',7,'본교'),(240,'건양사이버대학교','대전 서구 관저동로 158',7,'본교'),(241,'한국폴리텍 IV','대전광역시 동구 우암로 352-21 (가양동, 한국폴리텍Ⅳ대학)',7,' 대전캠'),(242,'경남정보대학교','부산광역시 사상구 주례로 45 경남정보대학교',16,'본교'),(243,'동부산대학교','부산광역시 해운대구 운봉길60',16,'본교'),(244,'동주대학교','부산 사하구 사리로55번길 16(괴정동)',16,'본교'),(245,'대동대학교','부산 금정구 동부곡로 27번길 88 대동대학교',16,'본교'),(246,'동의과학대학교','부산광역시 부산진구 양지로 54 (양정동, 동의과학대학)',16,'본교'),(247,'부산경상대학교','부산 연제구 고분로170 부산경상대학교',16,'본교'),(248,'부산과학기술대학교','부산광역시 북구 시랑로132번길 88 (구포동, 부산과학기술대학교)',16,'본교'),(249,'부산여자대학교','부산광역시 부산진구 진남로506번길  부산여자대학교',16,'본교'),(250,'부산예술대학교','부산광역시 남구 못골번영로71번길 74 (대연동, 부산예술대학교)',16,'본교'),(251,'성심외국어대학','부산광역시 해운대구 반송순환로 142(반송동)',16,'본교'),(252,'부산교육대학교','부산광역시 연제구 교대로 24 (거제동, 부산교육대학교)',16,'본교'),(253,'경성대학교','부산광역시 남구 수영로 309(대연동)',16,'본교'),(254,'고신대학교','부산광역시 영도구 와치로 194 (동삼동, 고신대학교)',16,'본교'),(255,'동명대학교','부산광역시 남구 신선로 428 (용당동, 동명대학교)',16,'본교'),(256,'동서대학교','부산광역시 사상구 주례로 47',16,'본교'),(257,'동아대학교','부산광역시 사하구 낙동대로550번길 37 (하단동, 동아대학교)',16,'본교'),(258,'동의대학교','부산 부산진구 엄광로 176 동의대학교',16,'본교'),(259,'부경대학교','부산 남구 용소로45(대연동)',16,'본교'),(260,'부산가톨릭대학교','부산광역시 금정구 오륜대로 57 (부곡동, 부산가톨릭대학교)',16,'본교'),(261,'부산대학교','부산광역시 금정구 부산대학로63번길 2 (장전동)',16,'본교'),(262,'부산외국어대학교','부산광역시 금정구 금샘로 485번길 65',16,'본교'),(263,'신라대학교','부산광역시 사상구 백양대로 700번길 140(괘법동)',16,'본교'),(264,'영산대학교','부산광역시 해운대구 반송순환로 142(반송동)',16,'해운대캠'),(265,'인제대학교 ','부산광역시 부산진구 복지로 75 (개금동, 백병원)',16,'백병원'),(266,'한국해양대학교','부산광역시 영도구 태종로 727 (동삼동, 한국해양대학교)',16,'본교'),(267,'동명정보대학교','부산 남구 용당동 동명정보대학교',16,'본교'),(268,'영산대학교(산업대)','부산광역시 해운대구 반송순환로 142(반송동)',16,'제2캠퍼스'),(269,'부산디지털대학교','부산광역시 사상구 주례로 57 (주례동, 부산디지털대학교)',16,'본교'),(270,'화신사이버대학교','부산광역시 연제구 고분로191번길 1 (연산동)',16,'본교'),(271,'한국폴리텍Ⅶ','부산광역시 북구 만덕대로155번길 99 (덕천동, 한국폴리텍VII대학부산캠퍼스)',16,'부산캠'),(272,'동양미래대학교','서울시 구로구 경인로 445 동양미래대학교 (고척동 62-160)',1,'본교'),(273,'명지전문대학','서울 서대문구 가좌로 134 명지전문대학 ',1,'본교'),(274,'배화여자대학교','서울 종로구 필운대로 1길 34 배화여자대학교',1,'본교'),(275,'삼육보건대학교','서울특별시 동대문구 망우로 82 (휘경동)',1,'본교'),(276,'서울여자간호대학교','서울특별시 서대문구 간호대로 38 (홍제동, 서울여자간호대학)',1,'본교'),(277,'적십자간호대학','서울 종로구 경교장길8',1,'본교'),(278,'서일대학교','서울특별시 중랑구 용마산로 90길 28  서일대학교',1,'본교'),(279,'숭의여자대학교','서울특별시 중구 소파로2길 10(예장동)',1,'본교'),(280,'인덕대학교','서울특별시 노원구 초안산로 12 (월계동, 인덕대학)',1,'본교'),(281,'한양여자대학교','서울특별시 성동구 살곶이길 200 (사근동, 한양여자대학)',1,'본교'),(282,'서울교육대학교','서울 서초구 서초중앙로 96(서초동 1650)',1,'본교'),(283,'가톨릭대학교','서울특별시 서초구 반포대로 222 (반포동, 가톨릭대학교성의교정)',1,'성의교정'),(284,'가톨릭대학교','서울 종로구 창경궁로 296-12(혜화동) ',1,'성신교정'),(285,'감리교신학대학교','서울특별시 서대문구 독립문로 56 (냉천동, 감리교신학대학)',1,'본교'),(286,'건국대학교','서울특별시 광진구 능동로 120 (화양동, 건국대학교)',1,'본교'),(287,'경기대학교','서울 서대문구 경기대로9길 24 경기대학교',1,'서울캠'),(288,'경희대학교','서울특별시 동대문구 경희대로 26 (회기동, 경희대학교)',1,'본교'),(289,'고려대학교','서울특별시 성북구 안암로 145 (안암동5가, 고려대학교안암캠퍼스(인문사회계))',1,'본교'),(290,'광운대학교','서울특별시 노원구 광운로 20 (월계동, 광운대학교)',1,'본교'),(291,'국민대학교','서울 성북구 정릉3동 국민대학교',1,'본교'),(292,'그리스도대학교','서울특별시 강서구 까치산로24길 47 (화곡동, 그리스도대학교)',1,'본교'),(293,'덕성여자대학교','서울특별시 도봉구 삼양로 144길 33',1,'본교'),(294,'동국대학교','서울특별시 중구 필동로1길 30 (장충동2가, 동국대학교)',1,'본교'),(295,'동덕여자대학교','서울특별시 성북구 화랑로13길 60 (상월곡동, 동덕여자대학교)',1,'본교'),(296,'명지대학교','서울특별시 서대문구 거북골로 34 (남가좌동, 명지대학교)',1,'인문캠'),(297,'삼육대학교','서울시 노원구 화랑로 815',1,'본교'),(298,'상명대학교','서울특별시 종로구 홍지문 2길 20 (홍지동, 상명대학교)',1,'본교'),(299,'서강대학교','서울특별시 마포구 백범로 35 (신수동, 서강대학교)',1,'본교'),(300,'서경대학교','서울 성북구 서경로 124',1,'본교'),(301,'서울과학기술대학교','서울 노원구 공릉로 232 (공릉동 172)',1,'본교'),(302,'서울기독대학교','서울 은평구 갈현로 4길 26-2호',1,'본교'),(303,'서울대학교','서울 관악구 관악로 1 서울대학교',1,'본교'),(304,'서울시립대학교','서울 동대문구 서울시립대로 163 (전농동 90번지) ',1,'본교'),(305,'서울여자대학교','서울특별시 노원구 화랑로 621 서울여자대학교',1,'본교'),(306,'성공회대학교','서울특별시 구로구 연동로 320 (항동, 성공회대학교)',1,'본교'),(307,'성균관대학교','서울특별시 종로구 성균관로 25-2 (명륜3가, 성균관대학교)',1,'본교'),(308,'성신여자대학교','서울특별시 성북구 보문로34다길 2 (돈암동, 성신여자대학교)',1,'본교'),(309,'세종대학교','서울특별시 광진구 능동로 209 (군자동, 세종대학교)',1,'본교'),(310,'숙명여자대학교','서울특별시 용산구 청파로47길 100 (청파동2가, 숙명여자대학교)',1,'본교'),(311,'숭실대학교','서울특별시 동작구 상도로 369 (상도동, 숭실대학교)',1,'본교'),(312,'연세대학교','서울특별시 서대문구 연세로 50 (신촌동, 연세대학교)',1,'본교'),(313,'이화여자대학교','서울특별시 서대문구 이화여대길 52 이화여자대학교 ',1,'본교'),(314,'장로회신학대학교','서울 광진구 광장로 5길 25-1(광장동) 장로회신학대학교',1,'본교'),(315,'중앙대학교','서울 동작구 흑석로 84',1,'서울캠'),(316,'총신대학교','서울특별시 동작구 사당로 143 (사당동, 대한예수교장로회총회신학원)',1,'본교'),(317,'추계예술대학교','서울특별시 서대문구 북아현로11가길 7 (북아현동, 추계예술대학교)',1,'본교'),(318,'한국성서대학교','서울특별시 노원구 동일로214길 32 (상계동, 한국성서대학교)',1,'본교'),(319,'한국외국어대학교','서울 동대문구 이문로 107',1,'본교'),(320,'한국체육대학교','서울 송파구 오륜동 양재대로 1239 한국체육대학교',1,'본교'),(321,'한성대학교','서울특별시 성북구 삼선교로16길 116 (삼선동2가, 한성대학교)',1,'본교'),(322,'한양대학교','서울특별시 성동구 왕십리로 222 ',1,'본교'),(323,'한영신학대학교','서울 구로구 경인로 290-42  한영신학대학교',1,'본교'),(324,'홍익대학교','서울특별시 마포구 와우산로 94(상수동)',1,'본교'),(325,'한국방송통신대학교','서울특별시 종로구 대학로 86 (동숭동, 한국방송통신대학교)',1,'본교'),(326,'서울과학기술대학교','서울시 노원구 공릉로 232 서울과학기술대학교',1,'본교'),(327,'정석대학','서울 강서구 오정로576(공항동)',1,'본교'),(328,'한국예술종합학교','서울 성북구 석관동 ',1,'본교'),(329,'SPC식품과학대학','서울특별시 동작구 신대방16다길 14 (신대방동)',1,'본교'),(330,'KDB금융대학교','서울 영등포구 은행로 14 ',1,'본교'),(331,'경희사이버대학교','서울특별시 동대문구 경희대로 26 (회기동, 경희사이버대학교)',1,'본교'),(332,'고려사이버대학교','서울특별시 종로구 북촌로 106 (계동, 중앙빌딩)',1,'본교'),(333,'디지털서울문화예술대학교','서울시 서대문구 통일로 37길 60 (홍제동 318-18)',1,'본교'),(334,'사이버한국외국어대학교','서울특별시 동대문구 이문로 107 (이문동, 한국외국어대학교) 사이버관',1,'본교'),(335,'서울디지털대학교','서울특별시 마포구 독막로 320 (도화동, 마포 태영 데시앙)',1,'본교'),(336,'서울사이버대학교','서울 강북구 솔매로49길 60(미아동 193-15) 서울사이버대학교',1,'본교'),(337,'세종사이버대학교 ','서울특별시 광진구 군자로 121 (군자동, 세종사이버대학)',1,'본교'),(338,'숭실사이버대학교','서울 종로구 삼일대로 30길 ',1,'본교'),(339,'열린사이버대학교','서울 종로구 삼일대로 461 102동 3층',1,'본교'),(340,'한양사이버대학교','서울 성동구 왕십리로 222',1,'본교'),(341,'국제예술대학','서울특별시 강남구 도산대로30길 47 (논현동, 국제예술대학)',1,'본교'),(342,'백석예술대학교','서울 서초구 방배로9길 23 ',1,'본교'),(343,'정화예술대학','서울특별시 중구 퇴계로16길 21 (남산동1가, 정화예술대학 명동캠퍼스)',1,'본교'),(344,'정화예술대학','서울특별시 중구 퇴계로8길 73 (회현동1가, 남산빌딩(정화예술대학 남산캠퍼스))',1,'남산캠'),(345,'한국폴리텍 I','서울특별시 강서구 우장산로10길 112 (화곡동, 한국폴리텍Ⅰ서울강서대학)',1,'강서캠'),(346,'한국폴리텍Ⅰ','서울 용산구 보광로 73(보광동 238)',1,'정수캠'),(347,'한국영상대학교','세종특별자치시 장군면 대학길 300 한국영상대학교',8,'본교'),(348,'고려대학교','세종특별자치시  세종로 2511 (조치원읍)',8,'세종캠'),(349,'대전가톨릭대학교','세종특별자치시 전의면 가톨릭대학로 30',8,'본교'),(350,'홍익대학교','세종특별자치시 조치원읍 세종로 2639 (신안리 300)',8,'세종캠'),(351,'울산과학대학교','울산 동구 봉수로 101(화정동 산160-1)번지',15,'본교'),(352,'춘해보건대학교','울산 울주군 웅촌면 대학길 9 춘해보건대학교',15,'본교'),(353,'울산과학기술대학교','울산 울주군 언양읍 반연리 유니스트길 50',15,'본교'),(354,'울산대학교','울산광역시 남구 대학로 93 (무거동, 울산대학교)',15,'본교'),(355,'현대중공업공과대학','울산광역시 동구 방어진순환도로 1000 (전하동, 현대중공업)',15,'본교'),(356,'한국폴리텍 VII','울산광역시 중구 산전길 155 (동동, 한국폴리텍7대학울산캠퍼스)',15,'울산캠'),(357,'경인여자대학교','인천광역시 계양구 계양산로 63 (계산동, 경인여자대학)',3,'본교'),(358,'인하공업전문대학','인천광역시 남구 인하로 100 (용현동)',3,'본교'),(359,'가천길대학','인천 연수구 함박뫼로 191 ',3,'본교'),(360,'인천재능대학교','인천 동구 재능로 178(송림동 122번지) 인천재능대학교',3,'본교'),(361,'인천전문대학','인천 남구 석정로 165(도화동 193-6)',3,'본교'),(362,'경인교육대학교','인천 계양구 계산로 62',3,'본교'),(363,'안양대학교','인천광역시 강화군 불은면 중앙로 602-14',3,'강화캠'),(364,'인천가톨릭대학교','인천광역시 강화군 양도면 고려왕릉로 53  ',3,'본교'),(365,'인천가톨릭대학교','인천광역시 연수구 해송로 12',3,'송도국제캠'),(366,'인천대학교','인천광역시 연수구 아카데미로 119 (송도동, 인천대학교)',3,'본교'),(367,'인하대학교','인천광역시 남구 인하로 100 (용현동, 인하대학교)',3,'본교'),(368,'한국폴리텍','인천 부평구 무네미로 448번길 56',3,'인천캠'),(369,'고구려대학교','전라남도 나주시 백호로 125 (다시면, 고구려대학)',10,'본교'),(370,'광양보건대학교','전라남도 광양시 한려대길 111 (광양읍, 광양보건대학)',10,'본교'),(371,'동아인재대학교','전남 영암군 학산면  영산로 76-57',10,'본교'),(372,'목포과학대학교','전남 목포시 영산로 413-1 (상동525)',10,'본교'),(373,'청암대학교','전남 순천시 녹색로 1641번지',10,'본교'),(374,'순천제일대학교','전남 순천시 제일대학길 17',10,'본교'),(375,'전남과학대학교','전남 곡성군 옥과면 대학로 113',10,'본교'),(376,'전남도립대학교','전남 담양군 담양읍 죽녹원로 152',10,'본교'),(377,'한영대학','전남 여수시 장군산길 18-43  한영대학',10,'본교'),(378,'광주가톨릭대학교','전남 나주시 남평읍 중남길 12-25 광주가톨릭대학교',10,'본교'),(379,'동신대학교','전남 나주시 건재로 185 동신대학교',10,'본교'),(380,'목포가톨릭대학교','전남 목포시 영산로 697 목포가톨릭대학교',10,'본교'),(381,'목포대학교','전남 무안군 청계면 영산로 1666  목포대학교',10,'본교'),(382,'목포해양대학교','전라남도 목포시 해양대학로 91 목포해양대학교',10,'본교'),(383,'세한대학교','전라남도 영암군 녹색로 1113 (삼호읍, 세한대학교)',10,'본교'),(384,'순천대학교','전라남도 순천시 중앙로 255 (석현동, 순천대학교)',10,'본교'),(385,'영산선학대학교','전남 영광군 백수읍 성지로 1357',10,'본교'),(386,'전남대학교','전남 여수시 대학로 50',10,'여수캠'),(387,'초당대학교','전라남도 무안군 무안로 380 (무안읍, 초당대학교)',10,'본교'),(388,'한려대학교','전라남도 광양시 한려대길 94-13 (광양읍, 한려대학교)',10,'본교'),(389,'초당대학교','전라남도 무안군 무안로 380 (무안읍, 초당대학교)',10,'본교'),(390,'한려대학교','전라남도 광양시 한려대길 94-13 (광양읍, 한려대학교)',10,'본교'),(391,'한국폴리텍V','전남 무안군 청계면 영산로 1854-16번지',10,'목포캠'),(392,'군산간호대학교','전북 군산시 동개정길 7 (개정동)',9,'본교'),(393,'군장대학교','전북 군산시 성산면 군장대길13 군장대학교',9,'본교'),(394,'서해대학','전라북도 군산시 서해대길 6 (오룡동, 서해대학)',9,'본교'),(395,'원광보건대학교','전라북도 익산시 익산대로 514',9,'본교'),(396,'전주기전대학','전북 전주시 완산구 전주천서로 267 (구 중화산동 1가 177번지)',9,'본교'),(397,'한국농수산대학','전라북도 전주시 완산구 콩쥐팥쥐로 1515 한국농수산대학',9,'본교'),(398,'백제예술대학교','전라북도 완주군 백제대학로 171 (봉동읍, 백제예술대학)',9,'본교'),(399,'전북과학대학교','전라북도 정읍시 정읍사로 509 (시기동, 전북과학대학)',9,'본교'),(400,'전주비전대학교','전북 전주시 완산구 천잠로 235(효자동 2가 1070) 전주비전대학교',9,'본교'),(401,'전주교육대학교','전북 전주시 완산구 서학로 50  전주교육대학교 ',9,'본교'),(402,'군산대학교','전라북도 군산시 대학로 558(미룡동 군산대학교)',9,'본교'),(403,'서남대학교','전라북도 남원시 춘향로 439 (광치동, 서남대학교)',9,'본교'),(404,'예수대학교','전라북도 전주시 완산구 서원로 383 (중화산동1가, 예수대학교)',9,'본교'),(405,'예원예술대학교','전라북도 임실군 창인로 117 (신평면, 예원예술학교)',9,'본교'),(406,'우석대학교','전라북도 완주군 삼례로 443 (삼례읍, 우석대학교)',9,'본교'),(407,'원광대학교','전라북도 익산시 익산대로 460 (신동, 원광대학교)',9,'본교'),(408,'전북대학교','전라북도 전주시 덕진구 백제대로 567',9,'본교'),(409,'전주대학교','전북 전주시 완산구 천잠로 303 전주대학교',9,'본교'),(410,'한일장신대학교','전라북도 완주군 왜목로 726-15 (상관면, 한일장신대학교)',9,'본교'),(411,'호원대학교','전라북도 군산시 호원대3길 64 (임피면, 호원대학교)',9,'본교'),(412,'원광디지털대학교','전북 익산시 익산대로 460(신용동 원광디지털대학교)',9,'본교'),(413,'한국폴리텍 V','전라북도 김제시 백학제길 154 (백학동, 한국 폴리텍V대학)',9,'김제캠'),(414,'한국폴리텍','전라북도 익산시 선화로 579 (어양동, 한국폴리텍V대학)',9,'익산캠'),(415,'제주산업정보대학','제주특별자치도 제주시 516로 2870 (영평동)',17,'본교'),(416,'제주한라대학교','제주특별자치도 제주시 한라대학로 38',17,'본교'),(417,'제주관광대학교','제주특별자치도 제주시 애월읍 평화로 2715 제주관광대학교',17,'본교'),(418,'제주국제대학교','제주 제주시 516로 2870(영평동) ',17,'본교'),(419,'제주대학교','제주특별자치도 제주시 제주대학로 102(아라일동, 제주대학교)',17,'본교'),(420,'탐라대학교','제주특별자치도 제주시 516로 2870 (영평동)',17,'본교'),(421,'백석문화대학교','충청남도 천안시 동남구 문암로 58 (안서동)',6,'본교'),(422,'신성대학교','충남 당진시 정미면 대학로1 ',6,'본교'),(423,'충남도립대학교','충청남도 청양군 학사길 55 (청양읍, 충남도립대학교)',6,'본교'),(424,'혜전대학교','충남 홍성군 홍성읍 대학길25 혜전대학교',6,'본교'),(425,'아주자동차대학','충청남도 보령시 대학길 106 (주포면, 아주자동차대학)',6,'본교'),(426,'천안연암대학','충청남도 천안시 서북구 연암로 313 (성환읍, 천안연암대학)',6,'본교'),(427,'공주교육대학교','충청남도 공주시 웅진로 27 (봉황동, 공주교육대학교)',6,'본교'),(428,'건양대학교','충청남도 논산시 대학로 121 (내동, 건양대학교)',6,'본교'),(429,'공주대학교','충청남도 공주시 공주대학로 56 (신관동, 공주대학교)',6,'본교'),(430,'금강대학교','충청남도 논산시 상월로 522 (상월면, 금강대학교)',6,'본교'),(431,'나사렛대학교','충남 천안시 서북구 월봉로 48  나사렛대학교',6,'본교'),(432,'남서울대학교','충남 천안시 성환읍 대학로91 남서울대학교 ',6,'본교'),(433,'단국대학교','충청남도 천안시 동남구 단대로 119 (안서동, 단국대학교천안캠퍼스)',6,'분교1'),(434,'단국대학교','충청남도 천안시 동남구 단대로 119 (안서동, 단국대학교천안캠퍼스)',6,'천안캠'),(435,'백석대학교','충남 천안시 안서동 문암로 76  백석대학교',6,'본교'),(436,'상명대학교','충남 천안시 동남구 상명대길 31',6,'천안캠'),(437,'선문대학교','충청남도 아산시 선문로221번길 70 (탕정면, 선문대학교)',6,'본교'),(438,'세한대학교','충청남도 당진시 남산길 71-200 (신평면)',6,'당진캠'),(439,'순천향대학교','충남 아산시 신창면 순천향로 22',6,'본교'),(440,'중부대학교','충청남도 금산군 대학로 201 (추부면)',6,'본교'),(441,'한국기술교육대학교','충남 천안시 동남구 병천면 충절로 1600',6,'본교'),(442,'한국전통문화대학교','충남 부여군 규암면 백제문로 367',6,'본교'),(443,'한서대학교','충청남도 서산시 한서1로 46 (해미면) 한서대학교',6,'본교'),(444,'호서대학교','충청남도 아산시 호서로79번길 20 (배방읍, 호서대학교)',6,'본교'),(445,'남서울대학교','충남 천안시 성환읍 대학로 91 남서울대학교',6,'본교'),(446,'청운대학교','충남 홍성군 홍성읍 대학길 25',6,'본교'),(447,'글로벌사이버대학교','충남 천안시 동남구 목천읍 교천지산길 284-88 ',6,'본교'),(448,'한국폴리텍 IV','충청남도 아산시 행목로 45 (신창면, 한국폴리텍Ⅳ대학아산캠퍼스)',6,'아산캠'),(449,'한국폴리텍 IV','충청남도 홍성군 충서로 1200 (홍성읍, 홍성기능대학)',6,'홍성캠'),(450,'한국폴리텍','충남 논산시 강경읍 동안로 112번길 48',6,'바이오캠'),(451,'강동대학교','충북 음성군 감곡면 대학길 278번지 강동대학교',5,'본교'),(452,'대원대학교','충북 제천시 대학로 316 대원대학교',5,'본교'),(453,'충북도립대학','충북 옥천군 옥천읍 대학길 15',5,'본교'),(454,'충북보건과학대학교','충북 청주시 청원구 내수읍 덕암길 10',5,'본교'),(455,'충청대학교','충청북도 청주시 흥덕구 월곡길 38 (강내면, 충청대학)',5,'본교'),(456,'청주교육대학교','충청북도 청주시 서원구 청남로 2065 (수곡동, 청주교육대학교)',5,'본교'),(457,'건국대학교','충북 충주시 충원대로 268 건국대학교 GLOCAL캠퍼스',5,'글로벌캠'),(458,'극동대학교','충청북도 음성군 대학길 76-32 (감곡면, 극동대학교)',5,'본교'),(459,'꽃동네대학교','충북 청주시 서원구 현도면 상삼길 133',5,'본교'),(460,'서원대학교','충청북도 청주시 서원구 무심서로 377-3 (모충동, 서원대학교)',5,'본교'),(461,'세명대학교','충청북도 제천시 세명로 65 (신월동, 세명대학교)',5,'본교'),(462,'영동대학교','충청북도 영동군 대학로 310 (영동읍, 영동대학교)',5,'본교'),(463,'중원대학교','충청북도 괴산군 문무로 85 (괴산읍, 중원대학교)',5,'본교'),(464,'청주대학교','충청북도 청주시 상당구 대성로 298 (내덕동, 청주대학교)',5,'본교'),(465,'충북대학교','충청북도 청주시 서원구 충대로 1 (개신동, 충북대학교)',5,'본교'),(466,'한국교원대학교','충청북도 청주시 흥덕구 태성탑연로 250 (강내면, 한국교원대학교)',5,'본교'),(467,'한국교통대학교','충북 충주시 대소원면 대학로 50 한국교통대학교 ',5,'본교'),(468,'한국교통대학교','충북 충주시 대소원면 대학로 50',5,'본교'),(469,'순복음총회신학교','충청북도 제천시 도전로 320 (덕산면)',5,'본교'),(470,'한국폴리텍 IV','충청북도 청주시 흥덕구 산단로 54 (송정동, 한국폴리텍Ⅳ대학청주캠퍼스)',5,'청주캠');
/*!40000 ALTER TABLE `delivery_site_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department_tbl`
--

DROP TABLE IF EXISTS `department_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `department_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `manager_idx` int(11) DEFAULT NULL COMMENT '부서장 인덱스',
  `location_idx` int(11) DEFAULT NULL COMMENT '부서 위치 인덱스',
  `parent_department_idx` int(11) DEFAULT NULL COMMENT '상위 부서 인덱스',
  `title` varchar(100) NOT NULL,
  `description` text,
  `main_number` varchar(45) DEFAULT NULL COMMENT '부서 대표 전화번호',
  PRIMARY KEY (`idx`),
  KEY `fk_manager_idx` (`manager_idx`),
  KEY `fk_dept_location_idx` (`location_idx`),
  KEY `fk_dept_parent_idx` (`parent_department_idx`),
  CONSTRAINT `fk_dept_location` FOREIGN KEY (`location_idx`) REFERENCES `location_for_department_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_dept_manager` FOREIGN KEY (`manager_idx`) REFERENCES `employee_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_dept_parent` FOREIGN KEY (`parent_department_idx`) REFERENCES `department_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='부서 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department_tbl`
--

LOCK TABLES `department_tbl` WRITE;
/*!40000 ALTER TABLE `department_tbl` DISABLE KEYS */;
INSERT INTO `department_tbl` VALUES (1,1,1,NULL,'본사','본사','02-1234-1234'),(2,1,2,1,'강남점','지사','02-1234-1234'),(3,1,1,1,'항공대점','항공대점','010-6478-4899'),(4,1,3,1,'군산대점','군산대점','010-6478-4899'),(5,1,3,1,'호원대점','호원대점','010-6478-4899');
/*!40000 ALTER TABLE `department_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `designation_tbl`
--

DROP TABLE IF EXISTS `designation_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `designation_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `employee_idx` int(11) DEFAULT NULL,
  `ordertime_idx` int(11) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_desg_emp_idx` (`employee_idx`),
  KEY `fk_desg_ot_idx` (`ordertime_idx`),
  CONSTRAINT `fk_desg_emp` FOREIGN KEY (`employee_idx`) REFERENCES `employee_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_desg_ot` FOREIGN KEY (`ordertime_idx`) REFERENCES `ordertime_for_store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='직원에게 지정된 담당 업체 매핑 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `designation_tbl`
--

LOCK TABLES `designation_tbl` WRITE;
/*!40000 ALTER TABLE `designation_tbl` DISABLE KEYS */;
INSERT INTO `designation_tbl` VALUES (34,2,1),(35,2,2),(36,2,3),(37,2,4),(38,2,5),(39,2,7),(40,2,8),(41,2,9),(42,2,10),(43,2,11),(44,2,13),(45,2,14),(46,2,15),(47,2,16),(48,2,17);
/*!40000 ALTER TABLE `designation_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detail_for_delivery_site_tbl`
--

DROP TABLE IF EXISTS `detail_for_delivery_site_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detail_for_delivery_site_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `delivery_site_idx` int(11) DEFAULT NULL,
  `name` varchar(100) NOT NULL COMMENT '상제 장소 이름',
  `location` varchar(100) DEFAULT NULL COMMENT '상세 주소',
  `sequence` int(11) NOT NULL COMMENT '도착 순서',
  `offset_arrival_time` time NOT NULL DEFAULT '00:00:00' COMMENT 'ordertime의 arrival_time을 기준으로,\n해당 지점에 도착 하기까지 추가적으로 소요되는 시간\n(실제 도착시간 = arrival_time + offset_arrival_time)\n',
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `abbreviation` varchar(100) DEFAULT NULL COMMENT '배달지 이름의 준말',
  PRIMARY KEY (`idx`),
  KEY `fk_detail_dsite_idx` (`delivery_site_idx`),
  CONSTRAINT `fk_detail_dsite` FOREIGN KEY (`delivery_site_idx`) REFERENCES `delivery_site_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='배달지 상세 장소 테이블\n(배달이 도착하는 곳)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_for_delivery_site_tbl`
--

LOCK TABLES `detail_for_delivery_site_tbl` WRITE;
/*!40000 ALTER TABLE `detail_for_delivery_site_tbl` DISABLE KEYS */;
INSERT INTO `detail_for_delivery_site_tbl` VALUES (1,1,'학생회관 뒤','항공대 학생회관 뒤편 (족구장)',0,'00:00:00',37.600326,126.864485,'ㅎ'),(2,1,'기숙사 식당','항공대 기숙사 안 식당',1,'00:10:00',37.598048,126.866489,'ㄱ'),(3,402,'제1학생회관','군산대 제1학생회관 정문',0,'00:00:00',35.947834,126.68199,'학1'),(4,402,'기숙사 정문','군산대 기숙사 정문',1,'00:10:00',35.949846,126.68324,'기'),(5,411,'제1학생회관','호원대 제1학생회관 정문',0,'00:00:00',35.962375,126.865167,'학1'),(6,411,'반계관 정문','호원대 반계관 정문',1,'00:05:00',35.968652,126.863773,'반'),(7,411,'연암관 정문','호원대 연암관 정문',2,'00:10:00',35.971283,126.864038,'연');
/*!40000 ALTER TABLE `detail_for_delivery_site_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dsite_link_advertise_popup_tbl`
--

DROP TABLE IF EXISTS `dsite_link_advertise_popup_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dsite_link_advertise_popup_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `delivery_site_idx` int(11) DEFAULT NULL,
  `advertise_popup_idx` int(11) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_sitelink_ad_popup_idx` (`advertise_popup_idx`),
  KEY `fk_sitelink_dsite_popup` (`delivery_site_idx`),
  CONSTRAINT `fk_sitelink_ad_popup` FOREIGN KEY (`advertise_popup_idx`) REFERENCES `advertise_for_popup_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sitelink_dsite_popup` FOREIGN KEY (`delivery_site_idx`) REFERENCES `delivery_site_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='delivery site에 따라 나눠어 지는 메인 광고 팝업 연결 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dsite_link_advertise_popup_tbl`
--

LOCK TABLES `dsite_link_advertise_popup_tbl` WRITE;
/*!40000 ALTER TABLE `dsite_link_advertise_popup_tbl` DISABLE KEYS */;
INSERT INTO `dsite_link_advertise_popup_tbl` VALUES (1,1,1),(2,402,2),(3,411,3);
/*!40000 ALTER TABLE `dsite_link_advertise_popup_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dsite_link_cmt_advertise_main_tbl`
--

DROP TABLE IF EXISTS `dsite_link_cmt_advertise_main_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dsite_link_cmt_advertise_main_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `delivery_site_idx` int(11) DEFAULT NULL,
  `cmt_advertise_main_idx` int(11) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_sitelink_imgpath_main_idx` (`cmt_advertise_main_idx`),
  KEY `fk_sitelink_cmt_dsite_main_idx` (`delivery_site_idx`),
  CONSTRAINT `fk_sitelink_cmt_ad_main` FOREIGN KEY (`cmt_advertise_main_idx`) REFERENCES `imgpath_for_comment_all_main_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sitelink_cmt_dsite_main` FOREIGN KEY (`delivery_site_idx`) REFERENCES `delivery_site_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='delivery site에 따라 나눠어 지는 메인 이미지 연결 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dsite_link_cmt_advertise_main_tbl`
--

LOCK TABLES `dsite_link_cmt_advertise_main_tbl` WRITE;
/*!40000 ALTER TABLE `dsite_link_cmt_advertise_main_tbl` DISABLE KEYS */;
INSERT INTO `dsite_link_cmt_advertise_main_tbl` VALUES (1,1,1),(2,1,2),(3,402,2),(4,411,3),(5,1,3),(6,1,4);
/*!40000 ALTER TABLE `dsite_link_cmt_advertise_main_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dsite_link_event_tbl`
--

DROP TABLE IF EXISTS `dsite_link_event_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dsite_link_event_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `delivery_site_idx` int(11) DEFAULT NULL,
  `event_idx` int(11) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_sitelink_event_site_idx` (`delivery_site_idx`),
  KEY `fk_sitelink_event_idx` (`event_idx`),
  CONSTRAINT `fk_sitelink_event` FOREIGN KEY (`event_idx`) REFERENCES `event_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sitelink_event_site` FOREIGN KEY (`delivery_site_idx`) REFERENCES `delivery_site_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='delivery site에 따라 나눠어 지는 이벤트 연결 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dsite_link_event_tbl`
--

LOCK TABLES `dsite_link_event_tbl` WRITE;
/*!40000 ALTER TABLE `dsite_link_event_tbl` DISABLE KEYS */;
INSERT INTO `dsite_link_event_tbl` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5);
/*!40000 ALTER TABLE `dsite_link_event_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dsite_link_notice_tbl`
--

DROP TABLE IF EXISTS `dsite_link_notice_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dsite_link_notice_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `delivery_site_idx` int(11) DEFAULT NULL,
  `notice_idx` int(11) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_sitelink_notice_idx` (`notice_idx`),
  KEY `fk_sitelink_site_idx` (`delivery_site_idx`),
  CONSTRAINT `fk_sitelink_notice` FOREIGN KEY (`notice_idx`) REFERENCES `notice_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sitelink_site` FOREIGN KEY (`delivery_site_idx`) REFERENCES `delivery_site_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='delivery site에 따라 나눠어 지는 공지사항 연결 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dsite_link_notice_tbl`
--

LOCK TABLES `dsite_link_notice_tbl` WRITE;
/*!40000 ALTER TABLE `dsite_link_notice_tbl` DISABLE KEYS */;
INSERT INTO `dsite_link_notice_tbl` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8);
/*!40000 ALTER TABLE `dsite_link_notice_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dstie_link_advertise_main_tbl`
--

DROP TABLE IF EXISTS `dstie_link_advertise_main_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dstie_link_advertise_main_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `delivery_site_idx` int(11) DEFAULT NULL,
  `advertise_main_idx` int(11) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_sitelink_dsite_main_idx` (`delivery_site_idx`),
  KEY `fk_sitelink_ad_main_idx` (`advertise_main_idx`),
  CONSTRAINT `fk_sitelink_ad_main` FOREIGN KEY (`advertise_main_idx`) REFERENCES `advertise_for_main_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sitelink_dsite_main` FOREIGN KEY (`delivery_site_idx`) REFERENCES `delivery_site_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='delivery site에 따라 나눠어 지는 메인 광고 연결 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dstie_link_advertise_main_tbl`
--

LOCK TABLES `dstie_link_advertise_main_tbl` WRITE;
/*!40000 ALTER TABLE `dstie_link_advertise_main_tbl` DISABLE KEYS */;
INSERT INTO `dstie_link_advertise_main_tbl` VALUES (4,1,1),(5,1,2),(6,1,3),(7,2,2);
/*!40000 ALTER TABLE `dstie_link_advertise_main_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dstie_link_sub_advertise_main_tbl`
--

DROP TABLE IF EXISTS `dstie_link_sub_advertise_main_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dstie_link_sub_advertise_main_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `delivery_site_idx` int(11) DEFAULT NULL,
  `sub_advertise_main_idx` int(11) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_sitelink_sub_ad_main_idx` (`sub_advertise_main_idx`),
  KEY `fk_sitelink_sub_dsite_main_idx` (`delivery_site_idx`),
  CONSTRAINT `fk_sitelink_sub_ad_main` FOREIGN KEY (`sub_advertise_main_idx`) REFERENCES `sub_advertise_for_main_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sitelink_sub_dsite_main` FOREIGN KEY (`delivery_site_idx`) REFERENCES `delivery_site_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='delivery site에 따라 나눠어 지는 메인 서브 광고 연결 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dstie_link_sub_advertise_main_tbl`
--

LOCK TABLES `dstie_link_sub_advertise_main_tbl` WRITE;
/*!40000 ALTER TABLE `dstie_link_sub_advertise_main_tbl` DISABLE KEYS */;
INSERT INTO `dstie_link_sub_advertise_main_tbl` VALUES (1,1,1),(2,1,2),(3,1,3),(4,2,2),(5,2,3);
/*!40000 ALTER TABLE `dstie_link_sub_advertise_main_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_tbl`
--

DROP TABLE IF EXISTS `employee_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `job_idx` int(11) DEFAULT NULL,
  `department_idx` int(11) DEFAULT NULL,
  `id` varchar(45) NOT NULL COMMENT '아이디',
  `pw` varchar(150) NOT NULL COMMENT '패스워드',
  `name` varchar(150) NOT NULL COMMENT '패스워드',
  `gender` tinyint(4) DEFAULT NULL COMMENT '성별\n0 - 남자\n1 - 여자',
  `year_of_birth` smallint(6) DEFAULT NULL,
  `month_of_birth` tinyint(4) DEFAULT NULL,
  `days_of_birth` tinyint(4) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `state_active` tinyint(4) NOT NULL DEFAULT '1' COMMENT '활성화 여부 상태\n0 - 비활성화\n1 - 활성화',
  `hire_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `salary` int(11) NOT NULL COMMENT '기본급여\n기간 단위 : 월 (month)\n화폐 단위 : 원 (won)\n',
  `commission_prc` int(11) DEFAULT '0',
  `commission_pct` smallint(6) DEFAULT '0',
  PRIMARY KEY (`idx`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_emp_job_idx` (`job_idx`),
  KEY `fk_emp_dept_idx` (`department_idx`),
  CONSTRAINT `fk_emp_dept` FOREIGN KEY (`department_idx`) REFERENCES `department_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_emp_job` FOREIGN KEY (`job_idx`) REFERENCES `job_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='직원 관리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_tbl`
--

LOCK TABLES `employee_tbl` WRITE;
/*!40000 ALTER TABLE `employee_tbl` DISABLE KEYS */;
INSERT INTO `employee_tbl` VALUES (1,1,1,'staff','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','관리자',0,1993,1,10,'010-1234-1234','cholnh1@naver.com',1,'2019-02-11 12:44:29','2019-02-11 12:44:05',NULL,30000000,1500000,50),(2,6,2,'emp1','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','홍길동',0,1999,1,1,'010-1234-1234','hh@naver.com',1,'2019-02-11 12:44:29','2019-02-11 12:47:16','2019-02-11 13:55:44',2000000,0,0),(3,7,3,'deli1','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','김항공',0,1987,1,1,'010-1234-1234','hh@naver.com',1,'2019-04-24 18:24:09','2019-04-24 18:24:09','2019-04-24 18:24:20',2000000,0,0),(4,7,4,'deli2','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','최군산',1,1972,1,1,'010-1234-1234','hh@naver.com',1,'2019-04-24 18:24:09','2019-04-24 18:24:09','2019-04-24 18:24:20',2000000,0,0),(5,7,5,'deli3','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','박호원',1,1975,1,1,'010-1234-1234','hh@naver.com',1,'2019-04-24 18:24:09','2019-04-24 18:24:09','2019-04-24 18:24:20',2000000,0,0);
/*!40000 ALTER TABLE `employee_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_tbl`
--

DROP TABLE IF EXISTS `event_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `contents` text,
  `begin_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '이벤트 시작 날짜\n(기간 0 입력 시 무제한으로 설정)',
  `end_date` timestamp NULL DEFAULT NULL COMMENT '이벤트 종료 날짜\n(기간 0 입력 시 무제한으로 설정)',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `state_active` tinyint(4) NOT NULL DEFAULT '1' COMMENT '제품 상태\n0 - 비활성화\n1 - 활성화',
  `url` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='이벤트 관리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_tbl`
--

LOCK TABLES `event_tbl` WRITE;
/*!40000 ALTER TABLE `event_tbl` DISABLE KEYS */;
INSERT INTO `event_tbl` VALUES (1,'3월 신학기 이벤트','항공대 이벤트 테스트 내용 <p style=\"color:red\">항공대 이벤트 테스트 내용</p>','2019-04-24 18:12:34',NULL,'2019-04-24 18:12:34','2019-05-07 15:03:14',1,'eventType1'),(2,'포만감 추천 이벤트','항공대 이벤트 테스트 내용 <p style=\"color:red\">항공대 이벤트 테스트 내용</p>','2019-04-24 18:12:34',NULL,'2019-04-24 18:12:34','2019-05-07 15:03:14',1,'eventType1'),(3,'친구 추천 이벤트','항공대 이벤트 테스트 내용 <p style=\"color:red\">항공대 이벤트 테스트 내용</p>','2019-04-24 18:12:34',NULL,'2019-04-24 18:12:34','2019-05-07 15:03:14',1,'eventType1'),(4,'학생회 제휴 이벤트','항공대 이벤트 테스트 내용 <p style=\"color:red\">항공대 이벤트 테스트 내용</p>','2019-04-24 18:12:34',NULL,'2019-05-07 15:03:14','2019-05-07 15:03:14',1,'eventType1'),(5,'MT안주 증정 이벤트','항공대 이벤트 테스트 내용 <p style=\"color:red\">항공대 이벤트 테스트 내용</p>','2019-04-24 18:12:34',NULL,'2019-05-07 15:03:14','2019-05-07 15:03:14',1,'eventType1');
/*!40000 ALTER TABLE `event_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faq_tbl`
--

DROP TABLE IF EXISTS `faq_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faq_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) DEFAULT NULL,
  `contents` text,
  `register_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faq_tbl`
--

LOCK TABLES `faq_tbl` WRITE;
/*!40000 ALTER TABLE `faq_tbl` DISABLE KEYS */;
INSERT INTO `faq_tbl` VALUES (1,'주문은 어떻게 하나요?','미스터포터 회사(전자상거래 사업자)가 운영하는 포만감 사이버 몰(이하 “몰”이라 한다)에서 제공하는 인터넷 관련 서비스(이하 “서비스”라 한다)를 이용함에 있어 사이버 몰과 이용자의 권리․의무 및 책임사항을 규정함을 목적으로 합니다.','2019-05-05 20:59:59'),(2,'주문 취소는 어떻게 하나요?','미스터포터 회사(전자상거래 사업자)가 운영하는 포만감 사이버 몰(이하 “몰”이라 한다)에서 제공하는 인터넷 관련 서비스(이하 “서비스”라 한다)를 이용함에 있어 사이버 몰과 이용자의 권리․의무 및 책임사항을 규정함을 목적으로 합니다.','2019-05-05 21:07:03'),(3,'주문 환불을 하고 싶습니다.','미스터포터 회사(전자상거래 사업자)가 운영하는 포만감 사이버 몰(이하 “몰”이라 한다)에서 제공하는 인터넷 관련 서비스(이하 “서비스”라 한다)를 이용함에 있어 사이버 몰과 이용자의 권리․의무 및 책임사항을 규정함을 목적으로 합니다.','2019-05-05 21:07:03'),(4,'배달을 받지 못했습니다.','미스터포터 회사(전자상거래 사업자)가 운영하는 포만감 사이버 몰(이하 “몰”이라 한다)에서 제공하는 인터넷 관련 서비스(이하 “서비스”라 한다)를 이용함에 있어 사이버 몰과 이용자의 권리․의무 및 책임사항을 규정함을 목적으로 합니다.','2019-05-05 21:07:03'),(5,'음식 상태가 좋지 않습니다.','미스터포터 회사(전자상거래 사업자)가 운영하는 포만감 사이버 몰(이하 “몰”이라 한다)에서 제공하는 인터넷 관련 서비스(이하 “서비스”라 한다)를 이용함에 있어 사이버 몰과 이용자의 권리․의무 및 책임사항을 규정함을 목적으로 합니다.','2019-05-05 21:07:03'),(6,'음식에서 이물질이 나왔어요.','미스터포터 회사(전자상거래 사업자)가 운영하는 포만감 사이버 몰(이하 “몰”이라 한다)에서 제공하는 인터넷 관련 서비스(이하 “서비스”라 한다)를 이용함에 있어 사이버 몰과 이용자의 권리․의무 및 책임사항을 규정함을 목적으로 합니다.','2019-05-05 21:07:03'),(7,'회원가입은 어떻게 하나요?','미스터포터 회사(전자상거래 사업자)가 운영하는 포만감 사이버 몰(이하 “몰”이라 한다)에서 제공하는 인터넷 관련 서비스(이하 “서비스”라 한다)를 이용함에 있어 사이버 몰과 이용자의 권리․의무 및 책임사항을 규정함을 목적으로 합니다.','2019-05-05 21:07:03'),(8,'회원 탈퇴는 어떻게 하나요?','미스터포터 회사(전자상거래 사업자)가 운영하는 포만감 사이버 몰(이하 “몰”이라 한다)에서 제공하는 인터넷 관련 서비스(이하 “서비스”라 한다)를 이용함에 있어 사이버 몰과 이용자의 권리․의무 및 책임사항을 규정함을 목적으로 합니다.','2019-05-05 21:07:03'),(9,'포인트 사용 관련','미스터포터 회사(전자상거래 사업자)가 운영하는 포만감 사이버 몰(이하 “몰”이라 한다)에서 제공하는 인터넷 관련 서비스(이하 “서비스”라 한다)를 이용함에 있어 사이버 몰과 이용자의 권리․의무 및 책임사항을 규정함을 목적으로 합니다.','2019-05-05 21:07:03'),(10,'쿠폰은 어떻게 받나요?','미스터포터 회사(전자상거래 사업자)가 운영하는 포만감 사이버 몰(이하 “몰”이라 한다)에서 제공하는 인터넷 관련 서비스(이하 “서비스”라 한다)를 이용함에 있어 사이버 몰과 이용자의 권리․의무 및 책임사항을 규정함을 목적으로 합니다.','2019-05-05 21:07:03');
/*!40000 ALTER TABLE `faq_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guest_tbl`
--

DROP TABLE IF EXISTS `guest_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guest_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `register_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8 COMMENT='비회원 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest_tbl`
--

LOCK TABLES `guest_tbl` WRITE;
/*!40000 ALTER TABLE `guest_tbl` DISABLE KEYS */;
INSERT INTO `guest_tbl` VALUES (1,'2019-04-22 08:46:35'),(2,'2019-04-22 08:46:35'),(3,'2019-04-22 08:46:35'),(4,'2019-04-22 08:46:35'),(5,'2019-04-22 08:46:35'),(6,'2019-05-05 16:55:42'),(7,'2019-05-06 00:10:27'),(8,'2019-05-06 01:26:02'),(9,'2019-05-06 07:12:08'),(10,'2019-05-06 07:39:24'),(11,'2019-05-06 08:23:58'),(12,'2019-05-06 08:25:32'),(13,'2019-05-06 08:26:29'),(14,'2019-05-06 08:26:30'),(15,'2019-05-06 08:29:05'),(16,'2019-05-06 08:29:48'),(17,'2019-05-06 08:44:32'),(18,'2019-05-06 08:44:35'),(19,'2019-05-06 09:27:45'),(20,'2019-05-06 14:45:05'),(21,'2019-05-06 23:09:01'),(22,'2019-05-06 23:43:28'),(23,'2019-05-07 00:38:40'),(24,'2019-05-07 00:40:23'),(25,'2019-05-07 01:56:16'),(26,'2019-05-07 02:22:26'),(27,'2019-05-07 02:24:02'),(28,'2019-05-07 02:34:44'),(29,'2019-05-07 02:47:12'),(30,'2019-05-07 02:48:43'),(31,'2019-05-07 07:48:31'),(32,'2019-05-07 14:17:18'),(33,'2019-05-07 23:36:03'),(34,'2019-05-08 03:05:58'),(35,'2019-05-08 06:44:37'),(36,'2019-05-08 08:21:32'),(37,'2019-05-08 09:46:49'),(38,'2019-05-09 01:29:10'),(39,'2019-05-09 07:23:35'),(40,'2019-05-10 03:48:10'),(41,'2019-05-10 03:50:40'),(42,'2019-05-11 00:46:38'),(43,'2019-05-11 01:15:24'),(44,'2019-05-11 01:20:11'),(45,'2019-05-11 06:05:52'),(46,'2019-05-11 07:43:54');
/*!40000 ALTER TABLE `guest_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imgpath_for_comment_all_main_tbl`
--

DROP TABLE IF EXISTS `imgpath_for_comment_all_main_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imgpath_for_comment_all_main_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `imgpath` varchar(100) DEFAULT NULL COMMENT '광고 제목',
  `comment_all_idx` int(11) DEFAULT NULL,
  `state_active` tinyint(4) NOT NULL DEFAULT '1' COMMENT '활성화 여부 상태\n0 - 비활성화\n1 - 활성화',
  `sequence` int(11) NOT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_cmtad_comment_all_idx` (`comment_all_idx`),
  CONSTRAINT `fk_cmtad_comment_all` FOREIGN KEY (`comment_all_idx`) REFERENCES `comment_for_all_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='메인화면 고객의소리 관리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imgpath_for_comment_all_main_tbl`
--

LOCK TABLES `imgpath_for_comment_all_main_tbl` WRITE;
/*!40000 ALTER TABLE `imgpath_for_comment_all_main_tbl` DISABLE KEYS */;
INSERT INTO `imgpath_for_comment_all_main_tbl` VALUES (1,'/assets/image/comment/main/1.png',1,1,1),(2,'/assets/image/comment/main/2.png',2,1,2),(3,'/assets/image/comment/main/5.png',3,1,3),(4,'/assets/image/comment/main/4.png',1,1,4);
/*!40000 ALTER TABLE `imgpath_for_comment_all_main_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imgpath_for_comment_all_tbl`
--

DROP TABLE IF EXISTS `imgpath_for_comment_all_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imgpath_for_comment_all_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `comment_all_idx` int(11) DEFAULT NULL,
  `imgpath` varchar(200) DEFAULT NULL,
  `type` tinyint(4) DEFAULT '0' COMMENT '이미지 타입\n0 - 메인 이미지\n1 - 서브 이미지',
  PRIMARY KEY (`idx`),
  KEY `fk_imgpath_comment_all_idx` (`comment_all_idx`),
  CONSTRAINT `fk_imgpath_comment_all` FOREIGN KEY (`comment_all_idx`) REFERENCES `comment_for_all_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='후기 내 이미지 정보 배열 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imgpath_for_comment_all_tbl`
--

LOCK TABLES `imgpath_for_comment_all_tbl` WRITE;
/*!40000 ALTER TABLE `imgpath_for_comment_all_tbl` DISABLE KEYS */;
INSERT INTO `imgpath_for_comment_all_tbl` VALUES (1,1,'/assets/image/comment/post/1.png',1);
/*!40000 ALTER TABLE `imgpath_for_comment_all_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imgpath_for_event_thumbnail_tbl`
--

DROP TABLE IF EXISTS `imgpath_for_event_thumbnail_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imgpath_for_event_thumbnail_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `event_idx` int(11) DEFAULT NULL,
  `imgpath` varchar(200) DEFAULT NULL,
  `type` tinyint(4) DEFAULT '0' COMMENT '이미지 타입\n0 - 메인 이미지\n1 - 서브 이미지',
  PRIMARY KEY (`idx`),
  KEY `fk_imgpath_event_idx` (`event_idx`),
  CONSTRAINT `fk_imgpath_event` FOREIGN KEY (`event_idx`) REFERENCES `event_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='이벤트 미리보기 이미지 정보 배열 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imgpath_for_event_thumbnail_tbl`
--

LOCK TABLES `imgpath_for_event_thumbnail_tbl` WRITE;
/*!40000 ALTER TABLE `imgpath_for_event_thumbnail_tbl` DISABLE KEYS */;
INSERT INTO `imgpath_for_event_thumbnail_tbl` VALUES (1,1,'/assets/image/event/1.png',0),(2,2,'/assets/image/event/2.png',0),(3,3,'/assets/image/event/3.png',0),(4,4,'/assets/image/event/4.png',0),(5,5,'/assets/image/event/5.png',0);
/*!40000 ALTER TABLE `imgpath_for_event_thumbnail_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imgpath_for_notice_tbl`
--

DROP TABLE IF EXISTS `imgpath_for_notice_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imgpath_for_notice_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `notice_idx` int(11) DEFAULT NULL,
  `imgpath` varchar(200) DEFAULT NULL,
  `type` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`idx`),
  KEY `fk_imgpath_notice_idx` (`notice_idx`),
  CONSTRAINT `fk_imgpath_notice` FOREIGN KEY (`notice_idx`) REFERENCES `notice_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='공지사항 내 이미지 정보 배열 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imgpath_for_notice_tbl`
--

LOCK TABLES `imgpath_for_notice_tbl` WRITE;
/*!40000 ALTER TABLE `imgpath_for_notice_tbl` DISABLE KEYS */;
INSERT INTO `imgpath_for_notice_tbl` VALUES (1,1,'/assets/image/notice/1.png',0),(2,2,'/assets/image/notice/1.png',0),(3,3,'/assets/image/notice/1.png',0);
/*!40000 ALTER TABLE `imgpath_for_notice_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imgpath_for_product_tbl`
--

DROP TABLE IF EXISTS `imgpath_for_product_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imgpath_for_product_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `product_idx` int(11) DEFAULT NULL,
  `imgpath` varchar(200) DEFAULT NULL,
  `type` tinyint(4) DEFAULT '0' COMMENT '이미지 타입\n0 - 메인 이미지\n1 - 서브 이미지',
  PRIMARY KEY (`idx`),
  KEY `asd_idx` (`product_idx`),
  CONSTRAINT `imgpath_product_fk` FOREIGN KEY (`product_idx`) REFERENCES `product_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=350 DEFAULT CHARSET=utf8 COMMENT='제품 내 이미지 정보 배열 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imgpath_for_product_tbl`
--

LOCK TABLES `imgpath_for_product_tbl` WRITE;
/*!40000 ALTER TABLE `imgpath_for_product_tbl` DISABLE KEYS */;
INSERT INTO `imgpath_for_product_tbl` VALUES (8,21,'/assets/image/product/1.jpg',0),(9,22,'/assets/image/product/1.jpg',0),(10,23,'/assets/image/product/2.jpg',0),(11,24,'/assets/image/product/3.jpg',0),(12,25,'/assets/image/product/4.jpg',0),(13,26,'/assets/image/product/5.jpg',0),(14,27,'/assets/image/product/6.jpg',0),(15,28,'/assets/image/product/f.jpg',0),(16,29,'/assets/image/product/144.jpg',0),(17,30,'/assets/image/product/145.jpg',0),(18,31,'/assets/image/product/146.jpg',0),(19,32,'/assets/image/product/147.jpg',0),(20,33,'/assets/image/product/148.jpg',0),(21,34,'/assets/image/product/149.jpg',0),(22,35,'/assets/image/product/150.jpg',0),(23,36,'/assets/image/product/151.jpg',0),(24,37,'/assets/image/product/152.jpg',0),(25,38,'/assets/image/product/153.jpg',0),(26,39,'/assets/image/product/154.jpg',0),(27,40,'/assets/image/product/144.jpg',0),(28,41,'/assets/image/product/145.jpg',0),(29,42,'/assets/image/product/146.jpg',0),(30,43,'/assets/image/product/147.jpg',0),(31,44,'/assets/image/product/148.jpg',0),(32,45,'/assets/image/product/149.jpg',0),(33,46,'/assets/image/product/150.jpg',0),(34,47,'/assets/image/product/151.jpg',0),(35,48,'/assets/image/product/152.jpg',0),(36,49,'/assets/image/product/153.jpg',0),(37,50,'/assets/image/product/154.jpg',0),(38,51,'/assets/image/product/1.jpg',0),(39,52,'/assets/image/product/2.jpg',0),(40,53,'/assets/image/product/3.jpg',0),(41,54,'/assets/image/product/4.jpg',0),(42,55,'/assets/image/product/5.jpg',0),(43,56,'/assets/image/product/6.jpg',0),(44,57,'/assets/image/product/288.jpg',0),(45,58,'/assets/image/product/288.jpg',0),(46,59,'/assets/image/product/2.jpg',0),(47,60,'/assets/image/product/2.jpg',0),(48,61,'/assets/image/product/2.jpg',0),(49,62,'/assets/image/product/2.jpg',0),(50,63,'/assets/image/product/411.jpg',0),(51,64,'/assets/image/product/412.jpg',0),(52,65,'/assets/image/product/50.jpg',0),(53,66,'/assets/image/product/51.jpg',0),(54,67,'/assets/image/product/52.jpg',0),(55,68,'/assets/image/product/53.jpg',0),(56,69,'/assets/image/product/58.jpg',0),(57,70,'/assets/image/product/55.jpg',0),(58,71,'/assets/image/product/56.jpg',0),(59,72,'/assets/image/product/57.jpg',0),(60,73,'/assets/image/product/54.jpg',0),(61,74,'/assets/image/product/59.jpg',0),(62,75,'/assets/image/product/198.jpg',0),(63,76,'/assets/image/product/199.jpg',0),(64,77,'/assets/image/product/200.jpg',0),(65,78,'/assets/image/product/201.jpg',0),(66,79,'/assets/image/product/202.jpg',0),(67,80,'/assets/image/product/203.jpg',0),(68,81,'/assets/image/product/204.jpg',0),(69,82,'/assets/image/product/205.jpg',0),(70,83,'/assets/image/product/206.jpg',0),(71,84,'/assets/image/product/207.jpg',0),(72,85,'/assets/image/product/208.jpg',0),(73,86,'/assets/image/product/209.jpg',0),(74,87,'/assets/image/product/210.jpg',0),(75,88,'/assets/image/product/211.jpg',0),(76,89,'/assets/image/product/212.jpg',0),(77,90,'/assets/image/product/213.jpg',0),(78,91,'/assets/image/product/214.jpg',0),(79,92,'/assets/image/product/215.jpg',0),(80,93,'/assets/image/product/216.jpg',0),(81,94,'/assets/image/product/217.jpg',0),(82,95,'/assets/image/product/218.jpg',0),(83,96,'/assets/image/product/219.jpg',0),(84,97,'/assets/image/product/220.jpg',0),(85,98,'/assets/image/product/221.jpg',0),(86,99,'/assets/image/product/222.jpg',0),(87,100,'/assets/image/product/223.jpg',0),(88,101,'/assets/image/product/224.jpg',0),(89,102,'/assets/image/product/225.jpg',0),(90,103,'/assets/image/product/226.jpg',0),(91,104,'/assets/image/product/227.jpg',0),(92,105,'/assets/image/product/228.jpg',0),(93,106,'/assets/image/product/229.jpg',0),(94,107,'/assets/image/product/230.jpg',0),(95,108,'/assets/image/product/231.jpg',0),(96,109,'/assets/image/product/232.jpg',0),(97,110,'/assets/image/product/233.jpg',0),(98,111,'/assets/image/product/234.jpg',0),(99,112,'/assets/image/product/235.jpg',0),(100,113,'/assets/image/product/236.jpg',0),(101,114,'/assets/image/product/237.jpg',0),(102,115,'/assets/image/product/238.jpg',0),(103,116,'/assets/image/product/239.jpg',0),(104,117,'/assets/image/product/426.jpg',0),(105,118,'/assets/image/product/427.jpg',0),(106,119,'/assets/image/product/90.jpg',0),(107,120,'/assets/image/product/91.jpg',0),(108,121,'/assets/image/product/92.jpg',0),(109,122,'/assets/image/product/93.jpg',0),(110,123,'/assets/image/product/94.jpg',0),(111,124,'/assets/image/product/95.jpg',0),(112,125,'/assets/image/product/96.jpg',0),(113,126,'/assets/image/product/97.jpg',0),(114,127,'/assets/image/product/98.jpg',0),(115,128,'/assets/image/product/99.jpg',0),(116,129,'/assets/image/product/100.jpg',0),(117,130,'/assets/image/product/101.jpg',0),(118,131,'/assets/image/product/102.jpg',0),(119,132,'/assets/image/product/103.jpg',0),(120,133,'/assets/image/product/104.jpg',0),(121,134,'/assets/image/product/105.jpg',0),(122,135,'/assets/image/product/106.jpg',0),(123,136,'/assets/image/product/107.jpg',0),(124,137,'/assets/image/product/108.jpg',0),(125,138,'/assets/image/product/109.jpg',0),(126,139,'/assets/image/product/110.jpg',0),(127,140,'/assets/image/product/111.jpg',0),(128,141,'/assets/image/product/a43.jpg',0),(129,142,'/assets/image/product/a44.jpg',0),(130,143,'/assets/image/product/a45.jpg',0),(131,144,'/assets/image/product/a46.jpg',0),(132,145,'/assets/image/product/a29.jpg',0),(133,146,'/assets/image/product/a30.jpg',0),(134,147,'/assets/image/product/a31.jpg',0),(135,148,'/assets/image/product/a32.jpg',0),(136,149,'/assets/image/product/a33.jpg',0),(137,150,'/assets/image/product/a34.jpg',0),(138,151,'/assets/image/product/a35.jpg',0),(139,152,'/assets/image/product/a36.jpg',0),(140,153,'/assets/image/product/a37.jpg',0),(141,154,'/assets/image/product/a38.jpg',0),(142,155,'/assets/image/product/a39.jpg',0),(143,156,'/assets/image/product/a40.jpg',0),(144,157,'/assets/image/product/a41.jpg',0),(145,158,'/assets/image/product/a42.jpg',0),(146,159,'/assets/image/product/cocacola.png',0),(147,160,'/assets/image/product/cocacola.png',0),(148,161,'/assets/image/product/chilsung.png',0),(149,162,'/assets/image/product/chilsung.png',0),(150,163,'/assets/image/product/410.png',0),(151,164,'/assets/image/product/130.jpg',0),(152,165,'/assets/image/product/131.jpg',0),(153,166,'/assets/image/product/132.jpg',0),(154,167,'/assets/image/product/133.jpg',0),(155,168,'/assets/image/product/134.jpg',0),(156,169,'/assets/image/product/135.jpg',0),(157,170,'/assets/image/product/136.jpg',0),(158,171,'/assets/image/product/a47.jpg',0),(159,172,'/assets/image/product/a48.jpg',0),(160,173,'/assets/image/product/default.png',0),(161,174,'/assets/image/product/pepsi.png',0),(162,175,'/assets/image/product/pepsi.png',0),(163,176,'/assets/image/product/sprite.png',0),(164,177,'/assets/image/product/sprite.png',0),(165,178,'/assets/image/product/194.jpg',0),(166,179,'/assets/image/product/195.jpg',0),(167,180,'/assets/image/product/196.jpg',0),(168,181,'/assets/image/product/240.jpg',0),(169,182,'/assets/image/product/241.jpg',0),(170,183,'/assets/image/product/242.jpg',0),(171,184,'/assets/image/product/243.jpg',0),(172,185,'/assets/image/product/244.jpg',0),(173,186,'/assets/image/product/245.jpg',0),(174,187,'/assets/image/product/246.jpg',0),(175,188,'/assets/image/product/247.jpg',0),(176,189,'/assets/image/product/248.jpg',0),(177,190,'/assets/image/product/249.jpg',0),(178,191,'/assets/image/product/250.jpg',0),(179,192,'/assets/image/product/251.jpg',0),(180,193,'/assets/image/product/252.jpg',0),(181,194,'/assets/image/product/253.jpg',0),(182,195,'/assets/image/product/254.jpg',0),(183,196,'/assets/image/product/255.jpg',0),(184,197,'/assets/image/product/256.jpg',0),(185,198,'/assets/image/product/257.jpg',0),(186,199,'/assets/image/product/258.jpg',0),(187,200,'/assets/image/product/259.jpg',0),(188,201,'/assets/image/product/260.jpg',0),(189,202,'/assets/image/product/261.jpg',0),(190,203,'/assets/image/product/262.jpg',0),(191,204,'/assets/image/product/263.jpg',0),(192,205,'/assets/image/product/264.jpg',0),(193,206,'/assets/image/product/265.jpg',0),(194,207,'/assets/image/product/266.jpg',0),(195,208,'/assets/image/product/267.jpg',0),(196,209,'/assets/image/product/268.jpg',0),(197,210,'/assets/image/product/269.jpg',0),(198,211,'/assets/image/product/270.jpg',0),(199,212,'/assets/image/product/271.jpg',0),(200,213,'/assets/image/product/272.jpg',0),(201,214,'/assets/image/product/273.jpg',0),(202,215,'/assets/image/product/274.jpg',0),(203,216,'/assets/image/product/275.jpg',0),(204,217,'/assets/image/product/276.jpg',0),(205,218,'/assets/image/product/277.jpg',0),(206,219,'/assets/image/product/278.jpg',0),(207,220,'/assets/image/product/279.jpg',0),(208,221,'/assets/image/product/280.jpg',0),(209,222,'/assets/image/product/a72.jpg',0),(210,223,'/assets/image/product/a73.jpg',0),(211,224,'/assets/image/product/a74.jpg',0),(212,225,'/assets/image/product/a75.jpg',0),(213,226,'/assets/image/product/a76.jpg',0),(214,227,'/assets/image/product/a77.jpg',0),(215,228,'/assets/image/product/a78.jpg',0),(216,229,'/assets/image/product/290.jpg',0),(217,230,'/assets/image/product/298.png',0),(218,231,'/assets/image/product/299.png',0),(219,232,'/assets/image/product/300.png',0),(220,233,'/assets/image/product/301.png',0),(221,234,'/assets/image/product/302.png',0),(222,235,'/assets/image/product/303.png',0),(223,236,'/assets/image/product/298.png',0),(224,237,'/assets/image/product/298.png',0),(225,238,'/assets/image/product/298.png',0),(226,239,'/assets/image/product/299.png',0),(227,240,'/assets/image/product/299.png',0),(228,241,'/assets/image/product/299.png',0),(229,242,'/assets/image/product/310.png',0),(230,243,'/assets/image/product/310.png',0),(231,244,'/assets/image/product/310.png',0),(232,245,'/assets/image/product/310.png',0),(233,246,'/assets/image/product/314.jpg',0),(234,247,'/assets/image/product/314.jpg',0),(235,248,'/assets/image/product/314.jpg',0),(236,249,'/assets/image/product/314.jpg',0),(237,250,'/assets/image/product/300.png',0),(238,251,'/assets/image/product/302.png',0),(239,252,'/assets/image/product/302.png',0),(240,253,'/assets/image/product/302.png',0),(241,254,'/assets/image/product/322.png',0),(242,255,'/assets/image/product/322.png',0),(243,256,'/assets/image/product/322.png',0),(244,257,'/assets/image/product/325.jpg',0),(245,258,'/assets/image/product/325.jpg',0),(246,259,'/assets/image/product/325.jpg',0),(247,260,'/assets/image/product/325.jpg',0),(248,261,'/assets/image/product/329.png',0),(249,262,'/assets/image/product/329.png',0),(250,263,'/assets/image/product/329.png',0),(251,264,'/assets/image/product/303.png',0),(252,265,'/assets/image/product/333.png',0),(253,266,'/assets/image/product/333.png',0),(254,267,'/assets/image/product/333.png',0),(255,268,'/assets/image/product/333.png',0),(256,269,'/assets/image/product/337.png',0),(257,270,'/assets/image/product/337.png',0),(258,271,'/assets/image/product/337.png',0),(259,272,'/assets/image/product/337.png',0),(260,273,'/assets/image/product/341.png',0),(261,274,'/assets/image/product/341.png',0),(262,275,'/assets/image/product/341.png',0),(263,276,'/assets/image/product/341.png',0),(264,277,'/assets/image/product/345.png',0),(265,278,'/assets/image/product/346.png',0),(266,279,'/assets/image/product/default.png',0),(267,280,'/assets/image/product/348.png',0),(268,281,'/assets/image/product/298.png',0),(269,282,'/assets/image/product/299.png',0),(270,283,'/assets/image/product/300.png',0),(271,284,'/assets/image/product/302.png',0),(272,285,'/assets/image/product/303.png',0),(273,286,'/assets/image/product/310.png',0),(274,287,'/assets/image/product/314.jpg',0),(275,288,'/assets/image/product/329.png',0),(276,289,'/assets/image/product/579.png',0),(277,290,'/assets/image/product/580.png',0),(278,291,'/assets/image/product/581.png',0),(279,292,'/assets/image/product/348.png',0),(280,293,'/assets/image/product/348.png',0),(281,294,'/assets/image/product/348.png',0),(282,295,'/assets/image/product/291.jpg',0),(283,296,'/assets/image/product/292.jpg',0),(284,297,'/assets/image/product/293.jpg',0),(285,298,'/assets/image/product/294.jpg',0),(286,299,'/assets/image/product/295.jpg',0),(287,300,'/assets/image/product/296.jpg',0),(288,301,'/assets/image/product/391.png',0),(289,302,'/assets/image/product/392.png',0),(290,303,'/assets/image/product/393.jpg',0),(291,304,'/assets/image/product/394.png',0),(292,305,'/assets/image/product/395.png',0),(293,306,'/assets/image/product/396.png',0),(294,307,'/assets/image/product/397.png',0),(295,308,'/assets/image/product/398.jpg',0),(296,309,'/assets/image/product/399.jpg',0),(297,310,'/assets/image/product/400.jpg',0),(298,311,'/assets/image/product/401.jpg',0),(299,312,'/assets/image/product/402.jpg',0),(300,313,'/assets/image/product/403.png',0),(301,314,'/assets/image/product/404.png',0),(302,315,'/assets/image/product/405.png',0),(303,316,'/assets/image/product/406.png',0),(304,317,'/assets/image/product/407.png',0),(305,318,'/assets/image/product/408.png',0),(306,319,'/assets/image/product/409.jpg',0),(307,320,'/assets/image/product/413.jpg',0),(308,321,'/assets/image/product/414.jpg',0),(309,322,'/assets/image/product/415.png',0),(310,323,'/assets/image/product/416.png',0),(311,324,'/assets/image/product/425.jpg',0),(312,325,'/assets/image/product/353.jpg',0),(313,326,'/assets/image/product/354.jpg',0),(314,327,'/assets/image/product/355.jpg',0),(315,328,'/assets/image/product/356.jpg',0),(316,329,'/assets/image/product/357.jpg',0),(317,330,'/assets/image/product/358.jpg',0),(318,331,'/assets/image/product/359.jpg',0),(319,332,'/assets/image/product/360.jpg',0),(320,333,'/assets/image/product/361.jpg',0),(321,334,'/assets/image/product/362.jpg',0),(322,335,'/assets/image/product/363.jpg',0),(323,336,'/assets/image/product/364.jpg',0),(324,337,'/assets/image/product/365.jpg',0),(325,338,'/assets/image/product/366.jpg',0),(326,339,'/assets/image/product/367.jpg',0),(327,340,'/assets/image/product/368.jpg',0),(328,341,'/assets/image/product/369.jpg',0),(329,342,'/assets/image/product/370.jpg',0),(330,343,'/assets/image/product/371.jpg',0),(331,344,'/assets/image/product/372.jpg',0),(332,345,'/assets/image/product/373.jpg',0),(333,346,'/assets/image/product/374.jpg',0),(334,347,'/assets/image/product/375.jpg',0),(335,348,'/assets/image/product/376.jpg',0),(336,349,'/assets/image/product/377.jpg',0),(337,350,'/assets/image/product/378.jpg',0),(338,351,'/assets/image/product/379.jpg',0),(339,352,'/assets/image/product/380.jpg',0),(340,353,'/assets/image/product/381.jpg',0),(341,354,'/assets/image/product/382.jpg',0),(342,355,'/assets/image/product/383.jpg',0),(343,356,'/assets/image/product/384.jpg',0),(344,357,'/assets/image/product/385.jpg',0),(345,358,'/assets/image/product/386.jpg',0),(346,359,'/assets/image/product/353.jpg',0),(347,360,'/assets/image/product/353.jpg',0),(348,361,'/assets/image/product/353.jpg',0),(349,362,'/assets/image/product/353.jpg',0);
/*!40000 ALTER TABLE `imgpath_for_product_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imgpath_for_store_tbl`
--

DROP TABLE IF EXISTS `imgpath_for_store_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imgpath_for_store_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `store_idx` int(11) DEFAULT NULL,
  `imgpath` varchar(200) DEFAULT NULL,
  `type` tinyint(4) DEFAULT '0' COMMENT '이미지 타입\n0 - 메인 이미지\n1 - 서브 이미지',
  PRIMARY KEY (`idx`),
  KEY `img_store_fk_idx` (`store_idx`),
  CONSTRAINT `imgpath_store_fk` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='업체 내 이미지 정보 배열 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imgpath_for_store_tbl`
--

LOCK TABLES `imgpath_for_store_tbl` WRITE;
/*!40000 ALTER TABLE `imgpath_for_store_tbl` DISABLE KEYS */;
INSERT INTO `imgpath_for_store_tbl` VALUES (1,1,'/assets/image/store/1.png',0),(2,2,'/assets/image/store/2.png',0),(3,3,'/assets/image/store/3.png',0),(4,4,'/assets/image/store/4.png',0),(5,5,'/assets/image/store/5.png',0),(6,6,'/assets/image/store/6.png',0),(7,7,'/assets/image/store/7.png',0),(8,8,'/assets/image/store/8.png',0),(9,9,'/assets/image/store/9.png',0);
/*!40000 ALTER TABLE `imgpath_for_store_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `initial_consonant_for_dsite_tbl`
--

DROP TABLE IF EXISTS `initial_consonant_for_dsite_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `initial_consonant_for_dsite_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `initial_consonant` varchar(45) DEFAULT NULL,
  `delivery_site_idx` int(11) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_ic_dsite_idx` (`delivery_site_idx`),
  CONSTRAINT `fk_ic_dsite` FOREIGN KEY (`delivery_site_idx`) REFERENCES `delivery_site_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1408 DEFAULT CHARSET=utf8 COMMENT='배달지 초성검색용';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `initial_consonant_for_dsite_tbl`
--

LOCK TABLES `initial_consonant_for_dsite_tbl` WRITE;
/*!40000 ALTER TABLE `initial_consonant_for_dsite_tbl` DISABLE KEYS */;
INSERT INTO `initial_consonant_for_dsite_tbl` VALUES (939,'ㅎㄱㄷㅎㄱ',1),(940,'ㄷㄱㄷㅎㄱ',2),(941,'ㅎㄱㄱㅍㄷㅎ',4),(942,'ㅎㄹㅅㅅㄷㅎㄱ',5),(943,'ㄱㅇㄱㄱㄷㅎㄱ',6),(944,'ㄱㅇㄷㄹㄷㅎ',7),(945,'ㄷㅇㄷㅎ',8),(946,'ㅅㅈㅇㅅㄷㅎㄱ',9),(947,'ㅅㄱㄷㅎㄱ',10),(948,'ㅅㄱㄷㅎㄱ',11),(949,'ㅅㅎㄷㅎㄱ',12),(950,'ㅊㅊㄱㅇㄷㅎㄱ',13),(951,'ㄱㅌㄹㄱㄷㄷㅎㄱ',14),(952,'ㄱㄹㅇㅈㄷㅎㄱ',15),(953,'ㄱㄹㅇㅈㄷㅎㄱ',16),(954,'ㄱㅇㄷㅎㄱ',17),(955,'ㄱㅇㄷㅎㄱ',18),(956,'ㄱㄷㄷㅎㄱ',19),(957,'ㄱㄷㄷㅎㄱ',20),(958,'ㄱㄷㄷㅎㄱ',21),(959,'ㅅㅈㄷㅎㄱ',22),(960,'ㅇㅅㄷㅎㄱ',23),(961,'ㅎㄹㄷㅎㄱ',24),(962,'ㅎㄹㄷㅎㄱ',25),(963,'ㅎㅈㄷㅎㄱ',26),(964,'ㅎㄱㅍㄹㅌIII',27),(965,'ㅎㄱㅍㄹㅌIII',28),(966,'ㅎㄱㅍㄹㅌIII',29),(967,'ㄱㅁㄷㅎㄱ',30),(968,'ㄱㅂㄷㅎㄱ',31),(969,'ㄱㅍㄷㅎㄱ',32),(970,'ㄴㅎㄷㅎㄱ',33),(971,'ㄷㄹㄷㅎㄱ',34),(972,'ㄷㄴㅂㄱㄷㅎㄱ',35),(973,'ㄷㅇㅂㅅㅇㅅㄷㅎㄱ',36),(974,'ㄷㅇㄷㅎㄱ',37),(975,'ㅅㅇㅇㅅㄷㅎㄱ',38),(976,'ㅅㅈㄷㅎㄱ',39),(977,'ㅅㅇㄱㅎㄷㅎㄱ',40),(978,'ㅅㄱㄷㅎㄱ',41),(979,'ㅇㅅㄷㅎㄱ',42),(980,'ㅇㅅㄷㅎㄱ',43),(981,'ㅇㅅㄷㅎㄱ',44),(982,'ㅇㅇㅅㄷㄷㅎㄱ',45),(983,'ㅇㅈㅅㅁㄷㅎㄱ',46),(984,'ㅊㄱㅁㅎㅅㅇㄷㅎㄱ',47),(985,'ㅎㄱㅂㅈㄷㅎㄱ',48),(986,'ㄱㄱㄱㅎㄱㅅㄷㅎㄱ',49),(987,'ㄱㅇㅈㅁㄷㅎ',50),(988,'ㄱㅇㅇㅅㄷㅎㄱ',51),(989,'ㄱㅈㄷㅎㄱ',52),(990,'ㄷㅅㅇㄷㅎㄱ',53),(991,'ㄷㅇㄱㄱㄷㅎㄱ',54),(992,'ㅂㅊㄷㅎㄱ',55),(993,'ㅅㅇㅂㄱㄷㅎ',56),(994,'ㅅㅇㅇㅈㄷㅎㄱ',57),(995,'ㅅㅇㅅㄷㅎㄱ',58),(996,'ㅅㅎㄷㅎㄱ',59),(997,'ㅇㅈㄷㅎㄱ',60),(998,'ㅇㅎㄷㅎㄱ',61),(999,'ㅈㅇㄷㅎㄱ',62),(1000,'ㅎㄱㄱㄱㄷㅎㄱ',63),(1001,'ㅎㄱㅊㄷㄷㅎ',64),(1002,'ㄱㅇㄱㅇㄷㅎㄱ',65),(1003,'ㄱㅊㄷㅎㄱ',66),(1004,'ㄱㅌㄹㄷㅎㄱ',67),(1005,'ㄱㄴㄷㅎㄱ',68),(1006,'ㄱㄱㄷㅎㄱ',69),(1007,'ㄱㄷㄷㅎㄱ',70),(1008,'ㄷㄱㄷㅎㄱ',71),(1009,'ㄷㅈㄷㅎㄱ',72),(1010,'ㄹㅌㄷㅎㄱ',73),(1011,'ㅁㅈㄷㅎㄱ',74),(1012,'ㅅㅇㅅㅎㄷㅎㄱ',75),(1013,'ㅅㅇㅈㅅㄷㅎㄱ',76),(1014,'ㅅㄱㄷㅎㄱ',77),(1015,'ㅅㅇㄱㅌㄹㄷㅎㄱ',78),(1016,'ㅅㅇㄷㅎㄱ',79),(1017,'ㅅㄱㄷㅎㄱ',80),(1018,'ㅅㅎㄷㅎㄱ',81),(1019,'ㅅㅎㄷㅎㄱ',82),(1020,'ㅇㅅㅇㅇㅎㅅㅎㄷㅎㄱ',83),(1021,'ㅇㅈㄷㅎㄱ',84),(1022,'ㅇㅇㄷㅎㄱ',85),(1023,'ㅇㅇㅇㅅㄷㅎㄱ',86),(1024,'ㅇㅇㄷㅎㄱ',87),(1025,'ㅇㅈㄷㅎㄱ',88),(1026,'ㅈㅂㄷㅎㄱ',89),(1027,'ㅈㅇㄷㅎㄱ',90),(1028,'ㅈㅇㅅㄱㄷㅎㄱ',91),(1029,'ㅊㅇㄱㅎㄷㅎㄱ',92),(1030,'ㅋㅂㄷㅎㄱ',93),(1031,'ㅍㅌㄷㅎㄱ',94),(1032,'ㅎㄱㄷㅎㄱ',95),(1033,'ㅎㄱㅅㅇㄱㅅㄷㅎㄱ',96),(1035,'ㅎㅂㄷㅎㄱ',98),(1036,'ㅎㅅㄷㅎㄱ',99),(1037,'ㅎㅅㄷㅎㄱ',100),(1038,'ㅎㅇㄷㅎㄱ',101),(1039,'ㅎㅅㄷㅎㄱ',102),(1040,'ㅎㄱㄷㅎㄱ',103),(1041,'ㅎㄱㅅㅇㄱㅅㄷㅎㄱ',104),(1042,'ㄱㅅㄱㅅㄱㅎㄱ',105),(1043,'ㅅㄱㅅㅇㅂㄷㅎ',106),(1044,'ㅅㅅㅈㅈㄱㄱㄷㅎㄱ',107),(1045,'ㄱㅈㅅㅇㅂㄷㅎㄱ',108),(1046,'ICTㅍㄹㅌㄷㅎ',109),(1047,'ㅎㄱㅍㄹㅌI',110),(1048,'ㅎㄱㅍㄹㅌII',111),(1049,'ㅎㄱㅍㄹㅌ',112),(1050,'ㄱㅈㄷㅎㄱ',113),(1051,'ㄱㅎㄷㅎㄱ',114),(1052,'ㅁㅅㄷㅎㄱ',115),(1053,'ㅇㅇㄱㅇㄷㅎ',116),(1054,'ㅈㅈㅂㄱㄷㅎㄱ',117),(1055,'ㅊㅇㅁㅅㄷㅎ',118),(1056,'ㄱㄴㄷㄹㄱㅊㄷㅎ',119),(1057,'ㄱㄴㄷㄹㄴㅎㄷㅎ',120),(1058,'ㄷㅇㄱㅎㄱㅅㄷㅎㄱ',121),(1059,'ㅊㅅㄷㅎ',122),(1060,'ㅎㄱㅅㄱㄱㄷㅎㄱ',123),(1061,'ㅈㅈㄱㅇㄷㅎㄱ',124),(1062,'ㄱㅇㄷㅎㄱ',125),(1063,'ㄱㄴㄱㅎㄱㅅㄷㅎㄱ',126),(1064,'ㄱㄴㄷㅎㄱ',127),(1065,'ㄱㅅㄷㅎㄱ',128),(1066,'ㅂㅅㅈㅅㄷㅎㄱ',129),(1067,'ㅇㅅㄷㅎㄱ',130),(1068,'ㅇㅈㄷㅎㄱ',131),(1069,'ㅊㅅㄷㅎㄱ',132),(1070,'ㅊㅇㄷㅎㄱ',133),(1071,'ㅎㄱㄱㅈㄷㅎㄱ',134),(1072,'ㅇㅅㄷㅎㄱ(ㅅㅇㄷ)',135),(1073,'ㅈㅈㅅㅇㄷㅎㄱ',136),(1074,'ㄷㅇㅈㅅㅎㅇㄱㄱㄷㅎ',137),(1075,'ㅅㅅㅈㄱㅇㄱㄱㄷㅎ',138),(1076,'ㅎㄱㅍㄹㅌ',139),(1077,'ㅎㄱㅍㄹㅌ',140),(1078,'ㄱㅌㄹㅅㅈㄷㅎㄱ',141),(1079,'ㄱㅂㅈㅁㄷㅎㄱ',142),(1080,'ㄷㄱㅁㄹㄷㅎㄱ',143),(1081,'ㅅㄹㅂㄷㅎㄱ',144),(1082,'ㅅㄹㄷㅎㄱ',145),(1083,'ㅅㄷㄷㅎㄱ',146),(1084,'ㅇㄷㄱㅎㄷㅎㄱ',147),(1085,'ㅍㅎㄷㅎㄱ',148),(1086,'ㄱㅂㄱㅎㄷㅎㄱ',149),(1087,'ㄱㅂㄷㄹㄷㅎㄱ',150),(1088,'ㄱㅁㄷㅎㄱ',151),(1089,'ㄱㅊㄱㅎㄷㅎㄱ',152),(1090,'ㄱㅊㄷㅎ',153),(1091,'ㄷㄱㄷㅎㄱ',154),(1092,'ㅁㄱㄷㅎㄱ',155),(1093,'ㅇㄴㅇㄱㅇㄷㅎ',156),(1094,'ㅎㅅㄷㅎㄱ',157),(1095,'ㄱㅇㄷㅎㄱ',158),(1096,'ㄱㅇㄷㅎㄱ',159),(1097,'ㄱㅇㄷㅎㄱ',160),(1098,'ㄱㅈㄷㅎㄱ',161),(1099,'ㄱㅇㄱㄱㄷㅎㄱ',162),(1100,'ㄱㅊㄷㅎㄱ',163),(1101,'ㄷㄱㄱㅌㄹㄷㅎㄱ',164),(1102,'ㄷㄱㄷㅎㄱ',165),(1103,'ㄷㄱㅇㅅㄷㅎㄱ',166),(1104,'ㄷㄱㅇㄱㅇㄷㅎㄱ',167),(1105,'ㄷㄱㅎㅇㄷㅎㄱ',168),(1106,'ㄷㅅㄷㅎㄱ',169),(1107,'ㄷㄱㄷㅎㄱ',170),(1108,'ㄷㅇㄷㅎㄱ',171),(1109,'ㅇㄷㄷㅎㄱ',172),(1110,'ㅇㄴㄷㅎㄱ',173),(1111,'ㅇㄴㅅㅎㄷㅎㄱ',174),(1112,'ㅇㄷㄷㅎㄱ',175),(1113,'ㅍㅎㄱㄱㄷㅎㄱ',176),(1114,'ㅎㄷㄷㅎㄱ',177),(1115,'ㄱㅇㄷㅎㄱ',178),(1116,'ㅇㄴㅅㅇㅂㄷㅎㄱ',179),(1117,'ㅍㅅㅋㄱㅅㄷㅎ',180),(1118,'ㄷㄱㅅㅇㅂㄷㅎㄱ',181),(1119,'ㅎㄱㅂㅈㅅㅇㅂㄷㅎ',182),(1120,'ㅎㄱㅍㄹㅌⅥ',183),(1121,'ㅎㄱㅍㄹㅌ',184),(1122,'ㄱㅈㅂㄱㄷㅎㄱ',185),(1123,'ㄱㄷㄱㅎㄷㅎㄱ',186),(1124,'ㅅㅇㄷㅎㄱ',187),(1125,'ㅅㅇㄷㅎ',188),(1126,'ㅈㅅㄱㅎㄷㅎㄱ',189),(1127,'ㄷㄱㄷㅎㄱ',190),(1128,'ㅈㅅㅇㄱㄷㅎㄱ',191),(1129,'ㄱㅈㄱㅇㄷㅎㄱ',192),(1130,'ㄱㅅㄷㅎㄱ',193),(1131,'ㄱㅈㄱㅎㄱㅅㅇ',194),(1132,'ㄱㅈㄷㅎㄱ',195),(1133,'ㄱㅈㅇㅈㄷㅎㄱ',196),(1134,'ㄴㅂㄷㅎㄱ',197),(1135,'ㅅㅇㄷㅎㄱ',198),(1136,'ㅈㄴㄷㅎㄱ',199),(1137,'ㅈㅅㄷㅎㄱ',200),(1138,'ㅎㄴㄷㅎㄱ',201),(1139,'ㅎㄴㅅㅎㄷㅎㄱ',202),(1140,'ㄱㅈㄷㅎㄱ(ㅅㅇㄷ)',203),(1141,'ㅎㄱㅍㄹㅌ',204),(1142,'ㄱㅁㅁㅎㄷㅎㄱ',205),(1143,'ㄷㄱㅂㄱㄷㅎㄱ',206),(1144,'ㅅㅅㄷㅎㄱ',207),(1145,'ㄷㄱㄱㅇㄷㅎㄱ',208),(1146,'ㄷㄱㄱㅎㄷㅎㄱ',209),(1147,'ㅇㄴㅇㄱㄷㅎㄱ',210),(1148,'ㅇㅈㅈㅁㄷㅎ',211),(1149,'ㄷㄱㄱㅇㄷㅎㄱ',212),(1150,'ㄱㅂㄷㅎㄱ',213),(1151,'ㄱㅁㄷㅎㄱ',214),(1152,'ㄷㄱㄱㅂㄱㅎㄱㅅㅇ',215),(1153,'ㅇㅈㅅㅇㅂㄷㅎ',216),(1154,'ㅎㄱㅍㄹㅌ',217),(1155,'ㅎㄱㅍㄹㅌ',218),(1156,'ㅇㅅㅈㅂㄷㅎ',219),(1157,'ㄷㄷㄷㅎㄱ',220),(1158,'ㄷㅈㄱㅎㄱㅅㄷㅎㄱ',221),(1159,'ㄷㅈㅂㄱㄷㅎㄱ',222),(1160,'ㅇㅅㄱㅇㄷㅎ',223),(1161,'ㄱㅇㄷㅎㄱ',224),(1162,'ㄷㅈㄷㅎㄱ',225),(1163,'ㄷㅈㅅㅎㄷㅎㄱ',226),(1164,'ㅁㅇㄷㅎㄱ',227),(1165,'ㅂㅈㄷㅎㄱ',228),(1166,'ㅇㅅㄷㅎㄱ',229),(1167,'ㅇㅈㄷㅎㄱ',230),(1168,'ㅊㄴㄷㅎㄱ',231),(1169,'ㅊㄹㅅㅎㄷㅎㄱ',232),(1170,'ㅎㄱㄱㅎㄱㅅㅇ',233),(1171,'ㅎㄴㄷㅎㄱ',234),(1172,'ㅎㅂㄷㅎㄱ',235),(1173,'ㅇㅅㄷㅎㄱ',236),(1174,'ㅎㅂㄷㅎㄱ',237),(1175,'ㄷㅈㅅㅎㄱ',238),(1176,'LHㅌㅈㅈㅌㄷㅎㄱ',239),(1177,'ㄱㅇㅅㅇㅂㄷㅎㄱ',240),(1178,'ㅎㄱㅍㄹㅌIV',241),(1179,'ㄱㄴㅈㅂㄷㅎㄱ',242),(1180,'ㄷㅂㅅㄷㅎㄱ',243),(1181,'ㄷㅈㄷㅎㄱ',244),(1182,'ㄷㄷㄷㅎㄱ',245),(1183,'ㄷㅇㄱㅎㄷㅎㄱ',246),(1184,'ㅂㅅㄱㅅㄷㅎㄱ',247),(1185,'ㅂㅅㄱㅎㄱㅅㄷㅎㄱ',248),(1186,'ㅂㅅㅇㅈㄷㅎㄱ',249),(1187,'ㅂㅅㅇㅅㄷㅎㄱ',250),(1188,'ㅅㅅㅇㄱㅇㄷㅎ',251),(1189,'ㅂㅅㄱㅇㄷㅎㄱ',252),(1190,'ㄱㅅㄷㅎㄱ',253),(1191,'ㄱㅅㄷㅎㄱ',254),(1192,'ㄷㅁㄷㅎㄱ',255),(1193,'ㄷㅅㄷㅎㄱ',256),(1194,'ㄷㅇㄷㅎㄱ',257),(1195,'ㄷㅇㄷㅎㄱ',258),(1196,'ㅂㄱㄷㅎㄱ',259),(1197,'ㅂㅅㄱㅌㄹㄷㅎㄱ',260),(1198,'ㅂㅅㄷㅎㄱ',261),(1199,'ㅂㅅㅇㄱㅇㄷㅎㄱ',262),(1200,'ㅅㄹㄷㅎㄱ',263),(1201,'ㅇㅅㄷㅎㄱ',264),(1202,'ㅇㅈㄷㅎㄱ',265),(1203,'ㅎㄱㅎㅇㄷㅎㄱ',266),(1204,'ㄷㅁㅈㅂㄷㅎㄱ',267),(1205,'ㅇㅅㄷㅎㄱ(ㅅㅇㄷ)',268),(1206,'ㅂㅅㄷㅈㅌㄷㅎㄱ',269),(1207,'ㅎㅅㅅㅇㅂㄷㅎㄱ',270),(1208,'ㅎㄱㅍㄹㅌⅦ',271),(1209,'ㄷㅇㅁㄹㄷㅎㄱ',272),(1210,'ㅁㅈㅈㅁㄷㅎ',273),(1211,'ㅂㅎㅇㅈㄷㅎㄱ',274),(1212,'ㅅㅇㅂㄱㄷㅎㄱ',275),(1213,'ㅅㅇㅇㅈㄱㅎㄷㅎㄱ',276),(1214,'ㅈㅅㅈㄱㅎㄷㅎ',277),(1215,'ㅅㅇㄷㅎㄱ',278),(1216,'ㅅㅇㅇㅈㄷㅎㄱ',279),(1217,'ㅇㄷㄷㅎㄱ',280),(1218,'ㅎㅇㅇㅈㄷㅎㄱ',281),(1219,'ㅅㅇㄱㅇㄷㅎㄱ',282),(1220,'ㄱㅌㄹㄷㅎㄱ',283),(1221,'ㄱㅌㄹㄷㅎㄱ',284),(1222,'ㄱㄹㄱㅅㅎㄷㅎㄱ',285),(1223,'ㄱㄱㄷㅎㄱ',286),(1224,'ㄱㄱㄷㅎㄱ',287),(1225,'ㄱㅎㄷㅎㄱ',288),(1226,'ㄱㄹㄷㅎㄱ',289),(1227,'ㄱㅇㄷㅎㄱ',290),(1228,'ㄱㅁㄷㅎㄱ',291),(1229,'ㄱㄹㅅㄷㄷㅎㄱ',292),(1230,'ㄷㅅㅇㅈㄷㅎㄱ',293),(1231,'ㄷㄱㄷㅎㄱ',294),(1232,'ㄷㄷㅇㅈㄷㅎㄱ',295),(1233,'ㅁㅈㄷㅎㄱ',296),(1234,'ㅅㅇㄷㅎㄱ',297),(1235,'ㅅㅁㄷㅎㄱ',298),(1236,'ㅅㄱㄷㅎㄱ',299),(1237,'ㅅㄱㄷㅎㄱ',300),(1238,'ㅅㅇㄱㅎㄱㅅㄷㅎㄱ',301),(1239,'ㅅㅇㄱㄷㄷㅎㄱ',302),(1240,'ㅅㅇㄷㅎㄱ',303),(1241,'ㅅㅇㅅㄹㄷㅎㄱ',304),(1242,'ㅅㅇㅇㅈㄷㅎㄱ',305),(1243,'ㅅㄱㅎㄷㅎㄱ',306),(1244,'ㅅㄱㄱㄷㅎㄱ',307),(1245,'ㅅㅅㅇㅈㄷㅎㄱ',308),(1246,'ㅅㅈㄷㅎㄱ',309),(1247,'ㅅㅁㅇㅈㄷㅎㄱ',310),(1248,'ㅅㅅㄷㅎㄱ',311),(1249,'ㅇㅅㄷㅎㄱ',312),(1250,'ㅇㅎㅇㅈㄷㅎㄱ',313),(1251,'ㅈㄹㅎㅅㅎㄷㅎㄱ',314),(1252,'ㅈㅇㄷㅎㄱ',315),(1253,'ㅊㅅㄷㅎㄱ',316),(1254,'ㅊㄱㅇㅅㄷㅎㄱ',317),(1255,'ㅎㄱㅅㅅㄷㅎㄱ',318),(1256,'ㅎㄱㅇㄱㅇㄷㅎㄱ',319),(1257,'ㅎㄱㅊㅇㄷㅎㄱ',320),(1258,'ㅎㅅㄷㅎㄱ',321),(1259,'ㅎㅇㄷㅎㄱ',322),(1260,'ㅎㅇㅅㅎㄷㅎㄱ',323),(1261,'ㅎㅇㄷㅎㄱ',324),(1262,'ㅎㄱㅂㅅㅌㅅㄷㅎㄱ',325),(1263,'ㅅㅇㄱㅎㄱㅅㄷㅎㄱ',326),(1264,'ㅈㅅㄷㅎ',327),(1265,'ㅎㄱㅇㅅㅈㅎㅎㄱ',328),(1266,'SPCㅅㅍㄱㅎㄷㅎ',329),(1267,'KDBㄱㅇㄷㅎㄱ',330),(1268,'ㄱㅎㅅㅇㅂㄷㅎㄱ',331),(1269,'ㄱㄹㅅㅇㅂㄷㅎㄱ',332),(1270,'ㄷㅈㅌㅅㅇㅁㅎㅇㅅㄷㅎㄱ',333),(1271,'ㅅㅇㅂㅎㄱㅇㄱㅇㄷㅎㄱ',334),(1272,'ㅅㅇㄷㅈㅌㄷㅎㄱ',335),(1273,'ㅅㅇㅅㅇㅂㄷㅎㄱ',336),(1274,'ㅅㅈㅅㅇㅂㄷㅎㄱ',337),(1275,'ㅅㅅㅅㅇㅂㄷㅎㄱ',338),(1276,'ㅇㄹㅅㅇㅂㄷㅎㄱ',339),(1277,'ㅎㅇㅅㅇㅂㄷㅎㄱ',340),(1278,'ㄱㅈㅇㅅㄷㅎ',341),(1279,'ㅂㅅㅇㅅㄷㅎㄱ',342),(1280,'ㅈㅎㅇㅅㄷㅎ',343),(1281,'ㅈㅎㅇㅅㄷㅎ',344),(1282,'ㅎㄱㅍㄹㅌI',345),(1283,'ㅎㄱㅍㄹㅌⅠ',346),(1284,'ㅎㄱㅇㅅㄷㅎㄱ',347),(1285,'ㄱㄹㄷㅎㄱ',348),(1286,'ㄷㅈㄱㅌㄹㄷㅎㄱ',349),(1287,'ㅎㅇㄷㅎㄱ',350),(1288,'ㅇㅅㄱㅎㄷㅎㄱ',351),(1289,'ㅊㅎㅂㄱㄷㅎㄱ',352),(1290,'ㅇㅅㄱㅎㄱㅅㄷㅎㄱ',353),(1291,'ㅇㅅㄷㅎㄱ',354),(1292,'ㅎㄷㅈㄱㅇㄱㄱㄷㅎ',355),(1293,'ㅎㄱㅍㄹㅌVII',356),(1294,'ㄱㅇㅇㅈㄷㅎㄱ',357),(1295,'ㅇㅎㄱㅇㅈㅁㄷㅎ',358),(1296,'ㄱㅊㄱㄷㅎ',359),(1297,'ㅇㅊㅈㄴㄷㅎㄱ',360),(1298,'ㅇㅊㅈㅁㄷㅎ',361),(1299,'ㄱㅇㄱㅇㄷㅎㄱ',362),(1300,'ㅇㅇㄷㅎㄱ',363),(1301,'ㅇㅊㄱㅌㄹㄷㅎㄱ',364),(1302,'ㅇㅊㄱㅌㄹㄷㅎㄱ',365),(1303,'ㅇㅊㄷㅎㄱ',366),(1304,'ㅇㅎㄷㅎㄱ',367),(1305,'ㅎㄱㅍㄹㅌ',368),(1306,'ㄱㄱㄹㄷㅎㄱ',369),(1307,'ㄱㅇㅂㄱㄷㅎㄱ',370),(1308,'ㄷㅇㅇㅈㄷㅎㄱ',371),(1309,'ㅁㅍㄱㅎㄷㅎㄱ',372),(1310,'ㅊㅇㄷㅎㄱ',373),(1311,'ㅅㅊㅈㅇㄷㅎㄱ',374),(1312,'ㅈㄴㄱㅎㄷㅎㄱ',375),(1313,'ㅈㄴㄷㄹㄷㅎㄱ',376),(1314,'ㅎㅇㄷㅎ',377),(1315,'ㄱㅈㄱㅌㄹㄷㅎㄱ',378),(1316,'ㄷㅅㄷㅎㄱ',379),(1317,'ㅁㅍㄱㅌㄹㄷㅎㄱ',380),(1318,'ㅁㅍㄷㅎㄱ',381),(1319,'ㅁㅍㅎㅇㄷㅎㄱ',382),(1320,'ㅅㅎㄷㅎㄱ',383),(1321,'ㅅㅊㄷㅎㄱ',384),(1322,'ㅇㅅㅅㅎㄷㅎㄱ',385),(1323,'ㅈㄴㄷㅎㄱ',386),(1324,'ㅊㄷㄷㅎㄱ',387),(1325,'ㅎㄹㄷㅎㄱ',388),(1326,'ㅊㄷㄷㅎㄱ',389),(1327,'ㅎㄹㄷㅎㄱ',390),(1328,'ㅎㄱㅍㄹㅌV',391),(1329,'ㄱㅅㄱㅎㄷㅎㄱ',392),(1330,'ㄱㅈㄷㅎㄱ',393),(1331,'ㅅㅎㄷㅎ',394),(1332,'ㅇㄱㅂㄱㄷㅎㄱ',395),(1333,'ㅈㅈㄱㅈㄷㅎ',396),(1334,'ㅎㄱㄴㅅㅅㄷㅎ',397),(1335,'ㅂㅈㅇㅅㄷㅎㄱ',398),(1336,'ㅈㅂㄱㅎㄷㅎㄱ',399),(1337,'ㅈㅈㅂㅈㄷㅎㄱ',400),(1338,'ㅈㅈㄱㅇㄷㅎㄱ',401),(1339,'ㄱㅅㄷㅎㄱ',402),(1340,'ㅅㄴㄷㅎㄱ',403),(1341,'ㅇㅅㄷㅎㄱ',404),(1342,'ㅇㅇㅇㅅㄷㅎㄱ',405),(1343,'ㅇㅅㄷㅎㄱ',406),(1344,'ㅇㄱㄷㅎㄱ',407),(1345,'ㅈㅂㄷㅎㄱ',408),(1346,'ㅈㅈㄷㅎㄱ',409),(1347,'ㅎㅇㅈㅅㄷㅎㄱ',410),(1348,'ㅎㅇㄷㅎㄱ',411),(1349,'ㅇㄱㄷㅈㅌㄷㅎㄱ',412),(1350,'ㅎㄱㅍㄹㅌV',413),(1351,'ㅎㄱㅍㄹㅌ',414),(1352,'ㅈㅈㅅㅇㅈㅂㄷㅎ',415),(1353,'ㅈㅈㅎㄹㄷㅎㄱ',416),(1354,'ㅈㅈㄱㄱㄷㅎㄱ',417),(1355,'ㅈㅈㄱㅈㄷㅎㄱ',418),(1356,'ㅈㅈㄷㅎㄱ',419),(1357,'ㅌㄹㄷㅎㄱ',420),(1358,'ㅂㅅㅁㅎㄷㅎㄱ',421),(1359,'ㅅㅅㄷㅎㄱ',422),(1360,'ㅊㄴㄷㄹㄷㅎㄱ',423),(1361,'ㅎㅈㄷㅎㄱ',424),(1362,'ㅇㅈㅈㄷㅊㄷㅎ',425),(1363,'ㅊㅇㅇㅇㄷㅎ',426),(1364,'ㄱㅈㄱㅇㄷㅎㄱ',427),(1365,'ㄱㅇㄷㅎㄱ',428),(1366,'ㄱㅈㄷㅎㄱ',429),(1367,'ㄱㄱㄷㅎㄱ',430),(1368,'ㄴㅅㄹㄷㅎㄱ',431),(1369,'ㄴㅅㅇㄷㅎㄱ',432),(1370,'ㄷㄱㄷㅎㄱ',433),(1371,'ㄷㄱㄷㅎㄱ',434),(1372,'ㅂㅅㄷㅎㄱ',435),(1373,'ㅅㅁㄷㅎㄱ',436),(1374,'ㅅㅁㄷㅎㄱ',437),(1375,'ㅅㅎㄷㅎㄱ',438),(1376,'ㅅㅊㅎㄷㅎㄱ',439),(1377,'ㅈㅂㄷㅎㄱ',440),(1378,'ㅎㄱㄱㅅㄱㅇㄷㅎㄱ',441),(1379,'ㅎㄱㅈㅌㅁㅎㄷㅎㄱ',442),(1380,'ㅎㅅㄷㅎㄱ',443),(1381,'ㅎㅅㄷㅎㄱ',444),(1382,'ㄴㅅㅇㄷㅎㄱ',445),(1383,'ㅊㅇㄷㅎㄱ',446),(1384,'ㄱㄹㅂㅅㅇㅂㄷㅎㄱ',447),(1385,'ㅎㄱㅍㄹㅌIV',448),(1386,'ㅎㄱㅍㄹㅌIV',449),(1387,'ㅎㄱㅍㄹㅌ',450),(1388,'ㄱㄷㄷㅎㄱ',451),(1389,'ㄷㅇㄷㅎㄱ',452),(1390,'ㅊㅂㄷㄹㄷㅎ',453),(1391,'ㅊㅂㅂㄱㄱㅎㄷㅎㄱ',454),(1392,'ㅊㅊㄷㅎㄱ',455),(1393,'ㅊㅈㄱㅇㄷㅎㄱ',456),(1394,'ㄱㄱㄷㅎㄱ',457),(1395,'ㄱㄷㄷㅎㄱ',458),(1396,'ㄲㄷㄴㄷㅎㄱ',459),(1397,'ㅅㅇㄷㅎㄱ',460),(1398,'ㅅㅁㄷㅎㄱ',461),(1399,'ㅇㄷㄷㅎㄱ',462),(1400,'ㅈㅇㄷㅎㄱ',463),(1401,'ㅊㅈㄷㅎㄱ',464),(1402,'ㅊㅂㄷㅎㄱ',465),(1403,'ㅎㄱㄱㅇㄷㅎㄱ',466),(1404,'ㅎㄱㄱㅌㄷㅎㄱ',467),(1405,'ㅎㄱㄱㅌㄷㅎㄱ',468),(1406,'ㅅㅂㅇㅊㅎㅅㅎㄱ',469),(1407,'ㅎㄱㅍㄹㅌIV',470);
/*!40000 ALTER TABLE `initial_consonant_for_dsite_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_for_cart_tbl`
--

DROP TABLE IF EXISTS `item_for_cart_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_for_cart_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `cart_idx` int(11) DEFAULT NULL,
  `product_idx` int(11) DEFAULT NULL,
  `store_idx` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `requirement` text,
  `parent_item_idx` int(11) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_citem_product_idx` (`product_idx`),
  KEY `fk_citem_store_idx` (`store_idx`),
  KEY `fk_citem_parent_idx` (`parent_item_idx`),
  KEY `fk_citem_cart_idx` (`cart_idx`),
  CONSTRAINT `fk_citem_cart` FOREIGN KEY (`cart_idx`) REFERENCES `cart_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_citem_parent` FOREIGN KEY (`parent_item_idx`) REFERENCES `item_for_cart_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_citem_product` FOREIGN KEY (`product_idx`) REFERENCES `product_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_citem_store` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=168 DEFAULT CHARSET=utf8 COMMENT='카트 내 물품 관리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_for_cart_tbl`
--

LOCK TABLES `item_for_cart_tbl` WRITE;
/*!40000 ALTER TABLE `item_for_cart_tbl` DISABLE KEYS */;
INSERT INTO `item_for_cart_tbl` VALUES (148,39,359,9,1,NULL,NULL),(150,40,119,3,1,NULL,NULL),(153,42,178,5,1,NULL,NULL),(154,42,178,5,1,NULL,NULL),(161,43,178,5,1,NULL,NULL),(162,43,181,6,1,NULL,NULL),(163,43,181,6,1,NULL,NULL),(164,43,280,7,3,NULL,NULL),(165,43,282,7,1,NULL,NULL),(167,46,117,2,1,NULL,NULL);
/*!40000 ALTER TABLE `item_for_cart_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item_for_order_tbl`
--

DROP TABLE IF EXISTS `item_for_order_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item_for_order_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `order_idx` int(11) DEFAULT NULL,
  `store_idx` int(11) DEFAULT NULL,
  `product_idx` int(11) DEFAULT NULL,
  `quantity` int(11) NOT NULL DEFAULT '1',
  `requirement` text,
  `parent_item_idx` int(11) DEFAULT NULL,
  `unit_amount` int(11) DEFAULT NULL COMMENT '해당 제품 가격 로깅용',
  PRIMARY KEY (`idx`),
  KEY `fk_oi_order_idx` (`order_idx`),
  KEY `fk_oi_store_idx` (`store_idx`),
  KEY `fk_oi_prod_idx` (`product_idx`),
  CONSTRAINT `fk_oi_order` FOREIGN KEY (`order_idx`) REFERENCES `order_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_oi_prod` FOREIGN KEY (`product_idx`) REFERENCES `product_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_oi_store` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=498 DEFAULT CHARSET=utf8 COMMENT='주문 항목 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_for_order_tbl`
--

LOCK TABLES `item_for_order_tbl` WRITE;
/*!40000 ALTER TABLE `item_for_order_tbl` DISABLE KEYS */;
INSERT INTO `item_for_order_tbl` VALUES (492,144,5,178,1,NULL,NULL,8500),(493,144,9,359,1,NULL,NULL,5400),(494,145,5,178,1,NULL,NULL,8500),(495,145,9,359,1,NULL,NULL,5400),(496,146,5,178,1,NULL,NULL,8500),(497,146,5,178,1,NULL,NULL,8500);
/*!40000 ALTER TABLE `item_for_order_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_tbl`
--

DROP TABLE IF EXISTS `job_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `min_salary` int(11) DEFAULT NULL COMMENT '해당 직무의 최소 급여\n기간 단위 : 월 (month)\n화폐 단위 : 원 (won)\n',
  `max_salary` int(11) DEFAULT NULL COMMENT '해당 직무의 최대 급여\n기간 단위 : 월 (month)\n화폐 단위 : 원 (won)\n',
  `description` text,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='직업 직무 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_tbl`
--

LOCK TABLES `job_tbl` WRITE;
/*!40000 ALTER TABLE `job_tbl` DISABLE KEYS */;
INSERT INTO `job_tbl` VALUES (1,'cto',0,0,'cto'),(2,'ceo',0,0,'ceo'),(3,'cdo',0,0,'cdo'),(4,'cmo',0,0,'cmo'),(5,'coo',0,0,'coo'),(6,'사원',0,0,'사원'),(7,'배달기사',2000000,2000000,'배달기사');
/*!40000 ALTER TABLE `job_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kakao_authcode_tbl`
--

DROP TABLE IF EXISTS `kakao_authcode_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kakao_authcode_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(45) DEFAULT NULL,
  `auth_code` varchar(45) DEFAULT NULL,
  `register_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `ip` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 COMMENT='카카오톡을 통한 인증에 필요한 인증코드 저장소';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kakao_authcode_tbl`
--

LOCK TABLES `kakao_authcode_tbl` WRITE;
/*!40000 ALTER TABLE `kakao_authcode_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `kakao_authcode_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_for_comment_all_tbl`
--

DROP TABLE IF EXISTS `like_for_comment_all_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `like_for_comment_all_tbl` (
  `comment_all_idx` int(11) NOT NULL,
  `customer_idx` int(11) NOT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '0 - like\n1 - unlike',
  PRIMARY KEY (`comment_all_idx`,`customer_idx`),
  KEY `fk_like_comment_all_idx` (`comment_all_idx`),
  KEY `fk_like_customer_all_idx` (`customer_idx`),
  CONSTRAINT `fk_like_comment_all` FOREIGN KEY (`comment_all_idx`) REFERENCES `comment_for_all_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_like_customer_all` FOREIGN KEY (`customer_idx`) REFERENCES `user_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='고객의소리 리뷰 관련  like, unlike 관리 테이블 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_for_comment_all_tbl`
--

LOCK TABLES `like_for_comment_all_tbl` WRITE;
/*!40000 ALTER TABLE `like_for_comment_all_tbl` DISABLE KEYS */;
INSERT INTO `like_for_comment_all_tbl` VALUES (1,1,1);
/*!40000 ALTER TABLE `like_for_comment_all_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_for_comment_store_tbl`
--

DROP TABLE IF EXISTS `like_for_comment_store_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `like_for_comment_store_tbl` (
  `comment_store_idx` int(11) NOT NULL,
  `customer_idx` int(11) NOT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '0 - like\n1 - unlike',
  PRIMARY KEY (`comment_store_idx`,`customer_idx`),
  KEY `fk_like_comment_store_idx` (`comment_store_idx`),
  KEY `fk_like_customer_store_idx` (`customer_idx`),
  CONSTRAINT `fk_like_comment_store` FOREIGN KEY (`comment_store_idx`) REFERENCES `comment_for_store_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_like_customer_store` FOREIGN KEY (`customer_idx`) REFERENCES `user_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='업체 5마디 리뷰 관련  like, unlike 관리 테이블 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_for_comment_store_tbl`
--

LOCK TABLES `like_for_comment_store_tbl` WRITE;
/*!40000 ALTER TABLE `like_for_comment_store_tbl` DISABLE KEYS */;
INSERT INTO `like_for_comment_store_tbl` VALUES (9,1,0),(9,13,1),(10,2,1),(30,13,0);
/*!40000 ALTER TABLE `like_for_comment_store_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_for_product_tbl`
--

DROP TABLE IF EXISTS `like_for_product_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `like_for_product_tbl` (
  `product_idx` int(11) NOT NULL,
  `customer_idx` int(11) NOT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '0 - like\n1 - unlike',
  PRIMARY KEY (`product_idx`,`customer_idx`),
  KEY `fk_like_product_idx` (`product_idx`),
  KEY `fk_like_customer_p_idx` (`customer_idx`),
  CONSTRAINT `fk_like_customer_p` FOREIGN KEY (`customer_idx`) REFERENCES `user_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_like_product` FOREIGN KEY (`product_idx`) REFERENCES `product_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='제품 관련  like, unlike 관리 테이블 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_for_product_tbl`
--

LOCK TABLES `like_for_product_tbl` WRITE;
/*!40000 ALTER TABLE `like_for_product_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `like_for_product_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_for_reply_all_tbl`
--

DROP TABLE IF EXISTS `like_for_reply_all_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `like_for_reply_all_tbl` (
  `reply_all_idx` int(11) NOT NULL,
  `customer_idx` int(11) NOT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '0 - like\n1 - unlike',
  PRIMARY KEY (`reply_all_idx`,`customer_idx`),
  KEY `fk_like_reply_all_idx` (`reply_all_idx`),
  KEY `fk_like_reply_customer_all_idx` (`customer_idx`),
  CONSTRAINT `fk_like_reply_all` FOREIGN KEY (`reply_all_idx`) REFERENCES `reply_for_comment_all_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_like_reply_customer_all` FOREIGN KEY (`customer_idx`) REFERENCES `user_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='고객의소리 리뷰 댓글 관련  like, unlike 관리 테이블 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_for_reply_all_tbl`
--

LOCK TABLES `like_for_reply_all_tbl` WRITE;
/*!40000 ALTER TABLE `like_for_reply_all_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `like_for_reply_all_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_for_reply_store_tbl`
--

DROP TABLE IF EXISTS `like_for_reply_store_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `like_for_reply_store_tbl` (
  `reply_store_idx` int(11) NOT NULL,
  `customer_idx` int(11) NOT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '0 - like\n1 - unlike',
  PRIMARY KEY (`reply_store_idx`,`customer_idx`),
  KEY `fk_like_reply_store_idx` (`reply_store_idx`),
  KEY `fk_like_reply_customer_store_idx` (`customer_idx`),
  CONSTRAINT `fk_like_reply_customer_store` FOREIGN KEY (`customer_idx`) REFERENCES `user_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_like_reply_store` FOREIGN KEY (`reply_store_idx`) REFERENCES `reply_for_comment_store_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='업체 5마디 리뷰 댓글 관련  like, unlike 관리 테이블 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_for_reply_store_tbl`
--

LOCK TABLES `like_for_reply_store_tbl` WRITE;
/*!40000 ALTER TABLE `like_for_reply_store_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `like_for_reply_store_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_for_store_tbl`
--

DROP TABLE IF EXISTS `like_for_store_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `like_for_store_tbl` (
  `store_idx` int(11) NOT NULL,
  `customer_idx` int(11) NOT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '0 - like\n1 - unlike',
  PRIMARY KEY (`store_idx`,`customer_idx`),
  KEY `fk_like_store_idx` (`store_idx`),
  KEY `fk_like_s_cust_idx` (`customer_idx`),
  CONSTRAINT `fk_like_s_cust` FOREIGN KEY (`customer_idx`) REFERENCES `user_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_like_store` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='업체 관련  like, unlike 관리 테이블 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_for_store_tbl`
--

LOCK TABLES `like_for_store_tbl` WRITE;
/*!40000 ALTER TABLE `like_for_store_tbl` DISABLE KEYS */;
INSERT INTO `like_for_store_tbl` VALUES (1,2,0),(1,13,1),(3,13,0);
/*!40000 ALTER TABLE `like_for_store_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location_for_department_tbl`
--

DROP TABLE IF EXISTS `location_for_department_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location_for_department_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `address` varchar(200) NOT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='부서 상세 위치 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_for_department_tbl`
--

LOCK TABLES `location_for_department_tbl` WRITE;
/*!40000 ALTER TABLE `location_for_department_tbl` DISABLE KEYS */;
INSERT INTO `location_for_department_tbl` VALUES (1,'고양','경기도 고양시 덕양구'),(2,'강남','서울특별시 강남구'),(3,'군산','전라북도 군산시');
/*!40000 ALTER TABLE `location_for_department_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_for_coupon_tbl`
--

DROP TABLE IF EXISTS `log_for_coupon_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_for_coupon_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `employee_idx` int(11) DEFAULT NULL COMMENT '발급, 지급, 수정 ,삭제에 관여한\n관리자 인덱스',
  `coupon_idx` int(11) NOT NULL,
  `customer_idx` int(11) DEFAULT NULL,
  `guest_idx` int(11) DEFAULT NULL,
  `order_idx` int(11) DEFAULT NULL,
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` tinyint(4) NOT NULL COMMENT '로깅 타입\n0 - 쿠폰 생성 로그\n1 - 쿠폰 지급 로그\n2 - 쿠폰 사용 로그\n3 - 쿠폰 수정 로그\n4 - 쿠폰 삭제 로그',
  `sequence` int(11) DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='쿠폰 사용 내역 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_for_coupon_tbl`
--

LOCK TABLES `log_for_coupon_tbl` WRITE;
/*!40000 ALTER TABLE `log_for_coupon_tbl` DISABLE KEYS */;
INSERT INTO `log_for_coupon_tbl` VALUES (1,1,1,NULL,NULL,NULL,'2019-04-16 08:52:06',0,1),(2,NULL,1,2,NULL,NULL,'2019-04-16 08:52:29',1,2),(4,1,2,NULL,NULL,NULL,'2019-04-16 10:34:09',0,1),(5,NULL,2,3,NULL,NULL,'2019-04-16 10:34:09',1,2),(8,NULL,1,2,NULL,6,'2019-04-16 19:57:35',2,3);
/*!40000 ALTER TABLE `log_for_coupon_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_for_job`
--

DROP TABLE IF EXISTS `log_for_job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_for_job` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `employee_idx` int(11) DEFAULT NULL,
  `job_idx` int(11) DEFAULT NULL,
  `department_idx` int(11) DEFAULT NULL,
  `start_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '계약 시작 날짜',
  `end_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '계약 종료 날짜',
  `register_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`idx`),
  KEY `fk_log_emp_idx` (`employee_idx`),
  KEY `fk_log_job_idx` (`job_idx`),
  KEY `fk_log_dept_idx` (`department_idx`),
  CONSTRAINT `fk_log_dept` FOREIGN KEY (`department_idx`) REFERENCES `department_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_log_emp` FOREIGN KEY (`employee_idx`) REFERENCES `employee_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_log_job` FOREIGN KEY (`job_idx`) REFERENCES `job_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='직업 직무 로깅 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_for_job`
--

LOCK TABLES `log_for_job` WRITE;
/*!40000 ALTER TABLE `log_for_job` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_for_job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_for_order_tbl`
--

DROP TABLE IF EXISTS `log_for_order_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_for_order_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `box_no` int(11) NOT NULL COMMENT '박스 번호\n기사가 고객에게 물건을 나누어줄 때 사용하는 번호',
  `customer_idx` int(11) DEFAULT NULL COMMENT '고객 인덱스',
  `guest_idx` int(11) DEFAULT NULL,
  `employee_idx` int(11) DEFAULT NULL COMMENT '배달 직원 인덱스',
  `delivery_site_idx` int(11) DEFAULT NULL COMMENT '배달지 인덱스',
  `detail_site_idx` int(11) DEFAULT NULL,
  `type_payment` tinyint(4) NOT NULL COMMENT '주문 결제 방식',
  `state_order` tinyint(4) NOT NULL DEFAULT '0' COMMENT '주문 상태\n0 - 결제 대기\n1 - 결제 완료\n2 - 결제 실패\n3 - 배달 완료\n4 - 결제 취소\n5 - 결제 환불\n',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록 날짜',
  `arrival_date_only` date DEFAULT NULL COMMENT '받는 날짜',
  `arrival_time_only` time DEFAULT NULL,
  `using_point` int(11) DEFAULT NULL,
  `using_coupon_idx` int(11) DEFAULT NULL,
  `final_amount` int(11) DEFAULT NULL,
  `saved_point` int(11) DEFAULT NULL,
  `order_id` varchar(45) DEFAULT NULL,
  `receipt_id` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8 COMMENT='주문 로깅 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_for_order_tbl`
--

LOCK TABLES `log_for_order_tbl` WRITE;
/*!40000 ALTER TABLE `log_for_order_tbl` DISABLE KEYS */;
INSERT INTO `log_for_order_tbl` VALUES (1,0,2,NULL,NULL,1,1,3,0,'2019-04-21 22:58:59','2019-04-22','12:00:00',2000,NULL,52460,NULL,'test',NULL,NULL),(2,0,2,NULL,NULL,1,1,3,0,'2019-04-21 23:03:43','2019-04-22','12:00:00',2000,NULL,52460,NULL,'merchant_5_1555887823121',NULL,NULL),(3,1,2,NULL,NULL,1,1,3,0,'2019-04-21 23:03:55','2019-04-22','12:00:00',2000,NULL,52460,NULL,'merchant_5_1555887835273',NULL,NULL),(4,2,2,NULL,NULL,1,1,3,0,'2019-04-21 23:07:16','2019-04-22','12:00:00',2000,NULL,52460,NULL,'merchant_5_1555888035699',NULL,NULL),(5,3,3,NULL,NULL,1,1,3,0,'2019-04-21 23:28:45','2019-04-22','12:00:00',0,NULL,3880,NULL,'merchant_6_1555889324942',NULL,NULL),(6,0,3,NULL,NULL,1,1,3,0,'2019-04-21 23:30:50','2019-04-22','12:00:00',0,NULL,3880,NULL,'merchant_6_1555889450292',NULL,NULL),(7,1,3,NULL,NULL,1,1,3,0,'2019-04-21 23:41:12','2019-04-22','12:00:00',0,NULL,3880,NULL,'merchant_6_1555890071877',NULL,NULL),(8,2,3,NULL,NULL,1,1,3,0,'2019-04-21 23:58:44','2019-04-22','12:00:00',0,NULL,3880,NULL,'merchant_6_1555891123389',NULL,NULL),(9,3,3,NULL,NULL,1,1,3,0,'2019-04-22 00:03:32','2019-04-22','12:00:00',0,NULL,3880,NULL,'merchant_6_1555891411434',NULL,NULL),(10,4,3,NULL,NULL,1,1,3,0,'2019-04-22 00:03:58','2019-04-22','12:00:00',0,NULL,3880,NULL,'merchant_6_1555891438081',NULL,NULL),(11,0,2,1,NULL,1,1,4,0,'2019-04-29 22:08:06','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556575685799',NULL,'010-1234-1234'),(12,1,2,1,NULL,1,1,4,0,'2019-04-29 22:11:26','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556575885947',NULL,'010-1234-1234'),(13,2,2,1,NULL,1,1,4,0,'2019-04-29 22:25:12','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556576711435',NULL,'010-1234-1234'),(14,0,2,1,NULL,1,1,4,0,'2019-04-29 22:27:30','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556576849257',NULL,'010-1234-1234'),(15,1,2,1,NULL,1,1,4,0,'2019-04-29 22:36:54','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556577413589',NULL,'010-1234-1234'),(16,0,2,1,NULL,1,1,4,0,'2019-04-29 22:53:55','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556578435060',NULL,'010-1234-1234'),(17,1,2,1,NULL,1,1,4,0,'2019-04-29 22:55:11','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556578511179',NULL,'010-1234-1234'),(18,2,2,1,NULL,1,1,4,0,'2019-04-29 22:56:05','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556578564757',NULL,'010-1234-1234'),(19,3,2,1,NULL,1,1,4,0,'2019-04-29 22:56:43','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556578603134',NULL,'010-1234-1234'),(20,4,2,1,NULL,1,1,4,0,'2019-04-29 22:59:54','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556578793328',NULL,'010-1234-1234'),(21,5,2,1,NULL,1,1,4,0,'2019-04-29 23:03:48','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556579027502',NULL,'010-1234-1234'),(22,6,2,1,NULL,1,1,4,0,'2019-04-29 23:05:49','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556579148921',NULL,'010-1234-1234'),(23,0,2,1,NULL,1,1,4,0,'2019-04-29 23:06:59','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556579218608',NULL,'010-1234-1234'),(24,1,2,1,NULL,1,1,4,0,'2019-04-29 23:08:24','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556579304451',NULL,'010-1234-1234'),(25,2,2,1,NULL,1,1,4,0,'2019-04-29 23:08:38','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556579318271',NULL,'010-1234-1234'),(26,3,2,1,NULL,1,1,4,0,'2019-04-29 23:09:33','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556579373118',NULL,'010-1234-1234'),(27,4,2,1,NULL,1,1,4,0,'2019-04-29 23:10:34','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556579433757',NULL,'010-1234-1234'),(28,5,2,1,NULL,1,1,4,0,'2019-04-29 23:14:33','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556579672985',NULL,'010-1234-1234'),(29,6,2,1,NULL,1,1,4,0,'2019-04-29 23:28:20','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556580500145',NULL,'010-1234-1234'),(30,7,2,1,NULL,1,1,4,0,'2019-04-29 23:28:23','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556580502628',NULL,'010-1234-1234'),(31,8,2,1,NULL,1,1,4,0,'2019-04-29 23:29:04','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556580543791',NULL,'010-1234-1234'),(32,0,2,1,NULL,1,1,4,0,'2019-04-29 23:29:56','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556580595996',NULL,'010-1234-1234'),(33,0,2,1,NULL,1,1,4,0,'2019-04-29 23:32:13','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556580732351',NULL,'010-1234-1234'),(34,1,2,1,NULL,1,1,4,0,'2019-04-29 23:42:17','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556581337231',NULL,'010-1234-1234'),(35,2,2,1,NULL,1,1,4,0,'2019-04-30 00:00:53','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556582452361',NULL,'010-1234-1234'),(36,3,2,1,NULL,1,1,4,0,'2019-04-30 00:02:05','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556582524395',NULL,'010-1234-1234'),(37,4,2,1,NULL,1,1,4,0,'2019-04-30 00:03:29','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556582609256',NULL,'010-1234-1234'),(38,5,2,1,NULL,1,1,4,0,'2019-04-30 00:04:29','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556582668749',NULL,'010-1234-1234'),(39,6,2,1,NULL,1,1,4,0,'2019-04-30 00:06:26','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556582786197',NULL,'010-1234-1234'),(40,7,2,1,NULL,1,1,4,0,'2019-04-30 00:07:10','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556582830269',NULL,'010-1234-1234'),(41,8,2,1,NULL,1,1,4,0,'2019-04-30 00:11:41','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556583100757',NULL,'010-1234-1234'),(42,9,2,1,NULL,1,1,4,0,'2019-04-30 00:12:02','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556583121893',NULL,'010-1234-1234'),(43,10,2,1,NULL,1,1,4,0,'2019-04-30 00:13:02','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556583182206',NULL,'010-1234-1234'),(44,11,2,1,NULL,1,1,4,0,'2019-04-30 00:15:15','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556583314310',NULL,'010-1234-1234'),(45,12,2,1,NULL,1,1,4,0,'2019-04-30 00:16:11','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556583370521',NULL,'010-1234-1234'),(46,13,2,1,NULL,1,1,4,0,'2019-04-30 00:17:01','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556583420437',NULL,'010-1234-1234'),(47,14,2,1,NULL,1,1,4,0,'2019-04-30 00:17:38','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556583457695',NULL,'010-1234-1234'),(48,15,2,1,NULL,1,1,4,0,'2019-04-30 00:18:25','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556583504884',NULL,'010-1234-1234'),(49,0,2,1,NULL,1,1,4,0,'2019-04-30 00:33:00','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556584380231',NULL,'010-1234-1234'),(50,1,2,1,NULL,1,1,4,0,'2019-04-30 01:08:40','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556586520319',NULL,'010-1234-1234'),(51,0,2,1,NULL,1,1,4,0,'2019-04-30 01:31:58','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556587917925',NULL,'010-1234-1234'),(52,1,2,1,NULL,1,1,4,0,'2019-04-30 01:36:30','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556588189715',NULL,'010-1234-1234'),(53,2,2,1,NULL,1,1,4,0,'2019-04-30 01:39:21','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556588360465',NULL,'010-1234-1234'),(54,3,2,1,NULL,1,1,4,0,'2019-04-30 01:41:45','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556588505215',NULL,'010-1234-1234'),(55,4,2,1,NULL,1,1,4,0,'2019-04-30 01:44:23','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556588662368',NULL,'010-1234-1234'),(56,5,2,1,NULL,1,1,4,0,'2019-04-30 02:21:38','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556590898106',NULL,'010-1234-1234'),(57,0,2,1,NULL,1,1,4,0,'2019-04-30 02:23:27','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556591006591',NULL,'010-1234-1234'),(58,1,2,1,NULL,1,1,4,0,'2019-04-30 02:24:29','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556591068359',NULL,'010-1234-1234'),(59,2,2,1,NULL,1,1,4,0,'2019-04-30 02:27:04','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556591224121',NULL,'010-1234-1234'),(60,3,2,2,NULL,1,1,4,0,'2019-04-30 02:41:57','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556592116717',NULL,'010-1234-1234'),(61,4,2,2,NULL,1,1,4,0,'2019-04-30 02:42:29','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556592149398',NULL,'010-1234-1234'),(62,5,2,2,NULL,1,1,4,0,'2019-04-30 03:39:07','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556595547198',NULL,'010-1234-1234'),(63,6,2,2,NULL,1,1,4,0,'2019-04-30 03:41:16','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556595675673',NULL,'010-1234-1234'),(64,7,2,2,NULL,1,1,4,0,'2019-04-30 03:42:36','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556595755940',NULL,'010-1234-1234'),(65,8,2,2,NULL,1,1,4,0,'2019-04-30 03:44:09','2019-04-22','12:00:00',0,NULL,54460,NULL,'ORDER_6_1556595849068',NULL,'010-1234-1234'),(66,8,2,2,NULL,1,1,4,1,'2019-04-30 03:44:48','2019-04-22','12:00:00',0,NULL,54460,54460,'ORDER_6_1556595888140','5cc7c4b1ed32b33f2c1c8230','010-1234-1234'),(67,9,3,2,NULL,1,1,4,0,'2019-04-30 04:48:40','2019-04-22','12:00:00',0,NULL,46560,NULL,'ORDER_6_1556599719483',NULL,'010-1234-1234'),(68,10,3,2,NULL,1,1,4,1,'2019-04-30 04:52:03','2019-04-22','12:00:00',0,NULL,46560,46560,'ORDER_6_1556599923100','5cc7d474e23d1b773556972a','010-1234-1234'),(69,11,3,2,NULL,1,1,4,2,'2019-05-03 23:08:19','2019-04-22','12:00:00',0,NULL,23280,NULL,'ORDER_8_1556924898340',NULL,'010-1234-1234'),(70,12,3,2,NULL,1,1,4,2,'2019-05-03 23:08:27','2019-04-22','12:00:00',0,NULL,23280,NULL,'ORDER_8_1556924906864',NULL,'010-1234-1234'),(71,13,3,2,NULL,1,1,4,0,'2019-05-03 23:11:32','2019-04-22','12:00:00',0,NULL,23280,NULL,'ORDER_8_1556925091346',NULL,'010-1234-1234'),(72,13,3,2,NULL,1,1,4,0,'2019-05-03 23:12:25','2019-04-22','12:00:00',0,NULL,23280,NULL,'ORDER_8_1556925145116',NULL,'010-1234-1234'),(73,14,3,2,NULL,1,1,4,0,'2019-05-03 23:13:10','2019-04-22','12:00:00',0,NULL,23280,NULL,'ORDER_8_1556925190275',NULL,'010-1234-1234'),(74,15,3,2,NULL,1,1,4,0,'2019-05-03 23:54:00','2019-04-22','12:00:00',0,NULL,23280,NULL,'ORDER_8_1556927639666',NULL,'010-1234-1234'),(75,16,3,2,NULL,1,1,4,0,'2019-05-04 00:02:02','2019-04-22','12:00:00',0,NULL,23280,NULL,'ORDER_8_1556928121470',NULL,'010-1234-1234'),(76,17,3,NULL,NULL,1,1,4,0,'2019-05-04 00:04:15','2019-04-22','12:00:00',0,NULL,2000,NULL,'ORDER_10_1556928254731',NULL,'010-1234-1234'),(77,18,3,NULL,NULL,1,1,4,0,'2019-05-04 00:18:42','2019-04-22','12:00:00',0,NULL,2000,NULL,'ORDER_10_1556929121367',NULL,'010-1234-1234'),(78,18,3,NULL,NULL,1,1,4,0,'2019-05-04 00:18:57','2019-04-22','12:00:00',0,NULL,2000,NULL,'ORDER_10_1556929137102',NULL,'010-1234-1234'),(79,19,3,NULL,NULL,1,1,4,0,'2019-05-04 01:16:49','2019-04-22','12:00:00',0,NULL,2000,NULL,'ORDER_10_1556932608934',NULL,'010-1234-1234'),(80,20,3,NULL,NULL,1,1,4,0,'2019-05-04 01:18:17','2019-04-22','12:00:00',0,NULL,2000,NULL,'ORDER_10_1556932697307',NULL,'010-1234-1234'),(81,0,13,NULL,NULL,1,2,4,0,'2019-05-05 17:35:40','2019-05-06','06:00:00',0,NULL,3470,NULL,'ORDER_20_1557077740366',NULL,'01029354191'),(82,0,13,NULL,NULL,1,2,4,2,'2019-05-05 17:59:01','2019-05-06','07:00:00',0,NULL,-530,NULL,'ORDER_20_1557079140782',NULL,'01029354191'),(83,1,13,NULL,NULL,1,2,4,2,'2019-05-05 17:59:05','2019-05-06','07:00:00',0,NULL,-530,NULL,'ORDER_20_1557079145016',NULL,'01029354191'),(84,2,13,NULL,NULL,1,2,4,2,'2019-05-05 17:59:06','2019-05-06','07:00:00',0,NULL,-530,NULL,'ORDER_20_1557079146266',NULL,'01029354191'),(85,3,13,NULL,NULL,1,2,4,2,'2019-05-05 17:59:07','2019-05-06','07:00:00',0,NULL,-530,NULL,'ORDER_20_1557079146844',NULL,'01029354191'),(86,0,NULL,7,NULL,1,1,1,0,'2019-05-06 01:18:42','2019-05-06','12:00:00',0,NULL,1470,NULL,'ORDER_26_1557105521621',NULL,'null'),(87,1,13,7,NULL,1,1,4,1,'2019-05-06 02:31:34','2019-05-06','12:00:00',0,NULL,2860,2860,'ORDER_27_1557109893591','5ccf9c85ed32b3202498cd8e','01029354191'),(88,0,13,7,NULL,1,1,4,1,'2019-05-06 06:38:06','2019-05-06','05:00:00',0,NULL,3840,3840,'ORDER_29_1557124686398','5ccfd64fed32b3203698d449','010-2935-4191'),(89,1,NULL,6,NULL,1,1,4,1,'2019-05-06 06:39:24','2019-05-06','05:00:00',0,NULL,2000,NULL,'ORDER_30_1557124764085','5ccfd69ced32b3201a98cd16','null'),(90,2,13,NULL,NULL,1,1,1,0,'2019-05-06 06:43:43','2019-05-06','05:00:00',0,NULL,6250,NULL,'ORDER_31_1557125022693',NULL,'010-2935-4191'),(91,3,13,NULL,NULL,1,1,2,0,'2019-05-06 06:44:02','2019-05-06','05:00:00',0,NULL,6250,NULL,'ORDER_31_1557125042131',NULL,'010-2935-4191'),(92,4,13,NULL,NULL,1,1,3,0,'2019-05-06 06:44:11','2019-05-06','05:00:00',0,NULL,6250,NULL,'ORDER_31_1557125051021',NULL,'010-2935-4191'),(93,5,13,NULL,NULL,1,1,4,1,'2019-05-06 06:44:22','2019-05-06','05:00:00',0,NULL,6250,6250,'ORDER_31_1557125062303','5ccfd7c6ed32b3203798ce92','010-2935-4191'),(94,6,NULL,3,NULL,1,2,4,0,'2019-05-06 07:08:10','2019-05-06','05:00:00',0,NULL,97000,NULL,'ORDER_23_1557126490498',NULL,'null'),(95,7,NULL,3,NULL,1,2,3,0,'2019-05-06 07:08:17','2019-05-06','05:00:00',0,NULL,97000,NULL,'ORDER_23_1557126497467',NULL,'null'),(96,8,NULL,3,NULL,1,2,3,0,'2019-05-06 07:08:31','2019-05-06','05:00:00',0,NULL,97000,NULL,'ORDER_23_1557126511170',NULL,'null'),(97,0,NULL,22,NULL,1,1,1,0,'2019-05-06 23:53:04','2019-05-07','12:00:00',0,NULL,3390,NULL,'ORDER_37_1557186783815',NULL,'null'),(98,1,NULL,22,NULL,1,1,4,0,'2019-05-06 23:53:15','2019-05-07','12:00:00',0,NULL,3390,NULL,'ORDER_37_1557186795424',NULL,'null'),(99,0,1,18,NULL,1,1,4,0,'2019-05-08 08:01:52','2019-05-08','06:00:00',0,NULL,13900,NULL,'ORDER_38_1557302511665',NULL,'010-6478-4899'),(100,1,1,18,NULL,1,1,1,0,'2019-05-08 08:02:21','2019-05-08','06:00:00',0,NULL,13900,NULL,'ORDER_38_1557302540743',NULL,'010-6478-4899'),(101,0,13,3,NULL,1,1,4,0,'2019-05-09 16:41:22','2019-05-10','12:00:00',0,NULL,17000,NULL,'ORDER_41_1557420081677',NULL,'010-2935-4191');
/*!40000 ALTER TABLE `log_for_order_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_for_point_tbl`
--

DROP TABLE IF EXISTS `log_for_point_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_for_point_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `employee_idx` int(11) DEFAULT NULL,
  `customer_idx` int(11) DEFAULT NULL,
  `order_idx` int(11) DEFAULT NULL,
  `pre_prc` int(11) NOT NULL COMMENT '로깅 전 포인트',
  `post_prc` int(11) NOT NULL COMMENT '로깅 후 포인트\n최종 포인트 금액',
  `using_point` int(11) NOT NULL COMMENT 'type에 따른,\n사용한 포인트 금액\n(지급, 사용, 수정 등의 금액)',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` tinyint(4) NOT NULL COMMENT '포인트 사용 종류\n[포인트 지급]\n00 - 관리자 권한 지급\n01 - 프로모션 지급\n02 - 구매활동을 통한 지급\n[포인트 사용]\n10 - 주문 가격 할인에 사용\n[포인트 수정]\n20 - 관리자 권한 수정',
  `contents` varchar(150) DEFAULT NULL COMMENT '비고',
  `sequence` int(11) DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='포인트 사용 내역 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_for_point_tbl`
--

LOCK TABLES `log_for_point_tbl` WRITE;
/*!40000 ALTER TABLE `log_for_point_tbl` DISABLE KEYS */;
INSERT INTO `log_for_point_tbl` VALUES (1,1,1,NULL,0,1000,1000,'2019-02-11 12:32:30',0,NULL,1),(2,NULL,1,NULL,1000,2000,1000,'2019-02-11 12:33:00',1,NULL,2),(3,NULL,1,NULL,2000,2500,500,'2019-02-11 12:33:02',2,NULL,3),(4,NULL,1,32,2500,1500,1000,'2019-02-11 12:49:56',10,'포인트사용',4),(5,2,2,NULL,0,1000,1000,'2019-02-11 12:32:30',0,NULL,1),(6,NULL,2,NULL,1000,2000,1000,'2019-02-11 12:33:00',1,NULL,2),(7,NULL,2,NULL,2000,2500,500,'2019-02-11 12:33:02',2,NULL,3),(8,NULL,2,32,2500,1500,1000,'2019-02-11 12:49:56',10,'포인트사용',4);
/*!40000 ALTER TABLE `log_for_point_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notice_tbl`
--

DROP TABLE IF EXISTS `notice_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notice_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `contents` text,
  `begin_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '이벤트 시작 날짜\n(기간 0 입력 시 무제한으로 설정)',
  `end_date` timestamp NULL DEFAULT NULL COMMENT '이벤트 종료 날짜\n(기간 0 입력 시 무제한으로 설정)',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `state_active` tinyint(4) NOT NULL DEFAULT '1' COMMENT '제품 상태\n0 - 비활성화\n1 - 활성화',
  `url` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='공지사항 관리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice_tbl`
--

LOCK TABLES `notice_tbl` WRITE;
/*!40000 ALTER TABLE `notice_tbl` DISABLE KEYS */;
INSERT INTO `notice_tbl` VALUES (1,'항공대 공지사항','항공대 공지사항 테스트 내용<p style=\"color:red\">항공대 공지사항 테스트 내용</p>','2019-04-24 18:09:57',NULL,'2019-04-24 18:09:57','2019-05-07 15:07:20',1,'noticeType1'),(2,'제휴음식점 맘스터치 오픈','항공대 공지사항 테스트 내용<p style=\"color:red\">항공대 공지사항 테스트 내용</p>','2019-04-24 18:10:29',NULL,'2019-04-24 18:10:29','2019-05-07 15:09:10',1,'noticeType1'),(3,'포만감 5주년 기념 이벤트','항공대 공지사항 테스트 내용<p style=\"color:red\">항공대 공지사항 테스트 내용</p>','2019-04-24 18:10:29',NULL,'2019-04-24 18:10:29','2019-05-07 15:09:10',1,'noticeType1'),(4,'이용약관 변경안내','항공대 공지사항 테스트 내용<p style=\"color:red\">항공대 공지사항 테스트 내용</p>','2019-05-07 15:07:20',NULL,'2019-05-07 15:07:20','2019-05-07 15:07:20',1,'noticeType1'),(5,'배달지연 공지','항공대 공지사항 테스트 내용<p style=\"color:red\">항공대 공지사항 테스트 내용</p>','2019-05-07 15:07:20',NULL,'2019-05-07 15:07:20','2019-05-07 15:07:20',1,'noticeType1'),(6,'포만감 인턴 채용안내','항공대 공지사항 테스트 내용<p style=\"color:red\">항공대 공지사항 테스트 내용</p>','2019-05-07 15:07:20',NULL,'2019-05-07 15:07:20','2019-05-07 15:07:20',1,'noticeType1'),(7,'긴급 회선공사 안내','항공대 공지사항 테스트 내용<p style=\"color:red\">항공대 공지사항 테스트 내용</p>','2019-05-07 15:07:20',NULL,'2019-05-07 15:07:20','2019-05-07 15:07:20',1,'noticeType1'),(8,'서비스 안정화 작업 안내','항공대 공지사항 테스트 내용<p style=\"color:red\">항공대 공지사항 테스트 내용</p>','2019-05-07 15:07:20',NULL,'2019-05-07 15:07:20','2019-05-07 15:07:20',1,'noticeType1');
/*!40000 ALTER TABLE `notice_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_access_token`
--

DROP TABLE IF EXISTS `oauth_access_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` mediumblob,
  `authentication_id` varchar(256) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` mediumblob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_access_token`
--

LOCK TABLES `oauth_access_token` WRITE;
/*!40000 ALTER TABLE `oauth_access_token` DISABLE KEYS */;
INSERT INTO `oauth_access_token` VALUES ('a3de148c50235ceb46833c7950f29a9a','�\�\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken��6$�\�\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6�Z\�\�\�\0\0xpsr\0java.util.Datehj�KYt\0\0xpw\0\0jQ�<bxsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valueq\0~\0xpt\0$c2d997cc-94b4-4463-8107-301a652cc938sq\0~\0	w\0\0j\�\�axsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxt\0bearert\0$f79cef70-f535-4edd-a9a9-88d4f14c9819','0b2cf4e9cecccaabbde73d30db70d7d2','cholnh','staff','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0\nROLE_ADMINxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0adminsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0cholnhxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0\nROLE_ADMINxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0cholnh','c3e5e84c791597a669527b7bb7b0258f'),('03811486848ff44f579ad7c445794c9a','�\�\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken��6$�\�\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6�Z\�\�\�\0\0xpsr\0java.util.Datehj�KYt\0\0xpw\0\0j�\�<xsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valueq\0~\0xpt\0$67513564-33fa-42aa-ae0d-4d870ea8eb06sq\0~\0	w\0\0kr\�\�xsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxt\0bearert\0$822c6491-29c1-4c16-acce-711ca5fdbb93','1e18516d193d977c7ac117d81f0558c4','staff','login','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0loginsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0adminxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0admin','1d3ff0d56f988edbfa5634b3aef3f2c8'),('183e0f47b5cf578c58705fd84433ae53','�\�\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken��6$�\�\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6�Z\�\�\�\0\0xpsr\0java.util.Datehj�KYt\0\0xpw\0\0j�0��xsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valueq\0~\0xpt\0$653af68d-0ebd-456c-b7db-8add4315f8fbsq\0~\0	w\0\0k*Bxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxt\0bearert\0$7ac27b3c-2044-436b-81ba-f9dbffb1ab1c','2abbe761e5735126f44d41628ef7ec61','cholnh','login','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0loginsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0cholnhxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0cholnh','c1a3cc86c8216b5ca55a057f58ecf2aa'),('98b4c180db5160b476a6cfb5c0f9af9c','�\�\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken��6$�\�\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6�Z\�\�\�\0\0xpsr\0java.util.Datehj�KYt\0\0xpw\0\0j�<JCxsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valueq\0~\0xpt\0$ebab7f7e-270f-4bc6-b743-3974365c6967sq\0~\0	w\0\0k1\'\�Cxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxt\0bearert\0$16e1ee38-8c5d-4996-9bc0-6ffdf8c61418','6933500b372699418eebfb0d6977e0f3','d_duck@naver.com','login','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0loginsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0d_duck@naver.comxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0d_duck@naver.com','d78c68e6e301a55cd823f03dba372eba'),('00a4f63b608dfb4e0bc80614d8d05500','�\�\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken��6$�\�\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6�Z\�\�\�\0\0xpsr\0java.util.Datehj�KYt\0\0xpw\0\0j�dxsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valueq\0~\0xpt\0$0a79c3a2-d86c-4668-874b-524f46be3d40sq\0~\0	w\0\0j�\�+xsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxt\0bearert\0$cdc2eed9-f37b-4aa2-bf75-c95ab1e425fd','701b6ac4efbdaa2de4718c263a3af5ab','test@naver.com','login','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0loginsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0test@naver.comxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0test@naver.com','f864a94c7b222efacd730f5499c040c4'),('ecc8ba22d5426787eda54707bc74566d','�\�\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken��6$�\�\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6�Z\�\�\�\0\0xpsr\0java.util.Datehj�KYt\0\0xpw\0\0j�r\\qxsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valueq\0~\0xpt\0$c223722c-2f42-452b-afb0-df1bbfe4910fsq\0~\0	w\0\0k%]�qxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxt\0bearert\0$aa1e3c80-a4e7-49c2-9007-a2663cc84068','7f04f4d5f6029e8efb2ec72b99c26f8a','cholnh2@naver.com','login','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0loginsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0cholnh2@naver.comxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0cholnh2@naver.com','7700d2d9c95adedcbd8ea25113a35e91'),('79906f1a5fa22c71c180ef296521f539','�\�\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken��6$�\�\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6�Z\�\�\�\0\0xpsr\0java.util.Datehj�KYt\0\0xpw\0\0j`��mxsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valueq\0~\0xpt\0$9faaac92-f028-4ef6-b6ff-90db99e2e564sq\0~\0	w\0\0j�}\"mxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxt\0bearert\0$ab0c2800-d163-4168-bc94-df894b2c78e1','92ffbdfbf407554f461b0069b10104bb','test22','login','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0loginsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0test22xsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0test22','f00779e2789466774198742bf32e1f1c'),('f8a82e1d1f980a4cc6f7d7ee3429f565','�\�\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken��6$�\�\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6�Z\�\�\�\0\0xpsr\0java.util.Datehj�KYt\0\0xpw\0\0j�mxsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valueq\0~\0xpt\0$48cc4cc9-c266-48ff-9505-71ef1632ebd5sq\0~\0	w\0\0k%Y\\�xsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxt\0bearert\0$e60f497a-45c5-4208-80db-3af1e0f61ff3','b443c7485270d3a7b48ae915df316708','cholnh1@naver.com','login','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0loginsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0cholnh1@naver.comxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0cholnh1@naver.com','572d1a172eb2cd41c5df071f14da9ee3'),('a90c4840f5fdad65b2bb3122b94e4d13','�\�\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken��6$�\�\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6�Z\�\�\�\0\0xpsr\0java.util.Datehj�KYt\0\0xpw\0\0j]���xsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valueq\0~\0xpt\0$f63b127a-8cae-4b89-88c0-1ab8ee12851asq\0~\0	w\0\0j�\�2�xsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxt\0bearert\0$4c4facbf-fb06-4ebc-84dd-8a8a914f6f6e','b4eb4b43d5bc9f836dd3e4b3eba583c8','test@naver.com','admin','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0adminsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0test@naver.comxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0\nROLE_ADMINxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0test@naver.com','f739c4491544555d009c8c880f1e5507'),('7741958e6203ab7c6dd11b7d1a51dfe3','�\�\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken��6$�\�\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6�Z\�\�\�\0\0xpsr\0java.util.Datehj�KYt\0\0xpw\0\0jQ�\Z\�xsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valueq\0~\0xpt\0$dbb2a66b-a09a-4702-be00-c9822a379e01sq\0~\0	w\0\0j̼�+xsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxt\0bearert\0$b1a867f8-6440-40a3-8a0b-b0f13b71787d','b8d530911778e9bab22d10e472c7d629',NULL,'admin','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0\nROLE_ADMINxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0adminsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0client_credentialsxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0!w\0\0\0?@\0\0\0\0\0q\0~\0xsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0!w\0\0\0?@\0\0\0\0\0\0xsq\0~\0!w\0\0\0?@\0\0\0\0\0\0xp','5eae8dbaa6044f023a185c3fca6f17e4'),('4d899966ff8452ed7b06a89b078b7632','�\�\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken��6$�\�\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6�Z\�\�\�\0\0xpsr\0java.util.Datehj�KYt\0\0xpw\0\0jQ�<\�xsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valueq\0~\0xpt\0$674b6e43-19be-46a1-9543-77feda6eed84sq\0~\0	w\0\0j\�\�\�xsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxt\0bearert\0$e9339d2c-6602-48e7-945b-a0703bab55d4','d39f7e5d335eb9c128c796f072d156dc','admin','admin','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0\nROLE_ADMINxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0adminsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0adminxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0\nROLE_ADMINxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0admin','aa17e6c7884ad35a63b0907b733e74a2'),('56009bf7ee4032dedfb8956086f5b791','�\�\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken��6$�\�\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6�Z\�\�\�\0\0xpsr\0java.util.Datehj�KYt\0\0xpw\0\0iI�|xsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valueq\0~\0xpt\0$cad2d048-1b3d-4888-bb80-91ac347274b3sq\0~\0	w\0\0i\�	N{xsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxt\0bearert\0$294ce09f-2fac-4ef8-974a-bd789babc9c9','e2c44e5dca49668ee9df2135279c26ea','admin','client2','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0client2sr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0adminxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0/sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0admin','e18c9750c8245cd0498ce3d3e5ef95f9'),('3326fa32788d3c2146ddf4f46759c412','�\�\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken��6$�\�\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6�Z\�\�\�\0\0xpsr\0java.util.Datehj�KYt\0\0xpw\0\0j�\�\�\nxsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valueq\0~\0xpt\0$2bc5b83f-312b-4aef-a0b8-3f28681efda1sq\0~\0	w\0\0k>�j\nxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0trustxt\0bearert\0$f0fac73c-e090-4dd7-a40e-2dd08e8f0d81','ed021a663a38a4e59e78b26a7e662d61',NULL,'guest','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0\nROLE_GUESTxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0guestsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0client_credentialsxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0trustxsq\0~\0!w\0\0\0?@\0\0\0\0\0q\0~\0xsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0!w\0\0\0?@\0\0\0\0\0\0xsq\0~\0!w\0\0\0?@\0\0\0\0\0\0xp','76525f8d8bfa1b56c6e85a0e1502fe0d'),('adb3f07ecf59eeb131ac348531b1199f','�\�\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken��6$�\�\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6�Z\�\�\�\0\0xpsr\0java.util.Datehj�KYt\0\0xpw\0\0i�\�I�xsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valueq\0~\0xpt\0$6f332e4c-cb2d-4fa8-8737-7d76a2ed1673sq\0~\0	w\0\0j��xsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxt\0bearert\0$3dc4eeed-8ebb-4f55-8e93-4d9163c5ba61','f56857b08844a1626a031eb521523cab',NULL,'client2','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0\0w\0\0\0\0xq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUrit\0Ljava/lang/String;L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0client2sr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0client_credentialsxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0w\0\0\0?@\0\0\0\0\0\0xsq\0~\0?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0w\0\0\0?@\0\0\0\0\0\0xsq\0~\0w\0\0\0?@\0\0\0\0\0\0xp','a520e46125f32f3bfeb84e15e0d24a5c'),('54c608e082bfd3ed3389180c485ad94d','�\�\0sr\0Corg.springframework.security.oauth2.common.DefaultOAuth2AccessToken��6$�\�\0L\0additionalInformationt\0Ljava/util/Map;L\0\nexpirationt\0Ljava/util/Date;L\0refreshTokent\0?Lorg/springframework/security/oauth2/common/OAuth2RefreshToken;L\0scopet\0Ljava/util/Set;L\0	tokenTypet\0Ljava/lang/String;L\0valueq\0~\0xpsr\0java.util.Collections$EmptyMapY6�Z\�\�\�\0\0xpsr\0java.util.Datehj�KYt\0\0xpw\0\0j�Fcxsr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationq\0~\0xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valueq\0~\0xpt\0$a316fe90-4f0c-4840-93fc-c9fb9fb52b94sq\0~\0	w\0\0k:1�cxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0ct\0Ljava/util/Collection;xpsr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxt\0bearert\0$242ae90e-846e-40d6-96a4-a9870c914c1c','fbf8e53e012a6d1c60126d92a4cf71d0','cholnh3@naver.com','login','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0loginsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0cholnh3@naver.comxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0cholnh3@naver.com','ad1624bf1963909ac30c4580e620a334');
/*!40000 ALTER TABLE `oauth_access_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_approvals`
--

DROP TABLE IF EXISTS `oauth_approvals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `lastModifiedAt` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_approvals`
--

LOCK TABLES `oauth_approvals` WRITE;
/*!40000 ALTER TABLE `oauth_approvals` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_approvals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_client_details`
--

DROP TABLE IF EXISTS `oauth_client_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(256) NOT NULL COMMENT '클라이언트 구별 ID',
  `resource_ids` varchar(256) DEFAULT NULL COMMENT '리소스 식별자',
  `client_secret` varchar(256) DEFAULT NULL COMMENT '클라이언트 비밀번호',
  `scope` varchar(256) DEFAULT NULL COMMENT '엑세스 토큰 권한 범위',
  `authorized_grant_types` varchar(256) DEFAULT NULL COMMENT '액세스 토큰 받는 방법에 대한 권한 부여',
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL COMMENT '권한 부여',
  `access_token_validity` int(11) DEFAULT '43200' COMMENT '액세스 토큰 유효 시간 (기본값 : 12시간)',
  `refresh_token_validity` int(11) DEFAULT '2592000' COMMENT '리프래시 토큰 유효 시간 (기본값 : 30일)',
  `additional_information` varchar(4096) DEFAULT NULL COMMENT '추가 정보',
  `autoapprove` varchar(256) DEFAULT NULL COMMENT '허가받기 위한 화면을 나오지 않게 하는 속성 (기본값 false)',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='oauth2 client 관련 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_client_details`
--

LOCK TABLES `oauth_client_details` WRITE;
/*!40000 ALTER TABLE `oauth_client_details` DISABLE KEYS */;
INSERT INTO `oauth_client_details` VALUES ('staff',NULL,'$2a$10$77TW8.y3X1Ecc41cOWbIY.mqwNRxesa5o15taletTrwJtUmWW4yzW','read,write,trust','password,authorization_code,refresh_token,implicit,client_credentials',NULL,'ROLE_ADMIN',43200,2592000,NULL,'true'),('guest',NULL,'$2a$10$vFzjh6H941yMkUp0f1vQj.4I4cU5WM/xLwR9PTOoLXpSuSj3wxhgC','trust','client_credentials,refresh_token',NULL,'ROLE_GUEST',43200,2592000,NULL,'true'),('login',NULL,'$2a$10$GQKvIWBd6wi3/tDc2xHMKu.7hZnL/rjxrxoKrYOVkDEAMIDHq815y','read,write,trust','password,authorization_code,refresh_token,implicit,client_credentials',NULL,'ROLE_USER',43200,2592000,NULL,'true');
/*!40000 ALTER TABLE `oauth_client_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_client_token`
--

DROP TABLE IF EXISTS `oauth_client_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` mediumblob,
  `authentication_id` varchar(256) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_client_token`
--

LOCK TABLES `oauth_client_token` WRITE;
/*!40000 ALTER TABLE `oauth_client_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_client_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_code`
--

DROP TABLE IF EXISTS `oauth_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_code`
--

LOCK TABLES `oauth_code` WRITE;
/*!40000 ALTER TABLE `oauth_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `oauth_refresh_token`
--

DROP TABLE IF EXISTS `oauth_refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` mediumblob,
  `authentication` mediumblob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `oauth_refresh_token`
--

LOCK TABLES `oauth_refresh_token` WRITE;
/*!40000 ALTER TABLE `oauth_refresh_token` DISABLE KEYS */;
INSERT INTO `oauth_refresh_token` VALUES ('e18c9750c8245cd0498ce3d3e5ef95f9','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$cad2d048-1b3d-4888-bb80-91ac347274b3sr\0java.util.Datehj�KYt\0\0xpw\0\0i\�	N{x','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0client2sr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0adminxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\0/sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0admin'),('f9a521c7d9192dcb7ed0f5ee63d30fcf','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$570fe9ba-1ee7-411c-8087-289fc7a450f0sr\0java.util.Datehj�KYt\0\0xpw\0\0j�\Z�x','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0\0w\0\0\0\0xq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUrit\0Ljava/lang/String;L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0client2sr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0client_credentialsxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0w\0\0\0?@\0\0\0\0\0\0xsq\0~\0?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0w\0\0\0?@\0\0\0\0\0\0xsq\0~\0w\0\0\0?@\0\0\0\0\0\0xp'),('a520e46125f32f3bfeb84e15e0d24a5c','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$6f332e4c-cb2d-4fa8-8737-7d76a2ed1673sr\0java.util.Datehj�KYt\0\0xpw\0\0j��x','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0\0w\0\0\0\0xq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUrit\0Ljava/lang/String;L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0client2sr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0client_credentialsxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0w\0\0\0?@\0\0\0\0\0\0xsq\0~\0?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0w\0\0\0?@\0\0\0\0\0\0xsq\0~\0w\0\0\0?@\0\0\0\0\0\0xp'),('65b4e16d8512cc25c4411b94f5d35442','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$734a07c4-ad71-4ac4-97e7-f4faabbfa9e5sr\0java.util.Datehj�KYt\0\0xpw\0\0j�L\�ex','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0\0w\0\0\0\0xq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUrit\0Ljava/lang/String;L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0adminsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0client_credentialsxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0w\0\0\0?@\0\0\0\0\0\0xsq\0~\0?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0w\0\0\0?@\0\0\0\0\0\0xsq\0~\0w\0\0\0?@\0\0\0\0\0\0xp'),('3a6ddd56308d55f2ee038fb8ad37b41e','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$2bc67c21-3f8d-4ffc-8107-eefaf09fd8bbsr\0java.util.Datehj�KYt\0\0xpw\0\0j�Kw�x','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0\0w\0\0\0\0xq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUrit\0Ljava/lang/String;L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0adminsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0client_credentialsxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0w\0\0\0?@\0\0\0\0\0\0xsq\0~\0?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0w\0\0\0?@\0\0\0\0\0\0xsq\0~\0w\0\0\0?@\0\0\0\0\0\0xp'),('6e2de32710acd112d3081ccde4dfca0f','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$99b22e8d-290f-4d2f-8158-e47514098752sr\0java.util.Datehj�KYt\0\0xpw\0\0j\�n�x','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0\0w\0\0\0\0xq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUrit\0Ljava/lang/String;L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0adminsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0client_credentialsxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0w\0\0\0?@\0\0\0\0\0\0xsq\0~\0?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0w\0\0\0?@\0\0\0\0\0\0xsq\0~\0w\0\0\0?@\0\0\0\0\0\0xp'),('b91ae6feb5c9e959e99a8a4267ccb4c6','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$de92af9c-ec6a-49e1-af3a-26f5ce58d035sr\0java.util.Datehj�KYt\0\0xpw\0\0j\�Gpx','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0\0w\0\0\0\0xq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUrit\0Ljava/lang/String;L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0adminsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0client_credentialsxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0w\0\0\0?@\0\0\0\0\0\0xsq\0~\0?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0w\0\0\0?@\0\0\0\0\0\0xsq\0~\0w\0\0\0?@\0\0\0\0\0\0xp'),('17263d2686f69373c3b34dbaf8b36f21','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$cbb8dbcc-4f4c-4f82-a117-afdf9488ed20sr\0java.util.Datehj�KYt\0\0xpw\0\0jɟ�x','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0\0w\0\0\0\0xq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUrit\0Ljava/lang/String;L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0adminsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0client_credentialsxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0w\0\0\0?@\0\0\0\0\0\0xsq\0~\0?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0w\0\0\0?@\0\0\0\0\0\0xsq\0~\0w\0\0\0?@\0\0\0\0\0\0xp'),('5eae8dbaa6044f023a185c3fca6f17e4','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$dbb2a66b-a09a-4702-be00-c9822a379e01sr\0java.util.Datehj�KYt\0\0xpw\0\0j̼�+x','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0\nROLE_ADMINxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0adminsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0client_credentialsxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0!w\0\0\0?@\0\0\0\0\0q\0~\0xsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0!w\0\0\0?@\0\0\0\0\0\0xsq\0~\0!w\0\0\0?@\0\0\0\0\0\0xp'),('c3e5e84c791597a669527b7bb7b0258f','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$c2d997cc-94b4-4463-8107-301a652cc938sr\0java.util.Datehj�KYt\0\0xpw\0\0j\�\�ax','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0\nROLE_ADMINxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0adminsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0	client_idt\0admint\0usernamet\0cholnhxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0%w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0\nROLE_ADMINxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0%w\0\0\0?@\0\0\0\0\0\0xsq\0~\0%w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\03sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0t\0\rclient_secrett\01234q\0~\0q\0~\0q\0~\0q\0~\0q\0~\0 q\0~\0!x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0\"sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0cholnh'),('aa17e6c7884ad35a63b0907b733e74a2','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$674b6e43-19be-46a1-9543-77feda6eed84sr\0java.util.Datehj�KYt\0\0xpw\0\0j\�\�\�x','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0\nROLE_ADMINxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0adminsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0adminxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0\nROLE_ADMINxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0admin'),('f739c4491544555d009c8c880f1e5507','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$f63b127a-8cae-4b89-88c0-1ab8ee12851asr\0java.util.Datehj�KYt\0\0xpw\0\0j�\�2�x','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0adminsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0test@naver.comxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0\nROLE_ADMINxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0test@naver.com'),('f00779e2789466774198742bf32e1f1c','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$9faaac92-f028-4ef6-b6ff-90db99e2e564sr\0java.util.Datehj�KYt\0\0xpw\0\0j�}\"mx','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0loginsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0test22xsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0test22'),('1c86fbc528457a4fd635f30f6cc2cd69','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$0d868f9f-b6a7-4ad8-8c82-b2108dcfbda0sr\0java.util.Datehj�KYt\0\0xpw\0\0j���x','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0\nROLE_GUESTxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0guestsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0client_credentialsxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0trustxsq\0~\0!w\0\0\0?@\0\0\0\0\0q\0~\0xsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0!w\0\0\0?@\0\0\0\0\0\0xsq\0~\0!w\0\0\0?@\0\0\0\0\0\0xp'),('1e667450159642e87a35b561aa1c0d04','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$3086f0b8-8428-4340-9fa3-f4ce32d1bda6sr\0java.util.Datehj�KYt\0\0xpw\0\0k �\�rx','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0\nROLE_GUESTxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0guestsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0client_credentialsxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0trustxsq\0~\0!w\0\0\0?@\0\0\0\0\0q\0~\0xsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0!w\0\0\0?@\0\0\0\0\0\0xsq\0~\0!w\0\0\0?@\0\0\0\0\0\0xp'),('1d3ff0d56f988edbfa5634b3aef3f2c8','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$67513564-33fa-42aa-ae0d-4d870ea8eb06sr\0java.util.Datehj�KYt\0\0xpw\0\0kr\�\�x','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0loginsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0adminxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0admin'),('572d1a172eb2cd41c5df071f14da9ee3','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$48cc4cc9-c266-48ff-9505-71ef1632ebd5sr\0java.util.Datehj�KYt\0\0xpw\0\0k%Y\\�x','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0loginsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0cholnh1@naver.comxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0cholnh1@naver.com'),('7700d2d9c95adedcbd8ea25113a35e91','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$c223722c-2f42-452b-afb0-df1bbfe4910fsr\0java.util.Datehj�KYt\0\0xpw\0\0k%]�qx','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0loginsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0cholnh2@naver.comxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0cholnh2@naver.com'),('5d85695d16391acee70af5e91469bb04','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$40fd1225-81be-47af-8ecc-2ecf747f5feesr\0java.util.Datehj�KYt\0\0xpw\0\0k%�׋x','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0\nROLE_GUESTxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0guestsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0client_credentialsxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0trustxsq\0~\0!w\0\0\0?@\0\0\0\0\0q\0~\0xsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0!w\0\0\0?@\0\0\0\0\0\0xsq\0~\0!w\0\0\0?@\0\0\0\0\0\0xp'),('c1a3cc86c8216b5ca55a057f58ecf2aa','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$653af68d-0ebd-456c-b7db-8add4315f8fbsr\0java.util.Datehj�KYt\0\0xpw\0\0k*Bx','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0loginsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0cholnhxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0cholnh'),('d78c68e6e301a55cd823f03dba372eba','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$ebab7f7e-270f-4bc6-b743-3974365c6967sr\0java.util.Datehj�KYt\0\0xpw\0\0k1\'\�Cx','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0loginsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0d_duck@naver.comxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0d_duck@naver.com'),('ad1624bf1963909ac30c4580e620a334','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$a316fe90-4f0c-4840-93fc-c9fb9fb52b94sr\0java.util.Datehj�KYt\0\0xpw\0\0k:1�cx','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0loginsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0cholnh3@naver.comxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0cholnh3@naver.com'),('ac39d82f7a1f86f75f380faeaef912cf','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$28214603-67ec-49b9-a1b5-47df70758145sr\0java.util.Datehj�KYt\0\0xpw\0\0k,�\ZSx','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0\nROLE_GUESTxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0guestsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0client_credentialsxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0trustxsq\0~\0!w\0\0\0?@\0\0\0\0\0q\0~\0xsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0!w\0\0\0?@\0\0\0\0\0\0xsq\0~\0!w\0\0\0?@\0\0\0\0\0\0xp'),('76525f8d8bfa1b56c6e85a0e1502fe0d','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$2bc5b83f-312b-4aef-a0b8-3f28681efda1sr\0java.util.Datehj�KYt\0\0xpw\0\0k>�j\nx','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0\nROLE_GUESTxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0guestsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0client_credentialsxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0trustxsq\0~\0!w\0\0\0?@\0\0\0\0\0q\0~\0xsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0!w\0\0\0?@\0\0\0\0\0\0xsq\0~\0!w\0\0\0?@\0\0\0\0\0\0xp'),('f864a94c7b222efacd730f5499c040c4','�\�\0sr\0Lorg.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken/\�Gc�\�ɷ\0L\0\nexpirationt\0Ljava/util/Date;xr\0Dorg.springframework.security.oauth2.common.DefaultOAuth2RefreshTokens\�\ncT\�^\0L\0valuet\0Ljava/lang/String;xpt\0$0a79c3a2-d86c-4668-874b-524f46be3d40sr\0java.util.Datehj�KYt\0\0xpw\0\0j�\�+x','�\�\0sr\0Aorg.springframework.security.oauth2.provider.OAuth2Authentication�@bR\0L\0\rstoredRequestt\0<Lorg/springframework/security/oauth2/provider/OAuth2Request;L\0userAuthenticationt\02Lorg/springframework/security/core/Authentication;xr\0Gorg.springframework.security.authentication.AbstractAuthenticationTokenӪ(~nGd\0Z\0\rauthenticatedL\0authoritiest\0Ljava/util/Collection;L\0detailst\0Ljava/lang/Object;xp\0sr\0&java.util.Collections$UnmodifiableList�%1�\�\0L\0listt\0Ljava/util/List;xr\0,java.util.Collections$UnmodifiableCollectionB\0�\�^�\0L\0cq\0~\0xpsr\0java.util.ArrayListx�\��\�a�\0I\0sizexp\0\0\0w\0\0\0sr\0Borg.springframework.security.core.authority.SimpleGrantedAuthority\0\0\0\0\0\0�\0L\0rolet\0Ljava/lang/String;xpt\0	ROLE_USERxq\0~\0psr\0:org.springframework.security.oauth2.provider.OAuth2Request\0\0\0\0\0\0\0\0Z\0approvedL\0authoritiesq\0~\0L\0\nextensionst\0Ljava/util/Map;L\0redirectUriq\0~\0L\0refresht\0;Lorg/springframework/security/oauth2/provider/TokenRequest;L\0resourceIdst\0Ljava/util/Set;L\0\rresponseTypesq\0~\0xr\08org.springframework.security.oauth2.provider.BaseRequest6(z>�qi�\0L\0clientIdq\0~\0L\0requestParametersq\0~\0L\0scopeq\0~\0xpt\0loginsr\0%java.util.Collections$UnmodifiableMap��t�B\0L\0mq\0~\0xpsr\0java.util.HashMap\��\�`\�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\ngrant_typet\0passwordt\0usernamet\0test@naver.comxsr\0%java.util.Collections$UnmodifiableSet��я��U\0\0xq\0~\0	sr\0java.util.LinkedHashSet\�l\�Z�\�*\0\0xr\0java.util.HashSet�D�����4\0\0xpw\0\0\0?@\0\0\0\0\0t\0readt\0writet\0trustxsq\0~\0#w\0\0\0?@\0\0\0\0\0sq\0~\0\rt\0	ROLE_USERxsq\0~\0\Z?@\0\0\0\0\0\0w\0\0\0\0\0\0\0xppsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsq\0~\0#w\0\0\0?@\0\0\0\0\0\0xsr\0Oorg.springframework.security.authentication.UsernamePasswordAuthenticationToken\0\0\0\0\0\0�\0L\0credentialsq\0~\0L\0	principalq\0~\0xq\0~\0sq\0~\0sq\0~\0\0\0\0w\0\0\0q\0~\0xq\0~\01sr\0java.util.LinkedHashMap4�N\\l��\0Z\0accessOrderxq\0~\0\Z?@\0\0\0\0\0w\0\0\0\0\0\0q\0~\0q\0~\0q\0~\0q\0~\0x\0psr\02org.springframework.security.core.userdetails.User\0\0\0\0\0\0�\0Z\0accountNonExpiredZ\0accountNonLockedZ\0credentialsNonExpiredZ\0enabledL\0authoritiesq\0~\0L\0passwordq\0~\0L\0usernameq\0~\0xpsq\0~\0 sr\0java.util.TreeSetݘP��\�[\0\0xpsr\0Forg.springframework.security.core.userdetails.User$AuthorityComparator\0\0\0\0\0\0�\0\0xpw\0\0\0q\0~\0xpt\0test@naver.com');
/*!40000 ALTER TABLE `oauth_refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_tbl`
--

DROP TABLE IF EXISTS `order_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `box_no` int(11) NOT NULL COMMENT '박스 번호\n기사가 고객에게 물건을 나누어줄 때 사용하는 번호',
  `customer_idx` int(11) DEFAULT NULL COMMENT '고객 인덱스',
  `guest_idx` int(11) DEFAULT NULL,
  `employee_idx` int(11) DEFAULT NULL COMMENT '배달 직원 인덱스',
  `delivery_site_idx` int(11) DEFAULT NULL COMMENT '배달지 인덱스',
  `detail_site_idx` int(11) DEFAULT NULL,
  `type_payment` tinyint(4) NOT NULL COMMENT '주문 결제 방식',
  `state_order` tinyint(4) NOT NULL DEFAULT '0' COMMENT '주문 상태\n0 - 주문 대기\n1 - 주문 완료\n2 - 주문 실패\n3 - 배달 완료\n4 - 주문 취소\n5 - 주문 환불\n',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록 날짜',
  `arrival_date_only` date DEFAULT NULL COMMENT '받는 날짜',
  `arrival_time_only` time DEFAULT NULL,
  `using_point` int(11) DEFAULT NULL,
  `using_coupon_idx` int(11) DEFAULT NULL,
  `final_amount` int(11) DEFAULT NULL,
  `saved_point` int(11) DEFAULT NULL,
  `order_id` varchar(45) DEFAULT NULL,
  `receipt_id` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_order_cust_idx` (`customer_idx`),
  KEY `fk_order_emp_idx` (`employee_idx`),
  KEY `fk_order_dsite_idx` (`delivery_site_idx`),
  KEY `fk_order_dtsite_idx` (`detail_site_idx`),
  KEY `fk_order_gst_idx` (`guest_idx`),
  KEY `fk_order_cp_idx` (`using_coupon_idx`),
  CONSTRAINT `fk_order_cp` FOREIGN KEY (`using_coupon_idx`) REFERENCES `coupon_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_cust` FOREIGN KEY (`customer_idx`) REFERENCES `user_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_order_dsite` FOREIGN KEY (`delivery_site_idx`) REFERENCES `delivery_site_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_dtsite` FOREIGN KEY (`detail_site_idx`) REFERENCES `detail_for_delivery_site_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_emp` FOREIGN KEY (`employee_idx`) REFERENCES `employee_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_gst` FOREIGN KEY (`guest_idx`) REFERENCES `guest_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8 COMMENT='주문 목록 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_tbl`
--

LOCK TABLES `order_tbl` WRITE;
/*!40000 ALTER TABLE `order_tbl` DISABLE KEYS */;
INSERT INTO `order_tbl` VALUES (144,0,1,18,NULL,1,1,4,0,'2019-05-08 08:01:52','2019-05-08','06:00:00',0,NULL,13900,NULL,'ORDER_38_1557302511665',NULL,'010-6478-4899'),(145,1,1,18,NULL,1,1,1,0,'2019-05-08 08:02:21','2019-05-08','06:00:00',0,NULL,13900,NULL,'ORDER_38_1557302540743',NULL,'010-6478-4899'),(146,0,13,3,NULL,1,1,4,0,'2019-05-09 16:41:22','2019-05-10','12:00:00',0,NULL,17000,NULL,'ORDER_41_1557420081677',NULL,'010-2935-4191');
/*!40000 ALTER TABLE `order_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordertime_for_store_tbl`
--

DROP TABLE IF EXISTS `ordertime_for_store_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordertime_for_store_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `delivery_site_idx` int(11) DEFAULT NULL,
  `store_idx` int(11) DEFAULT NULL,
  `state_pause` tinyint(4) DEFAULT '0' COMMENT '해당 시간 일시정지 여부',
  `order_deadline` time NOT NULL,
  `arrival_time` time NOT NULL,
  `arrival_tomorrow` tinyint(4) DEFAULT '0',
  `sequence` smallint(6) DEFAULT NULL,
  `pause_description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_ots_dsite_idx` (`delivery_site_idx`),
  KEY `fk_ots_store_idx` (`store_idx`),
  CONSTRAINT `fk_ots_dsite` FOREIGN KEY (`delivery_site_idx`) REFERENCES `delivery_site_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ots_store` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8 COMMENT='업체 주문_가능_시간표 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordertime_for_store_tbl`
--

LOCK TABLES `ordertime_for_store_tbl` WRITE;
/*!40000 ALTER TABLE `ordertime_for_store_tbl` DISABLE KEYS */;
INSERT INTO `ordertime_for_store_tbl` VALUES (1,1,1,0,'11:40:00','12:00:00',0,1,NULL),(2,1,1,0,'12:40:00','13:00:00',0,2,NULL),(3,1,1,0,'16:40:00','17:00:00',0,3,NULL),(4,1,1,0,'17:40:00','18:00:00',0,4,NULL),(5,1,1,0,'18:50:00','19:00:00',0,5,NULL),(7,1,2,0,'11:40:00','12:00:00',0,1,NULL),(8,1,2,0,'12:40:00','13:00:00',0,2,NULL),(9,1,2,0,'16:40:00','17:00:00',0,3,NULL),(10,1,2,0,'17:40:00','18:00:00',0,4,NULL),(11,1,2,0,'18:40:00','19:00:00',0,5,NULL),(13,1,3,0,'11:40:00','12:00:00',0,1,NULL),(14,1,3,0,'12:40:00','13:00:00',0,2,NULL),(15,1,3,0,'16:40:00','17:00:00',0,3,NULL),(16,1,3,0,'17:40:00','18:00:00',0,4,NULL),(17,1,3,0,'18:40:00','19:00:00',0,5,NULL),(19,1,4,0,'11:40:00','12:00:00',0,1,NULL),(20,1,4,0,'12:40:00','13:00:00',0,2,NULL),(21,1,4,0,'16:40:00','17:00:00',0,3,NULL),(22,1,4,0,'17:40:00','18:00:00',0,4,NULL),(23,1,4,0,'18:40:00','19:00:00',0,5,NULL),(25,1,5,0,'11:40:00','12:00:00',0,1,NULL),(26,1,5,0,'12:40:00','13:00:00',0,2,NULL),(27,1,5,0,'16:40:00','17:00:00',0,3,NULL),(28,1,5,0,'17:40:00','18:00:00',0,4,NULL),(29,1,5,0,'18:40:00','19:00:00',0,5,NULL),(31,1,6,0,'11:40:00','12:00:00',0,1,NULL),(32,1,6,0,'12:40:00','13:00:00',0,2,NULL),(33,1,6,0,'16:40:00','17:00:00',0,3,NULL),(34,1,6,0,'17:40:00','18:00:00',0,4,NULL),(35,1,6,0,'18:40:00','19:00:00',0,5,NULL),(36,1,7,0,'11:40:00','12:00:00',0,1,NULL),(37,1,7,0,'12:40:00','13:00:00',0,2,NULL),(38,1,7,0,'16:40:00','17:00:00',0,3,NULL),(39,1,7,0,'17:40:00','18:00:00',0,4,NULL),(40,1,7,0,'18:40:00','19:00:00',0,5,NULL),(41,1,8,0,'11:40:00','12:00:00',0,1,NULL),(42,1,8,0,'12:40:00','13:00:00',0,2,NULL),(43,1,8,0,'16:40:00','17:00:00',0,3,NULL),(44,1,8,0,'17:40:00','18:00:00',0,4,NULL),(45,1,8,0,'18:40:00','19:00:00',0,5,NULL),(46,1,9,0,'11:40:00','12:00:00',0,1,NULL),(47,1,9,0,'12:40:00','13:00:00',0,2,NULL),(48,1,9,0,'16:40:00','17:00:00',0,3,NULL),(49,1,9,0,'17:40:00','18:00:00',0,4,NULL),(50,1,9,0,'18:40:00','19:00:00',0,5,NULL);
/*!40000 ALTER TABLE `ordertime_for_store_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `owner_of_store_tbl`
--

DROP TABLE IF EXISTS `owner_of_store_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `owner_of_store_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `store_idx` int(11) DEFAULT NULL,
  `id` varchar(45) NOT NULL COMMENT '아이디',
  `pw` varchar(150) NOT NULL COMMENT '패스워드',
  `name` varchar(150) NOT NULL COMMENT '패스워드',
  `gender` tinyint(4) DEFAULT NULL COMMENT '성별\n0 - 남자\n1 - 여자',
  `year_of_birth` smallint(6) DEFAULT NULL,
  `month_of_birth` tinyint(4) DEFAULT NULL,
  `days_of_birth` tinyint(4) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `state_active` tinyint(4) NOT NULL DEFAULT '1' COMMENT '활성화 여부 상태\n0 - 비활성화\n1 - 활성화',
  `hire_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `type_role` tinyint(4) NOT NULL COMMENT '업무 별 역할\n0 - 업주\n1 - 매니저\n2 - 직원',
  PRIMARY KEY (`idx`),
  KEY `fk_owner_store_idx` (`store_idx`),
  CONSTRAINT `fk_owner_store` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='업주 관리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `owner_of_store_tbl`
--

LOCK TABLES `owner_of_store_tbl` WRITE;
/*!40000 ALTER TABLE `owner_of_store_tbl` DISABLE KEYS */;
INSERT INTO `owner_of_store_tbl` VALUES (1,1,'moms1','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','맘스터치',0,1979,1,1,'-','-',1,'2019-05-07 03:28:14','2019-05-07 03:28:14','2019-05-07 03:28:43',0),(2,2,'hansot','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','한솥',0,1979,1,1,'-','-',1,'2019-05-07 03:28:14','2019-05-07 03:28:14','2019-05-07 03:28:43',0),(3,3,'pizza','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','피자스쿨',0,1979,1,1,'-','-',1,'2019-05-07 03:28:14','2019-05-07 03:28:14','2019-05-07 03:28:43',0),(4,4,'gamaro','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','가마로',0,1979,1,1,'-','-',1,'2019-05-07 03:28:14','2019-05-07 03:28:14','2019-05-07 03:28:43',0),(5,5,'gang','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','강순자김치찌개',0,1979,1,1,'-','-',1,'2019-05-07 03:28:14','2019-05-07 03:28:14','2019-05-07 03:28:43',0),(6,6,'misoya','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','미소야',0,1979,1,1,'-','-',1,'2019-05-07 03:28:14','2019-05-07 03:28:14','2019-05-07 03:28:43',0),(7,7,'chicken','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','굽네',0,1979,1,1,'-','-',1,'2019-05-07 03:28:14','2019-05-07 03:28:14','2019-05-07 03:28:43',0),(8,8,'sin','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','신전',0,1979,1,1,'-','-',1,'2019-05-07 03:28:14','2019-05-07 03:28:14','2019-05-07 03:28:43',0),(9,9,'back','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','빽다방',0,1979,1,1,'-','-',1,'2019-05-07 03:28:14','2019-05-07 03:28:14','2019-05-07 03:28:43',0);
/*!40000 ALTER TABLE `owner_of_store_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `policy_tbl`
--

DROP TABLE IF EXISTS `policy_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `policy_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `policy_name` varchar(100) DEFAULT NULL,
  `policy_contents` text,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `policy_tbl`
--

LOCK TABLES `policy_tbl` WRITE;
/*!40000 ALTER TABLE `policy_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `policy_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_tbl`
--

DROP TABLE IF EXISTS `product_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `store_idx` int(11) DEFAULT NULL COMMENT '판매 업체 인덱스',
  `name` varchar(100) NOT NULL COMMENT '제품 이름',
  `description` text COMMENT '제품 설명',
  `sub_description` varchar(300) DEFAULT NULL COMMENT '제품 부가 설명',
  `category_id` int(11) DEFAULT NULL COMMENT '대분류 인덱스',
  `category_name` varchar(100) DEFAULT NULL COMMENT '대분류 이름',
  `state_active` tinyint(4) NOT NULL DEFAULT '1' COMMENT '제품 상태\n0 - 비활성화\n1 - 활성화',
  `type` tinyint(4) NOT NULL COMMENT '상품 판매 종류\n0 - 메인\n1 - 서브\n2 - 토핑\n3 - 음료',
  `cnt_like` int(11) DEFAULT NULL,
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sequence` int(11) DEFAULT NULL COMMENT '진열 순서',
  PRIMARY KEY (`idx`),
  KEY `fk_prod_store_idx` (`store_idx`),
  CONSTRAINT `fk_prod_store` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=363 DEFAULT CHARSET=utf8 COMMENT='판매되는 제품에 관한 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_tbl`
--

LOCK TABLES `product_tbl` WRITE;
/*!40000 ALTER TABLE `product_tbl` DISABLE KEYS */;
INSERT INTO `product_tbl` VALUES (21,1,'화이트 갈릭버거 세트','<br>세트 메뉴에는 <b>버거 + 케이준양념감자 + 펩시 콜라 (355ml)</b> 로 구성됩니다.<br>1000원 추가 시 양념감자 → 치즈감자로 변경 (추가사항에서 변경 가능)<br><br>\n크림처럼 부드러운 화이트 갈릭 소스에 <br>프리미엄 더블햄과 통가슴살 패티까지<br> 하나에 다 담은 묵직한 버거! ',NULL,1,'세트',1,0,0,'2019-05-07 13:08:13','2019-05-07 13:12:55',36),(22,1,'화이트 갈릭버거 세트','<br>세트 메뉴에는 <b>버거 + 케이준양념감자 + 펩시 콜라 (355ml)</b> 로 구성됩니다.<br>1000원 추가 시 양념감자 → 치즈감자로 변경 (추가사항에서 변경 가능)<br><br>\n크림처럼 부드러운 화이트 갈릭 소스에 <br>프리미엄 더블햄과 통가슴살 패티까지<br> 하나에 다 담은 묵직한 버거! ',NULL,1,'세트',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',36),(23,1,'싸이버거 세트','<br>세트 메뉴에는 <b>버거 + 케이준양념감자 + 펩시 콜라 (355ml)</b> 로 구성됩니다.<br>1000원 추가 시 양념감자 → 치즈감자로 변경 (추가사항에서 변경 가능)<br><br>\n매콤한 통다리살 패티가 통째로~~<br>\n매콤한 양파와 신선한 양상추가 절묘하게 조화를 이뤄  <br>\n깔끔한 맛을 느낄수 있는 프리미엄 수제버거',NULL,1,'세트',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',3),(24,1,'불싸이버거 세트','<br>세트 메뉴에는 <b>버거 + 케이준양념감자 + 펩시 콜라 (355ml)</b> 로 구성됩니다.<br>1000원 추가 시 양념감자 → 치즈감자로 변경 (추가사항에서 변경 가능)<br><br>\n불 맛이 살아있는<br>\n싸이버거의 진火<br>\n<br>\n맘스터치의 베스트 메뉴인 싸이버거를<br>\n매운 버전으로 즐길 수 있는 메뉴<br>',NULL,1,'세트',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',4),(25,1,'마살라버거 세트','<br>세트 메뉴에는 <b>버거 + 케이준양념감자 + 펩시 콜라 (355ml)</b> 로 구성됩니다.<br>1000원 추가 시 양념감자 → 치즈감자로 변경 (추가사항에서 변경 가능)<br><br>\n다양한 양념과 버터가 들어간 마살라 소스로 <br>\n고소하고 깊은 인도의 풍미를 느낄 수 있는 버거<br>',NULL,1,'세트',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',5),(26,1,'치즈베이컨버거 세트','<br>세트 메뉴에는 <b>버거 + 케이준양념감자 + 펩시 콜라 (355ml)</b> 로 구성됩니다.<br>1000원 추가 시 양념감자 → 치즈감자로 변경 (추가사항에서 변경 가능)<br><br>\n두툼한 통가슴살패티와 바삭하고 스모키한 베이컨칩,<br>\n부드러운 화이트치즈소스가 어우러진 환상적인 조합의 프리미엄 버거',NULL,1,'세트',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',6),(27,1,'딥치즈버거 세트','<br>세트 메뉴에는 <b>버거 + 케이준양념감자 + 펩시 콜라 (355ml)</b> 로 구성됩니다.<br>1000원 추가 시 양념감자 → 치즈감자로 변경 (추가사항에서 변경 가능)<br><br>\n부드러운 치즈와 한층 더 촉촉해진 닭가슴살로<br>\n딥~하게 빠져드는 버거',NULL,1,'세트',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',7),(28,1,'케이준 양념감자',NULL,NULL,3,'서브',1,1,0,'2019-05-07 13:11:59','2019-05-07 13:15:17',50),(29,1,'스파이시디럭스불고기버거 세트','<br>세트 메뉴에는 <b>버거 + 케이준양념감자 + 펩시 콜라 (355ml)</b> 로 구성됩니다.<br>1000원 추가 시 양념감자 → 치즈감자로 변경 (추가사항에서 변경 가능)<br><br>\n매콤한 맛의 불고기소스와 풍미 가득한 불고기 패티가 두장!!<br>\n여기에 신선한 양상추와 고소한 치즈가 완벽한 조화를 이뤄 <br>\n매콤하면서도 부드러운 불고기 프리미엄 버거',NULL,1,'세트',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',8),(30,1,'디럭스불고기버거 세트','<br>세트 메뉴에는 <b>버거 + 케이준양념감자 + 펩시 콜라 (355ml)</b> 로 구성됩니다.<br>1000원 추가 시 양념감자 → 치즈감자로 변경 (추가사항에서 변경 가능)<br><br>\n',NULL,1,'세트',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',9),(31,1,'치킨커틀렛버거 세트','<br>세트 메뉴에는 <b>버거 + 케이준양념감자 + 펩시 콜라 (355ml)</b> 로 구성됩니다.<br>1000원 추가 시 양념감자 → 치즈감자로 변경 (추가사항에서 변경 가능)<br><br>\n',NULL,1,'세트',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',10),(32,1,'휠렛버거 세트','<br>세트 메뉴에는 <b>버거 + 케이준양념감자 + 펩시 콜라 (355ml)</b> 로 구성됩니다.<br>1000원 추가 시 양념감자 → 치즈감자로 변경 (추가사항에서 변경 가능)<br><br>\n',NULL,1,'세트',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',11),(33,1,'햄치즈휠렛버거 세트','<br>세트 메뉴에는 <b>버거 + 케이준양념감자 + 펩시 콜라 (355ml)</b> 로 구성됩니다.<br>1000원 추가 시 양념감자 → 치즈감자로 변경 (추가사항에서 변경 가능)<br><br>\n',NULL,1,'세트',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',12),(34,1,'스파이시 불고기버거 세트','<br>세트 메뉴에는 <b>버거 + 케이준양념감자 + 펩시 콜라 (355ml)</b> 로 구성됩니다.<br>1000원 추가 시 양념감자 → 치즈감자로 변경 (추가사항에서 변경 가능)<br><br>\n',NULL,1,'세트',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',13),(35,1,'불고기버거 세트','<br>세트 메뉴에는 <b>버거 + 케이준양념감자 + 펩시 콜라 (355ml)</b> 로 구성됩니다.<br>1000원 추가 시 양념감자 → 치즈감자로 변경 (추가사항에서 변경 가능)<br><br>\n',NULL,1,'세트',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',14),(36,1,'할라피뇨 통살 버거 세트','<br>세트 메뉴에는 <b>버거 + 케이준양념감자 + 펩시 콜라 (355ml)</b> 로 구성됩니다.<br>1000원 추가 시 양념감자 → 치즈감자로 변경 (추가사항에서 변경 가능)<br><br>\n',NULL,1,'세트',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',15),(37,1,'리샐 버거 세트','<br>세트 메뉴에는 <b>버거 + 케이준양념감자 + 펩시 콜라 (355ml)</b> 로 구성됩니다.<br>1000원 추가 시 양념감자 → 치즈감자로 변경 (추가사항에서 변경 가능)<br><br>\n',NULL,1,'세트',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',16),(38,1,'통새우버거 세트','<br>세트 메뉴에는 <b>버거 + 케이준양념감자 + 펩시 콜라 (355ml)</b> 로 구성됩니다.<br>1000원 추가 시 양념감자 → 치즈감자로 변경 (추가사항에서 변경 가능)<br><br>\n',NULL,1,'세트',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',17),(39,1,'불갈비치킨버거 세트','<br>세트 메뉴에는 <b>버거 + 케이준양념감자 + 펩시 콜라 (355ml)</b> 로 구성됩니다.<br>1000원 추가 시 양념감자 → 치즈감자로 변경 (추가사항에서 변경 가능)<br><br>\n',NULL,1,'세트',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',18),(40,1,'스파이시디럭스불고기버거','매콤한 맛의 불고기소스와 풍미 가득한 불고기 패티가 두장!!<br>\n여기에 신선한 양상추와 고소한 치즈가 완벽한 조화를 이뤄 <br>\n매콤하면서도 부드러운 불고기 프리미엄 버거',NULL,2,'단품',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',25),(41,1,'디럭스불고기버거','풍미 가득한 불고기 패티 두장에<br>\n신선한 양상추와 고소한 치즈를 더한 완벽한 조합의 프리미엄 버거\n',NULL,2,'단품',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',26),(42,1,'치킨커틀렛버거','새콤달콤 커틀렛 소스와 아삭한 양배추채,<br>\n두툼하고 촉촉한 통 가슴살패티로 색다르게 즐기는 버거',NULL,2,'단품',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',27),(43,1,'휠렛버거','휠렛버거의 상큼함과 담백함을 느껴보세요<br>\n보기만해도 먹음직~! 치킨, 상큼한 양상치가 만들어내는 휠렛버거',NULL,2,'단품',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',28),(44,1,'햄치즈휠렛버거','영양가득 푸짐~한 영양치킨버거<br>\n보기만해도 먹음직~! 햄과 치킨, <br>\n상큼한 양상치가 만들어내는 맛이 일품!',NULL,2,'단품',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',29),(45,1,'스파이시 불고기버거','매콤한 맛의 불고기소스와 풍미 가득한 불고기패티로 <br>\n매콤하게 즐기는 <br><br>\n\n\'스파이시 불고기버거\'\n',NULL,2,'단품',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',30),(46,1,'불고기버거','영양만점의불고기 패티와 고소한 불고기 소스의 조화!<br>\n영양만점의 소고기를 원료로 만들어 낸 불고기패티에 <br>\n고소한 불고기 소스를 가미하여 한층 더 맛있는 영양버거',NULL,2,'단품',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',31),(47,1,'할라피뇨 통살 버거','닭고기 통다리살과 할라피뇨의 매콤한 조화<br>\n닭고기 통다리살의 패티와 매콤한 할라피뇨, <br>\n신선한 야채와 달콤한 소스가 잘 조화를 이룬 <br>\n매콤한 수제 버거 할라피뇨 통살버거',NULL,2,'단품',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',32),(48,1,'리샐 버거','프레쉬한 리코타 샐러드를 버거로 만나다<br><br>\n\n두툼한 닭가슴살 패티에<br>\n부드러운 리코타와 산뜻한 샐러드,<br>\n상큼한 발사믹 소스를 더해 완성한 버거',NULL,2,'단품',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',33),(49,1,'통새우버거','기존의 새우버거를 업그레이드 한 프리미엄 버거<br>\n통새우살의 씹히는 맛이 강하며 <br>\n겉표면이 코코넛가루로 입혀져 고소하면서 바삭한 <br>\n프리미엄 통새우 버거',NULL,2,'단품',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',34),(50,1,'불갈비치킨버거','달콤매콤한 불갈비 소스와<br>\n쫄깃한 치킨패티의 맛있는 조화\n\n',NULL,2,'단품',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',35),(51,1,'화이트 갈릭버거','크림처럼 부드러운 화이트 갈릭 소스에 <br>프리미엄 더블햄과 통가슴살 패티까지<br> 하나에 다 담은 묵직한 버거! ',NULL,2,'단품',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',19),(52,1,'싸이버거','매콤한 통다리살 패티가 통째로~~<br>\n매콤한 양파와 신선한 양상추가 절묘하게 조화를 이뤄  <br>\n깔끔한 맛을 느낄수 있는 프리미엄 수제버거',NULL,2,'단품',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',20),(53,1,'불싸이버거','불 맛이 살아있는<br>\n싸이버거의 진火<br>\n<br>\n맘스터치의 베스트 메뉴인 싸이버거를<br>\n매운 버전으로 즐길 수 있는 메뉴<br>',NULL,2,'단품',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',21),(54,1,'마살라버거','다양한 양념과 버터가 들어간 마살라 소스로 <br>\n고소하고 깊은 인도의 풍미를 느낄 수 있는 버거<br>',NULL,2,'단품',1,0,0,'2019-05-07 13:11:59','2019-05-07 13:12:55',22),(55,1,'치즈베이컨버거','두툼한 통가슴살패티와 바삭하고 스모키한 베이컨칩,<br>\n부드러운 화이트치즈소스가 어우러진 환상적인 조합의 프리미엄 버거',NULL,2,'단품',1,0,0,'2019-05-07 13:12:00','2019-05-07 13:12:55',23),(56,1,'딥치즈버거','부드러운 치즈와 한층 더 촉촉해진 닭가슴살로<br>\n딥~하게 빠져드는 버거',NULL,2,'단품',1,0,0,'2019-05-07 13:12:00','2019-05-07 13:12:55',24),(57,1,'인크레더블 버거 세트','에그프라이, 프리미엄 더블햄, 통다리살패티에<br>\n신선한 양상추가 들어간 <br>\n크고 확실한 행복을 즐길 수 있는 대확행 버거',NULL,1,'세트',1,0,0,'2019-05-07 13:12:00','2019-05-07 13:12:55',1),(58,1,'인크레더블 버거','에그프라이, 프리미엄 더블햄, 통다리살패티에<br>',NULL,2,'단품',1,0,0,'2019-05-07 13:12:00','2019-05-07 13:12:55',2),(59,1,'싸이버거 세트 X 2개','싸이버거 세트 2개<br><b>고객 배달요금 400원 할인</b>','<b>고객 배달요금 400원 할인</b>',1,'세트',1,0,0,'2019-05-07 13:12:00','2019-05-07 13:12:55',0),(60,1,'싸이버거 세트 X 3개','싸이버거 세트 3개<br><b>고객 배달요금 800원 할인</b>','<b>고객 배달요금 800원 할인</b>',1,'세트',1,0,0,'2019-05-07 13:12:00','2019-05-07 13:12:55',0),(61,1,'싸이버거 세트 X 4개','싸이버거 세트 4개<br><b>고객 배달요금 1200원 할인</b>','<b>고객 배달요금 1200원 할인</b>',1,'세트',1,0,0,'2019-05-07 13:12:00','2019-05-07 13:12:55',0),(62,1,'싸이버거 세트 X 5개','싸이버거 세트 5개<br><b>고객 배달요금 2200원 할인</b>','<b>고객 배달요금 2200원 할인</b>',1,'세트',1,0,0,'2019-05-07 13:12:00','2019-05-07 13:12:55',0),(63,1,'언빌리버블버거','통새우, 에그프라이, 부드러운 통가슴살에 <br>\n매콤한 스리라차 마요 소스를 더한<br>\n환상적인 조합의 대확행버거',NULL,2,'단품',1,0,0,'2019-05-07 13:12:00','2019-05-07 13:12:55',1),(64,1,'언빌리버블버거 세트','<br>세트 메뉴에는 <b>버거 + 케이준양념감자 + 펩시 콜라 (355ml)</b> 로 구성됩니다.<br>1000원 추가 시 양념감자 → 치즈감자로 변경 (추가사항에서 변경 가능)<br><br>',NULL,1,'세트',1,0,0,'2019-05-07 13:12:00','2019-05-07 13:12:55',1),(65,2,'치킨마요','[베스트&스테디셀러 SINCE 2003] <br>\n2003년에 한국 최초로 개발 출시한 한솥 대표 인기 메뉴입니다. <br>\n밥 위에 지단채와 슬라이스한 치킨가라아게를 올렸습니다. <br>\n따로 드리는 조미 김을 잘게 부수어 넣고 <br>\n 마요드레싱과 덮밥소스를 뿌린 후 비벼 먹는 맛이 일품입니다.<br>',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',0),(66,2,'빅치킨마요','배부르게 먹고 싶은 날 즐겨보세요. <br>\n치킨마요에 밥(+70g), 치킨(+15g), 소스(+10g) <br>\n모두 빅으로 푸짐하게 담았습니다.<br>',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',1),(67,2,'왕치킨마요','마요시리즈 중 가장 양이 많은 빅치킨마요보다<br> \n더 푸짐한 양을 원하는 고객을 위해<br> \n밥 370g에 치킨양도 늘리고, 커플소스도 2개씩 제공됩니다.<br>\n',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',2),(68,2,'한솥 철판볶음밥','고기집에서 먹는 볶음밥 형태로<br>\\n짭조름한 베이컨, 아삭한 양파, 대파, 홍 피망을 넣어 불 맛을 잘 살렸습니다. <br>\\n영양 가득한 계란프라이와 김 가루와 함께 풍미 가득한 철판볶음밥을 즐겨보세요.<br>',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',3),(69,2,'돈까스 카레','[베스트&스테디셀러 SINCE 1993]<br>\n두툼한 국내산 등심 돈까스와 누구도 흉내낼 수 없는 <br>\n한결같은 맛을 고수해 온 한솥 카레를 함께 즐겨보세요.<br>',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',4),(70,2,'치킨제육 도시락','치킨가라아게와 부드러운 제육볶음으로 구성한 도시락입니다.<br> \n짭조름한 치킨과 매콤한 제육볶음으로 든든한 한 끼가 됩니다.<br>',NULL,2,'사각도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',5),(71,2,'동백 도시락','[베스트&스테디셀러 SINCE 2008]<br>\n연하고 부드러운 햄버그에 쫄깃한 떡을 넣은 떡햄버그와 <br>\n탱글탱글한 식감이 살아있는 새우튀김, 호주산 소불고기, <br>\n한솥만의 명품 치킨, 해물 볼어묵, 각종 밑반찬은 물론 김, <br>\n타르타르소스가 들어간 도시락입니다.',NULL,2,'사각도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',6),(72,2,'돈치 고기고기 도시락','돈까스, 치킨가라아게, 소불고기, 제육볶음으로 구성한 돈.치.고기.고기 도시락입니다.<br>\n고기반찬과 튀김을 함께 즐기고 싶은 분에게 추천합니다.<br>\n한솥의 돈까스는 국내산 돼지고기의 등심 부위를 도톰하게 썰어 튀겨 육즙이 풍부합니다.<br>',NULL,2,'사각도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',7),(73,2,'돈까스 도련님 도시락','[베스트&스테디셀러 SINCE 1994] <br>\n도련님 도시락의 오징어까스 대신 <br>\n두툼한 돼지고기 등심으로 만든 돈까스를 넣어 구성했습니다. <br>\n한솥의 대표 메뉴입니다.<br>',NULL,2,'사각도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',8),(74,2,'불닭 비빔밥','입맛이 없는 여름철 식욕을 자극할 수 있는 화끈하고 매운 맛에,<br>\n따뜻한 음식인 닭을 활용하여 이열치열로 영양가 있는 음식을 섭취하여<br>\n몸을 보양 할 수 있도록 하였습니다.',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',9),(75,2,'돈치마요','[베스트&스테디셀러 SINCE 1993]<br>\n두툼한 국내산 등심 돈까스와 누구도 흉내낼 수 없는 <br>\n한결같은 맛을 고수해 온 한솥 카레를 함께 즐겨보세요.<br>',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',10),(76,2,'참치 샐러드마요','담백한 참치와 신선한 6가지 채소를 듬뿍 올려 비벼 먹는 건강한 도시락입니다. 비타민, 섬유질, 단백질, 탄수화물 등 한 그릇에 영양이 골고루 담겨 있습니다.',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',11),(77,2,'치킨 샐러드마요','한솥 대표 메뉴 치킨마요에 신선한 6가지 채소를 듬뿍 넣어 비벼 먹는 메뉴입니다. 채소가 느끼함은 잡아주고 영양 균형도 맞춰주며 포만감도 더해줍니다.',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',12),(78,2,'참치마요','[베스트&스테디셀러 SINCE 2003] 치킨마요에 치킨 대신 담백한 참치를 올렸습니다. 조미 김을 부수어 넣고 마요드레싱과 덮밥소스를 뿌려 조금씩 비벼 드세요.',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',13),(79,2,'소불고기 카레','부드러운 호주산 소불고기와 누구도 흉내낼 수 없는 한결같은 맛을 고수해 온 한솥 카레를 함께 즐겨보세요.',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',14),(80,2,'카레도시락','[베스트&스테디셀러 SINCE 1993] 누구도 흉내 낼 수 없는 한결같은 맛을 고수해오며 20년 동안 인기를 이어온 오리지널 한솥 카레도시락. 일반 레토르트 카레와는 비교가 안 되는 한솥 카레의 맛을 경험해보세요.',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',15),(81,2,'소불고기 덮밥','부드러운 소불고기와 달콤한 한솥 특제 덮밥소스에 볶음김치, 청양고추가 더해져 맛과 영양이 풍부한 한국식 퓨전 소불고기 덮밥입니다.',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',16),(82,2,'새우돈까스 덮밥','흰다리새우(중하)를 통째로 튀긴 새우튀김과 국내산 돼지고기의 등심 부분만을 두껍게 썰어 만든 돈까스를 함께 올린 고급 덮밥입니다. 정통 일식 레스토랑 \"미타니야\"의 돈부리 소스를 재현한 한솥만의 특제 덮밥소스를 사용합니다.',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',17),(83,2,'돈까스 덮밥','[베스트&스테디셀러 SINCE 2006] 정통 일식 레스토랑 \"미타니야\"의 돈부리 소스를 재현한 한솥만의 특제 덮밥소스를 사용합니다. 국내산 돼지고기의 등심 부분만을 두껍게 썰어 바삭하게 튀긴 돈까스를 한솥밥 위에 올려 먹는 덮밥입니다.',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',18),(84,2,'소불고기 철판볶음밥','고기집에서 먹는 볶음밥 형태로 짭조름한 베이컨, 아삭한 양파, 대파, 홍 피망을 넣어 불 맛을 잘 살렸습니다. 달콤한 소불고기와 단백한 지단 채 그리고 김 가루와 함께 풍미 가득한 불고기철판볶음밥을 즐겨보세요.',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',19),(85,2,'소고기 육개장','진한 사골육수에 단백질이 풍부한 소고기와 감기, 피로회복에 좋은 신선한 대파를 듬뿍 넣어 얼큰하고 깊은 풍미의 보양식',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',20),(86,2,'묵은지 김치찌개','오롯이 우리 농산물로 만든 김치를 장시간 저온 숙성하여 진한 사골육수와 부드러운 돼지 앞다리살과 함께 오랜 시간 뭉근하게 끓여 칼칼한 찌개',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',21),(87,2,'왕카레 돈까스 덮밥','곱빼기 밥(300g)에 카레와 돈까스덮밥을 합친 메뉴입니다. 식사양이 많은 분 혹은 2가지 메뉴를 동시에 드시고 싶으실때 이용하세요.',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',22),(88,2,'새치 고기고기','새우튀김, 치킨가라아게, 불고기, 제육볶음으로 구성한 새.치.고기.고기 도시락입니다. 새우튀김은 흰다리새우(중하)가 통으로 들어가 탱글탱글한 식감이 살아있으며 치킨가라아게는 부드러운 순 닭다리살을 간장으로 가미한 후 튀겨내 짭조름한 감칠맛이 좋습니다.',NULL,2,'사각도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',23),(89,2,'고기고기','두 가지 고기반찬을 넣어 푸짐한 도시락입니다. 한솥이 개발한 특제 불고기 소스에 부드러운 호주산 쇠고기를 재워 만든 소불고기, 부드러우면서도 씹는 맛이 살아있는 매콤한 제육볶음을 모두 드실 수 있습니다.',NULL,2,'사각도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',24),(90,2,'국화','[베스트&스테디셀러 SINCE 1994] 두툼한 국내산 돼지고기 등심 돈까스, 담백한 오징어로 만든 오징어까스, 부드러우면서도 씹는 맛이 살아있는 제육볶음, 계절별 밑반찬이 모두 담긴 모둠 도시락입니다.',NULL,2,'사각도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',25),(91,2,'제육볶음','볶음용으로 가장 맛있는 부위로 만든 제육볶음을 듬뿍 담았습니다. 간이 잘 배고 부드러운 제육볶음과 계절 반찬으로 구성해 누구나 좋아합니다.',NULL,2,'사각도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',26),(92,2,'돈치불고기','돈까스, 치킨가라아게, 소불고기로 구성된 돈.치.불고기도시락입니다. 다양한 메뉴를 골고루 맛볼 수 있어 누구나 좋아합니다.',NULL,2,'사각도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',27),(93,2,'생선까스 도련님','[베스트&스테디셀러 SINCE 1994] 한솥을 대표하는 도시락 중 하나입니다. 담백한 오징어까스와 떡햄버그, 치킨가라아게 등 인기메뉴를 골고루 즐겨보세요.',NULL,2,'사각도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',28),(94,2,'시골제육 두부강된장 비빔밥','구수한 된장에 갖은 야채와 부드러운 두부를 넣어 깊은 풍미의 한솥 두부강된장을 만들었습니다. 신선한 야채, 매콤한 제육볶음, 구수한 두부강된장이 한데 조화롭게 어우러지는 건강한 비빔밥입니다.',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',29),(95,2,'소불고기 감초고추장 비빔밥','신선한 채소, 지단채, 김가루, 특제 소스로 조리한 소불고기, 계란후라이에 한솥의 노하우가 담긴 감초 고추장을 넣어 비벼먹는 메뉴입니다. 감초의 부드러운 단맛과 다양한 재료들의 맛이 한데 잘 어우러지는 건강한 비빔밥입니다.',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',30),(96,2,'치즈 불닭비빔밥','식욕을 자극할 수 있는 화끈하고 매운 맛에, 따뜻한 음식인 닭을 활용하여 이열치열로 영양가 있는 음식을 섭취하여 몸을 보양 할 수 있도록 하였습니다.',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',31),(97,2,'열무 두부강된장 비빔밥','구수한 된장에 갖은 야채와 부드러운 두부를 넣어 깊은 풍미의 한솥 두부강된장을 만들었습니다. 신선한 야채, 아삭아삭한 무생채와 열무김치, 구수한 두부강된장이 어우러져 싱그러운 맛을 내는 건강한 비빔밥입니다.',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',32),(98,2,'참치야채 감초고추장 비빔밥','기름기를 뺀 담백한 통조림 참치에 신선한 채소를 듬뿍 넣고 한솥의 노하우가 담긴 감초 고추장과 마요소스를 넣어 비벼먹는 메뉴입니다. 감초 고추장의 매콤한 맛과 마요소스의 고소한 맛이 어우러져 입맛을 돋워주는 건강한 비빔밥입니다.',NULL,1,'보울도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',33),(99,2,'매화 (치킨, 연어구이)','[베스트 & 스테디셀러 SINCE 2007] 12가지 다양한 반찬으로 구성된 프리미엄 도시락입니다. 푸짐한 반찬과 함께 촉촉하고 부드러운 연어구이와 치킨이 구성되어 있어 부족하지 않고 든든하게 드실 수 있는 도시락 입니다. 생수와 조미 김이 함께 제공 됩니다.',NULL,3,'프리미엄',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',34),(100,2,'개나리 (순살 고등어데리야끼)','[베스트 & 스테디셀러 SINCE 2008] 고등어데리야끼, 소 불고기, 새우튀김, 치킨 등 누구나 좋아하는 메뉴로 구성된 도시락입니다.',NULL,3,'프리미엄',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',36),(101,2,'진달래','한솥 도시락 Top 5 메뉴 중 하나. 떡 햄버그, 돈까스, 새우튀김, 치킨 가라아게, 제육볶음이 모두 들어 있어 푸짐합니다. 생수와 조미 김이 함께 제공 됩니다.',NULL,3,'프리미엄',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',38),(102,2,'순살 고등어조림','뼈와 가시를 발라내 먹기 좋은 고등어를 한국인 입맛에 맞는 칼칼한 특제 양념 소스로 조렸습니다. 홍고추와 통마늘로 비린내까지 잡아 고등어 순살의 맛이 맛깔스럽습니다.',NULL,3,'프리미엄',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',39),(103,2,'깐깐도시락','깐깐하게 만들어 깐깐도시락입니다. 탱글탱글한 칠리새우와 매콤달콤한 깐풍기를 함께 즐겨보세요',NULL,3,'프리미엄',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',40),(104,2,'데미그라스 함박스테이크','육즙이 살아있고 부드러운 함박스테이크를 따끈하게 즐겨보세요. 국내산 돼지고기와 호주산 쇠고기, 생 빵가루 등 좋은 재료로만 만든 함박스테이크에 풍미 좋은 데미그라스까지 곁들였습니다. 칼 없이 젓가락으로도 잘라 드실 수 있을 만큼 부드럽습니다.',NULL,3,'프리미엄',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',41),(105,2,'나시고랭','CNN에서 선정한 세계에서 가장 맛있는 음식 2위인 인도네시아 전통 볶음밥 나시고랭! 안남미를 사용한 전통 나시고랭이 아닌 한국인의 입 맛을 위한 나시고랭으로 다시 태어나다!',NULL,3,'프리미엄',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',42),(106,2,'깐풍기 도시락','겉은 바삭하고 속은 촉촉하게 튀긴 닭고기에 매콤달콤한 깐풍기소스와 청양고추까지 더했습니다. 깐풍기도시락으로 한솥에서 중화요리의 진수를 느껴보세요.',NULL,3,'프리미엄',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',43),(107,2,'닭강정 (중)','한솥에서 1년이 넘는 기간동안 연구 개발한 닭강정은 한 번 먹으면 자꾸 생각나는 매콤달콤한 소스가 매력적입니다.',NULL,4,'간식',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',44),(108,2,'닭강정 (소)','한솥에서 1년이 넘는 기간동안 연구 개발한 닭강정은 한 번 먹으면 자꾸 생각나는 매콤달콤한 소스가 매력적입니다.',NULL,4,'간식',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',45),(109,2,'치킨박스(대) + 케이준감자 ','일본식 닭튀김으로 부드러운 순 닭다리살을 간장에 재웠다가 한솥이 개발한 튀김옷을 얇게 입혀 튀겨내 감칠 맛과 식감이 좋습니다.',NULL,4,'간식',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',46),(110,2,'치킨박스(중) + 케이준감자 ','일본식 닭튀김으로 부드러운 순 닭다리살을 간장에 재웠다가 한솥이 개발한 튀김옷을 얇게 입혀 튀겨내 감칠 맛과 식감이 좋습니다.',NULL,4,'간식',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',47),(111,2,'치킨박스(소) + 케이준감자 ','일본식 닭튀김으로 부드러운 순 닭다리살을 간장에 재웠다가 한솥이 개발한 튀김옷을 얇게 입혀 튀겨내 감칠 맛과 식감이 좋습니다.',NULL,4,'간식',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',48),(112,2,'교자만두','부드러운 만두 피에 돼지고기, 양배추, 양파로 속을 꽉 채워 튀긴 육즙이 풍부하고 풍미가 일품입니다.',NULL,4,'간식',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',49),(113,2,'땅콩시나몬빠스','튀긴 고구마에 향긋한 시나몬 시럽과 고소한 땅콩을 버무린 달콤하게 즐길 수 있는 고급 디저트입니다.',NULL,4,'간식',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',50),(114,2,'케이준 후라이','매콤 짭짤한 케이준 시즈닝을 입힌 고소하고 바삭한 감자튀김입니다.',NULL,4,'간식',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',51),(115,2,'매화 (순살 고등어데리야끼)','[베스트 & 스테디셀러 SINCE 2007] 12가지 다양한 반찬으로 구성된 프리미엄 도시락입니다. 푸짐한 반찬과 함께 짭쪼름한 맛이 일품인 고등어 데리야끼 2조각이 구성되어 있어 부족하지 않고 든든하게 드실 수 있는 도시락 입니다. 생수와 조미 김이 함께 제공 됩니다.',NULL,3,'프리미엄',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',35),(116,2,'개나리 (순살 고등어조림)','[베스트&스테디셀러 SINCE 2008] 생선, 소불고기, 새우튀김, 치킨 등 누구나 좋아하는 메뉴로 구성했습니다. 생수와 조미 김이 함께 제공되며 취향에 따라 고등어조림(1조각)이나 고등어데리야끼(2조각) 중 선택할 수 있습니다',NULL,3,'프리미엄',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',37),(117,2,'오리지널 찹쌀탕수육','바삭하고 쫀득한 찹쌀탕수육, 새콤달콤한 오리지널 탕수육 소스를 찍먹! 부먹!으로 취향에 따라 즐겨보세요',NULL,2,'사각도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',0),(118,2,'칠리 찹쌀탕수육 도련님','돈까스와 바삭한 찹쌀탕수육으로 구성된 3번째 도련님 시리즈를 매콤달콤한 칠리소스와 함께 즐겨보세요',NULL,2,'사각도시락',1,0,0,'2019-05-07 13:21:52','2019-05-07 13:23:35',0),(119,3,'치즈피자','모짜렐라 치즈의 <br>\n진하고 고소한 맛이 <br>\n입 안 가득~<br>',NULL,1,'클래식',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',0),(120,3,'페퍼로니피자','짭짤한 페퍼로니와<br>\n풍성한 맛의 치즈가 어우러진<br>\n절묘한 풍미!',NULL,1,'클래식',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',1),(121,3,'콤비네이션피자','신선한 돼지고기와 <br>\n치즈, 페퍼로니, 버섯, <br>\n옥수수, 피망 등 <br>\n각종 야채가 가득한 토핑',NULL,1,'클래식',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',2),(122,3,'고구마피자','고소하고 달콤한 고구마와<br>\n특제 소스의 떨리는 만남!',NULL,2,'스페셜',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',3),(123,3,'포테이토피자','포근하고 담백한 통감자와<br>\n부드러운 치즈의 부드러운 조화~',NULL,1,'클래식',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',4),(124,3,'핫치킨피자','매콤 달콤 불닭이 어우러진 화끈한 맛!<br>\n느끼하지 않은 매운맛으로<br>\n한국인의 입맛을 끌어 당기는 묘한 매력~',NULL,2,'스페셜',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',5),(125,3,'불고기피자','한국 대표 음식 불고기와 피자가 만나<br>\n한국인의 미각을 사로잡는 맛!',NULL,1,'클래식',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',6),(126,3,'나폴리피자','소세지와 방울토마토<br>\n그리고 골드마요소스의 조화!',NULL,2,'스페셜',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',7),(127,3,'스테이크피자','부드러운 비프 스테이크와<br>\n향긋한 피망, 신선한 토마토가<br>\n고급 레스토랑급 피자의 맛을 살렸다!',NULL,2,'스페셜',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',9),(128,3,'까르보네피자','고소한 화이트크림과 스파게티와 치즈까지~<br>\n피자 최고의 부드러움의 진수~',NULL,2,'스페셜',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',10),(129,3,'아이리쉬포테이토피자','사워크림과 포테이토의 담백한 이중주',NULL,2,'스페셜',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',11),(130,3,'직화파인애플피자','불향이 가득한 직화불고기와<br>\n달콤한 파인애플이 어우러진 피자!',NULL,2,'스페셜',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',12),(131,3,'깐쇼새우피자','새콤달콤한 새우의 맛이 일품인 <br>\n깐쇼새우피자',NULL,2,'스페셜',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',13),(132,3,'닭안심살피자','담백한 닭안심살과 <br>\n달콤한 칠리소스의 조화가 일품인 피자!',NULL,2,'스페셜',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',14),(133,3,'도이치바이트피자','치즈롤을 한 조각씩 <br>\n갈릭디핑소스에 찍어 먹는 맛~ <br>\n독일식 소시지와 달콤한 고구마무스가<br>\n어우러진 피자와 함께 <br>\n기호따라 식성 따라 즐거움이 2배~!!',NULL,2,'스페셜',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',15),(134,3,'멕시칸바이트피자','치즈롤을 한 조각씩<br>\n갈릭디핑소스에 찍어 먹는 맛~ <br>\n핫바베큐 치킨과 달콤한 고구마 무스가<br>\n어우러진 피자와 함께 <br>\n기호따라 식성 따라 즐거움이 2배~!!',NULL,2,'스페셜',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',16),(135,3,'직화홀릭바이트피자','직화로 구워 풍미가 가득한 불고기와 <br>\n홀그레인 머스터드 소스의 환상적인 만남',NULL,2,'스페셜',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',17),(136,3,'야채퀘사디아피자','퀘사디아 속에 풍부한 야채의 식감 ! 깔끔한 맛!',NULL,2,'스페셜',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',18),(137,3,'비프퀘사디아피자','타코불고기와 체다치즈소스의 고소한 만남!',NULL,2,'스페셜',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',19),(138,3,'치킨퀘사디아피자','쫄깃한 또띠아와 매콤한 치킨의 어우러짐!',NULL,2,'스페셜',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',20),(139,3,'떡갈비피자','달콤하고 고소한 떡갈비! 이젠 피자와 함께~',NULL,2,'스페셜',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',21),(140,3,'고르곤졸라피자','톡 쏘는 향의 <br>\n고르곤졸라 치즈가 가득 토핑된 피자. <br>\n달콤한 허니시럽과 함께 즐길 수 있는 제품.',NULL,2,'스페셜',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',8),(141,3,'치킨스틱&윙',NULL,NULL,3,'사이드',1,1,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',22),(142,3,'새우링&웨지감자',NULL,NULL,3,'사이드',1,1,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',23),(143,3,'치즈오븐스파게티',NULL,NULL,3,'사이드',1,1,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',24),(144,3,'갈릭포테이토',NULL,NULL,3,'사이드',1,1,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',25),(145,3,'핫소스',NULL,NULL,3,'사이드',1,1,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',26),(146,3,'파마산치즈',NULL,NULL,3,'사이드',1,1,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',27),(147,3,'치즈시즈닝',NULL,NULL,3,'사이드',1,1,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',28),(148,3,'케첩',NULL,NULL,3,'사이드',1,1,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',29),(149,3,'우리텃밭 피클',NULL,NULL,3,'사이드',1,1,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',30),(150,3,'갈릭디핑소스',NULL,NULL,3,'사이드',1,1,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',31),(151,3,'허니시럽',NULL,NULL,3,'사이드',1,1,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',32),(152,3,'스위트칠리소스',NULL,NULL,3,'사이드',1,1,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',33),(153,3,'콘크림무스(토핑)',NULL,NULL,4,'토핑',1,2,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',34),(154,3,'고구마무스(토핑)',NULL,NULL,4,'토핑',1,2,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',35),(155,3,'파인애플(토핑)',NULL,NULL,4,'토핑',1,2,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',36),(156,3,'치즈(토핑)',NULL,NULL,4,'토핑',1,2,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',37),(157,3,'치즈크러스트(토핑)',NULL,NULL,4,'토핑',1,2,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',38),(158,3,'치즈바이트(토핑)',NULL,NULL,4,'토핑',1,2,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',39),(159,3,'콜라 (500ml)',NULL,NULL,5,'음료',1,3,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',40),(160,3,'콜라 (1.25L)',NULL,NULL,5,'음료',1,3,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',41),(161,3,'사이다 (500ml)',NULL,NULL,5,'음료',1,3,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',42),(162,3,'사이다 (1.25L)',NULL,NULL,5,'음료',1,3,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',43),(163,3,'더블갈릭 바베큐피자',NULL,'',2,'스페셜',1,0,0,'2019-05-07 13:23:45','2019-05-07 13:26:17',0),(164,4,'달콤한 강정 (중)','데리야끼풍 소스로 달콤하게, <br>\n남녀노소 모두가 함께 즐기는',NULL,1,'메인',1,0,0,'2019-05-07 13:39:47','2019-05-07 13:40:23',0),(165,4,'달콤한 강정 (대)','데리야끼풍 소스로 달콤하게, <br>\n남녀노소 모두가 함께 즐기는',NULL,1,'메인',1,0,0,'2019-05-07 13:39:47','2019-05-07 13:40:23',1),(166,4,'매콤한 강정 (중)','청양고추와 함께 매콤한 소스로 버무린 옛날 전통의 맛',NULL,1,'메인',1,0,0,'2019-05-07 13:39:47','2019-05-07 13:40:23',2),(167,4,'매콤한 강정 (대)','청양고추와 함께 매콤한 소스로 버무린 옛날 전통의 맛',NULL,1,'메인',1,0,0,'2019-05-07 13:39:47','2019-05-07 13:40:23',3),(168,4,'땡초불강정 (중)','청양고추가 들어간 간장소스로 버무려 <br>\n얼얼한 매운 맛이 입 안 가득',NULL,1,'메인',1,0,0,'2019-05-07 13:39:47','2019-05-07 13:40:23',4),(169,4,'땡초불강정 (대)','청양고추가 들어간 간장소스로 버무려 <br>\n얼얼한 매운 맛이 입 안 가득',NULL,1,'메인',1,0,0,'2019-05-07 13:39:47','2019-05-07 13:40:23',5),(170,4,'반반 강정 (매콤,달콤)','매콤한 강정과 달콤한강정 <br>\n두가지를 동시에 즐길 수 있는 메뉴',NULL,1,'메인',1,0,0,'2019-05-07 13:39:47','2019-05-07 13:40:23',6),(171,4,'각무',NULL,NULL,2,'사이드',1,1,0,'2019-05-07 13:39:47','2019-05-07 13:40:23',7),(172,4,'떡',NULL,NULL,2,'사이드',1,1,0,'2019-05-07 13:39:47','2019-05-07 13:40:23',8),(173,4,'소스',NULL,NULL,2,'사이드',1,1,0,'2019-05-07 13:39:47','2019-05-07 13:40:23',9),(174,4,'콜라 (500ml)',NULL,NULL,3,'음료',1,3,0,'2019-05-07 13:39:47','2019-05-07 13:40:23',10),(175,4,'콜라 (1.25L)',NULL,NULL,3,'음료',1,3,0,'2019-05-07 13:39:47','2019-05-07 13:40:23',11),(176,4,'사이다 (500ml)',NULL,NULL,3,'음료',1,3,0,'2019-05-07 13:39:47','2019-05-07 13:40:23',12),(177,4,'사이다 (1.25L)',NULL,NULL,3,'음료',1,3,0,'2019-05-07 13:39:47','2019-05-07 13:40:23',13),(178,5,'김치찌개(1인분, 공기밥 포함)','<b>공기밥 1개 포함</b>\r <br><br>\r 자체 제조한 김치와 양념,<br>\r 국내산 생고기의 환상적인 조합',NULL,1,'메인',1,0,0,'2019-05-07 13:42:45','2019-05-07 13:42:57',0),(179,5,'부대찌개(2인분, 공기밥 포함)','<b>공기밥 2개 포함</b>\r <br><br>\r 강순자김치와 특제소스<br>\r 그리고 프리미엄 햄의 만남',NULL,1,'메인',1,0,0,'2019-05-07 13:42:45','2019-05-07 13:42:57',1),(180,5,'김치돼지볶음(2인분, 공기밥 별도)','<b>공기밥 별도</b>\n<br><br>\n도톰한 돼지고기와 강순자김치를<br>\n달달 볶아낸 환상적인 밥도둑',NULL,1,'메인',1,0,0,'2019-05-07 13:42:45','2019-05-07 13:42:57',2),(181,6,'로스카츠','담백한 육즙가득 등심',NULL,1,'돈카츠',1,0,0,'2019-05-07 13:43:37','2019-05-07 13:45:21',0),(182,6,'생선카츠','촉촉하고 부드러운',NULL,1,'돈카츠',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',1),(183,6,'히레카츠','부드러운 안심',NULL,1,'돈카츠',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',2),(184,6,'치즈 돈카츠','100% 자연치즈로 만든',NULL,1,'돈카츠',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',3),(185,6,'돈카츠 명란 정식','mini돈카츠 + 치즈돈카츠<br>\n+ 새우튀김 + mini명란밥 + mini우동',NULL,1,'돈카츠',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',4),(186,6,'스페셜 카츠정식','mini돈카츠 + 생선카츠<br>\n+ 새우튀김(2개) + mini우동 + 공기밥',NULL,1,'돈카츠',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',5),(187,6,'매운 철판 돈카츠','지글지글 매콤한',NULL,1,'돈카츠',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',6),(188,6,'고구마 치즈 돈카츠','부어먹는 돈카츠 시리즈',NULL,1,'돈카츠',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',7),(189,6,'옛날식 달콤한 로스카츠','부어먹는 돈카츠 시리즈',NULL,1,'돈카츠',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',8),(190,6,'냉모밀','70년 장인의 쯔유로 피워내는 얼음꽃',NULL,3,'소바,우동',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',9),(191,6,'판모밀','오리지널 쯔유의 진한 맛',NULL,3,'소바,우동',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',10),(192,6,'돈카츠 모밀','뜻밖의 환상조합',NULL,3,'소바,우동',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',11),(193,6,'레드 냉모밀','새빨간 얼음꽃으로 피어나',NULL,3,'소바,우동',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',12),(194,6,'레드카츠 비빔모밀','얼얼하지만 쿨한 척',NULL,3,'소바,우동',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',13),(195,6,'카츠모밀 알밥정식','알밥 + mini돈카츠 + mini냉모밀',NULL,3,'소바,우동',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',14),(196,6,'로스카츠 모밀','로스카츠 + mini냉모밀',NULL,3,'소바,우동',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',15),(197,6,'돈카츠 김치나베','얼큰하게 든든한 실속 메뉴',NULL,2,'나베,덮밥',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',22),(198,6,'우삼겹 나베','칼칼하게 진한 실속 메뉴',NULL,2,'나베,덮밥',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',23),(199,6,'크랩알밥','입안에서 톡톡 터지는',NULL,2,'나베,덮밥',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',24),(200,6,'레드핫 차슈덮밥','매콤한 돼지고기볶음 덮밥',NULL,2,'나베,덮밥',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',25),(201,6,'레드핫 낙지덮밥','매콤한 감칠맛의 덮밥',NULL,2,'나베,덮밥',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',26),(202,6,'치킨데리야끼 벤토','입에 착 감기는 감칠맛',NULL,2,'나베,덮밥',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',27),(203,6,'부타야끼 벤토','갓 볶은 돼지고기를<br>듬뿍 올린 도시락',NULL,2,'나베,덮밥',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',28),(204,6,'알밥정식','실속있고 알찬 구성<br>알밥 + mini우동 + mini돈카츠',NULL,2,'나베,덮밥',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',29),(205,6,'부대어묵 나베','부대찌개와의 바람직한 콜라보<br>우동면 1/2이 사리로 들어있어요!',NULL,2,'나베,덮밥',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',30),(206,6,'알탕','칼칼하게 끓여낸 알탕',NULL,2,'나베,덮밥',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',31),(207,6,'돈카츠 마요','가장 으뜸가는 마요 덮밥',NULL,4,'보울',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',32),(208,6,'치킨 마요','마요 덮밥의 진수',NULL,4,'보울',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',33),(209,6,'돈카츠동','일본식 돈카츠 덮밥',NULL,4,'보울',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',34),(210,6,'치킨가라아게동','바삭쫄깃한 치킨 덮밥',NULL,4,'보울',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',35),(211,6,'부타야끼동','갓 볶은 돼지고기를 듬뿍 올린',NULL,4,'보울',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',36),(212,6,'돈카츠카레','진한 일본식 카레와 돈카츠',NULL,4,'보울',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',37),(213,6,'회덮밥','매콤새콤 아삭아삭',NULL,4,'보울',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',38),(214,6,'치킨가라아게 카레','진한 일본식 카레와 닭고기 튀김',NULL,4,'보울',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',39),(215,6,'점보믹스동','일본식 돈카츠 + 치킨 덮밥',NULL,4,'보울',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',40),(216,6,'사누끼우동','70년 장인의 쯔유와 최상급 사누끼면',NULL,3,'소바,우동',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',16),(217,6,'사누끼우동 돈카츠정식','진리의 미소야 우동정식',NULL,3,'소바,우동',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',17),(218,6,'돈카츠 야끼우동','살짝 매콤해서 좋은',NULL,3,'소바,우동',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',18),(219,6,'돈카츠 카레우동','부드럽고 매혹적인',NULL,3,'소바,우동',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',19),(220,6,'돈카츠 김치우동전골','얼큰하고 시원한 감동의 맛',NULL,3,'소바,우동',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',20),(221,6,'우삼겹 우동전골','묵직하게 깊고 얼큰한 고기국물이 일품인',NULL,3,'소바,우동',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',21),(222,6,'치즈감자고로케',NULL,NULL,5,'사이드',1,1,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',42),(223,6,'치킨가라아게',NULL,NULL,5,'사이드',1,1,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',43),(224,6,'새우튀김',NULL,NULL,5,'사이드',1,1,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',44),(225,6,'오징어링',NULL,NULL,5,'사이드',1,1,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',45),(226,6,'타코야끼',NULL,NULL,5,'사이드',1,1,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',46),(227,6,'mini우동',NULL,NULL,5,'사이드',1,1,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',47),(228,6,'mini명란밥',NULL,NULL,5,'사이드',1,1,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',48),(229,6,'우삼겹 덮밥','매콤하고 든든한',NULL,2,'나베,덮밥',1,0,0,'2019-05-07 13:43:38','2019-05-07 13:45:21',41),(230,7,'(대표) 허니멜로','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',1),(231,7,'(추천) 갈비천왕','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',2),(232,7,'(추천) 볼케이노','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',3),(233,7,'(대표) 고추 바사삭','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',4),(234,7,'(대표) 오리지널 치킨','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',5),(235,7,'(대표) 양념치킨','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',6),(236,7,'허니멜로 순살','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',7),(237,7,'허니멜로 통다리','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',8),(238,7,'허니멜로 윙','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',9),(239,7,'갈비천왕 순살','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',10),(240,7,'갈비천왕 통다리','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',11),(241,7,'갈비천왕 윙','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',12),(242,7,'핫 갈비천왕','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',13),(243,7,'핫 갈비천왕 순살','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',14),(244,7,'핫 갈비천왕 윙','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',15),(245,7,'핫 갈비천왕 통다리','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',16),(246,7,'스윗 볼케이노','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',17),(247,7,'스윗 볼케이노 순살','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',18),(248,7,'스윗 볼케이노 통다리','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',19),(249,7,'스윗 볼케이노 윙','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',20),(250,7,'볼케이노 순살','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',21),(251,7,'오리지널 순살','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',22),(252,7,'오리지널 통다리','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',23),(253,7,'오리지널 윙','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',24),(254,7,'볼케이노 모짜렐라','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',25),(255,7,'볼케이노 순살 모짜렐라','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',26),(256,7,'볼케이노 윙 모짜렐라','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',27),(257,7,'볼케이노 + 쌀떡볶이','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',28),(258,7,'볼케이노 순살 + 쌀떡볶이','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',29),(259,7,'볼케이노 통다리 + 쌀떡볶이','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',30),(260,7,'볼케이노 윙 + 쌀떡볶이','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',31),(261,7,'딥 치즈','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',32),(262,7,'딥 치즈 순살','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',33),(263,7,'딥 치즈 윙','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',34),(264,7,'양념치킨 순살','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',35),(265,7,'반반 A','오리지널 반 + 볼케이노 반<br>\n<br>\n굽네 오리지널과 굽네 볼케이노를<br>\n하프&하프로 즐기자!<br>','오리지널 반 + 볼케이노 반',2,'반반',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',36),(266,7,'반반 A 순살','오리지널 순살 반 + 볼케이노 순살 반<br>\n<br>\n굽네 오리지널과 굽네 볼케이노를<br>\n하프&하프로 즐기자!<br>','오리지널 순살 반 + 볼케이노 순살 반',2,'반반',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',37),(267,7,'반반 A 통다리','오리지널 통다리 반 + 볼케이노 통다리 반<br>\n<br>\n굽네 오리지널과 굽네 볼케이노를<br>\n하프&하프로 즐기자!<br>','오리지널 통다리 반 + 볼케이노 통다리 반',2,'반반',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',38),(268,7,'반반 A 윙','오리지널 윙 반 + 볼케이노 윙 반<br>\n<br>\n굽네 오리지널과 굽네 볼케이노를<br>\n하프&하프로 즐기자!<br>','오리지널 윙 반 + 볼케이노 윙 반',2,'반반',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',39),(269,7,'반반 B','오리지널 반 + 갈비천왕 반<br>','오리지널 반 + 갈비천왕 반',2,'반반',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',40),(270,7,'반반 B 순살','오리지널 순살 반 + 갈비천왕 순살 반<br>','오리지널 순살 반 + 갈비천왕 순살 반',2,'반반',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',41),(271,7,'반반 B 통다리','오리지널 통다리 반 + 갈비천왕 통다리 반<br>','오리지널 통다리 반 + 갈비천왕 통다리 반',2,'반반',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',42),(272,7,'반반 B 윙','오리지널 윙 반 + 갈비천왕 윙 반<br>','오리지널 윙 반 + 갈비천왕 윙 반',2,'반반',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',43),(273,7,'반반 D','오리지널 반 + 허니멜로 반<br>','오리지널 반 + 허니멜로 반',2,'반반',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',44),(274,7,'반반 D 순살','오리지널 순살 반 + 허니멜로 순살 반','오리지널 순살 반 + 허니멜로 순살 반',2,'반반',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',45),(275,7,'반반 D 통다리','오리지널 통다리 반 + 허니멜로 통다리 반','오리지널 통다리 반 + 허니멜로 통다리 반',2,'반반',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',46),(276,7,'반반 D 윙','오리지널 윙 반 + 허니멜로 윙 반','오리지널 윙 반 + 허니멜로 윙 반',2,'반반',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',47),(277,7,'오리지널 통다리(반) + 윙(반)','굽네 오리지널 통다리(반) + 굽네 오리지널  윙(반)',NULL,2,'반반',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',48),(278,7,'폭립','입안에서 사르르 녹는 부드러운 육질!<br>\n바비큐 소스의 풍미가 더해진<br>\n매력적인 맛에 포오옥~~',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',49),(279,7,'불닭발','<br>',NULL,1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',50),(280,7,'두마리 세트','굽네 오리지널 2마리 + 치킨무 + 콜라500ml + 소스 2개<br>\n<br>\n푸짐함은 두 배로~ 실속은 제대로~ 함께 해서 더 욱 맛있는!\n','오리지널2+치킨무+콜라500ml+소스2',3,'세트',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',0),(281,7,'(반마리) 허니멜로','<br>나 혼자 즐기는 허니멜로',NULL,4,'반마리',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',0),(282,7,'(반마리) 갈비천왕','<br>나 혼자 즐기는 갈비천왕',NULL,4,'반마리',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',0),(283,7,'(반마리) 볼케이노','<br>나 혼자 즐기는 볼케이노',NULL,4,'반마리',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',0),(284,7,'(반마리) 오리지널 치킨','<br>나 혼자 즐기는 오리지널 치킨',NULL,4,'반마리',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',0),(285,7,'(반마리) 양념치킨','<br>나 혼자 즐기는 양념치킨',NULL,4,'반마리',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',0),(286,7,'(반마리) 핫 갈비천왕','<br>나 혼자 즐기는 핫 갈비천왕',NULL,4,'반마리',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',0),(287,7,'(반마리) 스윗 볼케이노','<br>나 혼자 즐기는 스윗 볼케이노',NULL,4,'반마리',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',0),(288,7,'(반마리) 딥 치즈','<br>나 혼자 즐기는 딥 치즈',NULL,4,'반마리',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',0),(289,7,'그릴드비프갈비천왕','<br>','L 사이즈 + 콜라 1.25L',1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',0),(290,7,'바베큐치킨볼케이노','<br>','L 사이즈 + 콜라 1.25L',1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',0),(291,7,'스윗포테이토허니멜로','<br>','L 사이즈 + 콜라 1.25L',1,'메인',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',0),(292,7,'오리지널+피자 세트','<b>추가사항에서 피자를 선택해 주세요.</b>','추가사항에서 피자 종류를 선택해 주세요',3,'세트',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',51),(293,7,'갈비천왕+피자 세트','<b>추가사항에서 피자를 선택해 주세요.</b>','추가사항에서 피자 종류를 선택해 주세요',3,'세트',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',52),(294,7,'볼케이노+피자 세트','<b>추가사항에서 피자를 선택해 주세요.</b>','추가사항에서 피자 종류를 선택해 주세요',3,'세트',1,0,0,'2019-05-07 13:48:45','2019-05-07 13:49:40',53),(295,8,'기본 1세트','떡(1인분) + 튀김오뎅(12개) + 만두(5개) + 잡채말이(3개) + 김말이(3개) + 쿨피스\n<br><br>\n<b>요청사항에 원하시는 매운맛 정도를 적어주세요.<br>\n▷ <u>매운맛</u> / <u>중간맛(기본선택)</u> / <u>순한맛</u> ◁\n</b>\n<br><br>',NULL,2,'세트',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',5),(296,8,'기본 2세트','떡(1인분) + 튀김오뎅(6개) + 만두(5개) + 잡채말이(6개) + 샐컵밥 + 쿨피스\n<br><br>\n<b>요청사항에 원하시는 매운맛 정도를 적어주세요.<br>\n▷ <u>매운맛</u> / <u>중간맛(기본선택)</u> / <u>순한맛</u> ◁\n</b>\n<br><br>',NULL,2,'세트',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',6),(297,8,'기본 3세트','떡(1인분) + 튀김오뎅(12개) + 만두(10개) + 치킨텐더(3개) + 샐컵밥\n<br><br>\n<b>요청사항에 원하시는 매운맛 정도를 적어주세요.<br>\n▷ <u>매운맛</u> / <u>중간맛(기본선택)</u> / <u>순한맛</u> ◁\n</b>\n<br><br>',NULL,2,'세트',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',7),(298,8,'기본 4세트','떡(2인분) + 튀김오뎅(12개) + 만두(10개) + 잡채말이(3개) + 쿨피스\n<br><br>\n<b>요청사항에 원하시는 매운맛 정도를 적어주세요.<br>\n▷ <u>매운맛</u> / <u>중간맛(기본선택)</u> / <u>순한맛</u> ◁\n</b>\n<br><br>',NULL,2,'세트',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',8),(299,8,'신전 세트','떡(2인분) + 튀김오뎅(18개) + 만두(15개) + 잡채말이(9개) + 쿨피스\n<br><br>\n<b>요청사항에 원하시는 매운맛 정도를 적어주세요.<br>\n▷ <u>매운맛</u> / <u>중간맛(기본선택)</u> / <u>순한맛</u> ◁\n</b>\n<br><br>',NULL,2,'세트',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',9),(300,8,'스페셜 세트','떡(2인분) + 튀김오뎅(12개) + 만두(10개) + 김말이(6개) + 잡채말이(3개) <br>신전김밥 + 샐컵밥 + 쿨피스\n<br><br>\n<b>요청사항에 원하시는 매운맛 정도를 적어주세요.<br>\n▷ <u>매운맛</u> / <u>중간맛(기본선택)</u> / <u>순한맛</u> ◁\n</b>\n<br><br>',NULL,2,'세트',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',10),(301,8,'떡볶이','<b>요청사항에 원하시는 매운맛 정도를 적어주세요.<br>\n▷ <u>매운맛</u> / <u>중간맛(기본선택)</u> / <u>순한맛</u> ◁','▷ <u>매운맛</u> / <u>중간맛(기본선택)</u> / <u>순한맛</u> ◁',1,'기본',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',11),(302,8,'치즈떡볶이','<b>요청사항에 원하시는 매운맛 정도를 적어주세요.<br>\n▷ <u>매운맛</u> / <u>중간맛(기본선택)</u> / <u>순한맛</u> ◁','▷ <u>매운맛</u> / <u>중간맛(기본선택)</u> / <u>순한맛</u> ◁',1,'기본',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',12),(303,8,'얼큰오뎅탕','<br>',NULL,1,'기본',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',13),(304,8,'튀김오뎅','<br>','6개',1,'기본',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',14),(305,8,'만두','<br>','5개',1,'기본',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',15),(306,8,'잡채말이','<br>','3개',1,'기본',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',16),(307,8,'김말이','<br>','3개',1,'기본',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',17),(308,8,'고추튀김','<br>','3개',1,'기본',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',18),(309,8,'오징어튀김','<br>','3개',1,'기본',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',19),(310,8,'치즈스틱','<br>','3개',1,'기본',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',20),(311,8,'치킨링','<br>','10개',1,'기본',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',21),(312,8,'치킨텐더','<br>','3개',1,'기본',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',22),(313,8,'참치샐러드컵밥','<br>',NULL,1,'기본',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',23),(314,8,'참치김치컵밥','<br>',NULL,1,'기본',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',24),(315,8,'참치마요컵밥','<br>',NULL,1,'기본',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',25),(316,8,'스팸마요컵밥','<br>',NULL,1,'기본',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',26),(317,8,'신전김밥','<br>',NULL,1,'기본',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',27),(318,8,'신전치즈김밥','<br>',NULL,1,'기본',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',28),(319,8,'버터갈릭 감자튀김','<br>',NULL,1,'기본',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',29),(320,8,'항공대 1세트','<br>(스팸, 참치) 마요컵밥 미 선택 시,<br>\r 참치마요컵밥이 기본으로 제공됩니다.','떡볶이 + (스팸 / 참치) 마요컵밥',2,'세트',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',1),(321,8,'항공대 2세트','<br>(스팸, 참치) 마요컵밥 미 선택 시,<br>\r 참치마요컵밥이 기본으로 제공됩니다.','떡볶이 + 튀김오뎅 + (스팸 / 참치) 마요컵밥',2,'세트',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',2),(322,8,'항공대 3세트','<br>','치즈떡볶이 + 튀김오뎅 + 참치샐러드컵밥',2,'세트',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',3),(323,8,'항공대 4세트','<br>(스팸, 참치) 마요컵밥 미 선택 시,<br>\r 참치마요컵밥이 기본으로 제공됩니다.','떡볶이 + 김말이 +  (스팸 / 참치) 마요컵밥',2,'세트',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',4),(324,8,'치즈볼','<br>','4개',1,'기본',1,0,0,'2019-05-07 13:53:22','2019-05-07 13:54:08',30),(325,9,'앗!메리카노','※ <b>따듯한 음료</b>입니다.<br>\n추가사항에서 [아이스 변경 추가] 선택 시 <br>\n차가운 음료로 변경 가능합니다. <br>',NULL,1,'커피',1,0,0,'2019-05-07 13:56:28','2019-05-07 13:57:36',1),(326,9,'더블 에스프레소','※ <b>따듯한 음료</b>입니다.<br>',NULL,1,'커피',1,0,0,'2019-05-07 13:56:28','2019-05-07 13:57:36',2),(327,9,'원조커피','※ <b>따듯한 음료</b>입니다.<br>\n추가사항에서 [아이스 변경 추가] 선택 시 <br>\n차가운 음료로 변경 가능합니다. <br>',NULL,1,'커피',1,0,0,'2019-05-07 13:56:28','2019-05-07 13:57:36',3),(328,9,'빽\'s 라떼','※ <b>따듯한 음료</b>입니다.<br>\n추가사항에서 [아이스 변경 추가] 선택 시 <br>\n차가운 음료로 변경 가능합니다. <br>',NULL,1,'커피',1,0,0,'2019-05-07 13:56:28','2019-05-07 13:57:36',4),(329,9,'바닐라라떼','※ <b>따듯한 음료</b>입니다.<br>\n추가사항에서 [아이스 변경 추가] 선택 시 <br>\n차가운 음료로 변경 가능합니다. <br>',NULL,1,'커피',1,0,0,'2019-05-07 13:56:28','2019-05-07 13:57:36',5),(330,9,'카페모카','※ <b>따듯한 음료</b>입니다.<br>\n추가사항에서 [아이스 변경 추가] 선택 시 <br>\n차가운 음료로 변경 가능합니다. <br>',NULL,1,'커피',1,0,0,'2019-05-07 13:56:28','2019-05-07 13:57:36',6),(331,9,'카라멜마키아또','※ <b>따듯한 음료</b>입니다.<br>\n추가사항에서 [아이스 변경 추가] 선택 시 <br>\n차가운 음료로 변경 가능합니다. <br>',NULL,1,'커피',1,0,0,'2019-05-07 13:56:28','2019-05-07 13:57:36',7),(332,9,'빽엔나','※ <b>따듯한 음료</b>입니다.<br>\n추가사항에서 [아이스 변경 추가] 선택 시 <br>\n차가운 음료로 변경 가능합니다. <br>',NULL,1,'커피',1,0,0,'2019-05-07 13:56:28','2019-05-07 13:57:36',8),(333,9,'달달연유라떼','※ <b>따듯한 음료</b>입니다.<br>\n추가사항에서 [아이스 변경 추가] 선택 시 <br>\n차가운 음료로 변경 가능합니다. <br>',NULL,1,'커피',1,0,0,'2019-05-07 13:56:28','2019-05-07 13:57:36',9),(334,9,'코코넛라떼','※ <b>따듯한 음료</b>입니다.<br>',NULL,1,'커피',1,0,0,'2019-05-07 13:56:28','2019-05-07 13:57:36',10),(335,9,'코코넛커피스무디','※ <b>차가운 음료</b>입니다.<br>',NULL,1,'커피',1,0,0,'2019-05-07 13:56:28','2019-05-07 13:57:36',11),(336,9,'완전초코','※ <b>따듯한 음료</b>입니다.<br>\n추가사항에서 [아이스 변경 추가] 선택 시 <br>\n차가운 음료로 변경 가능합니다. <br>',NULL,2,'음료',1,0,0,'2019-05-07 13:56:28','2019-05-07 13:57:36',12),(337,9,'청포도플라워','※ <b>차가운 음료</b>입니다.<br>\n',NULL,2,'음료',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',13),(338,9,'녹차라떼','※ <b>따듯한 음료</b>입니다.<br>\n추가사항에서 [아이스 변경 추가] 선택 시 <br>\n차가운 음료로 변경 가능합니다. <br>',NULL,2,'음료',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',14),(339,9,'밀크티','※ <b>따듯한 음료</b>입니다.<br>\n추가사항에서 [아이스 변경 추가] 선택 시 <br>\n차가운 음료로 변경 가능합니다. <br>',NULL,2,'음료',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',15),(340,9,'아이스미초(석류)','※ <b>차가운 음료</b>입니다.<br>',NULL,2,'음료',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',16),(341,9,'달콤아이스티','※ <b>차가운 음료</b>입니다.<br>',NULL,2,'음료',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',17),(342,9,'자몽티','※ <b>따듯한 음료</b>입니다.<br>\n추가사항에서 [아이스 변경 추가] 선택 시 <br>\n차가운 음료로 변경 가능합니다. <br>',NULL,2,'음료',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',18),(343,9,'깔라만시티','※ <b>따듯한 음료</b>입니다.<br>',NULL,2,'음료',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',19),(344,9,'깔라만시에이드','※ <b>차가운 음료</b>입니다.<br>',NULL,2,'음료',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',20),(345,9,'고구마라떼','※ <b>따듯한 음료</b>입니다.<br>\n추가사항에서 [아이스 변경 추가] 선택 시 <br>\n차가운 음료로 변경 가능합니다. <br>',NULL,2,'음료',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',21),(346,9,'고구마스무디','※ <b>차가운 음료</b>입니다.<br>',NULL,2,'음료',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',22),(347,9,'노말한소프트','<br>',NULL,4,'디저트',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',23),(348,9,'옥수크림','<br>',NULL,4,'디저트',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',24),(349,9,'호두크런치','<br>',NULL,4,'디저트',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',25),(350,9,'사라다빵','<br>',NULL,4,'디저트',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',26),(351,9,'소세지빵','<br>',NULL,4,'디저트',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',27),(352,9,'크리미슈','<br>',NULL,4,'디저트',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',28),(353,9,'크리미단팥빵','<br>',NULL,4,'디저트',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',29),(354,9,'피스타치오','<br>',NULL,3,'빽스치노',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',30),(355,9,'녹차빽스치노','<br>',NULL,3,'빽스치노',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',31),(356,9,'원조빽스치노','<br>',NULL,3,'빽스치노',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',32),(357,9,'완전딸기바나나','<br>',NULL,3,'빽스치노',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',33),(358,9,'완전초코바나나','<br>',NULL,3,'빽스치노',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',34),(359,9,'앗!메리카노 X 2개','앗!메리카노 2개<br><b>고객 배달요금 200원 할인</b>\n<br><br>\n※ <b>따듯한 음료</b>입니다.<br>\n추가사항에서 [아이스 변경 추가] 선택 시 <br>\n차가운 음료로 변경 가능합니다. <br>','<b>고객 배달요금 200원 할인</b>',1,'커피',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',0),(360,9,'앗!메리카노 X 3개','앗!메리카노 3개<br><b>고객 배달요금 1100원 할인</b>\n<br><br>\n※ <b>따듯한 음료</b>입니다.<br>\n추가사항에서 [아이스 변경 추가] 선택 시 <br>\n차가운 음료로 변경 가능합니다. <br>','<b>고객 배달요금 1100원 할인</b>',1,'커피',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',0),(361,9,'앗!메리카노 X 4개','앗!메리카노 4개<br><b>고객 배달요금 2000원 할인</b>\n<br><br>\n※ <b>따듯한 음료</b>입니다.<br>\n추가사항에서 [아이스 변경 추가] 선택 시 <br>\n차가운 음료로 변경 가능합니다. <br>','<b>고객 배달요금 2000원 할인</b>',1,'커피',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',0),(362,9,'앗!메리카노 X 5개','앗!메리카노 5개<br><b>고객 배달요금 3300원 할인</b>\n<br><br>\n※ <b>따듯한 음료</b>입니다.<br>\n추가사항에서 [아이스 변경 추가] 선택 시 <br>\n차가운 음료로 변경 가능합니다. <br>','<b>고객 배달요금 3300원 할인</b>',1,'커피',1,0,0,'2019-05-07 13:56:29','2019-05-07 13:57:36',0);
/*!40000 ALTER TABLE `product_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion_for_store_tbl`
--

DROP TABLE IF EXISTS `promotion_for_store_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotion_for_store_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `promotion_idx` int(11) DEFAULT NULL,
  `store_idx` int(11) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_prom_link_idx` (`promotion_idx`),
  KEY `fk_store_link_idx` (`store_idx`),
  CONSTRAINT `fk_prom_link` FOREIGN KEY (`promotion_idx`) REFERENCES `promotion_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_store_link` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='프로모션 - 업체 연결 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion_for_store_tbl`
--

LOCK TABLES `promotion_for_store_tbl` WRITE;
/*!40000 ALTER TABLE `promotion_for_store_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `promotion_for_store_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion_tbl`
--

DROP TABLE IF EXISTS `promotion_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotion_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `discount_prc` int(11) DEFAULT '0',
  `discount_pct` smallint(6) DEFAULT '0',
  `begin_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '프로모션 시작 날짜\n(기간 0 입력 시 무제한으로 설정)',
  `end_date` timestamp NULL DEFAULT NULL COMMENT '프로모션 종료 날짜\n(기간 0 입력 시 무제한으로 설정)',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `state_active` tinyint(4) NOT NULL DEFAULT '1' COMMENT '제품 상태\n0 - 비활성화\n1 - 활성화',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='product에 적용되는 가격 할인 프로모션 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion_tbl`
--

LOCK TABLES `promotion_tbl` WRITE;
/*!40000 ALTER TABLE `promotion_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `promotion_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `region_category_tbl`
--

DROP TABLE IF EXISTS `region_category_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `region_category_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='권역 별 카테고리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `region_category_tbl`
--

LOCK TABLES `region_category_tbl` WRITE;
/*!40000 ALTER TABLE `region_category_tbl` DISABLE KEYS */;
INSERT INTO `region_category_tbl` VALUES (1,'서울'),(2,'경기'),(3,'인천'),(4,'강원'),(5,'충북'),(6,'충남'),(7,'대전'),(8,'세종'),(9,'전북'),(10,'전남'),(11,'광주'),(12,'경북'),(13,'경남'),(14,'대구'),(15,'울산'),(16,'부산'),(17,'제주');
/*!40000 ALTER TABLE `region_category_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply_for_comment_all_tbl`
--

DROP TABLE IF EXISTS `reply_for_comment_all_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reply_for_comment_all_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `comment_all_idx` int(11) DEFAULT NULL,
  `customer_idx` int(11) DEFAULT NULL,
  `owner_idx` int(11) DEFAULT NULL COMMENT '업주 인덱스',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `contents` text,
  `state_active` tinyint(4) NOT NULL DEFAULT '1' COMMENT '후기 댓글 상태\n0 - 비활성화 (신고 등으로 인한 비활성화)\n1 - 활성화 (기본)',
  `state_anonymous` tinyint(4) NOT NULL DEFAULT '0' COMMENT '익명 여부\n0 - 실명\n1 - 익명',
  PRIMARY KEY (`idx`),
  KEY `fk_reply_comment_all_idx` (`comment_all_idx`),
  KEY `fk_reply_customer_all_idx` (`customer_idx`),
  KEY `fk_reply_owner_all_idx` (`owner_idx`),
  CONSTRAINT `fk_reply_comment_all` FOREIGN KEY (`comment_all_idx`) REFERENCES `comment_for_all_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_reply_customer_all` FOREIGN KEY (`customer_idx`) REFERENCES `user_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_reply_owner_all` FOREIGN KEY (`owner_idx`) REFERENCES `owner_of_store_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='고객의소리 글의 댓글 관리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply_for_comment_all_tbl`
--

LOCK TABLES `reply_for_comment_all_tbl` WRITE;
/*!40000 ALTER TABLE `reply_for_comment_all_tbl` DISABLE KEYS */;
INSERT INTO `reply_for_comment_all_tbl` VALUES (1,1,1,NULL,'2019-05-10 04:09:19','2019-05-10 04:57:27','댓글입니다.',1,1);
/*!40000 ALTER TABLE `reply_for_comment_all_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reply_for_comment_store_tbl`
--

DROP TABLE IF EXISTS `reply_for_comment_store_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reply_for_comment_store_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `comment_store_idx` int(11) DEFAULT NULL,
  `customer_idx` int(11) DEFAULT NULL,
  `owner_idx` int(11) DEFAULT NULL COMMENT '업주 인덱스',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `contents` text,
  `state_active` tinyint(4) NOT NULL DEFAULT '1' COMMENT '후기 댓글 상태\n0 - 비활성화 (신고 등으로 인한 비활성화)\n1 - 활성화 (기본)',
  `state_anonymous` tinyint(4) NOT NULL DEFAULT '0' COMMENT '익명 여부\n0 - 실명\n1 - 익명',
  PRIMARY KEY (`idx`),
  KEY `fk_reply_comment_store_idx` (`comment_store_idx`),
  KEY `fk_reply_customer_store_idx` (`customer_idx`),
  KEY `fk_reply_owner_store_idx` (`owner_idx`),
  CONSTRAINT `fk_reply_comment_store` FOREIGN KEY (`comment_store_idx`) REFERENCES `comment_for_store_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_reply_customer_store` FOREIGN KEY (`customer_idx`) REFERENCES `user_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_reply_owner_store` FOREIGN KEY (`owner_idx`) REFERENCES `owner_of_store_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='5마디 후기에 달린 댓글 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply_for_comment_store_tbl`
--

LOCK TABLES `reply_for_comment_store_tbl` WRITE;
/*!40000 ALTER TABLE `reply_for_comment_store_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `reply_for_comment_store_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule_for_store_tbl`
--

DROP TABLE IF EXISTS `schedule_for_store_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule_for_store_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `store_idx` int(11) DEFAULT NULL,
  `open_time` time DEFAULT NULL,
  `close_time` time DEFAULT NULL,
  `state_active` tinyint(4) DEFAULT '1' COMMENT '활성화 여부 상태\n0 - 비활성화\n1 - 활성화',
  `state_pause` tinyint(4) DEFAULT '0' COMMENT '일시정지 여부 상태\n0 - 비활성화 (실행 중)\n1 - 활성화 (일시정지)',
  `pause_description` varchar(200) DEFAULT NULL COMMENT '일시정지 사유',
  `first_week_close` tinyint(4) DEFAULT NULL COMMENT '월 - 0\n화 - 1\n수 - 2\n목 - 3\n금 - 4\n토 - 5\n일 - 6',
  `second_week_close` tinyint(4) DEFAULT NULL COMMENT '월 - 0\n화 - 1\n수 - 2\n목 - 3\n금 - 4\n토 - 5\n일 - 6',
  `third_week_close` tinyint(4) DEFAULT NULL COMMENT '월 - 0\n화 - 1\n수 - 2\n목 - 3\n금 - 4\n토 - 5\n일 - 6',
  `fourth_week_close` tinyint(4) DEFAULT NULL COMMENT '월 - 0\n화 - 1\n수 - 2\n목 - 3\n금 - 4\n토 - 5\n일 - 6',
  PRIMARY KEY (`idx`),
  KEY `schedule_store_fk_idx` (`store_idx`),
  CONSTRAINT `schedule_store_fk` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='업체 스케쥴 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_for_store_tbl`
--

LOCK TABLES `schedule_for_store_tbl` WRITE;
/*!40000 ALTER TABLE `schedule_for_store_tbl` DISABLE KEYS */;
INSERT INTO `schedule_for_store_tbl` VALUES (1,1,'10:00:00','21:00:00',1,0,'-',NULL,NULL,NULL,NULL),(2,2,'10:00:00','21:00:00',1,0,'-',NULL,NULL,NULL,NULL),(3,3,'10:00:00','21:00:00',1,0,'-',NULL,NULL,NULL,NULL),(4,4,'10:00:00','21:00:00',1,0,'-',NULL,NULL,NULL,NULL),(5,5,'10:00:00','21:00:00',1,0,'-',NULL,NULL,NULL,NULL),(6,6,'10:00:00','21:00:00',1,0,'-',NULL,NULL,NULL,NULL),(7,7,'10:00:00','21:00:00',1,0,'-',NULL,NULL,NULL,NULL),(8,8,'10:00:00','21:00:00',1,0,'-',NULL,NULL,NULL,NULL),(9,9,'10:00:00','21:00:00',1,0,'-',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `schedule_for_store_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session_for_customer_tbl`
--

DROP TABLE IF EXISTS `session_for_customer_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_for_customer_tbl` (
  `idx` int(11) NOT NULL,
  `session_key` varchar(45) DEFAULT NULL,
  `session_limit` timestamp NULL DEFAULT NULL,
  `customer_idx` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `cust_id_fk_idx` (`customer_idx`),
  CONSTRAINT `session_for_customer_id_fk` FOREIGN KEY (`customer_idx`) REFERENCES `user_tbl` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='사용자 로그인 세션 관리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session_for_customer_tbl`
--

LOCK TABLES `session_for_customer_tbl` WRITE;
/*!40000 ALTER TABLE `session_for_customer_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `session_for_customer_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_info_tbl`
--

DROP TABLE IF EXISTS `store_info_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store_info_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `store_idx` int(11) DEFAULT NULL,
  `intro` text,
  `tip` text,
  `owner_name` varchar(45) DEFAULT NULL,
  `company_name` varchar(100) DEFAULT NULL,
  `company_location` varchar(256) DEFAULT NULL,
  `company_no` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_info_store_idx` (`store_idx`),
  CONSTRAINT `fk_info_store` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='업체 추가 정보 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_info_tbl`
--

LOCK TABLES `store_info_tbl` WRITE;
/*!40000 ALTER TABLE `store_info_tbl` DISABLE KEYS */;
INSERT INTO `store_info_tbl` VALUES (1,1,'<br>\r 미리 만들어 놓고 나눠주다시피 하는 판매가 아닌<br>\r \'정성어린 한 끼 식사를 준비한다\'는 마음가짐으로 주문을 받는 즉시<br>\r 제조를 시작하여 따듯하고 신선한 높은 퀄리티의 제품을 고객에게 제공하는<br>\r \'고객을 가장 먼저 생각하는 맘스터치\'입니다. <br><br>\r <b>#존맛탱 #신선 #맛집 #혜자</b>','','맘스터치','맘스터치 (주)','서울특별시 강동구 성내로 6길 11(성내동)<br>\n삼원타워 2층','546-05-00933'),(2,2,'<br>\r 한솥은 \'따끈한 도시락으로 지역사회에 공헌한다\' 라는<br>\r 기업이념 아래, 고객 이익을 최우선으로 하며 엄선된<br>\r 좋은 식재료만 사용하는 대한민국 외식종합기업 시장을<br>\r 리드하는 글로벌 종합외식기업 입니다.<br><br>\r \r <b> #따끈한 #집밥 #혼밥 #먹방 #한식 #포장 #간편식 </b>\r ','','한솥','한솥 (주)','서울특별시 강동구 성내로 6길 11(성내동)<br>\r 삼원타워 2층','546-05-00933'),(3,3,'<br>\r <b>사업규모가 커져도 결코 잊지 않는 것,<br>\r 품질만큼은 최고급으로 유지하겠다는 약속과 다짐</b><br>\r 피자스쿨은 언제 어디서나 부담없는 가격으로 즐길 수 있는<br>\r 최고의 품질과 맛을 유지하게 위해 노력할것이며<br>\r 늘 고객여러분 곁에 있는 친구같은 브랜드가 되도록 하겠습니다.<br><br>\r \r <b> #담백도우 #특제소스 #혼피자 # #1인1피자 #포장 #간편식 </b>','','피자스쿨','피자스쿨 (주)','서울특별시 강동구 성내로 6길 11(성내동)<br>\r 삼원타워 2층','546-05-00933'),(4,4,'<br><b>가마로강정은 맛과 정직을 바탕으로 합니다.</b><br>\r 늘 한결 같은 맛, 행복한 즐거움으로 가득한<br>\r 닭강정 브랜드 찾으시는 고객분들도 함께<br>\r 행복함이 가득할 수 있는 브랜드로 오래도록 사랑 받고자 합니다.<br><br>\r \r <b> #떡강정 #닭강정 #치밥 # #1인1강정 #겉바속촉 #간편식 </b>','','가마로','가마로 (주)','서울특별시 강동구 성내로 6길 11(성내동)<br>\r 삼원타워 2층','546-05-00933'),(5,5,'<br>대한민국 대표 NO.1 김치찌개 전문점','','강순자','강순자 (주)','서울특별시 강동구 성내로 6길 11(성내동)<br>\r 삼원타워 2층','546-05-00933'),(6,6,'<br>수제돈카츠, 우동, 모밀 등 맛과 웃음이 있는 공간.\r <br><br>\r <b>단무지, 깍두기 더 필요하시면 요청사항에 적어주세요.<br>\r (단, 포함되는 메뉴에만 적용됩니다.) \r </b>\r <br>','','미소야','미소야 (주)','서울특별시 강동구 성내로 6길 11(성내동)<br>\r 삼원타워 2층','546-05-00933'),(7,7,'<br><b>튀기지 않고 오븐에 구워</b> 치킨 본연의 맛을 유지하며<br>\r 기름은 쏙 빼고 그 자리에 맛과 건강을 채워 넣는 치킨의 혁신<br><br>\r <b><굽네치킨 행신2호점></b><br>\r 메뉴얼 준수! 신속한 배달! 더 나은 서비스로 고객님께 보답하겠습니다.<br>\r <br>\r <b style=\"color:red\">[항공대 특가]<br>\r  피자(L) + 콜라(1.25L) 할인 중 !</b>','','굽네','굽네 (주_','서울특별시 강동구 성내로 6길 11(성내동)<br>','546-05-00933'),(8,8,'<br>매운 떡볶이를 좋아하는<br>VIP 공간_신전떡볶이 \r <br><br>\r <b>요청사항에 원하시는 매운맛 정도를 적어주세요.<br>\r ▷ <u>매운맛</u> / <u>중간맛(기본선택)</u> / <u>순한맛</u> ◁\r <br><br><br>\r ※ 13시 부터 배달이 가능합니다.\r </b>\r <br>','','신전','신전 (주)','서울특별시 강동구 성내로 6길 11(성내동)<br>','546-05-00933'),(9,9,'<br>\r 싸다! 크다! 맛있다!<br>\r <br>\r 합리적인 가격, 놀라운 퀄리티의 <br>\r 커피전문점 <b>“빽다방”</b>입니다.\r ','','백종원','빽다방 (주)','서울특별시 강동구 성내로 6길 11(성내동)<br>','546-05-00933');
/*!40000 ALTER TABLE `store_info_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `store_tbl`
--

DROP TABLE IF EXISTS `store_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `store_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '업체 이름',
  `location` varchar(150) DEFAULT NULL COMMENT '업체 상세 위치',
  `main_phone_number` varchar(45) DEFAULT NULL,
  `description` text COMMENT '상세설명',
  `cnt_like` int(11) DEFAULT '0',
  `cnt_comment` int(11) DEFAULT '0',
  `minimum_time` time NOT NULL COMMENT '최소 생산 가능 시간',
  `parallel_production` smallint(6) NOT NULL COMMENT '평균 병렬 생산량\n(단위 분)',
  `maximum_production` int(11) NOT NULL,
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sequence` int(11) DEFAULT NULL,
  `type` tinyint(4) DEFAULT '0' COMMENT '업체 제휴 타입\n0 : 비 제휴\n1 : 제휴',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='업체 관련 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_tbl`
--

LOCK TABLES `store_tbl` WRITE;
/*!40000 ALTER TABLE `store_tbl` DISABLE KEYS */;
INSERT INTO `store_tbl` VALUES (1,'맘스터치','행신동 1079-2','031-971-8399','<br>\r 미리 만들어 놓고 나눠주다시피 하는 판매가 아닌<br>\r \'정성어린 한 끼 식사를 준비한다\'는 마음가짐으로 주문을 받는 즉시<br>\r 제조를 시작하여 따듯하고 신선한 높은 퀄리티의 제품을 고객에게 제공하는<br>\r \'고객을 가장 먼저 생각하는 맘스터치\'입니다. <br><br>\r <b>#존맛탱 #신선 #맛집 #혜자</b>',0,0,'00:03:00',2,30,'2019-02-11 14:00:42','2019-05-07 03:23:33',3,1),(2,'한솥 도시락','행신동 1083-3','031-938-9994','<br>\r 한솥은 \'따끈한 도시락으로 지역사회에 공헌한다\' 라는<br>\r 기업이념 아래, 고객 이익을 최우선으로 하며 엄선된<br>\r 좋은 식재료만 사용하는 대한민국 외식종합기업 시장을<br>\r 리드하는 글로벌 종합외식기업 입니다.<br><br>\r \r <b> #따끈한 #집밥 #혼밥 #먹방 #한식 #포장 #간편식 </b>\r ',0,0,'00:01:00',2,40,'2019-02-11 14:00:42','2019-05-07 11:34:43',8,1),(3,'피자 스쿨','행신동1080-5 미라클프라자105호','031-979-5589','<br>\r <b>사업규모가 커져도 결코 잊지 않는 것,<br>\r 품질만큼은 최고급으로 유지하겠다는 약속과 다짐</b><br>\r 피자스쿨은 언제 어디서나 부담없는 가격으로 즐길 수 있는<br>\r 최고의 품질과 맛을 유지하게 위해 노력할것이며<br>\r 늘 고객여러분 곁에 있는 친구같은 브랜드가 되도록 하겠습니다.<br><br>\r \r <b> #담백도우 #특제소스 #혼피자 # #1인1피자 #포장 #간편식 </b>',0,0,'00:05:00',2,20,'2019-02-11 14:00:42','2019-05-07 11:34:43',7,1),(4,'가마로 강정','행신동 1080-1','031-978-7292','<br><b>가마로강정은 맛과 정직을 바탕으로 합니다.</b><br>\r 늘 한결 같은 맛, 행복한 즐거움으로 가득한<br>\r 닭강정 브랜드 찾으시는 고객분들도 함께<br>\r 행복함이 가득할 수 있는 브랜드로 오래도록 사랑 받고자 합니다.<br><br>\r \r <b> #떡강정 #닭강정 #치밥 # #1인1강정 #겉바속촉 #간편식 </b>',0,0,'00:03:00',2,20,'2019-02-11 14:00:42','2019-05-07 11:34:43',5,1),(5,'강순자 김찌치개','행신동 1075','031-971-3420','<br>대한민국 대표 NO.1 김치찌개 전문점',0,0,'00:03:00',2,20,'2019-02-11 14:00:42','2019-05-07 11:34:43',9,1),(6,'미소야','행신동 1080-5 미라클프라자','031-972-4003','<br>수제돈카츠, 우동, 모밀 등 맛과 웃음이 있는 공간.\r <br><br>\r <b>단무지, 깍두기 더 필요하시면 요청사항에 적어주세요.<br>\r (단, 포함되는 메뉴에만 적용됩니다.) \r </b>\r <br>',0,0,'00:03:00',2,20,'2019-02-11 14:00:42','2019-05-07 03:24:22',2,0),(7,'굽네 치킨','행신동 195-1','0504-3143-8334','<br><b>튀기지 않고 오븐에 구워</b> 치킨 본연의 맛을 유지하며<br>\r 기름은 쏙 빼고 그 자리에 맛과 건강을 채워 넣는 치킨의 혁신<br><br>\r <b><굽네치킨 행신2호점></b><br>\r 메뉴얼 준수! 신속한 배달! 더 나은 서비스로 고객님께 보답하겠습니다.<br>\r <br>\r <b style=\"color:red\">[항공대 특가]<br>\r  피자(L) + 콜라(1.25L) 할인 중 !</b>',0,0,'00:05:00',2,20,'2019-05-07 03:17:24','2019-05-07 03:24:22',1,0),(8,'신전 떡볶이','-','031-973-1070','<br>매운 떡볶이를 좋아하는<br>VIP 공간_신전떡볶이 \r <br><br>\r <b>요청사항에 원하시는 매운맛 정도를 적어주세요.<br>\r ▷ <u>매운맛</u> / <u>중간맛(기본선택)</u> / <u>순한맛</u> ◁\r <br><br><br>\r ※ 13시 부터 배달이 가능합니다.\r </b>\r <br>',0,0,'00:03:00',2,20,'2019-05-07 03:17:24','2019-05-07 11:34:43',6,1),(9,'빽다방','-','031-979-2100','<br>\r 싸다! 크다! 맛있다!<br>\r <br>\r 합리적인 가격, 놀라운 퀄리티의 <br>\r 커피전문점 <b>“빽다방”</b>입니다.\r ',0,0,'00:03:00',2,20,'2019-05-07 03:17:24','2019-05-07 11:34:43',10,1);
/*!40000 ALTER TABLE `store_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_advertise_for_main_tbl`
--

DROP TABLE IF EXISTS `sub_advertise_for_main_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_advertise_for_main_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `imgpath` varchar(100) DEFAULT NULL COMMENT '광고 제목',
  `next_step_type` tinyint(4) NOT NULL COMMENT '클릭시, 이동할 다음 페이지의 타입\n0 - 이동 안 함\n1 - 음식점 상세 페이지\n2 - 음식 상세 페이지\n3 - 이벤트 상세 페이지\n4 - 공지사항 상세 페이지',
  `next_step_location` varchar(45) NOT NULL COMMENT '클릭시, 이동할 다음 페이지의 주소',
  `state_active` tinyint(4) NOT NULL DEFAULT '1' COMMENT '활성화 여부 상태\n0 - 비활성화\n1 - 활성화',
  `sequence` int(11) NOT NULL,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='메인화면 서브 광고 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_advertise_for_main_tbl`
--

LOCK TABLES `sub_advertise_for_main_tbl` WRITE;
/*!40000 ALTER TABLE `sub_advertise_for_main_tbl` DISABLE KEYS */;
INSERT INTO `sub_advertise_for_main_tbl` VALUES (1,'/assets/image/advertise/submain/1.png',2,'products/1',1,1),(2,'/assets/image/advertise/submain/1.png',3,'events/1',1,2),(3,'/assets/image/advertise/submain/1.png',4,'notices/1',1,3);
/*!40000 ALTER TABLE `sub_advertise_for_main_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_tbl`
--

DROP TABLE IF EXISTS `user_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `delivery_site_idx` int(11) DEFAULT NULL,
  `id` varchar(45) NOT NULL COMMENT '아이디',
  `pw` varchar(150) NOT NULL COMMENT '패스워드',
  `name` varchar(45) NOT NULL COMMENT '사용자 이름',
  `nickname` varchar(100) DEFAULT NULL COMMENT '사용자 별명',
  `gender` tinyint(4) DEFAULT NULL COMMENT '성별\n0 - 남자\n1 - 여자',
  `year_of_birth` smallint(6) DEFAULT NULL,
  `month_of_birth` tinyint(4) DEFAULT NULL,
  `days_of_birth` tinyint(4) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `state_active` tinyint(4) NOT NULL DEFAULT '1' COMMENT '활성화 여부 상태\n0 - 비활성화\n1 - 활성화',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `point` int(11) NOT NULL DEFAULT '0' COMMENT '현재 포인트\n(point_log_tbl에 기입 되어있는 포인트가 합산된 금액)',
  `authorities` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  UNIQUE KEY `cust_id_UNIQUE` (`id`),
  UNIQUE KEY `phone_number_UNIQUE` (`phone_number`),
  KEY `fk_cust_dsite_idx` (`delivery_site_idx`),
  CONSTRAINT `fk_cust_dsite` FOREIGN KEY (`delivery_site_idx`) REFERENCES `delivery_site_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='본 서비스를 사용하게 되는 사용자에 관한 테이블\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_tbl`
--

LOCK TABLES `user_tbl` WRITE;
/*!40000 ALTER TABLE `user_tbl` DISABLE KEYS */;
INSERT INTO `user_tbl` VALUES (1,1,'cholnh','$2a$10$HUXnaAJXXeUbNVwtaoKo..vUQdfNMNS5oANmxgMPJCTRu7UTu5cX6','홍길동','낙지',0,2019,12,31,'010-6478-4894',1,'2019-01-08 05:52:27','2019-05-10 03:00:43',3000,'ROLE_USER'),(2,1,'admin','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','관리자','관리자',0,2019,12,31,'010-1234-1235',1,'2019-01-08 05:52:27','2019-05-06 03:07:41',111920,'ROLE_USER'),(3,1,'test','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','테스트222','ㅇㅇ',0,2019,12,31,'010-1234-1234',1,'2019-02-11 10:18:25','2019-04-25 17:46:48',46560,'ROLE_USER'),(12,1,'test_make','$2a$10$8Jc1yCeO5FlUiYhMNvtWc.o5.eU9zgUC0y2pr2xau3bMCd1fg9Vuy','깔끔','dd',0,2018,2,3,'010-2345-2345',0,'2019-04-13 12:44:20','2019-05-06 03:07:41',0,'ROLE_USER'),(13,1,'test@naver.com','$2a$10$JNQCw22DDKpclUGonsOBduqPPSmuxvB/aVSEKIXr968qM2ge5uIQK','테스터','익명23341',0,1111,1,1,'010-2935-4191',0,'2019-04-22 16:06:49','2019-05-11 01:28:49',12950,'ROLE_USER'),(14,1,'cholnh1@naver.com','$2a$10$gyxwLlOLf7O1hS0AeCLJvucxu33EX5e9/lr6zDSvwyR0zIdMadMU.','최낙형','익명',0,1993,10,1,'010-6478-4897',1,'2019-05-06 01:53:50','2019-05-06 03:07:41',0,'ROLE_USER'),(15,1,'cholnh2@naver.com','$2a$10$vbLNcQ2ADs80d2JgSk0pQedWfad8T./LKK4dTq8sc5hWz9FWEDbJe','최낙형','익명',0,1993,10,1,'010-6478-4898',1,'2019-05-06 01:58:14','2019-05-06 03:07:41',0,'ROLE_USER'),(16,1,'d_duck@naver.com','$2a$10$k1LlgTk3jG6Pd6erjw03Tuck0a9j4xEMfZ/qZb32CykrfXtyqVd.2','이준성','고든램쥐',0,1994,2,5,'010-4726-6155',1,'2019-05-08 08:56:37','2019-05-08 08:57:32',0,'ROLE_USER');
/*!40000 ALTER TABLE `user_tbl` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-11 20:06:05
