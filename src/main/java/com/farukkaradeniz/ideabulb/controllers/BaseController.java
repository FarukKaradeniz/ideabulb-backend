package com.farukkaradeniz.ideabulb.controllers;

import com.farukkaradeniz.ideabulb.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class BaseController {
    protected Logger LOG = LoggerFactory.getLogger(this.getClass().getName());
    @Autowired
    protected UserService userService;

    protected String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((User)authentication.getPrincipal()).getUsername();
    }

}
