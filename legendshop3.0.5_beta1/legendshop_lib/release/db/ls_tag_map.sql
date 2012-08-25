/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;

CREATE TABLE `ls_tag_map` (
  `tag_map_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tag_id` int(11) NOT NULL,
  `refer_id` int(11) NOT NULL,
  `type` char(1) NOT NULL COMMENT 'P:产品，N：新闻，B:品牌，G:团购产品，A:广告',
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`tag_map_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='挑选的产品和内容块的对应关系';


/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
