DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` TIMESTAMP ,
  `title` varchar(255) DEFAULT NULL,

  PRIMARY KEY (`id`) USING BTREE
);
