package br.jteodoro.wallet.controllers;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.function.Supplier;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
public class ResponseController {

    private ResponseController() {}

    public static <T> ResponseEntity<T> process(Supplier<T> fn) {
        return process(fn, HttpStatus.OK);
    }

    public static <T> ResponseEntity<T> process(Supplier<T> fn, HttpStatus onSuccess) {
        try {
            return ResponseEntity.status(onSuccess).body(fn.get());
        } catch (Exception exception) {
            log.error(exception.getMessage(), exception);
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

}

