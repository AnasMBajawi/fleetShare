package com.anas.fleetshare.infrastructure.repositories;

import com.anas.fleetshare.application.repositories.UserRepository;
import com.anas.fleetshare.domain.user.User;

import java.net.UnknownServiceException;
import java.util.List;
import java.util.Optional;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository implements UserRepository {
    private final Map<UUID, User> store = new ConcurrentHashMap<>();

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void save(User user) {
        store.put(user.getId(), user);
    }
}
