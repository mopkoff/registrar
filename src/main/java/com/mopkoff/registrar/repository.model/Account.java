package com.mopkoff.registrar.repository.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "accounts")
public class Account {

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
    @JsonBackReference
    User user;
    @Column
    @Enumerated(EnumType.STRING)
    AccountType type;
    @Column
    String username;
    @Column
    String password;

    public enum AccountType {
        DISCORD, TWITTER
    }
}
