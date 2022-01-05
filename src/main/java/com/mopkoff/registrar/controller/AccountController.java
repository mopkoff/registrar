package com.mopkoff.registrar.controller;

import com.mopkoff.registrar.model.domain.Account;
import com.mopkoff.registrar.model.domain.User;
import com.mopkoff.registrar.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.mopkoff.registrar.common.Constants.USER_ID_HEADER;

@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @GetMapping
  public List<Account> findAll() {
    return accountService.findAll();
  }

  @PostMapping
  public Account add(
      @RequestHeader(USER_ID_HEADER) UUID userId,
      @RequestBody Account account) {
    account = account.withUser(User.builder().id(userId).build());
    return accountService.save(account);
  }
}
