package com.przemyslawostrouch.homebudgetassistant.register.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
public class Balance {

    private BigDecimal value;

    public Balance(BigDecimal value) {
        this.value = value.setScale(2, RoundingMode.HALF_UP);
    }
}
