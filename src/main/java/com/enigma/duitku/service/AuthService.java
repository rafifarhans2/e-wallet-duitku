package com.enigma.duitku.service;


import com.enigma.duitku.model.request.AuthRequest;
import com.enigma.duitku.model.response.LoginResponse;
import com.enigma.duitku.model.response.RegisterResponse;

public interface AuthService {

    RegisterResponse registerUsers(AuthRequest authRequest);
    RegisterResponse registerAdmin(AuthRequest authRequest);
    LoginResponse login(AuthRequest request);
}
