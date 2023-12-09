package com.viguer.authenticator.controller;

import com.viguer.authenticator.dto.ResponseDTO;
import com.viguer.authenticator.dto.UserDTO;
import com.viguer.authenticator.service.AuthenticatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/authenticator")
public class MainController {

    @Autowired
    AuthenticatorService authenticatorService;

    @PostMapping(value = "/register")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        ResponseDTO responseDTO = authenticatorService.registerUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

}
