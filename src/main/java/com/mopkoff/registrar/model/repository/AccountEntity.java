package com.mopkoff.registrar.model.repository;

import com.mopkoff.registrar.model.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    UUID id;
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(name = "account_user_fk", value = ConstraintMode.NO_CONSTRAINT)
    )
    UserEntity user;
    @Column
    @Enumerated(EnumType.STRING)
    AccountType type;
    @Column
    String username;
    @Column
    String password;
}
