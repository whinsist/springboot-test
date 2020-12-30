DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `author` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `create_time` TIMESTAMP ,
  `title` varchar(255) DEFAULT NULL,

  PRIMARY KEY (`id`) USING BTREE
);


#初始化qutarz数据表：quartz-2.3.0.jar!\org\quartz\impl\jdbcjobstore\tables_mysql_innodb.sql


DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `userpwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
);



DROP TABLE IF EXISTS `purchase_plan_item`;
CREATE TABLE `purchase_plan_item`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `userpwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
);