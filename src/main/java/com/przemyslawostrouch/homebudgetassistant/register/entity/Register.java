package com.przemyslawostrouch.homebudgetassistant.register.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.przemyslawostrouch.homebudgetassistant.exception.IncorrectRegisterBalanceException;
import com.przemyslawostrouch.homebudgetassistant.mapper.RegisterDeserializer;
import com.przemyslawostrouch.homebudgetassistant.mapper.RegisterSerializer;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue;
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

    public void validateBalance(TransferValue transfer) {
        if (balance.getValue().compareTo(transfer.getValue()) < 0) {
            throw new IncorrectRegisterBalanceException("Not enough amount of money, debit not possible");
        }
    }

    public Register reduceBalanceValue(TransferValue transfer) {
        BigDecimal newBalance = balance.getValue().subtract(transfer.getValue());
        return new Register(
                id,
                name,
                new Balance(newBalance)
        );
    }

    public Register recharge(TransferValue rechargeAmount) {
        BigDecimal newBalance = balance.getValue().add(rechargeAmount.getValue());
        return new Register(
                id,
                name,
                new Balance(newBalance)
        );
    }
}
