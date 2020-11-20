package com.przemyslawostrouch.homebudgetassistant.register.controller;

import com.przemyslawostrouch.homebudgetassistant.register.TransferManager;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferRequest;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/transfers")
public class TransferController {

    private final TransferManager transferManager;

    @PostMapping
    public Transaction makeTransfer(@RequestBody TransferRequest transferRequest){
        return transferManager.transfer(transferRequest);
    }
}
