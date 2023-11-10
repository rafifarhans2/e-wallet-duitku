package com.enigma.duitku.service.impl;

import com.enigma.duitku.entity.Bill;
import com.enigma.duitku.entity.Transaction;
import com.enigma.duitku.model.request.BillRequest;
import com.enigma.duitku.model.response.TransactionResponse;
import com.enigma.duitku.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {

    @Override
    @Transactional(rollbackOn = Exception.class)
    public TransactionResponse billPayment(BillRequest request) {

        return TransactionResponse.builder()
                .build();
    }

    @Override
    public List<Bill> viewBillPayments() {
        return null;
    }
}

