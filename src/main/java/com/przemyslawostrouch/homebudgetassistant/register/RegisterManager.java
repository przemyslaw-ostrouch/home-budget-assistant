package com.przemyslawostrouch.homebudgetassistant.register;

import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;
import com.przemyslawostrouch.homebudgetassistant.register.repository.RegisterRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
public class RegisterManager {

    private final RegisterRepository registerRepository;
    private final RegisterFinder registerFinder;
    private final RegisterTransactionManager registerTransactionManager;

    @Transactional
    public Register rechargeRegister(Long registerId, TransferValue transferValue) {
        transferValue.validate();
        Register foundRegister = registerFinder.findRegisterOrException(registerId);
        foundRegister.recharge(transferValue.getValue());
        Register registerAfterRecharge = registerRepository.save(foundRegister);
        registerTransactionManager.saveRechargeTransaction(registerAfterRecharge, transferValue);
        return registerAfterRecharge;
    }

    public List<Register> getAll() {
        return registerFinder.findAllRegisters();
    }
}
