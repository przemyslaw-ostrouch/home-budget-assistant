package com.przemyslawostrouch.homebudgetassistant.register.dto;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Embeddable
@NoArgsConstructor
public class Balance extends MoneyValue {

    @Builder
    private Balance(BigDecimal value, Currency currency) {
        super(value, currency);
    }

    public void recharge(BigDecimal rechargeAmount){
        setValue(getValue().add(rechargeAmount));
    }
}
