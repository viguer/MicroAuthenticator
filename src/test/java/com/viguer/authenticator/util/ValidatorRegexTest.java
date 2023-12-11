package com.viguer.authenticator.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ValidatorRegexTest {
    @Autowired
    private Environment environment;

    @Autowired
    ValidatorRegex validatorRegex;

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void emailTest() {
        assertTrue(validatorRegex.email("email@example.com"));
        assertFalse(validatorRegex.email("email.example.com"));
    }

    @Test
    public void passwordTest() {
        assertTrue(validatorRegex.password("Passw0rd@"));
        assertFalse(validatorRegex.password("password"));
        assertFalse(validatorRegex.password("Passw0rd"));
    }

}