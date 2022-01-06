package com.mopkoff.registrar.model.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import javax.validation.constraints.Email;
import lombok.Builder;
import lombok.Value;
import lombok.With;

@With
@Value
@Builder
public class User {

    UUID id;
    String name;
    @Email
    String email;
    //@PhoneNumberValid
    String phoneNumber;
    Instant birthDate;
    @JsonManagedReference
    List<Account> accounts;
}
