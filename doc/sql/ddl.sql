CREATE TABLE `t_message` (
  `id` bigint(20) NOT NULL,
  `title` varchar(50) DEFAULT NULL COMMENT '标题',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `type` tinyint(4) DEFAULT NULL COMMENT '1=通知，2=私信',
  `state` tinyint(4) DEFAULT NULL COMMENT '0=未读，1=已读',
  `create_time` datetime DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息表';