package com.przemyslawostrouch.homebudgetassistant.register;

import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<Register, Long> {
}
