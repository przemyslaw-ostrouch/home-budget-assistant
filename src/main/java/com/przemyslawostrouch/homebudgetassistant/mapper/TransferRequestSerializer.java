package com.przemyslawostrouch.homebudgetassistant.mapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferRequest;

import java.io.IOException;

public class TransferRequestSerializer extends StdSerializer<TransferRequest> {
    protected TransferRequestSerializer(Class<TransferRequest> t) {
        super(t);
    }

    public TransferRequestSerializer() {
        this(null);
    }

    @Override
    public void serialize(TransferRequest transferRequest, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("fromRegisterId", transferRequest.getFromRegisterId());
        gen.writeNumberField("toRegisterId", transferRequest.getToRegisterId());
        gen.writeNumberField("transfer", transferRequest.getTransfer().getValue());
        gen.writeEndObject();
    }
}
