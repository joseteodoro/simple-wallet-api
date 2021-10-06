package br.jteodoro.wallet.repositories;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TransactionRepository {
    
    private final SimpleJdbcInsert simpleJdbcInsert;
    
}
