CREATE TABLE IF NOT EXISTS `wallet`.`trx` (
`trx_id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`uuid` varchar(64) not null default UUID(),
`value` BIGINT not null,
`operation_id` int not null,
`account_id` int not null,
`createdAt` BIGINT not null default UNIX_TIMESTAMP(NOW()),
CONSTRAINT fk_trx_account FOREIGN KEY (account_id) REFERENCES account(account_id),
CONSTRAINT fk_trx_operation FOREIGN KEY (operation_id) REFERENCES operation(operation_id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
