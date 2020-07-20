-- liquibase formatted sql

-- changeset chenxt:1
CREATE TABLE IF NOT EXISTS `user`
(
    `id`               bigint(20)  NOT NULL COMMENT '编号 - 雪花',
    `phone`            varchar(30) NOT NULL DEFAULT '' COMMENT '手机号码',
    `email`            varchar(50) NOT NULL DEFAULT '' COMMENT '电子邮箱',
    `password`         varchar(32)          DEFAULT NULL COMMENT '密码',
    `nick_name`        varchar(128)         DEFAULT NULL COMMENT '昵称',
    `nick_name_pinyin` varchar(512)         DEFAULT NULL COMMENT '昵称拼音',
    `avatar`           varchar(3072)        DEFAULT NULL COMMENT '头像',
    `sex`              int(11)              DEFAULT '9' COMMENT '性别',
    `bio`              varchar(512)         DEFAULT '' COMMENT '个性签名',
    `birthday`         date        NOT NULL COMMENT '生日',
    `constellation`    varchar(128)         DEFAULT '' COMMENT '星座',
    `province`         varchar(255)         DEFAULT '' COMMENT '省份',
    `city`             varchar(255)         DEFAULT '' COMMENT '城市',
    `district`         varchar(255)         DEFAULT '' COMMENT '区域',
    `addr`             varchar(255)         DEFAULT '' COMMENT '地址',
    `real_name`        varchar(128)         DEFAULT '' COMMENT '真实姓名',
    `is_enable`        tinyint(1)  NOT NULL DEFAULT '1' COMMENT '状态(true:正常,false:封号)',
    `is_deleted`       tinyint(1)  NOT NULL DEFAULT '0' COMMENT '是否已删除。0：未删除；1：已删除；',
    `created_at`       timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`       timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_created_at_id` (`created_at`, `id`),
    KEY `idx_id` (`id`),
    KEY `idx_phone` (`phone`),
    KEY `idx_email` (`email`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户';

CREATE TABLE IF NOT EXISTS `follow`
(
    `id`           bigint(20)   NOT NULL COMMENT '编号 - 雪花',
    `from_user_id` bigint(20)   NOT NULL COMMENT '关注用户编号',
    `to_user_id`   bigint(20)   NULL     DEFAULT NULL COMMENT '被关注用户编号',
    `is_deleted`   tinyint(1)   NOT NULL DEFAULT 0 COMMENT '是否已删除。0：未删除；1：已删除；',
    `created_at`   timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `updated_at`   timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `idx_follow_from_user_id_to_user_id` (`from_user_id`, `to_user_id`) USING BTREE,
    UNIQUE INDEX `idx_follow_to_user_id` (`to_user_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '关注'
  ROW_FORMAT = Dynamic;

insert ignore into user (id,birthday) value(1,CURRENT_TIMESTAMP);

-- changeset chenxt:2
CREATE TABLE IF NOT EXISTS `user_nick_name_index`
(
    `nick_name_index` int(11)     NOT NULL COMMENT '用户昵称index'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC COMMENT ='用户昵称索引';

insert into user_nick_name_index value(0);

-- changeset chenxt:3
CREATE TABLE IF NOT EXISTS `blacklist`
(
    `id`           bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '编号 - 自增',
    `from_user_id` bigint(20)   NOT NULL COMMENT '拉黑用户编号',
    `to_user_id`   bigint(20)   NOT NULL COMMENT '被拉黑用户编号',
    `is_deleted`   tinyint(1)   NOT NULL DEFAULT 0 COMMENT '是否已删除。0：未删除；1：已删除；',
    `created_at`   timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
    `updated_at`   timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `idx_blacklist_from_user_id_to_user_id` (`from_user_id`, `to_user_id`) USING BTREE,
    UNIQUE INDEX `idx_blacklist_to_user_id` (`to_user_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT = '拉黑'
  ROW_FORMAT = Dynamic;

-- changeset chenxt:4
alter table `follow` modify column `to_user_id` bigint (20) NOT NULL COMMENT '关注用户编号';