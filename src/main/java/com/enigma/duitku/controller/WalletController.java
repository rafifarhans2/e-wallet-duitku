package com.enigma.duitku.controller;

import com.enigma.duitku.entity.Wallet;
import com.enigma.duitku.exception.BankAccountException;
import com.enigma.duitku.exception.WalletException;
import com.enigma.duitku.model.request.TransactionRequest;
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
@RequestMapping("api/")
public class WalletController {

    private final WalletService walletService;

    @PostMapping("transfer")
    public ResponseEntity<?> transferMoney(TransactionRequest transactionRequest) {

        TransactionResponse transactionResponse = walletService.transferMoney(transactionRequest);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully transfer money")
                        .data(transactionResponse)
                        .build());
    }

    @PostMapping(value = "transfer/towallet")
    public ResponseEntity<?> addMoneyToWallet(@RequestBody TransactionRequest request) throws BankAccountException, WalletException {
         TransactionResponse transactionResponse= walletService.addMoneyToWallet(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .data(transactionResponse)
                        .message("Sucessfully add money to wallet")
                        .build());
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

}
