package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.Generatable;
import org.imsglobal.caliper.entities.assignable.Attempt;
import org.imsglobal.caliper.entities.outcome.Result;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class OutcomeProfile extends org.imsglobal.caliper.profiles.Profile {

    public enum OutcomeActions {
        GRADED("outcome.graded"),
        // POSTED("outcome.posted");
        // REPORTED("outcome.reported");

        NAVIGATED_TO("navigation.navigatedTo");

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

    /**
     * Constructor
     */
    public OutcomeProfile() {

    }

    /**
     * @param key
     * @return localized action string.
     */
    public static String getActionFromBundle(String key) {
        if (OutcomeActions.hasKey(key) || Actions.hasKey(key)) {
            return ResourceBundle.getBundle("actions").getString(key);
        } else {
            throw new IllegalArgumentException("Unrecognized key: " + key);
        }
    }

    /**
     * @param object
     * @return attempt.
     */
    public static Attempt validateObject(Object object) {
        if (object instanceof Attempt) {
            // TODO add additional checks
            return (Attempt) object;
        } else {
            throw new ClassCastException("Object must be of type Attempt.");
        }
    }

    /**
     * @param generated
     * @return assessment.
     */
    public static Result validateGenerated(Generatable generated) {
        if (generated instanceof Result) {
            // TODO add additional checks
            return (Result) generated;
        } else {
            throw new ClassCastException("Generatable must be of type Result.");
        }
    }
}