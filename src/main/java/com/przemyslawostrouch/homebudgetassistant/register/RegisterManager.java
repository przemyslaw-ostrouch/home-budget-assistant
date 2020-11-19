package com.przemyslawostrouch.homebudgetassistant.register;

import com.przemyslawostrouch.homebudgetassistant.register.dto.Balance;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferRequest;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Transaction;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RegisterManager {

    public Balance rechargeRegister(TransferValue transferValue, Long registerId) {
        //find and take register
        //register balance + transferValue
        //save new balance
        //return new account balance
        return null;
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
        //find register
        // if exist get balance
        return null;
    }
}
