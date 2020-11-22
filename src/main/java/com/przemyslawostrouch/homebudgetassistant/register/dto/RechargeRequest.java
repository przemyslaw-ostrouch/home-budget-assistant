package com.przemyslawostrouch.homebudgetassistant.register.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.przemyslawostrouch.homebudgetassistant.mapper.RechargeRequestDeserializer;
import com.przemyslawostrouch.homebudgetassistant.mapper.RechargeRequestSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = RechargeRequestSerializer.class)
@JsonDeserialize(using = RechargeRequestDeserializer.class)
public class RechargeRequest {
    private Long toRegisterId;
    private TransferValue transfer;

    public BigDecimal getTransferValue(){
        return transfer.getValue();
    }

    public void validateTransferValue(){
        transfer.validate();
    }
}