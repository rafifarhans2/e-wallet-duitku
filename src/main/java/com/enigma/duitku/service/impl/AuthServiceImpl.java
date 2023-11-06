package com.enigma.duitku.service.impl;

import com.enigma.duitku.entity.Role;
import com.enigma.duitku.entity.User;
import com.enigma.duitku.entity.UserCredential;
import com.enigma.duitku.entity.UserDetailImpl;
import com.enigma.duitku.entity.constant.ERole;
import com.enigma.duitku.model.request.AuthRequest;
import com.enigma.duitku.model.request.RegisterUserRequest;
import com.enigma.duitku.model.response.LoginResponse;
import com.enigma.duitku.model.response.RegisterResponse;
import com.enigma.duitku.repository.UserCredentialRepository;
import com.enigma.duitku.security.BCryptUtils;
import com.enigma.duitku.security.JwtUtils;
import com.enigma.duitku.service.AuthService;
import com.enigma.duitku.service.RoleService;
import com.enigma.duitku.service.UserService;
import com.enigma.duitku.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserCredentialRepository userCredentialRepository;
    private final BCryptUtils bCryptUtils;
    private final UserService userService;
    private final RoleService roleService;

    private final ValidationUtil validationUtil;
    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public RegisterResponse register(AuthRequest authRequest) {
        try {
            Role role = roleService.getOrSave(ERole.ROLE_USER);
            UserCredential credential= UserCredential.builder()
                    .email(authRequest.getEmail())
                    .password(bCryptUtils.hashPassword(authRequest.getPassword()))
                    .roles(List.of(role))
                    .build();
            userCredentialRepository.saveAndFlush(credential);

            User user = User.builder()
                    .name(authRequest.getName())
                    .address(authRequest.getAddress())
                    .mobilePhone(authRequest.getMobilePhone())
                    .email(authRequest.getEmail())
                    .userCredential(credential)
                    .build();
            userService.create(user);

            return RegisterResponse.builder()
                    .email(credential.getEmail())
                    .build();

        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user already exists");

        }
    }

    @Override
    public RegisterResponse registerAdmin(AuthRequest authRequest) {
        return null;
    }

    @Override
    public RegisterResponse registerUsers(RegisterUserRequest request) {
        return null;
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        String token = jwtUtils.generateToken(userDetails.getEmail());
        return LoginResponse.builder()
                .email(userDetails.getEmail())
                .roles(roles)
                .token(token)
                .build();
    }

}
