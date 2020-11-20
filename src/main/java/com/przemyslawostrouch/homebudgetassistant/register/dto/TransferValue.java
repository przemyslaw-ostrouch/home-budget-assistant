package com.przemyslawostrouch.homebudgetassistant.register.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@AllArgsConstructor
public class TransferValue {
    private final MoneyValue moneyValue;
}

