CREATE DATABASE  IF NOT EXISTS `db_pmg` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_pmg`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: db_pmg
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='물품 추가사항 매핑 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `additional_for_product_tbl`
--

LOCK TABLES `additional_for_product_tbl` WRITE;
/*!40000 ALTER TABLE `additional_for_product_tbl` DISABLE KEYS */;
INSERT INTO `additional_for_product_tbl` VALUES (1,1,10),(2,2,10),(3,3,10),(4,4,11),(5,5,11),(6,6,11),(7,7,12),(8,8,12),(9,9,12);
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
INSERT INTO `advertise_for_main_tbl` VALUES (1,'c:/resources/advertises/main/1.png',2,'products/1',1,1),(2,'c:/resources/advertises/main/2.png',3,'events/1',1,2),(3,'c:/resources/advertises/main/3.png',4,'notices/1',1,3);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='메인화면 팝업 광고 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advertise_for_popup_tbl`
--

LOCK TABLES `advertise_for_popup_tbl` WRITE;
/*!40000 ALTER TABLE `advertise_for_popup_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `advertise_for_popup_tbl` ENABLE KEYS */;
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
  `detail_site_idx` int(11) DEFAULT NULL,
  `arrival_date` date DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_cart_customer_idx` (`customer_idx`),
  KEY `fk_cart_dsite_idx` (`detail_site_idx`),
  CONSTRAINT `fk_cart_customer` FOREIGN KEY (`customer_idx`) REFERENCES `customer_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cart_dsite` FOREIGN KEY (`detail_site_idx`) REFERENCES `detail_for_delivery_site_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='카트 관리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_tbl`
--

LOCK TABLES `cart_tbl` WRITE;
/*!40000 ALTER TABLE `cart_tbl` DISABLE KEYS */;
INSERT INTO `cart_tbl` VALUES (1,1,1,'2019-02-21');
/*!40000 ALTER TABLE `cart_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cmt_advertise_for_main_tbl`
--

DROP TABLE IF EXISTS `cmt_advertise_for_main_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmt_advertise_for_main_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `imgpath` varchar(100) DEFAULT NULL COMMENT '광고 제목',
  `comment_all_idx` int(11) DEFAULT NULL,
  `state_active` tinyint(4) NOT NULL DEFAULT '1' COMMENT '활성화 여부 상태\n0 - 비활성화\n1 - 활성화',
  `sequence` int(11) NOT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_cmtad_comment_all_idx` (`comment_all_idx`),
  CONSTRAINT `fk_cmtad_comment_all` FOREIGN KEY (`comment_all_idx`) REFERENCES `comment_for_all_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='메인화면 고객의소리 관리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cmt_advertise_for_main_tbl`
--

LOCK TABLES `cmt_advertise_for_main_tbl` WRITE;
/*!40000 ALTER TABLE `cmt_advertise_for_main_tbl` DISABLE KEYS */;
INSERT INTO `cmt_advertise_for_main_tbl` VALUES (1,'/view/main/imageForStore/1.png',1,1,1),(2,'/view/main/imageForStore/2.png',2,1,2),(3,'/view/main/imageForStore/3.png',2,1,3);
/*!40000 ALTER TABLE `cmt_advertise_for_main_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_for_all_tbl`
--

DROP TABLE IF EXISTS `comment_for_all_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment_for_all_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `store_idx` int(11) DEFAULT NULL,
  `customer_idx` int(11) DEFAULT NULL,
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `cnt_star` tinyint(4) DEFAULT '0' COMMENT '별점',
  `cnt_like` int(11) DEFAULT '0' COMMENT '좋아요 개수',
  `cnt_unlike` int(11) DEFAULT '0' COMMENT '싫어요 개수',
  `cnt_view` int(11) NOT NULL DEFAULT '0',
  `title` varchar(200) NOT NULL COMMENT '제목',
  `contents` text COMMENT '내용',
  `state_active` tinyint(4) NOT NULL DEFAULT '1' COMMENT '후기 상태\n0 - 비활성화 (신고 등으로 인한 비활성화)\n1 - 활성화 (기본)',
  `state_anonymous` tinyint(4) NOT NULL DEFAULT '0' COMMENT '익명 여부\n0 - 실명\n1 - 익명',
  PRIMARY KEY (`idx`),
  KEY `comment_store_fk_idx` (`store_idx`),
  KEY `comment_customer_fk_idx` (`customer_idx`),
  CONSTRAINT `comment_customer_fk` FOREIGN KEY (`customer_idx`) REFERENCES `customer_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `comment_store_fk` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='고객의소리 리뷰 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_for_all_tbl`
--

LOCK TABLES `comment_for_all_tbl` WRITE;
/*!40000 ALTER TABLE `comment_for_all_tbl` DISABLE KEYS */;
INSERT INTO `comment_for_all_tbl` VALUES (1,1,1,'2019-02-20 14:56:27','2019-02-20 14:56:27',5,43,1,56665,'리뷰제목','맛있어요 내용무',1,0),(2,1,1,'2019-02-20 15:16:21',NULL,4,23,3,34412,'리뷰제목2','존맛탱 구리 구분자 (delimiter) delim가 count 만큼 나오기 전에 스트링 str 에서 서브 스트링을 리턴한다. ',1,0);
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
  `cnt_star` tinyint(4) DEFAULT '0',
  `cnt_like` int(11) DEFAULT '0',
  `contents` varchar(45) NOT NULL,
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `state_active` tinyint(4) NOT NULL DEFAULT '1' COMMENT '후기 상태\n0 - 비활성화 (신고 등으로 인한 비활성화)\n1 - 활성화 (기본)',
  `state_anonymous` tinyint(4) NOT NULL DEFAULT '0' COMMENT '익명 여부\n0 - 실명\n1 - 익명',
  PRIMARY KEY (`idx`),
  KEY `fk_cs_store_idx` (`store_idx`),
  KEY `fk_cs_cust_idx` (`customer_idx`),
  CONSTRAINT `fk_cs_cust` FOREIGN KEY (`customer_idx`) REFERENCES `customer_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cs_store` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='5마디 리뷰 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_for_store_tbl`
--

