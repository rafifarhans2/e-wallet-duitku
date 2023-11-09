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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BeneficiaryServiceImpl implements BeneficiaryService {

    private final UserRepository userRepository;

    private final WalletRepository walletRepository;

    private final BeneficiaryRepository beneficiaryRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public BeneficiaryResponse addBeneficiary(BeneficiaryRequest request) {

        Optional<User> optionalUser = userRepository.findById(request.getMobileNumber());

        if(optionalUser.isPresent()) {

            User user = optionalUser.get();

            Wallet wallet = user.getWallet();

            List<Beneficiary> beneficiaryList = wallet.getListOfBeneficiaries();

            Beneficiary databasebeneficiary = null;

            for (Beneficiary b : beneficiaryList) {

                if(Objects.equals(b.getMobileNumber(), request.getMobileNumber())) {
                    databasebeneficiary = b;
                }
            }

            if(databasebeneficiary == null) {
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

            return BeneficiaryResponse.builder()
                    .mobileNumber(request.getMobileNumber())
                    .bankName(request.getBankName())
                    .name(request.getName())
                    .accountNo(request.getAccountNo())
                    .build();
        } else {
            return BeneficiaryResponse.builder()
                    .errors("A beneficiary already exists with this Mobile Number " + request.getMobileNumber())
                    .build();
        }



    }
}
