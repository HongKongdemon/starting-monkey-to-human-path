-- Adminer 4.6.2 MySQL dump

SET NAMES utf8;
SET time_zone = '' + 00 :00'';
SET foreign_key_checks = 0;
SET sql_mode = '' NO_AUTO_VALUE_ON_ZERO'';

DROP TABLE IF EXISTS `notes`;
CREATE TABLE `notes` (
  `id`            int(11) NOT NULL AUTO_INCREMENT,
  `title`         text    NOT NULL,
  `creation_date` date    NOT NULL,
  `text`          text    NOT NULL,
  `author_id`     int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `author_id` (`author_id`),
  CONSTRAINT `notes_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `notes` (`id`, `title`, `creation_date`, `text`, `author_id`)
VALUES (1, ''Еда'', ''2019-01-29'', ''Приготовить еду'', 1),
       (2, ''Лабы'', ''2019-01-29'', ''Сделать 1 лабу'', 2),
       (3, ''Лабы'', ''2019-01-29'', ''Сделать 2 лабу'', 1);

DROP TABLE IF EXISTS `user_privileges`;
CREATE TABLE `user_privileges` (
  `id`        int(11) NOT NULL AUTO_INCREMENT,
  `privilege` int(5)  NOT NULL,
  `notes_id`  int(11) NOT NULL,
  `users_id`  int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `notes_id` (`notes_id`),
  KEY `users_id` (`users_id`),
  CONSTRAINT `user_privileges_ibfk_1` FOREIGN KEY (`notes_id`) REFERENCES `notes` (`id`),
  CONSTRAINT `user_privileges_ibfk_2` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `user_privileges` (`id`, `privilege`, `notes_id`, `users_id`)
VALUES (1, 3, 1, 1),
       (2, 1, 3, 2),
       (3, 3, 3, 1);

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id`                 int(11)  NOT NULL AUTO_INCREMENT,
  `name`               text     NOT NULL,
  `encrypted_password` int(100) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO `users` (`id`, `name`, `encrypted_password`)
VALUES (1, ''test'', 31231231),
       (2, ''user'', 1231231);

-- 2019-01-29 16:10:45