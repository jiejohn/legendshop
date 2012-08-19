/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;

CREATE TABLE `ls_tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `sort_id` int(11) DEFAULT NULL COMMENT '分类ID',
  `news_category_id` int(11) DEFAULT NULL COMMENT '新闻类别',
  `type` char(1) NOT NULL COMMENT 'P:产品，N：新闻，B:品牌，G:团购产品，A:广告',
  `status` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `user_id` varchar(32) NOT NULL DEFAULT '',
  `user_name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Tag管理';

INSERT INTO `ls_tag` VALUES (1,'1313',1,1,'1',1,NULL,'er','erer');
INSERT INTO `ls_tag` VALUES (2,'13132',33,123,'1',123,NULL,'123','12');

/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
