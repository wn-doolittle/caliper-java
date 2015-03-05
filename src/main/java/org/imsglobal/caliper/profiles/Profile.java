package org.imsglobal.caliper.profiles;

import com.google.common.collect.ImmutableMap;
import org.imsglobal.caliper.events.Event;
import org.imsglobal.caliper.events.NavigationEvent;
import org.imsglobal.caliper.events.ViewEvent;
import org.imsglobal.caliper.validators.ValidatorResult;
import org.imsglobal.caliper.validators.events.EventValidatorContext;
import org.imsglobal.caliper.validators.events.NavigationEventValidator;
import org.imsglobal.caliper.validators.events.ViewEventValidator;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class Profile {

    public enum Actions {
        NAVIGATED_TO("navigation.navigatedTo") {
            @Override
            ValidatorResult validate(Event event) {
                NavigationEvent navigationEvent = (NavigationEvent) event;
                EventValidatorContext<NavigationEvent> validator;
                validator = new EventValidatorContext<>(NavigationEventValidator.action(Actions.NAVIGATED_TO.key()));
                return validator.validate(navigationEvent);
            }
        },
        VIEWED("reading.viewed") {
            @Override
            ValidatorResult validate(Event event) {
                ViewEvent viewEvent = (ViewEvent) event;
                EventValidatorContext<ViewEvent> validator;
                validator = new EventValidatorContext<>(ViewEventValidator.action(Actions.VIEWED.key()));
                return validator.validate(viewEvent);
            }
        },
        UNRECOGNIZED("action.unrecognized") {
            @Override
            ValidatorResult validate(Event event) {
                ValidatorResult result = new ValidatorResult();
                String violation = "Caliper profile conformance: unrecognized action";
                result.errorMessage().appendViolation(violation);
                result.errorMessage().endSentence();
                return result;
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
         * Retrieve bundle key from reverse lookup map with matching localized action value.
         * @param action
         * @return action bundle key
         */
        public static String lookupBundleKeyWithLocalizedAction(String action) {
            ResourceBundle bundle = ResourceBundle.getBundle("actions");
            for (Map.Entry<String, Actions> entry: lookup.entrySet()) {
                if (action.equals(bundle.getString(entry.getKey()))) {
                    return entry.getKey();
                }
            }
            return Actions.UNRECOGNIZED.key();
        }

        /**
         * Retrieve constant from reverse lookup map after matching on the action bundle key.
         * @param key
         * @return constant
         */
        public static Profile.Actions lookupConstantWithActionKey(String key) {
            return lookup.get(key);
        }

        /**
         * Retrieve constant from reverse lookup map after matching the localized action value against its bundle key.
         * @param action
         * @return constant
         */
        public static Profile.Actions lookupConstantWithLocalizedAction(String action) {
            ResourceBundle bundle = ResourceBundle.getBundle("actions");
            for (Map.Entry<String, Actions> entry: lookup.entrySet()) {
                if (action.equals(bundle.getString(entry.getKey()))) {
                    return entry.getValue();
                }
            }
            return Actions.UNRECOGNIZED;
        }

        /**
         * Validate method implemented by each enum constant.
         * @param event
         */
        abstract ValidatorResult validate(Event event);

        /**
         * Match action to enum constant and then validate event.
         * @param event
         * @return error message if validation errors are encountered.
         */
        protected static ValidatorResult validateEvent(Event event) {
            return Actions.lookupConstantWithLocalizedAction(event.getAction()).validate(event);
        }
    }

    /**
     * Constructor
     */
    public Profile() {

    }

    /**
     * Get localized action string.
     * @param key
     * @return
     */
    public static String getLocalizedAction(String key) {
        if (Profile.Actions.hasKey(key)) {
            return ProfileUtils.getLocalizedAction(key);
        } else {
            throw new IllegalArgumentException("Event action is unrecognized (" + key + ")");
        }
    }

    /**
     * Validate Event.
     * @param event
     * @return ValidatorResult
     */
    public static ValidatorResult validateEvent(@Nonnull Event event) {
        return Actions.validateEvent(event);
    }
}