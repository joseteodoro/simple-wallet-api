package br.jteodoro.wallet.controllers.dto;

import br.jteodoro.wallet.models.AccountOperation;
import lombok.Data;

@Data
public class CreateTransactionPayload {
    
    private Long accountId;

    private AccountOperation operation;

    private Float value;

}
