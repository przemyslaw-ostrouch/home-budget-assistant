package com.przemyslawostrouch.homebudgetassistant.register.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class TransferValue {
    private BigDecimal value;

    public void validate() {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Transfer value has to be positive");
        }
    }
}

