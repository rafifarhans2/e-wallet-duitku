package com.enigma.duitku.service.impl;

import com.enigma.duitku.entity.Transaction;
import com.enigma.duitku.entity.Wallet;
import com.enigma.duitku.model.request.TransactionRequest;
import com.enigma.duitku.model.response.TransactionResponse;
import com.enigma.duitku.repository.TransactionRepository;
import com.enigma.duitku.service.TransactionService;
import com.enigma.duitku.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public TransactionResponse addTransaction(TransactionRequest transactionRequest) {

            Transaction transaction = new Transaction();
                    transaction.setAmount(transactionRequest.getAmount());
                    transaction.setLocalDate(LocalDateTime.now());
                    transaction.setDescription(transactionRequest.getDescription());
                    transaction.setReceiver(transactionRequest.getReceiver());
                    transaction.setType(transactionRequest.getTransactionType());

                    transactionRepository.saveAndFlush(transaction);

            return TransactionResponse.builder()
                    .amount(transaction.getAmount())
                    .description(transaction.getDescription())
                    .transactionType(transaction.getType())
                    .build();
    }
}
