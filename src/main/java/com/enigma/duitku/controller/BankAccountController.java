package com.enigma.duitku.controller;

import com.enigma.duitku.entity.BankAccount;
import com.enigma.duitku.entity.User;
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
    public ResponseEntity<?> addBankAccount(@RequestBody BankAccountRequest request)  {

        BankAccountResponse bankAccountResponse = bankAccountService.addAccount(request);

        if(bankAccountResponse.getErrors() != null || request.getMobileNumber().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(CommonResponse.builder()
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .data(bankAccountResponse)
                            .message("Failed created bank account")
                            .build());

        } else {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(CommonResponse.builder()
                            .statusCode(HttpStatus.CREATED.value())
                            .data(bankAccountResponse)
                            .message("Successfully created bank account")
                            .build());
        }
    }

    @PostMapping("/view")
    public ResponseEntity<?> viewProfile(@RequestBody BankAccountRequest request) {

        BankAccountResponse profileWallet = bankAccountService.viewProfile(request);

        if(profileWallet.getErrors() != null || request.getMobileNumber().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(CommonResponse.builder()
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .data(profileWallet)
                            .message("Failed view profile because mobile number bank not registered")
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponse.builder()
                            .statusCode(HttpStatus.OK.value())
                            .data(profileWallet)
                            .message("Successfully view profile")
                            .build());
        }
    }

    @PostMapping("/view/balance")
    public ResponseEntity<?> viewBankBalance(@RequestBody BankAccountRequest request) {

        BankAccountResponse balance = bankAccountService.viewBankBalance(request);

        if(balance.getErrors() != null || request.getMobileNumber().isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(CommonResponse.builder()
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .message("Failed get balance because mobile number bank not registered")
                            .data(balance)
                            .build());
        } else {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponse.builder()
                            .statusCode(HttpStatus.OK.value())
                            .data(balance)
                            .message("Succcessfully get balance")
                            .build());
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBankAccount(@RequestBody User user) {

        bankAccountService.removeAccountBank(user);

        BankAccount bankAccount = new BankAccount();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message(String.valueOf("Sucessfully delete account bank"))
                        .build());
    }
}
