package com.mopkoff.registrar.model.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mopkoff.registrar.model.enums.AccountType;
import java.util.UUID;
import javax.validation.constraints.NotNull;
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
  @NotNull String username;
  @NotNull String password;
  @NotNull AccountType type;
}
