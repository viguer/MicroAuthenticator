package com.viguer.authenticator.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneDTO {

    private Integer number;
    private Integer cityCode;
    private Integer countryCode;
}
