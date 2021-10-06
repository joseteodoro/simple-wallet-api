package br.jteodoro.wallet.services.converter;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

import br.jteodoro.wallet.models.AccountOperationEnum;

public class AccountOperationConverter implements Converter<Long, AccountOperationEnum> {

    @Override
    public AccountOperationEnum convert(Long id) {
        return AccountOperationEnum.of(id);
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return typeFactory.constructType(Long.class);
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return typeFactory.constructType(AccountOperationEnum.class);
    }

}
