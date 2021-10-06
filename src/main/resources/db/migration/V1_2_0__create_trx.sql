CREATE TABLE IF NOT EXISTS `wallet`.`trx` (
`trxId` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`uuid` varchar(64) not null default UUID(),
`value` DECIMAL(18,2) not null,
`operationId` int not null,
`accountId` int not null,
`createdAt` BIGINT not null default UNIX_TIMESTAMP(NOW()),
`updatedAt` BIGINT not null default UNIX_TIMESTAMP(NOW()) ON UPDATE UNIX_TIMESTAMP(NOW()),
CONSTRAINT fk_trx_account FOREIGN KEY (accountId) REFERENCES wallet.account(accountId),
CONSTRAINT fk_trx_operation FOREIGN KEY (operationId) REFERENCES wallet.operation(operationId)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
