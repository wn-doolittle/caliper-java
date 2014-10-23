package org.imsglobal.caliper.actions;

import java.util.HashMap;
import java.util.Map;

public enum AssignableActions {
    ABANDONED("assignable.abandoned"),
    ACTIVATED("assignable.activated"),
    COMPLETED("assignable.completed"),
    DEACTIVATED("assignable.deactivated"),
    HID("assignable.hid"),
    REVIEWED("assignable.reviewed"),
    SHOWED("assignable.showed"),
    STARTED("assignable.started"),
    SUBMITTED("assignable.submitted"),

    NAVIGATED_TO("navigation.navigatedTo");

    private final String key;
    private static final Map<String, AssignableActions> lookup = new HashMap<String, AssignableActions>();

    /**
     * Create reverse lookup hash map
     */
    static {
        for (AssignableActions constants : AssignableActions.values())
            lookup.put(constants.key(), constants);
    }

    /**
     * Constructor
     * @param key
     */
    private AssignableActions(String key) {
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
    public static AssignableActions lookupConstant(String key) {
        return lookup.get(key);
    }
}
