package br.jteodoro.wallet.controllers;

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

import br.jteodoro.wallet.controllers.dto.AccountInput;
import br.jteodoro.wallet.models.Account;
import br.jteodoro.wallet.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountRepository repository;

    @GetMapping(value = "/{accountId}", produces = "application/json")
    public ResponseEntity<Account> findOne(@PathVariable(required = true) Integer accountId) {
        return ResponseController.process(() -> this.repository.findOne(accountId).get());
    }

    @Transactional
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Account> create(@RequestBody AccountInput payload) {
        return ResponseController.process(() -> this.repository.create(payload).get(), HttpStatus.CREATED);
    }
}
