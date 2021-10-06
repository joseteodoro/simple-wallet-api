package br.jteodoro.wallet.repositories;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import br.jteodoro.wallet.services.fp.UnsafeDBConsumer;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PersistenceFactory {

    private final DataSource dataSource;

    private final JdbcTemplate template;

    public long executeInsert(String insert, UnsafeDBConsumer<PreparedStatement> populateFields) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.template.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(insert);
                populateFields.accept(ps);
                return ps;
            }, keyHolder);
    
        return (long) keyHolder.getKey();
    }

    public <T> T findOne(String query, SqlParameterSource namedParameters, RowMapper<T> mapper) {
        return new NamedParameterJdbcTemplate(this.dataSource)
            .queryForObject(query, namedParameters, mapper);
    }

    public <T> List<T> list(String sql, SqlParameterSource namedParameters, RowMapper<T> mapper) {
        return new NamedParameterJdbcTemplate(this.dataSource)
            .query(sql, namedParameters, mapper);
    }

}
