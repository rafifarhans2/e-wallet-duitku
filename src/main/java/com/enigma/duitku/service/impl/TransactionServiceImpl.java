package com.enigma.duitku.service.impl;

import com.enigma.duitku.entity.Transaction;
import com.enigma.duitku.entity.User;
import com.enigma.duitku.entity.Wallet;
import com.enigma.duitku.model.request.TransactionRequest;
import com.enigma.duitku.model.response.TransactionResponse;
import com.enigma.duitku.repository.TransactionRepository;
import com.enigma.duitku.repository.WalletRepository;
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

    private final WalletRepository walletRepository;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public TransactionResponse addTransaction(TransactionRequest transactionRequest) {

            User user = new User();

            Wallet wallet = user.getWallet();

        
            Transaction transaction = new Transaction();

                    transaction.setAmount(transactionRequest.getAmount());
                    transaction.setLocalDate(LocalDateTime.now());
                    transaction.setDescription(transactionRequest.getDescription());
                    transaction.setReceiver(transactionRequest.getReceiver());
                    transaction.setType(transactionRequest.getTransactionType());

                    transaction.setWalletId(wallet.getId());

                    List<Transaction> listoftransactions = wallet.getListOfTransactions();

                    listoftransactions.add(transaction);

                    wallet.setListOfTransactions(listoftransactions);

                    walletRepository.saveAndFlush(wallet);

                    transactionRepository.saveAndFlush(transaction);

            return TransactionResponse.builder()
                    .amount(transaction.getAmount())
                    .description(transaction.getDescription())
                    .transactionType(transaction.getType())
                    .build();
    }
}
