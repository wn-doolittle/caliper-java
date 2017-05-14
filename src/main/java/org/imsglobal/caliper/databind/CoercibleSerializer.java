package org.imsglobal.caliper.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.imsglobal.caliper.entities.Coercible;

import java.io.IOException;

public class CoercibleSerializer extends JsonSerializer<Coercible> {
    private JsonSerializer<Object> defaultSerializer;

    /**
     * Constructor
     */
    public CoercibleSerializer() {
        this(null);
    }

    /**
     * Constructor that injects default serializer.
     * @param defaultSerializer
     */
    public CoercibleSerializer(JsonSerializer<Object> defaultSerializer) {
        this.defaultSerializer = defaultSerializer;
    }

    @Override
    public void serialize(Coercible value, JsonGenerator jgen, SerializerProvider provider)
        throws IOException, JsonProcessingException {

        // System.out.print("IS_COERCED: " + value.getClass().getSimpleName() + " " + value.isCoercedToId() + "\n");

        if (value.isCoercedToId()) {
            jgen.writeString(value.getId());
        } else {
            defaultSerializer.serialize(value, jgen, provider);
        }
    }
}