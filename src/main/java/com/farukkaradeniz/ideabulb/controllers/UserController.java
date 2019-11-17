package com.farukkaradeniz.ideabulb.controllers;

import com.farukkaradeniz.ideabulb.models.dtos.UserDTO;
import com.farukkaradeniz.ideabulb.models.entities.User;
import com.farukkaradeniz.ideabulb.models.mappers.UserMapper;
import com.farukkaradeniz.ideabulb.utils.Constants;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Constants.API_V1 + "/users")
public class UserController extends BaseController {


    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    @GetMapping("/{username}")
    public UserDTO getUserAuthenticated(@PathVariable("username") String username) {
        LOG.info("authenticated çalıştı");
        String currentUsername = getCurrentUsername();
        User user = userService.findByUsername(username);
        return UserMapper.convertToUserDTO(user);
    }

}
