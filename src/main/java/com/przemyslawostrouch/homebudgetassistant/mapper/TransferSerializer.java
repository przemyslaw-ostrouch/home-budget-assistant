package com.przemyslawostrouch.homebudgetassistant.mapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue;

import java.io.IOException;

public class TransferSerializer extends StdSerializer<TransferValue> {
    protected TransferSerializer(Class<TransferValue> t) {
        super(t);
    }

    public TransferSerializer() {
        this(null);
    }

    @Override
    public void serialize(TransferValue transferValue, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("transfer", transferValue.getValue());
        gen.writeEndObject();
    }
}
