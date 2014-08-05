package org.imsglobal.caliper.actions;

import java.util.HashMap;
import java.util.Map;

public enum PerformanceActions {
    GRADED("performance.graded"),
    POSTED("performance.posted");
    // REPORTED("performance.reported");

    private final String key;
    private static final Map<String, PerformanceActions> lookup = new HashMap<String, PerformanceActions>();

    /**
     * Create reverse lookup hash map
     */
    static {
        for (PerformanceActions constants : PerformanceActions.values())
            lookup.put(constants.key(), constants);
    }

    /**
     * Constructor
     * @param key
     */
    private PerformanceActions(String key) {
        this.key = key;
    }

    /**
     * @return ResourceBundle key for internationalized action strings.
     */
    public String key() {
        return key;
    }

    /**
     * @param key
     * @return true if lookup returns a key match; false otherwise.
     */
    public static boolean hasKey(String key) {
        return lookup.containsKey(key.toLowerCase());
    }

    /**
     * @param key
     * @return enum constant by reverse lookup
     */
    public static PerformanceActions lookupConstant(String key) {
        return lookup.get(key);
    }
}