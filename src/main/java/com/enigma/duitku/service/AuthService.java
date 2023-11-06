package com.enigma.duitku.service;


import com.enigma.duitku.model.request.AuthRequest;
import com.enigma.duitku.model.request.RegisterUserRequest;
import com.enigma.duitku.model.response.LoginResponse;
import com.enigma.duitku.model.response.RegisterResponse;

public interface AuthService {

    RegisterResponse register(AuthRequest authRequest);
    RegisterResponse registerAdmin(AuthRequest authRequest);
    RegisterResponse registerUsers(RegisterUserRequest request);
    LoginResponse login(AuthRequest request);
}
