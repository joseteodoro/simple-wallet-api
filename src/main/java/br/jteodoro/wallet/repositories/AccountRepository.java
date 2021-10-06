package br.jteodoro.wallet.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import br.jteodoro.wallet.models.Account;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

    private static final String FIND_BY_ID = "select * from `wallet`.`account` where accountId = :pk";

    private final PersistenceFactory persistence;

    public Optional<Account> create(Account account) {
        if (Objects.isNull(account.getIdentifier()) || account.getIdentifier().isEmpty()) {
            return Optional.empty();
        }

        SqlParameterSource paramer = new BeanPropertySqlParameterSource(Account.class);
        SimpleJdbcInsert store =  this.persistence.of("account", "accountId");
        return findOne(store.execute(paramer));
    }

    public Optional<Account> findOne(long pk) {
        SqlParameterSource namedParameters = new MapSqlParameterSource().addValue("pk", pk);
        return Optional.ofNullable(this.persistence.query()
            .queryForObject(FIND_BY_ID, namedParameters, Account.class)
        );
    }

}
