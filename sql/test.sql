-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.20-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 test 的数据库结构
DROP DATABASE IF EXISTS `test`;
CREATE DATABASE IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `test`;

-- 导出  表 test.admin_detail 结构
DROP TABLE IF EXISTS `admin_detail`;
CREATE TABLE IF NOT EXISTS `admin_detail` (
  `ADMIN_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理员ID',
  `ADMIN_ACCOUNT` varchar(20) NOT NULL COMMENT '管理员账号',
  `ADMIN_PASSWORD` varchar(50) NOT NULL COMMENT '管理员密码',
  `ADMIN_YAN` varchar(50) NOT NULL COMMENT '管理员盐',
  `ADMIN_NAME` varchar(50) DEFAULT NULL COMMENT '管理员姓名',
  PRIMARY KEY (`ADMIN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='管理员信息表';

-- 正在导出表  test.admin_detail 的数据：~1 rows (大约)
DELETE FROM `admin_detail`;
/*!40000 ALTER TABLE `admin_detail` DISABLE KEYS */;
INSERT INTO `admin_detail` (`ADMIN_ID`, `ADMIN_ACCOUNT`, `ADMIN_PASSWORD`, `ADMIN_YAN`, `ADMIN_NAME`) VALUES
	(1, 'zzz1', '31fddc57ab02c35cfa531e8d0fa02c19', 'e7ce0e65255b6f8e9290559451f52077', NULL),
	(2, 'asda', 'baca4bfa792170076bb6fabdd04165c1', '8eaf7a5837f1d9d33d7a7aa04c53b355', NULL);
/*!40000 ALTER TABLE `admin_detail` ENABLE KEYS */;

-- 导出  表 test.goods 结构
DROP TABLE IF EXISTS `goods`;
CREATE TABLE IF NOT EXISTS `goods` (
  `GOODS_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `NAME` varchar(50) NOT NULL COMMENT '商品名称',
  `DESCRIPTION` varchar(255) NOT NULL COMMENT '商品描述',
  `PRICE` double NOT NULL COMMENT '商品价格',
  PRIMARY KEY (`GOODS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- 正在导出表  test.goods 的数据：~1 rows (大约)
DELETE FROM `goods`;
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` (`GOODS_ID`, `NAME`, `DESCRIPTION`, `PRICE`) VALUES
	(1, 'aaa', 'awdw2', 23.6);
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;

-- 导出  表 test.person_detail 结构
DROP TABLE IF EXISTS `person_detail`;
CREATE TABLE IF NOT EXISTS `person_detail` (
  `PERSON_ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `PERSON_ACCOUNT` varchar(20) NOT NULL COMMENT '用户账号',
  `PERSON_PASSWORD` varchar(50) NOT NULL COMMENT '用户密码',
  `PERSON_YAN` varchar(50) NOT NULL COMMENT '用户盐',
  `PERSON_NAME` varchar(50) DEFAULT NULL COMMENT '用户姓名',
  PRIMARY KEY (`PERSON_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- 正在导出表  test.person_detail 的数据：~3 rows (大约)
DELETE FROM `person_detail`;
/*!40000 ALTER TABLE `person_detail` DISABLE KEYS */;
INSERT INTO `person_detail` (`PERSON_ID`, `PERSON_ACCOUNT`, `PERSON_PASSWORD`, `PERSON_YAN`, `PERSON_NAME`) VALUES
	(1, 'zzz123', '3ff965bdc94cde9d5c64ddfe1a713a88', 'baa5d283e1bbd87b88605c86250488dc', NULL),
	(2, 'qqqq', 'cad0ec08351cac715269cc8ff4f5b299', 'b29e0485c51fc5e67b622b592dc0b7d0', NULL);
/*!40000 ALTER TABLE `person_detail` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
