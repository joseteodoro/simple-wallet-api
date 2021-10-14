package br.jteodoro.wallet.services.converter;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

public class BigdecimalConverter implements Converter<String, BigDecimal> {

    @Override
    public BigDecimal convert(String value) {
        return new BigDecimal(value);
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return typeFactory.constructType(String.class);
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return typeFactory.constructType(BigDecimal.class);
    }

}
