package com.enigma.duitku.controller;

import com.enigma.duitku.entity.Transaction;
import com.enigma.duitku.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/addtransaction")
    public ResponseEntity<Transaction> addTransactionHandler(@Valid @RequestParam String receiver, @RequestParam String description,
                                                             @Valid @RequestParam String transactionType, @Valid @RequestParam Double amount) {

        Transaction transaction = transactionService.addTransaction(receiver, description, transactionType, amount);

        return new ResponseEntity<>(transaction, HttpStatus.ACCEPTED);

    }

}
