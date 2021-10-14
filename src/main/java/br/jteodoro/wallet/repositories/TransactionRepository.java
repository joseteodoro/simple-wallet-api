package br.jteodoro.wallet.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import br.jteodoro.wallet.controllers.dto.TransactionInput;
import br.jteodoro.wallet.models.AccountBalance;
import br.jteodoro.wallet.models.Transaction;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TransactionRepository {

    private static final String INSERT = "insert into `wallet`.`trx` (value, operationId, accountId, trxUuid) values (:value, :operationId, :accountId, :trxUuid);";
    private static final String FIND_BY_ID = "select * from `wallet`.`trx` where trxId = :pk";
    private static final String FIND_BY_UUID = "select * from `wallet`.`trx` where trxUuid = :trxUuid";
    private static final String LIST_BY_ACCOUNT = "select * from `wallet`.`trx` where accountId = :account";
    private static final String BALANCE_BY_ACCOUNT = "select accountId, SUM(value) as balance from `wallet`.`trx` where accountId = :account group by accountId";
    private static final String BALANCE_WITH_LIMIT_BY_ACCOUNT = "select accountId, SUM(value) as balance from `wallet`.`trx` where accountId = :account group by accountId";

    private final PersistenceFactory persistence;

    public Optional<Transaction> create(TransactionInput trx) {
        String uuid = UUID.randomUUID().toString();
        MapSqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("value", trx.getValue())
            .addValue("operationId", trx.getOperation().getId())
            .addValue("accountId", trx.getAccountId())
            .addValue("trxUuid", uuid);

        this.persistence.executeInsert(INSERT, parameters);

        return this.persistence.findOne(
            FIND_BY_UUID,
            new MapSqlParameterSource().addValue("trxUuid", uuid),
            new BeanPropertyRowMapper<>(Transaction.class)
        );
    }

    public Optional<Transaction> findOne(long pk) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
            .addValue("pk", pk);

        return this.persistence.findOne(
            FIND_BY_ID,
            namedParameters,
            new BeanPropertyRowMapper<>(Transaction.class)
        );
    }

    public List<Transaction> listBy(Long accountId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
            .addValue("account", accountId);

        return this.persistence.list(
            LIST_BY_ACCOUNT,
            namedParameters,
            new BeanPropertyRowMapper<>(Transaction.class)
        );
    }

    public Optional<AccountBalance> balanceBy(Long accountId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
            .addValue("account", accountId);

        return this.persistence.findOne(
            BALANCE_BY_ACCOUNT,
            namedParameters,
            new BeanPropertyRowMapper<>(AccountBalance.class)
        );
    }

}
