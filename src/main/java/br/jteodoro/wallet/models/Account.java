package br.jteodoro.wallet.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    
    private Long accountId;

    private String identifier;

    private String accountUuid;

}