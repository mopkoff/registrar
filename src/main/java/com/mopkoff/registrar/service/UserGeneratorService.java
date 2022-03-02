package com.mopkoff.registrar.service;

import com.github.javafaker.Faker;
import com.mopkoff.registrar.model.domain.User;
import com.mopkoff.registrar.service.integration.OnlineSimService;
import com.mopkoff.registrar.service.utils.CustomRandom;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

@Component
@RequiredArgsConstructor
public class UserGeneratorService {
    private final Faker faker;
    private final CustomRandom random;
    private final OnlineSimService onlineSimService;

    public User generateUserGoogle() {
        var username = generateUsername();

        return User.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .userName(username)
                .email(username + "@gmail.com")
                .password("!" + username + "!")
                .birthDate(generateDate())
                .phone(onlineSimService.rentNumberGoogle())
                .build();
    }

    private String generateUsername() {
        return faker.name().username() + random.nextNonNegative(100000, 999999);
    }

    @SneakyThrows
    private LocalDate generateDate() {
        var from = DateFormat.getInstance().parse("01/01/80");
        var to = DateFormat.getInstance().parse("01/01/90");
        var generated = faker.date().between(from, to);
        return generated.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
