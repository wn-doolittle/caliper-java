package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.entities.reading.Frame;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ReadingProfile extends org.imsglobal.caliper.profiles.Profile {

    public enum ReadingActions {
        SEARCHED("reading.searched"),
        VIEWED("reading.viewed");

        private final String key;
        private static final Map<String, ReadingActions> lookup = new HashMap<String, ReadingActions>();

        /**
         * Create reverse lookup hash map
         */
        static {
            for (ReadingActions constants : ReadingActions.values())
                lookup.put(constants.key(), constants);
        }

        /**
         * Constructor
         * @param key
         */
        private ReadingActions(String key) {
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
        public static ReadingActions lookupConstant(String key) {
            return lookup.get(key);
        }
    }

    /**
     * Constructor
     */
    public ReadingProfile() {

    }

    /**
     * @param key
     * @return localized action string.
     */
    public static String getActionFromBundle(String key) {
        if (ReadingActions.hasKey(key) || Actions.hasKey(key)) {
            return ResourceBundle.getBundle("actions").getString(key);
        } else {
            throw new IllegalArgumentException("Unrecognized key: " + key);
        }
    }

    /**
     * @param key
     * @return localized action string.
     */
    public static String getViewActionFromBundle(String key) {
        if (key.equals(ReadingActions.VIEWED.key())) {
            return ResourceBundle.getBundle("actions").getString(key);
        } else {
            throw new IllegalArgumentException("Unrecognized key: " + key);
        }
    }

    /**
     * @param object
     * @return target DigitalResource.
     */
    public static DigitalResource validateObject(Object object) {
        if (object instanceof DigitalResource) {
            // TODO add additional checks
            return (DigitalResource) object;
        } else {
            throw new ClassCastException("Object must be of type DigitalResource.");
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