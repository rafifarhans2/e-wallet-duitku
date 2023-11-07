package com.enigma.duitku.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class WalletRequest {

    private String mobilePhone;
    private String targetMobileNumber;
    private Double amount;
    private String description;
}
