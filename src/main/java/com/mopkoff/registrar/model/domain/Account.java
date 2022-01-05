package com.mopkoff.registrar.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mopkoff.registrar.model.enums.AccountType;
import com.mopkoff.registrar.model.repository.UserEntity;
import java.util.UUID;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class Account {

  UUID id;
  @JsonBackReference
  User user;
  AccountType type;
  String username;
  String password;
}
