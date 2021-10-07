CREATE TABLE IF NOT EXISTS `wallet`.`operation` (
`operationId` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`naturalKey` varchar(32),
`createdAt` timestamp not null default NOW()
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
