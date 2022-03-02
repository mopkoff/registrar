package com.mopkoff.registrar.model.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mopkoff.registrar.service.integration.model.RentStateResponse;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

@Data
@Entity
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@TypeDefs(
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
)
public class UserEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    UUID id;
    String firstName;
    String lastName;
    String userName;
    String email;
    String password;
    @Type(type = "jsonb")
    RentStateResponse phone;
    LocalDate birthDate;
    @OneToMany(mappedBy = "user")
    List<AccountEntity> accounts;
}
