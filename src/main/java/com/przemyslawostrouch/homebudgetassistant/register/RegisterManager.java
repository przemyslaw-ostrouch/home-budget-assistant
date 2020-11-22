package com.przemyslawostrouch.homebudgetassistant.register;

import com.przemyslawostrouch.homebudgetassistant.register.dto.Balance;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;
import com.przemyslawostrouch.homebudgetassistant.register.repository.RegisterRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
public class RegisterManager {

    private final RegisterRepository registerRepository;
    private final RegisterFinder registerFinder;
    private final RegisterTransactionManager registerTransactionManager;

    @Transactional
    public Register rechargeRegister(Long registerId, TransferValue transferValue) {
        if (isRechargeValueValid(transferValue)) {
            Register foundRegister = registerFinder.findRegisterOrException(registerId);
            foundRegister.getBalance().recharge(transferValue.getValue());
            Register registerAfterRecharge = registerRepository.save(foundRegister);
            registerTransactionManager.saveRechargeTransaction(registerAfterRecharge, transferValue);
            return registerAfterRecharge;
        } else {
            throw new IllegalArgumentException("Recharge value have to be more then 0");
        }
    }

    private boolean isRechargeValueValid(TransferValue transferValue) {
        return transferValue.getValue().compareTo(BigDecimal.ZERO) > 0;
    }

    public List<Register> getAll() {
        return registerFinder.findAllRegisters();
    }
}
