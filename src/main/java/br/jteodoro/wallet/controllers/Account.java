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

@RestController
@RequestMapping("/v1/accounts")
public class Account {

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity findOne(@RequestParam Integer accountId) {
        return null;
    }
    
    @PostMapping(value = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity create(@RequestBody CreateAccountPayload payload) {
        return null;
    }
}
