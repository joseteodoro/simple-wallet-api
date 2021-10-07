CREATE SCHEMA IF NOT EXISTS `wallet`;

CREATE TABLE IF NOT EXISTS `wallet`.`account` (
`accountId` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`identifier` varchar(255),
`accountUuid` varchar(255),
`createdAt` timestamp not null default NOW(),
`updatedAt` timestamp not null default NOW() ON UPDATE NOW(),
INDEX (accountUuid)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
