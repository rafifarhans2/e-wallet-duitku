package com.enigma.duitku.model.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TransactionRequest {

    String receiver;
    String description;
    String transactionType;
    Double amount;

}
