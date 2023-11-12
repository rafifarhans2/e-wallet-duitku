package com.enigma.duitku.service.impl;

import com.enigma.duitku.entity.BankAccount;
import com.enigma.duitku.entity.Transaction;
import com.enigma.duitku.entity.User;
import com.enigma.duitku.entity.Wallet;
import com.enigma.duitku.exception.BankAccountException;
import com.enigma.duitku.exception.UserException;
import com.enigma.duitku.exception.WalletException;
import com.enigma.duitku.model.request.TransactionRequest;
import com.enigma.duitku.model.request.UserWallet;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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
    @Transactional
    public TransactionResponse transferMoney(TransactionRequest request) {
        User sender = userRepository.findByMobileNumber(request.getSenderMobileNumber());
        User receiver = userRepository.findByMobileNumber(request.getReceiverMobileNumber());

        Wallet senderWallet = walletRepository.findByUser(sender);
        Wallet receiverWallet = walletRepository.findByUser(receiver);

        if (sender != null && receiver != null) {
            senderWallet.setBalance(senderWallet.getBalance() - request.getAmount());
            walletRepository.saveAndFlush(senderWallet);

            receiverWallet.setBalance(receiverWallet.getBalance() + request.getAmount());
            walletRepository.saveAndFlush(receiverWallet);

            Transaction transaction = Transaction.builder()
                    .amount(request.getAmount())
                    .description(request.getDescription())
                    .localDate(LocalDateTime.now())
                    .type(request.getTransactionType())
                    .walletId(senderWallet.getId())
                    .receiver(receiver.getMobileNumber())
                    .build();

            return TransactionResponse.builder()
                    .amount(request.getAmount())
                    .receiver(request.getReceiverMobileNumber())
                    .transactionType(transaction.getType())
                    .description(transaction.getDescription())
                    .build();
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "sender or receiver not found");
    }


//        if (sender == null || receiver == null){
//            TransactionResponse response = new TransactionResponse();
//            response.setMessage("Not Found");
//            return response;
//        }
//
//        Wallet senderWallet = walletRepository.findByUser(sender);
//        Wallet receiverWallet = walletRepository.findByUser(receiver);
//
//        if (senderWallet == null || receiverWallet == null){
//            TransactionResponse response = new TransactionResponse();
//            response.setMessage("Wallet Not Found");
//            return response;
//        }
//        if (senderWallet.getBalance().compareTo(request.getAmount()) < 0){
//            TransactionResponse response = new TransactionResponse();
//            response.setMessage("Insufficient Balance");
//            return response;
//        }
//
//        Double newSenderBalance = senderWallet.getBalance() - request.getAmount();
//        Double newReceiverBalance = receiverWallet.getBalance() + request.getAmount();
//
//        senderWallet.setBalance(newSenderBalance);
//        receiverWallet.setBalance(newReceiverBalance);
//
//        Transaction transaction = new Transaction();
//        transaction.setSenderMobileNumber(sender);
//        transaction.setReceiverMobileNumber(receiver);
//        transaction.setAmount(request.getAmount());
//        transaction.setLocalDate(LocalDateTime.now());
//
//        transactionRepository.saveAndFlush(transaction);
//        walletRepository.saveAndFlush(senderWallet);
//        walletRepository.saveAndFlush(receiverWallet);
//
//        TransactionResponse responses = new TransactionResponse();
//        responses.setMessage("Transfer Success");
//       return responses;

    @Override
    public Wallet getById(String id) {
        return walletRepository.findById(id).get();
    }

    @Override
    public String create(UserWallet wallet) {

        User user = new User();
        user.setName(wallet.getUser().getName());
        user.setMobileNumber(wallet.getUser().getMobileNumber());
        user.setEmail(wallet.getUser().getEmail());
        user.setAddress(wallet.getUser().getAddress());
        userRepository.saveAndFlush(user);

        Wallet wallet1 = new Wallet();
        wallet1.setId(wallet.getWallet().getId());
        wallet1.setBalance(1000000.0);
        wallet1.setUser(user);
        walletRepository.saveAndFlush(wallet1);

        return "Create Success";
    }


}
