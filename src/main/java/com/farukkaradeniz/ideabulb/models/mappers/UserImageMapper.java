package com.farukkaradeniz.ideabulb.models.mappers;

import com.farukkaradeniz.ideabulb.models.dtos.UserImageDTO;
import com.farukkaradeniz.ideabulb.models.entities.UserImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserImageMapper {
    private static Logger log = LoggerFactory.getLogger(UserImageMapper.class);

    public static UserImageDTO convertToUserImageDTO(UserImage userImage) {
        UserImageDTO dto = new UserImageDTO(userImage.getId(),
                userImage.getFullImage(),
                userImage.getThumbnail());

        log.info("UserImage Entity to UserImageDTO Conversion successfully completed.");
        return dto;
    }
}
