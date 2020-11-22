package com.przemyslawostrouch.homebudgetassistant.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferRequest;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue;

import java.io.IOException;
import java.math.BigDecimal;

public class TransferRequestDeserializer extends StdDeserializer<TransferRequest> {
    protected TransferRequestDeserializer(Class<TransferRequest> t) {
        super(t);
    }

    public TransferRequestDeserializer() {
        this(null);
    }

    @Override
    public TransferRequest deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        Long fromRegisterId = node.get("fromRegisterId").asLong();
        Long toRegisterId = node.get("toRegisterId").asLong();
        String transfer = node.get("transfer").asText();

        return new TransferRequest(fromRegisterId, toRegisterId, new TransferValue(new BigDecimal(transfer)));
    }
}
