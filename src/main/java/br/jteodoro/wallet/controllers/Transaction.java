package br.jteodoro.wallet.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jteodoro.wallet.controllers.dto.CreateTransactionPayload;

@RestController
@RequestMapping("/v1/transactions")
public class Transaction {
 
    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity create(@RequestBody CreateTransactionPayload payload) {
        return null;
    }

}
