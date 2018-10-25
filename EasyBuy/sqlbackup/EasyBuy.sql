-- MySQL dump 10.11
--
-- Host: 192.168.13.212    Database: easyBuy
-- ------------------------------------------------------
-- Server version	5.5.44

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
-- Table structure for table `easybuy_news`
--

DROP TABLE IF EXISTS `easybuy_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `easybuy_news` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `content` varchar(1024) NOT NULL,
  `createTime` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `easybuy_news`
--

LOCK TABLES `easybuy_news` WRITE;
/*!40000 ALTER TABLE `easybuy_news` DISABLE KEYS */;
/*!40000 ALTER TABLE `easybuy_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `easybuy_order`
--

DROP TABLE IF EXISTS `easybuy_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `easybuy_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userid` int(4) NOT NULL,
  `loginName` varchar(20) NOT NULL,
  `userAddress` varchar(300) NOT NULL,
  `createTime` datetime NOT NULL,
  `cost` float(10,3) NOT NULL,
  `status` int(1) NOT NULL,
  `type` int(1) NOT NULL,
  `serialNumber` varchar(64) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=697 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `easybuy_order`
--

LOCK TABLES `easybuy_order` WRITE;
/*!40000 ALTER TABLE `easybuy_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `easybuy_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `easybuy_order_detail`
--

DROP TABLE IF EXISTS `easybuy_order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `easybuy_order_detail` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `orderId` int(10) NOT NULL COMMENT '订单主键',
  `productId` int(10) NOT NULL COMMENT '商品主键',
  `quantity` int(10) NOT NULL COMMENT '数量',
  `cost` float NOT NULL COMMENT '消费',
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__EASYBUY___66E1BD8E2F10007B` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `easybuy_order_detail`
--

LOCK TABLES `easybuy_order_detail` WRITE;
/*!40000 ALTER TABLE `easybuy_order_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `easybuy_order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `easybuy_product`
--

DROP TABLE IF EXISTS `easybuy_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `easybuy_product` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `price` float(10,3) NOT NULL,
  `stock` int(10) NOT NULL,
  `categoryLevel1` int(10) NOT NULL,
  `categoryLevel2` int(10) NOT NULL,
  `categoryLevel3` int(10) NOT NULL,
  `fileName` varchar(200) NOT NULL,
  `isDelete` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `easybuy_product`
--

LOCK TABLES `easybuy_product` WRITE;
/*!40000 ALTER TABLE `easybuy_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `easybuy_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `easybuy_product_category`
--

DROP TABLE IF EXISTS `easybuy_product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `easybuy_product_category` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) NOT NULL COMMENT '名称',
  `parentId` int(10) NOT NULL COMMENT '父级目录id',
  `type` int(11) DEFAULT NULL COMMENT '级别(1:一级 2：二级 3：三级)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__EASYBUY___9EC2A4E236B12243` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `easybuy_product_category`
--

LOCK TABLES `easybuy_product_category` WRITE;
/*!40000 ALTER TABLE `easybuy_product_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `easybuy_product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `easybuy_user`
--

DROP TABLE IF EXISTS `easybuy_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `easybuy_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) NOT NULL,
  `loginName` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `sex` int(4) NOT NULL,
  `identityCode` varchar(18) DEFAULT NULL,
  `email` varchar(80) DEFAULT NULL,
  `mobile` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `easybuy_user`
--

LOCK TABLES `easybuy_user` WRITE;
/*!40000 ALTER TABLE `easybuy_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `easybuy_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `easybuy_user_address`
--

DROP TABLE IF EXISTS `easybuy_user_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `easybuy_user_address` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `address` varchar(20) NOT NULL,
  `createTime` datetime NOT NULL,
  `userId` int(10) NOT NULL,
  `isDefault` int(4) NOT NULL,
  `remark` varchar(18) DEFAULT NULL,
  PRIMARY KEY (`id`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `easybuy_user_address`
--

LOCK TABLES `easybuy_user_address` WRITE;
/*!40000 ALTER TABLE `easybuy_user_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `easybuy_user_address` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-10-24 16:57:14
