package com.enigma.duitku.service.impl;

import com.enigma.duitku.entity.User;
import com.enigma.duitku.model.request.TransactionRequest;
import com.enigma.duitku.model.response.TransactionResponse;
import com.enigma.duitku.repository.UserRepository;
import com.enigma.duitku.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final UserRepository userRepository;

    @Override
    public TransactionResponse addMoneyToWallet(TransactionRequest request) {

        Optional<User> optionalUser = userRepository.findById(request.getWalletId());

        return TransactionResponse.builder()

                .build();
    }
}
