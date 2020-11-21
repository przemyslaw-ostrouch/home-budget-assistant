package com.przemyslawostrouch.homebudgetassistant.register;

import com.przemyslawostrouch.homebudgetassistant.register.dto.Balance;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;
import com.przemyslawostrouch.homebudgetassistant.register.repository.RegisterRepository;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
public class RegisterManager {

    private final RegisterRepository registerRepository;
    private final RegisterFinder registerFinder;

    public Register rechargeRegister(Long registerId, TransferValue transferValue) {
        if (isRechargeValueValid(transferValue)) {
            Register foundRegister = registerFinder.findRegisterOrException(registerId);
            Balance currentBalance = foundRegister.getBalance();
            currentBalance.recharge(transferValue.getValue());
            return registerRepository.save(foundRegister);
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
