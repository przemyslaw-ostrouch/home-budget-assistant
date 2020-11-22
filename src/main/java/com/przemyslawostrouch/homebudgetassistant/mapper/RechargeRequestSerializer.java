package com.przemyslawostrouch.homebudgetassistant.mapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.przemyslawostrouch.homebudgetassistant.register.dto.RechargeRequest;

import java.io.IOException;

public class RechargeRequestSerializer extends StdSerializer<RechargeRequest> {
    protected RechargeRequestSerializer(Class<RechargeRequest> t) {
        super(t);
    }

    public RechargeRequestSerializer() {
        this(null);
    }

    @Override
    public void serialize(RechargeRequest rechargeRequest, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("toRegisterId", rechargeRequest.getToRegisterId());
        gen.writeNumberField("transfer", rechargeRequest.getTransferValue());
        gen.writeEndObject();
    }
}
