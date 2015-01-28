package org.imsglobal.caliper.profiles;

import com.google.common.collect.ImmutableMap;
import org.imsglobal.caliper.events.AnnotationEvent;
import org.imsglobal.caliper.validators.AnnotationEventValidator;
import org.imsglobal.caliper.validators.EventValidator;
import org.imsglobal.caliper.validators.EventValidatorContext;
import org.imsglobal.caliper.validators.ValidatorResult;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AnnotationProfile {

    public enum Actions {
        ATTACHED("annotation.attached") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new AnnotationEventValidator());
                return validator.validate(event);
            }
        },
        BOOKMARKED("annotation.bookmarked") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new AnnotationEventValidator());
                return validator.validate(event);
            }
        },
        CLASSIFIED("annotation.classified") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new AnnotationEventValidator());
                return validator.validate(event);
            }
        },
        COMMENTED("annotation.commented") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new AnnotationEventValidator());
                return validator.validate(event);
            }
        },
        DESCRIBED("annotation.described") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new AnnotationEventValidator());
                return validator.validate(event);
            }
        },
        HIGHLIGHTED("annotation.highlighted") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new AnnotationEventValidator());
                return validator.validate(event);
            }
        },
        IDENTIFIED("annotation.identified") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new AnnotationEventValidator());
                return validator.validate(event);
            }
        },
        LIKED("annotation.liked") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new AnnotationEventValidator());
                return validator.validate(event);
            }
        },
        LINKED("annotation.linked") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new AnnotationEventValidator());
                return validator.validate(event);
            }
        },
        RANKED("annotation.ranked") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new AnnotationEventValidator());
                return validator.validate(event);
            }
        },
        QUESTIONED("annotation.questioned") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new AnnotationEventValidator());
                return validator.validate(event);
            }
        },
        RECOMMENDED("annotation.recommended") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new AnnotationEventValidator());
                return validator.validate(event);
            }
        },
        REPLIED("annotation.replied") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new AnnotationEventValidator());
                return validator.validate(event);
            }
        },
        SHARED("annotation.shared") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new AnnotationEventValidator());
                return validator.validate(event);
            }
        },
        SUBSCRIBED("annotation.subscribed") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new AnnotationEventValidator());
                return validator.validate(event);
            }
        },
        TAGGED("annotation.tagged") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new AnnotationEventValidator());
                return validator.validate(event);
            }
        },
        UNRECOGNIZED("action.unrecognized") {
            @Override
            ValidatorResult validate(AnnotationEvent event) {
                ValidatorResult result = new ValidatorResult();
                result.errorMessage().appendText("Caliper Annotation profile conformance: "
                    + EventValidator.Conformance.ACTION_UNRECOGNIZED.violation());
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
         * Lookup key by comparing localized action string against matching bundle value.
         * @param action
         * @return
         */
        public static String lookupKey(String action) {
            ResourceBundle bundle = ResourceBundle.getBundle("actions");
            for (Map.Entry<String, Actions> entry: lookup.entrySet()) {
                if (action.equals(bundle.getString(entry.getKey()))) {
                    return entry.getKey();
                }
            }
            return Actions.UNRECOGNIZED.key();
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
            return Actions.matchConstant(event.getAction()).validate(event);
        }

        /**
         * Match the event action string against the bundle value and return
         * the corresponding constant.
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
    public AnnotationProfile() {

    }

    /**
     * Validate AssessmentItemEvent.
     * @param event
     * @return ValidatorResult
     */
    public static ValidatorResult validateEvent(AnnotationEvent event) {
        return Actions.validateEvent(event);
    }
}