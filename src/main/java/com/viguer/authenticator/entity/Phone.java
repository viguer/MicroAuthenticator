package com.viguer.authenticator.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Getter
@Setter
@Table(name = "Phones")
@NoArgsConstructor
public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID phoneId;

    private Integer number;
    private Integer cityCode;
    private Integer countryCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Phone(Integer number, Integer cityCode, Integer countryCode, User user) {
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
        this.user = user;
    }
}
