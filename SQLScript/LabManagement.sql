-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 2020-10-17 08:38:21
-- 服务器版本： 5.5.62-log
-- PHP Version: 7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `LabManagement`
--

-- --------------------------------------------------------

--
-- 表的结构 `tb_bill_info`
--

CREATE TABLE `tb_bill_info` (
  `bill_id` int(11) NOT NULL COMMENT '主键',
  `bill_money` float(10,2) DEFAULT NULL COMMENT '金额',
  `bill_type` int(1) NOT NULL COMMENT '类型 1 收入 2 支出',
  `bill_comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `bill_time` date NOT NULL COMMENT '交易时间',
  `bill_balance` float(10,2) DEFAULT NULL COMMENT '余额'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_bill_info`
--

INSERT INTO `tb_bill_info` (`bill_id`, `bill_money`, `bill_type`, `bill_comment`, `bill_time`, `bill_balance`) VALUES
(1, 480.00, 1, 'IOT实验室年初缴费', '2020-09-10', 480.00),
(5, -458.00, 2, '办网', '2020-09-12', 22.00),
(6, -15.00, 2, '实验室新钥匙', '2020-09-12', 7.00),
(11, -9.50, 2, 'oj以及实验室管理系统服务器', '2020-10-04', -2.50),
(35, -200.00, 2, '电信宽带缴费', '2020-10-15', -202.50);

-- --------------------------------------------------------

--
-- 表的结构 `tb_device_info`
--

CREATE TABLE `tb_device_info` (
  `device_id` int(11) NOT NULL,
  `device_name` varchar(50) NOT NULL,
  `device_type` varchar(20) NOT NULL,
  `device_number` int(11) DEFAULT NULL,
  `device_comment` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_device_info`
--

INSERT INTO `tb_device_info` (`device_id`, `device_name`, `device_type`, `device_number`, `device_comment`) VALUES
(9, 'WIFI/蓝牙/LoRa/433 ', '网关', 1, '贵重物品（代管人：余森林）'),
(10, 'AM2305', '温湿度传感器', 2, '贵重物品（代管人：余森林）'),
(11, 'PM-G7', '激光颗粒物传感器', 5, '贵重物品（代管人：余森林）'),
(12, 'GM1708', '电磁传感器', 4, '非贵重'),
(13, 'GY-39', '四合一气象模块', 5, '贵重物品（代管人：余森林）'),
(14, 'ATK-LoRa-V2.0', 'LoRa终端模块', 3, '非贵重'),
(15, 'WH-NB73', 'NB-IOT模块', 1, '贵重物品（代管人：余森林）'),
(16, 'AIR NANO', '微型多旋翼飞行器', 1, '贵重物品（代管人：余森林）'),
(18, 'ARDUINO', '开发板', 2, '贵重物品（代管人：余森林）'),
(20, 'FriendlyARM 3G model', '3G ', 1, '贵重物品（代管人：余森林）'),
(21, 'YZC-1B(20KG)', '重力传感器', 1, '贵重物品（代管人：余森林）'),
(22, '音响', '音响', 2, '非贵重'),
(26, '模块套组', '传感器模块', 6, '非贵重'),
(27, 'ATGM332D', 'GPS/北斗 模块', 1, '贵重物品（代管人：余森林）');

-- --------------------------------------------------------

--
-- 表的结构 `tb_device_lend`
--

CREATE TABLE `tb_device_lend` (
  `lend_people` varchar(20) DEFAULT NULL COMMENT '借用人',
  `lend_device` varchar(100) DEFAULT NULL COMMENT '设备名称',
  `lend_time` date DEFAULT NULL COMMENT '借用时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `tb_match`
--

CREATE TABLE `tb_match` (
  `match_id` int(11) NOT NULL COMMENT '比赛Id',
  `match_name` varchar(50) NOT NULL COMMENT '比赛名称',
  `match_name_format` varchar(50) NOT NULL COMMENT '格式化后的比赛名称(代称)',
  `match_time` date DEFAULT NULL COMMENT '比赛日期'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_match`
--

INSERT INTO `tb_match` (`match_id`, `match_name`, `match_name_format`, `match_time`) VALUES
(1, 'TI杯全国大学生电子设计竞赛', '10010', '2020-10-10'),
(2, 'test', '20201014000000', '2020-10-14');

-- --------------------------------------------------------

--
-- 表的结构 `tb_match_bill_info_10010`
--

CREATE TABLE `tb_match_bill_info_10010` (
  `bill_id` int(11) NOT NULL COMMENT '主键',
  `bill_money` float(10,2) DEFAULT NULL COMMENT '金额',
  `bill_type` int(1) NOT NULL COMMENT '类型 1 收入 2 支出',
  `bill_comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `bill_time` date NOT NULL COMMENT '交易时间',
  `bill_balance` float(10,2) DEFAULT NULL COMMENT '余额'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_match_bill_info_10010`
--

INSERT INTO `tb_match_bill_info_10010` (`bill_id`, `bill_money`, `bill_type`, `bill_comment`, `bill_time`, `bill_balance`) VALUES
(1, 400.00, 1, '竞赛缴费', '2020-09-29', 400.00),
(5, 400.00, 1, '学校提供经费', '2020-09-16', 800.00),
(9, -17.40, 2, 'TB6612FNG电机驱动*3', '2020-10-07', 793.11),
(10, -25.50, 2, '二位云台', '2020-10-07', 767.61),
(11, -50.80, 2, '伺服电机*6', '2020-10-07', 716.81),
(12, -199.00, 2, '四驱小车*1', '2020-10-07', 517.81),
(13, -11.99, 2, 'TB6612FNG电机驱动*2', '2020-10-07', 505.82),
(14, -51.00, 2, '耗材', '2020-10-07', 454.82),
(15, -10.51, 2, '电压模块', '2020-10-07', 433.80),
(16, -23.00, 2, '开发板快递费', '2020-10-08', 410.80);

-- --------------------------------------------------------

--
-- 表的结构 `tb_match_bill_info_20201014000000`
--

CREATE TABLE `tb_match_bill_info_20201014000000` (
  `bill_id` int(11) NOT NULL COMMENT '主键',
  `bill_money` float(10,2) DEFAULT NULL COMMENT '金额',
  `bill_type` int(1) NOT NULL COMMENT '类型 1 收入 2 支出',
  `bill_comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `bill_time` date NOT NULL COMMENT '交易时间',
  `bill_balance` float(10,2) DEFAULT NULL COMMENT '余额'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_match_bill_info_20201014000000`
--

INSERT INTO `tb_match_bill_info_20201014000000` (`bill_id`, `bill_money`, `bill_type`, `bill_comment`, `bill_time`, `bill_balance`) VALUES
(1, 0.00, 1, '数据表初始化', '2020-10-14', 0.00);

-- --------------------------------------------------------

--
-- 表的结构 `tb_role`
--

CREATE TABLE `tb_role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(20) DEFAULT NULL,
  `role_type` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_role`
--

INSERT INTO `tb_role` (`role_id`, `role_name`, `role_type`) VALUES
(1, '管理员', '1'),
(2, '普通用户', '2'),
(3, '领队', '3'),
(4, '会计', '4');

-- --------------------------------------------------------

--
-- 表的结构 `tb_role_admin`
--

CREATE TABLE `tb_role_admin` (
  `admin_role_id` int(11) NOT NULL,
  `admin_role_type` varchar(20) NOT NULL,
  `admin_role_device_controller` varchar(10) NOT NULL,
  `admin_role_economics_management` varchar(10) NOT NULL,
  `admin_role_member_management` varchar(10) NOT NULL,
  `admin_role_personal_change` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_role_admin`
--

INSERT INTO `tb_role_admin` (`admin_role_id`, `admin_role_type`, `admin_role_device_controller`, `admin_role_economics_management`, `admin_role_member_management`, `admin_role_personal_change`) VALUES
(1, '1', '设备管理', '账目管理', '人员管理', '个人信息更改');

-- --------------------------------------------------------

--
-- 表的结构 `tb_role_member`
--

CREATE TABLE `tb_role_member` (
  `member_role_id` int(11) NOT NULL,
  `member_role_type` varchar(20) NOT NULL,
  `member_role_device_controller` varchar(10) NOT NULL,
  `member_role_personal_change` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_role_member`
--

INSERT INTO `tb_role_member` (`member_role_id`, `member_role_type`, `member_role_device_controller`, `member_role_personal_change`) VALUES
(1, '2', '设备管理', '个人信息更改');

-- --------------------------------------------------------

--
-- 表的结构 `tb_role_module`
--

CREATE TABLE `tb_role_module` (
  `role_module_id` varchar(5) NOT NULL,
  `role_module_type` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_role_module`
--

INSERT INTO `tb_role_module` (`role_module_id`, `role_module_type`) VALUES
('1', '设备管理'),
('1', '账目管理'),
('1', '人员管理'),
('1', '个人信息更改'),
('2', '设备管理'),
('2', '个人信息更改');

-- --------------------------------------------------------

--
-- 表的结构 `tb_user`
--

CREATE TABLE `tb_user` (
  `user_name` varchar(20) NOT NULL,
  `user_password` varchar(20) NOT NULL,
  `user_id_number` varchar(20) DEFAULT NULL,
  `user_phone` varchar(20) DEFAULT NULL,
  `user_class_name` varchar(20) DEFAULT NULL,
  `user_limit` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `tb_user`
--

INSERT INTO `tb_user` (`user_name`, `user_password`, `user_id_number`, `user_phone`, `user_class_name`, `user_limit`) VALUES
('1106649325@qq.com', 'dyk159357123', '20181117026', '17362942385', '物联网hopu1801', '2'),
('admin', 'iot123456.', '00000000000', '18164076193', 'IOT实验室', '1'),
('dh', '20000711dh', NULL, NULL, NULL, '2'),
('KroFin', '123456', '20171117052', '17340526910', '物联网hopu1701班', '4'),
('smile', '199866cb', NULL, NULL, NULL, '2'),
('一块五', 'xgt13035', NULL, NULL, NULL, '2'),
('菜菜酱*', 'dsl661100', NULL, NULL, NULL, '2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_bill_info`
--
ALTER TABLE `tb_bill_info`
  ADD PRIMARY KEY (`bill_id`);

--
-- Indexes for table `tb_device_info`
--
ALTER TABLE `tb_device_info`
  ADD PRIMARY KEY (`device_id`);

--
-- Indexes for table `tb_match`
--
ALTER TABLE `tb_match`
  ADD PRIMARY KEY (`match_id`);

--
-- Indexes for table `tb_match_bill_info_10010`
--
ALTER TABLE `tb_match_bill_info_10010`
  ADD PRIMARY KEY (`bill_id`);

--
-- Indexes for table `tb_match_bill_info_20201014000000`
--
ALTER TABLE `tb_match_bill_info_20201014000000`
  ADD PRIMARY KEY (`bill_id`);

--
-- Indexes for table `tb_role`
--
ALTER TABLE `tb_role`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `tb_role_admin`
--
ALTER TABLE `tb_role_admin`
  ADD PRIMARY KEY (`admin_role_id`);

--
-- Indexes for table `tb_role_member`
--
ALTER TABLE `tb_role_member`
  ADD PRIMARY KEY (`member_role_id`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`user_name`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `tb_bill_info`
--
ALTER TABLE `tb_bill_info`
  MODIFY `bill_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键', AUTO_INCREMENT=36;

--
-- 使用表AUTO_INCREMENT `tb_device_info`
--
ALTER TABLE `tb_device_info`
  MODIFY `device_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- 使用表AUTO_INCREMENT `tb_match`
--
ALTER TABLE `tb_match`
  MODIFY `match_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '比赛Id', AUTO_INCREMENT=8;

--
-- 使用表AUTO_INCREMENT `tb_match_bill_info_10010`
--
ALTER TABLE `tb_match_bill_info_10010`
  MODIFY `bill_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键', AUTO_INCREMENT=18;

--
-- 使用表AUTO_INCREMENT `tb_match_bill_info_20201014000000`
--
ALTER TABLE `tb_match_bill_info_20201014000000`
  MODIFY `bill_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键', AUTO_INCREMENT=3;

--
-- 使用表AUTO_INCREMENT `tb_role`
--
ALTER TABLE `tb_role`
  MODIFY `role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- 使用表AUTO_INCREMENT `tb_role_admin`
--
ALTER TABLE `tb_role_admin`
  MODIFY `admin_role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- 使用表AUTO_INCREMENT `tb_role_member`
--
ALTER TABLE `tb_role_member`
  MODIFY `member_role_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
