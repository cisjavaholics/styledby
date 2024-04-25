package cis.javaholics.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.google.cloud.Timestamp;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;


public class TimestampDeserializer extends StdDeserializer<Timestamp> {

    protected TimestampDeserializer() {
        super(Timestamp.class);
    }
    @Override
    public Timestamp  deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        String timestampStr = node.asText();
        return Timestamp.parseTimestamp(timestampStr);
    }
}
