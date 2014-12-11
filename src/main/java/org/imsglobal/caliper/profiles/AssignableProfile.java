package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.Generatable;
import org.imsglobal.caliper.entities.assignable.AssignableDigitalResource;
import org.imsglobal.caliper.entities.assignable.Attempt;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AssignableProfile extends org.imsglobal.caliper.profiles.Profile {

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

    /**
     * Constructor
     */
    public AssignableProfile() {

    }

    /**
     * @param key
     * @return localized action string.
     */
    public static String getActionFromBundle(String key) {
        if (AssignableActions.hasKey(key) || Actions.hasKey(key)) {
            return ResourceBundle.getBundle("actions").getString(key);
        } else {
            throw new IllegalArgumentException("Unrecognized key: " + key);
        }
    }

    /**
     * @param object
     * @return assignable digital resource.
     */
    public static AssignableDigitalResource validateObject(Object object) {
        if (object instanceof AssignableDigitalResource) {
            // TODO add additional checks
            return (AssignableDigitalResource) object;
        } else {
            throw new ClassCastException("Object must be of type AssignableDigitalResource.");
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
            throw new ClassCastException("Generated must be of type Attempt.");
        }
    }
}