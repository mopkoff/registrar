package com.mopkoff.registrar.service;

import com.mopkoff.registrar.repository.UserRepository;
import com.mopkoff.registrar.repository.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User add(User user) {
        return repository.save(user);
    }
}
