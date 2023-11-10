package com.enigma.duitku.service;

import com.enigma.duitku.entity.BankAccount;
import com.enigma.duitku.entity.User;
import com.enigma.duitku.exception.UserException;
import com.enigma.duitku.model.request.BankAccountRequest;
import com.enigma.duitku.model.response.BankAccountResponse;

import java.util.List;

public interface BankAccountService {

    BankAccountResponse addAccount(BankAccountRequest request);

    BankAccount getById(String id);

    BankAccountResponse removeAccountBank(User user);
    BankAccountResponse topUpWallet(BankAccountRequest request);

}
