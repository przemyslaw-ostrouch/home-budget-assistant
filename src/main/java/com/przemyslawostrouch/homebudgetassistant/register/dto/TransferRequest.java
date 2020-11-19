package com.przemyslawostrouch.homebudgetassistant.register.dto;

import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;
import lombok.Getter;

@Getter
public class TransferRequest {
    private Register fromRegister;
    private Register toRegister;
    private TransferValue transferValue;
}
