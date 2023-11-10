package com.enigma.duitku.service;

import com.enigma.duitku.entity.Bill;
import com.enigma.duitku.model.request.BillRequest;
import com.enigma.duitku.model.response.TransactionResponse;

import java.util.List;

public interface BillService {

    TransactionResponse billPayment(BillRequest request);

    List<Bill> viewBillPayments();
}
