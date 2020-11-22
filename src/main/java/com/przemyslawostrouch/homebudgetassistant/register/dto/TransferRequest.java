package com.przemyslawostrouch.homebudgetassistant.register.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {
    private Long fromRegisterId;
    private Long toRegisterId;
    private TransferValue transfer;

    public void validateTransfer() {
        transfer.validate();
    }
}