package org.imsglobal.caliper.databind;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import java.util.List;

/**
 * Provides a factory method for initializing a Caliper-friendly Jackson ObjectMapper with a custom configuration.
 */
public class JxnObjectMapper {

    /**
     * Constructor.  Scope is private to force use of the factory method.
     */
    private JxnObjectMapper() {

    }

    /**
     * Default factory method that returns a Caliper-friendly Jackson ObjectMapper instance.
     *
     * Base configuration
     *
     * setDateFormat(DateFormat df)         -- establishes the default DateFormat when serializing time values as
     *                                      Strings and deserializing from JSON Strings.  ISO 86001 Date format
     *                                      is required.
     * setSerializationInclusion(Include i) -- Enumeration used with Jackson's JsonInclude that defines which
     *                                      properties are to be serialized. Default to NON_EMPTY.
     * setFilterProvider(FilterProvider fp) -- specifies the FilterProvider to use for mapping Filter Ids
     *                                      to actual filter instances.
     * registerModules(module, module ...)  -- register modules that extend the functionality of the mapper.
     *         JodaModule()                 -- leverage Joda date time library.
     *         CoercibleSimpleModule()      -- adds a custom serializer for entities coerced
     *                                      to their identifiers.
     *
     * @return ObjectMapper
     */
    public static ObjectMapper create() {
        SimpleFilterProvider provider = JxnFilterProvider.create();
        ObjectMapper mapper = createBaseObjectMapper(JsonInclude.Include.NON_EMPTY, provider);
        mapper.registerModules(new JodaModule(), new JxnCoercibleSimpleModule());

        return mapper;
    }

    /**
     * Overloaded factory method that allows the user to set
     * (1) the Inclusion rule to use for instances (values) of types (Classes) or properties annotated.
     * (2) a Filter Provider
     * (3) a single module to add additional functionality.
     *
     * Base configuration
     *
     * setDateFormat(DateFormat df)         -- establishes the default DateFormat when serializing time values as
     *                                      Strings and deserializing from JSON Strings.  ISO 86001 Date format
     *                                      is required.
     * setSerializationInclusion(Include i) -- Enumeration used with Jackson's JsonInclude that defines which
     *                                      properties are to be serialized.
     * setFilterProvider(FilterProvider fp) -- specifies the FilterProvider to use for mapping Filter Ids
     *                                      to actual filter instances.
     * registerModules(module, module ...)  -- register modules that extend the functionality of the mapper.
     *
     * @param include
     * @param provider
     * @return ObjectMapper
     */
    public static ObjectMapper create(JsonInclude.Include include, FilterProvider provider, Module module) {
        ObjectMapper mapper = createBaseObjectMapper(include, provider);
        mapper.registerModule(module);

        return mapper;
    }

    /**
     * Overloaded factory method that allows the user to set
     * (1) the Inclusion rule to use for instances (values) of types (Classes) or properties annotated.
     * (2) a Filter Provider
     * (3) multiple modules to add additional functionality.
     *
     * Base configuration
     *
     * setDateFormat(DateFormat df)         -- establishes the default DateFormat when serializing time values as
     *                                      Strings and deserializing from JSON Strings.  ISO 86001 Date format
     *                                      is required.
     * setSerializationInclusion(Include i) -- Enumeration used with Jackson's JsonInclude that defines which
     *                                      properties are to be serialized.
     * setFilterProvider(FilterProvider fp) -- specifies the FilterProvider to use for mapping Filter Ids
     *                                      to actual filter instances.
     * registerModules(module, module ...)  -- register modules that extend the functionality of the mapper.
     *
     * @param include
     * @param provider
     * @return ObjectMapper
     */
    public static ObjectMapper create(JsonInclude.Include include, FilterProvider provider, List<Module> modules) {
        ObjectMapper mapper = createBaseObjectMapper(include, provider);
        mapper.registerModules(modules);

        return mapper;
    }

    /**
     * Factory method that provisions a Caliper-friendly ObjectMapper as a prelude to registering modules.
     * @param include
     * @param provider
     * @return ObjectMapper
     */
    private static ObjectMapper createBaseObjectMapper(JsonInclude.Include include, FilterProvider provider) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(new ISO8601DateFormat());
        mapper.setSerializationInclusion(include);
        mapper.setFilterProvider(provider);

        return mapper;
    }
}