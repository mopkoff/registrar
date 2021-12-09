package com.mopkoff.registrar.repository.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    Integer id;
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            foreignKey = @ForeignKey(name = "account_user_fk", value = ConstraintMode.NO_CONSTRAINT)
    )
    User user;
    @Column
    String email;
    @Column
    String phone;
}
