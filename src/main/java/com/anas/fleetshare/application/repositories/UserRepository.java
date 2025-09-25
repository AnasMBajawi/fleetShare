package com.anas.fleetshare.application.repositories;

import com.anas.fleetshare.domain.user.User;

import java.util.Optional;
import java.util.*;

public interface UserRepository {
    Optional<User> findById(UUID id);
    List<User> findAll();
    void save(User user);
}
