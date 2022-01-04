package com.mopkoff.registrar.repository.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    UUID id;
    @Column
    String name;
    @Column
    String email;
    @Column
    String phone;
    @Column
    Instant birthDate;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    List<Account> accounts;
}
