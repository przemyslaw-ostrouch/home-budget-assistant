package com.przemyslawostrouch.homebudgetassistant.register;

import com.przemyslawostrouch.homebudgetassistant.exception.IncorrectRegisterBalanceException;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferRequest;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
class TransferManager {

    private final RegisterRepository registerRepository;
    private final RegisterFinder registerFinder;
    private final RegisterTransactionManager registerTransactionManager;

    @Transactional
    Transaction transfer(TransferRequest transferRequest) {
        transferRequest.validateTransfer();
        Register fromRegister = registerFinder.findRegisterOrException(transferRequest.getFromRegisterId());
        Register toRegister = registerFinder.findRegisterOrException(transferRequest.getToRegisterId());
        return makeTransactionOrException(transferRequest.getTransfer(), fromRegister, toRegister);
    }

    private Transaction makeTransactionOrException(TransferValue transfer, Register fromRegister, Register toRegister) {
        fromRegister.validateBalance(transfer);
        makeTransfer(fromRegister, toRegister, transfer);
        return registerTransactionManager.saveTransactionBetweenAccounts(transfer, fromRegister, toRegister);
    }

    private void makeTransfer(Register fromRegister, Register toRegister, TransferValue transfer) {
        registerRepository.save(fromRegister.reduceBalanceValue(transfer));
        registerRepository.save(toRegister.recharge(transfer));
    }
}
