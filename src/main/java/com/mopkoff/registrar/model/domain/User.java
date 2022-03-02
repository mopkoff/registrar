package com.mopkoff.registrar.model.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.Email;

import com.mopkoff.registrar.service.integration.model.RentStateResponse;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class User {

    UUID id;
    String firstName;
    String lastName;
    String userName;
    @Email
    String email;
    String password;
    RentStateResponse phone;
    LocalDate birthDate;
    @JsonManagedReference
    List<Account> accounts;
}
