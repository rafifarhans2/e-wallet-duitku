package com.enigma.duitku.service.impl;

import com.enigma.duitku.entity.Role;

import com.enigma.duitku.entity.User;
import com.enigma.duitku.entity.UserCredential;
import com.enigma.duitku.entity.constant.ERole;
import com.enigma.duitku.exception.UserException;
import com.enigma.duitku.model.request.AuthRequest;
import com.enigma.duitku.model.response.RegisterResponse;
import com.enigma.duitku.repository.UserCredentialRepository;
import com.enigma.duitku.security.BCryptUtils;
import com.enigma.duitku.security.JwtUtils;
import com.enigma.duitku.service.RoleService;
import com.enigma.duitku.service.UserService;
import com.enigma.duitku.util.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
class AuthServiceImplTest {

    @Mock
    private UserCredentialRepository userCredentialRepository;

    @Mock
    private BCryptUtils bCryptUtils;

    @Mock
    private UserService userService;

    @Mock
    private RoleService roleService;

    @Mock
    private ValidationUtil validationUtil;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtils jwtUtils;

    private AuthServiceImpl authService;

    @BeforeEach
    void setUp() {
        authService = new AuthServiceImpl(userCredentialRepository, bCryptUtils, userService, roleService, validationUtil, authenticationManager, jwtUtils);
    }

    @Test
    public void testRegisterUsers() throws UserException {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("dim4211@gmail.com");
        authRequest.setPassword("apakahkamutau");
        authRequest.setName("dimdoang");
        authRequest.setAddress("jalan-jalan teros");
        authRequest.setMobileNumber("08954353434");


        Role role = new Role();
        role.setRole(ERole.ROLE_USER);

        UserCredential userCredential = new UserCredential();
        userCredential.setMobileNumber("089543534534");

        User user = new User();
        user.setMobileNumber("089543534534");



        when(roleService.getOrSave(ERole.ROLE_USER)).thenReturn(role);
        when(bCryptUtils.hashPassword(authRequest.getPassword())).thenReturn(authRequest.getPassword());
        when(userCredentialRepository.saveAndFlush(any(UserCredential.class))).thenReturn(userCredential);
        when(userService.create(any(User.class))).thenReturn(user);


        RegisterResponse response = authService.registerUsers(authRequest);

        verify(roleService).getOrSave(ERole.ROLE_USER);
        verify(bCryptUtils).hashPassword(authRequest.getPassword());
        verify(userCredentialRepository).saveAndFlush(any(UserCredential.class));
        verify(userService).create(any(User.class));


        assertEquals("dim4211@gmail.com", response.getEmail());
        assertEquals("08954353434", response.getMobileNumber());
        assertEquals(null, response.getBalance());
    }

    @Test
    void testRegisterAdmin() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("dim4211@gmail.com");
        authRequest.setPassword("apakahkamutau");
        authRequest.setName("ajidim");

        Role role = new Role();
        role.setRole(ERole.ROLE_ADMIN);

        UserCredential userCredential = new UserCredential();
        userCredential.setMobileNumber(authRequest.getEmail());
        userCredential.setPassword(authRequest.getPassword());
        userCredential.setRoles(Collections.singletonList(role));

        when(roleService.getOrSave(ERole.ROLE_ADMIN)).thenReturn(role);
        when(bCryptUtils.hashPassword(authRequest.getPassword())).thenReturn(authRequest.getPassword());
        when(userCredentialRepository.saveAndFlush(any(UserCredential.class))).thenReturn(userCredential);

        RegisterResponse response = authService.registerAdmin(authRequest);

        assertEquals(authRequest.getEmail(), response.getEmail());

        verify(roleService, times(1)).getOrSave(ERole.ROLE_ADMIN);
        verify(bCryptUtils, times(1)).hashPassword(authRequest.getPassword());
        verify(userCredentialRepository, times(1)).saveAndFlush(any(UserCredential.class));

    }

    @Test
    void testRegisterUsersWithConflict() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("dim4211@gmail.com");
        authRequest.setPassword("apakahkamutau");
        authRequest.setName("dimdoang");
        authRequest.setAddress("jalan-jalan teros");
        authRequest.setMobileNumber("08954353434");

        Role role = new Role();
        role.setRole(ERole.ROLE_USER);

        when(roleService.getOrSave(ERole.ROLE_USER)).thenReturn(role);
        when(bCryptUtils.hashPassword(authRequest.getPassword())).thenReturn(authRequest.getPassword());
        when(userCredentialRepository.saveAndFlush(any(UserCredential.class))).thenThrow(DataIntegrityViolationException.class);

        ResponseStatusException exception = org.junit.jupiter.api.Assertions.assertThrows(ResponseStatusException.class, () -> {
            authService.registerUsers(authRequest);
        });

        assertEquals(HttpStatus.CONFLICT, exception.getStatus());
        assertEquals("user already exists", exception.getReason());

        verify(roleService, times(1)).getOrSave(ERole.ROLE_USER);
        verify(bCryptUtils, times(1)).hashPassword(authRequest.getPassword());
        verify(userCredentialRepository, times(1)).saveAndFlush(any(UserCredential.class));
    }

    @Test
    void testRegisterAdminWithConflict() {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setEmail("dim4211@gmail.com");
        authRequest.setPassword("apakahkamutau");
        authRequest.setName("dimdoang");


        Role role = new Role();
        role.setRole(ERole.ROLE_ADMIN);

        when(roleService.getOrSave(ERole.ROLE_ADMIN)).thenReturn(role);
        when(bCryptUtils.hashPassword(authRequest.getPassword())).thenReturn(authRequest.getPassword());
        when(userCredentialRepository.saveAndFlush(any(UserCredential.class))).thenThrow(DataIntegrityViolationException.class);

        ResponseStatusException exception = org.junit.jupiter.api.Assertions.assertThrows(ResponseStatusException.class, () -> {
            authService.registerAdmin(authRequest);
        });

        assertEquals(HttpStatus.CONFLICT, exception.getStatus());
        assertEquals("admin already exists", exception.getReason());

        verify(roleService, times(1)).getOrSave(ERole.ROLE_ADMIN);
        verify(bCryptUtils, times(1)).hashPassword(authRequest.getPassword());
        verify(userCredentialRepository, times(1)).saveAndFlush(any(UserCredential.class));
    }
}