CREATE SCHEMA IF NOT EXISTS `wallet`;

CREATE TABLE IF NOT EXISTS `wallet`.`account` (
`account_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`identifier` varchar(32),
`password` varchar(255),
`createdAt` BIGINT not null default UNIX_TIMESTAMP(NOW())
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
