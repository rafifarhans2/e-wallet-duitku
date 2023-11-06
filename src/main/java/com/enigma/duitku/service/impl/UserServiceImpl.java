package com.enigma.duitku.service.impl;

import com.enigma.duitku.entity.Admin;
import com.enigma.duitku.entity.User;
import com.enigma.duitku.repository.AdminRepository;
import com.enigma.duitku.repository.UserRepository;
import com.enigma.duitku.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AdminRepository adminRepository;

    @Override
    public User create(User user) {
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email already used");
        }
    }

    @Override
    public Admin create(Admin admin) {
        try {
            return adminRepository.save(admin);
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "email already used");
        }
    }

    @Override
    public User getById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found"));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        User currentUser= getById(user.getId());
        if (currentUser!= null) {
            return userRepository.save(user);
        } else {
            throw new RuntimeException("Pelanggan dengan ID " + user.getId() + " tidak ditemukan. Tidak dapat melakukan pembaruan.");
        }
    }

    @Override
    public void deleteById(String id) {
        User user= getById(id);
        userRepository.delete(user);
    }
}
