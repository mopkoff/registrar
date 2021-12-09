package com.mopkoff.registrar.repository.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    Integer id;
    @Column
    String name;
    @Column
    String email;
    @Column
    String phone;
}
