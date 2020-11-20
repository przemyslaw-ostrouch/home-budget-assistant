package com.przemyslawostrouch.homebudgetassistant.register.controller;

import com.przemyslawostrouch.homebudgetassistant.register.RegisterManager;
import com.przemyslawostrouch.homebudgetassistant.register.dto.Balance;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/registers")
public class RegisterController {

    private final RegisterManager registerManager;

    @GetMapping("/{registerId}/balance")
    public Balance getRegisterBalance(@PathVariable Long registerId){
        return registerManager.getBalance(registerId);
    }

    @PutMapping("/{registerId}")
    public Register rechargeRegister(@PathVariable Long registerId, @RequestBody TransferValue transferValue){
        return registerManager.rechargeRegister(registerId, transferValue);
    }
}

