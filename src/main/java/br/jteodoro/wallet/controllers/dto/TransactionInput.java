package br.jteodoro.wallet.controllers.dto;

import br.jteodoro.wallet.models.AccountOperationEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionInput {

    private Long accountId;

    private AccountOperationEnum operation;

    private Float value;

    public Float getValue() {
        return operation.applyOp(this.value);
    }
}
