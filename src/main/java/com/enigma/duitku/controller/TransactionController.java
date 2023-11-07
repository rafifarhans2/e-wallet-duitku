package com.enigma.duitku.controller;

import com.enigma.duitku.entity.Transaction;
import com.enigma.duitku.model.request.TransactionRequest;
import com.enigma.duitku.model.response.CommonResponse;
import com.enigma.duitku.model.response.TransactionResponse;
import com.enigma.duitku.service.TransactionService;
import com.enigma.duitku.service.impl.TransactionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/transaction")
public class TransactionController {

    private final TransactionServiceImpl transactionService;

    @PostMapping("/addtransaction")
    public ResponseEntity<?> addTransactionHandler(@RequestBody TransactionRequest request) {
        TransactionResponse transactionResponse = transactionService.addTransaction(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .data(transactionResponse)
                        .message("Successfully Create Transaction")
                        .build());
    }

}
