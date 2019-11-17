package com.farukkaradeniz.ideabulb.security;

import com.farukkaradeniz.ideabulb.models.entities.User;
import com.farukkaradeniz.ideabulb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class MyUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = userRepository.findByUsername(username);

        if (byUsername == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(byUsername.getPassword())
                .authorities(Arrays.stream(
                        byUsername.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList()))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
