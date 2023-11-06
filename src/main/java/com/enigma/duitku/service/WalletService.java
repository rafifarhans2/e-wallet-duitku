package com.enigma.duitku.service;

import com.enigma.duitku.model.request.TransactionRequest;
import com.enigma.duitku.model.response.TransactionResponse;

public interface WalletService {

        TransactionResponse addMoneyToWallet(TransactionRequest request);

}
