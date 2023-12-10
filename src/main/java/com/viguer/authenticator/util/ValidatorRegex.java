package com.viguer.authenticator.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidatorRegex {

    @Value("${password.regex}")
    private String regexPassword;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    public Boolean email(String email){
        return Pattern.matches(EMAIL_PATTERN, email);
    }

    public Boolean password(String password){
        return Pattern.matches(regexPassword, password);
    }
}
