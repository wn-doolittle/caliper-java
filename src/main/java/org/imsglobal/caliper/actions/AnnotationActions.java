package org.imsglobal.caliper.actions;

import java.util.HashMap;
import java.util.Map;

public enum AnnotationActions {
    ATTACHED("annotation.attached"),
    BOOKMARKED("annotation.bookmarked"),
    CLASSIFIED("annotation.classified"),
    COMMENTED("annotation.commented"),
    DESCRIBED("annotation.described"),
    HIGHLIGHTED("annotation.highlighted"),
    IDENTIFIED("annotation.identified"),
    LIKED("annotation.liked"),
    LINKED("annotation.linked"),
    RANKED("annotation.ranked"),
    QUESTIONED("annotation.questioned"),
    RECOMMENDED("annotation.recommended"),
    REPLIED("annotation.replied"),
    SHARED("annotation.shared"),
    SUBSCRIBED("annotation.subscribed"),
    TAGGED("annotation.tagged");

    private final String key;
    private static final Map<String, AnnotationActions> lookup = new HashMap<String, AnnotationActions>();

    /**
     * Create reverse lookup hash map
     */
    static {
        for (AnnotationActions constants : AnnotationActions.values())
            lookup.put(constants.key(), constants);
    }

    /**
     * Constructor
     * @param key
     */
    private AnnotationActions(String key) {
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
    public static AnnotationActions lookupConstant(String key) {
        return lookup.get(key);
    }
}