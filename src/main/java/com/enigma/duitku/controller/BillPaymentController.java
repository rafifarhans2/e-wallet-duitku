package com.enigma.duitku.controller;

import com.enigma.duitku.entity.Bill;
import com.enigma.duitku.model.request.BillRequest;
import com.enigma.duitku.model.response.CommonResponse;
import com.enigma.duitku.model.response.TransactionResponse;
import com.enigma.duitku.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/bill")
public class BillPaymentController {


    private final BillService billService;

    @PostMapping("/payment")
    public ResponseEntity<?> BillPayment(@RequestBody BillRequest request) {

        TransactionResponse transactionResponse =billService.billPayment(request);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.ACCEPTED.value())
                        .data(transactionResponse)
                        .message("Successfully payment")
                        .build());
    }

    @GetMapping("/view/allpayments")
    public ResponseEntity<?> viewAllBillPayment() {
        List<Bill> bills = billService.viewBillPayments();
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.OK.value())
                        .data(bills)
                        .message("Successfully get all payment")
                        .build());
    }



}
