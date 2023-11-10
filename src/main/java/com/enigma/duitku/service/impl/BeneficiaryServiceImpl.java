package com.enigma.duitku.service.impl;

import com.enigma.duitku.entity.Beneficiary;
import com.enigma.duitku.entity.User;
import com.enigma.duitku.entity.Wallet;
import com.enigma.duitku.model.request.BeneficiaryRequest;
import com.enigma.duitku.model.response.BeneficiaryResponse;
import com.enigma.duitku.repository.BeneficiaryRepository;
import com.enigma.duitku.repository.UserRepository;
import com.enigma.duitku.repository.WalletRepository;
import com.enigma.duitku.service.BeneficiaryService;
import com.enigma.duitku.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BeneficiaryServiceImpl implements BeneficiaryService {

    private final UserService userService;

    private final WalletRepository walletRepository;

    private final BeneficiaryRepository beneficiaryRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    // still revisi
    public BeneficiaryResponse addBeneficiary(BeneficiaryRequest request) {

        // TODO 1: Validate user
        User user = userService.getById(request.getMobileNumber());

        // TODO 2: Retrieve wallet and beneficiaries
        Wallet wallet = user.getWallet();
        List<Beneficiary> beneficiaryList = wallet.getListOfBeneficiaries();

        Beneficiary databasebeneficiary = null;

        // TODO 3: Check for existing beneficiary
        for (Beneficiary b : beneficiaryList) {
            if (Objects.equals(b.getMobileNumber(), request.getMobileNumber())) {
                databasebeneficiary = b;
                break;
            }
        }

        // TODO 4: Add beneficiary if not exist
        if (databasebeneficiary == null) {
            Beneficiary newBeneficiary = new Beneficiary();
            newBeneficiary.setMobileNumber(request.getMobileNumber());
            newBeneficiary.setName(request.getName());
            newBeneficiary.setBankName(request.getBankName());
            newBeneficiary.setAccountNo(request.getAccountNo());

            beneficiaryList.add(newBeneficiary);
            wallet.setListOfBeneficiaries(beneficiaryList);

            walletRepository.save(wallet);
            beneficiaryRepository.save(newBeneficiary);
        }

        // TODO 5: Return BeneficiaryResponse
        return BeneficiaryResponse.builder()
                .mobileNumber(request.getMobileNumber())
                .bankName(request.getBankName())
                .name(request.getName())
                .accountNo(request.getAccountNo())
                .build();
    }

    @Override
    public List<BeneficiaryResponse> viewAllBeneficiaries() {

        List<Beneficiary> beneficiaries = beneficiaryRepository.findAll();
        List<BeneficiaryResponse> responses = new ArrayList<>();
        for(Beneficiary beneficiary : beneficiaries) {
            BeneficiaryResponse response = BeneficiaryResponse.builder()
                    .mobileNumber(beneficiary.getMobileNumber())
                    .accountNo(beneficiary.getAccountNo())
                    .bankName(beneficiary.getBankName())
                    .build();
            responses.add(response);
        }
        return responses;
    }

    @Override
    public String deleteByMobileNumber(String beneficiaryMobileNumber) {

        return null;
    }
}
