package com.enigma.duitku.service;

import com.enigma.duitku.entity.Transaction;
import com.enigma.duitku.entity.Wallet;
import com.enigma.duitku.exception.BankAccountException;
import com.enigma.duitku.exception.WalletException;
import com.enigma.duitku.model.request.TransactionRequest;
import com.enigma.duitku.model.request.WalletRequest;
import com.enigma.duitku.model.response.TransactionResponse;
import com.enigma.duitku.model.response.WalletResponse;

public interface WalletService {

        TransactionResponse transferMoney(TransactionRequest transaction);

        TransactionResponse addMoneyToWallet(TransactionRequest request)throws WalletException, BankAccountException;

        Wallet getById(String id);

}
