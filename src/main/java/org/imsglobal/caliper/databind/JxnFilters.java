package org.imsglobal.caliper.databind;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public enum Filters {
    EXCLUDE_CONTEXT("entityFilter", SimpleBeanPropertyFilter.serializeAllExcept("@context")),
    ID_ONLY("idOnlyFilter", SimpleBeanPropertyFilter.filterOutAllExcept("id")),
    SERIALIZE_ALL("defaultFilter", SimpleBeanPropertyFilter.serializeAll());

    private final String id;
    private final SimpleBeanPropertyFilter filter;
    private static final Map<String, SimpleBeanPropertyFilter> filters;

    static {
        Map<String, SimpleBeanPropertyFilter> map = new HashMap<>();
        for (Filters constants : Filters.values()) {
            map.put(constants.id(), constants.filter());
        }
        filters = ImmutableMap.copyOf(map);
    }

    /**
     * Private constructor
     * @param id
     */
    private Filters(final String id, final SimpleBeanPropertyFilter filter) {
        this.id = id;
        this.filter = filter;
    }

    /**
     * Retrieve id.
     * @return id
     */
    public String id() {
        return id;
    }

    /**
     * Retrieve filter.
     * @return filter
     */
    public SimpleBeanPropertyFilter filter() {
        return filter;
    }

    /**
     * Retrieve filters map.
     * @return filters map
     */
    public static Map<String, SimpleBeanPropertyFilter> filters() {
        return filters;
    }

    /**
     * Retrieve PropertyFilter by id.
     * @param id
     * @return SimplePropertyFilter
     */
    public static SimpleBeanPropertyFilter getFilterById(String id) {
        return filters.get(id);
    }
}