package com.mopkoff.registrar.service;

import com.mopkoff.registrar.repository.AccountRepository;
import com.mopkoff.registrar.repository.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public List<Account> findAll() {
        return repository.findAll();
    }

    public Account add(Account Account) {
        return repository.save(Account);
    }
}
