package com.enigma.duitku.model.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TransactionResponse {

    String receiver;
    String description;
    String transactionType;
    Double amount;

}
