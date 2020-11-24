package com.przemyslawostrouch.homebudgetassistant.register;

import com.przemyslawostrouch.homebudgetassistant.exception.RegisterNotFoundException;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
class RegisterFinder {

    private final RegisterRepository registerRepository;

    Register findRegisterOrException(Long registerId) {
        return registerRepository.findById(registerId)
                .orElseThrow(
                        () -> new RegisterNotFoundException("Unexpected number of record exception")
                );
    }

    List<Register> findAllRegisters() {
        return registerRepository.findAll();
    }
}