LOCK TABLES `comment_for_store_tbl` WRITE;
/*!40000 ALTER TABLE `comment_for_store_tbl` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='공통 매핑 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `common_map_tbl`
--

LOCK TABLES `common_map_tbl` WRITE;
/*!40000 ALTER TABLE `common_map_tbl` DISABLE KEYS */;
INSERT INTO `common_map_tbl` VALUES (2,'splash-background','c:/resources/images/splash/splash-background.png'),(3,'splash-logo','c:/resources/images/splash/splash-logo.png'),(4,'splash-title','c:/resources/images/splash/splash-title.png'),(5,'company-name-kr','ⓒ 미스터포터'),(6,'service-name-kr','포만감'),(7,'company-name-en','ⓒ MR.PORTER'),(8,'service-name-en','POMANGAM');
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
  CONSTRAINT `cost_product_fk` FOREIGN KEY (`product_idx`) REFERENCES `product_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='product의 가격에 대한 상세 정보가 기입되는 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cost_tbl`
--

LOCK TABLES `cost_tbl` WRITE;
/*!40000 ALTER TABLE `cost_tbl` DISABLE KEYS */;
INSERT INTO `cost_tbl` VALUES (1,1,5000,300,0,1000,0),(2,2,5500,300,0,1000,0),(3,3,4500,300,0,1000,0),(4,4,8000,0,0,1300,0),(5,5,9000,0,0,1300,0),(6,6,10000,0,0,1300,0),(7,7,6000,0,5,1000,0),(8,8,6000,0,5,1000,0),(9,9,7000,0,5,1000,0),(10,10,1500,0,0,0,0),(11,11,1500,0,0,0,0),(12,12,1000,0,0,0,0),(13,13,5000,500,0,1000,0),(14,14,8000,1000,0,500,0),(15,15,6000,300,0,1000,0);
/*!40000 ALTER TABLE `cost_tbl` ENABLE KEYS */;
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
  `begin_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '쿠폰 시작 날짜\n(기간 0 입력 시 무제한으로 설정)',
  `end_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '쿠폰 종료 날짜\n(기간 0 입력 시 무제한으로 설정)',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `code` varchar(100) NOT NULL COMMENT '쿠폰 식별 코드',
  PRIMARY KEY (`idx`),
  UNIQUE KEY `code_UNIQUE` (`code`),
  KEY `fk_cp_emp_idx` (`issuer_idx`),
  KEY `fk_cp_modify_emp_idx` (`modifier_idx`),
  CONSTRAINT `fk_cp_issue_emp` FOREIGN KEY (`issuer_idx`) REFERENCES `employee_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cp_modify_emp` FOREIGN KEY (`modifier_idx`) REFERENCES `employee_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='쿠폰 관련  테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coupon_tbl`
--

LOCK TABLES `coupon_tbl` WRITE;
/*!40000 ALTER TABLE `coupon_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `coupon_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_tbl`
--

DROP TABLE IF EXISTS `customer_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_tbl` (
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
  PRIMARY KEY (`idx`),
  UNIQUE KEY `cust_id_UNIQUE` (`id`),
  KEY `fk_cust_dsite_idx` (`delivery_site_idx`),
  CONSTRAINT `fk_cust_dsite` FOREIGN KEY (`delivery_site_idx`) REFERENCES `delivery_site_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='본 서비스를 사용하게 되는 사용자에 관한 테이블\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_tbl`
--

LOCK TABLES `customer_tbl` WRITE;
/*!40000 ALTER TABLE `customer_tbl` DISABLE KEYS */;
INSERT INTO `customer_tbl` VALUES (1,1,'cholnh','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','홍길동','낙지',0,2019,12,31,'010-1234-1234',1,'2019-01-08 05:52:27','2019-01-08 05:52:27',3000),(2,1,'admin','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','관리자','관리자',0,2019,12,31,'010-1234-1234',1,'2019-01-08 05:52:27','2019-01-08 05:52:27',3000),(3,1,'test','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','테스트','ㅇㅇ',0,2019,12,31,'010-1234-1234',1,'2019-02-11 10:18:25',NULL,0);
/*!40000 ALTER TABLE `customer_tbl` ENABLE KEYS */;
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
  PRIMARY KEY (`idx`),
  KEY `fk_dsite_region_idx` (`region_category_idx`),
  CONSTRAINT `fk_dsite_region` FOREIGN KEY (`region_category_idx`) REFERENCES `region_category_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='배달지 테이블\n(기관, 학교 등)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_site_tbl`
--

LOCK TABLES `delivery_site_tbl` WRITE;
/*!40000 ALTER TABLE `delivery_site_tbl` DISABLE KEYS */;
INSERT INTO `delivery_site_tbl` VALUES (1,'한국항공대학교','화전',2),(2,'단국대학교','천안',6);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='부서 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department_tbl`
--

LOCK TABLES `department_tbl` WRITE;
/*!40000 ALTER TABLE `department_tbl` DISABLE KEYS */;
INSERT INTO `department_tbl` VALUES (1,1,1,NULL,'본사','본사','02-1234-1234'),(2,1,2,NULL,'강남점','지사','02-1234-1234');
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
  `employee_idx` int(11) DEFAULT NULL COMMENT '담당 직원 인덱스',
  `store_idx` int(11) DEFAULT NULL COMMENT '지정된 업체 인덱스',
  `designate_date` timestamp NULL DEFAULT NULL COMMENT '지정 날짜',
  `expire_date` timestamp NULL DEFAULT NULL COMMENT '만료 날짜',
  PRIMARY KEY (`idx`),
  KEY `fk_des_emp_idx` (`employee_idx`),
  KEY `fk_des_store_idx` (`store_idx`),
  CONSTRAINT `fk_des_emp` FOREIGN KEY (`employee_idx`) REFERENCES `employee_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_des_store` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='담당 업체 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `designation_tbl`
--

LOCK TABLES `designation_tbl` WRITE;
/*!40000 ALTER TABLE `designation_tbl` DISABLE KEYS */;
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
  PRIMARY KEY (`idx`),
  KEY `fk_detail_dsite_idx` (`delivery_site_idx`),
  CONSTRAINT `fk_detail_dsite` FOREIGN KEY (`delivery_site_idx`) REFERENCES `delivery_site_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='배달지 상세 장소 테이블\n(배달이 도착하는 곳)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detail_for_delivery_site_tbl`
--

