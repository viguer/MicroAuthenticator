package com.viguer.authenticator.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Getter
@Setter
@Table(name = "Users")
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;

    private String username;
    private String email;
    private String password;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private LocalDateTime lastLoginDate;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EEstado estado;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones;

    public User(String username, String email, String password, LocalDateTime createdDate, LocalDateTime modifiedDate, LocalDateTime lastLoginDate, EEstado estado) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.lastLoginDate = lastLoginDate;
        this.estado = estado;

    }

}
