package com.mopkoff.registrar.controller;

import com.mopkoff.registrar.model.domain.User;
import com.mopkoff.registrar.service.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    return userService.save(user.withId(null));
  }

  @PostMapping("generate")
  public User generate() {
    return userService.generate();
  }

  @PutMapping
  public User update(@RequestBody @Valid User user) {
    return userService.save(user);
  }
}
