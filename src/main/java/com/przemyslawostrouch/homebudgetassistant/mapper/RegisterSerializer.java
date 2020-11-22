package com.przemyslawostrouch.homebudgetassistant.mapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;

import java.io.IOException;

public class RegisterSerializer extends StdSerializer<Register> {
    protected RegisterSerializer(Class<Register> t) {
        super(t);
    }

    public RegisterSerializer() {
        this(null);
    }

    @Override
    public void serialize(Register register, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", register.getId());
        gen.writeStringField("name", register.getName());
        gen.writeNumberField("balance", register.getBalanceValue());
        gen.writeEndObject();
    }
}
