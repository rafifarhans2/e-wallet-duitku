package com.enigma.duitku.service.impl;

import com.enigma.duitku.entity.Transaction;
import com.enigma.duitku.entity.User;
import com.enigma.duitku.model.request.TransactionRequest;
import com.enigma.duitku.model.response.TransactionResponse;
import com.enigma.duitku.repository.TransactionRepository;
import com.enigma.duitku.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public TransactionResponse addTransaction(TransactionRequest transactionRequest) {

            Transaction transaction = new Transaction();
            transaction.setAmount(transactionRequest.getAmount());
            transaction.setLocalTime(LocalTime.now());
            transaction.setLocalDate(LocalDate.now());
            transaction.setDescription(transaction.getDescription());
            transaction.setReceiver(transactionRequest.getReceiver());
            transaction.setType(transactionRequest.getTransactionType());

            transactionRepository.saveAndFlush(transaction);
            return TransactionResponse.builder()
                    .amount(transaction.getAmount())
                    .build();
    }
}
