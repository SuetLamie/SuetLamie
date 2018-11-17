-- MySQL dump 10.11
--
-- Host: localhost    Database: PhoneInfo
-- ------------------------------------------------------
-- Server version	5.0.96-community-nt

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
-- Table structure for table `informations`
--

DROP TABLE IF EXISTS `informations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `informations` (
  `id` bigint(20) NOT NULL auto_increment COMMENT '自动增长',
  `title` varchar(100) NOT NULL,
  `content` varchar(200) NOT NULL,
  `replyCount` int(50) NOT NULL,
  `viewCount` int(50) NOT NULL,
  `reportTime` datetime NOT NULL COMMENT '发表时的系统时间',
  `lastPostTime` datetime NOT NULL COMMENT '回复时的系统时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `informations`
--

LOCK TABLES `informations` WRITE;
/*!40000 ALTER TABLE `informations` DISABLE KEYS */;
INSERT INTO `informations` VALUES (1,'标题1','标题1',0,0,'2018-11-17 18:59:59','2018-11-17 19:00:03'),(2,'标题2','标题2',0,0,'2018-11-17 19:00:23','2018-11-17 19:00:27'),(3,'标题3','标题3',0,0,'2018-11-17 19:00:43','2018-11-17 19:00:46'),(4,'标题4','标题4',0,0,'2018-11-17 19:00:55','2018-11-17 19:00:58'),(5,'标题5','标题5',0,0,'2018-11-17 19:01:08','2018-11-17 19:01:10'),(6,'标题6','标题6',0,0,'2018-11-17 19:01:19','2018-11-17 19:01:22'),(7,'标题7','标题7',0,0,'2018-11-17 19:01:33','2018-11-17 19:01:35'),(8,'标题8','标题8',0,0,'2018-11-17 19:01:45','2018-11-17 19:01:48'),(9,'标题9','标题9',0,0,'2018-11-17 19:02:08','2018-11-17 19:02:09'),(10,'标题10','标题10',0,0,'2018-11-17 19:02:21','2018-11-17 19:02:23'),(11,'标题11','标题11',0,0,'2018-11-17 19:02:35','2018-11-17 19:02:37');
/*!40000 ALTER TABLE `informations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `replies`
--

DROP TABLE IF EXISTS `replies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `replies` (
  `id` bigint(20) NOT NULL auto_increment COMMENT '自动增长',
  `content` varchar(100) NOT NULL,
  `replyTime` datetime NOT NULL COMMENT '发表时的系统时间',
  `infoId` bigint(20) NOT NULL COMMENT '对应资讯表的主键',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `replies`
--

LOCK TABLES `replies` WRITE;
/*!40000 ALTER TABLE `replies` DISABLE KEYS */;
INSERT INTO `replies` VALUES (1,'内容1','2018-11-17 19:02:55',1),(2,'内容2','2018-11-17 19:03:09',1),(3,'内容3','2018-11-17 19:03:14',1),(4,'内容4','2018-11-17 19:03:20',2),(5,'内容5','2018-11-17 19:03:25',2),(6,'内容6','2018-11-17 19:03:34',3),(7,'内容7','2018-11-17 19:03:40',3);
/*!40000 ALTER TABLE `replies` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-17 19:06:39
