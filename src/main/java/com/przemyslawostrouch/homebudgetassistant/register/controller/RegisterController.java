package com.przemyslawostrouch.homebudgetassistant.register.controller;

import com.przemyslawostrouch.homebudgetassistant.register.RegisterManager;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/registers")
public class RegisterController {

    private final RegisterManager registerManager;

    @GetMapping
    public List<Register> getAll() {
        return registerManager.getAll();
    }
}

