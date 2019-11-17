package com.farukkaradeniz.ideabulb.services.impl;

import com.farukkaradeniz.ideabulb.models.dtos.UserDTO;
import com.farukkaradeniz.ideabulb.models.entities.User;
import com.farukkaradeniz.ideabulb.models.entities.UserImage;
import com.farukkaradeniz.ideabulb.models.mappers.UserMapper;
import com.farukkaradeniz.ideabulb.models.models.LoginModel;
import com.farukkaradeniz.ideabulb.repositories.UserRepository;
import com.farukkaradeniz.ideabulb.security.JwtTokenProvider;
import com.farukkaradeniz.ideabulb.services.AuthenticationService;
import com.farukkaradeniz.ideabulb.services.UserImageService;
import com.farukkaradeniz.ideabulb.utils.generators.UUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserImageService userImageService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private Logger LOG = LoggerFactory.getLogger(AuthenticationService.class);

    @Override
    public String signIn(LoginModel loginModel) {
        LOG.info("Attempting sign in action for user (" + loginModel.getUsername() + ":" + loginModel.getPassword() + ").");
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginModel.getUsername(), loginModel.getPassword()));
        String jwtToken = jwtTokenProvider.createToken(loginModel.getUsername(), authenticate.getAuthorities());
        LOG.info("Token created: " + jwtToken);
        return jwtToken;
    }

    @Override
    public UserDTO signUp(User user) {
        user.setId(user.getId() == null ? UUIDGenerator.generateUUIDString() : user.getId());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        LOG.info("User " + user.getUsername() + " saved to the database.");
        UserImage defaultUserImage = new UserImage(user.getId(), savedUser);
        UserImage savedImage = userImageService.save(defaultUserImage);
        savedUser.setUserImage(savedImage);
        return UserMapper.convertToUserDTO(savedUser);
    }
}
