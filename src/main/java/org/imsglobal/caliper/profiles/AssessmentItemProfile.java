package org.imsglobal.caliper.profiles;

import com.google.common.collect.ImmutableMap;
import org.imsglobal.caliper.events.AssessmentItemEvent;
import org.imsglobal.caliper.validators.ValidatorResult;
import org.imsglobal.caliper.validators.events.AssessmentItemEventValidator;
import org.imsglobal.caliper.validators.events.EventValidatorContext;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AssessmentItemProfile {

    public enum Actions {
        STARTED("assessment.item.started") {
            @Override
            ValidatorResult validate(AssessmentItemEvent event) {
                EventValidatorContext<AssessmentItemEvent> validator;
                validator = new EventValidatorContext<>(AssessmentItemEventValidator.action(Actions.STARTED.key()));
                return validator.validate(event);
            }
        },
        COMPLETED("assessment.item.completed") {
            @Override
            ValidatorResult validate(AssessmentItemEvent event) {
                EventValidatorContext<AssessmentItemEvent> validator;
                validator = new EventValidatorContext<>(AssessmentItemEventValidator.action(Actions.COMPLETED.key()));
                return validator.validate(event);
            }
        },
        SKIPPED("assessment.item.skipped") {
            @Override
            ValidatorResult validate(AssessmentItemEvent event) {
                EventValidatorContext<AssessmentItemEvent> validator;
                validator = new EventValidatorContext<>(AssessmentItemEventValidator.action(Actions.SKIPPED.key()));
                return validator.validate(event);
            }
        },
        REVIEWED("assessment.item.reviewed") {
            @Override
            ValidatorResult validate(AssessmentItemEvent event) {
                EventValidatorContext<AssessmentItemEvent> validator;
                validator = new EventValidatorContext<>(AssessmentItemEventValidator.action(Actions.REVIEWED.key()));
                return validator.validate(event);
            }
        },
        VIEWED("assessment.item.viewed") {
            @Override
            ValidatorResult validate(AssessmentItemEvent event) {
                EventValidatorContext<AssessmentItemEvent> validator;
                validator = new EventValidatorContext<>(AssessmentItemEventValidator.action(Actions.VIEWED.key()));
                return validator.validate(event);
            }
        },
        UNRECOGNIZED("action.unrecognized") {
            @Override
            ValidatorResult validate(AssessmentItemEvent event) {
                ValidatorResult result = new ValidatorResult();
                String violation = "Caliper AssessmentItem profile conformance: unrecognized action";
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
        public static AssessmentItemProfile.Actions lookupConstantWithActionKey(String key) {
            return lookup.get(key);
        }

        /**
         * Retrieve constant from reverse lookup map after matching the localized action value against its bundle key.
         * @param action
         * @return constant
         */
        public static AssessmentItemProfile.Actions lookupConstantWithLocalizedAction(String action) {
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
        abstract ValidatorResult validate(AssessmentItemEvent event);

        /**
         * Match action to enum constant and then validate event.
         * @param event
         * @return error message if validation errors are encountered.
         */
        protected static ValidatorResult validateEvent(AssessmentItemEvent event) {
            return Actions.lookupConstantWithLocalizedAction(event.getAction()).validate(event);
        }
    }

    /**
     * Constructor
     */
    public AssessmentItemProfile() {

    }

    /**
     * Get localized action string.
     * @param key
     * @return
     */
    public static String getLocalizedAction(String key) {
        if (AssessmentItemProfile.Actions.hasKey(key)) {
            return ProfileUtils.getLocalizedAction(key);
        } else {
            throw new IllegalArgumentException("AssessmentItemEvent action is unrecognized (" + key + ")");
        }
    }

    /**
     * Validate AssessmentItemEvent.
     * @param event
     * @return ValidatorResult
     */
    public static ValidatorResult validateEvent(@Nonnull AssessmentItemEvent event) {
        return Actions.validateEvent(event);
    }
}