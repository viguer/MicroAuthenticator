package com.viguer.authenticator.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;
import java.util.regex.Pattern;

@Component
public class ValidatorRegex {

    @Autowired
    ParametersPropertyReader parametersPropertyReader;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
    public Boolean email(String email){
        return Pattern.matches(EMAIL_PATTERN, email);
    }

    public Boolean password(String password){
        Properties properties = parametersPropertyReader.readProperties();
        String regexPattern = properties.getProperty("password.regex");
        return Pattern.matches(regexPattern, password);
    }
}
