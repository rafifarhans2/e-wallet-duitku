package com.enigma.duitku.service;

import com.enigma.duitku.entity.Admin;
import com.enigma.duitku.entity.User;

import java.util.List;

public interface UserService {

    User create(User  user);
    Admin create (Admin admin);
    User getById(String id);
    List<User> getAll();
    User update(User user);
    void deleteById(String id);

}
