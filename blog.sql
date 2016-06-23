-- MySQL dump 10.13  Distrib 5.5.49, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: blog
-- ------------------------------------------------------
-- Server version	5.5.49-0ubuntu0.14.04.1

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
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Testing');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `post_id` varchar(45) NOT NULL,
  `comment` varchar(1000) NOT NULL,
  `author_name` varchar(45) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group`
--

DROP TABLE IF EXISTS `group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `group` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `create_post` bit(1) NOT NULL,
  `delete_post` bit(1) NOT NULL,
  `edit_post` bit(1) NOT NULL,
  `create_page` bit(1) NOT NULL,
  `create_category` bit(1) NOT NULL,
  `create_tag` bit(1) NOT NULL,
  `delete_category` bit(1) NOT NULL,
  `delete_tag` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group`
--

LOCK TABLES `group` WRITE;
/*!40000 ALTER TABLE `group` DISABLE KEYS */;
/*!40000 ALTER TABLE `group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `page`
--

DROP TABLE IF EXISTS `page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `url` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `content` varchar(10000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `page`
--

LOCK TABLES `page` WRITE;
/*!40000 ALTER TABLE `page` DISABLE KEYS */;
INSERT INTO `page` VALUES (1,'About Us','about',1,'Some Content'),(2,'Script','scripts',1,'Thanks for the scripts dumbasses');
/*!40000 ALTER TABLE `page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `content` varchar(10000) NOT NULL,
  `post_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `expiration_date` datetime DEFAULT NULL,
  `approved` bit(1) NOT NULL,
  `active` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_post_1_idx` (`category_id`),
  CONSTRAINT `fk_post_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'IoT: Convenient But Is It Secure?',1,1,'How do we make sure “machines” don’t take over our daily decisions and infringe on our privacy and security? One of the top innovators in IoT devices, Nest, has run into issues in regards to user data being leaked. Though it was only zip codes that were compromised, the next time it could be even more sensitive data.\n\nResearchers estimate that by 2020 the amount of wireless connected devices will be over 40 billion. More devices means more access points and the increased possibility for security breaches. Each of these products connects to your home wifi network, and receives IP addresses from your home router. Making sure your home network is secure is a great start.','2016-06-22 14:29:20','2016-06-22 10:29:20','',''),(6,'Streamlining Your VoIP Business: Automation',1,1,'A college student walks into a convenience store late on a Saturday. He approaches a screen. It asks for his order all while displaying a colorful buffet of his favorite fried snacks. He touches the “fries” icon. It asks for the size. He selects bucket. With a few more taps on the screen, his order is processed and delivered just as he wanted. A bucket-sized order of chili-cheese fries with extra sour cream. The college student is pleased. The convenience of the automated machine supplied him with the chili-cheese fries he desired along with more time to eat, party, and study.','2016-06-22 19:17:03',NULL,'',''),(7,'Choose to Become a Better ITSP Business: The Power of Choice',1,1,'<p>A quick message for wholesale VoIP providers who choose carriers and features on behalf of ITSP providers: &ldquo;We the people hereby declare independence from the oppression from tyrannical wholesale providers. We demand our right of choice when accepting services from a telecom carrier. Let it be known, on this glorious day of liberation, that we shall exercise the Freedom of Choice!&rdquo;</p>','2016-06-23 18:19:31',NULL,'\0','\0'),(8,'What You Need for Quality Toll-Free Service',1,1,'We’ve recently gone over 10 questions to ask your wholesale VoIP provider before registering a toll-free number. Today, we’re going into more detail on the subject of underlying carriers (ULCs). ULCs are a vital component in toll-free services. These guys provide service straight from the top, pass it on to your wholesale provider, and then right onto your plate. Be sure to explore the following questions and don’t miss out on the VoIP features you deserve.','2016-06-22 19:20:15',NULL,'\0',''),(15,'WELCOME TO THE JUNGLE',1,1,'Welcome to the jungle we got fun and games\nWe got everything you want honey, we know the names\nWe are the people that can find whatever you may need\nIf you got the money honey we got your disease\nIn the jungle, welcome to the jungle\nWatch it bring you to your sha na na na na knees knees \nI wanna watch you bleed\nWelcome to the jungle we take it day by day\nIf you want it you\'re gonna bleed but it\'s the price you pay\nAnd you\'re a very sexy girl very hard to please\nYou can taste the bright lights but you won\'t get there for free\nIn the jungle welcome to the jungle\nFeel my, my, my, my serpentine\nI,I wanna hear you scream','2016-06-22 19:57:21',NULL,'',''),(16,'Year-End Watch List: 10 VoIP Trends to Keep an Eye on',1,1,'According to a recent report by Future Market Insights (FMI), a research group that studies trends in the IT industry, the VoIP market is expected to experience continual growth and generate a predicted $86.2 billion by the year 2020. That’s a lot of money to be earned.\n\nIn order to stay ahead of your competitors, you must study trending developments and decide when to take necessary action. If you want a slice of that $86 billion dollar pie, you need to prepare yourself and tackle these trends before your competition. Here are ten trends to watch as we conclude these final months of 2015 and enter the New Year. ','2016-06-22 22:31:25',NULL,'',''),(17,'How to Update Your E911 Services',1,1,'<p>E911 just may be the most important DID feature there is. Without E911, you could be setting yourself up for some serious risks when emergency services can&rsquo;t immediately respond to your 9-1-1 dial. As important as having E911 services registered to your DIDs is, you must be able to effectively manage themin real-time. Today, we&rsquo;ll be going over two ways you can update your DIDs so you&rsquo;re ready for any emergency that life might throw your way.</p>','2016-06-22 22:33:58',NULL,'','\0'),(19,'This Post is About Gravy',1,1,'<p>Gravy is the shit man.</p>','2016-06-23 18:24:49',NULL,'','\0'),(20,'Hey buddy',1,1,'<p>Yo man what it do</p>','2016-06-23 18:24:48',NULL,'','\0'),(21,'TITLE',1,1,'<h1><strong>HKJDSBKJFS <em>Talking TOAD</em> COMING TO</strong><em> NICK</em></h1>','2016-06-23 14:12:17',NULL,'','\0');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post_tag`
--

DROP TABLE IF EXISTS `post_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post_tag`
--

LOCK TABLES `post_tag` WRITE;
/*!40000 ALTER TABLE `post_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `post_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagName` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NOT NULL,
  `first_name` varchar(150) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `user_name` varchar(150) NOT NULL,
  `password` varchar(150) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,1,'Pat','Toner','pToner','pattypatpat');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-06-23 14:59:27
