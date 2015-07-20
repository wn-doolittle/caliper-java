package org.imsglobal.caliper.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.joda.JodaModule;

public class JsonMapper {

    /**
     * Constructor
     */
    public JsonMapper() {

    }

    /**
     * Convert object to JSON
     * @param data
     * @return JSON string
     * @throws JsonProcessingException
     */
    public static String serialize(Object data, JsonInclude.Include include) throws JsonProcessingException {
        return createMapper(include).writeValueAsString(data);
    }

    /**
     * Create mapper.
     * @return mapper
     */
    private static ObjectMapper createMapper(JsonInclude.Include include) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new ISO8601DateFormat());
        mapper.setSerializationInclusion(include);
        mapper.registerModule(new JodaModule());

        return mapper;
    }
}
