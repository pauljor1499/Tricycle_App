-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tricycle_app
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admins` (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `admin_firstname` varchar(45) DEFAULT NULL,
  `admin_middlename` varchar(45) DEFAULT NULL,
  `admin_lastname` varchar(45) DEFAULT NULL,
  `admin_gender` varchar(45) DEFAULT NULL,
  `admin_address` varchar(45) DEFAULT NULL,
  `admin_phone` varchar(45) DEFAULT NULL,
  `admin_username` varchar(45) DEFAULT NULL,
  `admin_password` varchar(45) DEFAULT NULL,
  `admin_photo` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `admin_firstname_UNIQUE` (`admin_firstname`),
  UNIQUE KEY `admin_middlename_UNIQUE` (`admin_middlename`),
  UNIQUE KEY `admin_lastname_UNIQUE` (`admin_lastname`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` VALUES (1,'Paul Vincent','Lao','Jor','Male','Digos City','091978658655','admin1','admin1','E:\\NetBeansFiles\\Tricycle_App\\saved_images\\admin_images\\1Jor.jpeg');
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deliveries`
--

DROP TABLE IF EXISTS `deliveries`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deliveries` (
  `delivery_id` int NOT NULL AUTO_INCREMENT,
  `order_id` varchar(45) DEFAULT NULL,
  `tricycle_id` varchar(45) DEFAULT NULL,
  `delivery_date` varchar(45) DEFAULT NULL,
  `delivery_time` varchar(45) DEFAULT NULL,
  `delivery_location` varchar(45) DEFAULT NULL,
  `delivery_total_payment` varchar(45) DEFAULT NULL,
  `delivery_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`delivery_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deliveries`
--

LOCK TABLES `deliveries` WRITE;
/*!40000 ALTER TABLE `deliveries` DISABLE KEYS */;
INSERT INTO `deliveries` VALUES (1,'1','',NULL,NULL,'Here','20.00','Pending'),(2,'1',NULL,NULL,NULL,'Here','21.00','Dropped');
/*!40000 ALTER TABLE `deliveries` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drivers`
--

DROP TABLE IF EXISTS `drivers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `drivers` (
  `driver_id` int NOT NULL AUTO_INCREMENT,
  `driver_firstname` varchar(45) DEFAULT NULL,
  `driver_middlename` varchar(45) DEFAULT NULL,
  `driver_lastname` varchar(45) DEFAULT NULL,
  `driver_gender` varchar(45) DEFAULT NULL,
  `driver_address` varchar(45) DEFAULT NULL,
  `driver_phone` varchar(45) DEFAULT NULL,
  `driver_username` varchar(45) DEFAULT NULL,
  `driver_password` varchar(45) DEFAULT NULL,
  `driver_photo` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`driver_id`),
  UNIQUE KEY `driver_firstname_UNIQUE` (`driver_firstname`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drivers`
--

LOCK TABLES `drivers` WRITE;
/*!40000 ALTER TABLE `drivers` DISABLE KEYS */;
INSERT INTO `drivers` VALUES (1,'Kyler','Montero','Sarmiento','Male','Davao City','09090909','driver1','driver1','E:\\NetBeansFiles\\Tricycle_App\\saved_images\\driver_images\\1Sarmiento.jpeg'),(11,'Jobert','Secret','Padilla','Male','Digos City','0909090909','driver2','driver2',NULL),(24,'Kaloy','','','','','','','',NULL),(25,'Robert','','','','','','','',NULL),(26,'John',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(27,'Peter',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(28,'Jimuel',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(29,'Robin',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(30,'Kael',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(31,'Harol',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(32,'','','','','','','','',NULL);
/*!40000 ALTER TABLE `drivers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `franchises`
--

DROP TABLE IF EXISTS `franchises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `franchises` (
  `franchise_id` int NOT NULL AUTO_INCREMENT,
  `tricycle_id` varchar(45) DEFAULT NULL,
  `franchise_date_start` varchar(45) DEFAULT NULL,
  `franchise_date_end` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`franchise_id`),
  UNIQUE KEY `tricycle_id_UNIQUE` (`tricycle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `franchises`
--

LOCK TABLES `franchises` WRITE;
/*!40000 ALTER TABLE `franchises` DISABLE KEYS */;
INSERT INTO `franchises` VALUES (3,'1','jan1','feb1');
/*!40000 ALTER TABLE `franchises` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `product_id` varchar(45) DEFAULT NULL,
  `passenger_id` varchar(45) DEFAULT NULL,
  `order_quantity` varchar(45) DEFAULT NULL,
  `order_total_payment` varchar(45) DEFAULT NULL,
  `order_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (97,'15','5','1','45.00','TAKE OUT'),(98,'16','5','1','20.00','TAKE OUT');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passengers`
--

DROP TABLE IF EXISTS `passengers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passengers` (
  `passenger_id` int NOT NULL AUTO_INCREMENT,
  `passenger_firstname` varchar(45) DEFAULT NULL,
  `passenger_middlename` varchar(45) DEFAULT NULL,
  `passenger_lastname` varchar(45) DEFAULT NULL,
  `passenger_gender` varchar(45) DEFAULT NULL,
  `passenger_address` varchar(45) DEFAULT NULL,
  `passenger_phone` varchar(45) DEFAULT NULL,
  `passenger_username` varchar(45) DEFAULT NULL,
  `passenger_password` varchar(45) DEFAULT NULL,
  `passenger_photo` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`passenger_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passengers`
--

LOCK TABLES `passengers` WRITE;
/*!40000 ALTER TABLE `passengers` DISABLE KEYS */;
INSERT INTO `passengers` VALUES (5,'pgr1','pgr1','pgr1','pgr1','pgr1','pgr1','pgr1','pgr1','E:\\NetBeansFiles\\Tricycle_App\\saved_images\\vendor_images\\5vendor4.jpeg');
/*!40000 ALTER TABLE `passengers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `penalties`
--

DROP TABLE IF EXISTS `penalties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `penalties` (
  `penalty_id` int NOT NULL AUTO_INCREMENT,
  `penalty_type` varchar(45) DEFAULT NULL,
  `penalty_charge` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`penalty_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `penalties`
--

LOCK TABLES `penalties` WRITE;
/*!40000 ALTER TABLE `penalties` DISABLE KEYS */;
INSERT INTO `penalties` VALUES (1,'Overpriced','1000'),(2,'Something','2000');
/*!40000 ALTER TABLE `penalties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `store_id` varchar(45) DEFAULT NULL,
  `product_name` varchar(45) DEFAULT NULL,
  `product_type` varchar(45) DEFAULT NULL,
  `product_price` varchar(45) DEFAULT NULL,
  `product_photo` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `product_name_UNIQUE` (`product_name`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'1','Halo-halo','Dessert','50.00','E:\\NetBeansFiles\\Tricycle_App\\saved_images\\product_images\\halo-halo.jpg'),(2,'2','Chicharon','Snacks','15.00',NULL),(3,'1','Burger','Snacks','35.00','E:\\NetBeansFiles\\Tricycle_App\\saved_images\\product_images\\hamburger.jpg'),(4,'1','French fries','Snacks','20.00','E:\\NetBeansFiles\\Tricycle_App\\saved_images\\product_images\\french fries.jpg'),(5,'2','Hawaiian','Snacks','130.00',NULL),(6,'2','Engineer\'s Cut','Snacks','135.00','E:\\NetBeansFiles\\Tricycle_App\\saved_images\\product_images\\pizza'),(7,'3','Chicken Wings','Meal','110.00',NULL),(8,'3','Pork Sisig','Meal','90.00',NULL),(9,'3','Chicken Sisig','Meal','90.00',NULL),(15,'10','Buko Juice','Dessert','45.00',NULL),(16,'10','Pinipig','Dessert','20.00',NULL),(17,'10','asd','asd','asd',NULL),(18,'10','aasdad','asdad','asad',NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stores`
--

DROP TABLE IF EXISTS `stores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stores` (
  `store_id` int NOT NULL AUTO_INCREMENT,
  `vendor_id` varchar(45) DEFAULT NULL,
  `store_name` varchar(45) DEFAULT NULL,
  `store_address` varchar(45) DEFAULT NULL,
  `store_phone` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`store_id`),
  UNIQUE KEY `store_name_UNIQUE` (`store_name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stores`
--

LOCK TABLES `stores` WRITE;
/*!40000 ALTER TABLE `stores` DISABLE KEYS */;
INSERT INTO `stores` VALUES (1,'1','Jollibee','Rizal Avenue','223-1236'),(2,'2','Greenwhich','Roxas Extension','224-2623'),(3,'3','Mang Inasal','Rizal Avenue','132-1244'),(10,'5','Balong Store','Jumao-as Street','090909');
/*!40000 ALTER TABLE `stores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tricycles`
--

DROP TABLE IF EXISTS `tricycles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tricycles` (
  `tricycle_id` int NOT NULL AUTO_INCREMENT,
  `franchise_id` varchar(45) DEFAULT NULL,
  `driver_id` varchar(45) DEFAULT NULL,
  `tricycle_color` varchar(45) DEFAULT NULL,
  `tricycle_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`tricycle_id`),
  UNIQUE KEY `driver_id_UNIQUE` (`driver_id`),
  UNIQUE KEY `franchise_id_UNIQUE` (`franchise_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tricycles`
--

LOCK TABLES `tricycles` WRITE;
/*!40000 ALTER TABLE `tricycles` DISABLE KEYS */;
INSERT INTO `tricycles` VALUES (1,'1','1','Blue','Disable'),(2,'2','11','Yellow','Enable');
/*!40000 ALTER TABLE `tricycles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trips`
--

DROP TABLE IF EXISTS `trips`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trips` (
  `trip_id` int NOT NULL AUTO_INCREMENT,
  `tricycle_id` varchar(45) DEFAULT NULL,
  `passenger_id` varchar(45) DEFAULT NULL,
  `trip_date` varchar(45) DEFAULT NULL,
  `trip_time` varchar(45) DEFAULT NULL,
  `trip_start` varchar(45) DEFAULT NULL,
  `trip_end` varchar(45) DEFAULT NULL,
  `trip_weather` varchar(45) DEFAULT NULL,
  `trip_traffic` varchar(45) DEFAULT NULL,
  `trip_distance` varchar(45) DEFAULT NULL,
  `trip_estimated_time` varchar(45) DEFAULT NULL,
  `trip_total_payment` varchar(45) DEFAULT NULL,
  `trip_status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`trip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trips`
--

LOCK TABLES `trips` WRITE;
/*!40000 ALTER TABLE `trips` DISABLE KEYS */;
INSERT INTO `trips` VALUES (1,'1','4','October','12:00PM','Here','There',NULL,NULL,NULL,NULL,'12.00','Delivered'),(3,'1','5','September','09:00PM','Here2','There2',NULL,NULL,NULL,NULL,'50.00','Delivered'),(4,'1','5','November','10:00','adads','Thereasd','asdasd','sdad','sdad','asdadsad','100.00','Delivered'),(47,'1','5','02 November 2021','01:45:14 PM','asdadad','sadad','Windy','Moderate','1.6 km','8 min','13','Cancelled'),(48,NULL,'5','02 November 2021','08:45:47 PM','asdas','asd','Sunny','Light','1.2 km','5 min','10.5','Cancelled'),(49,NULL,'5','03 November 2021','09:19:25 AM','asd','asd','Stormy','Moderate','1.5 km','7 min','12.5','Cancelled'),(50,NULL,'5','03 November 2021','09:19:41 AM','sdasd','asd','Stormy','Light','1 km','5 min','10.5','Cancelled'),(51,NULL,'5','03 November 2021','09:20:11 AM','asdad','asd','Stormy','Light','1.7 km','8 min','13','Cancelled'),(52,NULL,'5','03 November 2021','09:20:43 AM','sadadas','asdsadd','Rainy','Light','2.1 km','10 min','15','Cancelled'),(53,NULL,'5','03 November 2021','09:21:18 AM','sdfdsfdsfs','dsffsd','Cloudy','Moderate','1.8 km','9 min','14.5','Cancelled'),(54,NULL,'5','03 November 2021','09:22:37 AM','sds','sdf','Rainy','Moderate','2.4 km','11 min','16.5','Cancelled'),(55,NULL,'5','03 November 2021','09:24:11 AM','asdad','ads','Cloudy','Light','0.8 km','2 min','8','Cancelled'),(56,NULL,'5','03 November 2021','09:28:36 AM','sadadsad','asdasddas','Rainy','Moderate','1.4 km','6 min','11','Cancelled'),(57,NULL,'5','03 November 2021','09:29:24 AM','asdadsa','asdsa','Cloudy','Light','1.9 km','9 min','14.5','Cancelled'),(58,NULL,'5','03 November 2021','09:29:49 AM','asdada','sadad','Cloudy','Light','1.6 km','8 min','13','Cancelled'),(59,NULL,'5','03 November 2021','09:31:21 AM','asdadad','asdsda','Rainy','Heavy','2.6 km','12 min','17','Cancelled'),(60,NULL,'5','14 November 2021','09:04:06 PM','asdsad','asdasd','Cloudy','Light','2.6 km','12 min','17','Cancelled'),(61,NULL,'5','15 November 2021','08:47:16 PM','sadsa','dsad','Cloudy','Moderate','1.5 km','7 min','12.5','Waiting');
/*!40000 ALTER TABLE `trips` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendors`
--

DROP TABLE IF EXISTS `vendors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendors` (
  `vendor_id` int NOT NULL AUTO_INCREMENT,
  `vendor_firstname` varchar(45) DEFAULT NULL,
  `vendor_middlename` varchar(45) DEFAULT NULL,
  `vendor_lastname` varchar(45) DEFAULT NULL,
  `vendor_gender` varchar(45) DEFAULT NULL,
  `vendor_address` varchar(45) DEFAULT NULL,
  `vendor_phone` varchar(45) DEFAULT NULL,
  `vendor_username` varchar(45) DEFAULT NULL,
  `vendor_password` varchar(45) DEFAULT NULL,
  `vendor_photo` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`vendor_id`),
  UNIQUE KEY `vendor_firstname_UNIQUE` (`vendor_firstname`),
  UNIQUE KEY `vendor_middlename_UNIQUE` (`vendor_middlename`),
  UNIQUE KEY `vendor_lastname_UNIQUE` (`vendor_lastname`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendors`
--

LOCK TABLES `vendors` WRITE;
/*!40000 ALTER TABLE `vendors` DISABLE KEYS */;
INSERT INTO `vendors` VALUES (1,'v','v','v','v','v','v','v','v',NULL),(2,'v2','v2','v2','v2','v2','v2','v2','v2',NULL),(3,'v3',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'','','','','','','','',NULL),(5,'vendor4','vendor4','vendor4','vendor4','vendor4','vendor4','v4','v4',NULL);
/*!40000 ALTER TABLE `vendors` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-16 20:36:43
