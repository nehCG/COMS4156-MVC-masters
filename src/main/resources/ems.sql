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
INSERT INTO `t_user` VALUES (1, 'admin', '4QrcOUm6Wau+VuBX8g+IPg==', 'Admin', 'admin@starpreschool.com', '000000000', 1, '2023-11-13 00:00:00', '2023-11-13 00:00:00');
INSERT INTO `t_user` VALUES (2, 'director', '4QrcOUm6Wau+VuBX8g+IPg==', 'Education Director', 'director@starpreschool.com', '9375902750', 1, '2023-11-13 00:00:00', '2023-11-13 00:00:00');
INSERT INTO `t_user` VALUES (3, 'leader', '4QrcOUm6Wau+VuBX8g+IPg==', 'Lead Teacher', 'leader@starpreschool.com', '4567218763', 1, '2023-11-13 00:00:00', '2023-11-13 00:00:00');
INSERT INTO `t_user` VALUES (4, 'assistant', '4QrcOUm6Wau+VuBX8g+IPg==', 'Assistant Teacher', 'staff@starpreschool.com', '2315738295', 1, '2023-11-13 00:00:00', '2023-11-13 00:00:00');
INSERT INTO `t_user` VALUES (5, 'TA', '4QrcOUm6Wau+VuBX8g+IPg==', 'Teaching Assistant', 'TA@starpreschool.com', '5343859012', 1, '2023-11-13 00:00:00', '2023-11-13 00:00:00');


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
INSERT INTO `t_role` VALUES (2, 'director', 'director', '2023-11-13 00:00:00', '2023-11-13 00:00:00', 1);
INSERT INTO `t_role` VALUES (3, 'leader', 'leader', '2023-11-13 00:00:00', '2023-11-13 00:00:00', 1);
INSERT INTO `t_role` VALUES (4, 'staff', 'staff', '2023-11-13 00:00:00', '2023-11-13 00:00:00', 1);
INSERT INTO `t_role` VALUES (5, 'TA', 'TA', '2023-11-13 00:00:00', '2023-11-13 00:00:00', 1);


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
INSERT INTO `t_user_role` VALUES (2, 2, 2, '2023-11-13 00:00:00', '2023-11-13 00:00:00');
INSERT INTO `t_user_role` VALUES (3, 3, 3, '2023-11-13 00:00:00', '2023-11-13 00:00:00');
INSERT INTO `t_user_role` VALUES (4, 4, 4, '2023-11-13 00:00:00', '2023-11-13 00:00:00');
INSERT INTO `t_user_role` VALUES (5, 5, 5, '2023-11-13 00:00:00', '2023-11-13 00:00:00');


-- ----------------------------
-- Table structure for t_module
-- ----------------------------
DROP TABLE IF EXISTS `t_module`;
CREATE TABLE `t_module`  (
    `id` int(0) NOT NULL AUTO_INCREMENT,
    `module_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'module name',
    `module_style` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'module style',
    `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url',
    `parent_id` int(0) NULL DEFAULT NULL,
    `parent_opt_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `grade` int(0) NULL DEFAULT NULL COMMENT 'grade',
    `opt_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'opt value',
    `orders` int(0) NULL DEFAULT NULL,
    `is_valid` tinyint(0) NULL DEFAULT NULL,
    `create_date` datetime(0) NULL DEFAULT NULL,
    `update_date` datetime(0) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_module
-- ----------------------------
INSERT INTO `t_module` VALUES (1, 'Shared Space', '', '#', -1, NULL, 0, '10', 1, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (2, 'Announcement', '', 'announcement/index', 1, NULL, 1, '1010', 1, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (3, 'Search', '', '#', 2, NULL, 2, '101001', 2, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (4, 'Add', '', '#', 2, NULL, 2, '101002', 2, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (5, 'Delete', '', '#', 2, NULL, 2, '101003', 3, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (6, 'Management', '', '#', -1, NULL, 0, '20', 6, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (7, 'User Mgmt', '', 'user/index', 6, NULL, 1, '2010', NULL, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (8, 'Role Mgmt', '', 'role/index', 6, NULL, 1, '2020', NULL, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (9, 'Add', '', NULL, 7, NULL, 2, '201001', NULL, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (10, 'Search', '', NULL, 7, NULL, 2, '201002', NULL, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (11, 'Update', '', NULL, 7, NULL, 2, '201003', NULL, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (12, 'Delete', '', NULL, 7, NULL, 2, '201004', NULL, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (13, 'Add', '', NULL, 8, NULL, 2, '202001', NULL, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (14, 'Search', '', NULL, 8, NULL, 2, '202002', NULL, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (15, 'Update', '', NULL, 8, NULL, 2, '202003', NULL, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (16, 'Delete', '', NULL, 8, NULL, 2, '202004', NULL, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (17, 'Class One', '', '#', -1, NULL, 0, '30', 1, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (18, 'Discussion Board', '', '#', 17, NULL, 0, '3010', 1, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (19, 'Activities', '', '#', 17, NULL, 0, '3020', 1, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (20, 'Class Two', '', '#', -1, NULL, 0, '40', 1, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (21, 'Discussion Board', '', '#', 20, NULL, 0, '4010', 1, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (22, 'Activities', '', '#', 20, NULL, 0, '4020', 1, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (23, 'Class Three', '', '#', -1, NULL, 0, '50', 1, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (24, 'Discussion Board', '', '#', 23, NULL, 0, '5010', 1, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (25, 'Activities', '', '#', 23, NULL, 0, '5020', 1, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (26, 'Playground', '', '#', -1, NULL, 0, '60', 1, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (27, 'Games', '', '#', 26, NULL, 0, '6010', 1, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_module` VALUES (28, 'Activities', '', '#', 26, NULL, 0, '6020', 1, 1, '2023-11-24 00:00:00', '2023-11-24 00:00:00');


-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
    `id` int(0) NOT NULL AUTO_INCREMENT,
    `role_id` int(0) NULL DEFAULT NULL COMMENT 'role id',
    `module_id` int(0) NULL DEFAULT NULL COMMENT 'module id',
    `acl_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'acl value',
    `create_date` datetime(0) NULL DEFAULT NULL,
    `update_date` datetime(0) NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES (1, 1, 1, '10', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (2, 1, 2, '1010', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (3, 1, 3, '101001', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (4, 1, 4, '101002', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (5, 1, 5, '101003', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (6, 1, 6, '20', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (7, 1, 7, '2010', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (8, 1, 9, '201001', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (9, 1, 10, '201002', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (10, 1, 11, '201003', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (11, 1, 12, '201004', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (12, 1, 8, '2020', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (13, 1, 13, '202001', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (14, 1, 14, '202002', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (15, 1, 15, '202003', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (16, 1, 16, '202004', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (17, 1, 17, '30', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (18, 1, 18, '3010', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (19, 1, 19, '3020', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (20, 1, 20, '40', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (21, 1, 21, '4010', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (22, 1, 22, '4020', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (23, 1, 23, '50', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (24, 1, 24, '5010', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (25, 1, 25, '5020', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (26, 1, 26, '60', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (27, 1, 27, '6010', '2023-11-24 00:00:00', '2023-11-24 00:00:00');
INSERT INTO `t_permission` VALUES (28, 1, 28, '6020', '2023-11-24 00:00:00', '2023-11-24 00:00:00');