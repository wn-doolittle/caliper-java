package org.imsglobal.caliper.databind;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import java.util.Map;

public class JxnFilterProvider {

    /**
     * Constructor
     */
    private JxnFilterProvider() {

    }

    /**
     * Factory method that returns a Caliper-friendly SimpleFilterProvider instance.
     * @return SimpleFilterProvider
     */
    public static SimpleFilterProvider create() {
        return createBaseSimpleFilterProvider();
    }

    /**
     * Factory method that returns a Caliper-friendly JsonSimpleFilterProvider instance
     * provisioned with a single filter.
     * @return JsonSimpleFilterProvider
     */
    public static SimpleFilterProvider create(String id, SimpleBeanPropertyFilter filter) {
        SimpleFilterProvider provider = createBaseSimpleFilterProvider();
        provider.addFilter(id, filter);

        return provider;
    }

    /**
     * Factory method that returns a Caliper-friendly JsonSimpleFilterProvider instance
     * provisioned with multiple filters.
     * @return JsonSimpleFilterProvider
     */
    public static SimpleFilterProvider create(Map<String, SimpleBeanPropertyFilter> filters) {
        SimpleFilterProvider provider = createBaseSimpleFilterProvider();
        for (Map.Entry<String, SimpleBeanPropertyFilter> entry : filters.entrySet()) {
            provider.addFilter(entry.getKey(), entry.getValue());
        }

        return provider;
    }

    /**
     * Factory method that provisions a Caliper-friendly SimpleFilterProvider as a prelude to adding Filters.
     * @return SimpleFilterProvider
     */
    private static SimpleFilterProvider createBaseSimpleFilterProvider() {
        SimpleFilterProvider provider = new SimpleFilterProvider();
        provider.setDefaultFilter(JxnFilters.EXCLUDE_CONTEXT.filter());
        provider.setFailOnUnknownId(true);

        return provider;
    }
}