LOCK TABLES `detail_for_delivery_site_tbl` WRITE;
/*!40000 ALTER TABLE `detail_for_delivery_site_tbl` DISABLE KEYS */;
INSERT INTO `detail_for_delivery_site_tbl` VALUES (1,1,'학생회관 뒤','항공대 학생회관 뒤편',0,'00:00:00'),(2,1,'기숙사 정문','항공대 기숙사 정문',1,'00:05:00'),(3,2,'정문','단국대학교 정문',0,'00:00:00'),(4,2,'후문','단국대학교 후문',1,'00:10:00');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='delivery site에 따라 나눠어 지는 메인 광고 팝업 연결 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dsite_link_advertise_popup_tbl`
--

LOCK TABLES `dsite_link_advertise_popup_tbl` WRITE;
/*!40000 ALTER TABLE `dsite_link_advertise_popup_tbl` DISABLE KEYS */;
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
  CONSTRAINT `fk_sitelink_cmt_ad_main` FOREIGN KEY (`cmt_advertise_main_idx`) REFERENCES `cmt_advertise_for_main_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_sitelink_cmt_dsite_main` FOREIGN KEY (`delivery_site_idx`) REFERENCES `delivery_site_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='delivery site에 따라 나눠어 지는 메인 이미지 연결 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dsite_link_cmt_advertise_main_tbl`
--

LOCK TABLES `dsite_link_cmt_advertise_main_tbl` WRITE;
/*!40000 ALTER TABLE `dsite_link_cmt_advertise_main_tbl` DISABLE KEYS */;
INSERT INTO `dsite_link_cmt_advertise_main_tbl` VALUES (1,1,1),(2,1,2),(3,2,1),(4,2,3);
/*!40000 ALTER TABLE `dsite_link_cmt_advertise_main_tbl` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='직원 관리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_tbl`
--

LOCK TABLES `employee_tbl` WRITE;
/*!40000 ALTER TABLE `employee_tbl` DISABLE KEYS */;
INSERT INTO `employee_tbl` VALUES (1,1,1,'admin','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','관리자',0,1993,1,10,'010-1234-1234','cholnh1@naver.com',1,'2019-02-11 12:44:29','2019-02-11 12:44:05',NULL,30000000,1500000,50),(2,6,2,'emp1','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','홍길동',0,1999,1,1,'010-1234-1234','hh@naver.com',1,'2019-02-11 12:44:29','2019-02-11 12:47:16','2019-02-11 13:55:44',2000000,0,0);
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
  `begin_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '이벤트 시작 날짜\n(기간 0 입력 시 무제한으로 설정)',
  `end_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '이벤트 종료 날짜\n(기간 0 입력 시 무제한으로 설정)',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `state_active` tinyint(4) NOT NULL DEFAULT '1' COMMENT '제품 상태\n0 - 비활성화\n1 - 활성화',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='이벤트 관리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_tbl`
--

LOCK TABLES `event_tbl` WRITE;
/*!40000 ALTER TABLE `event_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `event_tbl` ENABLE KEYS */;
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
  `type` tinyint(4) DEFAULT NULL COMMENT '이미지 타입\n0 - 메인 이미지\n1 - 서브 이미지',
  PRIMARY KEY (`idx`),
  KEY `fk_imgpath_comment_all_idx` (`comment_all_idx`),
  CONSTRAINT `fk_imgpath_comment_all` FOREIGN KEY (`comment_all_idx`) REFERENCES `comment_for_all_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='후기 내 이미지 정보 배열 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imgpath_for_comment_all_tbl`
--

LOCK TABLES `imgpath_for_comment_all_tbl` WRITE;
/*!40000 ALTER TABLE `imgpath_for_comment_all_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `imgpath_for_comment_all_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `imgpath_for_event_tbl`
--

DROP TABLE IF EXISTS `imgpath_for_event_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `imgpath_for_event_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `event_idx` int(11) DEFAULT NULL,
  `imgpath` varchar(200) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_imgpath_event_idx` (`event_idx`),
  CONSTRAINT `fk_imgpath_event` FOREIGN KEY (`event_idx`) REFERENCES `event_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='이벤트 내 이미지 정보 배열 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imgpath_for_event_tbl`
--

LOCK TABLES `imgpath_for_event_tbl` WRITE;
/*!40000 ALTER TABLE `imgpath_for_event_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `imgpath_for_event_tbl` ENABLE KEYS */;
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
  `type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_imgpath_notice_idx` (`notice_idx`),
  CONSTRAINT `fk_imgpath_notice` FOREIGN KEY (`notice_idx`) REFERENCES `notice_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='공지사항 내 이미지 정보 배열 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imgpath_for_notice_tbl`
--

