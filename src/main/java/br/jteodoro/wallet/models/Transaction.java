package br.jteodoro.wallet.models;

import lombok.Data;

@Data
public class Transaction {

    private Long accountId;

    private AccountOperation operation;

    private Float value;

    private String uuid;

}