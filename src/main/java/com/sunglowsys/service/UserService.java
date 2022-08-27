package com.sunglowsys.service;

import com.sunglowsys.domain.User;

import java.util.List;

public interface UserService {

    User save(User user);

    User update(User user);

    List<User> findAll();

    User findById(Long id);

    User findByUsername(String username);

    void delete(Long id);
}
