package com.przemyslawostrouch.homebudgetassistant.register.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.przemyslawostrouch.homebudgetassistant.mapper.TransferRequestDeserializer;
import com.przemyslawostrouch.homebudgetassistant.mapper.TransferRequestSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = TransferRequestSerializer.class)
@JsonDeserialize(using = TransferRequestDeserializer.class)
public class TransferRequest {
    private Long fromRegisterId;
    private Long toRegisterId;
    private TransferValue transfer;

    public void validateTransfer() {
        transfer.validate();
    }
}