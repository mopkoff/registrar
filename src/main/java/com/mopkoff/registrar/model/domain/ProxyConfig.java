package com.mopkoff.registrar.model.domain;

import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class ProxyConfig {

  UUID id;
  @NotNull String login;
  @NotNull String password;
  @NotNull String host;
  String countryCode;
}
