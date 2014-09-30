package org.imsglobal.caliper.actions;

import java.util.HashMap;
import java.util.Map;

public enum ReadingActions {
    NAVIGATEDTO("reading.navigatedTo"),
    SEARCHED("reading.searched"),
    VIEWED("reading.viewed");

    private final String key;
    private static final Map<String, ReadingActions> lookup = new HashMap<String, ReadingActions>();

    /**
     * Create reverse lookup hash map
     */
    static {
        for (ReadingActions constants : ReadingActions.values())
            lookup.put(constants.key(), constants);
    }

    /**
     * Constructor
     * @param key
     */
    private ReadingActions(String key) {
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
    public static ReadingActions lookupConstant(String key) {
        return lookup.get(key);
    }
}