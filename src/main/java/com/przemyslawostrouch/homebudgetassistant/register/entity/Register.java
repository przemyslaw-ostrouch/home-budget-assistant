package com.przemyslawostrouch.homebudgetassistant.register.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.przemyslawostrouch.homebudgetassistant.register.dto.Balance;
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

    public BigDecimal getBalanceValue(){
        return balance.getValue();
    }
}
