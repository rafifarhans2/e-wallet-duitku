package com.enigma.duitku.controller;

import com.enigma.duitku.exception.UserException;
import com.enigma.duitku.model.request.BankAccountRequest;
import com.enigma.duitku.model.response.BankAccountResponse;
import com.enigma.duitku.model.response.CommonResponse;
import com.enigma.duitku.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
