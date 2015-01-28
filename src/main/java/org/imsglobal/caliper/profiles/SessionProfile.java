package org.imsglobal.caliper.profiles;

import com.google.common.collect.ImmutableMap;
import org.imsglobal.caliper.events.SessionEvent;
import org.imsglobal.caliper.validators.EventValidatorContext;
import org.imsglobal.caliper.validators.SessionLoginEventValidator;
import org.imsglobal.caliper.validators.SessionLogoutEventValidator;
import org.imsglobal.caliper.validators.SessionTimeoutEventValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SessionProfile extends org.imsglobal.caliper.profiles.Profile {

    public enum Actions {
        LOGGEDIN("session.loggedIn") {
            @Override
            String validate(SessionEvent event) {
                EventValidatorContext validator = new EventValidatorContext(new SessionLoginEventValidator());
                return validator.validate(event);
            }
        },
        LOGGEDOUT("session.loggedOut") {
            @Override
            String validate(SessionEvent event) {
                EventValidatorContext validator = new EventValidatorContext(new SessionLogoutEventValidator());
                return validator.validate(event);
            }
        },
        TIMEDOUT("session.timedOut") {
            @Override
            String validate(SessionEvent event) {
                EventValidatorContext validator = new EventValidatorContext(new SessionTimeoutEventValidator());
                return validator.validate(event);
            }
        },
        UNRECOGNIZED("action.unrecognized") {
            @Override
            String validate(SessionEvent event) {
                return "Caliper Session profile conformance: " + Profile.Conformance.ACTION_UNRECOGNIZED.violation();
            }
        };

        private final String key;
        private static Map<String, Actions> lookup;

        /**
         * Create reverse lookup hash map
         */
        static {
            Map<String, Actions> map = new HashMap<String, Actions>();
            for (Actions constants : Actions.values()) {
                map.put(constants.key(), constants);
            }
            lookup = ImmutableMap.copyOf(map);
        }

        /**
         * Private constructor
         * @param key
         */
        private Actions(final String key) {
            this.key = key;
        }

        /**
         * Resource bundle key
         * @return key
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
         * Validate method implemented by each enum constant.
         * @param event
         */
        abstract String validate(SessionEvent event);

        /**
         * Match action to enum constant and then validate event.
         * @param event
         * @return error message if validation errors are encountered.
         */
        protected static String validateEvent(SessionEvent event) {
            return Actions.matchConstant(event.getAction()).validate(event);
        }

        /**
         * Match the event action string against the bundle value and return the corresponding constant.
         * @param action
         * @return constant
         */
        private static Actions matchConstant(String action) {
            ResourceBundle bundle = ResourceBundle.getBundle("actions");
            for (Map.Entry<String, Actions> entry: lookup.entrySet()) {
                if (action.equals(bundle.getString(entry.getKey()))) {
                    return entry.getValue();
                }
            }
            return Actions.UNRECOGNIZED;
        }
    }

    /**
     * Constructor
     */
    public SessionProfile() {

    }

    /**
     * Validate SessionEvent.  Per Bloch throw an IllegalStateException if programing
     * errors are encountered during object construction.
     * @param event
     */
    public static void validateEvent(SessionEvent event) throws IllegalStateException {
        String message = Actions.validateEvent(event);
        if (message.length() > 0) {
            throw new IllegalStateException(message);
        }
    }
}