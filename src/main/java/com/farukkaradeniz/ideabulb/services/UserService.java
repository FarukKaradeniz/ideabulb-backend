package com.farukkaradeniz.ideabulb.services;

import com.farukkaradeniz.ideabulb.models.entities.User;

public interface UserService {
    User findByUsername(String username);
    boolean isUserExist(String username);
    User findByUsernameAndPassword(String username, String password);
}
