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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/")
public class WalletController {

    private final WalletService walletService;

    @PostMapping("transfer")
    public ResponseEntity<?> transferMoney(WalletRequest request) {

        WalletResponse walletResponse= walletService.transferMoney(request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully transfer money")
                        .data(walletResponse)
                        .build());
    }

    @PostMapping(value = "transfer/towallet")
    public ResponseEntity<?> addMoneyToWallet(@RequestBody WalletRequest request) throws BankAccountException, WalletException {
         WalletResponse walletResponse= walletService.addMoneyToWallet(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .data(walletResponse)
                        .message("Sucessfully add money to wallet")
                        .build());
    }

}
