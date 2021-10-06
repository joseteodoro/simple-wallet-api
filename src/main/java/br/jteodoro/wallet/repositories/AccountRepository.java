package br.jteodoro.wallet.repositories;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import br.jteodoro.wallet.controllers.dto.AccountInput;
import br.jteodoro.wallet.models.Account;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

    private static final String FIND_BY_ID = "select * from `wallet`.`account` where accountId = :pk";

    private static final String INSERT = "insert into `wallet`.`account` (identifier) values (?);";

    private final PersistenceFactory persistence;

    public Optional<Account> create(AccountInput account) {
        if (Objects.isNull(account.getIdentifier()) || account.getIdentifier().isEmpty()) {
            return Optional.empty();
        }

        long pk = this.persistence.executeInsert(INSERT, "accountId" , pst -> {
            pst.setString(1, account.getIdentifier());
        });
        return findOne(pk);
    }

    public Optional<Account> findOne(long pk) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
            .addValue("pk", pk);
        return Optional.ofNullable(this.persistence.findOne(
            FIND_BY_ID,
            namedParameters,
            new BeanPropertyRowMapper<>(Account.class)
        ));
    }

}
