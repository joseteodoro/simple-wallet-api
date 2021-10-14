package br.jteodoro.wallet.models;

import java.math.BigDecimal;

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

    private BigDecimal accountLimit;

}