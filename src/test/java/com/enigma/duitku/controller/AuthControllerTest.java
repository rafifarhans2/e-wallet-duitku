package com.enigma.duitku.controller;

import com.enigma.duitku.model.request.AuthRequest;
import com.enigma.duitku.model.response.RegisterResponse;
import com.enigma.duitku.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthService authService;

    @Test
    public void testRegisterUsers() throws Exception {

        AuthRequest authRequest = AuthRequest.builder()
                .email("dim3214@gmail.com")
                .password("apaya")
                .name("dimas")
                .address("jalan terus")
                .mobilePhone("08953245345")
                .build();
        RegisterResponse registerResponse = RegisterResponse.builder()
                .email("dim3214@gmail.com")
                .mobilePhone("08953245345")
                .build();


        when(authService.registerUsers(any(AuthRequest.class))).thenReturn(registerResponse);


        mockMvc.perform(post("/api/auth/register/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(201))
                .andExpect(jsonPath("$.message").value("Successfully registered user"))
                .andExpect(jsonPath("$.data.email").value("dim3214@gmail.com"))
                .andExpect(jsonPath("$.data.mobilePhone").value("08953245345"));


        verify(authService, times(1)).registerUsers(any(AuthRequest.class));
    }

    @Test
    public void testRegisterAdmin() throws Exception {
        AuthRequest authRequest = AuthRequest.builder()
                .email("dim3214@gmail.com")
                .password("apaya")
                .name("dimas")
                .address("jalan terus")
                .mobilePhone("08953245345")
                .build();
        RegisterResponse registerResponse = RegisterResponse.builder()
                .email("dim3214@gmail.com")
                .mobilePhone("08953245345")
                .build();


        when(authService.registerAdmin(any(AuthRequest.class))).thenReturn(registerResponse);


        mockMvc.perform(post("/api/auth/register/admin")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value(201))
                .andExpect(jsonPath("$.message").value("Successfully registered admin"))
                .andExpect(jsonPath("$.data.email").value("dim3214@gmail.com"))
                .andExpect(jsonPath("$.data.mobilePhone").value("08953245345"));

        verify(authService, times(1)).registerAdmin(any(AuthRequest.class));
    }
}