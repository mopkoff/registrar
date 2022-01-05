package com.mopkoff.registrar.model.repository;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@Data
@Entity
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    UUID id;
    @Column
    String name;
    @Email
    @Column
    String email;
    @Column
    String phone;
    @Column
    Instant birthDate;
    @OneToMany(mappedBy = "user")
    List<AccountEntity> accounts;
}
