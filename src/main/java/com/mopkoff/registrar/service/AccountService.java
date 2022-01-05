package com.mopkoff.registrar.service;

import com.mopkoff.registrar.model.domain.Account;
import com.mopkoff.registrar.repository.AccountRepository;
import com.mopkoff.registrar.model.repository.AccountEntity;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository repository;
  private final GenericConversionService registrarConversionService;

  public List<Account> findAll() {
    return repository.findAll().stream()
        .map(a -> registrarConversionService.convert(a, Account.class))
        .collect(Collectors.toList());
  }

  public Account save(Account account) {
    var entity = registrarConversionService.convert(account, AccountEntity.class);
    var saved = repository.save(entity);
    return registrarConversionService.convert(saved, Account.class);
  }
}
