package com.enigma.duitku.service;

import com.enigma.duitku.exception.UserException;
import com.enigma.duitku.model.request.BankAccountRequest;
import com.enigma.duitku.model.response.BankAccountResponse;

public interface BankAccountService {

    BankAccountResponse addAccount(BankAccountRequest request);

    BankAccountResponse viewBankBalance(BankAccountRequest request);

}
