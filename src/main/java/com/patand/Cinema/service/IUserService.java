package com.patand.Cinema.service;

import com.patand.Cinema.model.User;

import java.util.List;
import java.util.Map;

public interface IUserService {
    void saveUser(User user);
    User findByEmail(String email);
    User findById(Long userId);
    void update(User user);
    void forgetPassword(User user, Map<String, String> requestParam);
    void saveToken(User user);
    User findResetToken(String token);
    List<User> showUser();
}