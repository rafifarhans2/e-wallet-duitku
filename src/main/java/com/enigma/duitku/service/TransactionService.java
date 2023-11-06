package com.enigma.duitku.service;

import com.enigma.duitku.entity.Transaction;
import com.enigma.duitku.model.request.TransactionRequest;
import com.enigma.duitku.model.response.TransactionResponse;

public interface TransactionService {

    TransactionResponse addTransaction(TransactionRequest transactionRequest);
}
