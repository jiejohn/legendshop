# MySQL-Front 5.1  (Build 4.13)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: 127.0.0.1    Database: legendshop3035
# ------------------------------------------------------
# Server version 5.1.43-community

#
# Source for table ls_cas_application
#

DROP TABLE IF EXISTS `ls_cas_application`;
CREATE TABLE `ls_cas_application` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `app_no` varchar(50) NOT NULL DEFAULT '',
  `app_name` varchar(100) DEFAULT '',
  `descriptions` varchar(255) DEFAULT NULL,
  `service_url` varchar(255) NOT NULL DEFAULT '',
  `service_url_expression` varchar(255) NOT NULL DEFAULT '',
  `status` tinyint(3) NOT NULL DEFAULT '0',
  `ip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='CAS单点登陆应用注册表';

#
# Dumping data for table ls_cas_application
#

LOCK TABLES `ls_cas_application` WRITE;
/*!40000 ALTER TABLE `ls_cas_application` DISABLE KEYS */;
/*!40000 ALTER TABLE `ls_cas_application` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
