package com.przemyslawostrouch.homebudgetassistant.register;

import com.przemyslawostrouch.homebudgetassistant.register.dto.Balance;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterManager {

    private final RegisterRepository registerRepository;
    private final RegisterFinder registerFinder;

    public Register rechargeRegister(Long registerId, TransferValue transferValue) {
        Register foundRegister = registerFinder.findRegisterOrException(registerId);
        Balance currentBalance = foundRegister.getBalance();
        currentBalance.recharge(transferValue.getValue());
        return registerRepository.save(foundRegister);
    }

    public Register getBalance(Long registerId) {
        return registerFinder.findRegisterOrException(registerId);
    }
}
