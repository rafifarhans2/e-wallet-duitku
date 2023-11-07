package com.enigma.duitku.service.impl;

import com.enigma.duitku.entity.BankAccount;
import com.enigma.duitku.entity.Transaction;
import com.enigma.duitku.entity.User;
import com.enigma.duitku.entity.Wallet;
import com.enigma.duitku.exception.BankAccountException;
import com.enigma.duitku.exception.WalletException;
import com.enigma.duitku.model.request.TransactionRequest;
import com.enigma.duitku.model.request.WalletRequest;
import com.enigma.duitku.model.response.TransactionResponse;
import com.enigma.duitku.model.response.WalletResponse;
import com.enigma.duitku.repository.BankAccountRepository;
import com.enigma.duitku.repository.UserRepository;
import com.enigma.duitku.repository.WalletRepository;
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

    private final TransactionServiceImpl transactionService;

    private final WalletRepository walletRepository;

    @Override
    public WalletResponse addMoneyToWallet(WalletRequest request) throws WalletException, BankAccountException {

        Optional<User> optionalUser = userRepository.findById(request.getMobilePhone());

            if(optionalUser.isPresent()) {
                User user = optionalUser.get();
                Wallet wallet = user.getWallet();
                Double walletAvailableBalance = wallet.getBalance();
                Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(user.getMobilePhone());

                if(optionalBankAccount.isPresent()) {
                    BankAccount bankAccount = optionalBankAccount.get();
                    Double availableBalance = bankAccount.getBalance();

                    if(availableBalance >= request.getAmount()) {
                        wallet.setBalance(walletAvailableBalance + request.getAmount());

                        TransactionRequest transaction = new TransactionRequest();
                        user.getMobilePhone();
                        transaction.setDescription("Wallet Top Up");
                        transaction.setTransactionType("E-Wallet Transaction");
                        transaction.setAmount(request.getAmount());
                        transactionService.addTransaction(transaction);

                        if(transaction != null) {
                            bankAccount.setBalance(availableBalance - request.getAmount());
                            bankAccountRepository.saveAndFlush(bankAccount);
                            walletRepository.saveAndFlush(wallet);
                        } else {
                            throw new TransactionException("Sorry Transaction Failed");
                        }

                    } else {
                        throw new WalletException("Insufficient Funds!" + availableBalance);
                    }

                } else {
                    throw new BankAccountException("No Registered Bank Account Found With This Mobile Number " + user.getMobilePhone());
                }
            }

        return WalletResponse.builder()
                .amount(request.getAmount())
                .description(request.getDescription())
                .build();
    }

    @Override
    public WalletResponse transferMoney(WalletRequest request) {

        Optional<User> optionalUser = userRepository.findById(request.getMobilePhone());

        if(optionalUser.isPresent()) {
            Optional<User> optionalTargetUser= userRepository.findById(request.getTargetMobileNumber());

            if(optionalTargetUser.isPresent()) {
                User user = optionalUser.get();
                User targetUser =optionalTargetUser.get();
                Wallet wallet = user.getWallet();
                Wallet targetWallet = targetUser.getWallet();
                Double availableBalance = wallet.getBalance();
                Double targetAvailableBalance=targetWallet.getBalance();
                List<Transaction> targetListOfTransaction = targetWallet.getListOfTransactions();

                if(availableBalance >= request.getAmount()) {

                   TransactionRequest transaction =new TransactionRequest();
                   transaction.setTargetMobilePhone(request.getMobilePhone());
                   transaction.setDescription(request.getDescription());
                   transaction.setTransactionType("E-Wallet Transaction");
                   transaction.setAmount(request.getAmount());

                   transactionService.addTransaction(transaction);

                    if(transaction != null) {
                        wallet.setBalance(availableBalance - request.getAmount());
                        targetListOfTransaction.add(transaction);

                        targetWallet.setBalance(targetAvailableBalance + request.getAmount());
                        targetWallet.setListOfTransactions(targetListOfTransaction);
                        walletRepository.saveAndFlush(wallet);
                        walletRepository.saveAndFlush(targetWallet);
                    }

                }
            }

        }

        return WalletResponse.builder()

                .build();
    }
}
