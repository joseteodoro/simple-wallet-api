package br.jteodoro.wallet.controllers.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class AccountInput {

    private String identifier;

    private BigDecimal accountLimit = BigDecimal.ZERO;

}
