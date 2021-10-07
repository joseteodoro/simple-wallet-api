package br.jteodoro.wallet.controllers.dto;

import java.util.UUID;

import br.jteodoro.wallet.models.AccountOperationEnum;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class TransactionInput {

    private Long accountId;

    private AccountOperationEnum operation;

    private Float value;

    public Float getValue() {
        return operation.applyOp(this.value);
    }
}
