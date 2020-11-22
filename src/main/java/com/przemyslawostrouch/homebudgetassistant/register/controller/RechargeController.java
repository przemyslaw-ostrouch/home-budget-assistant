package com.przemyslawostrouch.homebudgetassistant.register.controller;

import com.przemyslawostrouch.homebudgetassistant.register.RegisterManager;
import com.przemyslawostrouch.homebudgetassistant.register.dto.RechargeRequest;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/recharges")
public class RechargeController {

    private final RegisterManager registerManager;

    @PostMapping
    public Register rechargeRegister(@RequestBody RechargeRequest rechargeRequest) {
        return registerManager.rechargeRegister(rechargeRequest);
    }
}
