package com.mopkoff.registrar.service;

import com.mopkoff.registrar.model.domain.User;
import com.mopkoff.registrar.model.repository.UserEntity;
import com.mopkoff.registrar.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository repository;
  private final UserGeneratorService userGeneratorService;
  private final GenericConversionService registrarConversionService;

  public List<User> findAll() {
    return repository.findAll().stream()
        .map(a -> registrarConversionService.convert(a, User.class))
        .collect(Collectors.toList());
  }

  public User save(User user) {
    var entity = registrarConversionService.convert(user, UserEntity.class);
    var saved = repository.save(entity);
    return registrarConversionService.convert(saved, User.class);
  }

  public User generate() {
    var user = userGeneratorService.generateUserGoogle();
    return save(user);
  }
}
