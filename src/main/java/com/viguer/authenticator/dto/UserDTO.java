package com.viguer.authenticator.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {

    private String name;
    private String email;
    private String password;
    private List<PhoneDTO> phones;
}
