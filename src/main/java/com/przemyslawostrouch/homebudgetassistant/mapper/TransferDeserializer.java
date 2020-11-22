package com.przemyslawostrouch.homebudgetassistant.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue;

import java.io.IOException;
import java.math.BigDecimal;

public class TransferDeserializer extends StdDeserializer<TransferValue> {
    protected TransferDeserializer(Class<TransferValue> t) {
        super(t);
    }

    public TransferDeserializer() {
        this(null);
    }

    @Override
    public TransferValue deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
//        String value = node.get("value").asText();
        return new TransferValue(new BigDecimal(node.asText()));
    }
}
