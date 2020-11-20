package com.przemyslawostrouch.homebudgetassistant.register;

import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class RegisterFinder {

    private final RegisterRepository registerRepository;

    Register findRegisterOrException(Long registerId) {
        Optional<Register> optionalRegister = registerRepository.findById(registerId);
        return optionalRegister.orElseThrow(
                () -> new RuntimeException("Unexpected number of record exception")
        );
    }
}
