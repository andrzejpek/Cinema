package com.patand.Cinema.service.impl;


import com.patand.Cinema.model.Role;
import com.patand.Cinema.model.User;
import com.patand.Cinema.repository.RoleRepository;
import com.patand.Cinema.repository.UserRepository;
import com.patand.Cinema.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;


    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role role = roleRepository.findByRoleType("ROLE_USER");
        user.setRole(role);
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void update(User user) {
        userRepository.findById(user.getId()).ifPresent(updateUser -> {
            updateUser.setName(user.getName());
            updateUser.setSurname(user.getSurname());
            updateUser.setEmail(user.getEmail());
            userRepository.save(updateUser);
        });
    }

    @Override
    public void forgetPassword(User user, Map<String, String> requestParam) {
        userRepository.findById(user.getId()).ifPresent(updateUserPassword -> {
           updateUserPassword.setPassword(bCryptPasswordEncoder.encode(requestParam.get("password")));
           updateUserPassword.setResetToken(null);
           userRepository.save(updateUserPassword);
        });
    }

    @Override
    public void saveToken(User user) {
        userRepository.findById(user.getId()).ifPresent(saveToken -> {
            saveToken.setResetToken(user.getResetToken());
            userRepository.save(saveToken);
        });
    }

    @Override
    public User findResetToken(String token) {
        return userRepository.findByResetToken(token);
    }
}
