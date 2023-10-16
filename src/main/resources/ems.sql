# CREATE SCHEMA ems;

USE ems;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
    `id` int NOT NULL AUTO_INCREMENT,
    `user_name` varchar(20) DEFAULT NULL,
    `user_pwd` varchar(100) DEFAULT NULL,
    `true_name` varchar(20) DEFAULT NULL,
    `email` varchar(30) DEFAULT NULL,
    `phone` varchar(20) DEFAULT NULL,
    `is_valid` int(0) NULL DEFAULT 1,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', '4QrcOUm6Wau+VuBX8g+IPg==', 'admin', 'admin@ems.com', '1111111111', 1);




-- ----------------------------
-- Table structure for t_sharedSpace
-- ----------------------------
DROP TABLE IF EXISTS `t_sharedSpace`;

CREATE TABLE `t_sharedSpace` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `uid` int DEFAULT NULL REFERENCES t_user(id),
                          `last_modified_by` int DEFAULT NULL REFERENCES t_user(id),
                          `content` varchar(200) DEFAULT NULL,
                          `created_time` timestamp DEFAULT NULL,
                          `modified_time` timestamp DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_sharedSpace` VALUES (1, 1, 1, 'First shared space!', TIMESTAMP('2022-05-08 14:30:45'), TIMESTAMP('2022-05-08 14:30:45'));