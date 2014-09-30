package org.imsglobal.caliper.actions;

import java.util.HashMap;
import java.util.Map;

public enum OutcomeActions {
    GRADED("outcome.graded");
    // POSTED("outcome.posted");
    // REPORTED("outcome.reported");

    private final String key;
    private static final Map<String, OutcomeActions> lookup = new HashMap<String, OutcomeActions>();

    /**
     * Create reverse lookup hash map
     */
    static {
        for (OutcomeActions constants : OutcomeActions.values())
            lookup.put(constants.key(), constants);
    }

    /**
     * Constructor
     * @param key
     */
    private OutcomeActions(String key) {
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
        return lookup.containsKey(key);
    }

    /**
     * @param key
     * @return enum constant by reverse lookup
     */
    public static OutcomeActions lookupConstant(String key) {
        return lookup.get(key);
    }
}