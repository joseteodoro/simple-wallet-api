package br.jteodoro.wallet.models;

import java.util.Arrays;

public enum AccountOperationEnum {
    
    COMPRA_A_VISTA(1l, "COMPRA A VISTA"),
    COMPRA_PARCELADA(2l, "COMPRA PARCELADA"),
    SAQUE(3l, "SAQUE"),
    PAGAMENTO(4l, "PAGAMENTO");

    private Long id;

    private String naturalKey;

    private AccountOperationEnum(Long id, String naturalKey) {
        this.id = id;
        this.naturalKey = naturalKey;
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

}
