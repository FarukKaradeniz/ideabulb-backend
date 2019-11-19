package com.farukkaradeniz.ideabulb.controllers;

import com.farukkaradeniz.ideabulb.models.dtos.UserDTO;
import com.farukkaradeniz.ideabulb.models.entities.User;
import com.farukkaradeniz.ideabulb.models.entities.UserImage;
import com.farukkaradeniz.ideabulb.models.mappers.UserMapper;
import com.farukkaradeniz.ideabulb.utils.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;

@RestController
@RequestMapping(value = Constants.API_V1 + "/users")
public class UserController extends BaseController {

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUser(
            @PathVariable("username") String username
    ) {
        User user = userService.findByUsername(username);
        // if user is deactivated, some fields will be censored
        if (!user.isActive()){
            user.setId(Constants.DEACTIVATED_USER);
            user.setEmail(Constants.DEACTIVATED_USER);
            user.setUserImage(new UserImage(user.getId(), user));
            user.setPosts(Collections.emptyList());
            user.setComments(Collections.emptyList());
        }
        return new ResponseEntity<>(UserMapper.convertToUserDTO(user), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @PutMapping("/{username}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable("username") String username,
            @RequestBody HashMap<String, String> fields
    ){
        if (getCurrentUserRoles().contains(Constants.ROLE_USER) && username.equals(getCurrentUsername())) {
            UserDTO userDTO = UserMapper.convertToUserDTO(userService.updateUser(username, fields));
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @DeleteMapping("/{username}")
    public ResponseEntity<String> setActivateUser(
            @PathVariable("username") String username,
            @RequestParam("active") Boolean active
    ) {
        if (getCurrentUserRoles().contains(Constants.ROLE_USER) && username.equals(getCurrentUsername())) {
            return new ResponseEntity<>(userService.setUserActive(username, active), HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
    }



}
