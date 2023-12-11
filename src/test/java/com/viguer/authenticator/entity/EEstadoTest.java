package com.viguer.authenticator.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EEstadoTest {

    @Test
    public void testValido() {
        assertEquals(EEstado.ACTIVO, EEstado.valueOf("ACTIVO"));
        assertEquals(EEstado.INACTIVO, EEstado.valueOf("INACTIVO"));
    }

    @Test
    public void testInvalido() {
        assertThrows(IllegalArgumentException.class, () -> EEstado.valueOf("NO_EXISTE"));
    }
}