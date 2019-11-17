package com.farukkaradeniz.ideabulb.controllers;

import com.farukkaradeniz.ideabulb.models.dtos.UserDTO;
import com.farukkaradeniz.ideabulb.models.entities.User;
import com.farukkaradeniz.ideabulb.models.models.LoginModel;
import com.farukkaradeniz.ideabulb.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationController extends BaseController {

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signUp(
            @RequestBody User user
            ) {
        UserDTO newUser = authenticationService.signUp(user);
        return new ResponseEntity<>(newUser, HttpStatus.ACCEPTED);
    }


    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginModel loginModel
            ) {
        String jwtToken = authenticationService.signIn(loginModel);
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }


}
