package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.entities.annotation.*;
import org.imsglobal.caliper.entities.reading.Frame;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AnnotationProfile extends org.imsglobal.caliper.profiles.Profile {

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

    /**
     * Constructor
     */
    public AnnotationProfile() {

    }

    /**
     * @param key
     * @return localized action string.
     */
    public static String getActionFromBundle(String key) {
        if (AnnotationActions.hasKey(key) || Actions.hasKey(key)) {
            return ResourceBundle.getBundle("actions").getString(key);
        } else {
            throw new IllegalArgumentException("Unrecognized key: " + key);
        }
    }

    /**
     * @param object
     * @return activityContext object.
     */
    public static Annotation validateObject(Object object) {
        if (object instanceof BookmarkAnnotation) {
            // TODO CHECK REQUIRED PROPS
            return (BookmarkAnnotation) object;
        } else if (object instanceof HighlightAnnotation) {
            // TODO CHECK REQUIRED PROPS
            return (HighlightAnnotation) object;
        } else if (object instanceof SharedAnnotation) {
            // TODO CHECK REQUIRED PROPS
            return (SharedAnnotation) object;
        } else if (object instanceof TagAnnotation) {
            // TODO CHECK REQUIRED PROPS
            return (TagAnnotation) object;
        } else {
            throw new ClassCastException("Object must be of type Annotation.");
        }
    }

    /**
     * @param target
     * @return target Frame.
     */
    public static Frame validateTarget(Targetable target) {
        if (target instanceof Frame) {
            // TODO add additional checks
            return (Frame) target;
        } else {
            throw new ClassCastException("Target must be of type Frame.");
        }
    }
}