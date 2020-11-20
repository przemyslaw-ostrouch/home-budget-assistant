package com.przemyslawostrouch.homebudgetassistant.register;

import com.przemyslawostrouch.homebudgetassistant.register.dto.Balance;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferRequest;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Transaction;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class RegisterManager {

    private final RegisterRepository registerRepository;

    public Register rechargeRegister(Long registerId, TransferValue transferValue) {
        Register foundRegister = findRegisterOrException(registerId);
        Balance currentBalance = foundRegister.getBalance();
        if (currentBalance.getCurrency() == transferValue.getMoneyValue().getCurrency()) {
            currentBalance.recharge(transferValue.getMoneyValue().getValue());
            return registerRepository.save(foundRegister);
        } else {
            throw new RuntimeException("Mismatch with currencies");
        }
    }


    public Transaction transfer(TransferRequest transferRequest) {
        //findFirst from register
        //findFirst to register
        //validate whether transfer is possible (enough money)
        //calculate transfer + balances and save both
        //create and return transaction obj
        return null;
    }

    public Balance getBalance(Long registerId) {
        return findRegisterOrException(registerId).getBalance();
    }


    private Register findRegisterOrException(Long registerId) {
        Optional<Register> optionalRegister = registerRepository.findById(registerId);
        return optionalRegister.orElseThrow(
                () -> new RuntimeException("Unexpected number of record exception")
        );
    }
}
