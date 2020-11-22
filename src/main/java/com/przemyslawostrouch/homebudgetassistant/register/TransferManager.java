package com.przemyslawostrouch.homebudgetassistant.register;

import com.przemyslawostrouch.homebudgetassistant.exception.IncorrectRegisterBalanceException;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferRequest;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
public class TransferManager {

    private final RegisterRepository registerRepository;
    private final RegisterFinder registerFinder;
    private final RegisterTransactionManager registerTransactionManager;

    @Transactional
    public Transaction transfer(TransferRequest transferRequest) {
        transferRequest.validateTransfer();
        Register fromRegister = registerFinder.findRegisterOrException(transferRequest.getFromRegisterId());
        Register toRegister = registerFinder.findRegisterOrException(transferRequest.getToRegisterId());
        return makeTransactionOrException(transferRequest.getTransfer(), fromRegister, toRegister);
    }

    private Transaction makeTransactionOrException(TransferValue transfer, Register fromRegister, Register toRegister) {
        if (fromRegister.isBalanceEnough(transfer)) {
            makeTransfer(fromRegister, toRegister, transfer);
            return registerTransactionManager.saveTransactionBetweenAccounts(transfer, fromRegister, toRegister);
        } else {
            throw new IncorrectRegisterBalanceException("Not enough amount of money, debit not possible");
        }
    }

    private void makeTransfer(Register fromRegister, Register toRegister, TransferValue transfer) {
        fromRegister.reduceBalanceValue(transfer);
        toRegister.increaseBalanceValue(transfer);
        registerRepository.save(fromRegister);
        registerRepository.save(toRegister);
    }
}
