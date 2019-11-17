package com.farukkaradeniz.ideabulb.models.mappers;

import com.farukkaradeniz.ideabulb.models.dtos.UserDTO;
import com.farukkaradeniz.ideabulb.models.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    private static Logger log = LoggerFactory.getLogger(UserMapper.class);

    public static UserDTO convertToUserDTO(User user) {
        UserDTO dto = new UserDTO(user.getId(),
                user.getUsername(),
                user.getEmail(),
                UserImageMapper.convertToUserImageDTO(user.getUserImage()),
                user.getPosts().stream().map(PostMapper::convertToPostDTO).collect(Collectors.toList()));

        log.info("User Entity to UserDTO Conversion successfully completed.");
        return dto;
    }
}
