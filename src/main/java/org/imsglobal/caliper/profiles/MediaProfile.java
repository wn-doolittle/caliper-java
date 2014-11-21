package org.imsglobal.caliper.profiles;

import org.imsglobal.caliper.entities.media.MediaObject;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MediaProfile extends org.imsglobal.caliper.profiles.Profile {

    public enum MediaActions {
        ENABLEDCLOSEDCAPTIONING("media.accessibility.enabledClosedCaptioning"),
        DISABLEDCLOSEDCAPTIONING("media.accessibility.disabledClosedCaptioning"),

        CHANGEDVOLUME("media.audio.changedVolume"),
        MUTED("media.audio.muted"),
        UNMUTED("media.audio.unmuted"),

        CHANGEDSPEED("media.playback.changedSpeed"),
        ENDED("media.playback.ended"),
        JUMPEDTO("media.playback.jumpedTo"),
        FORWARDEDTO("media.playback.forwardedTo"),
        PAUSED("media.playback.paused"),
        RESUMED("media.playback.resumed"),
        REWINDED("media.playback.rewindedTo"),
        STARTED("media.playback.started"),

        CHANGEDRESOLUTION("media.viewer.changedResolution"),
        CHANGEDVIEWERSIZE("media.viewer.changedViewerSize"),
        CLOSEDPOPOUT("media.viewer.closedPopout"),
        ENTEREDFULLSCREEN("media.viewer.enteredFullScreen"),
        EXITEDFULLSCREEN("media.viewer.exitedFullScreen"),
        OPENEDPOPOUT("media.viewer.openedPopout"),

        NAVIGATED_TO("navigation.navigatedTo");

        private final String key;
        private static final Map<String, MediaActions> lookup = new HashMap<String, MediaActions>();

        /**
         * Create reverse lookup hash map
         */
        static {
            for (MediaActions constants : MediaActions.values())
                lookup.put(constants.key(), constants);
        }

        /**
         * Constructor
         *
         * @param key
         */
        private MediaActions(String key) {
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
        public static MediaActions lookupConstant(String key) {
            return lookup.get(key);
        }
    }

    /**
     * Constructor
     */
    public MediaProfile() {

    }

    /**
     * @param key
     * @return localized action string.
     */
    public static String getActionFromBundle(String key) {
        if (MediaActions.hasKey(key) || Actions.hasKey(key)) {
            return ResourceBundle.getBundle("actions").getString(key);
        } else {
            throw new IllegalArgumentException("Unrecognized key: " + key);
        }
    }

    /**
     * @param object
     * @return activityContext object.
     */
    public static MediaObject validateObject(Object object) {
        if (object instanceof MediaObject) {
            //TODO CONSIDER ADDING REVERSE LOOKUP TO ENUM SO THAT ENUM CAN BE RETURNED FOR USE IN SWITCH STATEMENT
            String type = ((MediaObject) object).getType();
            if (type.equals(MediaObject.Type.AUDIO_OBJECT.uri())) {
                // TODO CHECK REQUIRED PROPS
            } else if (type.equals(MediaObject.Type.IMAGE_OBJECT.uri())) {
                // TODO CHECK REQUIRED PROPS
            } else if (type.equals(MediaObject.Type.VIDEO_OBJECT.uri())) {
                // TODO CHECK REQUIRED PROPS
            } else {
                // THROW ERROR unrecognized URI
            }
            // TODO add additional checks

            return (MediaObject) object;
        } else {
            throw new ClassCastException("Object must be of type MediaObject.");
        }
    }
}