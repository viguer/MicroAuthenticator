package com.viguer.authenticator.controller;

import com.viguer.authenticator.entity.EEstado;
import com.viguer.authenticator.entity.Phone;
import com.viguer.authenticator.entity.User;
import com.viguer.authenticator.payload.request.LoginRequest;
import com.viguer.authenticator.payload.request.PhoneDTO;
import com.viguer.authenticator.payload.request.SignupRequest;
import com.viguer.authenticator.repository.UserRepository;
import com.viguer.authenticator.config.security.jwt.JwtUtils;
import com.viguer.authenticator.util.ValidatorRegex;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @InjectMocks
    AuthController authController;

    @Mock
    UserRepository userRepository;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    JwtUtils jwtUtils;

    @Mock
    ValidatorRegex validatorRegex;

    @Mock
    PasswordEncoder passwordEncoder;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void testCreateUser() throws Exception {
        // Arrange
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();

        SignupRequest signupRequest = new SignupRequest("John Doe", "john.doe@example.com", "StrongPassword123", null );
        PhoneDTO phoneDTO = new PhoneDTO(1234567890, 00, 1 );
        signupRequest.setPhones(List.of(phoneDTO));

        when(validatorRegex.email(any())).thenReturn(true);
        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());
        when(validatorRegex.password(any())).thenReturn(true);

        User user = new User();
        user.setUsername("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("StrongPassword123");
        user.setCreatedDate(LocalDateTime.now());
        user.setModifiedDate(LocalDateTime.now());
        user.setLastLoginDate(LocalDateTime.now());
        user.setEstado(EEstado.ACTIVO);

        Phone phone = new Phone();
        phone.setNumber(1234567890);
        phone.setCityCode(00);
        phone.setCountryCode(1);

        List<Phone> phones = new ArrayList<>();
        phones.add(phone);

        user.setPhones(phones);

        when(userRepository.save(any())).thenReturn(user);

        // Act
        String json = "{\"name\":\"John Doe\",\"email\":\"john.doe@example.com\",\"password\":\"StrongPassword123\",\"phones\":[{\"number\":\"1234567890\",\"cityCode\":\"00\",\"countryCode\":\"1\"}]}";
        mockMvc.perform(post("/api/v1/auth/signup")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAuthenticateUser() throws Exception {
        // Arrange
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("john.doe@example.com");
        loginRequest.setPassword("StrongPassword123");

        User user = new User();
        user.setUsername("John Doe");
        user.setEmail("john.doe@example.com");
        user.setPassword("StrongPassword123");
        user.setCreatedDate(LocalDateTime.now());
        user.setModifiedDate(LocalDateTime.now());
        user.setLastLoginDate(LocalDateTime.now());
        user.setEstado(EEstado.ACTIVO);

       when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.ofNullable(user));

        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        String token = "Token1234567890";
        when(jwtUtils.generateJwtToken(any())).thenReturn(token);

        // Act
        String json = "{\"username\":\"john.doe@example.com\",\"password\":\"StrongPassword123\"}";
        mockMvc.perform(post("/api/v1/auth/signin")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk());
    }

}