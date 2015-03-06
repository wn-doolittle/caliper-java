package org.imsglobal.caliper.profiles;

import com.google.common.collect.ImmutableMap;
import org.imsglobal.caliper.events.AnnotationEvent;
import org.imsglobal.caliper.validators.ValidatorResult;
import org.imsglobal.caliper.validators.events.AnnotationEventValidator;
import org.imsglobal.caliper.validators.events.EventValidatorContext;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AnnotationProfile {

    public enum Actions {
        ATTACHED("annotation.attached") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext<AnnotationEvent> validator;
                validator = new EventValidatorContext<>(AnnotationEventValidator.action(Actions.ATTACHED.key()));
                return validator.validate(event);
            }
        },
        BOOKMARKED("annotation.bookmarked") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext<AnnotationEvent> validator;
                validator = new EventValidatorContext<>(AnnotationEventValidator.action(Actions.BOOKMARKED.key()));
                return validator.validate(event);
            }
        },
        CLASSIFIED("annotation.classified") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext<AnnotationEvent> validator;
                validator = new EventValidatorContext<>(AnnotationEventValidator.action(Actions.CLASSIFIED.key()));
                return validator.validate(event);
            }
        },
        COMMENTED("annotation.commented") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext<AnnotationEvent> validator;
                validator = new EventValidatorContext<>(AnnotationEventValidator.action(Actions.COMMENTED.key()));
                return validator.validate(event);
            }
        },
        DESCRIBED("annotation.described") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext<AnnotationEvent> validator;
                validator = new EventValidatorContext<>(AnnotationEventValidator.action(Actions.DESCRIBED.key()));
                return validator.validate(event);
            }
        },
        HIGHLIGHTED("annotation.highlighted") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext<AnnotationEvent> validator;
                validator = new EventValidatorContext<>(AnnotationEventValidator.action(Actions.HIGHLIGHTED.key()));
                return validator.validate(event);
            }
        },
        IDENTIFIED("annotation.identified") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext<AnnotationEvent> validator;
                validator = new EventValidatorContext<>(AnnotationEventValidator.action(Actions.IDENTIFIED.key()));
                return validator.validate(event);
            }
        },
        LIKED("annotation.liked") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext<AnnotationEvent> validator;
                validator = new EventValidatorContext<>(AnnotationEventValidator.action(Actions.LIKED.key()));
                return validator.validate(event);
            }
        },
        LINKED("annotation.linked") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext<AnnotationEvent> validator;
                validator = new EventValidatorContext<>(AnnotationEventValidator.action(Actions.LINKED.key()));
                return validator.validate(event);
            }
        },
        RANKED("annotation.ranked") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext<AnnotationEvent> validator;
                validator = new EventValidatorContext<>(AnnotationEventValidator.action(Actions.RANKED.key()));
                return validator.validate(event);
            }
        },
        QUESTIONED("annotation.questioned") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext<AnnotationEvent> validator;
                validator = new EventValidatorContext<>(AnnotationEventValidator.action(Actions.QUESTIONED.key()));
                return validator.validate(event);
            }
        },
        RECOMMENDED("annotation.recommended") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext<AnnotationEvent> validator;
                validator = new EventValidatorContext<>(AnnotationEventValidator.action(Actions.RECOMMENDED.key()));
                return validator.validate(event);
            }
        },
        REPLIED("annotation.replied") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext<AnnotationEvent> validator;
                validator = new EventValidatorContext<>(AnnotationEventValidator.action(Actions.REPLIED.key()));
                return validator.validate(event);
            }
        },
        SHARED("annotation.shared") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext<AnnotationEvent> validator;
                validator = new EventValidatorContext<>(AnnotationEventValidator.action(Actions.SHARED.key()));
                return validator.validate(event);
            }
        },
        SUBSCRIBED("annotation.subscribed") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext<AnnotationEvent> validator;
                validator = new EventValidatorContext<>(AnnotationEventValidator.action(Actions.SUBSCRIBED.key()));
                return validator.validate(event);
            }
        },
        TAGGED("annotation.tagged") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext<AnnotationEvent> validator;
                validator = new EventValidatorContext<>(AnnotationEventValidator.action(Actions.TAGGED.key()));
                return validator.validate(event);
            }
        },
        UNRECOGNIZED("action.unrecognized") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                ValidatorResult result = new ValidatorResult();
                String violation = "Caliper Annotation profile conformance: unrecognized action";
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
        public static AnnotationProfile.Actions lookupConstantWithActionKey(String key) {
            return lookup.get(key);
        }

        /**
         * Retrieve constant from reverse lookup map after matching the localized action value against its bundle key.
         * @param action
         * @return constant
         */
        public static AnnotationProfile.Actions lookupConstantWithLocalizedAction(String action) {
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
        abstract ValidatorResult validate(AnnotationEvent event);

        /**
         * Match action to enum constant and then validate event.
         * @param event
         * @return error message if validation errors are encountered.
         */
        protected static ValidatorResult validateEvent(AnnotationEvent event) {
            return Actions.lookupConstantWithLocalizedAction(event.getAction()).validate(event);
        }
    }

    /**
     * Constructor
     */
    public AnnotationProfile() {

    }

    /**
     * Get localized action string.
     * @param key
     * @return
     */
    public static String getLocalizedAction(String key) {
        if (AnnotationProfile.Actions.hasKey(key)) {
            return ProfileUtils.getLocalizedAction(key);
        } else {
            throw new IllegalArgumentException("AnnotationEvent action is unrecognized (" + key + ")");
        }
    }

    /**
     * Validate AnnotationEvent.
     * @param event
     * @return ValidatorResult
     */
    public static ValidatorResult validateEvent(@Nonnull AnnotationEvent event) {
        return Actions.validateEvent(event);
    }
}