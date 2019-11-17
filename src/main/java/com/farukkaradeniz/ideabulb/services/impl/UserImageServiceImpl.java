package com.farukkaradeniz.ideabulb.services.impl;

import com.farukkaradeniz.ideabulb.models.entities.UserImage;
import com.farukkaradeniz.ideabulb.repositories.UserImageRepository;
import com.farukkaradeniz.ideabulb.services.UserImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImageServiceImpl implements UserImageService {
    @Autowired
    private UserImageRepository repository;

    @Override
    public UserImage save(UserImage userImage) {
        return repository.save(userImage);
    }
}
