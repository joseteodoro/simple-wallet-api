package br.jteodoro.wallet.repositories;

import org.flywaydb.core.internal.jdbc.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

    private final SimpleJdbcInsert simpleJdbcInsert;

}
