package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.assessment.AssessmentItem;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AssessmentItemProfile extends org.imsglobal.caliper.profiles.Profile {

    public enum AssessmentItemActions {
        STARTED("assessment.item.started"),
        COMPLETED("assessment.item.completed"),
        SKIPPED("assessment.item.skipped"),
        REVIEWED("assessment.item.reviewed"),
        VIEWED("assessment.item.viewed"),

        NAVIGATED_TO("navigation.navigatedTo");

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
         *
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

    /**
     * Constructor
     */
    public AssessmentItemProfile() {

    }

    /**
     * @param key
     * @return localized action string.
     */
    public static String getActionFromBundle(String key) {
        if (AssessmentItemActions.hasKey(key) || Actions.hasKey(key)) {
            return ResourceBundle.getBundle("actions").getString(key);
        } else {
            throw new IllegalArgumentException("Unrecognized key: " + key);
        }
    }

    /**
     * @param object
     * @return assessment item.
     */
    public static AssessmentItem validateObject(Object object) {
        if (object instanceof AssessmentItem) {
            // TODO add additional checks
            return (AssessmentItem) object;
        } else {
            throw new ClassCastException("Object must be of type AssessmentItem.");
        }
    }

    /**
     * @param generated
     * @return generated response.
     */
    /**
    public static Response validateGenerated(Generatable generated) {
        if (generated instanceof Response) {
            // TODO add additional checks
            return (Response) generated;
        } else {
            throw new ClassCastException("Generatable must be of type Response.");
        }
    }
    */
}