package com.viguer.authenticator.service;

import com.viguer.authenticator.dto.PhoneDTO;
import com.viguer.authenticator.dto.ResponseDTO;
import com.viguer.authenticator.dto.UserDTO;
import com.viguer.authenticator.entity.Phone;
import com.viguer.authenticator.entity.User;
import com.viguer.authenticator.repository.UserRepository;
import com.viguer.authenticator.util.ValidatorRegex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticatorService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ValidatorRegex validatorRegex;


    public ResponseDTO registerUser(UserDTO userDTO) {

        ResponseDTO responseDTO = new ResponseDTO();
        User userEntity = new User();
        List<Phone> phones = new ArrayList<>();
        Phone phoneEntity;

        try {
            if (!validatorRegex.email(userDTO.getEmail())) {
                responseDTO.setMassage("EL correo no es correcto");
                return responseDTO;
            }
            if (!userRepository.findByEmail(userDTO.getEmail()).isEmpty()) {
                responseDTO.setMassage("EL correo ya existe");
                return responseDTO;
            }

            userEntity.setName(userDTO.getName());
            userEntity.setPassword(userDTO.getPassword());
            userEntity.setEmail(userDTO.getEmail());
            userEntity.setCreatedDate(LocalDateTime.now());
            userEntity.setModifiedDate(LocalDateTime.now());
            userEntity.setLastLoginDate(LocalDateTime.now());
            userEntity.setToken("crear Token");
            userEntity.setIsActive(true);

            for (PhoneDTO phone : userDTO.getPhones()) {
                phoneEntity = new Phone();
                phoneEntity.setCityCode(phone.getCityCode());
                phoneEntity.setCountryCode(phone.getCountryCode());
                phoneEntity.setNumber(phone.getNumber());
                phoneEntity.setUser(userEntity);
                phones.add(phoneEntity);
            }
            userEntity.setPhones(phones);

            userRepository.save(userEntity);

        } catch (Exception e) {
            System.out.println("Hubo un error: " + e.getMessage());
        }
        responseDTO.setMassage("exitoso");
        return responseDTO;
    }
}
