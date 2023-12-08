package com.viguer.authenticator.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "Phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID phoneId;

    private Integer number;
    private Integer cityCode;
    private Integer countryCode;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

}
