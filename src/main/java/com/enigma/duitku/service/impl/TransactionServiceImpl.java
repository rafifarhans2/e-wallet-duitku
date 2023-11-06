package com.enigma.duitku.service.impl;

import com.enigma.duitku.entity.Transaction;
import com.enigma.duitku.entity.User;
import com.enigma.duitku.service.TransactionService;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {


    @Override
    public Transaction addTransaction(String key, String receiver, String description, String transactionType, Double amount) {
        return null;
    }
}
