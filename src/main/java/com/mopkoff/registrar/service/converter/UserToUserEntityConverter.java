package com.mopkoff.registrar.service.converter;

import com.mopkoff.registrar.model.domain.Account;
import com.mopkoff.registrar.model.domain.User;
import com.mopkoff.registrar.model.repository.AccountEntity;
import com.mopkoff.registrar.model.repository.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserToUserEntityConverter extends AutoRegisteredConverter<User, UserEntity> {

    @Override
    public UserEntity convert(User source) {
        List<AccountEntity> accounts = convertCollection(source.getAccounts(), Account.class, AccountEntity.class, List.class);
        return UserEntity.builder()
                .id(source.getId())
                .birthDate(source.getBirthDate())
                .email(source.getEmail())
                .password(source.getPassword())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .userName(source.getUserName())
                .phone(source.getPhone())
                .accounts(accounts)
                .build();
    }
}
