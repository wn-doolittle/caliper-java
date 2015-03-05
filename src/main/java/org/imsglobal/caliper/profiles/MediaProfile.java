package org.imsglobal.caliper.profiles;

import com.google.common.collect.ImmutableMap;
import org.imsglobal.caliper.events.MediaEvent;
import org.imsglobal.caliper.validators.ValidatorResult;
import org.imsglobal.caliper.validators.events.EventValidatorContext;
import org.imsglobal.caliper.validators.events.MediaEventValidator;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MediaProfile {

    public enum Actions {
        ENABLEDCLOSEDCAPTIONING("media.accessibility.enabledClosedCaptioning") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext<MediaEvent> validator;
                validator = new EventValidatorContext<>(MediaEventValidator.action(Actions.ENABLEDCLOSEDCAPTIONING.key()));
                return validator.validate(event);
            }
        },
        DISABLEDCLOSEDCAPTIONING("media.accessibility.disabledClosedCaptioning") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext<MediaEvent> validator;
                validator = new EventValidatorContext<>(MediaEventValidator.action(Actions.DISABLEDCLOSEDCAPTIONING.key()));
                return validator.validate(event);
            }
        },
        CHANGEDVOLUME("media.audio.changedVolume") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext<MediaEvent> validator;
                validator = new EventValidatorContext<>(MediaEventValidator.action(Actions.CHANGEDVOLUME.key()));
                return validator.validate(event);
            }
        },
        MUTED("media.audio.muted") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext<MediaEvent> validator;
                validator = new EventValidatorContext<>(MediaEventValidator.action(Actions.MUTED.key()));
                return validator.validate(event);
            }
        },
        UNMUTED("media.audio.unmuted") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext<MediaEvent> validator;
                validator = new EventValidatorContext<>(MediaEventValidator.action(Actions.UNMUTED.key()));
                return validator.validate(event);
            }
        },
        CHANGEDSPEED("media.playback.changedSpeed") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext<MediaEvent> validator;
                validator = new EventValidatorContext<>(MediaEventValidator.action(Actions.CHANGEDSPEED.key()));
                return validator.validate(event);
            }
        },
        ENDED("media.playback.ended") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext<MediaEvent> validator;
                validator = new EventValidatorContext<>(MediaEventValidator.action(Actions.ENDED.key()));
                return validator.validate(event);
            }
        },
        JUMPEDTO("media.playback.jumpedTo") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext<MediaEvent> validator;
                validator = new EventValidatorContext<>(MediaEventValidator.action(Actions.JUMPEDTO.key()));
                return validator.validate(event);
            }
        },
        FORWARDEDTO("media.playback.forwardedTo") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext<MediaEvent> validator;
                validator = new EventValidatorContext<>(MediaEventValidator.action(Actions.FORWARDEDTO.key()));
                return validator.validate(event);
            }
        },
        PAUSED("media.playback.paused") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext<MediaEvent> validator;
                validator = new EventValidatorContext<>(MediaEventValidator.action(Actions.PAUSED.key()));
                return validator.validate(event);
            }
        },
        RESUMED("media.playback.resumed") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext<MediaEvent> validator;
                validator = new EventValidatorContext<>(MediaEventValidator.action(Actions.RESUMED.key()));
                return validator.validate(event);
            }
        },
        REWINDED("media.playback.rewindedTo") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext<MediaEvent> validator;
                validator = new EventValidatorContext<>(MediaEventValidator.action(Actions.REWINDED.key()));
                return validator.validate(event);
            }
        },
        STARTED("media.playback.started") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext<MediaEvent> validator;
                validator = new EventValidatorContext<>(MediaEventValidator.action(Actions.STARTED.key()));
                return validator.validate(event);
            }
        },
        CHANGEDRESOLUTION("media.viewer.changedResolution") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext<MediaEvent> validator;
                validator = new EventValidatorContext<>(MediaEventValidator.action(Actions.CHANGEDRESOLUTION.key()));
                return validator.validate(event);
            }
        },
        CHANGEDSIZE("media.viewer.changedSize") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext<MediaEvent> validator;
                validator = new EventValidatorContext<>(MediaEventValidator.action(Actions.CHANGEDSIZE.key()));
                return validator.validate(event);
            }
        },
        CLOSEDPOPOUT("media.viewer.closedPopout") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext<MediaEvent> validator;
                validator = new EventValidatorContext<>(MediaEventValidator.action(Actions.CLOSEDPOPOUT.key()));
                return validator.validate(event);
            }
        },
        ENTEREDFULLSCREEN("media.viewer.enteredFullScreen") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext<MediaEvent> validator;
                validator = new EventValidatorContext<>(MediaEventValidator.action(Actions.ENTEREDFULLSCREEN.key()));
                return validator.validate(event);
            }
        },
        EXITEDFULLSCREEN("media.viewer.exitedFullScreen") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext<MediaEvent> validator;
                validator = new EventValidatorContext<>(MediaEventValidator.action(Actions.EXITEDFULLSCREEN.key()));
                return validator.validate(event);
            }
        },
        OPENEDPOPOUT("media.viewer.openedPopout") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                EventValidatorContext<MediaEvent> validator;
                validator = new EventValidatorContext<>(MediaEventValidator.action(Actions.OPENEDPOPOUT.key()));
                return validator.validate(event);
            }
        },
        UNRECOGNIZED("action.unrecognized") {
            @Override
            ValidatorResult validate(MediaEvent event) {
                ValidatorResult result = new ValidatorResult();
                String violation = "Caliper Media profile conformance: unrecognized action";
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
        public static MediaProfile.Actions lookupConstantWithActionKey(String key) {
            return lookup.get(key);
        }

        /**
         * Retrieve constant from reverse lookup map after matching the localized action value against its bundle key.
         * @param action
         * @return constant
         */
        public static MediaProfile.Actions lookupConstantWithLocalizedAction(String action) {
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
        abstract ValidatorResult validate(MediaEvent event);

        /**
         * Match action to enum constant and then validate event.
         * @param event
         * @return error message if validation errors are encountered.
         */
        protected static ValidatorResult validateEvent(MediaEvent event) {
            return Actions.lookupConstantWithLocalizedAction(event.getAction()).validate(event);
        }
    }

    /**
     * Constructor
     */
    public MediaProfile() {

    }

    /**
     * Get localized action string.
     * @param key
     * @return
     */
    public static String getLocalizedAction(String key) {
        if (MediaProfile.Actions.hasKey(key)) {
            return ProfileUtils.getLocalizedAction(key);
        } else {
            throw new IllegalArgumentException("MediaEvent action is unrecognized (" + key + ")");
        }
    }

    /**
     * Validate MediaEvent.
     * @param event
     * @return ValidatorResult
     */
    public static ValidatorResult validateEvent(@Nonnull MediaEvent event) {
        return Actions.validateEvent(event);
    }
}