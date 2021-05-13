CREATE DATABASE  IF NOT EXISTS `fundrequest` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `fundrequest`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: fundrequest
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `fundrequests`
--

DROP TABLE IF EXISTS `fundrequests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fundrequests` (
  `fundID` int NOT NULL AUTO_INCREMENT,
  `clientID` varchar(45) DEFAULT NULL,
  `productID` int DEFAULT NULL,
  `contactName` varchar(45) DEFAULT NULL,
  `contactNo` varchar(45) DEFAULT NULL,
  `contactMail` varchar(45) DEFAULT NULL,
  `message` varchar(500) DEFAULT NULL,
  `orgName` varchar(45) DEFAULT NULL,
  `date` date DEFAULT NULL,
  PRIMARY KEY (`fundID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fundrequests`
--

LOCK TABLES `fundrequests` WRITE;
/*!40000 ALTER TABLE `fundrequests` DISABLE KEYS */;
INSERT INTO `fundrequests` VALUES (1,'kamal',99,'Kamal Perera','0712345678','kamalperera@gmail.com','Hello Mr.Prasanna I am representing on behalf of my company named Vortex. We are very interested in your innovation and like to discuss more with you.\n please contatc us when you are availabale. Regards.','Vortex.org','2021-05-08'),
(2,'janith',99,'Janith Silva','0779876543','janithsilva@gmail.com','Hey I am Janith an entrepreneur. I like to help and give hand to innovators like you. I was amazed by your product and like to discuss more about it. \n please contatc me after 7.00pm .',NULL,'2020-06-01'),
(3,'sunithK',99,'Sunith Karma','0714567891','sunithk@gmail.com','I am Sunith representing SunFlowerOrg. We are happy to annouce that we have choosen your project to fund. As a Organization we have to follow lot of rules and regulations therefore \n if you are interested in our oppertunity we would like to contatc you for more details. My mail and contact No are mentioned above. Please contact me after 6pm / working hours. \n Best of luck.','SunFlowerOrg','2020-06-03'),
(4,'kamal',128,'Kamal Perera','0712345678','kamalperera@gmail.com','Hello Mr.Saman I am representing on behalf of my company named Vortex. We are very interested in your innovation and like to discuss more with you.\n please contatc us when you are availabale. Regards.','Vortex.org','2020-06-07'),
(5,'kamal',1024,'Kamal Perera','0712345678','kamalperera@gmail.com','Hello Mr.Dhyan I am representing on behalf of my company named Vortex. We are very interested in your innovation and like to discuss more with you.\n please contatc us when you are availabale. Regards.','Vortex.org','2020-07-07'),
(6,'kamal',444,'Kamal Perera','0712345678','kamalperera@gmail.com','Hello Ms.Sachini I am representing on behalf of my company named Vortex. We are very interested in your innovation and like to discuss more with you.\n please contatc us when you are availabale. Regards.','Vortex.org','2020-07-18'),
(7,'kamal',321,'Kamal Perera','0712345678','kamalperera@gmail.com','Hello Mr.Ayendra I am representing on behalf of my company named Vortex. We are very interested in your innovation and like to discuss more with you.\n please contatc us when you are availabale. Regards.','Vortex.org','2020-07-22');
/*!40000 ALTER TABLE `fundrequests` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-20 15:44:07