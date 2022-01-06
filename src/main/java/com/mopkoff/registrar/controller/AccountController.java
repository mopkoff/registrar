package com.mopkoff.registrar.controller;

import static com.mopkoff.registrar.common.Constants.USER_ID_HEADER;

import com.mopkoff.registrar.model.domain.Account;
import com.mopkoff.registrar.model.domain.User;
import com.mopkoff.registrar.service.AccountService;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
      @RequestBody @Valid Account account) {
    account = account.withUser(User.builder().id(userId).build());
    return accountService.save(account);
  }
}
