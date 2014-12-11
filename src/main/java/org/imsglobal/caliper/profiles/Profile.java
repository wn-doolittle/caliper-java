package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.entities.media.MediaLocation;
import org.imsglobal.caliper.entities.reading.Frame;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class Profile {

    public enum Actions {
        DOWNLOADED("item.downloaded"),
        UPLOADED("item.uploaded"),

        LOGGED_IN("session.loggedIn"),
        LOGGED_OUT("session.loggedOut"),

        NAVIGATED_TO("navigation.navigatedTo");

        private final String key;
        private static final Map<String, Actions> lookup = new HashMap<String, Actions>();

        /**
         * Create reverse lookup hash map
         */
        static {
            for (Actions constants : Actions.values())
                lookup.put(constants.key(), constants);
        }

        /**
         * Constructor
         * @param key
         */
        private Actions(String key) {
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
        public static Actions lookupConstant(String key) {
            return lookup.get(key);
        }
    }

    /**
     * Constructor
     */
    public Profile() {

    }

    /**
     * @param key
     * @return localized action string.
     */
    public static String getNavigatedToActionFromBundle(String key) {
        if (key.equals("navigation.navigatedTo")) {
            return ResourceBundle.getBundle("actions").getString(key);
        } else {
            throw new IllegalArgumentException("Unrecognized key: " + key);
        }
    }

    /**
     * @param object
     * @return DigitalResource.
     */
    public static DigitalResource validateDigitalResource(Object object) {
        if (object instanceof DigitalResource) {
            // TODO add additional checks
            return (DigitalResource) object;
        } else {
            throw new ClassCastException("Object must be of type CreativeWork.");
        }
    }

    /**
     * @param target
     * @return digital resource.
     */
    public static Targetable validateTarget(Targetable target) {
        if (target instanceof Frame) {
            // TODO add additional checks
            return (Frame) target;
        } else if (target instanceof MediaLocation) {
            // TODO add additional checks
            return (MediaLocation) target;
        } else {
            throw new ClassCastException("Object must implement the Targetable interface.");
        }
    }
}