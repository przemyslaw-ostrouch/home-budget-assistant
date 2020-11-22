package com.przemyslawostrouch.homebudgetassistant.register.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.przemyslawostrouch.homebudgetassistant.register.dto.Balance;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue;
import com.przemyslawostrouch.homebudgetassistant.serializer.RegisterDeserializer;
import com.przemyslawostrouch.homebudgetassistant.serializer.RegisterSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = RegisterSerializer.class)
@JsonDeserialize(using = RegisterDeserializer.class)
public class Register {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Embedded
    private Balance balance;

    public BigDecimal getBalanceValue() {
        return balance.getValue();
    }

    public boolean isBalanceEnough(TransferValue transfer) {
        return getBalanceValue().compareTo(transfer.getValue()) >= 0;
    }

    public void reduceBalanceValue(TransferValue transfer) {
        balance.setValue(getBalanceValue().subtract(transfer.getValue()));
    }

    public void increaseBalanceValue(TransferValue transfer) {
        balance.setValue(getBalanceValue().add(transfer.getValue()));
    }

    public void recharge(BigDecimal rechargeAmount) {
        balance.setValue(getBalanceValue().add(rechargeAmount));
    }
}
