package com.enigma.duitku.model.request;


import com.enigma.duitku.entity.Transaction;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest extends Transaction {

    private String mobilePhone;
    //private String targetMobilePhone;
    private String receiver;
    private String description;
    private String transactionType;
    private Double amount;
    private LocalDateTime localDate;
}
