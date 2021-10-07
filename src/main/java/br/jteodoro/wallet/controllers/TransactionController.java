package br.jteodoro.wallet.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.jteodoro.wallet.controllers.dto.TransactionInput;
import br.jteodoro.wallet.models.AccountBalance;
import br.jteodoro.wallet.models.Transaction;
import br.jteodoro.wallet.repositories.AccountRepository;
import br.jteodoro.wallet.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionRepository repository;

    private final AccountRepository accountRepository;
 
    @Transactional
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Transaction> create(@RequestBody TransactionInput payload) {
        Optional<?> found = this.accountRepository.findOne(payload.getAccountId());
        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseController.process(() -> this.repository.create(payload).get(), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{accountId}", produces = "application/json")
    public ResponseEntity<List<Transaction>> findByAccountId(@PathVariable(required = true) Long accountId) {
        Optional<?> found = this.accountRepository.findOne(accountId);
        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseController.process(() -> this.repository.listBy(accountId));
    }

    @GetMapping(value = "/{accountId}/balance", produces = "application/json")
    public ResponseEntity<AccountBalance> balanceByAccountId(@PathVariable(required = true) Long accountId) {
        Optional<?> found = this.accountRepository.findOne(accountId);
        if (found.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseController.process(() -> this.repository.balanceBy(accountId).get());
    }
    
}
