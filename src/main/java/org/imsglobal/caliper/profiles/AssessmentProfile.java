package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.Generatable;
import org.imsglobal.caliper.entities.assessment.Assessment;
import org.imsglobal.caliper.entities.assignable.Attempt;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AssessmentProfile extends org.imsglobal.caliper.profiles.Profile {

    public enum AssessmentActions {
        STARTED("assessment.started"),
        PAUSED("assessment.paused"),
        RESTARTED("assessment.restarted"),
        SUBMITTED("assessment.submitted"),

        NAVIGATED_TO("navigation.navigatedTo");

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
         *
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
            return lookup.containsKey(key);
        }

        /**
         * @param key
         * @return enum constant by reverse lookup
         */
        public static AssessmentActions lookupConstant(String key) {
            return lookup.get(key);
        }
    }

    /**
     * Constructor
     */
    public AssessmentProfile() {

    }

    /**
     * @param key
     * @return localized action string.
     */
    public static String getActionFromBundle(String key) {
        if (AssessmentActions.hasKey(key) || Actions.hasKey(key)) {
            return ResourceBundle.getBundle("actions").getString(key);
        } else {
            throw new IllegalArgumentException("Unrecognized key: " + key);
        }
    }

    /**
     * @param object
     * @return assessment.
     */
    public static Assessment validateObject(Object object) {
        if (object instanceof Assessment) {
            // TODO add additional checks
            return (Assessment) object;
        } else {
            throw new ClassCastException("Object must be of type Assessment.");
        }
    }

    /**
     * @param generated
     * @return assessment.
     */
    public static Attempt validateGenerated(Generatable generated) {
        if (generated instanceof Attempt) {
            // TODO add additional checks
            return (Attempt) generated;
        } else {
            throw new ClassCastException("Generatable must be of type Attempt.");
        }
    }
}