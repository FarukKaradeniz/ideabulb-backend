package com.farukkaradeniz.ideabulb.repositories;

import com.farukkaradeniz.ideabulb.models.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);
}
