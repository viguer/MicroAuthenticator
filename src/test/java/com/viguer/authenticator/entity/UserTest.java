package com.viguer.authenticator.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void testConstructor() {
        String username = "username";
        String email = "email@example.com";
        String password = "password";
        LocalDateTime createdDate = LocalDateTime.now();
        LocalDateTime modifiedDate = LocalDateTime.now();
        LocalDateTime lastLoginDate = LocalDateTime.now();
        EEstado estado = EEstado.ACTIVO;

        User user = new User(username, email, password, createdDate, modifiedDate, lastLoginDate, estado);

        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(createdDate, user.getCreatedDate());
        assertEquals(modifiedDate, user.getModifiedDate());
        assertEquals(lastLoginDate, user.getLastLoginDate());
        assertEquals(estado, user.getEstado());
    }

    @Test
    public void testEstadoInvalid() {
        User user = new User();
        user.setEstado(EEstado.ACTIVO);
    }

    @Test
    public void testUUID() {
        UUID uuid = UUID.randomUUID();
        User user = new User();
        user.setUserId(uuid);
        assertEquals(uuid, user.getUserId());
    }
}