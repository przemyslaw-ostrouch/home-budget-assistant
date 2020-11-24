package com.przemyslawostrouch.homebudgetassistant.register;

import com.przemyslawostrouch.homebudgetassistant.register.dto.RechargeRequest;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
class RegisterManager {

    private final RegisterRepository registerRepository;
    private final RegisterFinder registerFinder;
    private final RegisterTransactionManager registerTransactionManager;

    @Transactional
    Transaction rechargeRegister(RechargeRequest rechargeRequest) {
        rechargeRequest.validateTransferValue();
        Register foundRegister = registerFinder.findRegisterOrException(rechargeRequest.getToRegisterId());
        Register registerAfterRecharge = registerRepository.save(foundRegister.recharge(rechargeRequest.getTransfer()));
        return registerTransactionManager.saveRechargeTransaction(registerAfterRecharge, rechargeRequest.getTransfer());
    }

    List<Register> getAll() {
        return registerFinder.findAllRegisters();
    }
}
