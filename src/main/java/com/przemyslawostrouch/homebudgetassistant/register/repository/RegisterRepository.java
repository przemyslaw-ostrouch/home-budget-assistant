package com.przemyslawostrouch.homebudgetassistant.register.repository;

import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<Register, Long> {
}
