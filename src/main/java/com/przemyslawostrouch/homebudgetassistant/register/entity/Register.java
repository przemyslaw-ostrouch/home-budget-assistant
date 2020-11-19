package com.przemyslawostrouch.homebudgetassistant.register.entity;

import com.przemyslawostrouch.homebudgetassistant.register.dto.Balance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Register {
    private String name;
    private Balance balance;
}
