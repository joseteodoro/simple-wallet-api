package br.jteodoro.wallet.repositories;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PersistenceFactory {

    private final DataSource dataSource;

    public SimpleJdbcInsert of(String tableName, String primaryKeyFieldName) {
        return new SimpleJdbcInsert(this.dataSource)
            .withTableName(tableName)
            .usingGeneratedKeyColumns(primaryKeyFieldName);
    }

    public NamedParameterJdbcTemplate query() {
        return new NamedParameterJdbcTemplate(this.dataSource);
    }

}
