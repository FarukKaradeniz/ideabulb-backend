package com.farukkaradeniz.ideabulb.services.impl;

import com.farukkaradeniz.ideabulb.models.entities.User;
import com.farukkaradeniz.ideabulb.repositories.UserRepository;
import com.farukkaradeniz.ideabulb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean isUserExist(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }


}
