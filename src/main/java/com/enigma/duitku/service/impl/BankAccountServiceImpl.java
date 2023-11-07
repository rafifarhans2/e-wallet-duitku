package com.enigma.duitku.service.impl;

import com.enigma.duitku.entity.BankAccount;
import com.enigma.duitku.entity.User;
import com.enigma.duitku.model.request.BankAccountRequest;
import com.enigma.duitku.model.response.BankAccountResponse;
import com.enigma.duitku.repository.UserRepository;
import com.enigma.duitku.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final UserRepository userRepository;

    @Override
    public BankAccountResponse addAccount(BankAccountRequest request) {


        BankAccount bankAccount = new BankAccount();

        return BankAccountResponse.builder()

                .build();
    }
}
