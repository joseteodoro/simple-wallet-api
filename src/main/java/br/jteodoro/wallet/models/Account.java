package br.jteodoro.wallet.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {
    
    private Long accountId;

    private String identifier;

}