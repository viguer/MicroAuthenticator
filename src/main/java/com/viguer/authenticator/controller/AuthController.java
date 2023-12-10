package com.viguer.authenticator.controller;

import com.viguer.authenticator.entity.EEstado;
import com.viguer.authenticator.entity.Phone;
import com.viguer.authenticator.entity.User;
import com.viguer.authenticator.payload.request.LoginRequest;
import com.viguer.authenticator.payload.request.PhoneDTO;
import com.viguer.authenticator.payload.request.SignupRequest;
import com.viguer.authenticator.payload.response.GeneralResponse;
import com.viguer.authenticator.payload.response.SigninResponse;
import com.viguer.authenticator.payload.response.SignupResponse;
import com.viguer.authenticator.repository.UserRepository;
import com.viguer.authenticator.security.jwt.JwtUtils;
import com.viguer.authenticator.util.ValidatorRegex;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    ValidatorRegex validatorRegex;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @PostMapping(value = "/signup")
    public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (!validatorRegex.email(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new GeneralResponse("Email no cumple el formato"));
        }
        if (!userRepository.findByEmail(signupRequest.getEmail()).isEmpty()) {
            return ResponseEntity.badRequest().body(new GeneralResponse("Error: Email ya existe"));
        }
        if (!validatorRegex.password(signupRequest.getPassword())) {
            return ResponseEntity.badRequest().body(new GeneralResponse("Error: Password no cumple formato"));
        }

        User user = new User(signupRequest.getName(), signupRequest.getEmail(), passwordEncoder.encode(signupRequest.getPassword()), LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), EEstado.ACTIVO);
        List<Phone> phones = new ArrayList<Phone>();
        for (PhoneDTO phone : signupRequest.getPhones()) {
            phones.add(new Phone(phone.getNumber(), phone.getCityCode(), phone.getCountryCode(), user));
        }
        user.setPhones(phones);
        user = userRepository.save(user);

        return new ResponseEntity<>(new SignupResponse(user.getUserId(), user.getCreatedDate(), user.getModifiedDate(), user.getLastLoginDate(), authenticateJWT(signupRequest.getEmail(), signupRequest.getPassword()), user.getEstado().name() == EEstado.ACTIVO.name()), HttpStatus.CREATED);
    }

    @Transactional
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authenticateJWT(loginRequest.getUsername(), loginRequest.getPassword());
        User user = userRepository.findByEmail(loginRequest.getUsername()).get();
        user.setLastLoginDate(LocalDateTime.now());
        userRepository.save(user);
        return ResponseEntity.ok(new SigninResponse(user.getUserId(), token, user.getEstado().name() == EEstado.ACTIVO.name()));
    }

    private String authenticateJWT(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtils.generateJwtToken(authentication);
    }

}
