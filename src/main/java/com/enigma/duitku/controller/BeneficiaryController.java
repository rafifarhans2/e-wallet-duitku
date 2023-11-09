package com.enigma.duitku.controller;

import com.enigma.duitku.model.request.BeneficiaryRequest;
import com.enigma.duitku.model.response.BeneficiaryResponse;
import com.enigma.duitku.model.response.CommonResponse;
import com.enigma.duitku.service.BeneficiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/beneficiary")
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    @PostMapping("/add")
    private ResponseEntity<?> addBeneficiary(@RequestBody BeneficiaryRequest request) {

        BeneficiaryResponse beneficiaryResponse = beneficiaryService.addBeneficiary(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .data(beneficiaryResponse)
                        .message("Successfully create benefiary")
                        .build());
    }

}
