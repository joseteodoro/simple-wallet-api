package br.jteodoro.wallet.models;

import java.util.Objects;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Transaction {

    private Long transactionId;

    private Long accountId;

    private AccountOperationEnum operation;

    private Float value;

    private String uuid;

}