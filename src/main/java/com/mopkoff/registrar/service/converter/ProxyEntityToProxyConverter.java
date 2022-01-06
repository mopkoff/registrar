package com.mopkoff.registrar.service.converter;

import com.mopkoff.registrar.model.domain.ProxyConfig;
import com.mopkoff.registrar.model.repository.ProxyEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProxyEntityToProxyConverter extends AutoRegisteredConverter<ProxyEntity, ProxyConfig>{

  @Override
  public ProxyConfig convert(ProxyEntity source) {
    return ProxyConfig.builder()
        .id(source.getId())
        .login(source.getLogin())
        .password(source.getPassword())
        .host(source.getHost())
        .countryCode(source.getCountryCode())
        .build();
  }
}
