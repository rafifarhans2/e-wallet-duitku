package com.enigma.duitku.model.request;

import com.enigma.duitku.entity.User;
import com.enigma.duitku.entity.Wallet;
import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWallet {

    private User user;
    private Wallet wallet;
}
