package com.mopkoff.registrar.model.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mopkoff.registrar.annotation.PhoneNumberValid;
import com.mopkoff.registrar.model.repository.AccountEntity;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import org.hibernate.annotations.GenericGenerator;

@Value
@Builder
public class User {

    UUID id;
    String name;
    @Email
    String email;
    @PhoneNumberValid
    String phoneNumber;
    Instant birthDate;
    @JsonManagedReference
    List<Account> accounts;
}
