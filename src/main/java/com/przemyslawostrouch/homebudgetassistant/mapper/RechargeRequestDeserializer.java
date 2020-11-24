package com.przemyslawostrouch.homebudgetassistant.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.przemyslawostrouch.homebudgetassistant.register.dto.RechargeRequest;
import com.przemyslawostrouch.homebudgetassistant.register.dto.TransferValue;

import java.io.IOException;
import java.math.BigDecimal;

public class RechargeRequestDeserializer extends StdDeserializer<RechargeRequest> {
    protected RechargeRequestDeserializer(Class<RechargeRequest> t) {
        super(t);
    }

    public RechargeRequestDeserializer() {
        this(null);
    }

    @Override
    public RechargeRequest deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        Long id = node.get("toRegisterId").asLong();
        String transfer = node.get("transfer").asText();

        return new RechargeRequest(id, new TransferValue(new BigDecimal(transfer)));
    }
}
