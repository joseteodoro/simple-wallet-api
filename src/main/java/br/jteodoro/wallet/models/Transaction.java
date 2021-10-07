package br.jteodoro.wallet.models;

import java.util.Objects;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private Long trxId;

    private Long accountId;

    @Getter(value = AccessLevel.NONE)
    private Long operationId;

    private Float value;

    public AccountOperationEnum getOperation() {
        return AccountOperationEnum.of(this.operationId);
    }

    public Float getValue() {
        return getOperation().applyOp(this.value);
    }

}