package com.viguer.authenticator.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class GeneralResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;

}
