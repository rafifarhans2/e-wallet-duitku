package com.enigma.duitku.service.impl;

import com.enigma.duitku.entity.Transaction;
import com.enigma.duitku.entity.User;
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
    public Transaction addTransaction(String receiver, String description, String transactionType, Double amount) {
            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setLocalTime(LocalTime.now());
            transaction.setLocalDate(LocalDate.now());
            transaction.setDescription(description);
            transaction.setReceiver(receiver);
            transaction.setType(transactionType);

            transactionRepository.saveAndFlush(transaction);
            return transaction;
    }
}
