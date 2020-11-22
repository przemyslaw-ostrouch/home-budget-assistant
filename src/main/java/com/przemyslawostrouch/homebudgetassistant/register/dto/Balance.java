package com.przemyslawostrouch.homebudgetassistant.register.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Balance {

    private BigDecimal value;
}
