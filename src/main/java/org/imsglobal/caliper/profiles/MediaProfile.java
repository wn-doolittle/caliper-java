package org.imsglobal.caliper.profiles;

import com.google.common.collect.ImmutableMap;
import org.imsglobal.caliper.events.MediaEvent;
import org.imsglobal.caliper.validators.EventValidator;
import org.imsglobal.caliper.validators.EventValidatorContext;
import org.imsglobal.caliper.validators.MediaEventValidator;
import org.imsglobal.caliper.validators.ValidatorResult;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MediaProfile {

    public enum Actions {
        ENABLEDCLOSEDCAPTIONING("media.accessibility.enabledClosedCaptioning") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new MediaEventValidator());
                return validator.validate(event);
            }
        },
        DISABLEDCLOSEDCAPTIONING("media.accessibility.disabledClosedCaptioning") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new MediaEventValidator());
                return validator.validate(event);
            }
        },
        CHANGEDVOLUME("media.audio.changedVolume") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new MediaEventValidator());
                return validator.validate(event);
            }
        },
        MUTED("media.audio.muted") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new MediaEventValidator());
                return validator.validate(event);
            }
        },
        UNMUTED("media.audio.unmuted") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new MediaEventValidator());
                return validator.validate(event);
            }
        },
        CHANGEDSPEED("media.playback.changedSpeed") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new MediaEventValidator());
                return validator.validate(event);
            }
        },
        ENDED("media.playback.ended") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new MediaEventValidator());
                return validator.validate(event);
            }
        },
        JUMPEDTO("media.playback.jumpedTo") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new MediaEventValidator());
                return validator.validate(event);
            }
        },
        FORWARDEDTO("media.playback.forwardedTo") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new MediaEventValidator());
                return validator.validate(event);
            }
        },
        PAUSED("media.playback.paused") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new MediaEventValidator());
                return validator.validate(event);
            }
        },
        RESUMED("media.playback.resumed") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new MediaEventValidator());
                return validator.validate(event);
            }
        },
        REWINDED("media.playback.rewindedTo") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new MediaEventValidator());
                return validator.validate(event);
            }
        },
        STARTED("media.playback.started") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new MediaEventValidator());
                return validator.validate(event);
            }
        },
        CHANGEDRESOLUTION("media.viewer.changedResolution") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new MediaEventValidator());
                return validator.validate(event);
            }
        },
        CHANGEDSIZE("media.viewer.changedSize") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new MediaEventValidator());
                return validator.validate(event);
            }
        },
        CLOSEDPOPOUT("media.viewer.closedPopout") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new MediaEventValidator());
                return validator.validate(event);
            }
        },
        ENTEREDFULLSCREEN("media.viewer.enteredFullScreen") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new MediaEventValidator());
                return validator.validate(event);
            }
        },
        EXITEDFULLSCREEN("media.viewer.exitedFullScreen") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new MediaEventValidator());
                return validator.validate(event);
            }
        },
        OPENEDPOPOUT("media.viewer.openedPopout") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext validator;
                validator = new EventValidatorContext(new MediaEventValidator());
                return validator.validate(event);
            }
        },
        UNRECOGNIZED("action.unrecognized") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                ValidatorResult result = new ValidatorResult();
                result.errorMessage().appendText("Caliper Media profile conformance: "
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
        abstract ValidatorResult validate(MediaEvent event);

        /**
         * Match action to enum constant and then validate event.
         * @param event
         * @return error message if validation errors are encountered.
         */
        protected static ValidatorResult validateEvent(MediaEvent event) {
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
    public MediaProfile() {

    }

    /**
     * Validate AssessmentItemEvent.
     * @param event
     * @return ValidatorResult
     */
    public static ValidatorResult validateEvent(@Nonnull MediaEvent event) {
        return Actions.validateEvent(event);
    }
}