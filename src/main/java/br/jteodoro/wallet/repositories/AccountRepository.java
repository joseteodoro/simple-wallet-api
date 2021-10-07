package br.jteodoro.wallet.repositories;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import br.jteodoro.wallet.controllers.dto.AccountInput;
import br.jteodoro.wallet.models.Account;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

    private static final String FIND_BY_ID = "select * from `wallet`.`account` where accountId = :pk";

    private static final String FIND_BY_UUID = "select * from `wallet`.`account` where accountUuid = :uuid";

    private static final String INSERT = "insert into `wallet`.`account` (identifier, accountUuid) values (:identifier, :accountUuid);";

    private final PersistenceFactory persistence;

    public Optional<Account> create(AccountInput account) {
        if (Objects.isNull(account.getIdentifier()) || account.getIdentifier().isEmpty()) {
            return Optional.empty();
        }

        MapSqlParameterSource parameters = new MapSqlParameterSource()
            .addValue("identifier", account.getIdentifier())
            .addValue("accountUuid", account.getAccountUuid());

        this.persistence.executeInsert(INSERT, parameters);
        
        SqlParameterSource namedParameters = new MapSqlParameterSource()
            .addValue("uuid", account.getAccountUuid());
    
        return this.persistence.findOne(
            FIND_BY_UUID,
            namedParameters,
            new BeanPropertyRowMapper<>(Account.class)
        );
    }

    public Optional<Account> findOne(long pk) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
            .addValue("pk", pk);
    
        return this.persistence.findOne(
            FIND_BY_ID,
            namedParameters,
            new BeanPropertyRowMapper<>(Account.class)
        );
    }

}
