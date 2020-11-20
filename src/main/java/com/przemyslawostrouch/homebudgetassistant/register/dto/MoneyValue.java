package com.przemyslawostrouch.homebudgetassistant.register.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@Setter
@Getter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class MoneyValue {
    private BigDecimal value;
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Getter
    @AllArgsConstructor
    public enum Currency {
        PLN("polish zloty");

        private final String fullName;
    }
}