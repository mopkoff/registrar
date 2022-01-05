package com.mopkoff.registrar.controller;

import com.mopkoff.registrar.model.domain.User;
import com.mopkoff.registrar.model.repository.UserEntity;
import com.mopkoff.registrar.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @PostMapping
    public User add(@RequestBody @Valid User user) {
        return userService.save(user);
    }
}
