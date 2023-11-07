package com.enigma.duitku.service.impl;

import com.enigma.duitku.entity.Transaction;
import com.enigma.duitku.entity.User;
import com.enigma.duitku.entity.Wallet;
import com.enigma.duitku.exception.UserException;
import com.enigma.duitku.model.request.TransactionRequest;
import com.enigma.duitku.model.response.TransactionResponse;
import com.enigma.duitku.repository.TransactionRepository;
import com.enigma.duitku.repository.WalletRepository;
import com.enigma.duitku.service.TransactionService;
import com.enigma.duitku.service.UserService;
import com.enigma.duitku.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final WalletRepository walletRepository;

    private final UserService userService;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public TransactionResponse addTransaction(TransactionRequest transactionRequest) {

            User user = getUserFromRequest(transactionRequest);

            Wallet wallet = user.getWallet();

            Transaction transaction = new Transaction();

                    transaction.setWalletId(wallet.getId());
                    transaction.setAmount(transactionRequest.getAmount());
                    transaction.setLocalDate(LocalDateTime.now());
                    transaction.setDescription(transactionRequest.getDescription());
                    transaction.setReceiver(transactionRequest.getReceiver());
                    transaction.setType(transactionRequest.getTransactionType());


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

    private User getUserFromRequest(TransactionRequest request) {
        User user = userService.getById(request.getUserId());
        if (user == null) {
            throw new RuntimeException("User not found for ID: " + request.getUserId());
        }
        return user;
    }
}
