package com.farukkaradeniz.ideabulb.services;

import com.farukkaradeniz.ideabulb.models.dtos.UserDTO;
import com.farukkaradeniz.ideabulb.models.entities.User;
import com.farukkaradeniz.ideabulb.models.models.LoginModel;

public interface AuthenticationService {
    String signIn(LoginModel loginModel);
    UserDTO signUp(User user);
}
