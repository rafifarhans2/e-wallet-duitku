package com.enigma.duitku.service.impl;

import com.enigma.duitku.entity.BankAccount;
import com.enigma.duitku.entity.Transaction;
import com.enigma.duitku.entity.User;
import com.enigma.duitku.entity.Wallet;
import com.enigma.duitku.exception.BankAccountException;
import com.enigma.duitku.exception.UserException;
import com.enigma.duitku.exception.WalletException;
import com.enigma.duitku.model.request.TransactionRequest;
import com.enigma.duitku.model.request.WalletRequest;
import com.enigma.duitku.model.response.TransactionResponse;
import com.enigma.duitku.model.response.WalletResponse;
import com.enigma.duitku.repository.BankAccountRepository;
import com.enigma.duitku.repository.TransactionRepository;
import com.enigma.duitku.repository.UserRepository;
import com.enigma.duitku.repository.WalletRepository;
import com.enigma.duitku.service.TransactionService;
import com.enigma.duitku.service.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletServiceImpl implements WalletService {

    private final UserRepository userRepository;

    private final BankAccountRepository bankAccountRepository;

    private final TransactionRepository transactionRepository;

    private final WalletRepository walletRepository;

    @Override
    public TransactionResponse transferMoney(TransactionRequest request) {
        if (request.getAmount() <= 0) {
            throw new IllegalArgumentException("Jumlah transfer harus lebih dari 0");
        }

        Transaction transaction = new Transaction();
        transaction.setReceiver(request.getReceiver());
        transaction.setAmount(request.getAmount());
        transaction.setDescription(request.getDescription());
        transaction.setType(request.getTransactionType());
        transaction.setLocalDate(request.getLocalDate());

        transaction = transactionRepository.saveAndFlush(transaction);

        // Continue with any additional logic or validation if needed

        return mapToTransactionResponse(transaction);
    }

    private TransactionResponse mapToTransactionResponse(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getType(),
                transaction.getLocalDate()
        );
    }


    @Override
    public Wallet getById(String id) {
        return walletRepository.findById(id).get();
    }

}
