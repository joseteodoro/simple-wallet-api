package br.jteodoro.wallet.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBalance {

    private Long accountId;

    private Float balance;

    public static AccountBalance of(Long accountLong, Float balance) {
        return new AccountBalance(accountLong, balance);
    }

}