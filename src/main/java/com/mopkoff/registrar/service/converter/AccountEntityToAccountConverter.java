package com.mopkoff.registrar.service.converter;

import com.mopkoff.registrar.exception.NotFoundException;
import com.mopkoff.registrar.model.domain.Account;
import com.mopkoff.registrar.model.repository.AccountEntity;
import com.mopkoff.registrar.model.repository.UserEntity;
import com.mopkoff.registrar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountEntityToAccountConverter extends AutoRegisteredConverter<AccountEntity, Account> {

  @Override
  public Account convert(AccountEntity source) {

    return Account.builder()
        .id(source.getId())
        .type(source.getType())
        .username(source.getUsername())
        .password(source.getPassword())
        .build();
  }
}
