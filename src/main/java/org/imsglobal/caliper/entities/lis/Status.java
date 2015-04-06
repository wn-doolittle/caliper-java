package org.imsglobal.caliper.entities.lis;

import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

public enum Status {
    ACTIVE("http://purl.imsglobal.org/vocab/lis/v2/status#Active"),
    DELETED("http://purl.imsglobal.org/vocab/lis/v2/status#Deleted"),
    INACTIVE("http://purl.imsglobal.org/vocab/lis/v2/status#Inactive");

    private final String value;
    private static Map<String, Status> lookup;

    /**
     * Create reverse lookup hash map
     */
    static {
        Map<String, Status> map = new HashMap<String, Status>();
        for (Status constants : Status.values()) {
            map.put(constants.value(), constants);
        }
        lookup = ImmutableMap.copyOf(map);
    }

    /**
     * Private constructor
     * @param value
     */
    private Status(final String value) {
        this.value = value;
    }

    /**
     * @param key
     * @return true if lookup returns a key match; false otherwise.
     */
    public static boolean hasKey(String key) {
        return lookup.containsKey(key);
    }

    /**
     * @return the URI
     */
    @JsonValue
    public String value() {
        return value;
    }
}