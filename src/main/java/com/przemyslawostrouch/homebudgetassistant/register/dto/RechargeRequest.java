package com.przemyslawostrouch.homebudgetassistant.register.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.przemyslawostrouch.homebudgetassistant.mapper.RechargeRequestDeserializer;
import com.przemyslawostrouch.homebudgetassistant.mapper.RechargeRequestSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonSerialize(using = RechargeRequestSerializer.class)
@JsonDeserialize(using = RechargeRequestDeserializer.class)
public class RechargeRequest {
    private final Long toRegisterId;
    private final TransferValue transfer;

    public void validateTransferValue(){
        transfer.validate();
    }
}