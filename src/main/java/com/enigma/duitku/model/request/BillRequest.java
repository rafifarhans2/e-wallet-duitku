package com.enigma.duitku.model.request;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class BillRequest {

    private String consumerNo;
    private String billType;
    private Double amount;
    private String receiver;
}
