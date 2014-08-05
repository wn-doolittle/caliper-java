package org.imsglobal.caliper.actions;

import java.util.HashMap;
import java.util.Map;

public enum AssessmentActions {
    STARTED("assessment.started"),
    PAUSED("assessment.paused"),
    RESTARTED("assessment.restarted"),
    SUBMITTED("assessment.submitted"),

    ANSWERED("assessment.item.answered"),
    ATTEMPTED("assessment.item.attempted"),
    CHALLENGED("assessment.item.challenged"),
    DESLECTEDOPTION("assessment.item.deselectedoption"),
    MARKEDFORREVIEW("assessment.item.markedforreview"),
    SELECTEDOPTION("assessment.item.selectedoption"),
    SKIPPED("assessment.item.skipped"),
    VIEWED("assessment.item.viewed");

    private final String key;
    private static final Map<String, AssessmentActions> lookup = new HashMap<String, AssessmentActions>();

    /**
     * Create reverse lookup hash map
     */
    static {
        for (AssessmentActions constants : AssessmentActions.values())
            lookup.put(constants.key(), constants);
    }

    /**
     * Constructor
     * @param key
     */
    private AssessmentActions(String key) {
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
    public static AssessmentActions lookupConstant(String key) {
        return lookup.get(key);
    }
}