package com.enigma.duitku.service;

import com.enigma.duitku.entity.User;

import java.util.List;

public interface UserService {

    User create(User  user);
    User getById(String id);
    List<User> getAll();
    User update(User user);
    void deleteById(String id);

}
