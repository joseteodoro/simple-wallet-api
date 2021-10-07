CREATE TABLE IF NOT EXISTS `wallet`.`trx` (
`trxId` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
`value` DECIMAL(18,2) not null,
`operationId` int not null,
`accountId` int not null,
`trxUuid` varchar(255),
`createdAt` timestamp not null default NOW(),
`updatedAt` timestamp not null default NOW() ON UPDATE NOW(),
CONSTRAINT fk_trx_account FOREIGN KEY (accountId) REFERENCES wallet.account(accountId),
CONSTRAINT fk_trx_operation FOREIGN KEY (operationId) REFERENCES wallet.operation(operationId),
INDEX (trxUuid)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;
