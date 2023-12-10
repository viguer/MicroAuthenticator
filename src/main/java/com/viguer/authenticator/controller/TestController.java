package com.viguer.authenticator.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/test")
public class TestController {


    @GetMapping("/all")
    public ResponseEntity<?> allAccess() {

        return ResponseEntity.ok("buen muchaho");
    }

    @GetMapping("/activo")
    @PreAuthorize("hasAuthority('ACTIVO')")
    public String userActive() {
        return "User Active";
    }


    @GetMapping("/auth")
    @PreAuthorize("isAuthenticated()")
    public String userNoActive() {
        return "User inactivo";
    }
}
