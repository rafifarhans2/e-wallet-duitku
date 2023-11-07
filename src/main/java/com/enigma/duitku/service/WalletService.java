package com.enigma.duitku.service;

import com.enigma.duitku.exception.BankAccountException;
import com.enigma.duitku.exception.WalletException;
import com.enigma.duitku.model.request.TransactionRequest;
import com.enigma.duitku.model.response.TransactionResponse;

public interface WalletService {

        TransactionResponse addMoneyToWallet(TransactionRequest request)throws WalletException, BankAccountException;

}
