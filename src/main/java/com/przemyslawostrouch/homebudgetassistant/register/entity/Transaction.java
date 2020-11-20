package com.przemyslawostrouch.homebudgetassistant.register.entity;

import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter

@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Register fromRegister;
    private Register toRegister;
    private TransferValue transferValue;
    private LocalDateTime transactionDateTime;
}

