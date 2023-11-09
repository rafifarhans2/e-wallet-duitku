package com.enigma.duitku.controller;

import com.enigma.duitku.model.request.BeneficiaryRequest;
import com.enigma.duitku.model.response.BeneficiaryResponse;
import com.enigma.duitku.model.response.CommonResponse;
import com.enigma.duitku.service.BeneficiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/beneficiary")
public class BeneficiaryController {

    private final BeneficiaryService beneficiaryService;

    @PostMapping("/add")
    public ResponseEntity<?> addBeneficiary(@RequestBody BeneficiaryRequest request) {

        BeneficiaryResponse beneficiaryResponse = beneficiaryService.addBeneficiary(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.builder()
                        .statusCode(HttpStatus.CREATED.value())
                        .data(beneficiaryResponse)
                        .message("Successfully create benefiary")
                        .build());
    }

    @GetMapping("/viewall")
    public ResponseEntity<List<BeneficiaryResponse>> getAllBeneficiaryResponse() {
        List<BeneficiaryResponse> allBeneficiaryResponse = beneficiaryService.viewAllBeneficiaries();
        return new ResponseEntity<>(allBeneficiaryResponse, HttpStatus.OK);
    }

}
