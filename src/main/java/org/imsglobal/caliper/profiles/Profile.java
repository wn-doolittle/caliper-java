package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.DigitalResource;
import org.imsglobal.caliper.entities.Targetable;
import org.imsglobal.caliper.entities.media.MediaLocation;
import org.imsglobal.caliper.entities.reading.Frame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
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

    public enum Conformance {
        ACTION_IS_NULL("an action must be specified."),
        ACTION_UNRECOGNIZED("unrecognized action."),
        ACTOR_NOT_PERSON("actor must be of type Person"),
        ACTOR_NOT_SOFTWAREAPP("actor must be of type SoftwareApplication"),
        CONTEXT_ERROR("context URI must be specified"),
        DURATION_INVALID("duration format is invalid"),
        ENDEDATTIME_SET("endedAtTime must not be specified"),
        EVENT_ILLEGAL_STATE("event constructed in an illegal state."),
        GENERATED_NOT_NULL("a generated object is not required."),
        GENERATED_NOT_SESSION("generated object must be of type Session"),
        OBJECT_NOT_SOFTWAREAPP("object must be of type SoftwareApplication"),
        STARTEDATTIME_IS_NULL("startedAtTime must be specified."),
        TARGET_NOT_DIGITALRESOURCE("target must be of type DigitalResource"),
        TARGET_NOT_SESSION("target must be of type Session"),
        TIME_ERROR("end time must be greater than start time."),
        TYPE_ERROR("type URI must be specified");

        private final String violation;

        /**
         * Private constructor
         * @param violation
         */
        private Conformance(final String violation) {
            this.violation = violation;
        }

        /**
         * @return violation string
         */
        public String violation() {
            return violation;
        }
    }

    /**
     * Logger
     */
    private static final Logger log = LoggerFactory.getLogger(Profile.class);

    /**
     * Constructor
     */
    public Profile() {

    }

    /**
     * Get action localized value from resource bundle.
     * @param key
     * @return action string
     */
    public static String getLocalizedAction(String key) throws MissingResourceException {
        return ResourceBundle.getBundle("actions").getString(key);
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