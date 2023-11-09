package com.enigma.duitku.model.request;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest  {

    private String mobileNumber;
    private String receiver;
    private String description;
    private String transactionType;
    private Double amount;
    private LocalDateTime localDate;
    private String userId;
}
