package com.przemyslawostrouch.homebudgetassistant.register;

import com.przemyslawostrouch.homebudgetassistant.exception.IncorrectRegisterBalanceException;
import com.przemyslawostrouch.homebudgetassistant.exception.IncorrectTransferValueException;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferRequest;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Transaction;
import com.przemyslawostrouch.homebudgetassistant.register.repository.RegisterRepository;
import com.przemyslawostrouch.homebudgetassistant.register.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@AllArgsConstructor
public class TransferManager {

    private final RegisterRepository registerRepository;
    private final TransactionRepository transactionRepository;
    private final RegisterFinder registerFinder;

    @Transactional
    public Transaction transfer(TransferRequest transferRequest) {
        BigDecimal valueToTransfer = transferRequest.getTransferValue();
        validateTransferValue(valueToTransfer);
        Register fromRegister = registerFinder.findRegisterOrException(transferRequest.getFromRegisterId());
        Register toRegister = registerFinder.findRegisterOrException(transferRequest.getToRegisterId());
        return doTransactionOrException(transferRequest, fromRegister, toRegister, valueToTransfer);
    }

    private void validateTransferValue(BigDecimal valueToTransfer) {
        if (valueToTransfer.compareTo(BigDecimal.ZERO) < 0) {
            throw new IncorrectTransferValueException("Transfer value have to be positive");
        }
    }

    private Transaction doTransactionOrException(TransferRequest transferRequest, Register fromRegister, Register toRegister, BigDecimal valueToTransfer) {
        if (isBalanceEnough(transferRequest, fromRegister)) {
            makeTransfer(fromRegister, toRegister, valueToTransfer);
            return saveTransaction(transferRequest, fromRegister, toRegister);
        } else {
            throw new IncorrectRegisterBalanceException("Not enough amount of money");
        }
    }

    private boolean isBalanceEnough(TransferRequest transferRequest, Register from) {
        return from.getBalanceValue().compareTo(transferRequest.getTransferValue()) >= 0;
    }

    private void makeTransfer(Register fromRegister, Register toRegister, BigDecimal valueToTransfer) {
        fromRegister.getBalance().setValue(fromRegister.getBalanceValue().subtract(valueToTransfer));
        toRegister.getBalance().setValue(toRegister.getBalanceValue().add(valueToTransfer));
        registerRepository.save(fromRegister);
        registerRepository.save(toRegister);
    }

    private Transaction saveTransaction(TransferRequest transferRequest, Register fromRegister, Register toRegister) {
        Transaction transaction = Transaction.builder()
                .fromRegister(fromRegister)
                .toRegister(toRegister)
                .transferValue(transferRequest.getTransfer())
                .transactionDateTime(LocalDateTime.now(ZoneOffset.UTC))
                .build();
        return transactionRepository.save(transaction);
    }
}
