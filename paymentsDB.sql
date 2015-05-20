-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.6.19 - MySQL Community Server (GPL)
-- ОС Сервера:                   Win64
-- HeidiSQL Версия:              8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных payments
CREATE DATABASE IF NOT EXISTS `payments` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `payments`;


-- Дамп структуры для таблица payments.bank_accounts
CREATE TABLE IF NOT EXISTS `bank_accounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `valid` tinyint(1) NOT NULL,
  `blocked` tinyint(1) NOT NULL,
  `credit_card_id` int(11) NOT NULL,
  `sum` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `credit_card_id` (`credit_card_id`),
  CONSTRAINT `credit_card_id` FOREIGN KEY (`credit_card_id`) REFERENCES `credit_cards` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы payments.bank_accounts: ~12 rows (приблизительно)
/*!40000 ALTER TABLE `bank_accounts` DISABLE KEYS */;
INSERT INTO `bank_accounts` (`id`, `valid`, `blocked`, `credit_card_id`, `sum`) VALUES
	(1, 1, 1, 1, 106000),
	(2, 1, 0, 2, 150000),
	(9, 1, 0, 11, 0),
	(10, 1, 0, 12, 10000),
	(11, 1, 0, 13, 0),
	(12, 1, 1, 14, 100000),
	(13, 1, 0, 15, 0),
	(14, 1, 0, 16, 0),
	(15, 1, 0, 17, 0),
	(16, 1, 1, 18, 96346),
	(17, 1, 0, 19, 0),
	(18, 1, 0, 20, 0),
	(19, 1, 0, 21, 0),
	(20, 1, 0, 22, 0);
/*!40000 ALTER TABLE `bank_accounts` ENABLE KEYS */;


-- Дамп структуры для таблица payments.clients
CREATE TABLE IF NOT EXISTS `clients` (
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `bank_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`login`),
  UNIQUE KEY `pasport_id` (`login`),
  KEY `role_id` (`role_id`),
  KEY `bank_id` (`bank_id`),
  CONSTRAINT `bank_id` FOREIGN KEY (`bank_id`) REFERENCES `bank_accounts` (`id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы payments.clients: ~7 rows (приблизительно)
/*!40000 ALTER TABLE `clients` DISABLE KEYS */;
INSERT INTO `clients` (`login`, `password`, `first_name`, `last_name`, `bank_id`, `role_id`) VALUES
	('1', 'pass1', 'Mikhail', 'Vasilevich', 1, 0),
	('10', 'pass10', 'Mikle', 'Tenth', 14, 1),
	('11', 'pass1', 'User11', 'Eleventh', 15, 1),
	('12', 'pass12', 'User12', 'Twellveth', 16, 1),
	('2', 'pass2', 'Leonid', 'Vasilevich', 2, 1),
	('3', 'pass3', 'Dmitry', 'Olifer', 9, 1),
	('4', 'pass4', 'Svetlana', 'Alferova', 11, 1),
	('5', 'pass5', 'Andrei', 'Karzhou', 13, 1);
/*!40000 ALTER TABLE `clients` ENABLE KEYS */;


-- Дамп структуры для таблица payments.credit_cards
CREATE TABLE IF NOT EXISTS `credit_cards` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы payments.credit_cards: ~15 rows (приблизительно)
/*!40000 ALTER TABLE `credit_cards` DISABLE KEYS */;
INSERT INTO `credit_cards` (`id`) VALUES
	(1),
	(2),
	(11),
	(12),
	(13),
	(14),
	(15),
	(16),
	(17),
	(18),
	(19),
	(20),
	(21),
	(22);
/*!40000 ALTER TABLE `credit_cards` ENABLE KEYS */;


-- Дамп структуры для таблица payments.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bank_account_id` int(11) NOT NULL DEFAULT '0',
  `sum` double NOT NULL DEFAULT '0',
  `paid` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `bank_account_id` (`bank_account_id`),
  CONSTRAINT `bank_account_id` FOREIGN KEY (`bank_account_id`) REFERENCES `bank_accounts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы payments.orders: ~5 rows (приблизительно)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`id`, `bank_account_id`, `sum`, `paid`) VALUES
	(1, 1, 11000, 1),
	(2, 16, 50, 1),
	(3, 16, 277, 1),
	(4, 16, 300, 1),
	(5, 16, 900, 1),
	(6, 16, 400, 1),
	(7, 16, 1527, 1),
	(8, 16, 200, 1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;


-- Дамп структуры для таблица payments.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы payments.roles: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `role`) VALUES
	(0, 'admin'),
	(1, 'client');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
