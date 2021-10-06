package br.jteodoro.wallet.models;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    private Long transactionId;

    private Long accountId;

    private AccountOperationEnum operation;

    private Float value;

    private String uuid;

}