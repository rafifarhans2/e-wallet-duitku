package com.enigma.duitku.repository;

import com.enigma.duitku.entity.BankAccount;
import com.enigma.duitku.model.request.BankAccountRequest;
import com.enigma.duitku.model.response.BankAccountResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, String> {



}
