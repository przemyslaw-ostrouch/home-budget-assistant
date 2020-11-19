package com.przemyslawostrouch.homebudgetassistant.register.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public abstract class MoneyValue {
    private BigDecimal value;
    private Currency currency;

    protected MoneyValue() {
    }

    @AllArgsConstructor
    enum Currency {
        PLN("polish zloty");

        private final String fullName;
    }
}
