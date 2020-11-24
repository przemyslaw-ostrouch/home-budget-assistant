package com.przemyslawostrouch.homebudgetassistant.register.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Embeddable
@NoArgsConstructor
public class TransferValue {
    private BigDecimal value;

    public TransferValue(BigDecimal value) {
        this.value = value.setScale(2, RoundingMode.HALF_UP);
    }

    public void validate() {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Transfer value has to be positive");
        }
    }
}

