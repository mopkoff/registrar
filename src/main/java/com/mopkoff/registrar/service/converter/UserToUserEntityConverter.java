package com.mopkoff.registrar.service.converter;

import com.mopkoff.registrar.model.domain.User;
import com.mopkoff.registrar.model.repository.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserToUserEntityConverter extends AutoRegisteredConverter<User, UserEntity>{

  @Override
  public UserEntity convert(User source) {
    return UserEntity.builder()
        .id(source.getId())
        .birthDate(source.getBirthDate())
        .email(source.getEmail())
        .name(source.getName())
        .phone(source.getPhoneNumber())
        .build();
  }
}
