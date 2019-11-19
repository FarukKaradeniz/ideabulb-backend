package com.farukkaradeniz.ideabulb.services.impl;

import com.farukkaradeniz.ideabulb.models.entities.User;
import com.farukkaradeniz.ideabulb.models.entities.UserImage;
import com.farukkaradeniz.ideabulb.repositories.UserRepository;
import com.farukkaradeniz.ideabulb.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private Logger LOG = LoggerFactory.getLogger(UserService.class);

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

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


    /**
     * User can update password, userImage and email fields
     * @param username Username of the user's
     * @param fields Modified fields
     * @return Saved and updated user
     */
    @Override
    public User updateUser(String username, HashMap<String, String> fields) {
        User byUsername = findByUsername(username);
        if (fields.containsKey("password")) {
            // TODO Should validate password here
            byUsername.setPassword(passwordEncoder.encode(fields.get("password")));
        }
        if (fields.containsKey("user_image")) {
            UserImage userImage = byUsername.getUserImage();
            userImage.setFullImage(fields.get("user_image"));
            // TODO Make thumbnail image here then set
            userImage.setThumbnail(fields.get("user_image"));
        }
        if (fields.containsKey("email")) {
            // TODO should validate email here
            byUsername.setEmail(fields.get("email"));
        }
        User saved = userRepository.save(byUsername);
        LOG.info("User " + username + "'s (" + fields.keySet().toString() +  ") fields updated.");
        return saved;
    }


    /**
     * User can set activation to true or false
     * @param username User to be modified
     * @param active Activation
     * @return Modified user's username
     */
    @Override
    public String setUserActive(String username, boolean active) {
        User byUsername = findByUsername(username);
        byUsername.setActive(active);
        User updated = userRepository.save(byUsername);
        LOG.info("User " + updated.getUsername() + " activation to " + active + ".");
        return updated.getUsername();
    }
}
