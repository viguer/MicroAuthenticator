package com.viguer.authenticator.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class PhoneDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank
    private Integer number;
    @NotBlank
    private Integer cityCode;
    @NotBlank
    private Integer countryCode;

}