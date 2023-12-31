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

import java.util.List;

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

    @GetMapping("/profile/{id}")
   public ResponseEntity<?> getViewProfileBankAccount(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .message("Successfully get bank account by id")
                        .data(bankAccountService.getById(id))
                        .build());
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

    @PostMapping("/topup")
    public ResponseEntity<?> topUpWallet(@RequestBody BankAccountRequest request){
        try {
            BankAccountResponse topUp = bankAccountService.topUpWallet(request);

            return ResponseEntity.status(HttpStatus.OK)
                    .body(CommonResponse.<BankAccountResponse>builder()
                            .statusCode(HttpStatus.OK.value())
                            .data(topUp)
                            .message("Top Up Successful")
                            .build());
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(CommonResponse.<BankAccountResponse>builder()
                            .statusCode(HttpStatus.NOT_FOUND.value())
                            .message("Top Up Failed. " + e.getMessage())
                            .build());
        }
    }
}
