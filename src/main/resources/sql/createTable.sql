-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: schoolroomsys
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `class_info`
--

DROP TABLE IF EXISTS `class_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_info` (
  `college_id` varchar(32) NOT NULL COMMENT '学院ID',
  `college_name` varchar(50) NOT NULL COMMENT '学院名称',
  `profession` varchar(50) NOT NULL COMMENT '专业名称',
  `class_name` varchar(20) NOT NULL COMMENT '班级名称，如：21-1',
  `teacher_id` varchar(32) NOT NULL COMMENT '分管教师ID',
  `teacher_name` varchar(30) NOT NULL COMMENT '分管教师姓名',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  KEY `idx_college_id` (`college_id`),
  KEY `idx_teacher_id` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='班级信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `college_info`
--

DROP TABLE IF EXISTS `college_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `college_info` (
  `college_id` varchar(32) DEFAULT NULL COMMENT '学院id',
  `college_name` varchar(32) DEFAULT NULL COMMENT '学院名字'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='学院信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `house_master`
--

DROP TABLE IF EXISTS `house_master`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `house_master` (
  `hm_id` varchar(32) DEFAULT NULL COMMENT '宿管id，以999开头，例如999021001，999代表宿管或维修人员，021代表21号宿舍楼，001的第一个0代表是宿管，01代表是宿管编号01',
  `hm_name` varchar(32) DEFAULT NULL COMMENT '宿管名称',
  `hm_sex` varchar(32) DEFAULT NULL COMMENT '性别',
  `hm_phone` varchar(32) DEFAULT NULL COMMENT '联系方式',
  `build_id` varchar(32) DEFAULT NULL COMMENT '所处宿舍楼'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='宿管信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `leave_approval`
--

DROP TABLE IF EXISTS `leave_approval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `leave_approval` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `student_id` varchar(32) NOT NULL COMMENT '申请学生ID',
  `start_time` datetime NOT NULL COMMENT '请假开始时间',
  `end_time` datetime NOT NULL COMMENT '请假结束时间',
  `reason` text NOT NULL COMMENT '请假原因',
  `destination` varchar(255) NOT NULL COMMENT '请假去向',
  `contact_phone` varchar(32) NOT NULL COMMENT '紧急联系电话',
  `teacher_id` varchar(32) NOT NULL COMMENT '辅导员ID',
  `status` varchar(2) DEFAULT '0' COMMENT '审批状态 0-待审批 1-已通过 2-已驳回',
  `opinion` text COMMENT '审批意见',
  `approve_time` datetime DEFAULT NULL COMMENT '审批时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='请假审批表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `repair_approval`
--

DROP TABLE IF EXISTS `repair_approval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repair_approval` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `student_id` varchar(32) NOT NULL COMMENT '申请学生ID',
  `repair_type` varchar(2) NOT NULL COMMENT '报修类型 1-宿舍物品 2-公共物品',
  `room_id` varchar(32) DEFAULT NULL COMMENT '宿舍ID（宿舍物品报修必填）',
  `public_area` varchar(255) DEFAULT NULL COMMENT '公共区域（公共物品报修必填）',
  `item_name` varchar(255) NOT NULL COMMENT '物品名称',
  `description` text NOT NULL COMMENT '问题描述',
  `images` text COMMENT '图片地址，多个用逗号分隔',
  `hm_id` varchar(32) DEFAULT NULL COMMENT '宿管ID',
  `hm_status` varchar(2) DEFAULT '0' COMMENT '宿管审批状态 0-待审批 1-已通过 2-已驳回',
  `hm_opinion` text COMMENT '宿管审批意见',
  `hm_time` datetime DEFAULT NULL COMMENT '宿管审批时间',
  `rp_id` varchar(32) DEFAULT NULL COMMENT '维修人员ID',
  `rp_status` varchar(2) DEFAULT '0' COMMENT '维修状态 0-待维修 1-已完成 2-已驳回',
  `rp_opinion` text COMMENT '维修意见',
  `rp_time` datetime DEFAULT NULL COMMENT '维修完成时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='报修审批表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `repair_people`
--

DROP TABLE IF EXISTS `repair_people`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repair_people` (
  `rp_id` varchar(32) DEFAULT NULL COMMENT '维修人员id，以999开头，例如999001101，按顺序999代表维修人员或宿管，001代表哪个校区，1梁园2睢阳，1代表是维修人员，01代表编号01',
  `rp_name` varchar(32) DEFAULT NULL COMMENT '维修人员姓名',
  `rp_sex` varchar(32) DEFAULT NULL COMMENT '性别',
  `rp_phone` varchar(32) DEFAULT NULL COMMENT '联系方式',
  `campus` varchar(32) DEFAULT NULL COMMENT '所在校区 1梁园 2睢阳'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='维修人员信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `room_build`
--

DROP TABLE IF EXISTS `room_build`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_build` (
  `build_id` varchar(32) DEFAULT NULL COMMENT '宿舍楼id',
  `build_name` varchar(32) DEFAULT NULL COMMENT '宿舍楼名称',
  `hm_id` varchar(32) DEFAULT NULL COMMENT 'house_master 宿管id',
  `campus` varchar(32) DEFAULT NULL COMMENT '所处校区 1梁园校区 2睢阳校区',
  `details_table` varchar(32) DEFAULT NULL COMMENT '宿舍楼详细信息表',
  `layer_number` varchar(32) DEFAULT NULL COMMENT '宿舍楼层数',
  `total_room_num` varchar(32) DEFAULT NULL COMMENT '宿舍间总数',
  `usable_room_num` varchar(32) DEFAULT NULL COMMENT '可用宿舍间数目',
  `build_type` varchar(32) DEFAULT NULL COMMENT '宿舍楼男女寝 1男寝 2女寝 3混合',
  `is_used` varchar(32) DEFAULT NULL COMMENT '是否处于正常使用 1使用 0暂停使用',
  `remark` varchar(32) DEFAULT NULL COMMENT '备注信息'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='宿舍楼信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `room_build_details`
--

DROP TABLE IF EXISTS `room_build_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_build_details` (
  `build_id` varchar(32) DEFAULT NULL COMMENT '宿舍楼id',
  `build_name` varchar(32) DEFAULT NULL COMMENT '宿舍楼姓名',
  `layer_number` varchar(32) DEFAULT NULL COMMENT '宿舍楼层数',
  `room_id` varchar(32) DEFAULT NULL COMMENT '房间号id',
  `is_mixed` varchar(32) DEFAULT NULL COMMENT '是否混寝 1混寝 2不混',
  `college_ids` varchar(32) DEFAULT NULL COMMENT '入住学生的学院id，如果是混寝，用英文逗号分隔',
  `manage_teacher_id` varchar(32) DEFAULT NULL COMMENT '所属老师id',
  `room_type` varchar(32) DEFAULT NULL COMMENT '几人寝',
  `status` varchar(2) DEFAULT '1' COMMENT '使用状态 1-正常使用 0-暂停使用'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='宿舍信息详情表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `room_info`
--

DROP TABLE IF EXISTS `room_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_info` (
  `room_id` varchar(32) DEFAULT NULL COMMENT '宿舍号',
  `build_id` varchar(32) DEFAULT NULL COMMENT '宿舍楼id',
  `stu_id` varchar(32) DEFAULT NULL COMMENT '学生id',
  `stu_name` varchar(32) DEFAULT NULL COMMENT '学生姓名',
  `bed_no` varchar(32) DEFAULT NULL COMMENT '床位号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='宿舍信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `school_user`
--

DROP TABLE IF EXISTS `school_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `school_user` (
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id，学校老师由00开头，学院老师由xx开头，xx为所属学院的id',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `pass_word` varchar(255) DEFAULT NULL COMMENT '密码',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(32) DEFAULT NULL COMMENT '联系方式',
  `level` varchar(32) DEFAULT NULL COMMENT '老师级别，用于区分权限等级，如学校和学院老师 0学校  其他为学院',
  `is_used` varchar(32) DEFAULT NULL COMMENT '是否正常启用 1启用 0禁用'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='老师用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_info`
--

DROP TABLE IF EXISTS `student_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_info` (
  `stu_id` varchar(32) DEFAULT NULL COMMENT '学生id',
  `stu_name` varchar(32) DEFAULT NULL COMMENT '学生姓名',
  `sex` varchar(32) DEFAULT NULL COMMENT '性别',
  `college` varchar(32) DEFAULT NULL COMMENT '所属学院',
  `profession` varchar(32) DEFAULT NULL COMMENT '专业',
  `class_room` varchar(32) DEFAULT NULL COMMENT '班级',
  `teacher_id` varchar(32) DEFAULT NULL COMMENT '所属老师id',
  `teacher_name` varchar(32) DEFAULT NULL COMMENT '所属老师名字',
  `build_id` varchar(32) DEFAULT NULL COMMENT '所属宿舍楼id',
  `room_id` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='学生信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `student_user`
--

DROP TABLE IF EXISTS `student_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_user` (
  `user_id` varchar(32) DEFAULT NULL,
  `user_name` varchar(32) DEFAULT NULL,
  `pass_word` varchar(255) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `phone` varchar(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='学生用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `teacher_info`
--

DROP TABLE IF EXISTS `teacher_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher_info` (
  `teacher_id` varchar(32) DEFAULT NULL COMMENT '老师id',
  `teacher_name` varchar(32) DEFAULT NULL COMMENT '老师姓名',
  `sex` varchar(32) DEFAULT NULL COMMENT '性别',
  `name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `college` varchar(32) DEFAULT NULL COMMENT '所属学院'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='老师信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `urge_record`
--

DROP TABLE IF EXISTS `urge_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `urge_record` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `approval_id` varchar(32) NOT NULL COMMENT '审批ID',
  `approval_type` varchar(2) NOT NULL COMMENT '审批类型 1-报修 2-请假',
  `urge_type` varchar(2) NOT NULL COMMENT '催促类型 1-学生催促宿管 2-学生催促维修 3-宿管催促维修',
  `urge_content` text NOT NULL COMMENT '催促内容',
  `from_id` varchar(32) NOT NULL COMMENT '发起人ID',
  `to_id` varchar(32) NOT NULL COMMENT '接收人ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='催促记录表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-28 15:46:33
