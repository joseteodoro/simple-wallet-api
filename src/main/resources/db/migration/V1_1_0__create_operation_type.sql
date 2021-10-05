CREATE TABLE IF NOT EXISTS `wallet`.`operation` (
`operation_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`natural_key` varchar(32),
`createdAt` BIGINT not null default UNIX_TIMESTAMP(NOW())
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
