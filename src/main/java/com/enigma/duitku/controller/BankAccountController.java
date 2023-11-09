package com.enigma.duitku.controller;

import com.enigma.duitku.entity.BankAccount;
import com.enigma.duitku.exception.UserException;
import com.enigma.duitku.model.request.BankAccountRequest;
import com.enigma.duitku.model.response.BankAccountResponse;
import com.enigma.duitku.model.response.CommonResponse;
import com.enigma.duitku.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bankaccount")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @PostMapping("/add")
    public ResponseEntity<?> addBankAccount(@RequestBody BankAccountRequest request) throws UserException {

        BankAccountResponse bankAccountResponse = bankAccountService.addAccount(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .data(bankAccountResponse)
                        .message("Successfully create bank account")
                        .build());
    }

    @PostMapping("/view/balance")
    public ResponseEntity<?> viewBankBalance(@RequestBody BankAccountRequest request) {

        BankAccountResponse balance = bankAccountService.viewBankBalance(request);

        if(balance.getErrors() == null) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponse.builder()
                            .statusCode(HttpStatus.OK.value())
                            .data(balance)
                            .message("Succcessfully get balance")
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(CommonResponse.builder()
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .message("Failed get balance")
                            .data(balance)
                            .build());
        }
    }
}
