package com.mopkoff.registrar.service.converter;

import com.mopkoff.registrar.exception.NotFoundException;
import com.mopkoff.registrar.model.domain.Account;
import com.mopkoff.registrar.model.repository.AccountEntity;
import com.mopkoff.registrar.model.repository.UserEntity;
import com.mopkoff.registrar.repository.AccountRepository;
import com.mopkoff.registrar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException.NotFound;

@Component
@RequiredArgsConstructor
public class AccountToAccountEntityConverter extends AutoRegisteredConverter<Account, AccountEntity> {

  private final UserRepository userRepository;

  @Override
  public AccountEntity convert(Account source) {
    var user = userRepository.findById(source.getUser().getId())
        .orElseThrow(() -> new NotFoundException(UserEntity.class));

    return AccountEntity.builder()
        .id(source.getId())
        .type(source.getType())
        .user(user)
        .username(source.getUsername())
        .password(source.getPassword())
        .build();
  }
}
