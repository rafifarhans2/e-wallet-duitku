package com.enigma.duitku.service.impl;

import com.enigma.duitku.entity.BankAccount;
import com.enigma.duitku.entity.User;
import com.enigma.duitku.exception.UserException;
import com.enigma.duitku.model.request.BankAccountRequest;
import com.enigma.duitku.model.response.BankAccountResponse;
import com.enigma.duitku.repository.BankAccountRepository;
import com.enigma.duitku.repository.UserRepository;
import com.enigma.duitku.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final UserRepository userRepository;

    private final BankAccountRepository bankAccountRepository;

    @Override
    public BankAccountResponse addAccount(BankAccountRequest request) {

        Optional<User> optionalUser = userRepository.findById(request.getMobileNumber());

        if(optionalUser.isPresent()) {

            User user = optionalUser.get();

            BankAccount bankAccount = new BankAccount();
            bankAccount.setId(user.getMobilePhone());
            bankAccount.setAccountNo(request.getAccountNo());
            bankAccount.setBalance(request.getBalance());
            bankAccount.setBankName(request.getBankName());

            bankAccountRepository.saveAndFlush(bankAccount);

            return BankAccountResponse.builder()
                    .mobileNumber(request.getMobileNumber())
                    .bankName(request.getBankName())
                    .accountNo(request.getAccountNo())
                    .balance(request.getBalance())
                    .build();

        } else {
            return BankAccountResponse.builder()
                    .errors("Invalid nomor mobile, Please Enter Your Registered  Mobile Number!")
                    .build();
        }
    }

    @Override
    public BankAccountResponse viewProfile(BankAccountRequest request) {

        Optional<BankAccount> optionalBankAccount = bankAccountRepository.findById(request.getMobileNumber());

        if(optionalBankAccount.isPresent()) {
            BankAccount bankAccount = optionalBankAccount.get();

            return BankAccountResponse.builder()
                    .mobileNumber(bankAccount.getId())
                    .bankName(bankAccount.getBankName())
                    .accountNo(bankAccount.getAccountNo())
                    .build();
        }

        return BankAccountResponse.builder()
                .errors("Mobile number not found")
                .build();
    }

    @Override
    public BankAccountResponse viewBankBalance(BankAccountRequest request) {
        Optional<BankAccount> optionalBankAccount= bankAccountRepository.findById(request.getMobileNumber());

        if(optionalBankAccount.isPresent()) {
            BankAccount bankAccount = optionalBankAccount.get();
            double balance = bankAccount.getBalance();
            return BankAccountResponse.builder()
                    .balance(request.getBalance())
                    .build();
        } else {
            return BankAccountResponse.builder()
                    .errors("Mobile number not found")
                    .build();
        }
    }
}
