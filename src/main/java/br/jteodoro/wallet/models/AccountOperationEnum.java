package br.jteodoro.wallet.models;

import java.util.Arrays;
import java.util.function.Predicate;

public enum AccountOperationEnum {
    
    COMPRA_A_VISTA(1l, "COMPRA A VISTA", f -> f < 0),
    COMPRA_PARCELADA(2l, "COMPRA PARCELADA", f -> f < 0),
    SAQUE(3l, "SAQUE", f -> f < 0),
    PAGAMENTO(4l, "PAGAMENTO", f -> f > 0);

    private Long id;

    private String naturalKey;

    private Predicate<Float> validation;

    private AccountOperationEnum(Long id, String naturalKey, Predicate<Float> validation) {
        this.id = id;
        this.naturalKey = naturalKey;
        this.validation = validation;
    }

    public static AccountOperationEnum of(Long id) {
        return Arrays.stream(values()).filter(p -> p.id.equals(id))
            .findFirst().orElse(null);
    }

    public Long getId() {
        return id;
    }

    public String getNaturalKey() {
        return naturalKey;
    }

    public boolean isValid(Float value) {
        return this.validation.test(value);
    }

}
