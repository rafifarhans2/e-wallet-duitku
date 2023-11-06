package com.enigma.duitku.service;

import com.enigma.duitku.entity.Transaction;

public interface TransactionService {

    public Transaction addTransaction(String key, String receiver, String description, String transactionType, Double amount);


}
