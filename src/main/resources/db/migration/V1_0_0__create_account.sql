CREATE SCHEMA IF NOT EXISTS `wallet`;

CREATE TABLE IF NOT EXISTS `wallet`.`account` (
`account_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`identifier` varchar(32),
`createdAt` BIGINT not null default UNIX_TIMESTAMP(NOW()),
`updatedAt` BIGINT not null default UNIX_TIMESTAMP(NOW()) ON UPDATE UNIX_TIMESTAMP(NOW())
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
