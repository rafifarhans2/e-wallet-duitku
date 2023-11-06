package com.enigma.duitku.service;

import com.enigma.duitku.entity.Transaction;

public interface TransactionService {

    Transaction addTransaction(String receiver, String description, String transactionType, Double amount);
}
