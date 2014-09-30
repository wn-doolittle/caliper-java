package org.imsglobal.caliper.actions;

import java.util.HashMap;
import java.util.Map;

public enum AssessmentItemActions {
    STARTED("assessment.item.started"),
    COMPLETED("assessment.item.completed"),
    SKIPPED("assessment.item.skipped"),
    REVIEWED("assessment.item.reviewed"),
    VIEWED("assessment.item.viewed");

    private final String key;
    private static final Map<String, AssessmentItemActions> lookup = new HashMap<String, AssessmentItemActions>();

    /**
     * Create reverse lookup hash map
     */
    static {
        for (AssessmentItemActions constants : AssessmentItemActions.values())
            lookup.put(constants.key(), constants);
    }

    /**
     * Constructor
     * @param key
     */
    private AssessmentItemActions(String key) {
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
    public static AssessmentItemActions lookupConstant(String key) {
        return lookup.get(key);
    }
}