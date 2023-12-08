package com.viguer.authenticator.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;

    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Phone> phones;

}
