package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.annotation.Annotation;

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
        if (object instanceof Annotation) {
            //TODO CONSIDER ADDING REVERSE LOOKUP TO ENUM SO THAT ENUM CAN BE RETURNED FOR USE IN SWITCH STATEMENT
            String type = ((Annotation) object).getType();
            if (type.equals(Annotation.Type.BOOKMARK_ANNOTATION.uri())) {
                // TODO CHECK REQUIRED PROPS
            } else if (type.equals(Annotation.Type.HIGHLIGHT_ANNOTATION.uri())) {
                // TODO CHECK REQUIRED PROPS
            } else if (type.equals(Annotation.Type.SHARED_ANNOTATION.uri())) {
                // TODO CHECK REQUIRED PROPS
            } else if (type.equals(Annotation.Type.TAG_ANNOTATION.uri())) {
                // TODO CHECK REQUIRED PROPS
            } else {
                // TODO THROW ERROR UNRECOGNIZED URI
            }
            // TODO add additional checks

            return (Annotation) object;
        } else {
            throw new ClassCastException("Object must be of type Annotation.");
        }
    }

    /**
     * @param target
     * @return target DigitalResource.
     */
    public static DigitalResource validateTarget(Object target) {
        if (target instanceof DigitalResource) {
            // TODO add additional checks
            return (DigitalResource) target;
        } else {
            throw new ClassCastException("Target must be of type DigitalResource.");
        }
    }
}