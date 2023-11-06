package com.enigma.duitku.model.request;


import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TransactionRequest {

    private String receiver;
    private String description;
    private String transactionType;
    private Double amount;
    private LocalDateTime localDate;

}