LOCK TABLES `imgpath_for_notice_tbl` WRITE;
/*!40000 ALTER TABLE `imgpath_for_notice_tbl` DISABLE KEYS */;
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
  `type` tinyint(4) DEFAULT NULL COMMENT '이미지 타입\n0 - 메인 이미지\n1 - 서브 이미지',
  PRIMARY KEY (`idx`),
  KEY `asd_idx` (`product_idx`),
  CONSTRAINT `imgpath_product_fk` FOREIGN KEY (`product_idx`) REFERENCES `product_tbl` (`idx`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='제품 내 이미지 정보 배열 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imgpath_for_product_tbl`
--

LOCK TABLES `imgpath_for_product_tbl` WRITE;
/*!40000 ALTER TABLE `imgpath_for_product_tbl` DISABLE KEYS */;
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
  `type` tinyint(4) DEFAULT NULL COMMENT '이미지 타입\n0 - 메인 이미지\n1 - 서브 이미지',
  PRIMARY KEY (`idx`),
  KEY `img_store_fk_idx` (`store_idx`),
  CONSTRAINT `imgpath_store_fk` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='업체 내 이미지 정보 배열 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `imgpath_for_store_tbl`
--

LOCK TABLES `imgpath_for_store_tbl` WRITE;
/*!40000 ALTER TABLE `imgpath_for_store_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `imgpath_for_store_tbl` ENABLE KEYS */;
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
  CONSTRAINT `fk_citem_cart` FOREIGN KEY (`cart_idx`) REFERENCES `cart_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_citem_parent` FOREIGN KEY (`parent_item_idx`) REFERENCES `item_for_cart_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_citem_product` FOREIGN KEY (`product_idx`) REFERENCES `product_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_citem_store` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='카트 내 물품 관리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_for_cart_tbl`
--

LOCK TABLES `item_for_cart_tbl` WRITE;
/*!40000 ALTER TABLE `item_for_cart_tbl` DISABLE KEYS */;
INSERT INTO `item_for_cart_tbl` VALUES (1,1,1,1,2,NULL,NULL),(2,1,2,1,1,NULL,1);
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
  PRIMARY KEY (`idx`),
  KEY `fk_oi_order_idx` (`order_idx`),
  KEY `fk_oi_store_idx` (`store_idx`),
  KEY `fk_oi_prod_idx` (`product_idx`),
  KEY `fk_oi_parent_idx` (`parent_item_idx`),
  CONSTRAINT `fk_oi_order` FOREIGN KEY (`order_idx`) REFERENCES `order_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_oi_parent` FOREIGN KEY (`parent_item_idx`) REFERENCES `item_for_order_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_oi_prod` FOREIGN KEY (`product_idx`) REFERENCES `product_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_oi_store` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='주문 항목 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item_for_order_tbl`
--

LOCK TABLES `item_for_order_tbl` WRITE;
/*!40000 ALTER TABLE `item_for_order_tbl` DISABLE KEYS */;
INSERT INTO `item_for_order_tbl` VALUES (1,1,1,1,1,'x',NULL),(2,2,1,10,1,'x',1),(3,3,2,4,2,NULL,NULL),(4,4,1,2,1,NULL,NULL),(5,5,3,7,2,NULL,NULL),(6,6,3,8,1,NULL,NULL),(7,7,1,2,1,NULL,NULL),(8,7,3,5,1,NULL,NULL),(9,8,2,6,2,NULL,NULL),(10,8,3,8,1,NULL,NULL),(11,9,1,1,1,'x',NULL),(12,10,1,1,1,'x',NULL),(13,11,2,4,2,NULL,NULL),(14,12,1,2,1,NULL,NULL),(15,13,3,7,2,NULL,NULL),(16,14,3,8,1,NULL,NULL),(17,15,1,2,1,NULL,NULL),(18,15,3,5,1,NULL,NULL),(19,16,2,6,2,NULL,NULL),(20,16,3,8,1,NULL,NULL),(21,17,4,4,1,'x',NULL),(22,18,4,4,1,'x',NULL),(23,19,5,5,2,NULL,NULL),(24,20,4,4,1,NULL,NULL),(25,21,6,6,2,NULL,NULL),(26,22,6,6,1,NULL,NULL),(27,23,4,4,1,NULL,NULL),(28,23,6,6,1,NULL,NULL),(29,24,5,5,2,NULL,NULL),(30,24,6,6,1,NULL,NULL),(31,25,4,4,1,'x',NULL),(32,26,4,4,1,'x',NULL),(33,27,5,5,2,NULL,NULL),(34,28,4,4,1,NULL,NULL),(35,29,6,6,2,NULL,NULL),(36,30,6,6,1,NULL,NULL),(37,31,4,4,1,NULL,NULL),(38,31,6,6,1,NULL,NULL),(39,32,5,5,2,NULL,NULL),(40,32,6,6,1,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='직업 직무 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_tbl`
--

LOCK TABLES `job_tbl` WRITE;
/*!40000 ALTER TABLE `job_tbl` DISABLE KEYS */;
INSERT INTO `job_tbl` VALUES (1,'cto',0,0,'cto'),(2,'ceo',0,0,'ceo'),(3,'cdo',0,0,'cdo'),(4,'cmo',0,0,'cmo'),(5,'coo',0,0,'coo'),(6,'사원',0,0,'사원');
/*!40000 ALTER TABLE `job_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_for_comment_all_tbl`
--

DROP TABLE IF EXISTS `like_for_comment_all_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `like_for_comment_all_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `comment_all_idx` int(11) DEFAULT NULL,
  `customer_idx` int(11) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '0 - like\n1 - unlike',
  PRIMARY KEY (`idx`),
  KEY `fk_like_comment_all_idx` (`comment_all_idx`),
  KEY `fk_like_customer_all_idx` (`customer_idx`),
  CONSTRAINT `fk_like_comment_all` FOREIGN KEY (`comment_all_idx`) REFERENCES `comment_for_all_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_like_customer_all` FOREIGN KEY (`customer_idx`) REFERENCES `customer_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='고객의소리 리뷰 관련  like, unlike 관리 테이블 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_for_comment_all_tbl`
--

LOCK TABLES `like_for_comment_all_tbl` WRITE;
/*!40000 ALTER TABLE `like_for_comment_all_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `like_for_comment_all_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_for_comment_store_tbl`
--

DROP TABLE IF EXISTS `like_for_comment_store_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `like_for_comment_store_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `comment_store_idx` int(11) DEFAULT NULL,
  `customer_idx` int(11) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '0 - like\n1 - unlike',
  PRIMARY KEY (`idx`),
  KEY `fk_like_comment_store_idx` (`comment_store_idx`),
  KEY `fk_like_customer_store_idx` (`customer_idx`),
  CONSTRAINT `fk_like_comment_store` FOREIGN KEY (`comment_store_idx`) REFERENCES `comment_for_store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='업체 5마디 리뷰 관련  like, unlike 관리 테이블 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `like_for_comment_store_tbl`
--

LOCK TABLES `like_for_comment_store_tbl` WRITE;
/*!40000 ALTER TABLE `like_for_comment_store_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `like_for_comment_store_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `like_for_reply_all_tbl`
--

DROP TABLE IF EXISTS `like_for_reply_all_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `like_for_reply_all_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `reply_all_idx` int(11) DEFAULT NULL,
  `customer_idx` int(11) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '0 - like\n1 - unlike',
  PRIMARY KEY (`idx`),
  KEY `fk_like_reply_all_idx` (`reply_all_idx`),
  KEY `fk_like_reply_customer_all_idx` (`customer_idx`),
  CONSTRAINT `fk_like_reply_all` FOREIGN KEY (`reply_all_idx`) REFERENCES `reply_for_comment_all_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_like_reply_customer_all` FOREIGN KEY (`customer_idx`) REFERENCES `customer_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `reply_store_idx` int(11) DEFAULT NULL,
  `customer_idx` int(11) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '0 - like\n1 - unlike',
  PRIMARY KEY (`idx`),
  KEY `fk_like_reply_store_idx` (`reply_store_idx`),
  KEY `fk_like_reply_customer_store_idx` (`customer_idx`),
  CONSTRAINT `fk_like_reply_customer_store` FOREIGN KEY (`customer_idx`) REFERENCES `customer_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_like_reply_store` FOREIGN KEY (`reply_store_idx`) REFERENCES `reply_for_comment_store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='부서 상세 위치 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location_for_department_tbl`
--

LOCK TABLES `location_for_department_tbl` WRITE;
/*!40000 ALTER TABLE `location_for_department_tbl` DISABLE KEYS */;
INSERT INTO `location_for_department_tbl` VALUES (1,'고양','경기도 고양시 덕양구'),(2,'강남','서울특별시 강남구');
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
  `order_idx` int(11) DEFAULT NULL,
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` tinyint(4) NOT NULL COMMENT '로깅 타입\n0 - 쿠폰 생성 로그\n1 - 쿠폰 지급 로그\n2 - 쿠폰 사용 로그\n3 - 쿠폰 수정 로그\n4 - 쿠폰 삭제 로그',
  PRIMARY KEY (`idx`),
  KEY `fk_cl_cp_idx` (`coupon_idx`),
  KEY `fk_cl_emp_idx` (`employee_idx`),
  KEY `fk_cl_cust_idx` (`customer_idx`),
  KEY `fk_cl_order_idx` (`order_idx`),
  CONSTRAINT `fk_cl_cp` FOREIGN KEY (`coupon_idx`) REFERENCES `coupon_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cl_cust` FOREIGN KEY (`customer_idx`) REFERENCES `customer_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cl_emp` FOREIGN KEY (`employee_idx`) REFERENCES `employee_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_cl_order` FOREIGN KEY (`order_idx`) REFERENCES `order_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='쿠폰 사용 내역 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_for_coupon_tbl`
--

LOCK TABLES `log_for_coupon_tbl` WRITE;
/*!40000 ALTER TABLE `log_for_coupon_tbl` DISABLE KEYS */;
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
  `employee_idx` int(11) DEFAULT NULL COMMENT '배달 직원 인덱스',
  `delivery_site_idx` int(11) DEFAULT NULL COMMENT '배달지 인덱스',
  `type_payment` tinyint(4) NOT NULL COMMENT '주문 결제 방식',
  `state_order` tinyint(4) NOT NULL DEFAULT '0' COMMENT '주문 상태\n0 - 결제 대기\n1 - 결제 완료\n2 - 결제 실패\n3 - 배달 완료\n4 - 결제 취소\n5 - 결제 환불\n',
  `total` int(11) NOT NULL COMMENT '합계 금액',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록 날짜',
  `arrival_date_only` date DEFAULT NULL COMMENT '받는 날짜',
  `ordertime_for_store_idx` int(11) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_lo_cust_idx` (`customer_idx`),
  KEY `fk_lo_emp_idx` (`employee_idx`),
  KEY `fk_lo_dsite_idx` (`delivery_site_idx`),
  CONSTRAINT `fk_lo_cust` FOREIGN KEY (`customer_idx`) REFERENCES `customer_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_lo_dsite` FOREIGN KEY (`delivery_site_idx`) REFERENCES `delivery_site_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_lo_emp` FOREIGN KEY (`employee_idx`) REFERENCES `employee_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='주문 로깅 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_for_order_tbl`
--

LOCK TABLES `log_for_order_tbl` WRITE;
/*!40000 ALTER TABLE `log_for_order_tbl` DISABLE KEYS */;
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
  `pre_prc` varchar(45) NOT NULL COMMENT '로깅 전 포인트',
  `post_prc` int(11) NOT NULL COMMENT '로깅 후 포인트\n최종 포인트 금액',
  `using_point` int(11) NOT NULL COMMENT 'type에 따른,\n사용한 포인트 금액\n(지급, 사용, 수정 등의 금액)',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` tinyint(4) NOT NULL COMMENT '포인트 사용 종류\n[포인트 지급]\n00 - 관리자 권한 지급\n01 - 프로모션 지급\n02 - 구매활동을 통한 지급\n[포인트 사용]\n10 - 주문 가격 할인에 사용\n[포인트 수정]\n20 - 관리자 권한 수정',
  `contents` varchar(150) DEFAULT NULL COMMENT '비고',
  `sequence` int(11) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_pl_emp_idx` (`employee_idx`),
  KEY `fk_pl_cust_idx` (`customer_idx`),
  KEY `fk_pl_order_idx` (`order_idx`),
  CONSTRAINT `fk_pl_cust` FOREIGN KEY (`customer_idx`) REFERENCES `customer_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pl_emp` FOREIGN KEY (`employee_idx`) REFERENCES `employee_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pl_order` FOREIGN KEY (`order_idx`) REFERENCES `order_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='포인트 사용 내역 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_for_point_tbl`
--

LOCK TABLES `log_for_point_tbl` WRITE;
/*!40000 ALTER TABLE `log_for_point_tbl` DISABLE KEYS */;
INSERT INTO `log_for_point_tbl` VALUES (1,1,1,NULL,'0',1000,1000,'2019-02-11 12:32:30',0,NULL,1),(2,NULL,1,NULL,'1000',2000,1000,'2019-02-11 12:33:00',1,NULL,2),(3,NULL,1,NULL,'2000',2500,500,'2019-02-11 12:33:02',2,NULL,3),(4,NULL,1,32,'2500',1500,1000,'2019-02-11 12:49:56',10,'포인트사용',4),(5,2,2,NULL,'0',1000,1000,'2019-02-11 12:32:30',0,NULL,1),(6,NULL,2,NULL,'1000',2000,1000,'2019-02-11 12:33:00',1,NULL,2),(7,NULL,2,NULL,'2000',2500,500,'2019-02-11 12:33:02',2,NULL,3),(8,NULL,2,32,'2500',1500,1000,'2019-02-11 12:49:56',10,'포인트사용',4);
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
  `begin_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '이벤트 시작 날짜\n(기간 0 입력 시 무제한으로 설정)',
  `end_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '이벤트 종료 날짜\n(기간 0 입력 시 무제한으로 설정)',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `state_active` tinyint(4) NOT NULL DEFAULT '1' COMMENT '제품 상태\n0 - 비활성화\n1 - 활성화',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='공지사항 관리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice_tbl`
--

LOCK TABLES `notice_tbl` WRITE;
/*!40000 ALTER TABLE `notice_tbl` DISABLE KEYS */;
/*!40000 ALTER TABLE `notice_tbl` ENABLE KEYS */;
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
  `employee_idx` int(11) DEFAULT NULL COMMENT '배달 직원 인덱스',
  `delivery_site_idx` int(11) DEFAULT NULL COMMENT '배달지 인덱스',
  `detail_site_idx` int(11) DEFAULT NULL,
  `type_payment` tinyint(4) NOT NULL COMMENT '주문 결제 방식',
  `state_order` tinyint(4) NOT NULL DEFAULT '0' COMMENT '주문 상태\n0 - 결제 대기\n1 - 결제 완료\n2 - 결제 실패\n3 - 배달 완료\n4 - 결제 취소\n5 - 결제 환불\n',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록 날짜',
  `arrival_date_only` date DEFAULT NULL COMMENT '받는 날짜',
  `arrival_time_only` time DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_order_cust_idx` (`customer_idx`),
  KEY `fk_order_emp_idx` (`employee_idx`),
  KEY `fk_order_dsite_idx` (`delivery_site_idx`),
  KEY `fk_order_dtsite_idx` (`detail_site_idx`),
  CONSTRAINT `fk_order_cust` FOREIGN KEY (`customer_idx`) REFERENCES `customer_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_dsite` FOREIGN KEY (`delivery_site_idx`) REFERENCES `delivery_site_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_dtsite` FOREIGN KEY (`detail_site_idx`) REFERENCES `detail_for_delivery_site_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_emp` FOREIGN KEY (`employee_idx`) REFERENCES `employee_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='주문 목록 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_tbl`
--

LOCK TABLES `order_tbl` WRITE;
/*!40000 ALTER TABLE `order_tbl` DISABLE KEYS */;
INSERT INTO `order_tbl` VALUES (1,11,1,1,1,NULL,1,0,'2019-02-01 03:00:00','2019-02-09','12:00:00'),(2,12,1,1,1,NULL,1,0,'2019-02-01 03:00:00','2019-02-09','12:00:00'),(3,13,1,1,1,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','13:00:00'),(4,14,1,1,1,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','13:00:00'),(5,15,1,1,1,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','17:00:00'),(6,16,1,1,1,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','12:00:00'),(7,17,1,1,1,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','12:00:00'),(8,18,1,1,1,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','17:00:00'),(9,11,1,1,1,NULL,1,0,'2019-02-01 03:00:00','2019-02-09','12:00:00'),(10,12,1,1,1,NULL,1,0,'2019-02-01 03:00:00','2019-02-09','12:00:00'),(11,13,1,1,1,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','17:00:00'),(12,14,1,1,1,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','17:00:00'),(13,15,1,1,1,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','12:00:00'),(14,16,1,1,1,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','18:00:00'),(15,17,1,1,1,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','18:00:00'),(16,18,1,1,1,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','18:00:00'),(17,11,1,1,2,NULL,1,0,'2019-02-01 03:00:00','2019-02-09','12:00:00'),(18,12,1,1,2,NULL,1,0,'2019-02-01 03:00:00','2019-02-09','12:00:00'),(19,13,1,1,2,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','13:00:00'),(20,14,1,1,2,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','13:00:00'),(21,15,1,1,2,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','17:00:00'),(22,16,1,1,2,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','12:00:00'),(23,17,1,1,2,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','12:00:00'),(24,18,1,1,2,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','17:00:00'),(25,11,1,1,2,NULL,1,0,'2019-02-01 03:00:00','2019-02-09','12:00:00'),(26,12,1,1,2,NULL,1,0,'2019-02-01 03:00:00','2019-02-09','12:00:00'),(27,13,1,1,2,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','17:00:00'),(28,14,1,1,2,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','17:00:00'),(29,15,1,1,2,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','12:00:00'),(30,16,1,1,2,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','18:00:00'),(31,17,1,1,2,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','18:00:00'),(32,18,1,1,2,NULL,1,0,'2019-02-07 14:39:03','2019-02-09','18:00:00');
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
  `state_pause` tinyint(4) NOT NULL DEFAULT '0' COMMENT '해당 시간 일시정지 여부',
  `order_deadline` time NOT NULL,
  `arrival_time` time NOT NULL,
  `arrival_tomorrow` tinyint(4) DEFAULT NULL,
  `sequence` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_ots_dsite_idx` (`delivery_site_idx`),
  KEY `fk_ots_store_idx` (`store_idx`),
  CONSTRAINT `fk_ots_dsite` FOREIGN KEY (`delivery_site_idx`) REFERENCES `delivery_site_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ots_store` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COMMENT='업체 주문_가능_시간표 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordertime_for_store_tbl`
--

LOCK TABLES `ordertime_for_store_tbl` WRITE;
/*!40000 ALTER TABLE `ordertime_for_store_tbl` DISABLE KEYS */;
INSERT INTO `ordertime_for_store_tbl` VALUES (1,1,1,0,'11:40:00','12:00:00',0,1),(2,1,1,0,'12:40:00','13:00:00',0,2),(3,1,1,0,'16:40:00','17:00:00',0,3),(4,1,1,0,'17:40:00','18:00:00',0,4),(5,1,1,0,'18:50:00','19:00:00',0,5),(7,1,2,0,'11:40:00','12:00:00',0,1),(8,1,2,0,'12:40:00','13:00:00',0,2),(9,1,2,0,'16:40:00','17:00:00',0,3),(10,1,2,0,'17:40:00','18:00:00',0,4),(11,1,2,0,'18:40:00','19:00:00',0,5),(13,1,3,0,'11:40:00','12:00:00',0,1),(14,1,3,0,'12:40:00','13:00:00',0,2),(15,1,3,0,'16:40:00','17:00:00',0,3),(16,1,3,0,'17:40:00','18:00:00',0,4),(17,1,3,0,'18:40:00','19:00:00',0,5),(19,2,4,0,'11:40:00','12:00:00',0,1),(20,2,4,0,'12:40:00','13:00:00',0,2),(21,2,4,0,'16:40:00','17:00:00',0,3),(22,2,4,0,'17:40:00','18:00:00',0,4),(23,2,4,0,'18:40:00','19:00:00',0,5),(25,2,5,0,'11:40:00','12:00:00',0,1),(26,2,5,0,'12:40:00','13:00:00',0,2),(27,2,5,0,'16:40:00','17:00:00',0,3),(28,2,5,0,'17:40:00','18:00:00',0,4),(29,2,5,0,'18:40:00','19:00:00',0,5),(31,2,6,0,'11:40:00','12:00:00',0,1),(32,2,6,0,'12:40:00','13:00:00',0,2),(33,2,6,0,'16:40:00','17:00:00',0,3),(34,2,6,0,'17:40:00','18:00:00',0,4),(35,2,6,0,'18:40:00','19:00:00',0,5);
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
  `hire_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `type_role` tinyint(4) NOT NULL COMMENT '업무 별 역할\n0 - 업주\n1 - 매니저\n2 - 직원',
  PRIMARY KEY (`idx`),
  KEY `fk_owner_store_idx` (`store_idx`),
  CONSTRAINT `fk_owner_store` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='업주 관리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `owner_of_store_tbl`
--

LOCK TABLES `owner_of_store_tbl` WRITE;
/*!40000 ALTER TABLE `owner_of_store_tbl` DISABLE KEYS */;
INSERT INTO `owner_of_store_tbl` VALUES (1,1,'moms1','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','김철수',0,1979,1,1,'010-1234-1234','moms@naver.com',1,'2018-08-31 15:00:00','2019-02-11 14:08:05','2019-02-11 14:08:05',0),(2,1,'kim23','$2a$10$Gye6eNp8DgS.knEDLycE5OzHicrRqe18nkdeUcT0De72Soiuuy2DC','김알바',0,1998,2,2,'010-1234-1234','kim23@naver.com',1,'2018-08-31 15:00:00','2019-02-11 14:08:53','2019-02-11 14:08:53',2);
/*!40000 ALTER TABLE `owner_of_store_tbl` ENABLE KEYS */;
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
  `cnt_like` int(11) DEFAULT '0' COMMENT '좋아요 수',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `sequence` int(11) DEFAULT NULL COMMENT '진열 순서',
  PRIMARY KEY (`idx`),
  KEY `fk_prod_store_idx` (`store_idx`),
  CONSTRAINT `fk_prod_store` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='판매되는 제품에 관한 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_tbl`
--

LOCK TABLES `product_tbl` WRITE;
/*!40000 ALTER TABLE `product_tbl` DISABLE KEYS */;
INSERT INTO `product_tbl` VALUES (1,1,'싸이버거','햄버거','햄버거',NULL,NULL,1,0,234,'2019-02-11 14:00:05','2019-02-11 14:00:05',0),(2,1,'불싸이버거','햄버거','햄버거',NULL,NULL,1,0,534,'2019-02-11 14:00:05','2019-02-11 14:00:05',1),(3,1,'불고기버거','햄버거','햄버거',NULL,NULL,1,0,654,'2019-02-11 14:00:05','2019-02-11 14:00:05',2),(4,2,'불고기피자','피자','피자',NULL,NULL,1,0,234,'2019-02-11 14:00:05','2019-02-11 14:00:05',0),(5,2,'포테이토피자','피자','피자',NULL,NULL,1,0,345,'2019-02-11 14:00:05','2019-02-11 14:00:05',1),(6,2,'핫치킨피자','피자','피자',NULL,NULL,1,0,234,'2019-02-11 14:00:05','2019-02-11 14:00:05',2),(7,3,'서브웨이 클럽','샌드위치','샌드위치',NULL,NULL,1,0,56,'2019-02-11 14:00:05','2019-02-11 14:00:05',0),(8,3,'스테이크 엔 치즈','샌드위치','샌드위치',NULL,NULL,1,0,8,'2019-02-11 14:00:05','2019-02-11 14:00:05',1),(9,3,'풀드포크','샌드위치','샌드위치',NULL,NULL,1,0,678,'2019-02-11 14:00:05','2019-02-11 14:00:05',2),(10,1,'케이준 후라이','감자튀김','감튀',NULL,NULL,1,1,12,'2019-02-11 14:00:05','2019-02-11 14:00:05',3),(11,2,'코카콜라(1.5L)','음료수','음료수',NULL,NULL,1,3,123,'2019-02-11 14:00:05','2019-02-11 14:00:05',3),(12,3,'쿠키 (화이트 마카다미아)','쿠키','쿠키',NULL,NULL,1,1,23,'2019-02-11 14:00:05','2019-02-11 14:00:05',3),(13,4,'싸이버거2','햄버거','햄버거',NULL,NULL,1,0,324,'2019-02-11 14:00:05','2019-02-11 14:00:05',0),(14,5,'불고기피자2','피자','피자',NULL,NULL,1,0,54,'2019-02-11 14:00:05','2019-02-11 14:00:05',0),(15,6,'서브웨이 클럽2','샌드위치','샌드위치',NULL,NULL,1,0,56,'2019-02-11 14:00:05','2019-02-11 14:00:05',0);
/*!40000 ALTER TABLE `product_tbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion_for_product_tbl`
--

DROP TABLE IF EXISTS `promotion_for_product_tbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promotion_for_product_tbl` (
  `idx` int(11) NOT NULL AUTO_INCREMENT,
  `promotion_idx` int(11) DEFAULT NULL,
  `product_idx` int(11) DEFAULT NULL,
  PRIMARY KEY (`idx`),
  KEY `fk_prod_link_idx` (`product_idx`),
  KEY `fk_prom_link_idx` (`promotion_idx`),
  CONSTRAINT `fk_prod_link` FOREIGN KEY (`product_idx`) REFERENCES `product_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_prom_link` FOREIGN KEY (`promotion_idx`) REFERENCES `promotion_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='프로모션 - 제품 연결 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion_for_product_tbl`
--

LOCK TABLES `promotion_for_product_tbl` WRITE;
/*!40000 ALTER TABLE `promotion_for_product_tbl` DISABLE KEYS */;
INSERT INTO `promotion_for_product_tbl` VALUES (1,1,1),(2,1,2),(3,1,3);
/*!40000 ALTER TABLE `promotion_for_product_tbl` ENABLE KEYS */;
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
  `begin_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '프로모션 시작 날짜\n(기간 0 입력 시 무제한으로 설정)',
  `end_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '프로모션 종료 날짜\n(기간 0 입력 시 무제한으로 설정)',
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `state_active` tinyint(4) NOT NULL DEFAULT '1' COMMENT '제품 상태\n0 - 비활성화\n1 - 활성화',
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='product에 적용되는 가격 할인 프로모션 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion_tbl`
--

LOCK TABLES `promotion_tbl` WRITE;
/*!40000 ALTER TABLE `promotion_tbl` DISABLE KEYS */;
INSERT INTO `promotion_tbl` VALUES (1,'2월 신학기 프로모션',1000,0,'2019-01-31 15:00:00','2019-02-28 14:59:59','2019-02-11 13:39:32','2019-02-11 14:01:57',1);
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
  CONSTRAINT `fk_reply_comment_all` FOREIGN KEY (`comment_all_idx`) REFERENCES `comment_for_all_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reply_customer_all` FOREIGN KEY (`customer_idx`) REFERENCES `customer_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reply_owner_all` FOREIGN KEY (`owner_idx`) REFERENCES `owner_of_store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='고객의소리 글의 댓글 관리 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reply_for_comment_all_tbl`
--

LOCK TABLES `reply_for_comment_all_tbl` WRITE;
/*!40000 ALTER TABLE `reply_for_comment_all_tbl` DISABLE KEYS */;
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
  CONSTRAINT `fk_reply_comment_store` FOREIGN KEY (`comment_store_idx`) REFERENCES `comment_for_store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reply_customer_store` FOREIGN KEY (`customer_idx`) REFERENCES `customer_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reply_owner_store` FOREIGN KEY (`owner_idx`) REFERENCES `owner_of_store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
  `state_active` tinyint(4) NOT NULL DEFAULT '1' COMMENT '활성화 여부 상태\n0 - 비활성화\n1 - 활성화',
  `state_pause` tinyint(4) NOT NULL DEFAULT '0' COMMENT '일시정지 여부 상태\n0 - 비활성화 (실행 중)\n1 - 활성화 (일시정지)',
  `pause_description` varchar(200) DEFAULT NULL COMMENT '일시정지 사유',
  `first_week_close` tinyint(4) DEFAULT NULL COMMENT '월 - 0\n화 - 1\n수 - 2\n목 - 3\n금 - 4\n토 - 5\n일 - 6',
  `second_week_close` tinyint(4) DEFAULT NULL COMMENT '월 - 0\n화 - 1\n수 - 2\n목 - 3\n금 - 4\n토 - 5\n일 - 6',
  `third_week_close` tinyint(4) DEFAULT NULL COMMENT '월 - 0\n화 - 1\n수 - 2\n목 - 3\n금 - 4\n토 - 5\n일 - 6',
  `fourth_week_close` tinyint(4) DEFAULT NULL COMMENT '월 - 0\n화 - 1\n수 - 2\n목 - 3\n금 - 4\n토 - 5\n일 - 6',
  PRIMARY KEY (`idx`),
  KEY `schedule_store_fk_idx` (`store_idx`),
  CONSTRAINT `schedule_store_fk` FOREIGN KEY (`store_idx`) REFERENCES `store_tbl` (`idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='업체 스케쥴 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_for_store_tbl`
--

LOCK TABLES `schedule_for_store_tbl` WRITE;
/*!40000 ALTER TABLE `schedule_for_store_tbl` DISABLE KEYS */;
INSERT INTO `schedule_for_store_tbl` VALUES (1,1,'10:00:00','21:00:00',1,0,'-',2,NULL,2,NULL),(2,2,'10:00:00','21:00:00',1,0,'-',NULL,NULL,NULL,NULL),(3,3,'10:00:00','21:00:00',1,0,'-',NULL,NULL,NULL,NULL),(4,4,'10:00:00','21:00:00',1,0,'-',NULL,NULL,NULL,NULL),(5,5,'10:00:00','21:00:00',1,0,'-',NULL,NULL,NULL,NULL),(6,6,'10:00:00','21:00:00',1,0,'-',NULL,NULL,NULL,NULL);
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
  CONSTRAINT `session_for_customer_id_fk` FOREIGN KEY (`customer_idx`) REFERENCES `customer_tbl` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
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
  `description` varchar(200) DEFAULT NULL COMMENT '상세설명',
  `cnt_like` int(11) DEFAULT '0',
  `minimum_time` time NOT NULL COMMENT '최소 생산 가능 시간',
  `parallel_production` smallint(6) NOT NULL COMMENT '평균 병렬 생산량\n(단위 분)',
  `maximum_production` int(11) NOT NULL,
  `register_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idx`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='업체 관련 테이블';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `store_tbl`
--

LOCK TABLES `store_tbl` WRITE;
/*!40000 ALTER TABLE `store_tbl` DISABLE KEYS */;
INSERT INTO `store_tbl` VALUES (1,'맘스터치 (가라뫼점)','가라뫼','010-1234-1234','햄버거가게',345,'00:03:00',2,20,'2019-02-11 14:00:42','2019-02-11 14:00:42'),(2,'피자스쿨 (가라뫼점)','가라뫼','010-1234-1234','피자가게',123,'00:10:00',2,20,'2019-02-11 14:00:42','2019-02-11 14:00:42'),(3,'서브웨이 (행신점)','행신','010-1234-1234','샌드위치가게',32,'00:03:00',1,20,'2019-02-11 14:00:42','2019-02-11 14:00:42'),(4,'맘스터치 (천안점)','천안','010-1234-1234','햄버거가게',12,'00:03:00',2,20,'2019-02-11 14:00:42','2019-02-11 14:00:42'),(5,'피자스쿨 (천안점)','천안','010-1234-1234','피자가게',34,'00:10:00',2,20,'2019-02-11 14:00:42','2019-02-11 14:00:42'),(6,'서브웨이 (천안점)','천안','010-1234-1234','샌드위치가게',24,'00:03:00',1,20,'2019-02-11 14:00:42','2019-02-11 14:00:42');
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
INSERT INTO `sub_advertise_for_main_tbl` VALUES (1,'c:/resources/subadvertises/main/1.png',2,'products/1',1,1),(2,'c:/resources/subadvertises/main/2.png',3,'events/1',1,2),(3,'c:/resources/subadvertises/main/3.png',4,'notices/1',1,3);
/*!40000 ALTER TABLE `sub_advertise_for_main_tbl` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-28  9:42:54
