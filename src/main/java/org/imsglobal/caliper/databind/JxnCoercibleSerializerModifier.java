package org.imsglobal.caliper.databind;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import org.imsglobal.caliper.entities.CaliperCoercible;

public class JxnCoercibleSerializerModifier extends BeanSerializerModifier {
    @Override
    public JsonSerializer<?> modifySerializer(
        SerializationConfig config, BeanDescription desc, JsonSerializer<?> serializer) {
        if (CaliperCoercible.class.isAssignableFrom(desc.getBeanClass())) {
            return new JxnCoercibleSerializer((JsonSerializer<Object>) serializer);
        }
        return serializer;
    }
}
