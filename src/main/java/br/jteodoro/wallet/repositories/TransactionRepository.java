package br.jteodoro.wallet.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import br.jteodoro.wallet.models.Transaction;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TransactionRepository {

    private static final String FIND_BY_ID = "select * from `wallet`.`trx` where trxId = :pk";
    private static final String LIST_BY_ACCOUNT = "select * from `wallet`.`trx` where accountId = :account";

    private final PersistenceFactory persistence;

    public Optional<Transaction> create(Transaction trx) {
        if (!trx.getOperation().isValid(trx.getValue())) {
            return Optional.empty();
        }

        SqlParameterSource paramer = new BeanPropertySqlParameterSource(Transaction.class);
        SimpleJdbcInsert store =  this.persistence.of("transaction", "transactionId");
        return findOne(store.execute(paramer));
    }

    public Optional<Transaction> findOne(long pk) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("pk", pk);
        return Optional.ofNullable(this.persistence.query()
            .queryForObject(FIND_BY_ID, namedParameters, Transaction.class)
        );
    }

    public List<Transaction> listBy(Integer accountId) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
            .addValue("account", accountId);

        return this.persistence.query().query(
            LIST_BY_ACCOUNT,
            namedParameters,
            new BeanPropertyRowMapper<>(Transaction.class));
    }
}
