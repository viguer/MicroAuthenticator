package com.viguer.authenticator.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class SigninResponse {
    private UUID id;
    private String token;
    private Boolean isActive;
}
