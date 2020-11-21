package com.przemyslawostrouch.homebudgetassistant.register;

import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferRequest;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Transaction;
import com.przemyslawostrouch.homebudgetassistant.register.repository.TransactionRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@AllArgsConstructor
public class RegisterTransactionManager {
    private final TransactionRepository transactionRepository;

    public Transaction saveTransactionBetweenAccounts(TransferValue transfer, Register fromRegister, Register toRegister) {
        Transaction transaction = Transaction.builder()
                .fromRegister(fromRegister)
                .toRegister(toRegister)
                .transferValue(transfer)
                .transactionDateTime(LocalDateTime.now(ZoneOffset.UTC))
                .transactionType(Transaction.TransactionType.TRANSFER)
                .build();
        return transactionRepository.save(transaction);
    }

    public void saveRechargeTransaction(Register toRegister, TransferValue transferValue) {
        Transaction transaction = Transaction.builder()
                .toRegister(toRegister)
                .transferValue(transferValue)
                .transactionDateTime(LocalDateTime.now(ZoneOffset.UTC))
                .transactionType(Transaction.TransactionType.RECHARGE)
                .build();
        transactionRepository.save(transaction);
    }

}
