CREATE TABLE IF NOT EXISTS `wallet`.`operation` (
`operationId` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`naturalKey` varchar(32),
`createdAt` BIGINT not null default UNIX_TIMESTAMP(NOW())
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
