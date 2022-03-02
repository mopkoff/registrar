package com.mopkoff.registrar.service.converter;

import com.mopkoff.registrar.model.domain.Account;
import com.mopkoff.registrar.model.domain.User;
import com.mopkoff.registrar.model.repository.AccountEntity;
import com.mopkoff.registrar.model.repository.UserEntity;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEntityToUserConverter extends AutoRegisteredConverter<UserEntity, User> {

    @Override
    public User convert(UserEntity source) {
        List<Account> accounts = convertCollection(source.getAccounts(), AccountEntity.class, Account.class, List.class);
        return User.builder()
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
