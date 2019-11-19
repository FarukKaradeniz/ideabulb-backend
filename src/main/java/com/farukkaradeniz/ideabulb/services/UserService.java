package com.farukkaradeniz.ideabulb.services;

import com.farukkaradeniz.ideabulb.models.entities.User;

import java.util.HashMap;

public interface UserService {
    User findByUsername(String username);
    boolean isUserExist(String username);
    User findByUsernameAndPassword(String username, String password);
    User updateUser(String username, HashMap<String, String> fields);
    String setUserActive(String username, boolean active);
}
