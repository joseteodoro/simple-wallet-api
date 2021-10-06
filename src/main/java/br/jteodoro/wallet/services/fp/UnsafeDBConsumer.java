package br.jteodoro.wallet.services.fp;

import java.sql.SQLException;

@FunctionalInterface
public interface UnsafeDBConsumer<T> {
    
    void accept(T value) throws SQLException;

}
