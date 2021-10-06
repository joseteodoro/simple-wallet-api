package br.jteodoro.wallet.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.jteodoro.wallet.controllers.dto.CreateTransactionPayload;
import br.jteodoro.wallet.models.AccountOperationEnum;
import br.jteodoro.wallet.models.Transaction;

@RestController
@RequestMapping("/v1/transactions")
public class TransactionController {
 
    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Transaction> create(@RequestBody Transaction payload) {
        return null;
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Transaction>> findByAccountId(@RequestParam(required = true) Integer accountId, @RequestParam(required = false) AccountOperationEnum operation) {
        return null;
    }
    
}
