package com.przemyslawostrouch.homebudgetassistant.register.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Balance {

    @Column(scale = 6, precision = 12)
    private BigDecimal value;

    public void recharge(BigDecimal rechargeAmount) {
        setValue(value.add(rechargeAmount));
    }
}
