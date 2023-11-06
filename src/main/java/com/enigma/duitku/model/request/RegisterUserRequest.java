package com.enigma.duitku.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RegisterUserRequest {

    private String email;
    private String password;
    private String username;
    private String mobilePhone;
}
