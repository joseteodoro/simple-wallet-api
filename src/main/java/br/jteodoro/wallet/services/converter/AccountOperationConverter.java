package br.jteodoro.wallet.services.converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

import br.jteodoro.wallet.models.AccountOperation;

public class AccountOperationConverter implements Converter<Long, AccountOperation> {

    @Override
    public AccountOperation convert(Long id) {
        return AccountOperation.of(id);
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return typeFactory.constructType(Long.class);
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return typeFactory.constructType(AccountOperation.class);
    }

}
