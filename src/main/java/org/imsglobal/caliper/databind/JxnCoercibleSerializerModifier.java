package org.imsglobal.caliper.databind;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import org.imsglobal.caliper.entities.Coercible;

public class CoercibleSerializerModifier extends BeanSerializerModifier {
    @Override
    public JsonSerializer<?> modifySerializer(
        SerializationConfig config, BeanDescription desc, JsonSerializer<?> serializer) {
        if (Coercible.class.isAssignableFrom(desc.getBeanClass())) {
            return new CoercibleSerializer((JsonSerializer<Object>) serializer);
        }
        return serializer;
    }
}
