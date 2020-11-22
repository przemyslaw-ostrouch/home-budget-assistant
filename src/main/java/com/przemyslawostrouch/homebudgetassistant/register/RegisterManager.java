package com.przemyslawostrouch.homebudgetassistant.register;

import com.przemyslawostrouch.homebudgetassistant.register.dto.RechargeRequest;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
public class RegisterManager {

    private final RegisterRepository registerRepository;
    private final RegisterFinder registerFinder;
    private final RegisterTransactionManager registerTransactionManager;

    @Transactional
    public Register rechargeRegister(RechargeRequest rechargeRequest) {
        rechargeRequest.validateTransferValue();
        Register foundRegister = registerFinder.findRegisterOrException(rechargeRequest.getToRegisterId());
        foundRegister.recharge(rechargeRequest.getTransferValue());
        Register registerAfterRecharge = registerRepository.save(foundRegister);
        registerTransactionManager.saveRechargeTransaction(registerAfterRecharge, rechargeRequest.getTransfer());
        return registerAfterRecharge;
    }

    public List<Register> getAll() {
        return registerFinder.findAllRegisters();
    }
}
