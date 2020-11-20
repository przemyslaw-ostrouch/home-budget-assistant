package com.przemyslawostrouch.homebudgetassistant.register.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {
    private Long fromRegisterId;
    private Long toRegisterId;
    private TransferValue transfer;

    public BigDecimal getTransferValue() {
        return transfer.getValue();
    }
}
