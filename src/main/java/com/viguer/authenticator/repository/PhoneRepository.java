package com.viguer.authenticator.repository;

import com.viguer.authenticator.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
