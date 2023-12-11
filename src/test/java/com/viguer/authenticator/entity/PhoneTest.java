package com.viguer.authenticator.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PhoneTest {

    @Test
    public void testConstructor() {
        Integer number = 1234567890;
        Integer cityCode = 123;
        Integer countryCode = 91;
        User user = new User();

        Phone phone = new Phone(number, cityCode, countryCode, user);

        assertEquals(number, phone.getNumber());
        assertEquals(cityCode, phone.getCityCode());
        assertEquals(countryCode, phone.getCountryCode());
        assertEquals(user, phone.getUser());
    }
}