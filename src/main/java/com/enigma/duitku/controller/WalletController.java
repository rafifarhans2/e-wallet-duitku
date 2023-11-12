package com.enigma.duitku.controller;

import com.enigma.duitku.entity.Wallet;
import com.enigma.duitku.exception.BankAccountException;
import com.enigma.duitku.exception.WalletException;
import com.enigma.duitku.model.request.TransactionRequest;
import com.enigma.duitku.model.request.UserWallet;
import com.enigma.duitku.model.request.WalletRequest;
import com.enigma.duitku.model.response.CommonResponse;
import com.enigma.duitku.model.response.TransactionResponse;
import com.enigma.duitku.model.response.WalletResponse;
import com.enigma.duitku.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class WalletController {

    private final WalletService walletService;

    @PostMapping("/transfer")
    public ResponseEntity<?> transferMoney(@RequestBody TransactionRequest transactionRequest) {
        try {
            TransactionResponse transfer = walletService.transferMoney(transactionRequest);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponse.builder()
                            .statusCode(HttpStatus.OK.value())
                            .data(transfer)
                            .message("Transfer Success")
                            .build());
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(CommonResponse.builder()
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message("Transfer Failed" + e.getMessage())
                            .build());
        }
    }

    @GetMapping(value="wallet/{id}")
    public ResponseEntity<?> getWalletById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get wallet by id")
                        .data(walletService.getById(id))
                        .build());
    }

    @PostMapping(value = "/transfertest")
    public ResponseEntity<?> create(@RequestBody UserWallet wallet){
        return ResponseEntity.ok().body(walletService.create(wallet));
    }
}
