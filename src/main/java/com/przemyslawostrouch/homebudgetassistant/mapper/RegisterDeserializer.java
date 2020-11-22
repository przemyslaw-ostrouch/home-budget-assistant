package com.przemyslawostrouch.homebudgetassistant.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.przemyslawostrouch.homebudgetassistant.register.dto.Balance;
import com.przemyslawostrouch.homebudgetassistant.register.entity.Register;

import java.io.IOException;
import java.math.BigDecimal;

public class RegisterDeserializer extends StdDeserializer<Register> {
    protected RegisterDeserializer(Class<Register> t) {
        super(t);
    }

    public RegisterDeserializer() {
        this(null);
    }

    @Override
    public Register deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        Long id = node.get("id").asLong();
        String name = node.get("name").asText();
        String balance = node.get("balance").asText();

        return new Register(id, name, new Balance(new BigDecimal(balance)));
    }
}
