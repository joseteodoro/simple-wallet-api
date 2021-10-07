package br.jteodoro.wallet.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import br.jteodoro.wallet.controllers.dto.TransactionInput;
import br.jteodoro.wallet.models.Transaction;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TransactionRepository {

    private static final String INSERT = "insert into `wallet`.`trx` (value, operationId, accountId) values (?, ?, ?);";
    private static final String FIND_BY_ID = "select * from `wallet`.`trx` where trxId = :pk";
    private static final String LIST_BY_ACCOUNT = "select * from `wallet`.`trx` where accountId = :account";

    private final PersistenceFactory persistence;

    public Optional<Transaction> create(TransactionInput trx) {
        long pk = this.persistence.executeInsert(INSERT, "trxId" , pst -> {
            pst.setFloat(1, trx.getValue());
            pst.setLong(2, trx.getOperation().getId());
            pst.setLong(3, trx.getAccountId());
        });
        return findOne(pk);
    }

    public Optional<Transaction> findOne(long pk) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
            .addValue("pk", pk);
        return Optional.ofNullable(this.persistence.findOne(
            FIND_BY_ID,
            namedParameters,
            new BeanPropertyRowMapper<>(Transaction.class)
        ));
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
}
