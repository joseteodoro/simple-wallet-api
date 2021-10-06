package br.jteodoro.wallet.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.jteodoro.wallet.controllers.dto.CreateAccountPayload;
import br.jteodoro.wallet.models.Account;

@RestController
@RequestMapping("/v1/accounts")
public class AccountController {

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<Account> findOne(@RequestParam(required = true) Integer accountId) {
        return null;
    }
    
    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Account> create(@RequestBody Account payload) {
        return null;
    }
}
