CREATE TABLE `t_users` (
  `id` bigint(20) NOT NULL,
  `name` varchar(10) NOT NULL,
  `time` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;

CREATE TABLE `t_order_0` (
  `id` bigint(20) NOT NULL,
  `name` varchar(10) NOT NULL,
  `time` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;

CREATE TABLE `t_order_1` (
  `id` bigint(20) NOT NULL,
  `name` varchar(10) NOT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB ;

