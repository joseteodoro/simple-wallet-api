package br.jteodoro.wallet.models;

import java.util.Arrays;
import java.util.function.Predicate;

public enum AccountOperationEnum {
    
    COMPRA_A_VISTA(1l, "COMPRA A VISTA", Op.DEBITO),
    COMPRA_PARCELADA(2l, "COMPRA PARCELADA", Op.DEBITO),
    SAQUE(3l, "SAQUE", Op.DEBITO),
    PAGAMENTO(4l, "PAGAMENTO", Op.CREDITO);

    private Long id;

    private String naturalKey;

    private int op;

    private AccountOperationEnum(Long id, String naturalKey, int op) {
        this.id = id;
        this.naturalKey = naturalKey;
        this.op = op;
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

    public float applyOp(float value) {
        return value * op;
    }

}
