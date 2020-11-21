package com.przemyslawostrouch.homebudgetassistant.register;

import com.przemyslawostrouch.homebudgetassistant.exception.IncorrectRegisterBalanceException;
import com.przemyslawostrouch.homebudgetassistant.exception.IncorrectTransferValueException;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferRequest;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Transaction;
import com.przemyslawostrouch.homebudgetassistant.register.repository.RegisterRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@AllArgsConstructor
public class TransferManager {

    private final RegisterRepository registerRepository;
    private final RegisterFinder registerFinder;
    private final RegisterTransactionManager registerTransactionManager;

    @Transactional
    public Transaction transfer(TransferRequest transferRequest) {
        validateTransferValue(transferRequest.getTransfer());
        Register fromRegister = registerFinder.findRegisterOrException(transferRequest.getFromRegisterId());
        Register toRegister = registerFinder.findRegisterOrException(transferRequest.getToRegisterId());
        return doTransactionOrException(transferRequest.getTransfer(), fromRegister, toRegister);
    }

    private void validateTransferValue(TransferValue transfer) {
        if (transfer.getValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new IncorrectTransferValueException("Transfer value has to be positive");
        }
    }

    private Transaction doTransactionOrException(TransferValue transfer, Register fromRegister, Register toRegister) {
        if (isBalanceEnough(transfer, fromRegister)) {
            makeTransfer(fromRegister, toRegister, transfer);
            return registerTransactionManager.saveTransactionBetweenAccounts(transfer, fromRegister, toRegister);
        } else {
            throw new IncorrectRegisterBalanceException("Not enough amount of money");
        }
    }

    private boolean isBalanceEnough(TransferValue transfer, Register from) {
        return from.getBalanceValue().compareTo(transfer.getValue()) >= 0;
    }

    private void makeTransfer(Register fromRegister, Register toRegister, TransferValue transfer) {
        fromRegister.getBalance().setValue(fromRegister.getBalanceValue().subtract(transfer.getValue()));
        toRegister.getBalance().setValue(toRegister.getBalanceValue().add(transfer.getValue()));
        registerRepository.save(fromRegister);
        registerRepository.save(toRegister);
    }
}
