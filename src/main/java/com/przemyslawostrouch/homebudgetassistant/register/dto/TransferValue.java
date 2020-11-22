package com.przemyslawostrouch.homebudgetassistant.register.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.przemyslawostrouch.homebudgetassistant.mapper.TransferSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
//@JsonDeserialize(using = TransferDeserializer.class)
@JsonSerialize(using = TransferSerializer.class)
public class TransferValue {
    private BigDecimal value;

    public void validate() {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Transfer value has to be positive");
        }
    }
}

