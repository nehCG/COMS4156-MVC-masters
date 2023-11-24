# CREATE SCHEMA ems;

USE ems;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user`  (
    `id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'pk',
    `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `user_pwd` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `true_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `is_valid` int(0) NULL DEFAULT 1,
    `create_date` datetime(0) NULL DEFAULT NULL,
    `update_date` datetime(0) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', '4QrcOUm6Wau+VuBX8g+IPg==', 'admin', 'admin@ems.com', '1111111111', 1, '2023-11-13 00:00:00', '2023-11-13 00:00:00');


-- ----------------------------
-- Table structure for t_sharedSpace
-- ----------------------------
DROP TABLE IF EXISTS `t_sharedSpace`;

CREATE TABLE `t_sharedSpace` (
    `id` int NOT NULL AUTO_INCREMENT,
    `uid` int DEFAULT NULL REFERENCES t_user(id),
    `last_modified_by` int DEFAULT NULL REFERENCES t_user(id),
    `subject` varchar(50) DEFAULT NULL,
    `content` varchar(200) DEFAULT NULL,
    `created_time` timestamp DEFAULT NULL,
    `modified_time` timestamp DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sharedSpace
-- ----------------------------
INSERT INTO `t_sharedSpace` VALUES (1, 1, 1, 'First Subject', 'First shared space!', TIMESTAMP('2022-05-08 14:30:45'), TIMESTAMP('2022-05-08 14:30:45'));


-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
    `id` int(0) NOT NULL AUTO_INCREMENT,
    `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `role_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'comment',
    `create_date` datetime(0) NULL DEFAULT NULL,
    `update_date` datetime(0) NULL DEFAULT NULL,
    `is_valid` int(0) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'admin', 'admin', '2023-11-13 00:00:00', '2023-11-13 00:00:00', 1);
INSERT INTO `t_role` VALUES (2, 'manager', 'manager', '2023-11-13 00:00:00', '2023-11-13 00:00:00', 1);
INSERT INTO `t_role` VALUES (3, 'staff', 'staff', '2023-11-13 00:00:00', '2023-11-13 00:00:00', 1);


-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
    `id` int(0) NOT NULL AUTO_INCREMENT,
    `user_id` int(0) NULL DEFAULT NULL,
    `role_id` int(0) NULL DEFAULT NULL,
    `create_date` datetime(0) NULL DEFAULT NULL,
    `update_date` datetime(0) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1, 1, '2023-11-13 00:00:00', '2023-11-13 00:00:00');