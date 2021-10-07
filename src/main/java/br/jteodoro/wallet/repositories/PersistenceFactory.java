package br.jteodoro.wallet.repositories;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
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

    public long executeInsert(String insert, SqlParameterSource parameters) {
        return new NamedParameterJdbcTemplate(this.dataSource).update(insert, parameters);
    }

    public <T> Optional<T> findOne(String query, SqlParameterSource namedParameters, RowMapper<T> mapper) {
        // should be a better way to avoid flow control by exceptions!
        // but I dont want to hit database twice
        try {
            return Optional.ofNullable(
                new NamedParameterJdbcTemplate(this.dataSource).queryForObject(query, namedParameters, mapper)
            );
        } catch (EmptyResultDataAccessException notFount) {
            return Optional.empty();
        }
    }

    public <T> List<T> list(String sql, SqlParameterSource namedParameters, RowMapper<T> mapper) {
        return new NamedParameterJdbcTemplate(this.dataSource)
            .query(sql, namedParameters, mapper);
    }

}
